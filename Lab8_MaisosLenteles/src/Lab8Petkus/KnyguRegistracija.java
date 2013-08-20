package Lab8Petkus;

import LaboraiDemo.*;
import GUI.KsSwing.MyException;
import java.util.*;

public class KnyguRegistracija {

    private ResourceBundle rb = ResourceBundle.getBundle("GUI.MyResources");
    private Random RandomNumber = new Random();
    private BookClass[] BookBase;
    private String[] IdMasyvas;
    private int kiekis = 0;
    private int idKiekis = 0;
    private final String idCode = "IF";      //  ***** nauja
    private int serNr = 10000;               //  ***** nauja

    public BookClass[] generuotiKnygas(int kiekis) {
        // KnyguRegistracija.serNr =
        //Atsitiktinių generatorius
        String[][] am = { 
          {"Diena", "M.K.Čiurlionis", "Jonas ", "Petras", "Algis", "Simas"},
          {"Kelias", "K.Binkis", "Ignas", "Lukas", "Rolandas", "Justinas", "Petras"},
          {"Eilerasciai", "A.Valatka", "Marija", "Inga"},
          {"Poezija", "S.Girėnas", "Ieva", "Monika", "Gerda"},
          {"Filosofija", "A.Stulginskis", "Beatričė", "Agnė", "Akvilė", "Kristina"},
          {"Enciklopedija", "T.Ignatavičius", "Anonimas1", "Anonimas2", "Anonimas3"}
       };
        BookBase = new BookClass[kiekis];
        IdMasyvas = new String[kiekis];
        RandomNumber.setSeed(1949);
        for(int i=0;i<kiekis;i++){
            int ma = RandomNumber.nextInt(am.length);      
            int mo = RandomNumber.nextInt(am[ma].length-2)+2;
            int Y = 2000 + RandomNumber.nextInt(20);
            int M = 1 + RandomNumber.nextInt(11);
            int D = 1 + RandomNumber.nextInt(29);
            String Date = Y + "-" + M + "-" + D;
            BookBase[i]= new BookClass(1990+RandomNumber.nextInt(20), am[ma][0], 
                am[ma][1], Date, am[ma][mo],
                7+RandomNumber.nextInt(358));
            IdMasyvas[i] = idCode + (serNr++);
        }
        Collections.shuffle(Arrays.asList(BookBase));
        Collections.shuffle(Arrays.asList(IdMasyvas));
        return BookBase;
    }

    public BookClass[] generavimasIrGrazinimas(int kiekis,
            int generApimtis) throws MyException {
        if (kiekis > generApimtis) {
            throw new MyException(rb.getStringArray("msgs")[3],100);
        }
        BookBase = generuotiKnygas(generApimtis);
        this.kiekis = kiekis;
        return Arrays.copyOf(BookBase, kiekis);
    }

    //Imame po viena elementą iš sugeneruoto masyvo. Kai elementai baigiasi sugeneruojame
    //nuosava situaciją ir išmetame pranesimą.
    public BookClass imtiIsBazes() throws MyException {
        if (kiekis < BookBase.length) {
            return BookBase[kiekis++];
        } else {
            throw new MyException(rb.getStringArray("msgs")[4]);
        }
    }

    public String formuotiKnygosID() throws MyException {
        if (idKiekis >= IdMasyvas.length) {
            idKiekis = 0;
        }
        return IdMasyvas[idKiekis++];
    }

    public BookClass[] grazintiKnyguMasyva() {
        return BookBase;
    }

    public String[] grazintiKnyguIDMasyva() {
        return IdMasyvas;
    }
}