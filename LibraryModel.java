// import javafx.collections.FXCollections;
// import javafx.collections.ObservableList;
// import java.io.*;
// import java.util.ArrayList;
// import java.util.List;

// public class LibraryModel {
//     private ObservableList<Book> books = FXCollections.observableArrayList();

//     // Load books from the file
//     public void loadBooks() {
//         File file = new File("books.dat");
//         if (file.exists()) {
//             try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
//                 List<Book> list = (List<Book>) ois.readObject();
//                 books = FXCollections.observableList(list);
//             } catch (IOException | ClassNotFoundException e) {
//                 System.out.println("Error loading books: " + e.getMessage());
//                 books.clear(); // Clear the existing content if any error occurs
//             }
//         }
//     }

//     // Save books to the file
//     public void saveBooks() {
//         try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("books.dat"))) {
//             oos.writeObject(new ArrayList<>(books));
//         } catch (IOException e) {
//             System.out.println("Error saving books: " + e.getMessage());
//         }
//     }

//     // Add a book to the collection
//     public void addBook(Book book) {
//         books.add(book);
//         saveBooks(); // Save every time a new book is added
//     }

//     // Issue a book (decrease the quantity by one)
//     public boolean issueBook(String bookId) {
//         for (Book book : books) {
//             if (book.getBookId().equals(bookId) && book.getQuantity() > 0) {
//                 book.setQuantity(book.getQuantity() - 1);
//                 saveBooks(); // Save changes to the file
//                 return true;
//             }
//         }
//         return false; // Return false if the book can't be issued
//     }

//     // Return a book (increase the quantity by one)
//     public boolean returnBook(String bookId) {
//         for (Book book : books) {
//             if (book.getBookId().equals(bookId)) {
//                 book.setQuantity(book.getQuantity() + 1);
//                 saveBooks(); // Save changes to the file
//                 return true;
//             }
//         }
//         return false; // Return false if the book isn't found
//     }

//     // Getter for the list of books
//     public ObservableList<Book> getBooks() {
//         return books;
//     }
// }


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryModel {
    private ObservableList<Book> books = FXCollections.observableArrayList();

    public void loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("books.dat"))) {
            books = FXCollections.observableArrayList((List<Book>) ois.readObject());
        } catch (Exception e) {
            System.out.println("Failed to load books: " + e.getMessage());
            books = FXCollections.observableArrayList();
        }
    }

    public void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("books.dat"))) {
            oos.writeObject(new ArrayList<>(books));
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    public void addBook(Book book) {
        Book existing = findBookById(book.getBookId());
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + 1);
        } else {
            books.add(book);
        }
        saveBooks();
    }

    public boolean issueBook(String bookId) {
        Book book = findBookById(bookId);
        if (book != null && book.getQuantity() > 0) {
            book.setQuantity(book.getQuantity() - 1);
            saveBooks();
            return true;
        }
        return false;
    }

    public boolean deleteBook(String bookId) {
        return books.removeIf(book -> book.getBookId().equals(bookId));
    }

    public boolean editBook(String bookId, String newTitle, String newAuthor) {
        Book book = findBookById(bookId);
        if (book != null) {
            book.setTitle(newTitle);
            book.setAuthor(newAuthor);
            return true;
        }
        return false;
    }
    public boolean returnBook(String bookId) {
        Book book = findBookById(bookId);
        if (book != null) {
            book.setQuantity(book.getQuantity() + 1);
            saveBooks();
            return true;
        }
        return false;
    }

    public Book findBookById(String bookId) {
        return books.stream().filter(b -> b.getBookId().equals(bookId)).findFirst().orElse(null);
    }

    public ObservableList<Book> getBooks() {
        return books;
    }
}
