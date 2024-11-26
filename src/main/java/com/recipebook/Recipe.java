package com.recipebook;

import java.io.Serializable;
import java.util.List;

public class Recipe implements Serializable {
    private String name;
    private List<String> ingredients;
    private String instructions; 
    private List<String> tags;

    public Recipe(String name, List<String> ingredients, String instructions, List<String> tags) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.tags = tags;
    }

    public String getName() { return name; }
    public List<String> getIngredients() { return ingredients; }
    public String getInstructions() { return instructions; }
    public List<String> getTags() { return tags; }

    public void setName(String name) { this.name = name; }
    public void setIngredients(List<String> ingredients) { this.ingredients = ingredients; }
    public void setInstructions(String instructions) { this.instructions = instructions; } 
    public void setTags(List<String> tags) { this.tags = tags; }
}
