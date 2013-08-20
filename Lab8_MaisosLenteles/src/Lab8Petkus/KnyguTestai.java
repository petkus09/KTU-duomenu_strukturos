package Lab8Petkus;

import Lab8Petkus.*;
import GUI.KsSwing.MyException;
import java.util.*;
import studijosKTU.*;
import studijosKTU.MapKTU.HashType;

public class KnyguTestai {

    public static void main(String[] args) throws MyException {
        Locale.setDefault(Locale.US); // suvienodiname skaičių formatus
        atvaizdzioTestas();
    }

    public static void atvaizdzioTestas() throws MyException {
        BookClass a1 = new BookClass(2000, "Vinetu", "S. King", "2000-01-01", "Jonas", 100);
        BookClass a2 = new BookClass(1993, "Poliana", "M.Tyson", "2003-02-02", "Petras", 3);
        BookClass a3 = new BookClass(1994, "Pabegimas", "U.Boll", "2002-03-03", "Kestas", 1000);
        BookClass a4 = new BookClass(1995, "Kunigas", "S.Hawkin", "2008-04-04", "Justinas", 9);
        BookClass a5 = new BookClass(2010, "Aivenhas", "M.K.Ciurlionis", "2004-05-05", "Eimantas", 38);
        BookClass a6 = new BookClass(2001, "Tiku Taku", "M.K.Ciurlionis", "2012-12-12", "Popas", 10);
        BookClass a7 = new BookClass(2003, "Niekados", "S. Ahahahaha", "2007-12-31", "Popas", 67);

        //Raktų masyvas
        String[] autoId = {"IF001", "IF002", "IF003", "IF004", "IF005", "IF006", "IF007", "IF008"};
        int id = 0;
        MapKTUx<String, BookClass> atvaizdis =
                new MapKTUx(new String(), new BookClass(), HashType.Division);
        //Reikšmių masyvas
        BookClass[] Knyga = {a1, a2, a3, a4, a5, a6, a7};
        for (BookClass a : Knyga) {
            atvaizdis.put(autoId[id++], a);
        }
        atvaizdis.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Ar egzistuoja pora atvaizdyje?");
        Ks.oun(atvaizdis.contains(autoId[6]));
        Ks.oun(atvaizdis.contains(autoId[7]));
        Ks.oun("Pašalinamos poros iš atvaizdžio:");
        Ks.oun(atvaizdis.remove(autoId[1]));
        Ks.oun(atvaizdis.remove(autoId[7]));
        atvaizdis.println("Porų išsidėstymas atvaizdyje pagal raktus");
        Ks.oun("Atliekame porų paiešką atvaizdyje:");
        Ks.oun(atvaizdis.get(autoId[2]));
        Ks.oun(atvaizdis.get(autoId[7]));
        Ks.oun("Išspausdiname atvaizdžio poras String eilute:");
        Ks.ounn(atvaizdis);
    }
}