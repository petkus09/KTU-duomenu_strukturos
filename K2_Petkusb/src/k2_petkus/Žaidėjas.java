/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package k2_petkus;

import java.util.Comparator;

/**
 *
 * @author Tautvydas
 */
public class Žaidėjas implements Comparable<Žaidėjas>{
    private String komanda;
    private String pavardė;
    private int ūgis;
    private double ni;

    public Žaidėjas() {
    }

    public Žaidėjas(String komanda, String pavardė, int ūgis, double ni) {
        this.komanda = komanda;
        this.pavardė = pavardė;
        this.ūgis = ūgis;
        this.ni = ni;
        if (ni > 100.0)
            this.ni = 100.0;
        if (ni < 0.0)
            this.ni = 0.0;
    }

    public void setKomanda(String komanda) {
        this.komanda = komanda;
    }

    public void setPavardė(String pavardė) {
        this.pavardė = pavardė;
    }

    public void setŪgis(int ūgis) {
        this.ūgis = ūgis;
    }

    public void setNi(double ni) {
        this.ni = ni;
    }

    public String getKomanda() {
        return komanda;
    }

    public String getPavardė() {
        return pavardė;
    }

    public double getŪgis() {
        return ūgis;
    }

    public double getNi() {
        return ni;
    }

    @Override
    public String toString() {
        return ("Komanda: " + komanda + ", Pavardė: " + pavardė + ", Ūgis: " + 
                ūgis+ ", Naudingumo indeksas: "+ ni);
    }  

    @Override
    public int compareTo(Žaidėjas o) {
        return ni == o.ni ? 0 : ni < o.ni ? 1 : -1;
    }
    
}
