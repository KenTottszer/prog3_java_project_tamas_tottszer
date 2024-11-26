package com.recipebook;

import javax.swing.SwingUtilities;
import com.recipebook.ui.MainFrame;
import java.util.logging.Logger;

public class RecipeBook {
    private static final Logger logger = Logger.getLogger(RecipeBook.class.getName());
    public static void main(String[] args) {
        RecipeManager manager = new RecipeManager();
        String filePath = "data/recipes.ser"; 
        manager.getAllRecipes().addAll(RecipeSerialiser.loadRecipes(filePath));

        SwingUtilities.invokeLater(() -> 
            new MainFrame(manager)
        );

        logger.info("Welcome to the Digital Recipe Book!");
    }
}
