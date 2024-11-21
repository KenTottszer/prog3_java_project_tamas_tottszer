package com.recipebook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class RecipeSerialiser {

    private RecipeSerialiser() {
        throw new UnsupportedOperationException("Utility class");
    }
    
    private static final Logger logger = Logger.getLogger(RecipeSerialiser.class.getName());

    private static final String DEFAULT_FILE_PATH = "data/recipes.ser";

    public static void saveRecipes(List<Recipe> recipes, String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(recipes);
        } catch (IOException e) {
            logger.severe("Error saving recipes: " + e.getMessage());
        }
    }

    public static List<Recipe> loadRecipes(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            logger.info("No recipes found. Initializing empty recipe collection.");
            return new ArrayList<>();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = in.readObject();
            if (obj instanceof List<?>) {
                List<?> rawList = (List<?>) obj;
                List<Recipe> recipeList = new ArrayList<>();
                for (Object item : rawList) {
                    if (item instanceof Recipe) {
                        recipeList.add((Recipe) item);
                    } else {
                        throw new IOException("List contains non-Recipe elements");
                    }
                }
                return recipeList;
            } else {
                throw new IOException("Unexpected data format");
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.severe("Error loading recipes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void saveRecipes(List<Recipe> recipes) {
        saveRecipes(recipes, DEFAULT_FILE_PATH);
    }

    public static List<Recipe> loadRecipes() {
        return loadRecipes(DEFAULT_FILE_PATH);
    }
}
