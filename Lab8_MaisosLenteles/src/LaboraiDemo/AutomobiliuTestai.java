package LaboraiDemo;

import GUI.KsSwing.MyException;
import java.util.*;
import studijosKTU.*;
import studijosKTU.MapKTU.HashType;

public class AutomobiliuTestai {

    public static void main(String[] args) throws MyException {
        Locale.setDefault(Locale.US); // suvienodiname skaičių formatus
        atvaizdzioTestas();
    }

    public static void atvaizdzioTestas() throws MyException {
        Automobilis a1 = new Automobilis("Renault", "Laguna", 1997, 50000, 1700);
        Automobilis a2 = new Automobilis("Renault", "Megane", 2001, 20000, 3500);
        Automobilis a3 = new Automobilis("Toyota", "Corolla", 2001, 20000, 8500.8);
        Automobilis a4 = new Automobilis("Renault Laguna 2001 115900 7500");
        Automobilis a5 = new Automobilis("Renault Megane 1946 365100 9500");
        Automobilis a6 = new Automobilis("Honda   Civic  2007  36400 8500.3");
        Automobilis a7 = new Automobilis("Renault Laguna 2001 115900 7500");

        //Raktų masyvas
        String[] autoId = {"TA101", "TA102", "TA103", "TA104", "TA105", "TA106", "TA107", "TA108"};
        int id = 0;
        MapKTUx<String, Automobilis> atvaizdis =
                new MapKTUx(new String(), new Automobilis(), HashType.Division);
        //Reikšmių masyvas
        Automobilis[] auto = {a1, a2, a3, a4, a5, a6, a7};
        for (Automobilis a : auto) {
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