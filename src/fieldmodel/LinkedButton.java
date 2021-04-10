/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldmodel;

import javafx.scene.control.Button;

/**
 *
 * @author ismail
 */

public class LinkedButton extends Button{
    private Button delete = new Button("Delete");
    private Charge charge;

    public LinkedButton(Charge charge) {
        this.charge = charge;
    }
    
    
    //Getter and setter
    public Charge getCharge() {
        return charge;
    }

    public Button getDelete() {
        return delete;
    }
    
    
}
