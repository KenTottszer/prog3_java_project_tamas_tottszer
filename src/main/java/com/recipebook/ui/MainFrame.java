package com.recipebook.ui;

import javax.swing.*;
import java.awt.*;
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
        setMinimumSize(new Dimension(800, 600)); // Set minimum window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create panels
        recipePanel = new RecipePanel();
        searchPanel = new SearchPanel(recipeManager, recipePanel);

        recipePanel.setMinimumSize(new Dimension(300, 600)); // Minimum width for RecipePanel
        searchPanel.setMinimumSize(new Dimension(300, 600)); // Minimum width for SearchPanel

        // Use JSplitPane for even distribution
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, recipePanel, searchPanel);
        splitPane.setResizeWeight(0.65); // Default resizing behavior
        add(splitPane, BorderLayout.CENTER);

        // Set initial divider location dynamically after the frame is packed
        SwingUtilities.invokeLater(() -> {
            int frameWidth = getWidth();
            splitPane.setDividerLocation((int) (frameWidth * 0.65)); // 65% of the current frame width
        });

        // Create custom bottom toolbar
        JPanel bottomToolbar = new JPanel(new GridBagLayout());
        bottomToolbar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        bottomToolbar.setBackground(Color.LIGHT_GRAY);

        JButton addRecipeButton = new JButton("Add Recipe");
        addRecipeButton.setPreferredSize(new Dimension(150, 40));
        addRecipeButton.addActionListener(e -> openAddRecipeDialog());

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
        bottomToolbar.add(saveRecipesButton, gbc);
        gbc.gridx = 2;
        bottomToolbar.add(exitButton, gbc);

        add(bottomToolbar, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void openAddRecipeDialog() {
        AddRecipeDialog dialog = new AddRecipeDialog(this, recipeManager);
        dialog.setVisible(true);
        recipePanel.refresh();
    }

    private void saveRecipes() {
        RecipeSerialiser.saveRecipes(recipeManager.getAllRecipes(), "data/recipes.ser");
        JOptionPane.showMessageDialog(this, "Recipes saved successfully!", "Save Recipes", JOptionPane.INFORMATION_MESSAGE);
    }
}
