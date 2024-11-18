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
            String query = searchField.getText();
            List<Recipe> results = manager.searchRecipes(query);
            listModel.clear();
            for (Recipe recipe : results) {
                listModel.addElement(recipe.getName());
            }

            resultList.addListSelectionListener(event -> {
                if (!event.getValueIsAdjusting()) {
                    String selectedRecipeName = resultList.getSelectedValue();
                    Recipe selectedRecipe = manager.getRecipe(selectedRecipeName);
                    recipePanel.displayRecipe(selectedRecipe);
                }
            });
        });

        add(searchTopPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
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
