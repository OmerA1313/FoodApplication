package food.ingredients;

public class IngredientInRecipe {
    private Ingredient m_Ingredient;
    private Measure m_Amount;

    public IngredientInRecipe(Ingredient m_Ingredient, Measure m_Amount) {
        this.m_Ingredient = m_Ingredient;
        this.m_Amount = m_Amount;
    }

    public Ingredient GetM_Ingredient() {
        return m_Ingredient;
    }



}
