package com.example;

import java.util.List;
import java.util.stream.Collectors;
import java.text.DecimalFormat;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class App extends Application
{
    private Pane mainMenuPane = new Pane();
    private Pane originalPane = new Pane();

    private boolean isGameOver = false; 
    private boolean isEndTextShowing = false;

    private double t = 0; // for time, for enemy attacks
    private double timerTime = 0; // for the time test
    private DecimalFormat numberFormat = new DecimalFormat(".#"); // used to format the timer

    private Font titleFont = Font.font("Times", FontWeight.EXTRA_BOLD, 42); // set title font and size
    private Font otherFont = Font.font("Times", FontWeight.EXTRA_BOLD, 25); // set other font and size

    private Label timerText = new Label("Time: " + t);
    private Label gameOverText = new Label("GameOver!");

    private Sprite player = new Sprite(615, 615, 50, 50, "player", Color.PURPLE);

    // enemy stats
    private int numberOfEnemies = 26;
    private int enemyWidth = 30;
    private int enemyHeight = 30;
    private int enemyXPosition = 0;
    private int enemyYPosition = 30;
    private int enemyFireRate = 2;
    
    private void nextLevel() // makes a bunch of squares that will be enemies
    {
        for(int i = 0; i < numberOfEnemies; i++)
        {
            // spaces enemys equally apart (the name S was awful but I am too far.)
            Sprite s = new Sprite(enemyXPosition + i * 50, enemyYPosition, enemyWidth, enemyHeight, "enemy", Color.RED);

            originalPane.getChildren().add(s);
        }
    }

    private List<Sprite> sprites() // gets all the sprite in the pane, I had to use AI to figure this out.
    {   
        try
        {
            // This gets all child nodes from the pane of type Sprite. This was a big headache because my timer was being cast to Sprite.
            return originalPane.getChildren().stream().filter(n -> n instanceof Sprite).map(n -> (Sprite)n).collect(Collectors.toList());
        }
        catch (Exception e)
        {
            // TODO: handle exception
            System.out.println("breaks at list");
            return null;
        }
    }

    private void update() // work in progress,I  am following some tutorials to get collision and smoother moving sprites.
    {
        t += 0.016; // from what I understand this is the speed (16 miliseconds) that the update runs at
        if (!player.isDead) // stop timer when player dies
        {
            timerTime += 0.016;
        }
        timerText.setText("Time: " + numberFormat.format(timerTime)); // game timer

        sprites().forEach(s -> 
        {
            switch (s.type) // again horribly named 
            {
                case "enemyattackSprite":
                    s.moveDown();

                    if(s.getBoundsInParent().intersects(player.getBoundsInParent())) // check for collision
                    {
                        player.isDead = true; // kill player
                        s.isDead = true; // kill enemy projectile 
                        System.out.println("Player is dead");
                        isGameOver = true;
                        timerTime = 0; // reset time for timer when reset button is hit
                    }
                    else if (s.getTranslateY() > 700) // remove attacks that go offscreen
                    {
                        s.isDead = true;
                    }
                    break;
            
                // case "playerattackSprite": // this is old from previous version when player could attack back
                //     s.moveUp();

                //     sprites().stream().filter(e -> e.type.equals("enemy")).forEach(enemy ->
                //     {
                //         if (s.getBoundsInParent().intersects(enemy.getBoundsInParent()))
                //         {
                //             enemy.isDead = true;
                //             s.isDead = true;
                //         }
                //     });
                //     break;

                case "enemy":
                    if (t > enemyFireRate) // fires every 2 seconds (or will it?)
                    {
                        if (Math.random() < 0.3) // decides if enemy should attack
                        {
                            attack(s);
                        }
                    }
                    break;
            }
        });

        // remove if dead, the catch should default to not remove other items
        originalPane.getChildren().removeIf(n ->
        {
            try
            {
                Sprite s = (Sprite) n;
                return s.isDead;
            }
            catch (Exception e)
            {           
                return false;
            }
        });

        if (t > 2) // resets t to attack again
        {
            t = 0;
        }
    }
    
    // shoots a pellet (creates a tiny grey sprite)
    private void attack(Sprite whoAttacked)
    {
        Sprite s = new Sprite
        (
            (int)whoAttacked.getTranslateX() + 12, (int)whoAttacked.getTranslateY(), 5, 20, whoAttacked.type + "attackSprite", Color.SILVER
        );
        
        originalPane.getChildren().add(s);
    }
    

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage)
    {
        Scene mainMenuScene = new Scene(mainMenuPane);
        mainMenuPane.setPrefSize(1280, 720); // size

        Scene gameScene = new Scene(originalPane);
        originalPane.setPrefSize(1280, 720);


        
     // -------------------- Main Menu --------------------------------------------------------------------------------------------//  
        Label headerText = new Label("How long can you survive?!");
        headerText.layoutXProperty().bind(mainMenuPane.widthProperty().subtract(headerText.widthProperty()).divide(2)); // set to be the center of the screen on X axis (resolution will now be locked to 1280*720 but I am keeping this)
        headerText.setFont(titleFont);

        Button playGameButton = new Button();
        playGameButton.setText("PLAY!");
        playGameButton.setPrefSize(100, 50);
        playGameButton.layoutXProperty().bind(mainMenuPane.widthProperty().subtract(playGameButton.widthProperty()).divide(2)); // set to be the center of the screen on X axis
        playGameButton.layoutYProperty().bind(mainMenuPane.heightProperty().subtract(playGameButton.heightProperty()).divide(2)); // set to be the center of the screen on Y axis

        mainMenuPane.getChildren().addAll(headerText, playGameButton);
     // ---------------------------------------------------------------------------------------------------------------------------//
     // ------------------------ Game----------------------------------------------------------------------------------------------// 
        timerText.layoutXProperty().bind(originalPane.widthProperty().subtract(timerText.widthProperty()).divide(2)); // set to be the center of the screen on X axis
        timerText.setFont(otherFont);

        gameOverText.layoutXProperty().bind(originalPane.widthProperty().subtract(gameOverText.widthProperty()).divide(2));
        gameOverText.layoutYProperty().bind(originalPane.heightProperty().subtract(gameOverText.heightProperty()).divide(2));
        gameOverText.setFont(titleFont);

        originalPane.getChildren().addAll(timerText, player);

        Button resetButton = new Button();
        resetButton.setText("Reset!");
        resetButton.setPrefSize(100, 50);
        resetButton.layoutXProperty().bind(mainMenuPane.widthProperty().subtract(playGameButton.widthProperty()).divide(2)); // set to be the center of the screen on X axis
        resetButton.layoutYProperty().bind(mainMenuPane.heightProperty().subtract(playGameButton.heightProperty()).divide(3)); // set to be the center of the screen on Y axis
        
        AnimationTimer timer = new AnimationTimer()
        {
            public void handle(long now)
            {
                if (!isGameOver)
                {
                    update();
                }
                else
                {
                    if (!isEndTextShowing)
                    {
                        originalPane.getChildren().addAll(gameOverText, resetButton); // only added when game is over
                        isEndTextShowing = true;
                    }
                }
            }
        };

        // play button event switches from mainMenu to game scene
        playGameButton.setOnAction(e ->
        {
            primaryStage.setScene(gameScene);
            timer.start();
        });

        // clean up and reset
        resetButton.setOnAction(e ->
        {
            originalPane.getChildren().remove(resetButton);
            originalPane.getChildren().remove(gameOverText);

            isEndTextShowing = false;
            isGameOver = false;

            player.isDead = false;
            originalPane.getChildren().add(player);
        });

        nextLevel();


     // -------------------------------------------------------------------------------------------------------------------------//
        gameScene.setOnKeyPressed(e -> 
        {
            switch (e.getCode())
            {
                case LEFT:
                case A:
                    player.moveLeft();
                    break;

                case RIGHT:
                case D:
                    player.moveRight();
                    break;

                // case UP: // moving up and down is no longer needed
                // case W:
                //     player.moveUp();
                //     break;

                // case DOWN:
                // case S:
                //     player.moveDown();
                //     break;
                // case SPACE: // players ability to attack has been revoked. (I wanted the game to be longer than 5 seconds)
                //     attack(player);
                //     break;
            }
        });

        primaryStage.setScene(mainMenuScene); // Place the scene in the stage
        primaryStage.setTitle("Game"); // Set the stage title
        primaryStage.setResizable(false);
        primaryStage.show(); // Display the stage
    }
    
    private static class Sprite extends Rectangle
    {
        boolean isDead = false;
        final String type;

        Sprite(int x, int y, int w, int h, String type, Color color)
        {
            super(w, h, color);
            this.type = type;
            setTranslateX(x);
            setTranslateY(y);
        }

        void moveLeft()
        {   
            if (this.getTranslateX() > 0) // stops from going offscreen left
            {
                setTranslateX(getTranslateX() - 10);  // moves left 10 pixels  
            }
        }
        void moveRight()
        {
            if (this.getTranslateX() < (1280 - this.getWidth())) // stops from going offscreen right
            {
                setTranslateX(getTranslateX() + 10); // move right 10 pixels
            }
        }
        void moveUp() // no longer needed player can only move sidways
        {   
            if (this.getTranslateY() > 0)
            {
                setTranslateY(getTranslateY() - 4); // move up 4 pixels
            }

        }
        void moveDown() // no longer needed 
        {
            if (this.getTranslateY() < (720 - this.getHeight()))
            {
                setTranslateY(getTranslateY() + 4);// move down 4 pixels   
            }
        }
    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }
}