package com.example.foodapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.foodapplication.databinding.ActivityMainBinding;
import com.example.foodapplication.fragments.BuildRecipeFragment;
import com.example.foodapplication.fragments.HomeFragment;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import adapters.RandomRecipeAdapter;
import food.fetch.FoodFetcher;
import food.recipes.Recipe;

public class MainActivity extends AppCompatActivity {
    RandomRecipeAdapter randomRecipeAdapter;
    ActivityMainBinding binding;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    FoodFetcher foodFetcher;

    private HomeFragment homeFragment;
    private BuildRecipeFragment buildRecipeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        homeFragment = new HomeFragment(this);
        initNavBarListeners();
        replaceFragment(homeFragment);

    }

    private void initNavBarListeners() {

        binding.bottomNavigationView.setOnItemSelectedListener(item ->{
            switch (item.getItemId()){
                case R.id.navBar_home:
                    replaceFragment(homeFragment);
                    break;

                case R.id.navBar_recipe_builder:
                    if (buildRecipeFragment == null) {
                        buildRecipeFragment = new BuildRecipeFragment();
                    }
                    replaceFragment(buildRecipeFragment);
                    break;
            }
            return true;
        });

//        binding.bottomNavigationView.setOnItemReselectedListener(item ->{
//            switch (item.getItemId()){
//
//                case R.id.navBar_home:
//                    replaceFragment(homeFragment);
//                case R.id.navBar_recipe_builder:
//                    replaceFragment(buildRecipeFragment);
//                    break;
//            }
//        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }

    private void populateCardView(List<Recipe> randomRecipes) {
        recyclerView = findViewById(R.id.recycler_random);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(new RandomRecipeAdapter(MainActivity.this, randomRecipes));
    }

    private List<Recipe> fetchRecipes() {
        Future futureObj;
        futureObj = Executors.newSingleThreadExecutor().submit(()->foodFetcher.GetRandomRecipes(10));
        List<Recipe> randomRecipes = null;
        try {
            randomRecipes = (List<Recipe>) futureObj.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return randomRecipes;
    }
}