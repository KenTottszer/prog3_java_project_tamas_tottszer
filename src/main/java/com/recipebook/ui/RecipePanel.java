package com.recipebook.ui;

import javax.swing.*;
import java.awt.*;
import com.recipebook.Recipe;

public class RecipePanel extends JPanel {
    private JTextArea recipeDetails;

    public RecipePanel() {
        setLayout(new BorderLayout());
        recipeDetails = new JTextArea();
        recipeDetails.setEditable(false);
        recipeDetails.setLineWrap(true); // Enable line wrapping
        recipeDetails.setWrapStyleWord(true); // Wrap at word boundaries
        JScrollPane scrollPane = new JScrollPane(recipeDetails);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // Disable horizontal scrolling
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED); // Enable vertical scrolling
        add(scrollPane, BorderLayout.CENTER);
    }

    public void displayRecipe(Recipe recipe) {
        if (recipe != null) {
            recipeDetails.setText("Name: " + recipe.getName() + "\n\n" +
                                  "Ingredients:\n" + String.join(", ", recipe.getIngredients()) + "\n\n" +
                                  "Instructions:\n" + recipe.getInstructions() + "\n\n" +
                                  "Tags:\n" + String.join(", ", recipe.getTags()));
        } else {
            recipeDetails.setText("No recipe selected.");
        }
    }

    public void refresh() {
        recipeDetails.setText("No recipe selected. Add or select a recipe to view details.");
    }
}
