# prog3_java_project_tamas_tottszer
**Programming 3 Java Project**  
Author: **Tamas Tottszer (KCP1K1)**  

Welcome to the **Digital Recipe Book** project! This application allows users to manage recipes through a user-friendly graphical interface, including adding, editing, deleting, searching, and saving recipes.  

---

## Table of Contents
1. [Features](#features)
2. [Getting Started](#getting-started)
3. [How to Run](#how-to-run)
4. [How to Use the Application](#how-to-use-the-application)
5. [Technologies Used](#technologies-used)

---

## Features
- **Add Recipes**: Create new recipes by specifying the name, ingredients, instructions, and tags.
- **Edit Recipes**: Modify existing recipes directly in the application.
- **Delete Recipes**: Remove recipes from the system.
- **Search Recipes**: Search recipes by name, ingredients, or tags.
- **View All Recipes**: Display all saved recipes in a list.
- **Save Recipes**: Persist your recipes to a file for future use.
- **Load Recipes**: Automatically load saved recipes upon application start.

---

## Getting Started

### Prerequisites
To run the application, ensure you have the following installed:
- **Java Development Kit (JDK)** version 17 or above
- **Apache Maven** for dependency management and building the project

### Clone the Repository
Clone this repository to your local machine:

git clone https://github.com/<your-repository-link>/prog3_java_project_tamas_tottszer.git


### Build the Project
Navigate to the project folder and build the project using Maven:

cd prog3_java_project_tamas_tottszer
mvn clean install


---

## How to Run

### Run the Application
You can start the application via Maven:

mvn exec:java -Dexec.mainClass="com.recipebook.RecipeBook"


---

## How to Use the Application

### Launching the Application
When you run the application, a GUI window titled **"Digital Recipe Book"** will appear.

### Main Features
1. **Add a Recipe**:
   - Click the "Add Recipe" button in the toolbar.
   - Fill in the recipe details (name, ingredients, instructions, and tags).
   - Click "Save" to add the recipe to your collection.

2. **Edit a Recipe**:
   - Select a recipe from the list in the **Search Panel**.
   - Click the "Edit Recipe" button in the toolbar.
   - Modify the recipe details in the popup dialog.
   - Click "Save" to update the recipe.

3. **Delete a Recipe**:
   - Select a recipe from the list in the **Search Panel**.
   - Click the "Delete Recipe" button in the toolbar.
   - Confirm the deletion when prompted.

4. **Search Recipes**:
   - Enter a keyword or ingredient in the search bar at the top of the **Search Panel**.
   - Click the "Search" button to filter recipes.
   - Matching recipes will be displayed in the list below.

5. **View All Recipes**:
   - Click the "Show All Recipes" button at the bottom of the **Search Panel** to display all saved recipes.

6. **Save Recipes**:
   - Click the "Save Recipes" button in the toolbar to save your recipes to a file.

7. **Exit**:
   - Click the "Exit" button in the toolbar to close the application.

### Automatic Loading
When the application starts, it will automatically load recipes from the default file (`data/recipes.ser`) if it exists.

---

## Technologies Used
- **Java Swing**: For building the graphical user interface.
- **JUnit 5**: For unit testing.
- **Maven**: For dependency management and build automation.
- **Java Serialization**: For saving and loading recipes.

---

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Contact
For any questions or suggestions, please feel free to reach out at: **tamaskennethtottszer@edu.bme.hu**

