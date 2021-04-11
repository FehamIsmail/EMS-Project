/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package properties;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import fieldview.SmallHBox;

/**
 *
 * @author ismail
 */
public class GridProperty extends VBox{
    private Label gridLabel = new Label("Grid properties");
    private SmallHBox labelBox, applyBox;
    private NumericProperty size;
    private Button apply = new Button("Apply");

    public GridProperty() {
        this.size = new NumericProperty("Grid Size: ", "m");
        this.size.getField().setTextFormatter(new DecimalTextFormatter(0, 2, false, 3));
        
        setLabel();
        setButton();
        
        this.getStyleClass().add("small-vbox");
        this.getChildren().addAll(labelBox, size, applyBox);
        
    }
    
    private void setLabel(){
        gridLabel.setFont(new Font("Segoe UI", 16));
        gridLabel.setUnderline(true);
        this.labelBox = new SmallHBox();
        labelBox.getChildren().add(gridLabel);
    }
    
    private void setButton(){
        this.applyBox = new SmallHBox();
        applyBox.getChildren().addAll(apply);
    }

    public Label getGridLabel() {
        return gridLabel;
    }

    public SmallHBox getLabelBox() {
        return labelBox;
    }

    public SmallHBox getApplyBox() {
        return applyBox;
    }

    public NumericProperty getSize() {
        return size;
    }

    public Button getApply() {
        return apply;
    }
    
    
    
    
}
