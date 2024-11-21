# prog3_java_project_tamas_tottszer
Programming 3 Java Project by Tamas Tottszer



PROG3_JAVA_PROJECT_TAMAS_TOTTSZER/
├── src/                            # Source code directory
│   ├── main/                       # Main application source files
│   │   ├── java/                   # Java source files
│   │   │   └── com/
│   │   │       └── recipebook/     # Root package
│   │   │           ├── RecipeBook.java          # Main application class
│   │   │           ├── Recipe.java              # Recipe model class
│   │   │           ├── RecipeManager.java       # Manages recipe operations (add, edit, delete, etc.)
│   │   │           ├── RecipeSerializer.java    # Handles file I/O for serialization
│   │   │           ├── SearchService.java       # Handles search logic
│   │   │           └── ui/                      # User Interface classes
│   │   │               ├── MainFrame.java       # Main GUI frame
│   │   │               ├── RecipePanel.java     # Panel for displaying recipe details
                        ├── EditRecipeDialog.java 
│   │   │               ├── AddRecipeDialog.java # Dialog for adding/editing recipes
│   │   │               └── SearchPanel.java     # Panel for searching recipes
│   └── test/                      # Test code directory
│       └── java/                  # Java test files
│           └── com/
│               └── recipebook/
│                   ├── RecipeManagerTest.java   # Unit tests for RecipeManager
│                   ├── RecipeSerializerTest.java # Unit tests for serialization
│                   └── SearchServiceTest.java    # Unit tests for search functionality
├── docs/                           # Documentation
│   ├── diagrams/                   # UML diagrams
│   │   ├── class_diagram.png       # Class diagram image
│   │   ├── sequence_diagram.png    # Sequence diagram image
│   └── specs/                      # Specification files
│       └── ProjectDescription.pdf  # Task description and specifications
├── lib/                            # External libraries (if any)
│   └── (place any JARs here)
├── data/                           # Serialized data files
│   └── recipes.ser                 # Serialized data file for recipes
├── .gitignore                      # Git ignore file
├── README.md                       # Project README
└── build.gradle or pom.xml         # Build configuration (Gradle or Maven)


1. Description of the Task
Overview: The Digital Recipe Book application will allow users to manage their favorite recipes digitally. Users will be able to add new recipes, categorize them by meal type (e.g., breakfast, lunch, dinner), and search based on ingredients or tags. The primary purpose of the application is to provide an organized way to store and access recipes with ease.
Features:
Recipe Management: Users can add, view, edit, and delete recipes.
Categorization: Recipes will be categorized by meal type (e.g., Breakfast, Lunch, Dinner, Desserts).
Ingredient-Based Search: Users can search for recipes by ingredients.
Persistence: Recipes will be saved using serialization, so data is preserved between application uses.
Technology: The application will use Java’s Swing for the graphical interface, Collections (e.g., ArrayList for storing recipes and HashSet for tags), file input/output for saving data, and unit tests for critical functionality.
2. Use-Case Diagrams and Descriptions
Use Cases:
Add Recipe: Users can add a recipe by entering its name, ingredients, instructions, and selecting a category.
View Recipe: Allows users to select and view a recipe’s details, including ingredients and instructions.
Edit Recipe: Users can update existing recipes, changing any attribute such as ingredients or instructions.
Delete Recipe: Allows users to remove a recipe from the collection.
Search Recipe: Users can search recipes by keyword or ingredient, displaying only those that match.
Diagram: The diagram will include actors (User) and use cases (Add, View, Edit, Delete, and Search Recipe).
3. Brief Description of the Solution
Core Logic:
Recipes will be represented as Java objects with fields for the name, ingredients (as a list), instructions, and tags (using a HashSet).
Recipes will be stored in an ArrayList, which is serialized to a file to ensure data persistence.
GUI Design:
The main interface will use a menu bar with options for adding, searching, and categorizing recipes.
A JTree may be used to categorize recipes by meal type for easy navigation.
Search results and recipe details can be displayed using a JTable or detailed view panel.
File Handling:
Serialization will be used to save and load the recipe list from a file.
Testing:
Unit tests will cover adding, editing, and deleting recipes, ensuring that the core functionality behaves as expected.
Additional tests will verify the search functionality based on various search criteria.
This outline covers the essentials, and each part will give you a good base for documenting and presenting your project. Let me know if you'd like help expanding any section or building your use-case diagram!