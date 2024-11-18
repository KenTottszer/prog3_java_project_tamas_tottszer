package com.recipebook.ui;

import javax.swing.*;
import java.awt.*;
import com.recipebook.Recipe;
import com.recipebook.RecipeManager;
import com.recipebook.RecipeSerialiser;

public class MainFrame extends JFrame {
    private RecipeManager recipeManager;
    private RecipePanel recipePanel;
    private SearchPanel searchPanel;

    public MainFrame(RecipeManager manager) {
        this.recipeManager = manager;
        setTitle("Digital Recipe Book");
        setSize(800, 600);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create panels
        recipePanel = new RecipePanel();
        searchPanel = new SearchPanel(recipeManager, recipePanel);

        recipePanel.setMinimumSize(new Dimension(300, 600));
        searchPanel.setMinimumSize(new Dimension(300, 600));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, recipePanel, searchPanel);
        splitPane.setResizeWeight(0.65); // Set resizing weight
        add(splitPane, BorderLayout.CENTER);

        // Dynamically set divider location after rendering
        SwingUtilities.invokeLater(() -> {
            splitPane.setDividerLocation((int) (getWidth() * 0.65)); // Set divider to 65% of frame width
        });

        // Bottom toolbar with buttons
        JPanel bottomToolbar = new JPanel(new GridBagLayout());
        bottomToolbar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomToolbar.setBackground(Color.LIGHT_GRAY);

        JButton addRecipeButton = new JButton("Add Recipe");
        addRecipeButton.setPreferredSize(new Dimension(150, 40));
        addRecipeButton.addActionListener(e -> openAddRecipeDialog());

        JButton editRecipeButton = new JButton("Edit Recipe");
        editRecipeButton.setPreferredSize(new Dimension(150, 40));
        editRecipeButton.addActionListener(e -> openEditRecipeDialog());

        JButton deleteRecipeButton = new JButton("Delete Recipe");
        deleteRecipeButton.setPreferredSize(new Dimension(150, 40));
        deleteRecipeButton.addActionListener(e -> deleteSelectedRecipe());

        JButton saveRecipesButton = new JButton("Save Recipes");
        saveRecipesButton.setPreferredSize(new Dimension(150, 40));
        saveRecipesButton.addActionListener(e -> saveRecipes());

        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(150, 40));
        exitButton.addActionListener(e -> System.exit(0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        bottomToolbar.add(addRecipeButton, gbc);
        gbc.gridx = 1;
        bottomToolbar.add(editRecipeButton, gbc);
        gbc.gridx = 2;
        bottomToolbar.add(deleteRecipeButton, gbc);
        gbc.gridx = 3;
        bottomToolbar.add(saveRecipesButton, gbc);
        gbc.gridx = 4;
        bottomToolbar.add(exitButton, gbc);

        add(bottomToolbar, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void openAddRecipeDialog() {
        AddRecipeDialog dialog = new AddRecipeDialog(this, recipeManager);
        dialog.setVisible(true);
        recipePanel.refresh();
    }

    private void openEditRecipeDialog() {
        String selectedRecipeName = recipePanel.getSelectedRecipeName();
        if (selectedRecipeName == null) {
            JOptionPane.showMessageDialog(this, "Please select a recipe to edit.", "Edit Recipe", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Recipe selectedRecipe = recipeManager.getRecipe(selectedRecipeName);
        if (selectedRecipe != null) {
            EditRecipeDialog dialog = new EditRecipeDialog(this, recipeManager, selectedRecipe);
            dialog.setVisible(true);

            // Update search panel and recipe panel dynamically
            String updatedRecipeName = selectedRecipe.getName();
            searchPanel.updateRecipeInResults(selectedRecipeName, updatedRecipeName);
            recipePanel.displayRecipe(selectedRecipe);
        }
    }

    private void deleteSelectedRecipe() {
        String selectedRecipeName = recipePanel.getSelectedRecipeName();
        if (selectedRecipeName == null) {
            JOptionPane.showMessageDialog(this, "Please select a recipe to delete.", "Delete Recipe", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the selected recipe?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            recipeManager.deleteRecipe(selectedRecipeName);
            searchPanel.removeRecipeFromResults(selectedRecipeName); // Remove from SearchPanel dynamically
            recipePanel.refresh();
            JOptionPane.showMessageDialog(this, "Recipe deleted successfully.", "Delete Recipe", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void saveRecipes() {
        RecipeSerialiser.saveRecipes(recipeManager.getAllRecipes(), "data/recipes.ser");
        JOptionPane.showMessageDialog(this, "Recipes saved successfully!", "Save Recipes", JOptionPane.INFORMATION_MESSAGE);
    }
}
