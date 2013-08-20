package LaboraiDemo;

import GUI.KsSwing.MyException;
import java.util.*;

public class AutoGamyba {

    private ResourceBundle rb = ResourceBundle.getBundle("GUI.MyResources");
    private Random ag = new Random();
    private Automobilis[] autoMasyvas;
    private String[] IdMasyvas;
    private int kiekis = 0;
    private int idKiekis = 0;
    private final String idCode = "TA";      //  ***** nauja
    private int serNr = 10000;               //  ***** nauja

    public Automobilis[] gamintiAutomobilius(int kiekis) {
        // AutoGamyba.serNr =
        //Atsitiktinių generatorius
        String[][] am = { //Galimų automobilių markių ir jų modelių masyvas
            {"Mazda", "121", "323", "626", "MX3", "MX6", "3", "6"},
            {"Ford", "Fiesta", "Escort", "Focus", "Sierra", "Mondeo"},
            {"Saab", "92", "96"},
            {"Honda", "Accord", "Civic", "Jazz", "Legend"},
            {"Renault", "Laguna", "Megane", "Twingo", "Scenic"},
            {"Peugeot", "206", "207", "307"},
            {"Wolkswagen", "Polo", "Golf", "Vento", "Passat", "Tuareg",
                "Transporter", "Touran", "Sharan"},
            {"Audi", "80", "100", "200", "A3", "A4", "A6", "A8", "Q1"}
        };
        autoMasyvas = new Automobilis[kiekis];
        IdMasyvas = new String[kiekis];
        ag.setSeed(1949);
        for (int i = 0; i < kiekis; i++) {
            int ma = ag.nextInt(am.length);        // markės indeksas  0..
            int mo = ag.nextInt(am[ma].length - 1) + 1;// modelio indeksas 1..
            autoMasyvas[i] = new Automobilis(am[ma][0], am[ma][mo],
                    1990 + ag.nextInt(20), // metai tarp 1990 ir 2009
                    6000 + ag.nextInt(222000), // rida tarp 6000 ir 228000
                    800 + ag.nextDouble() * 88000); // kaina tarp 800 ir 88800
            IdMasyvas[i] = idCode + (serNr++);
        }
        Collections.shuffle(Arrays.asList(autoMasyvas));
        Collections.shuffle(Arrays.asList(IdMasyvas));
        return autoMasyvas;
    }

    public Automobilis[] gamintiIrParduoti(int kiekis,
            int generApimtis) throws MyException {
        if (kiekis > generApimtis) {
            throw new MyException(rb.getStringArray("msgs")[3],100);
        }
        autoMasyvas = gamintiAutomobilius(generApimtis);
        this.kiekis = kiekis;
        return Arrays.copyOf(autoMasyvas, kiekis);
    }

    //Imame po viena elementą iš sugeneruoto masyvo. Kai elementai baigiasi sugeneruojame
    //nuosava situaciją ir išmetame pranesimą.
    public Automobilis parduotiIsSandelio() throws MyException {
        if (kiekis < autoMasyvas.length) {
            return autoMasyvas[kiekis++];
        } else {
            throw new MyException(rb.getStringArray("msgs")[4]);
        }
    }

    public String formuotiAutoID() throws MyException {
        if (idKiekis >= IdMasyvas.length) {
            idKiekis = 0;
        }
        return IdMasyvas[idKiekis++];
    }

    public Automobilis[] grazintiAutoMasyva() {
        return autoMasyvas;
    }

    public String[] grazintiAutoIDMasyva() {
        return IdMasyvas;
    }
}