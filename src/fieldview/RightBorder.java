/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldview;

import java.io.FileNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import properties.GridProperty;

/**
 *
 * @author ismail
 */

public class RightBorder extends VBox{
    private GridProperty gridProperty;
    private ChargeCreationBox chargeBox;
    private HBox errorBox;
    private Label error = new Label();

    public RightBorder() throws FileNotFoundException {
        this.gridProperty = new GridProperty();
        this.gridProperty.getSize().getField().setText("120");
        this.chargeBox = new ChargeCreationBox();
        setErrorBox();
        
        this.getStyleClass().add("vbox");
        this.getChildren().addAll(gridProperty, chargeBox, errorBox);
    }
    
    private void setErrorBox(){
        this.error.setTextFill(Paint.valueOf("#bf4949"));
        this.error.setFont(new Font("Segoe UI", 18));
        this.error.setTextAlignment(TextAlignment.CENTER);
        this.error.setUnderline(true);
        this.errorBox = new SmallHBox();
        this.errorBox.getStyleClass().add("error-box");
        this.errorBox.getChildren().add(error);
        this.errorBox.setVisible(false);
        this.errorBox.managedProperty().bind(this.errorBox.visibleProperty());
    }

    public HBox getErrorBox() {
        return errorBox;
    }

    public Label getError() {
        return error;
    }

    public GridProperty getGridProperty() {
        return gridProperty;
    }

    public ChargeCreationBox getChargeBox() {
        return chargeBox;
    }
    
    
    
    
}
