/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package k2_petkus;

/**
 *
 * @author Tautvydas
 */
public class K2_Petkus {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        testas();
    }
    public static void testas(){
        NBAbirža birža = new NBAbirža();
        birža.detiŽaidėją(new Žaidėjas("Lakers", "O'Niele", 195, 80.0));
        birža.detiŽaidėją(new Žaidėjas("Flippers", "Fliperis", 074, 70.0));
        birža.detiŽaidėją(new Žaidėjas("Portleand", "Sabonis", 158, 60.8));
        birža.detiŽaidėją(new Žaidėjas("Raptors", "Kleiza", 195, 50.5));
        birža.detiŽaidėją(new Žaidėjas("Heat", "Tony", 188, 90.0));
        birža.detiŽaidėją(new Žaidėjas("Warriors", "Rouge", 140, 100.4));
        birža.detiŽaidėją(new Žaidėjas("Golden State", "Big Smoke", 170, 40.6));
        birža.detiŽaidėją(new Žaidėjas("Flippers", "Timmy", 210, 37.2));
        birža.detiŽaidėją(new Žaidėjas("Portleand", "Billy", 200, 79.7));
        birža.detiŽaidėją(new Žaidėjas("Immortals", "Tadas", 177, 83.8));
        birža.detiŽaidėją(new Žaidėjas("Portleand", "Vasiliauskas", 191, 49.9));
        birža.detiŽaidėją(new Žaidėjas("Lakers", "Jonukas", 188, 55.5));
        birža.spausdintiSąrašą(birža.agentura, "Pradinis sąrašas");
        System.out.println("-----------------------------");
        birža.rikiavimasIrSpausdinimas(birža.rastiAukštusŽaidėjus(190));
        birža.kiekŽaidėjų();
        birža.kiekKomandų();
    }
}
