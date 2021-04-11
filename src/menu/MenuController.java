/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;

import chargecontroller.ChargeController;
import fieldcontroller.EFController;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.Scene;
import javafx.stage.Stage;
import motioncontroller.MotionSimController;
import motionview.SimWindow;
import fieldview.ElectricFieldPane;
import javafx.scene.layout.BorderPane;

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
    private MotionSimController motionController;
    //Charge Force (Coulomb's Law)
    private BorderPane chargePane;
    private ChargeController chargeController;
    
    

    public MenuController() throws FileNotFoundException {
        this.menu = new MainMenu();
        this.menuBorder = new MenuBorder();
        this.menuBorder.setCenter(menu);
    }
    
    private void goToElectricField() throws FileNotFoundException{
        //Change styling
        this.menuScene.getStylesheets().remove(0);
        this.menuScene.getStylesheets().add("styles/layoutstyles.css");
        //Auto-size by going through the main menu first
        //This method fixes a sizing bug
        menu.setVisible(false);
        goToMainMenu();
        menu.setVisible(true);
        //Changing center
        fieldController = new EFController();
        fieldPane = fieldController.getFieldPane();
        this.menuBorder.setCenter(fieldPane);
        
    }
    
    private void goToForce(){
        //Change styling
        this.menuScene.getStylesheets().remove(0);
        this.menuScene.getStylesheets().add("styles/stylesheet.css");
        chargeController = new ChargeController();
        chargePane = chargeController.setView();
        //Changing center
        this.menuBorder.setCenter(chargePane);
        sizeScene();
        
    }
    
    private void goToMainMenu(){
        //Change styling
        this.menuScene.getStylesheets().remove(0);
        this.menuScene.getStylesheets().add("styles/layoutstyles.css");
        //Changing center
        this.menuBorder.setCenter(menu);
        sizeScene();
        
    }
    
    private void goToMotion(){
        //Change styling
        this.menuScene.getStylesheets().remove(0);
        this.menuScene.getStylesheets().add("styles/layoutstyles.css");
        motionController = new MotionSimController();
        motionPane = motionController.getSimWindow();
        //Changing center
        this.menuBorder.setCenter(motionPane);
        sizeScene();
        
    }
    //Auto sizes the scene depending on the simulation
    private void sizeScene(){
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
        menu.getForceButton().setOnAction(e -> goToForce());
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
        menuBorder.getItemForce().setOnAction(e -> goToForce());
        menuBorder.getItemExit().setOnAction(e -> System.exit(0));
    }
    
    
    public void start(Stage stage){
        menuScene = new Scene(menuBorder);
        menuScene.getStylesheets().add("styles/layoutstyles.css");
        
        menuStage = stage;
        menuStage.setTitle("EMS - Electricity and Magnetism Simulators");
        menuStage.setScene(menuScene);
        menuStage.setResizable(false);
        menuStage.show();
        
        setMenuButtonHandlers(stage);
    }
}
