

// import javafx.fxml.FXML;
// import javafx.scene.control.TextField;
// import javafx.scene.control.TextArea;
// import javafx.scene.control.Button;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Alert.AlertType;


// public class LibraryViewController {
//     @FXML
//     private Parent rootView;
//     @FXML private TextField bookIdField;
//     @FXML private TextField titleField;
//     @FXML private TextField authorField;
//     @FXML private TextField genreField;
//     @FXML private TextField publisherField;
//     @FXML private TextField isbnField;
//     @FXML private Button addButton;
//     @FXML private Button issueButton;
//     @FXML private Button returnButton;
//     @FXML private Button logoutButton;
//     @FXML private TextArea displayArea;

//     private LibraryModel model;

//     public LibraryViewController() {
//         this.model = new LibraryModel();  // Assuming LibraryModel handles all data-related operations
//     }

//     @FXML
//     private void initialize() {
//         // Initialize anything that needs setup upon loading the controller
//     }

//     @FXML
//     private void handleAddBook() {
//         String bookId = bookIdField.getText();
//         String title = titleField.getText();
//         String author = authorField.getText();
//         String genre = genreField.getText();
//         String publisher = publisherField.getText();
//         String isbn = isbnField.getText();

//         if (bookId.isEmpty() || title.isEmpty()) {
//             displayAlert("Error", "Book ID and title are required.");
//             return;
//         }

//         Book existingBook = model.findBookById(bookId);
//         if (existingBook != null) {
//             existingBook.increaseQuantity();
//             displayMessage("Book quantity updated", "The quantity for the existing book has been increased.");
//         } else {
//             Book newBook = new Book(bookId, title, author, genre, publisher, isbn, 1);
//             model.addBook(newBook);
//             displayMessage("Book Added", "A new book has been added to the library.");
//         }
//         model.saveBooks();
//         updateBookDisplay();
//         clearFields();
//     }

//     @FXML
//     private void handleIssueBook() {
//         String bookId = bookIdField.getText();
//         if (bookId.isEmpty()) {
//             displayAlert("Error", "Book ID is required to issue a book.");
//             return;
//         }

//         if (model.issueBook(bookId)) {
//             displayMessage("Book Issued", "The book has been successfully issued.");
//             updateBookDisplay();
//         } else {
//             displayAlert("Issue Failed", "No sufficient copies available or book not found.");
//         }
//     }

//     @FXML
//     private void handleReturnBook() {
//         String bookId = bookIdField.getText();
//         if (bookId.isEmpty()) {
//             displayAlert("Error", "Book ID is required to return a book.");
//             return;
//         }

//         if (model.returnBook(bookId)) {
//             displayMessage("Book Returned", "The book has been successfully returned.");
//             updateBookDisplay();
//         } else {
//             displayAlert("Return Failed", "This book was not found in the library.");
//         }
//     }

//      public Scene getScene() {
//         return new Scene(rootView);  // Create and return the scene
//     }
//     @FXML
//     private void handleLogout() {
//         System.exit(0);  // Simply exits the application (consider a more graceful approach for real applications)
//     }

//     private void updateBookDisplay() {
//         displayArea.clear();
//         for (Book book : model.getBooks()) {
//             displayArea.appendText(String.format("%s - %s | Quantity: %d\n", book.getBookId(), book.getTitle(), book.getQuantity()));
//         }
//     }

//     private void clearFields() {
//         bookIdField.clear();
//         titleField.clear();
//         authorField.clear();
//         genreField.clear();
//         publisherField.clear();
//         isbnField.clear();
//     }
//     public void initModel(LibraryModel model) {
//         if (this.model != null) {
//             throw new IllegalStateException("Model can only be initialized once");
//         }

//         this.model = model;

//         // Example of additional initialization logic, like populating a list of books
//         updateBookDisplay();
//     }
//     private void displayMessage(String title, String content) {
//         Alert alert = new Alert(AlertType.INFORMATION);
//         alert.setTitle(title);
//         alert.setHeaderText(null);
//         alert.setContentText(content);
//         alert.showAndWait();
//     }

//     private void displayAlert(String title, String message) {
//         Alert alert = new Alert(AlertType.ERROR);
//         alert.setTitle(title);
//         alert.setHeaderText(null);
//         alert.setContentText(message);
//         alert.showAndWait();
//     }
// }
