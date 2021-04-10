/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package properties;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import fieldmodel.Prefix;

/**
 *
 * @author ismail
 */

public class NumericProperty extends HBox{
    private TextField value;
    private Label label;
    private Label unit;
    private PrefixProperty prefix;
    
    public NumericProperty(String label, String unit){
        this.value = new TextField();
        this.label = new Label(label);
        this.unit = new Label(unit);
        this.prefix = new PrefixProperty();
        this.setAlignment(Pos.BOTTOM_CENTER);
        this.value.setTextFormatter(new DecimalTextFormatter(0, 3, true, 6));
        
        this.setPadding(new Insets(3, 3, 3, 3));
        this.setSpacing(5);
        
        this.getChildren().addAll(this.label, value, prefix, this.unit);
    }
    
    
    //Getter and setter
    public Double getValue() {
        return Double.parseDouble(value.getText());
    }
    
    public TextField getField(){
        return value;
    }

    public Prefix getPrefix() {
        return (Prefix)prefix.getValue();
    }
    
    public PrefixProperty getPrefixProperty() {
        return prefix;
    }

    public void setPrefix(PrefixProperty prefix) {
        this.prefix = prefix;
    }

    public void setTextField(TextField tf) {
        this.value = tf;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
    
    
    
    
    
}
