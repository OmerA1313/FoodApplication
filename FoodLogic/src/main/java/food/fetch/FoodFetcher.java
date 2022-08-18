package food.fetch;

import android.app.Activity;
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import food.recipes.Recipe;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FoodFetcher {
    private OkHttpClient m_Client;
    private final String API_KEY = "9a7708cc03d0454faef33998131360cf";
    private final String BASE_URL = "https://api.spoonacular.com";
    private List<Recipe> recipesList;
    private Activity activity;
    private Context context;


    public FoodFetcher(Activity i_Activity) {
        m_Client = new OkHttpClient();
        activity = i_Activity;
    }

    public List<Recipe> GetRandomRecipes(Integer i_numberOfRecipes) throws IOException {
        String recipeJson = "";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BASE_URL + "/recipes/random").newBuilder();
        urlBuilder.addQueryParameter("apiKey", API_KEY);
        urlBuilder.addQueryParameter("limitLicense", "true");
        urlBuilder.addQueryParameter("number", i_numberOfRecipes.toString());
        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .build();

        Response response = m_Client.newCall(request).execute();
        recipeJson = response.body().string();
        recipesList = Recipe.ParseJson(recipeJson);
        return recipesList;
    }

    public static String[] getAllingredients() {
        List<String> ingredinets = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\oatar\\AndroidStudioProjects\\FoodApplication\\FoodLogic\\src\\main\\java\\food\\fetch\\ingredients- plain.txt"))) {
            String line = br.readLine();

            while (line != null) {
                ingredinets.add(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ingredinets.toArray(new String[ingredinets.size()]);
    }
}


//        @Override
//        public void onFailure(@NotNull Call call, @NotNull IOException e) {
//            System.out.println("request failed");
//
//        }
//
//        @Override
//        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//            requestThread[0] = Thread.currentThread();
//            String recipeJson = response.body().string();
//            Gson gson = new Gson();
//            JsonElement element = new JsonParser().parse(recipeJson);
//            JsonObject object = element.getAsJsonObject();
//            JsonArray recipes = object.getAsJsonArray("recipes");
//            //JsonElement recipe = recipes.get(0);
//            randomRecipes[0] = gson.fromJson(recipes,Recipe[].class);
//        }


