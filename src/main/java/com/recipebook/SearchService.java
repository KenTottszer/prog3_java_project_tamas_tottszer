package com.recipebook;

import java.util.ArrayList;
import java.util.List;

public class SearchService {
    private List<Recipe> recipes;

    public SearchService(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public List<Recipe> searchByKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return new ArrayList<>(); // Return empty list for empty or null keyword
        }

        List<Recipe> results = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getName().toLowerCase().contains(keyword.toLowerCase()) || 
                recipe.getTags().toString().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(recipe);
            }
        }
        return results;
    }

    public List<Recipe> searchByIngredient(String ingredient) {
        if (ingredient == null || ingredient.trim().isEmpty()) {
            return new ArrayList<>(); // Return empty list for empty or null ingredient
        }

        List<Recipe> results = new ArrayList<>();
        for (Recipe recipe : recipes) {
            for (String ing : recipe.getIngredients()) {
                if (ing.toLowerCase().contains(ingredient.toLowerCase())) {
                    results.add(recipe);
                    break;
                }
            }
        }
        return results;
    }
}
