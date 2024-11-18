package com.recipebook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeSerialiser {

    private static final String DEFAULT_FILE_PATH = "data/recipes.ser";

    public static void saveRecipes(List<Recipe> recipes, String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(recipes);
        } catch (IOException e) {
            System.err.println("Error saving recipes: " + e.getMessage());
        }
    }

    public static List<Recipe> loadRecipes(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("No recipes found. Initializing empty recipe collection.");
            return new ArrayList<>();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Recipe>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading recipes: " + e.getMessage());
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
