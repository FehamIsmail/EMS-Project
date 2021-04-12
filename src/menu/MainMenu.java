/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
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
    private Hyperlink documentation = new Hyperlink();
    
    
    public MainMenu(){
        this.setAlignment(Pos.CENTER);
        setButtons();
        this.setPrefWidth(1920);
        this.setPrefHeight(986);
        setDocumentationLink();
        Image BgImage = new Image("images/EMS_background.png");
        
        this.setBackground(new Background(new BackgroundImage(BgImage, BackgroundRepeat.NO_REPEAT,
                           BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
    }
    
    private void setDocumentationLink(){
        documentation.setText("Documentation");
        documentation.setStyle("-fx-font-family: Georgia; -fx-font-weight: bold;-fx-font-size: 24px; -fx-text-fill: #206eac");
        documentation.setOnAction(e ->{
            if(Desktop.isDesktopSupported()){
                try{
                    Desktop.getDesktop().browse(new URI("https://docs.google.com/document/d/1MzmXXhk0cH8QYQknbIciqSAlZWjQCNmeFYiLl3iJsr0/edit?usp=sharing"));
                }catch(IOException e1){
                    e1.printStackTrace();
                }catch(URISyntaxException e1){
                    e1.printStackTrace();
                }
            }
        });
        documentation.setTranslateY(410);
        documentation.setTranslateX(740);
        this.insertElement(documentation);
    }
    
    private void setButtons(){
        fieldButton.getStylesheets().add("styles/main-button.css");
        fieldButton.setTranslateX(0);
        fieldButton.setTranslateY(30);
        
        forceButton.getStylesheets().add("styles/main-button.css");
        forceButton.setTranslateX(0);
        forceButton.setTranslateY(130);
        
        motionButton.getStylesheets().add("styles/main-button.css");
        motionButton.setTranslateX(0);
        motionButton.setTranslateY(230);
        
        exitButton.getStylesheets().add("styles/main-button.css");
        exitButton.setTranslateX(0);
        exitButton.setTranslateY(330);
        
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
