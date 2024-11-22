package com.recipebook.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import com.recipebook.Recipe;
import com.recipebook.RecipeManager;
import java.util.List;

public class AddRecipeDialog extends JDialog {
    public AddRecipeDialog(JFrame parent, RecipeManager manager) {
        super(parent, "Add Recipe", true);

        // Set size dynamically based on screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int dialogWidth = screenSize.width / 2;
        int dialogHeight = screenSize.height / 2;
        setSize(dialogWidth, dialogHeight);

        // Set dialog location to the right side of the screen
        int x = screenSize.width - dialogWidth - 50; // Leave some margin from the right edge
        int y = (screenSize.height - dialogHeight) / 2; // Center vertically
        setLocation(x, y);

        // Wrap content with a border for padding
        JPanel contentPanel = new JPanel(new GridLayout(5, 2, 10, 10)); // Add spacing between rows and columns
        contentPanel.setBorder(new EmptyBorder(15, 15, 15, 15)); // Add padding around the panel

        JTextField nameField = new JTextField();
        JTextArea ingredientsArea = new JTextArea();
        JTextArea instructionsArea = new JTextArea();
        JTextField tagsField = new JTextField();

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
            String name = nameField.getText();
            List<String> ingredients = List.of(ingredientsArea.getText().split(","));
            String instructions = instructionsArea.getText();
            List<String> tags = List.of(tagsField.getText().split(","));

            Recipe recipe = new Recipe(name, ingredients, instructions, tags);
            manager.addRecipe(recipe);
            dispose();
        });

        contentPanel.add(new JLabel()); // Empty placeholder for alignment
        contentPanel.add(saveButton);

        setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);
    }
}
