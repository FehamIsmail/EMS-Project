/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author ismail
 */

public class ElectricField {
    private double x;
    private double y;
    private double magnitude;
    private double angle;
    private double ratio;
    private double distance;
    private boolean xpositive;
    private boolean ypositive;
    private DecimalFormat df = new DecimalFormat("#.###E0");
    private DecimalFormat df2 = new DecimalFormat("##0.#");

    public ElectricField(double x, double y, double magnitude, double angle) {
        this.x = x;
        this.y = y;
        this.magnitude = magnitude;
        this.angle = angle;
    }

    public ElectricField(double x, double y, Charge q, double ratio, double gPrefix) {
        //System.out.println("\n========================================");
        this.ratio = ratio;
        x = x - 672;
        y = 392 - y;
        
        angle = calculateAngle(x, y, q.getXpos().get(), -q.getYpos().get());
        distance = calculateDistance(x, y, q.getXpos().get(), -q.getYpos().get(), gPrefix);
        calculateElectricField(x, y, q);
    }
    
    public double calculateDistance(double x, double y, double x1, double y1, double gPrefix){
        double deltaX = Math.abs(x1-x);
        if(x>x1)xpositive = true;
        if(x<x1) xpositive = false;
        if(y>y1) ypositive = true;
        if(y<y1) ypositive = false;
        
        double deltaY = Math.abs(y1-y);
        //distance in pixels
        distance = Math.sqrt(Math.pow(deltaX, 2)+Math.pow(deltaY, 2));
        //distance in size
        distance = distance * ratio;
        //distance in m
        distance = distance * (gPrefix);
        //System.out.println("distance: " + distance);
        return distance;
    }
    
    public double calculateAngle(double x, double y, double x1, double y1){
        double deltaX = Math.abs(x1-x);
        double deltaY = Math.abs(y1-y);
        double angle  = Math.atan(deltaY/deltaX);
        //System.out.println("angle :"+ angle);
        return angle;
    }
    
    public double calculateElectricField(double x, double y, Charge q){
        //System.out.println("Charge :" + q.getAbsolutecharge());
        magnitude = 8990000000.0 * q.getAbsolutecharge() * (1/Math.pow(distance, 2));
        //System.out.println("magnitude: "+ magnitude);
        
        x = Math.cos(angle)*magnitude;
        if(xpositive != q.isPositive()){
            x = -x;
        }
        y = Math.sin(angle)*magnitude;
        if(ypositive != q.isPositive()){
            y = -y;
        }
        this.x = x;
        this.y = y;
//        System.out.println("x: " + x);
//        System.out.println("y: " + y);
        return magnitude;
    }
    
    public static ElectricField sumOfElectricFields(ArrayList<ElectricField> list){
        double sumX = 0;
        double sumY = 0;
        for(ElectricField e : list){
            //System.out.println(e);
            sumX += e.getX();
            sumY += e.getY();
        }
        //sumX += 0.0000000000001;
        //sumY += 0.0000000000001;
        //Calculate magnitude
        double magnitude = Math.sqrt(Math.pow(sumX, 2) + Math.pow(sumY, 2));
        //Calculate angle
        double newangle = Math.atan(Math.abs(sumY)/Math.abs(sumX));
        if     (sumX > 0 && sumY < 0) newangle = (Math.PI*2) - newangle;
        else if(sumX < 0 && sumY < 0) newangle =  Math.PI    + newangle;
        else if(sumX < 0 && sumY > 0) newangle =  Math.PI    - newangle;
        else if(sumY == 0 && sumX < 0) newangle = Math.PI    + newangle;
        //Creating and returning the sum of electric fields
        return new ElectricField(sumX, sumY, magnitude, newangle);
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

    public double getAngle() {
        return angle;
    }

    @Override
    public String toString() {
        return "Electric Field { " + "x = " + df.format(x) + ", y = " + df.format(y) + ", magnitude = " + df.format(magnitude) + ", angle = " + df2.format(Math.toDegrees(angle)) + "° }";
    }
    
    
    
    
}
