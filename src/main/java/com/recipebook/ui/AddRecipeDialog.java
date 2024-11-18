package com.recipebook.ui;

import javax.swing.*;
import java.awt.*;
import com.recipebook.Recipe;
import com.recipebook.RecipeManager;
import java.util.List;

public class AddRecipeDialog extends JDialog {
    public AddRecipeDialog(JFrame parent, RecipeManager manager) {
        super(parent, "Add Recipe", true);
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));

        JTextField nameField = new JTextField();
        JTextArea ingredientsArea = new JTextArea();
        JTextArea instructionsArea = new JTextArea();
        JTextField tagsField = new JTextField();

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Ingredients (comma-separated):"));
        add(new JScrollPane(ingredientsArea));
        add(new JLabel("Instructions:"));
        add(new JScrollPane(instructionsArea));
        add(new JLabel("Tags (comma-separated):"));
        add(tagsField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            List<String> ingredients = List.of(ingredientsArea.getText().split(","));
            String instructions = instructionsArea.getText();
            List<String> tags = List.of(tagsField.getText().split(","));

            Recipe recipe = new Recipe(name, ingredients, instructions, tags);
            manager.addRecipe(recipe); // Pass the Recipe object
            dispose();
        });

        add(saveButton);
    }
}
