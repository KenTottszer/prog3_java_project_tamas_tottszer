package com.recipebook;

import org.junit.jupiter.api.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeSerialiserTest {
    private static final String TEST_FILE_PATH = "test_recipes.ser";

    @AfterEach
    void cleanUp() {
        File testFile = new File(TEST_FILE_PATH);
        if (testFile.exists()) {
            assertTrue(testFile.delete(), "Failed to delete test file");
        }
    }

    @Test
    void testSaveAndLoadRecipes() {
        List<Recipe> recipes = Arrays.asList(
                new Recipe("Pasta", Arrays.asList("Pasta", "Tomato Sauce"), "Boil pasta and add sauce", Arrays.asList("Italian", "Dinner")),
                new Recipe("Salad", Arrays.asList("Lettuce", "Tomato", "Cucumber"), "Mix all ingredients", Arrays.asList("Healthy", "Lunch"))
        );

        RecipeSerialiser.saveRecipes(recipes, TEST_FILE_PATH);

        List<Recipe> loadedRecipes = RecipeSerialiser.loadRecipes(TEST_FILE_PATH);

        assertNotNull(loadedRecipes);
        assertEquals(2, loadedRecipes.size());
        assertEquals("Pasta", loadedRecipes.get(0).getName());
        assertEquals("Salad", loadedRecipes.get(1).getName());
    }

    @Test
    void testLoadFromNonExistentFile() {
        List<Recipe> loadedRecipes = RecipeSerialiser.loadRecipes("non_existent_file.ser");

        assertNotNull(loadedRecipes);
        assertTrue(loadedRecipes.isEmpty());
    }

    @Test
    void testSaveAndLoadEmptyList() {
        RecipeSerialiser.saveRecipes(Arrays.asList(), TEST_FILE_PATH);

        List<Recipe> loadedRecipes = RecipeSerialiser.loadRecipes(TEST_FILE_PATH);

        assertNotNull(loadedRecipes);
        assertTrue(loadedRecipes.isEmpty());
    }

    @Test
    void testDefaultFilePath() {
        List<Recipe> recipes = Arrays.asList(
                new Recipe("Soup", Arrays.asList("Water", "Vegetables"), "Boil vegetables in water", Arrays.asList("Healthy", "Dinner"))
        );

        RecipeSerialiser.saveRecipes(recipes);

        List<Recipe> loadedRecipes = RecipeSerialiser.loadRecipes();

        assertNotNull(loadedRecipes);
        assertEquals(1, loadedRecipes.size());
        assertEquals("Soup", loadedRecipes.get(0).getName());

        File defaultFile = new File("data/recipes.ser");
        if (defaultFile.exists()) {
            assertTrue(defaultFile.delete(), "Failed to delete default file");
        }
    }
}
