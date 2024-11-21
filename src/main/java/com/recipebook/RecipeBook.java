package com.recipebook;

import javax.swing.SwingUtilities;
import com.recipebook.ui.MainFrame;
import java.util.logging.Logger;

public class RecipeBook {
    private static final Logger logger = Logger.getLogger(RecipeBook.class.getName());
    public static void main(String[] args) {
        // Load recipes from the serialized file
        RecipeManager manager = new RecipeManager();
        String filePath = "data/recipes.ser"; // Ensure the path matches your project's structure
        manager.getAllRecipes().addAll(RecipeSerialiser.loadRecipes(filePath));

        // Initialize and display the GUI
        SwingUtilities.invokeLater(() -> 
            new MainFrame(manager)
        );

        logger.info("Welcome to the Digital Recipe Book!");
    }
}
