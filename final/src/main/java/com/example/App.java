package com.example;

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
    Pane originalPane = new Pane();

    private Sprite player = new Sprite(615, 650, 50, 50, "Player", Color.PURPLE);

    public Parent drawStuff()
    {
        originalPane.setPrefSize(1280, 720);

        Label headerText = new Label("This program will be a game!");

        Font titleFont = Font.font("Times", FontWeight.EXTRA_BOLD, 42); // set title font and size
        Font otherFont = Font.font("Times", FontWeight.EXTRA_BOLD, 15); // set font and size

        GridPane.setConstraints(headerText, 1, 0);
        headerText.setFont(titleFont);

        originalPane.getChildren().addAll(headerText, player);

        AnimationTimer timer = new AnimationTimer()
        {
            public void handle(long now)
            {
                update();
            }
        };

        timer.start();

        nextLevel();

        return originalPane;
    }

    private void update()
    {

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
        Scene scene = new Scene(drawStuff());

        scene.setOnKeyPressed(e -> 
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
                case SPACE:
                    player.moveUp();
                    break;

                case DOWN:
                case S:
                    player.moveDown();
                    break;
            }
        });

        primaryStage.setScene(scene); // Place the scene in the stage
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
            setTranslateX(getTranslateX() - 5);
        }
        void moveRight()
        {
            setTranslateX(getTranslateX() + 5);
        }
        void moveUp()
        {
            setTranslateY(getTranslateY() - 5);
        }
        void moveDown()
        {
            setTranslateY(getTranslateY() + 5);
        }
    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }
}