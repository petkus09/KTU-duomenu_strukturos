package Laboras3demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import studijosKTU.*;
/*
 * Tyrimo metu pateiktais metodais naudojamasi kaip šablonais
 * išbandant įvairius rūšiavimo aspektus
 */
public class GreitaveikosTyrimas {
    static Automobilis[] autoBazė1;
    static ListKTU<Automobilis> aSeries = new ListKTU<Automobilis>();
    static Random ag = new Random();  // atsitiktinių generatorius
    static int[] tiriamiKiekiai = {1000, 2000, 4000, 8000};//, 16000};
//    static int[] tiriamiKiekiai = {100000, 200000, 400000, 800000, 1600000};

    public static void tyrimoPasirinkimas(){
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= "+memTotal);
        // Pasižiūrime kaip generuoja automobilius (20) vienetų)
        generuotiAutomobilius(20);
        for(Automobilis a: aSeries) Ks.oun(a);
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - Rūšiavimas sisteminiu greitu būdu be Comparator");
        Ks.oun("4 - Rūšiavimas sisteminiu greitu būdu su Comparator");
        Ks.oun("5 - Rūšiavimas List burbuliuku be Comparator");
        Ks.oun("6 - Rūšiavimas List burbuliuku su Comparator");
        Ks.ouf("%6d %7d %7d %7d %7d %7d %7d \n", 0,1,2,3,4,5,6);
        for(int n:tiriamiKiekiai) paprastasTyrimas(n);
        sisteminisTyrimas();
    }
    public static void generuotiAutomobilius(int kiekis){
       String[][] am = { // galimų automobilių markių ir jų modelių masyvas
          {"Mazda", "121", "323", "626", "MX6"},
          {"Ford", "Fiesta", "Escort", "Focus", "Sierra", "Mondeo"},
          {"Saab", "92", "96"},
          {"Honda", "Accord", "Civic", "Jazz"},
          {"Renault", "Laguna", "Megane", "Twingo", "Scenic"},
          {"Peugeot", "206", "207", "307"}
       };
        autoBazė1= new Automobilis[kiekis];
        ag.setSeed(1949);
        for(int i=0;i<kiekis;i++){
            int ma = ag.nextInt(am.length);        // markės indeksas  0..
            int mo = ag.nextInt(am[ma].length-1)+1;// modelio indeksas 1..
            autoBazė1[i]= new Automobilis(am[ma][0], am[ma][mo],
                1990+ag.nextInt(20),     // metai tarp 1990 ir 2009
                6000+ag.nextInt(222000), // rida tarp 6000 ir 228000
                800+ ag.nextDouble()*88000); // kaina tarp 800 ir 88800
        }
        Collections.shuffle(Arrays.asList(autoBazė1));
        aSeries.clear();
        for(Automobilis a: autoBazė1) aSeries.add(a);
    }
    public static void paprastasTyrimas(int elementųKiekis){
// Paruošiamoji tyrimo dalis
        long t0=System.nanoTime();
        generuotiAutomobilius(elementųKiekis);
        ListKTU<Automobilis> aSeries2=(ListKTU<Automobilis>)aSeries.clone();
        ListKTU<Automobilis> aSeries3=(ListKTU<Automobilis>)aSeries.clone();
        ListKTU<Automobilis> aSeries4=(ListKTU<Automobilis>)aSeries.clone();
        long t1=System.nanoTime();
        System.gc(); System.gc(); System.gc();
        long t2=System.nanoTime();
//  Greitaveikos bandymai ir laiko matavimai
        aSeries.sort();
        long t3=System.nanoTime();
        aSeries2.sort(Automobilis.pagalKainą);
        long t4=System.nanoTime();
        aSeries3.sortBurbuliuku();
        long t5=System.nanoTime();
        aSeries4.sortBurbuliuku(Automobilis.pagalKainą);
        long t6=System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f \n", elementųKiekis,
                (t1-t0)/1e9, (t2-t1)/1e9, (t3-t2)/1e9,
                (t4-t3)/1e9, (t5-t4)/1e9, (t6-t5)/1e9 );
    }
    public static void sisteminisTyrimas() {
// Paruošiamoji tyrimo dalis
        Timekeeper tk = new Timekeeper(tiriamiKiekiai);
        for (int kiekis : tiriamiKiekiai) {
           generuotiAutomobilius(kiekis);
           ListKTU<Automobilis> aSeries2=(ListKTU<Automobilis>)aSeries.clone();
           ListKTU<Automobilis> aSeries3=(ListKTU<Automobilis>)aSeries.clone();
           ListKTU<Automobilis> aSeries4=(ListKTU<Automobilis>)aSeries.clone();

//  Greitaveikos bandymai ir laiko matavimai
            tk.start();
            aSeries.sort();
            tk.finish("SysBeCom");
            aSeries2.sort(Automobilis.pagalKainą);
            tk.finish("SysSuCom");
            aSeries3.sortBurbuliuku();
            tk.finish("BurBeCom");
            aSeries4.sortBurbuliuku(Automobilis.pagalKainą);
            tk.finish("BurSuCom");
            tk.seriesFinish();
        }
    }
}
