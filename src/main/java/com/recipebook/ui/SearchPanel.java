package com.recipebook.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.recipebook.Recipe;
import com.recipebook.RecipeManager;

public class SearchPanel extends JPanel {
    public SearchPanel(RecipeManager manager, RecipePanel recipePanel) {
        setLayout(new BorderLayout());

        // Create the top panel for search field and button
        JPanel searchTopPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Search");

        // Add search field and button to the top panel
        searchTopPanel.add(searchField, BorderLayout.CENTER);
        searchTopPanel.add(searchButton, BorderLayout.EAST);

        // Result list
        JList<String> resultList = new JList<>();
        resultList.setLayoutOrientation(JList.VERTICAL);
        resultList.setFixedCellWidth(-1); // Allow list cells to wrap

        JScrollPane scrollPane = new JScrollPane(resultList);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // Disable horizontal scrolling
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Search button action
        searchButton.addActionListener(e -> {
            String query = searchField.getText();
            List<Recipe> results = manager.searchRecipes(query);
            DefaultListModel<String> model = new DefaultListModel<>();
            for (Recipe recipe : results) {
                model.addElement(recipe.getName());
            }
            resultList.setModel(model);

            resultList.addListSelectionListener(event -> {
                if (!event.getValueIsAdjusting()) {
                    String selectedRecipeName = resultList.getSelectedValue();
                    Recipe selectedRecipe = manager.getRecipe(selectedRecipeName);
                    recipePanel.displayRecipe(selectedRecipe);
                }
            });
        });

        // Add components to the main SearchPanel
        add(searchTopPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
