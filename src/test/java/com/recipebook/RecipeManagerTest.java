package com.recipebook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeManagerTest {
    private RecipeManager recipeManager;

    @BeforeEach
    void setUp() {
        recipeManager = new RecipeManager();
    }

    @Test
    void testAddRecipe() {
        Recipe recipe = new Recipe("Pasta", Arrays.asList("Pasta", "Tomato Sauce"), "Boil pasta and add sauce", Arrays.asList("Italian", "Dinner"));
        recipeManager.addRecipe(recipe);
        assertEquals(1, recipeManager.getAllRecipes().size());
        assertEquals("Pasta", recipeManager.getAllRecipes().get(0).getName());
    }

    @Test
    void testDeleteRecipe() {
        Recipe recipe = new Recipe("Pasta", Arrays.asList("Pasta", "Tomato Sauce"), "Boil pasta and add sauce", Arrays.asList("Italian", "Dinner"));
        recipeManager.addRecipe(recipe);
        recipeManager.deleteRecipe("Pasta");
        assertEquals(0, recipeManager.getAllRecipes().size());
    }

    @Test
    void testGetRecipe() {
        Recipe recipe = new Recipe("Pasta", Arrays.asList("Pasta", "Tomato Sauce"), "Boil pasta and add sauce", Arrays.asList("Italian", "Dinner"));
        recipeManager.addRecipe(recipe);
        Recipe retrievedRecipe = recipeManager.getRecipe("Pasta");
        assertNotNull(retrievedRecipe);
        assertEquals("Pasta", retrievedRecipe.getName());
    }

    @Test
    void testUpdateRecipe() {
        Recipe recipe = new Recipe("Pasta", Arrays.asList("Pasta", "Tomato Sauce"), "Boil pasta and add sauce", Arrays.asList("Italian", "Dinner"));
        recipeManager.addRecipe(recipe);

        Recipe updatedRecipe = new Recipe("Pasta", Arrays.asList("Pasta", "Pesto Sauce"), "Boil pasta and add pesto", Arrays.asList("Italian", "Lunch"));
        recipeManager.updateRecipe(updatedRecipe);

        Recipe retrievedRecipe = recipeManager.getRecipe("Pasta");
        assertNotNull(retrievedRecipe);
        assertEquals("Pesto Sauce", retrievedRecipe.getIngredients().get(1));
        assertEquals("Boil pasta and add pesto", retrievedRecipe.getInstructions());
        assertEquals("Lunch", retrievedRecipe.getTags().get(1));
    }

    @Test
    void testGetAllRecipes() {
        Recipe recipe1 = new Recipe("Pasta", Arrays.asList("Pasta", "Tomato Sauce"), "Boil pasta and add sauce", Arrays.asList("Italian", "Dinner"));
        Recipe recipe2 = new Recipe("Salad", Arrays.asList("Lettuce", "Tomato", "Cucumber"), "Mix all ingredients", Arrays.asList("Healthy", "Lunch"));

        recipeManager.addRecipe(recipe1);
        recipeManager.addRecipe(recipe2);

        List<Recipe> allRecipes = recipeManager.getAllRecipes();
        assertEquals(2, allRecipes.size());
        assertEquals("Pasta", allRecipes.get(0).getName());
        assertEquals("Salad", allRecipes.get(1).getName());
    }

    @Test
    void testSearchRecipes() {
        Recipe recipe1 = new Recipe("Pasta", Arrays.asList("Pasta", "Tomato Sauce"), "Boil pasta and add sauce", Arrays.asList("Italian", "Dinner"));
        Recipe recipe2 = new Recipe("Salad", Arrays.asList("Lettuce", "Tomato", "Cucumber"), "Mix all ingredients", Arrays.asList("Healthy", "Lunch"));

        recipeManager.addRecipe(recipe1);
        recipeManager.addRecipe(recipe2);

        List<Recipe> results = recipeManager.searchRecipes("Tomato");
        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(recipe -> recipe.getName().equals("Pasta")));
        assertTrue(results.stream().anyMatch(recipe -> recipe.getName().equals("Salad")));

        results = recipeManager.searchRecipes("Healthy");
        assertEquals(1, results.size());
        assertEquals("Salad", results.get(0).getName());
    }
}
