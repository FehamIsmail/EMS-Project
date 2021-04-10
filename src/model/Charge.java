/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import properties.ChargeProperty;

/**
 *
 * @author ismail
 */
public class Charge {
    private double charge;
    private Prefix prefix;
    private double absolutecharge;
    private LinkedButton delete;
    private ChargeProperty property;
    private DoubleProperty xpos;
    private DoubleProperty ypos;
    private DoubleProperty exactXpos;
    private DoubleProperty exactYpos;
    private DoubleProperty ratio;
    private boolean isPositive;
    private ChargeImage image;
    private ChargeImage selectedImage;
    private static int counter = 0;
    private int id;

    public Charge(double charge, Prefix prefix, double x, double y, boolean isPositive, double ratio) {
        this.charge = charge;
        this.prefix = prefix;
        this.isPositive = isPositive;
        calculateAbsoluteCharge();
        this.id = counter;
        counter++;
        setImages();
        setProperties(x, y, ratio);
        setBindings();
        this.delete   = new LinkedButton(this);
        this.property = new ChargeProperty(this);
    }
    
    private void setBindings(){
        this.getImage().translateXProperty().bindBidirectional(xpos);
        this.getImage().translateYProperty().bindBidirectional(ypos);
        this.getSelectedImage().translateXProperty().bindBidirectional(getImage().translateXProperty());
        this.getSelectedImage().translateYProperty().bindBidirectional(getImage().translateYProperty());
    }
    
    private void setProperties(double x, double y, double ratio){
        this.xpos = new SimpleDoubleProperty(x);
        this.ypos = new SimpleDoubleProperty(y);
        this.ratio = new SimpleDoubleProperty();
        this.ratio.set(ratio);
        
        exactXpos = new SimpleDoubleProperty(x*ratio);
        exactXpos.bind(Bindings.createDoubleBinding(() ->
                this.xpos.get()*this.ratio.get(), xpos, this.ratio));
        
        exactYpos = new SimpleDoubleProperty(y*ratio);
        exactYpos.bind(Bindings.createDoubleBinding(() ->
                this.ypos.get()*this.ratio.get(), ypos, this.ratio));
    }
    
    public void calculateAbsoluteCharge(){
        this.absolutecharge = charge * PrefixValue.getPrefixValue(prefix);
    }
    
    private void setImages(){
        this.image = new ChargeImage(isPositive, false);
        this.selectedImage = new ChargeImage(isPositive, true);
        this.image.setFitHeight(32);
        this.image.setFitWidth(32);
        this.selectedImage.setFitWidth(32);
        this.selectedImage.setFitHeight(32);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
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
        final Charge other = (Charge) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    //Getter and setter
    public ChargeImage getSelectedImage() {
        return selectedImage;
    }

    public DoubleProperty getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio.set(ratio);
    }

    public double getExactXpos() {
        return exactXpos.getValue();
    }

    public double getExactYpos() {
        return exactYpos.getValue();
    }

    public int getId() {
        return id;
    }
    
    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
        calculateAbsoluteCharge();
    }

    public Prefix getPrefix() {
        return prefix;
    }

    public void setPrefix(Prefix prefix) {
        this.prefix = prefix;
    }

    public double getAbsolutecharge() {
        return absolutecharge;
    }

    public void setAbsolutecharge(double absolutecharge) {
        this.absolutecharge = absolutecharge;
    }

    public LinkedButton getDelete() {
        return delete;
    }

    public void setDelete(LinkedButton delete) {
        this.delete = delete;
    }

    public ChargeProperty getProperty() {
        return property;
    }

    public void setProperty(ChargeProperty property) {
        this.property = property;
    }

    public ChargeImage getImage() {
        return image;
    }

    public void setImage(ChargeImage image) {
        this.image = image;
        
    }

    public boolean isPositive() {
        return isPositive;
    }

    public DoubleProperty getXpos() {
        return xpos;
    }

    public DoubleProperty getYpos() {
        return ypos;
    }

    @Override
    public String toString() {
        return "Charge{" + "xpos=" + xpos + ", ypos=" + ypos + ", exactXpos=" + exactXpos + ", exactYpos=" + exactYpos + ", isPositive=" + isPositive + ", id=" + id + '}';
    }

    
    
    
    
    
    
    
    
    
    
    
    
}
