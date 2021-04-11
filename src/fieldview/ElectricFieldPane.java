/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldview;

import java.io.FileNotFoundException;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author ismail
 */

public class ElectricFieldPane extends BorderPane{
    private GraphPane grid;
    private RightBorder border;
    

    public ElectricFieldPane(double size) throws FileNotFoundException {
        this.grid = new GraphPane(25, 15, size);
        this.border = new RightBorder();
        
        this.setPrefHeight(1080);
        this.setPrefHeight(1920);
        this.getStyleClass().add("main-pane");
        
        this.setCenter(grid);
        this.setRight(border);
    }
    
    public void setSize(double size){
        this.grid.insertLabelAxis(size);
    }

    public GraphPane getGrid() {
        return grid;
    }

    public RightBorder getRightBorder() {
        return border;
    }
    
}
