package food.ingredients;

import java.io.Serializable;

public class Ingredient implements Serializable {
    private String id;
    private String aisle;
    private String image;
    private String name;
    private float amount;
    private String unit;

    public String getId() {
        return id;
    }

    public String getAisle() {
        return aisle;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public float getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public String getAmountWithUnit() {
        return amount + " " + unit;
    }
}
