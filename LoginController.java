

// import javafx.fxml.FXML;
// import javafx.scene.control.TextField;
// import javafx.scene.control.Button;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Alert.AlertType;
// import javafx.stage.Stage;


// public class LoginController {
//     @FXML
//     private TextField usernameField;
//     @FXML
//     private TextField passwordField;
//     @FXML
//     private Button loginButton;

//     private LoginModel loginModel;
//     private Stage primaryStage;
//     private LibraryViewController libraryViewController;

//     public LoginController(LoginModel loginModel, Stage primaryStage, LibraryViewController libraryViewController) {
//         this.loginModel = loginModel;
//         this.primaryStage = primaryStage;
//         this.libraryViewController = libraryViewController;
//     }

//     @FXML
//     public void initialize() {
//         loginButton.setOnAction(e -> handleLogin());
//     }

//     private void handleLogin() {
//         String username = usernameField.getText();
//         String password = passwordField.getText();
//         if (loginModel.authenticate(username, password)) {
//             // If authentication succeeds, switch to the library view
//             showLibraryView();
//         } else {
//             // Show error message if authentication fails
//             displayAlert("Login Failed", "Invalid username or password. Please try again.");
//         }
//     }
//     public void setLibraryViewController(LibraryViewController libraryViewController) {
//         this.libraryViewController = libraryViewController;
//     }
//     private void showLibraryView() {
//         // Assume that LibraryViewController provides a method to get the scene
//         primaryStage.setScene(libraryViewController.getScene());
//         primaryStage.setTitle("Library Management System");
//         primaryStage.show();
//     }

//     private void displayAlert(String title, String message) {
//         Alert alert = new Alert(AlertType.ERROR);
//         alert.setTitle(title);
//         alert.setHeaderText(null);
//         alert.setContentText(message);
//         alert.showAndWait();
//     }

//     // Getter and Setter for primary stage if needed elsewhere
//     public void setPrimaryStage(Stage primaryStage) {
//         this.primaryStage = primaryStage;
//     }

//     public Stage getPrimaryStage() {
//         return primaryStage;
//     }
// }


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController {
    private LoginModel loginModel;
    private LoginView loginView;
    private LibraryView libraryView;
    private LibraryModel libraryModel;

    public LoginController(LoginModel loginModel, LoginView loginView, LibraryView libraryView, LibraryModel libraryModel) {
        this.loginModel = loginModel;
        this.loginView = loginView;
        this.libraryView = libraryView;
        this.libraryModel = libraryModel;
        attachEventHandlers();
    }

    private void attachEventHandlers() {
        loginView.getLoginButton().setOnAction(e -> login());
    }

    private void login() {
        String username = loginView.getUsernameField().getText();
        String password = loginView.getPasswordField().getText();
        if (loginModel.authenticate(username, password)) {
            loginView.close();
            libraryView.setScene(new Stage());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password!");
            alert.showAndWait();
        }
    }
}
