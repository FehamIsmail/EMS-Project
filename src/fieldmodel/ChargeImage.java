/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldmodel;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author ismail
 */
public class ChargeImage extends ImageView{
    private Image image;
    private final boolean isSelected;
    private final boolean isPositive;
    private static int counter = 0;
    private int id;


    public ChargeImage(boolean isPositive, boolean isSelected) {
        super(new Image(findPath(isPositive, isSelected)));
        
        this.isSelected = isSelected;
        this.isPositive = isPositive;
        this.id = counter;
        counter++;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
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
        final ChargeImage other = (ChargeImage) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    private static String findPath(boolean isPositive, boolean isSelected){
        String path = "";
        if(isPositive){
            if(isSelected){
                path = "images/positive_selected.png";
            }else{
                path = "images/positive.png";
            }
        }
        else{
            if(isSelected){
                path = "images/negative_selected.png";
            }else{
                path = "images/negative.png";
            }
        }
        return path;
    }

    
    
    

    public boolean isSelected() {
        return isSelected;
    }

    public boolean isPositive() {
        return isPositive;
    }


    
    
    
}
