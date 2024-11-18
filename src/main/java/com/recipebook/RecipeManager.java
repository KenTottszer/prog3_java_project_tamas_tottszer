package com.recipebook;

import java.util.ArrayList;
import java.util.List;

public class RecipeManager {
    private List<Recipe> recipes;

    public RecipeManager() {
        recipes = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void deleteRecipe(String recipeName) {
        recipes.removeIf(recipe -> recipe.getName().equalsIgnoreCase(recipeName));
    }

    public Recipe getRecipe(String name) {
        return recipes.stream()
                      .filter(recipe -> recipe.getName().equalsIgnoreCase(name))
                      .findFirst()
                      .orElse(null);
    }

    public void updateRecipe(Recipe updatedRecipe) {
        for (int i = 0; i < recipes.size(); i++) {
            if (recipes.get(i).getName().equalsIgnoreCase(updatedRecipe.getName())) {
                recipes.set(i, updatedRecipe);
                return;
            }
        }
    }

    public List<Recipe> getAllRecipes() {
        return recipes;
    }

    public List<Recipe> searchRecipes(String query) {
        List<Recipe> results = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getName().toLowerCase().contains(query.toLowerCase()) ||
                recipe.getTags().toString().toLowerCase().contains(query.toLowerCase()) ||
                recipe.getIngredients().toString().toLowerCase().contains(query.toLowerCase())) {
                results.add(recipe);
            }
        }
        return results;
    }
}
