package adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapplication.R;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientVH> {

    List<String> ingredients;

    public IngredientAdapter(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_in_list,parent,false);
        return new IngredientVH(view).linkAdapter(this);

    }

    @Override
    public void onBindViewHolder(@NonNull IngredientVH holder, int position) {
        holder.TVingredientName.setText(ingredients.get(position));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    class IngredientVH extends RecyclerView.ViewHolder {

        TextView TVingredientName;
        private IngredientAdapter ingredientAdapter;

        public IngredientVH(@NonNull View itemView) {
            super(itemView);
            TVingredientName = itemView.findViewById(R.id.TextViewIngredientName);
            itemView.findViewById(R.id.ButtonRemoveIngredient).setOnClickListener(view -> {
                ingredientAdapter.ingredients.remove(getAdapterPosition());
                ingredientAdapter.notifyItemRemoved(getAdapterPosition());
            });
        }

        public IngredientVH linkAdapter(IngredientAdapter adapter) {
            this.ingredientAdapter = adapter;
            return this;
        }
    }
}
