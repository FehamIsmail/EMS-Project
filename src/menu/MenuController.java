/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import fieldcontroller.EFController;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.stage.Stage;
import motioncontroller.MotionController;
import motionview.SimWindow;
import view.ElectricFieldPane;

/**
 *
 * @author ismai
 */
public class MenuController {
    private Scene menuScene;
    private Stage menuStage;
    private MainMenu menu;
    private MenuBorder menuBorder;
    //Electric field
    private ElectricFieldPane fieldPane;
    private EFController fieldController;
    //Charge Motion
    private SimWindow motionPane;
    private MotionController motionController;
    
    

    public MenuController() throws FileNotFoundException {
        this.menu = new MainMenu();
        this.menuBorder = new MenuBorder();
        this.menuBorder.setCenter(menu);
        //goToElectricField();
    }
    
    private void goToElectricField() throws FileNotFoundException{
        //Auto-size by going through the main menu first
        //This method fixes a sizing bug
        menu.setVisible(false);
        goToMainMenu();
        menu.setVisible(true);
        
        fieldController = new EFController();
        fieldPane = fieldController.getFieldPane();
        this.menuBorder.setCenter(fieldPane);
        
    }
    
    private void goToMainMenu(){
        this.menuBorder.setCenter(menu);
        this.menuBorder.autosize();
        this.menuStage.sizeToScene();
    }
    
    private void goToMotion(){
        motionController = new MotionController();
        motionPane = motionController.getSimPane();
        this.menuBorder.setCenter(motionPane);
        //Auto size
        this.menuBorder.autosize();
        this.menuStage.sizeToScene();
    }
    
    
    
    public void setMenuButtonHandlers (Stage stage){
        //Setting Main menu's button
        menu.getFieldButton().setOnAction(e -> {
                try {
                    goToElectricField();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
                }
        });
        menu.getMotionButton().setOnAction(e -> goToMotion());
        menu.getExitButton().setOnAction(e -> System.exit(0));
        
        //Setting MenuBar items
        menuBorder.getItemMenu().setOnAction(e -> goToMainMenu());
        menuBorder.getItemField().setOnAction(e -> {
            try {
                goToElectricField();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        menuBorder.getItemMotion().setOnAction(e -> goToMotion());
        menuBorder.getItemExit().setOnAction(e -> System.exit(0));
    }
    
    
    public void start(Stage stage){
        menuScene = new Scene(menuBorder);
        menuScene.getStylesheets().add("styles/layoutstyles.css");
        
        menuStage = stage;
        menuStage.setTitle("EMS - Electricity and Magnetism Simulators");
        menuStage.setScene(menuScene);
        menuStage.show();
        
        setMenuButtonHandlers(stage);
    }
}
