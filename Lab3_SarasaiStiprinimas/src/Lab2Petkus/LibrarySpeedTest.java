/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Petkus;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import studijosKTU.Ks;
import studijosKTU.ListKTU;
import studijosKTU.ListKTUx;
import studijosKTU.Timekeeper;
/**
 *
 * @author Tautvydas
 */
public class LibrarySpeedTest {
    
    static Book[] BookBase1;
    static ListKTU<Book> aSeries = new ListKTU<Book>();
    static Random ag = new Random();  // atsitiktinių generatorius
    static int[] MeasureAmounts = {100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600};
 // static int[] MeasureAmounts = {100000, 200000, 400000, 800000, 1600000};
    static LinkedList<Book>KnygosBeComp = new LinkedList<Book>();
    static ListKTUx<Book> KnygosSuComp=new ListKTUx (new Book());

    public static void ResearchChoices(){
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= "+memTotal);
        // Pasižiūrime kaip generuoja automobilius (20) vienetų)
        //GenerateBooks(20);
        //for(Book a: aSeries) {Ks.oun(a);}
        //Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        //Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        //Ks.oun("3 - Rūšiavimas sisteminiu greitu būdu be Comparator");
        //Ks.oun("4 - Rūšiavimas sisteminiu greitu būdu su Comparator");
        //Ks.oun("5 - Rūšiavimas List burbuliuku su Comparator");
        //Ks.oun("6 - Rūšiavimas List burbuliuku be Comparator");
        //Ks.ouf("%6d %7d %7d %7d %7d %7d %7d\n", 0,1,2,3,4,5,6);
        //for(int n:MeasureAmounts) {SimpleTest(n);}
        //SystemicTest();
        Gynimas();
    }
    public static void GenerateBooks(int n){
       String[][] am = { 
          {"Viena Diena", "Jonas ", "Petras", "Algis", "Simas"},
          {"Kelias Namo", "Ignas", "Lukas", "Rolandas", "Justinas", "Petras"},
          {"Eilerasciai", "Marija", "Inga"},
          {"Poezija", "Ieva", "Monika", "Gerda"},
          {"Filosofija visiems", "Beatričė", "Agnė", "Akvilė", "Kristina"},
          {"Enciklopedija", "Anonimas1", "Anonimas2", "Anonimas3"}
       };
        BookBase1= new Book[n];
        ag.setSeed(1949);
        for(int i=0;i<n;i++){
            int ma = ag.nextInt(am.length);        // knygos pavadinimas  0..
            int mo = ag.nextInt(am[ma].length-1)+1;// Vartotojo vardas 1..
            int Y = 1990 + ag.nextInt(200);
            int M = 1 + ag.nextInt(11);
            int D = 1 + ag.nextInt(29);
            String Date = Y + "-" + M + "-" + D;
            BookBase1[i]= new Book(1900+ag.nextInt(200), am[ma][0], 
                "CustomAuthor", Date, am[ma][mo],
                7+ag.nextInt(358));
        }
        Collections.shuffle(Arrays.asList(BookBase1));
        aSeries.clear();
        for(Book a: BookBase1) 
        {
            aSeries.add(a);
        }
    }
    public static void SimpleTest(int n){
// Paruošiamoji tyrimo dalis
        
        long t0=System.nanoTime();
        GenerateBooks(n);
        
        ListKTU<Book> aSeries2=(ListKTU<Book>)aSeries.clone();
        ListKTU<Book> aSeries3=(ListKTU<Book>)aSeries.clone();
        ListKTU<Book> aSeries4=(ListKTU<Book>)aSeries.clone();
        long t1=System.nanoTime();
        System.gc(); System.gc(); System.gc();
        long t2=System.nanoTime();
//  Greitaveikos bandymai ir laiko matavimai
        aSeries.sort();
        
        long t3=System.nanoTime();
        aSeries2.sort(Book.byYear);
        long t4=System.nanoTime();
        
        aSeries4.sortBurbuliuku(Book.byYear);
        long t5=System.nanoTime();
        aSeries3.sortBurbuliuku();
        long t6=System.nanoTime();
        
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f\n", n,
                (t1-t0)/1e9, (t2-t1)/1e9, (t3-t2)/1e9,
                (t4-t3)/1e9, (t5-t4)/1e9, (t6-t5)/1e9);
    }
    public static void SystemicTest() {
// Paruošiamoji tyrimo dalis
        Timekeeper tk = new Timekeeper(MeasureAmounts);
        for (int kiekis : MeasureAmounts) {
           GenerateBooks(kiekis);
           ListKTU<Book> aSeries2=(ListKTU<Book>)aSeries.clone();
           ListKTU<Book> aSeries3=(ListKTU<Book>)aSeries.clone();
           ListKTU<Book> aSeries4=(ListKTU<Book>)aSeries.clone();

//  Greitaveikos bandymai ir laiko matavimai
            tk.start();
            aSeries.sort();
            tk.finish("SysBeCom");
            aSeries2.sort(Book.byYear);
            tk.finish("SysSuCom");
            aSeries3.sortBurbuliuku();
            tk.finish("BurBeCom");
            aSeries4.sortBurbuliuku(Book.byYear);
            tk.finish("BurSuCom");
            tk.seriesFinish();
        }
    }
    public static void Gynimas()
    {
        Timekeeper tk = new Timekeeper(MeasureAmounts);
        for (int kiekis : MeasureAmounts) {
            GenerateBooks(kiekis);
            ListKTU<Book> aSeries2=(ListKTU<Book>)aSeries.clone();
            ListKTU<Book> aSeries3=(ListKTU<Book>)aSeries.clone();
            tk.start();
            aSeries3.sort();
            tk.finish("Be Comp");
            aSeries2.sort(Book.byTimePeriodandPickDate);
            tk.finish("Su Comp");
            tk.seriesFinish();
        }
    }
}
