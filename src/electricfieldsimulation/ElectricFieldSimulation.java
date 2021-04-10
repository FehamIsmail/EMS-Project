/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElectricFieldSimulation;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.stage.Stage;
import menu.MenuController;

/**
 *
 * @author ismail
 */
public class ElectricFieldSimulation extends Application {
    
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        MenuController menu = new MenuController();
        menu.start(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
