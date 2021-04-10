/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;

/**
 *
 * @author ismail
 */
public class EFVector {
    private ImageView image;
    private ElectricField ef;
    private boolean pressed;
    private double x;
    private double y;

    public EFVector(ElectricField ef, double x, double y) {
        this.ef = ef;
        this.x = x;
        this.y = y;
        this.pressed = false;
        //Create the image
        image = new ImageView(new Image("images/vector_resized.png"));
        image.setTranslateX(x-2);
        image.setTranslateY(y-7);
        //Set the rotation
        Rotate rotation = new Rotate();
        rotation.setPivotX(2);
        rotation.setPivotY(7);
        rotation.setAngle(-Math.toDegrees(ef.getAngle()));
        image.getTransforms().add(rotation);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
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
        final EFVector other = (EFVector) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        return true;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public ImageView getImage() {
        return image;
    }

    public ElectricField getEf() {
        return ef;
    }

    public void setEf(ElectricField ef) {
        this.ef = ef;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
}
