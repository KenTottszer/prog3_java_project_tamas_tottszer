package com.recipebook.ui;

import javax.swing.*;
import java.awt.*;
import com.recipebook.Recipe;

public class RecipePanel extends JPanel {
    private JTextArea recipeDetails;
    private String selectedRecipeName; // Store the name of the selected recipe

    public RecipePanel() {
        setLayout(new BorderLayout());
        recipeDetails = new JTextArea();
        recipeDetails.setEditable(false);
        recipeDetails.setLineWrap(true);
        recipeDetails.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(recipeDetails);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void displayRecipe(Recipe recipe) {
        if (recipe != null) {
            selectedRecipeName = recipe.getName(); // Track the selected recipe name
            recipeDetails.setText("Name: " + recipe.getName() + "\n\n" +
                                  "Ingredients:\n" + String.join(", ", recipe.getIngredients()) + "\n\n" +
                                  "Instructions:\n" + recipe.getInstructions() + "\n\n" +
                                  "Tags:\n" + String.join(", ", recipe.getTags()));
        } else {
            selectedRecipeName = null; // Clear the selection if no recipe is selected
            recipeDetails.setText("No recipe selected.");
        }
    }

    public String getSelectedRecipeName() {
        return selectedRecipeName;
    }

    public void refresh() {
        recipeDetails.setText("No recipe selected. Add or select a recipe to view details.");
        selectedRecipeName = null;
    }
}
