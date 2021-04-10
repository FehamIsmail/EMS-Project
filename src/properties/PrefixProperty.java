/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package properties;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import model.Prefix;

/**
 *
 * @author ismail
 */

public class PrefixProperty extends ComboBox{
    
    public PrefixProperty(){
        this.getItems().addAll(Prefix.values());
        this.setValue(Prefix.no_prefix);
        this.getStyleClass().add("cBox");
    }
    
}
