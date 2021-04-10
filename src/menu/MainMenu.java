/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author ismai
 */
public class MainMenu extends StackPane{
    private Button fieldButton = new Button("Electric Field Simulation");
    private Button forceButton = new Button("Electric Force Simulation");
    private Button motionButton = new Button("Charge Motion Simulation");
    private Button exitButton = new Button("Exit");
    
    
    public MainMenu(){
        this.setAlignment(Pos.CENTER);
        setButtons();
        this.setPrefWidth(1920);
        this.setPrefHeight(1080);
        
        this.setBackground(new Background(new BackgroundImage(new Image("images/EMS_background.png"), BackgroundRepeat.NO_REPEAT,
                           BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
    
    private void setButtons(){
        fieldButton.getStylesheets().add("styles/main-button.css");
        fieldButton.setTranslateX(0);
        fieldButton.setTranslateY(-20);
        
        forceButton.getStylesheets().add("styles/main-button.css");
        forceButton.setTranslateX(0);
        forceButton.setTranslateY(80);
        
        motionButton.getStylesheets().add("styles/main-button.css");
        motionButton.setTranslateX(0);
        motionButton.setTranslateY(180);
        
        exitButton.getStylesheets().add("styles/main-button.css");
        exitButton.setTranslateX(0);
        exitButton.setTranslateY(280);
        
        this.insertElement(fieldButton);
        this.insertElement(forceButton);
        this.insertElement(motionButton);
        this.insertElement(exitButton);
    }
    
    public void insertElement(Node n){
        this.getChildren().add(n);
    }
    
    public void setButtonHandlers(Stage stage){
        
    }

    public Button getFieldButton() {
        return fieldButton;
    }

    public Button getForceButton() {
        return forceButton;
    }

    public Button getMotionButton() {
        return motionButton;
    }

    public Button getExitButton() {
        return exitButton;
    }
    
}
