import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

// modified from listing 14.4 in the book
public class ch157 extends Application
{
    /**
    * The main method is only needed for the IDE with limited
    * JavaFX support. Not needed for running from the command line.
    */
    public static void main(String[] args)
    {
        Application.launch(args);
    }

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage)
    {
        // Create a circle and set its properties
        Circle circle = new Circle();
        circle.setCenterX(100);
        circle.setCenterY(100);
        circle.setRadius(50);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);

        // Create a pane to hold the circle
        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(circle);

        stackPane.setOnMousePressed(e ->
        {
            circle.setFill(Color.BLACK);
        });

        stackPane.setOnMouseReleased(e ->
        {
            circle.setFill(Color.WHITE);
        });


        // Create a scene and place it in the stage
        Scene scene = new Scene(stackPane,200, 200);

        primaryStage.setTitle("ShowCircle"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
}