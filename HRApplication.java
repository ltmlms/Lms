import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
// import java.util.Optional;

public class HRApplication extends Application {

    private ObservableList<Employee> employeeList;
    private int currentIndex;
    

    private TextField firstNameField;
    private TextField lastNameField;
    private TextField phoneNumber;
    private TextField address;
    private TextField city;
    private TextField State;
    private TextField employeeId;
    private TextField title;
    private TextField Department;
    private TextField salary;
    private TextField hireDate;
    

    private Button previousButton;
    private Button newButton;
    private Button editButton;
    private Button deleteButton;
    private Button nextButton;
    private Button saveButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        loadEmployeesFromFile(); // Load employees from file during application startup

        // Initialize UI components
        previousButton = new Button("Previous");
        newButton = new Button("Add Book"); 
        editButton = new Button("Book Entry");
        deleteButton = new Button("Delete"); 
        nextButton = new Button("Next");
        saveButton = new Button("Save");
         

        firstNameField = new TextField();
        lastNameField = new TextField();
        phoneNumber = new TextField();
        address = new TextField();
        city = new TextField();
        State = new TextField();
        employeeId = new TextField();
        title = new TextField();
        Department = new TextField();
        salary = new TextField();
        hireDate = new TextField();
       

        
        previousButton.setOnAction(e -> showPreviousEmployee());
        newButton.setOnAction(e -> handleNewButton());
        editButton.setOnAction(e -> handleEditButton());
        deleteButton.setOnAction(e -> handleDeleteButton());
        nextButton.setOnAction(e -> showNextEmployee());
        saveButton.setOnAction(e -> handleSaveButton());

        // Set up the UI layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // gridPane.add(new Label("Manage Books:"), 0, 0);
        // gridPane.add(firstNameField, 1, 0);
        gridPane.add(new Label("Student ID:"), 0, 1);
        gridPane.add(lastNameField, 1, 1);
        gridPane.add(new Label("Book ID:"), 0, 2);
        gridPane.add(phoneNumber, 1, 2);
        gridPane.add(new Label("Book Name:"), 0, 3);
        gridPane.add(address, 1, 3);
        gridPane.add(new Label("Author"), 0, 4);
        gridPane.add(city, 1, 4);
        gridPane.add(new Label("Quantity:"), 0, 5);
        gridPane.add(State, 1, 5);
        gridPane.add(new Label("Issue Date:"), 0, 7);
        gridPane.add(title, 1, 7);
        gridPane.add(new Label("Due Date:"), 0, 8);
        gridPane.add(Department, 1, 8);
        // gridPane.add(new Label("Salary:"), 0, 9);
        // gridPane.add(salary, 1, 9);
        // gridPane.add(new Label("Hire Date:"), 0, 10);
        // gridPane.add(hireDate, 1, 10);

        gridPane.add(previousButton, 0, 11);
        gridPane.add(newButton, 1, 11);
        gridPane.add(editButton, 2, 11);
        gridPane.add(deleteButton, 3, 11);
        gridPane.add(nextButton, 4, 11);
        gridPane.add(saveButton, 5, 11);

        
        Scene scene = new Scene(gridPane, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Library Management System");
        primaryStage.show();

        
        if (!employeeList.isEmpty()) {
            if (isEditingState()) {
                showEmployeeForEditing(employeeList.get(currentIndex));
            } else {
                showEmployee(currentIndex, employeeList.get(currentIndex));
            }
        }

        updateButtonStates();
    }

    private void updateButtonStates() {
        if (isEditingState()) {
            // If in 'Update' state
            editButton.setText("Cancel Edit");
            editButton.setStyle("-fx-text-fill: red;");
            saveButton.setDisable(false);
            // Disable other buttons as needed
        } else {
            // If in 'Read' state
            editButton.setText("Edit");
            editButton.setStyle(""); // Reset text color to default
            saveButton.setDisable(true);
            // Enable other buttons as needed
        }
    }
    

