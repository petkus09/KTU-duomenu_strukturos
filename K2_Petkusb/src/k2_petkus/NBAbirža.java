/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package k2_petkus;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 * @author Tautvydas
 */
public class NBAbirža {
    LinkedList<Žaidėjas> agentura;

    public NBAbirža() {
        agentura = new LinkedList<Žaidėjas>();
    }
    
    public void detiŽaidėją(Žaidėjas a){
        agentura.add(a);
    }
    
    public LinkedList<Žaidėjas> rastiAukštusŽaidėjus(int riba){
        LinkedList<Žaidėjas> rez = new LinkedList<Žaidėjas>();
        for (Žaidėjas a : agentura)
            if (a.getŪgis() > riba)
                rez.add(a);
        return rez;
    }
    
    public void spausdintiSąrašą(LinkedList<Žaidėjas> a, String msg){
        System.out.println(msg);
        for (Žaidėjas b : a)
            System.out.println(b.toString());
    }
    
    public void rikiavimasIrSpausdinimas(LinkedList<Žaidėjas> atr){
        Collections.sort(atr, new Comparator<Žaidėjas>() {
            @Override
            public int compare(Žaidėjas o1, Žaidėjas o2){
                int pagalNi = o1.compareTo(o2);
                return pagalNi;
            }
        });
        spausdintiSąrašą(atr, "Atrinktų aukštų žaidėjų rikiuotas sąrašas");
    }
    
    public void kiekŽaidėjų(){
        HashMap<String, int[]> hashas = new HashMap<String, int[]>();
        for (Žaidėjas a : agentura)
        {
		int[] žaidėjųSkaičius = hashas.get(a.getKomanda());
                if (žaidėjųSkaičius == null) {
			žaidėjųSkaičius = new int[1];
			žaidėjųSkaičius[0] = 0;
		}
		žaidėjųSkaičius[0]++;
		hashas.put(a.getKomanda(), žaidėjųSkaičius);        
        }
        Set<String> Komandos = hashas.keySet();
	for (String komanda : Komandos) {
		int rez = hashas.get(komanda)[0];
		System.out.println(komanda +" turi pas save "+ rez + " žaid.");
	}
    }
    public void kiekKomandų(){
        HashMap<String, int[]> hashas = new HashMap<String, int[]>();
        for (Žaidėjas a : agentura)
        {
		int[] komandųSk = hashas.get(a.getKomanda());
                if (komandųSk == null) {
			komandųSk = new int[1];
			komandųSk[0] = 0;
		}
		komandųSk[0]++;
		hashas.put(a.getKomanda(), komandųSk);        
        }
        System.out.println("Iš viso yra " + hashas.size() +" komand.");
    }
    
    
}
