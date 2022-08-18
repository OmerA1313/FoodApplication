package food.recipes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import food.ingredients.Ingredient;
import food.ingredients.IngredientInRecipe;

public class Recipe implements Serializable {
    private String title;
    private String id;
    private int readyInMinutes;
    private int servings;
    private int aggregateLikes;
    private Ingredient[] extendedIngredients;
    private List<IngredientInRecipe> missedIngredients;
    private String image;
    private String sourceUrl;
    private String instructions;

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public static List<Recipe> ParseJson(String recipeJson){
        Gson gson = new Gson();
        JsonElement element = new JsonParser().parse(recipeJson);
        JsonObject object = element.getAsJsonObject();
        JsonArray recipes = object.getAsJsonArray("recipes");
        List<Recipe> recipesList = Arrays.asList(gson.fromJson(recipes, Recipe[].class));
        return recipesList;
    }

    public int getReadyInMinutes() {
        return readyInMinutes;
    }

    public int getServings() {
        return servings;
    }

    public Ingredient[] getExtendedIngredients() {
        return extendedIngredients;
    }

    public List<IngredientInRecipe> getMissedIngredients() {
        return missedIngredients;
    }

    public String getImage() {
        return image;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getInstructions() {
        return instructions;
    }

    public int getLikes() {
        return aggregateLikes;
    }
}