/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab6Petkus;

import java.util.*;
import lab6_demo.Automobilis;
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
    static Random ag = new Random();  // atsitiktinių generatorius
    static int[] MeasureAmounts = {100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600};
    static ListKTU<Book> PradinesKnygos = new ListKTU<Book>();


    public static void ResearchChoices(){
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= "+memTotal);
        pagrindinisTestas();
        paemimoTestas();
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
        PradinesKnygos.clear();
        for(Book a: BookBase1) 
        {
            PradinesKnygos.add(a);
        }
    }
    public static void SimpleTest(int n){
// Paruošiamoji tyrimo dalis
        
        /*long t0=System.nanoTime();
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
                (t4-t3)/1e9, (t5-t4)/1e9, (t6-t5)/1e9);*/
    }
    public static void pagrindinisTestas() {
// Paruošiamoji tyrimo dalis
        Timekeeper tk = new Timekeeper(MeasureAmounts);
        PradinesKnygos.clear();
        for (int kiekis : MeasureAmounts) {
           GenerateBooks(kiekis);
           tk.start();
           ListKTU<Book>ListKTUKnygos = (ListKTU<Book>)PradinesKnygos.clone();
           tk.finish("ListKTU");
           LinkedList<Book>LinkedListKnygos = new LinkedList<Book>();
           for (Book a :PradinesKnygos){
               LinkedListKnygos.add(a);
           }
           tk.finish("LinkedList");
           Map<String,Book>TreeMapKnygos = new TreeMap<String,Book>();
           int i = -1;
           for (Book a :PradinesKnygos){
               i++;
               TreeMapKnygos.put(String.valueOf(i), a);
           }
           tk.finish("TreeMap");
           Map<String,Book>HashMapKnygos = new HashMap<String, Book>();
           i = -1;
           for (Book a :PradinesKnygos){
               i++;
               HashMapKnygos.put(String.valueOf(i), a);
           }
           tk.finish("HashMap");
           tk.seriesFinish();
           //---------------------------------------------
        }
    }
    public static void paemimoTestas() {
// Paruošiamoji tyrimo dalis
        Timekeeper tk = new Timekeeper(MeasureAmounts);
        for (int kiekis : MeasureAmounts) {
           GenerateBooks(kiekis);
           ListKTU<Book>ListKTUKnygos = (ListKTU<Book>)PradinesKnygos.clone();
           LinkedList<Book>LinkedListKnygos = new LinkedList<Book>();
           for (Book a :PradinesKnygos){
               LinkedListKnygos.add(a);
           }
           Map<String,Book>TreeMapKnygos = new TreeMap<String,Book>();
           int i = -1;
           for (Book a :PradinesKnygos){
               i++;
               TreeMapKnygos.put(String.valueOf(i), a);
           }
           Map<String,Book>HashMapKnygos = new HashMap<String, Book>();
           i = -1;
           for (Book a :PradinesKnygos){
               i++;
               HashMapKnygos.put(String.valueOf(i), a);
           }
           //---------------------------------------------
           tk.start();
           Book Knyga = new Book();
           for (int j = 0; j < kiekis; j++){
               Knyga = ListKTUKnygos.get(j);
           }
           tk.finish("ListKTU");
           for (int j = 0; j < kiekis; j++){
               Knyga = LinkedListKnygos.get(j);
           }
           tk.finish("LinkedList");
           for (int j = 0; j < kiekis; j++){
               Knyga = TreeMapKnygos.get(String.valueOf(j));
           }
           tk.finish("TreeMap");
           for (int j = 0; j < kiekis; j++){
               Knyga = HashMapKnygos.get(String.valueOf(j));
           }
           tk.finish("HashMap");
           tk.seriesFinish();
        }
    }
    public static void Gynimas()
    {
        /*Timekeeper tk = new Timekeeper(MeasureAmounts);
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
        }*/
    }
}
