package Lab7Petkus;

import Lab7Petkus.*;
import java.util.*;
import studijosKTU.*;

/*
 * Aibės testavimas be Swing'o
 *
 */
public class KnyguTestai {

    static BookClass[] BookBase;
    
    static SortedSetADTx<BookClass> KnyguSerija = new BstSetKTUx(new BookClass(), BookClass.byYear);
    static Random RandomNumber = new Random();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // suvienodiname skaičių formatus
        KnyguAibesTestas();
    }

    static SortedSetADTx generuotiAibe(int kiekis) {
        String[][] am = { 
          {"Viena Diena", "Jonas ", "Petras", "Algis", "Simas"},
          {"Kelias Namo", "Ignas", "Lukas", "Rolandas", "Justinas", "Petras"},
          {"Eilerasciai", "Marija", "Inga"},
          {"Poezija", "Ieva", "Monika", "Gerda"},
          {"Filosofija visiems", "Beatričė", "Agnė", "Akvilė", "Kristina"},
          {"Enciklopedija", "Anonimas1", "Anonimas2", "Anonimas3"}
       };
        BookBase = new BookClass[kiekis];
        RandomNumber.setSeed(1949);
        for(int i=0;i<kiekis;i++){
            int ma = RandomNumber.nextInt(am.length);        
            int mo = RandomNumber.nextInt(am[ma].length-1)+1;
            int Y = 1990 + RandomNumber.nextInt(200);
            int M = 1 + RandomNumber.nextInt(11);
            int D = 1 + RandomNumber.nextInt(29);
            String Date = Y + "-" + M + "-" + D;
            BookBase[i]= new BookClass(1900+RandomNumber.nextInt(200), am[ma][0], 
                "CustomAuthor", Date, am[ma][mo],
                7+RandomNumber.nextInt(358));
        }
        Collections.shuffle(Arrays.asList(BookBase));
        KnyguSerija.clear();
        for(BookClass a: BookBase) 
        {
            KnyguSerija.add(a);
        }
        return KnyguSerija;
    }

    public static void KnyguAibesTestas() {
        BookClass a1 = new BookClass(1993, "Poliana", "M.Tyson", "2003-02-02", "Petras", 3);
        BookClass a2 = new BookClass(2011, "Aivenhas", "M.K.Ciurlionis", "2004-05-05", "Eimantas",38);
        BookClass a3 = new BookClass(1999, "Dienorastis", "T.Verdy", "2007-08-08", "Lukas", 48);
        BookClass a4 = new BookClass(1988, "Mazylis", "L.Duane", "2000-01-15", "Vilius", 31);
        BookClass a5 = new BookClass(2011, "Raganius", "M.K.Ciurlionis", "2004-08-11", "Eimantas", 39);
        BookClass a6 = new BookClass(1993, "Boruze", "A.Altlmer", "2004-02-22", "Ignas", 34);

        BookClass[] BookMasyvas = {a6, a5, a3, a1, a2, a4};

        Ks.oun("Knygu Aibė:");
        SortedSetADTx<BookClass> KnyguAibe = new BstSetKTUx(new BookClass());
        for (BookClass a : BookMasyvas) {
            KnyguAibe.add(a);
        }
        Ks.oun(KnyguAibe.toVisualizedString("Apskritimas", ""));

        SortedSetADTx<BookClass> KnyguAibe4 =
                (SortedSetADTx<BookClass>) KnyguAibe.clone();

        KnyguAibe4.add(a2);
        KnyguAibe4.add(a3);
        KnyguAibe4.add(a4);
        Ks.oun("Papildyta Knygu aibės kopija:");
        Ks.oun(KnyguAibe4.toVisualizedString("Apskritimas", ""));

        Ks.oun("Originalas:");
        Ks.ounn(KnyguAibe.toVisualizedString("Kvadratas", ""));

        Ks.oun("Knygu aibė su iteratoriumi:");
        Ks.oun("");
        for (BookClass a : KnyguAibe) {
            Ks.oun(a);
        }
        Ks.oun("");
        Ks.oun("Knygu aibė AVL-medyje:");
        SortedSetADTx<BookClass> KnyguAibe2 = new AvlSetKTUx(new BookClass());
        for (BookClass a : BookMasyvas) {
            KnyguAibe2.add(a);
        }
        Ks.ounn(KnyguAibe2.toVisualizedString("Kvadratas", ""));

        Ks.oun("Knygu aibė su iteratoriumi:");
        Ks.oun("");
        for (BookClass a : KnyguAibe2) {
            Ks.oun(a);
        }

        Ks.oun("");
        Ks.oun("Knygu aibė su atvirkštiniu iteratoriumi:");
        Ks.oun("");
        Iterator iter = KnyguAibe2.descendingIterator();
        while (iter.hasNext()) {
            Ks.oun(iter.next());
        }

        //Išvalome ir suformuojame aibes skaitydami iš failo
        KnyguAibe.clear();
        KnyguAibe2.clear();

        Ks.oun("");
        Ks.oun("Knygu aibė DP-medyje:");
        KnyguAibe.load("Books.txt");
        Ks.ounn(KnyguAibe.toVisualizedString("Apskritimas", ""));

        Ks.oun("");
        Ks.oun("Knygu aibė AVL-medyje:");
        KnyguAibe2.load("Books.txt");
        Ks.ounn(KnyguAibe2.toVisualizedString("Apskritimas", ""));

        SetADT<String> KnyguAibe3 = new BstSetKTU<String>();
        KnyguAibe3 = KnyguApskaita.KnyguMetai(BookMasyvas);
        Ks.oun("Pasikartojantys Knygu metai:\n" + KnyguAibe3.toString());
    }
}
