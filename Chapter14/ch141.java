import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ch141 extends Application
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
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, 750, 750); // set size to 750 x 750

        // create the images and set them to be the same size.
        int imageX = 300;
        int imageY = 200;
        ImageView imageFlag01 = new ImageView(new Image("Images/flag1.gif"));
        imageFlag01.setFitWidth(imageX);
        imageFlag01.setFitHeight(imageY);

        ImageView imageFlag02 = new ImageView(new Image("Images/flag2.gif"));
        imageFlag02.setFitWidth(imageX);
        imageFlag02.setFitHeight(imageY);

        ImageView imageFlag03 = new ImageView(new Image("Images/flag6.gif"));
        imageFlag03.setFitWidth(imageX);
        imageFlag03.setFitHeight(imageY);

        ImageView imageFlag04 = new ImageView(new Image("Images/flag7.gif"));
        imageFlag04.setFitWidth(imageX);
        imageFlag04.setFitHeight(imageY);

        GridPane.setConstraints(imageFlag01, 0, 0);
        GridPane.setConstraints(imageFlag02, 1, 0);
        GridPane.setConstraints(imageFlag03, 0, 1);
        GridPane.setConstraints(imageFlag04, 1, 1);

        gridPane.getChildren().addAll(imageFlag01, imageFlag02, imageFlag03, imageFlag04);

        gridPane.setAlignment(Pos.CENTER); // center the grid on screen

        primaryStage.setTitle("Chapter 14 exercise 1"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
}