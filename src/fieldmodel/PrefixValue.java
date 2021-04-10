/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fieldmodel;

/**
 *
 * @author ismail
 */

public class PrefixValue {
    public static double getPrefixValue(Prefix unit){
        switch(unit){
            case P:
                return 1000000000000000.0;
            case T:
                return 1000000000000.0;
            case G:
                return 1000000000.0;
            case M:
                return 1000000.0;
            case k:
                return 1000.0;
            case h:
                return 100.0;
            case da:
                return 10.0;
            case no_prefix:
                return 1.0;
            case d:
                return 0.1;
            case c:
                return 0.01;
            case m:
                return 0.001;
            case μ:
                return 0.000001;
            case n:
                return 0.000000001;
            case p:
                return 0.000000000001;
            case f:
                return 0.000000000000001;
            case a:
                return 0.000000000000000001;
            case z:
                return 0.000000000000000000001;
            case y:
                return 0.000000000000000000000001;
                    
        }
        System.out.println("An error has occured while converting units");
        return 0;
    }
}
