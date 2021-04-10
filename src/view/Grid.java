/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import model.Charge;
import model.EFVector;
import model.ElectricField;
import model.PrefixValue;

/**
 *
 * @author ismail
 */

public class Grid extends Pane{
    private int rows; 
    private int columns;
    

    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        
        this.setMaxSize((columns*53)+19, (rows*52)+5);
        this.getStyleClass().add("grid");
        
        
        createGrid();
        
    }
    
    private void createGrid(){
        int g = 56;
        //vertical lines
        for(int i = 0; i<columns; i++){
            Line line = new Line(i*g, 0, i*g, g*(rows-1));
            if(i==12){
                line.setStrokeWidth(1.5);
            }else{
                line.setStroke(Paint.valueOf("#525252"));
            }
            insertElement(line);
        }
        //horizontal lines
        for(int j = 0; j<rows; j++){
            Line line = new Line(0, j*g, g*(columns-1), j*g);
            if(j==7){
                line.setStrokeWidth(1.5);
            }else{
                line.setStroke(Paint.valueOf("#525252"));
            }
            insertElement(line);
        }
    }
    
    public void insertElement(Node n){
        this.getChildren().add(n);
    }
    
    public void removeElement(Node n){
        this.getChildren().remove(n);
    }
    
    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    
    
    
}
