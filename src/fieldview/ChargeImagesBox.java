
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldview;

import fieldmodel.ChargeImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.channels.Channels;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author ismail
 */

public class ChargeImagesBox extends HBox{
    private ChargeImage positive;
    private ChargeImage negative;

    public ChargeImagesBox() throws FileNotFoundException {
        
        setPositiveImage();
        setNegativeImage();
        this.getStyleClass().add("charge-box");
        
        this.getChildren().addAll(positive, negative);
    }
    
    private void setPositiveImage() throws FileNotFoundException{
        positive = new ChargeImage(true, false);
        positive.setFitHeight(64);
        positive.setFitWidth(64);
    }
    
    private void setNegativeImage() throws FileNotFoundException{
        negative = new ChargeImage(false, false);
        negative.setFitHeight(64);
        negative.setFitWidth(64);
    }

    //Getters
    public ChargeImage getPositive() {
        return positive;
    }

    public ChargeImage getNegative() {
        return negative;
    }
    
}
