/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldview;

import java.io.FileNotFoundException;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 *
 * @author ismail
 */

public class ChargeCreationBox extends VBox{
    private Label createLabel = new Label("Choose a charge to create");
    private ChargeImagesBox chargeImages;
    private Label select = new Label("Please select a charge");
    private SmallHBox labelBox, selectBox;

    public ChargeCreationBox() throws FileNotFoundException {
        chargeImages = new ChargeImagesBox();
        
        createLabel.setFont(new Font("Segoe UI", 16));
        createLabel.setUnderline(true);
        labelBox = new SmallHBox();
        labelBox.getChildren().add(createLabel);
        
        select.setTextFill(Paint.valueOf("#bf4949"));
        select.setFont(new Font("Segoe UI", 18));
        selectBox = new SmallHBox();
        selectBox.getChildren().add(select);
        
        this.getStyleClass().add("small-vbox");
        this.getChildren().addAll(labelBox, chargeImages, selectBox);
    }

    
    public Label getCreateLabel() {
        return createLabel;
    }

    public ChargeImagesBox getChargeImages() {
        return chargeImages;
    }

    public Label getSelect() {
        return select;
    }

    public SmallHBox getLabelBox() {
        return labelBox;
    }

    public SmallHBox getSelectBox() {
        return selectBox;
    }
    
    
    
}
