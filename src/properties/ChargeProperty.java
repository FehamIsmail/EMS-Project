/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package properties;

import java.util.Objects;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import fieldmodel.Charge;
import fieldview.SmallHBox;

/**
 *
 * @author ismail
 */

public class ChargeProperty extends VBox{
    private Label chargeLabel = new Label("Charge properties");
    SmallHBox labelBox;
    SmallHBox ButtonsBox;
    private Charge parentCharge;
    private NumericProperty charge;
    private NumericProperty xpos;
    private NumericProperty ypos;
    private Button apply = new Button("Apply");

    public ChargeProperty(Charge parentcharge) {
        this.parentCharge = parentcharge;
        this.charge = new NumericProperty("Charge: ", "C");
        this.xpos = new NumericProperty("X Pos: ", "m");
        this.ypos = new NumericProperty("Y Pos: ", "m");
        
        setButton();
        setLabel();
        
        this.getStyleClass().add("small-vbox");
        this.getChildren().addAll(labelBox, charge, xpos, ypos, ButtonsBox);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.parentCharge);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChargeProperty other = (ChargeProperty) obj;
        if (!Objects.equals(this.parentCharge, other.parentCharge)) {
            return false;
        }
        return true;
    }
    
    
    
    private void setLabel(){
        chargeLabel.setFont(new Font("Segoe UI", 16));
        chargeLabel.setUnderline(true);
        this.labelBox = new SmallHBox();
        labelBox.getChildren().add(chargeLabel);
    }
    
    private void setButton(){
        this.ButtonsBox = new SmallHBox();
        ButtonsBox.getChildren().addAll(apply, parentCharge.getDelete().getDelete());
    }
    
    //Getters
    public Charge getParentCharge() {
        return parentCharge;
    }

    public void setParentCharge(Charge parentCharge) {
        this.parentCharge = parentCharge;
    }

    public NumericProperty getCharge() {
        return charge;
    }

    public void setCharge(NumericProperty charge) {
        this.charge = charge;
    }

    public NumericProperty getXpos() {
        return xpos;
    }

    public void setXpos(NumericProperty xpos) {
        this.xpos = xpos;
    }

    public NumericProperty getYpos() {
        return ypos;
    }

    public void setYpos(NumericProperty ypos) {
        this.ypos = ypos;
    }

    public Button getApply() {
        return apply;
    }

    public void setApply(Button apply) {
        this.apply = apply;
    }


    
    
    
    
    
    
}
