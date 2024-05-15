package com.example.fitnessapp.model;

public class FoodItem {
    private String foodId;
    private String name;
    private long calories;
    private boolean selected;

    public FoodItem() {
    }

    public FoodItem(String foodId, String name, long calories) {
        this.foodId = foodId;
        this.name = name;
        this.calories = calories;
        this.selected = false;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public long getCalories() { // int yerine long olarak değiştirildi
        return calories;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
