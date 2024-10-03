package com.example;

import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    Pane mainMenuPane = new Pane();
    Pane originalPane = new Pane();

    private Sprite player = new Sprite(615, 650, 50, 50, "Player", Color.PURPLE);

    private List<Sprite> sprites()
    {

        return originalPane.getChildren().stream().map(n -> (Sprite)n).collect(Collectors.toList());
    }

    private void update()
    {
        // sprites().forEach(s -> 
        // {
        //     switch (s.type)
        //     {
        //         case "enemyattacksprite":
        //             s.moveDown();

        //             if(s.getBoundsInParent().intersects(player.getBoundsInParent())) // check for collision
        //             {
        //                 player.isDead = true;
        //                 s.isDead = true;
        //                 System.out.println("Player is dead");
        //             }
        //             break;
            
        //         case "playerattacksprite":
        //             s.moveUp();

        //             sprites().stream().filter(e - > e.type.equals("enemy")).forEach(enemy ->
        //             {
        //                 if (s.getBoundsInParent().intersects(enemy.getBoundsInParent()))
        //                 {
                            
        //                 }
        //             });
        //             break;
        //     }
        // });
    }

    private void nextLevel()
    {
        for(int i = 0; i < 5; i++)
        {
            Sprite s = new Sprite(90 + i * 100, 150, 30, 30, "enemy", Color.RED);

            originalPane.getChildren().add(s);
        }
    }

    private void attack(Sprite whoAttacked)
    {
        Sprite s = new Sprite
        (
            (int)whoAttacked.getTranslateX() + 25, (int)whoAttacked.getTranslateY() + 25, 5, 20, whoAttacked.type + "attackSprite", Color.SILVER
        );
        
        originalPane.getChildren().add(s);
    }
    

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage)
    {
        Scene mainMenuScene = new Scene(mainMenuPane);
        Scene gameScene = new Scene(originalPane);

        Font titleFont = Font.font("Times", FontWeight.EXTRA_BOLD, 42); // set title font and size
        Font otherFont = Font.font("Times", FontWeight.EXTRA_BOLD, 15); // set other font and size
        
     // -------------------- Main Menu --------------------------------------------------------------------------------------------//  
        mainMenuPane.setPrefSize(1280, 720);

        Label headerText = new Label("This program will be a game!");
        headerText.layoutXProperty().bind(mainMenuPane.widthProperty().subtract(headerText.widthProperty()).divide(2)); // set to be the center of the screen
        headerText.setFont(titleFont);

        Button playGameButton = new Button();
        playGameButton.setText("PLAY!");
        playGameButton.setPrefSize(100, 50);
        playGameButton.layoutXProperty().bind(mainMenuPane.widthProperty().subtract(playGameButton.widthProperty()).divide(2));
        playGameButton.layoutYProperty().bind(mainMenuPane.heightProperty().subtract(playGameButton.heightProperty()).divide(2));
        playGameButton.setOnAction(e -> primaryStage.setScene(gameScene));

        mainMenuPane.getChildren().addAll(headerText, playGameButton);
     // ---------------------------------------------------------------------------------------------------------------------------//
     // ------------------------ Game----------------------------------------------------------------------------------------------// 
        originalPane.setPrefSize(1280, 720);

        originalPane.getChildren().addAll(player);

        AnimationTimer timer = new AnimationTimer()
        {
            public void handle(long now)
            {
                update();
            }
        };

        timer.start();

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

                case UP:
                case W:
                    player.moveUp();
                    break;

                case DOWN:
                case S:
                    player.moveDown();
                    break;
                case SPACE:
                    attack(player);
                    break;
            }
        });

        primaryStage.setScene(mainMenuScene); // Place the scene in the stage
        primaryStage.setTitle("Game"); // Set the stage title
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
            setTranslateX(getTranslateX() - 7);
        }
        void moveRight()
        {
            setTranslateX(getTranslateX() + 7);
        }
        void moveUp()
        {
            setTranslateY(getTranslateY() - 7);
        }
        void moveDown()
        {
            setTranslateY(getTranslateY() + 7);
        }
    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }
}