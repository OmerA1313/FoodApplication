
package com.example.foodapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.foodapplication.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import adapters.IngredientAdapter;

public class BuildRecipeFragment extends Fragment {
//    private String [] ingredientsDemo = new String[] {"tomato", "onion","garlic","Garlic","Tomato"};
        View view;
        List<String> ingredients = new LinkedList<>();
        //List<String> ingredients = List.of("hello","does","this","work?");
        AutoCompleteTextView ingredientsEditText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_build_recipe, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeEditText();
        initializeIngredientsRecView();
    }

    private void initializeIngredientsRecView() {
        RecyclerView recyclerView = getView().findViewById(R.id.ChosenIngredientsRecView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        IngredientAdapter ingredientAdapter = new IngredientAdapter(ingredients);
        recyclerView.setAdapter(ingredientAdapter);
        getView().findViewById(R.id.buttonAddIngredient).setOnClickListener(buttonAddOnClick(ingredientAdapter));
    }

    private View.OnClickListener buttonAddOnClick(IngredientAdapter adapter){
        String ingredinetName = ingredientsEditText.getText().toString();
        if (!ingredinetName.isEmpty()) {
            ingredientsEditText.getText().clear();
            ingredients.add(ingredinetName);
            adapter.notifyItemInserted(ingredients.size() - 1);
        }
        else{
            // TODO handle this
        }
        return null;
    }

    private void initializeEditText() {
        String[] ingredientsFromFile = readIngredientsFromAsset();
        ingredientsEditText = view.findViewById(R.id.autoCompText_ingredient);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1,ingredientsFromFile);
        ingredientsEditText.setAdapter(adapter);
    }

    private String[] readIngredientsFromAsset() {
        List<String> allIngredients = new LinkedList<>();
        BufferedReader bReader;
        try { //TODO maybe use using?
            bReader = new BufferedReader(new InputStreamReader(getResources().getAssets().open("ingredients.txt")));
            String line = bReader.readLine();
            while(line!= null){
                allIngredients.add(line);
                line = bReader.readLine();
            }
            bReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allIngredients.toArray(new String[allIngredients.size()]);
    }
}