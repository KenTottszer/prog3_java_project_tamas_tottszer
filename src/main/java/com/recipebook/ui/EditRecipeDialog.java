package com.recipebook.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import com.recipebook.Recipe;
import com.recipebook.RecipeManager;
import java.util.List;

public class EditRecipeDialog extends JDialog {
    public EditRecipeDialog(JFrame parent, RecipeManager manager, Recipe recipe) {
        super(parent, "Edit Recipe", true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int dialogWidth = screenSize.width / 2;
        int dialogHeight = screenSize.height / 2;
        setSize(dialogWidth, dialogHeight);

        int x = screenSize.width - dialogWidth - 50; 
        int y = (screenSize.height - dialogHeight) / 2; 
        setLocation(x, y);


        JPanel contentPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15)); 

        JTextField nameField = new JTextField(recipe.getName());
        JTextArea ingredientsArea = new JTextArea(String.join(",", recipe.getIngredients()));
        JTextArea instructionsArea = new JTextArea(recipe.getInstructions());
        JTextField tagsField = new JTextField(String.join(",", recipe.getTags()));

        contentPanel.add(new JLabel("Name:"));
        contentPanel.add(nameField);
        contentPanel.add(new JLabel("Ingredients (comma-separated):"));
        contentPanel.add(new JScrollPane(ingredientsArea));
        contentPanel.add(new JLabel("Instructions:"));
        contentPanel.add(new JScrollPane(instructionsArea));
        contentPanel.add(new JLabel("Tags (comma-separated):"));
        contentPanel.add(tagsField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            recipe.setName(nameField.getText());
            recipe.setIngredients(List.of(ingredientsArea.getText().split(",")));
            recipe.setInstructions(instructionsArea.getText());
            recipe.setTags(List.of(tagsField.getText().split(",")));

            manager.updateRecipe(recipe);
            dispose();
        });

        contentPanel.add(new JLabel());
        contentPanel.add(saveButton);

        setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
    }
}
