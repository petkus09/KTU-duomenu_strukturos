package Lab7Petkus;

import Lab7Petkus.*;
import GUI.KsSwing.MyException;
import java.util.*;

public class KnyguRegistratura {

    private static Random RandomNumber = new Random();
    private static BookClass[] BookBase;
    private static int pradinisBaze = 0, galinisBaze = 0;
    private static boolean pradzia = true;

    public static BookClass[] generuotiKnyguMasyva(int kiekis) {
         String[][] am = { 
          {"Viena Diena", "M.K.Čiurlionis", "Jonas ", "Petras", "Algis", "Simas"},
          {"Kelias Namo", "K.Binkis", "Ignas", "Lukas", "Rolandas", "Justinas", "Petras"},
          {"Eilerasciai", "A.Valatka", "Marija", "Inga"},
          {"Poezija", "S.Girėnas", "Ieva", "Monika", "Gerda"},
          {"Filosofija visiems", "A.Stulginskis", "Beatričė", "Agnė", "Akvilė", "Kristina"},
          {"Enciklopedija", "T.Ignatavičius", "Anonimas1", "Anonimas2", "Anonimas3"}
       };
        BookBase = new BookClass[kiekis];
        RandomNumber.setSeed(1949);
        for(int i=0;i<kiekis;i++){
            int ma = RandomNumber.nextInt(am.length);      
            int mo = RandomNumber.nextInt(am[ma].length-2)+2;
            int Y = 1990 + RandomNumber.nextInt(30);
            int M = 1 + RandomNumber.nextInt(11);
            int D = 1 + RandomNumber.nextInt(29);
            String Date = Y + "-" + M + "-" + D;
            BookBase[i]= new BookClass(1900+RandomNumber.nextInt(110), am[ma][0], 
                am[ma][1], Date, am[ma][mo],
                7+RandomNumber.nextInt(358));
        }
        return BookBase;
    }

    public static BookClass[] generuotiIrIsmaisyti(int aibe, int aibesImtis,
            double isbarstKoef) throws MyException {
        BookBase = generuotiKnyguMasyva(aibe);
        return ismaisyti(BookBase, aibesImtis, isbarstKoef);
    }

    //Galima paduoti masyvą išmaišymui iš išorės
    public static BookClass[] ismaisyti(BookClass[] BookBase,
            int kiekis, double maisymoDalis) throws MyException {
        if (BookBase == null) {
            throw new NullPointerException("Null pointeris ismaisyti() metode");
        }
        if (kiekis <= 0) {
            throw new MyException(kiekis + "", 0);
        }
        if (BookBase.length < kiekis * 3) {
            throw new MyException(BookBase.length + " > " + kiekis + "*3", 3);
        }
        if ((maisymoDalis < 0) || (maisymoDalis > 1)) {
            throw new MyException(maisymoDalis + "", 2);
        }
        BookClass temp;
        //Kad medis augtų ne į vieną pusę, pradinė generuojama aibė paimama
        //ne iš masyvo pradžios, o iš atsitiktinės vietos, ir perrašoma į 
        //pradžią sukeičiant elementus
        int index = 0;
        if (BookBase.length - (kiekis * 2) > 0) {
            index = RandomNumber.nextInt(BookBase.length - (kiekis * 2));
        }
        index += kiekis;
        for (int i = 0; i < kiekis; i++) {
            temp = BookBase[i];
            BookBase[i] = BookBase[index];
            BookBase[index] = temp;
            index++;
        }
        //Likusi masyvo dalis surūšiuojama
        Arrays.sort(BookBase, kiekis, BookBase.length);
        //Išmaišoma spausdinama aibės imtis
        int j1 = (int) (kiekis * maisymoDalis / 2.0) + 1;
        int j2 = (kiekis - j1) < 0 ? 0 : kiekis - j1;
        if (j1 > 1) {
            Collections.shuffle(Arrays.asList(BookBase).subList(0, j1));
        }
        if (j2 < (kiekis - 1)) {
            Collections.shuffle(Arrays.asList(BookBase).subList(j2, kiekis));
        }
        //Išmaišoma likusi aibės imtis
        j1 = (int) ((BookBase.length + kiekis) * maisymoDalis / 2.0) + 1;
        j2 = (BookBase.length + kiekis - j1) < 0 ? 0 : BookBase.length + kiekis - j1;
        if (j1 > kiekis + 1) {
            Collections.shuffle(Arrays.asList(BookBase).subList(kiekis + 1, j1));
        }
        if (j2 < (BookBase.length - 1)) {
            Collections.shuffle(Arrays.asList(BookBase).subList(j2, BookBase.length));
        }
        pradinisBaze = kiekis;
        galinisBaze = BookBase.length - 1;
        KnyguRegistratura.BookBase = BookBase;
        return Arrays.copyOf(BookBase, kiekis);
    }

    public static BookClass imtiIsBazes() throws MyException {
        if ((galinisBaze - pradinisBaze) < 0) {
            throw new MyException(galinisBaze - pradinisBaze + "", 4);
        }
        if (pradzia) {
            pradzia = false;
            return BookBase[pradinisBaze++];
        } else {
            pradzia = true;
            return BookBase[galinisBaze--];
        }
    }
}
