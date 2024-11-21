package com.recipebook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchServiceTest {
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        List<Recipe> recipes = Arrays.asList(
                new Recipe("Pasta", Arrays.asList("Pasta", "Tomato Sauce"), "Boil pasta and add sauce", Arrays.asList("Italian", "Dinner")),
                new Recipe("Salad", Arrays.asList("Lettuce", "Tomato", "Cucumber"), "Mix all ingredients", Arrays.asList("Healthy", "Lunch")),
                new Recipe("Soup", Arrays.asList("Water", "Vegetables"), "Boil vegetables in water", Arrays.asList("Vegan", "Dinner"))
        );
        searchService = new SearchService(recipes);
    }

    @ParameterizedTest(name = "Keyword: {0}, Expected Recipe: {1}")
    @CsvSource({
        "Pasta, Pasta",
        "Healthy, Salad",
        "italian, Pasta",
        "Pizza, ", // No match
        "'', " // Empty keyword
    })
    void testSearchByKeyword(String keyword, String expectedRecipeName) {
        List<Recipe> results = searchService.searchByKeyword(keyword);

        if (expectedRecipeName == null || expectedRecipeName.isEmpty()) {
            assertTrue(results.isEmpty(), "Expected no results for keyword: " + keyword);
        } else {
            assertEquals(1, results.size(), "Expected one result for keyword: " + keyword);
            assertEquals(expectedRecipeName, results.get(0).getName());
        }
    }

    @ParameterizedTest(name = "Ingredient: {0}, Expected Recipes: {1}")
    @CsvSource({
        "Tomato, 2", // Matches "Pasta" and "Salad"
        "water, 1", // Matches "Soup"
        "Cheese, 0", // No match
        "'', 0" // Empty ingredient
    })
    void testSearchByIngredient(String ingredient, int expectedCount) {
        List<Recipe> results = searchService.searchByIngredient(ingredient);
        assertEquals(expectedCount, results.size(), "Expected " + expectedCount + " results for ingredient: " + ingredient);
    }
}