    private void loadEmployeesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Library.dat"))) {
            List<Employee> loadedEmployees = (List<Employee>) ois.readObject();
            employeeList = FXCollections.observableArrayList(loadedEmployees);
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Creating a new employee list.");
            employeeList = FXCollections.observableArrayList();
        } catch (EOFException e) {
            System.out.println("End of file reached. File might be empty or corrupted.");
            employeeList = FXCollections.observableArrayList();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading employees from file: " + e.getMessage());
            e.printStackTrace();
            employeeList = FXCollections.observableArrayList();
        }
    }
    

    private void showEmployee(int index, Employee employee) {
        if (index >= 0 && index < employeeList.size() && employee != null) {
            firstNameField.setText(employee.getFirstName());
            lastNameField.setText(employee.getLastName());
            phoneNumber.setText(employee.getPhoneNumber());
            address.setText(employee.getAddress());
            city.setText(employee.getCity());
            State.setText(employee.getState());
            employeeId.setText(employee.getEmployeeId());
            title.setText(employee.getTitle());
            Department.setText(employee.getDepartment());
            salary.setText(employee.getSalary());
            hireDate.setText(employee.getHireDate());

            
        }
    }

    private void showEmployeeForEditing(Employee employee) {

        System.out.println("eidtd");
        // Set text fields with the data of the employee for editing
        firstNameField.setText(employee.getFirstName());
        lastNameField.setText(employee.getLastName());
        phoneNumber.setText(employee.getPhoneNumber());
        address.setText(employee.getAddress());
        city.setText(employee.getCity());
        State.setText(employee.getState());
        employeeId.setText(String.valueOf(employee.getEmployeeId()));
        title.setText(employee.getTitle());
        Department.setText(employee.getDepartment());
        salary.setText(String.valueOf(employee.getSalary()));
        hireDate.setText(employee.getHireDate());

        previousButton.setOnAction(e -> showPreviousEmployee());
        newButton.setOnAction(e -> handleNewButton());
        editButton.setOnAction(e -> handleEditButton());
        deleteButton.setOnAction(e -> handleDeleteButton());
        nextButton.setOnAction(e -> showNextEmployee());
        saveButton.setOnAction(e -> handleSaveButton());


       
    }

    private void showNextEmployee() {
        if (currentIndex < employeeList.size() - 1) {
            currentIndex++;
            if (isEditingState()) {
                // If in 'Update' state, show the next employee for editing
                showEmployeeForEditing(employeeList.get(currentIndex));
            } else if ("Yes Delete".equals(deleteButton.getText())){
                resetToDeleteState();
            }else {
                // If in 'Read' state, show the next employee
                showEmployee(currentIndex, employeeList.get(currentIndex));
            }
        }
    }

    private void showPreviousEmployee() {
        if (currentIndex > 0) {
            currentIndex--;
            if (isEditingState()) {
                // If in 'Update' state, show the previous employee for editing
                showEmployeeForEditing(employeeList.get(currentIndex));
            } else if ("Yes Delete".equals(deleteButton.getText())){
                resetToDeleteState();
            }
            else{
                
                // If in 'Read' state, show the previous employee
                showEmployee(currentIndex, employeeList.get(currentIndex));
            }
        }
    }

    private boolean isEditingState() {
        return "Cancel Edit".equals(editButton.getText());
    }

    private void handleNewButton() {
        newButton.setText("Cancel New");
        newButton.setStyle("-fx-text-fill: red;");

        // System.out.println("new_clicked");
        // Clear all text fields
        firstNameField.clear();
        lastNameField.clear();
        phoneNumber.clear();
        address.clear();
        city.clear();
        State.clear();
        employeeId.clear();
        title.clear();
        Department.clear();
        salary.clear();
        hireDate.clear();

        // Clear other fields as needed

        // Disable Previous, Edit, Delete, and Next buttons
        previousButton.setDisable(true);
        editButton.setDisable(true);
        deleteButton.setDisable(true);
        nextButton.setDisable(true);
        saveButton.setDisable(false);

        // Set up event handler for "Cancel New" button
        newButton.setOnAction(event -> {
            // Change back to 'Read' state
            newButton.setText("New");
            newButton.setStyle(""); // Reset text color to default

            // Enable Previous, Edit, Delete, and Next buttons
            previousButton.setDisable(false);
            editButton.setDisable(false);
            deleteButton.setDisable(false);
            nextButton.setDisable(false);
            saveButton.setDisable(true);

            // Clear text fields
            firstNameField.clear();
            lastNameField.clear();
            phoneNumber.clear();
            address.clear();
            city.clear();
            State.clear();
            employeeId.clear();
            title.clear();
            Department.clear();
            salary.clear();
            hireDate.clear();

            resetToReadState(currentIndex);
            // Clear other fields as needed
        });

        // Set up event handler for "Save" button
        saveButton.setOnAction(event -> {
            // Create a new employee
            Employee newEmployee = new Employee(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    phoneNumber.getText(),
                    address.getText(),
                    city.getText(),
                    State.getText(),
                    employeeId.getText(),
                    title.getText(),
                    Department.getText(),
                    salary.getText(),
                    hireDate.getText());

            // Add the new employee to the list
            employeeList.add(newEmployee);

            // Change back to 'Read' state
            newButton.setText("New");
            newButton.setStyle(""); // Reset text color to default

            // Enable Previous, Edit, Delete, and Next buttons
            previousButton.setDisable(false);
            editButton.setDisable(false);
            deleteButton.setDisable(false);
            nextButton.setDisable(false);
            saveButton.setDisable(true);

            
            currentIndex = employeeList.size() - 1;
            
            resetToReadState(currentIndex);
        });
    }

    

    private void handleEditButton() {
        
        Employee currentEmployee = employeeList.get(currentIndex);

        // Change the button text to "Cancel Edit" and set text color to red
        editButton.setText("Cancel Edit");
        editButton.setStyle("-fx-text-fill: red;");

        // Disable Previous, New, Delete, and Next buttons
        previousButton.setDisable(true);
        newButton.setDisable(true);
        deleteButton.setDisable(true);
        nextButton.setDisable(true);
        saveButton.setDisable(false);

        // Set up event handler for "Cancel Edit" button
        editButton.setOnAction(event -> {
            // Change back to 'Read' state
            editButton.setText("Edit");
            editButton.setStyle(""); // Reset text color to default

            // Enable Previous, New, Delete, and Next buttons
            previousButton.setDisable(false);
            newButton.setDisable(false);
            deleteButton.setDisable(false);
            nextButton.setDisable(false);
            saveButton.setDisable(true);

            editButton.setText("Edit");
            editButton.setStyle("");

            // Show the original employee data
            resetToReadState(currentIndex);


        });

        // Set up event handler for "Save" button
        saveButton.setOnAction(event -> {
            // Create an updated employee
            Employee updatedEmployee = new Employee(
                    firstNameField.getText(),
                    lastNameField.getText(),
                    phoneNumber.getText(),
                    address.getText(),
                    city.getText(),
                    State.getText(),
                    employeeId.getText(),
                    title.getText(),
                    Department.getText(),
                    salary.getText(),
                    hireDate.getText());

            // Update the employee in the list
            updateEmployeeInList(currentIndex, updatedEmployee);

            // Change back to 'Read' state
            editButton.setText("Edit");
            editButton.setStyle(""); // Reset text color to default

            // Enable Previous, New, Delete, and Next buttons
            previousButton.setDisable(false);
            newButton.setDisable(false);
            deleteButton.setDisable(false);
            nextButton.setDisable(false);
            saveButton.setDisable(true);

            // Show the updated employee data for editing
            resetToReadState(currentIndex);
        });

       
        
    }

    private void handleDeleteButton() {
        if ("Delete".equals(deleteButton.getText())) {
            // If in 'Read' state, prepare for deletion
            deleteButton.setText("Yes Delete");
            deleteButton.setStyle("-fx-text-fill: red;");
            newButton.setDisable(true);
            editButton.setDisable(true);
            saveButton.setDisable(true);
            nextButton.setDisable(false);
            previousButton.setDisable(false);
    
            // Show the current employee to be deleted
            showEmployee(currentIndex, employeeList.get(currentIndex));
        } else if ("Yes Delete".equals(deleteButton.getText())) {
            // If user confirms deletion, delete the employee
            deleteEmployee(currentIndex);
            
            // Return to 'Read' state and show the next employee
            resetToDeleteState();
            showNextEmployee();
        }
    }

    private void deleteEmployee(int index) {
        if (index >= 0 && index < employeeList.size()) {
            // Delete the employee at the specified index
            employeeList.remove(index);
            
            
            
            // Handle the case when the list becomes empty after deletion
            if (employeeList.isEmpty()) {
                resetToReadState(currentIndex);
            } else {
                // Adjust the currentIndex if necessary
                if (currentIndex >= employeeList.size()) {
                    currentIndex = employeeList.size() - 1;
                }
            }
        }
    }

    private void resetToDeleteState() {
        // Reset the Delete button to its default state
        deleteButton.setText("Delete");
        deleteButton.setStyle(""); // Reset text color to default
    
        // Enable New, Edit, Save buttons
        newButton.setDisable(false);
        editButton.setDisable(false);
        saveButton.setDisable(true);
        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Library.dat"))) {
            oos.writeObject(new ArrayList<>(employeeList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    
        // Show the current employee (if any) in 'Read' state
        if (!employeeList.isEmpty()) {
            resetToReadState(currentIndex);
            // showEmployee(currentIndex, employeeList.get(currentIndex));
        }
    }

    private void handleSaveButton() {
        

        Employee updatedEmployee = new Employee(
                firstNameField.getText(),
                lastNameField.getText(),
                phoneNumber.getText(),
                address.getText(),
                city.getText(),
                State.getText(),
                employeeId.getText(),
                title.getText(),
                Department.getText(),
                salary.getText(),
                hireDate.getText());

        
        updateEmployeeInList(currentIndex, updatedEmployee);

        
        showEmployee(currentIndex,updatedEmployee);

        
        resetToReadState(currentIndex);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Library.dat"))) {
            oos.writeObject(new ArrayList<>(employeeList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    // Override stop method to save employees to file when closing the application
    @Override
    public void stop() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Library.dat"))) {
            oos.writeObject(new ArrayList<>(employeeList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateEmployeeInList(int index, Employee updatedEmployee) {
        try {
           
            employeeList.set(index, updatedEmployee);
             try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Library.dat"))) {
            oos.writeObject(new ArrayList<>(employeeList));
        } catch (IOException e) {
            e.printStackTrace();
        }

        } catch (IndexOutOfBoundsException e) {
            // Handle the exception appropriately, such as logging or showing an error
            // message
            System.err.println("IndexOutOfBoundsException: " + e.getMessage());
        }
    }

    private void resetToReadState(int index) {
        
        newButton.setText("New");
        newButton.setStyle("");
       
        previousButton.setDisable(false);
        editButton.setDisable(false);
        deleteButton.setDisable(false);
        nextButton.setDisable(false);

        
        
        showEmployee(index,employeeList.get(index));
        previousButton.setOnAction(e -> showPreviousEmployee());
        newButton.setOnAction(e -> handleNewButton());
        editButton.setOnAction(e -> handleEditButton());
        deleteButton.setOnAction(e -> handleDeleteButton());
        nextButton.setOnAction(e -> showNextEmployee());
        saveButton.setOnAction(e -> handleSaveButton());

    }

}
