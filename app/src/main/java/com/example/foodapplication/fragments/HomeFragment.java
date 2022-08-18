package com.example.foodapplication.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodapplication.MainActivity;
import com.example.foodapplication.R;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import adapters.RandomRecipeAdapter;
import food.fetch.FoodFetcher;
import food.recipes.Recipe;

public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Activity activity;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private FoodFetcher foodFetcher;
    private View view;
    private boolean needFetch = true;

    public HomeFragment(Activity i_activity){
        activity = i_activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (needFetch) {
            List<Recipe> randomRecipes = fetchRecipes();
            view = inflater.inflate(R.layout.fragment_home, container, false);
            populateCardView(randomRecipes);
            needFetch = false;
        }

        return view;
    }

    private void populateCardView(List<Recipe> randomRecipes) {
        recyclerView = view.findViewById(R.id.recycler_random);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        recyclerView.setAdapter(new RandomRecipeAdapter(getActivity(), randomRecipes));
    }

    private List<Recipe> fetchRecipes() {
        if (foodFetcher == null) {
            foodFetcher = new FoodFetcher(activity);
        }

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