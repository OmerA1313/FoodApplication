package com.example.foodapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import food.ingredients.Ingredient;
import food.recipes.Recipe;

public class RecipeInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Recipe recipe = (Recipe) intent.getSerializableExtra("recipe");
        Picasso.get().load(recipe.getImage()).into((ImageView) findViewById(R.id.ImageView_info_recipe));
        ((CollapsingToolbarLayout)(findViewById(R.id.collapsing_toolbar))).setTitle(recipe.getTitle());
       // ((TextView)(findViewById(R.id.TextView_info_prepTime))).setText(recipe.getReadyInMinutes() +" Minutes");
       // ((TextView)(findViewById(R.id.TextView_info_servings))).setText(recipe.getServings()+ "Persons");
        ((TextView)(findViewById(R.id.instructions))).setText(recipe.getInstructions());
            TextView TVIngredints = findViewById(R.id.ingredient);
            TextView TVAmount = findViewById(R.id.measure);
        for (Ingredient ingredient : recipe.getExtendedIngredients()) {
            TVIngredints.append("\n  \u2022 " + ingredient.getName());
            TVAmount.append("\n" + ingredient.getAmountWithUnit());
        }
    }
}