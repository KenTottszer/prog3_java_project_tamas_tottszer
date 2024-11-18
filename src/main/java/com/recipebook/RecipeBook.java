package com.recipebook;

import javax.swing.SwingUtilities;
import com.recipebook.ui.MainFrame;

public class RecipeBook {

    public static void main(String[] args) {
        // Load recipes from the serialized file
        RecipeManager manager = new RecipeManager();
        String filePath = "data/recipes.ser"; // Ensure the path matches your project's structure
        manager.getAllRecipes().addAll(RecipeSerialiser.loadRecipes(filePath));

        // Initialize and display the GUI
        SwingUtilities.invokeLater(() -> {
            new MainFrame(manager);
        });

        System.out.println("Welcome to the Digital Recipe Book!");
    }
}
