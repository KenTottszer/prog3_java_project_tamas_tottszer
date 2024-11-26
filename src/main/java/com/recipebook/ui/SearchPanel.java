package com.recipebook.ui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.recipebook.Recipe;
import com.recipebook.RecipeManager;

public class SearchPanel extends JPanel {
    private JList<String> resultList;
    private DefaultListModel<String> listModel;

    public SearchPanel(RecipeManager manager, RecipePanel recipePanel) {
        setLayout(new BorderLayout());

        JPanel searchTopPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Search");

        searchTopPanel.add(searchField, BorderLayout.CENTER);
        searchTopPanel.add(searchButton, BorderLayout.EAST);

        listModel = new DefaultListModel<>();
        resultList = new JList<>(listModel);
        resultList.setLayoutOrientation(JList.VERTICAL);

        JScrollPane scrollPane = new JScrollPane(resultList);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        searchButton.addActionListener(e -> {
            String query = searchField.getText().trim();
            if (query.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a search term.", "Search Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            List<Recipe> results = manager.searchRecipes(query);
            updateResultList(results);
        });

        resultList.addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                String selectedRecipeName = resultList.getSelectedValue();
                Recipe selectedRecipe = manager.getRecipe(selectedRecipeName);
                recipePanel.displayRecipe(selectedRecipe);
            }
        });

        JButton showAllButton = new JButton("Show All Recipes");
        showAllButton.addActionListener(e -> {
            List<Recipe> allRecipes = manager.getAllRecipes();
            updateResultList(allRecipes);
        });

        add(searchTopPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(showAllButton, BorderLayout.SOUTH);
    }


    private void updateResultList(List<Recipe> recipes) {
        listModel.clear();
        for (Recipe recipe : recipes) {
            listModel.addElement(recipe.getName());
        }
    }

    public void removeRecipeFromResults(String recipeName) {
        if (listModel.contains(recipeName)) {
            listModel.removeElement(recipeName);
        }
    }

    public void updateRecipeInResults(String oldName, String newName) {
        int index = listModel.indexOf(oldName);
        if (index != -1) {
            listModel.set(index, newName);
        }
    }
}
