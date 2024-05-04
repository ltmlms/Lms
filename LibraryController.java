public class LibraryController {
    private LibraryModel model;
    private LibraryView view;

    public LibraryController(LibraryModel model, LibraryView view) {
        this.model = model;
        this.view = view;
        attachEventHandlers();
        updateBookDisplay();  // Initially update display with existing books
    }

    private void attachEventHandlers() {
        view.getAddButton().setOnAction(e -> addBook());
        view.getIssueButton().setOnAction(e -> issueBook());
        view.getReturnButton().setOnAction(e -> returnBook());
        view.getDeleteButton().setOnAction(e -> deleteBook());
        view.getEditButton().setOnAction(e -> editBook());
        //view.getLogoutButton().setOnAction(e -> logout());
    }
    

    private void addBook() {
        try {
            String bookId = view.getBookIdField().getText();
            String title = view.getTitleField().getText();
            // Assume other details like author, genre, etc., are filled in similarly
            Book existingBook = model.getBooks().stream()
                                      .filter(b -> b.getBookId().equals(bookId))
                                      .findFirst()
                                      .orElse(null);

            if (existingBook != null) {
                // Book already exists, just update the quantity
                existingBook.setQuantity(existingBook.getQuantity() + 1);
                System.out.println("Increased quantity for existing book.");
            } else {
                // Create a new book since it doesn't exist
                Book newBook = new Book(bookId, title, "Author", "Genre", "Publisher", "ISBN", 1);
                model.addBook(newBook);
                System.out.println("Added new book to the library.");
            }
            model.saveBooks();  // Save changes to disk
            updateBookDisplay();  // Update the display to reflect changes
        } catch (Exception e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    private void deleteBook() {
        String bookId = view.getBookIdField().getText();
        if (model.deleteBook(bookId)) {
            view.getDisplayArea().setText("Book deleted successfully.");
            updateBookDisplay();
        } else {
            view.getDisplayArea().setText("Failed to delete book.");
        }
    }

    private void editBook() {
        String bookId = view.getBookIdField().getText();
        String newTitle = view.getTitleField().getText();
        String newAuthor = view.getTitleField().getText(); // Assuming the author's name is entered in the title field, replace with correct field if different
        if (model.editBook(bookId, newTitle, newAuthor)) {
            view.getDisplayArea().setText("Book updated successfully.");
            updateBookDisplay();
        } else {
            view.getDisplayArea().setText("Failed to update book.");
        }
    }
    private void issueBook() {
        String bookId = view.getBookIdField().getText();
        if (model.issueBook(bookId)) {
            System.out.println("Book issued successfully.");
            updateBookDisplay();
        } else {
            System.out.println("Failed to issue book.");
        }
    }

    private void returnBook() {
        String bookId = view.getBookIdField().getText();
        if (model.returnBook(bookId)) {
            System.out.println("Book returned successfully.");
            updateBookDisplay();
        } else {
            System.out.println("Failed to return book.");
        }
    }

    private void updateBookDisplay() {
        StringBuilder sb = new StringBuilder();
        for (Book book : model.getBooks()) {
            sb.append(book.getBookId())
              .append(" - ")
              .append(book.getTitle())
              .append(" | Quantity: ")
              .append(book.getQuantity())
              .append("\n");
        }
        view.getDisplayArea().setText(sb.toString());
    }
}
