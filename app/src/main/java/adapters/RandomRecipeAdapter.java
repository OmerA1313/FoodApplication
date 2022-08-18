package adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapplication.R;
import com.example.foodapplication.RecipeInfoActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import food.recipes.Recipe;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder> {
    Context context;
    List<Recipe> recipeList;

    public RandomRecipeAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipes,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) {
        holder.TextView_title.setText(recipeList.get(position).getTitle());
        holder.TextView_title.setSelected(true);
        holder.TextView_likes.setText(recipeList.get(position).getLikes()+ " Likes");
        holder.TextView_servings.setText(recipeList.get(position).getServings() + " Servings");
        holder.TextView_time.setText(recipeList.get(position).getReadyInMinutes() + " Minutes");
        Picasso.get().load(recipeList.get(position).getImage()).into(holder.ImageView_RecipeImg);

        holder.random_recipe_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardView cardView = holder.random_recipe_cardView;
                Recipe clickedRecipe = recipeList.get(position);
                Intent intent = new Intent(cardView.getContext(), RecipeInfoActivity.class);
                intent.putExtra("recipe", clickedRecipe);
                cardView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}

class RandomRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView random_recipe_cardView;
    TextView TextView_title, TextView_servings,TextView_likes,TextView_time;
    ImageView ImageView_RecipeImg;
    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        random_recipe_cardView = itemView.findViewById(R.id.random_recipe_cardView);
        TextView_title = itemView.findViewById(R.id.TextView_title);
        TextView_servings = itemView.findViewById(R.id.TextView_servings);
        TextView_likes = itemView.findViewById(R.id.TextView_likes);
        TextView_time = itemView.findViewById(R.id.TextView_time);
        ImageView_RecipeImg = itemView.findViewById(R.id.ImageView_RecipeImg);

    }
}
