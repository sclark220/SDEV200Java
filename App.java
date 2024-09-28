package chapter34;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;

public class App extends Application
{
    // Statement for executing queries
    private PreparedStatement preparedStatement;
    private String queryString = "nothing";
    private TextField idNumberField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField firstNameField = new TextField();
    private TextField middleInitialField = new TextField();
    private TextField addressField = new TextField();
    private TextField cityField = new TextField();
    private TextField stateField = new TextField();
    private TextField telephoneField = new TextField();
    private TextField emailField = new TextField();

    private Label labelStatus = new Label();

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage)
    {
        // Initialize database connection and create a Statement object
        initializeDB();

        Button buttonView = new Button("View");
        Button buttonInsert = new Button("Insert");
        Button buttonUpdate = new Button("Update");

        HBox hBox01 = new HBox(10);
        HBox hBox02 = new HBox(10);
        HBox hBox03 = new HBox(10);
        HBox hBox04 = new HBox(10);
        HBox hBox05 = new HBox(10);

        hBox01.setPadding(new Insets(5));
        hBox02.setPadding(new Insets(5));
        hBox03.setPadding(new Insets(5));
        hBox04.setPadding(new Insets(5));
        hBox05.setPadding(new Insets(5));

        hBox01.getChildren().addAll(new Label("ID"), idNumberField, buttonView, buttonInsert, buttonUpdate);
        hBox02.getChildren().addAll(new Label("First name"), firstNameField, new Label("Middle intial"), middleInitialField, new Label("Last name"), lastNameField);
        hBox03.getChildren().addAll(new Label("Address"), addressField, new Label("City"), cityField, new Label("State"), stateField);
        hBox04.getChildren().addAll(new Label("Telephone"), telephoneField, new Label("Email"), emailField);
        hBox05.getChildren().addAll(labelStatus);

        VBox vBox = new VBox(5);
        vBox.getChildren().addAll(hBox01, hBox02, hBox03, hBox04, labelStatus);

        firstNameField.setPrefColumnCount(6);
        lastNameField.setPrefColumnCount(6);
        buttonView.setOnAction(e -> viewStaff());
        buttonInsert.setOnAction(e -> insertStaff());
        buttonUpdate.setOnAction(e -> viewStaff());

        // Create a scene and place it in the stage
        Scene scene = new Scene(vBox, 700, 250);
        primaryStage.setTitle("Staff Table"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    private void initializeDB()
    {
        try
        {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver loaded");

            // Establish a connection
            Connection connection = DriverManager.getConnection
            ("jdbc:mysql://localhost/javabook", "scott", "tiger");
            // ("jdbc:oracle:thin:@liang.armstrong.edu:1521:orcl",
            // "scott", "tiger");
            System.out.println("Database connected");

            preparedStatement = connection.prepareStatement(queryString);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void viewStaff()
    {
        String id = idNumberField.getText();
        try
        {
            queryString = "select * from Staff where id =" + id;

            ResultSet rset = preparedStatement.executeQuery(queryString);

            if (rset.next())
            {
                String lastName = rset.getString(2);
                String firstName = rset.getString(3);
                String middleInitial = rset.getString(4);
                String address = rset.getString(5);
                String city = rset.getString(6);
                String state = rset.getString(7);
                String telephone = rset.getString(8);
                String email = rset.getString(9);

                lastNameField.setText(lastName);
                firstNameField.setText(firstName);
                middleInitialField.setText(middleInitial);
                addressField.setText(address);
                cityField.setText(city);
                stateField.setText(state);
                telephoneField.setText(telephone);
                emailField.setText(email);

                // Display result in a label
                labelStatus.setText("Got it!");
            }
            else
            {
                labelStatus.setText("Not found");
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            labelStatus.setText("Error");
        }
    }

    private void insertStaff()
    {
        String id = idNumberField.getText();
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String middleInitial = middleInitialField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String telephone = telephoneField.getText();
        String email = emailField.getText();
        try
        {
            queryString = "insert into Staff (id, lastName, firstName, mi, address, city, state, telephone, email)" + "values (?, ?, ?, ?, ?, ?, ?, ?, ?);";

            preparedStatement.setString(1, id);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, middleInitial);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, city);
            preparedStatement.setString(7, state);
            preparedStatement.setString(8, telephone);
            preparedStatement.setString(9, email);

            preparedStatement.executeQuery();
            
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            labelStatus.setText("Error");
        }
    }

    private void updateStaff()
    {
        String id = idNumberField.getText();
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String middleInitial = middleInitialField.getText();
        String address = addressField.getText();
        String city = cityField.getText();
        String state = stateField.getText();
        String telephone = telephoneField.getText();
        String email = emailField.getText();
        try
        {
            String queryString = "update Staff set id = ?, lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ?, email = ?";

            preparedStatement.setString(1, id);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3, firstName);
            preparedStatement.setString(4, middleInitial);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, city);
            preparedStatement.setString(7, state);
            preparedStatement.setString(8, telephone);
            preparedStatement.setString(9, email);

            preparedStatement.executeQuery(queryString);

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
            labelStatus.setText("Error");
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}