import javafx.application.Application;
import javafx.stage.Stage;

public class LibraryApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        LibraryModel libraryModel = new LibraryModel();
        LibraryView libraryView = new LibraryView();
        new LibraryController(libraryModel, libraryView);

        LoginModel loginModel = new LoginModel();
        LoginView loginView = new LoginView(primaryStage);
        new LoginController(loginModel, loginView, libraryView, libraryModel);

        libraryModel.loadBooks();
        loginView.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
