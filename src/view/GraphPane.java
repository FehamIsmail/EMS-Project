/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author ismail
 */

public class GraphPane extends StackPane{
    
    private LineChart lineChart;
    private Grid grid;
    private ArrayList<Label> xLabels = new ArrayList<>();
    private ArrayList<Label> yLabels = new ArrayList<>();
    private DecimalFormat df = new DecimalFormat("#,###.##");
    private Label statusLabel;
    private int rows;
    private int columns;
    
    public GraphPane(int columns, int rows, double size) {
        this.grid = new Grid(rows, columns);
        this.rows = rows;
        this.columns = columns;
        
        insertAxis();
        insertLabelAxis(size);
        insertElement(grid);
        
        this.statusLabel = new Label();
        this.statusLabel.setTranslateY(470);
        this.setAlignment(Pos.CENTER);
        this.getStyleClass().add("graph");
        
        insertClip();
        
        this.insertElement(statusLabel);
    }
    
    private void insertAxis(){
        ImageView x = new ImageView(new Image("images/x-axis.png"));
        x.setTranslateY(392+13);
        
        ImageView y = new ImageView(new Image("images/y-axis.png"));
        y.setTranslateX(-672-14);
        
        insertElement(x);
        insertElement(y);
    }
    
    public void insertElement(Node n){
        this.getChildren().add(n);
    }
    
    public void removeElement(Node n){
        this.getChildren().remove(n);
    }
    
    private void insertClip(){
        final Rectangle outputClip = new Rectangle();
        this.setClip(outputClip);

        this.layoutBoundsProperty().addListener((ov, oldValue, newValue) -> {
        outputClip.setWidth(newValue.getWidth());
        outputClip.setHeight(newValue.getHeight());
        });  
    }
    
    public void insertLabelAxis(double size){
        
        if(!xLabels.isEmpty() || !yLabels.isEmpty()){
            for(Label l : xLabels){
                this.removeElement(l);
            }
            for(Label l : yLabels){
                this.removeElement(l);
            }
            xLabels.clear();
            yLabels.clear();
        }
        double a = 0;
        double b = 0;
        double ratio = (size*2)/24;
        //x-axis
        for(int i=0;i<columns;i++){
            a = i - 12; 
            b = a * ratio;
            Label l = new Label(df.format(b));
            l.setTranslateX(a*56);
            l.setTranslateY(392+24);
            xLabels.add(l);
            insertElement(l);
        }
        //y-axis
        for(int j=0;j<rows;j++){
            a = j - 7; 
            b = -a * ratio;
            Label l;
            if(b == 0){
                l = new Label("0");
            }else{
                l = new Label(df.format(b));
            }
            l.setTranslateX(-672-30);
            l.setTranslateY(a*56);
            yLabels.add(l);
            insertElement(l);
        }
    }

    public Grid getGrid() {
        return grid;
    }

    public Label getStatusLabel() {
        return statusLabel;
    }
    
    
    
    
        
   

}
