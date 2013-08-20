/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4_kiaule_petkus;

import java.util.Stack;

/**
 *
 * @author Tautvydas
 */
public class Žaidėjas {
    public int bendra;
    public Stack taškai;
    public int degimųSkaičius;

    public Žaidėjas() {
        this.bendra = 0;
        this.taškai.clear();
        this.degimųSkaičius = 0;
    }

    public int getBendra() {
        return bendra;
    }

    public Stack getTaškai() {
        return taškai;
    }

    public void setBendra() {
        //this.bendra = Integer()taškai.
    }

    public void setTaškai(int taškas) {
        this.taškai.add(taškas);
    }

    public void setDegimųSkaičius() {
        degimųSkaičius++;
    }

    public int getDegimųSkaičius() {
        return degimųSkaičius;
    }
    
    
    
    
}
