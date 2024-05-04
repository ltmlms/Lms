import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LibraryView {
    private GridPane gridPane = new GridPane();
    private TextField bookIdField = new TextField();
    private TextField titleField = new TextField();
    private Button addButton = new Button("Add Book");
    private Button issueButton = new Button("Issue Book");
    private Button returnButton = new Button("Return Book");
    private Button logoutButton = new Button("Logout");
    private TextArea displayArea = new TextArea();
    private Button deleteButton = new Button("Delete Book"); // New delete button
    private Button editButton = new Button("Edit Book"); // New edit button

    public LibraryView() {
        initializeUI();
    }

    private void initializeUI() {
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(new Label("Book ID:"), 0, 0);
        gridPane.add(bookIdField, 1, 0);
        gridPane.add(new Label("Title:"), 0, 1);
        gridPane.add(titleField, 1, 1);
        gridPane.add(addButton, 0, 2);
        gridPane.add(deleteButton, 1, 2); // Adding delete button to the layout
        gridPane.add(editButton, 2, 2); // Adding edit button to the layout
        gridPane.add(issueButton, 3, 2);
        gridPane.add(returnButton, 4, 2);
        gridPane.add(logoutButton, 5, 2);
        gridPane.add(displayArea, 0, 3, 3, 1);
    }

    public void setScene(Stage primaryStage) {
        Scene scene = new Scene(gridPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Library Management System");
        primaryStage.show();
    }

    // Getters for UI controls
    public TextField getBookIdField() { return bookIdField; }
    public TextField getTitleField() { return titleField; }
    public Button getAddButton() { return addButton; }
    public Button getIssueButton() { return issueButton; }
    public Button getReturnButton() { return returnButton; }
    public Button getLogoutButton() { return logoutButton; } 
    public TextArea getDisplayArea() { return displayArea; }
    public Button getDeleteButton() { return deleteButton; }
    public Button getEditButton() { return editButton; }
}
