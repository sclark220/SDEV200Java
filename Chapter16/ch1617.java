import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ch1617 extends Application
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

        Scene scene = new Scene(gridPane, 1000, 500); // set size to 720p

        // these are all the text components
        Label headerText = new Label("This program changes the color of this text!");
        Label redText = new Label("Red");
        Label greenText = new Label("Green");
        Label blueText = new Label("Blue");
        Label alphaText = new Label("Alpha");

        // slider components
        Slider redSlider = new Slider();
        Slider greenSlider = new Slider();
        Slider blueSlider = new Slider();
        Slider alphaSlider = new Slider();

        Font titleFont = Font.font("Times", FontWeight.EXTRA_BOLD, 42); // set title font and size
        Font otherFont = Font.font("Times", FontWeight.EXTRA_BOLD, 15); // set font and size

        GridPane.setConstraints(headerText, 1, 0);
        headerText.setFont(titleFont);

        GridPane.setConstraints(redText, 0, 1);
        redText.setTextFill(Color.RED);
        redText.setFont(otherFont);
        GridPane.setConstraints(redSlider, 1, 1);
        redSlider.setMax(1);

        GridPane.setConstraints(greenText, 0, 2);
        greenText.setTextFill(Color.GREEN);
        greenText.setFont(otherFont);
        GridPane.setConstraints(greenSlider, 1, 2);
        greenSlider.setMax(1);

        GridPane.setConstraints(blueText, 0, 3);
        blueText.setTextFill(Color.BLUE);
        blueText.setFont(otherFont);
        GridPane.setConstraints(blueSlider, 1, 3);
        blueSlider.setMax(1);

        GridPane.setConstraints(alphaText, 0, 4);
        alphaText.setFont(otherFont);
        GridPane.setConstraints(alphaSlider, 1, 4);
        alphaSlider.setMax(1);
        alphaSlider.setValue(1); // default to full opacity so you can see the text
        
        gridPane.getChildren().addAll(headerText, redText,  redSlider, greenText, greenSlider, blueText, blueSlider, alphaText, alphaSlider);

        // updates color eahc time a slider is changed
        redSlider.valueProperty().addListener(ov ->
        {
            headerText.setTextFill(Color.color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue(),alphaSlider.getValue()));
            System.out.println(redSlider.getValue()); // testing
        });

        greenSlider.valueProperty().addListener(ov ->
        {
            headerText.setTextFill(Color.color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue(),alphaSlider.getValue()));
            System.out.println(greenSlider.getValue()); // testing
        });

        blueSlider.valueProperty().addListener(ov ->
        {
            headerText.setTextFill(Color.color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue(),alphaSlider.getValue()));
            System.out.println(blueSlider.getValue()); // testing
        });

        alphaSlider.valueProperty().addListener(ov ->
        {
            headerText.setTextFill(Color.color(redSlider.getValue(),greenSlider.getValue(),blueSlider.getValue(),alphaSlider.getValue()));
            System.out.println(alphaSlider.getValue()); // testing
        });

        gridPane.setAlignment(Pos.CENTER);

        primaryStage.setTitle("ColorPickerForTextProgramForExerciseSixteenPointOneSevenForSoftwareDevelopmentForJavaForTheBookCalledIntroductionToJavaProgrammingAndDataStructuresComprehensiveVersion"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
}