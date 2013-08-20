package Laboras3demo;
import java.util.Comparator;
import studijosKTU.*;
/* tikriname tiek atskirų objektų metodus, tiek visą sąrašą
 * @author eimutis
 */
public class AutomobiliuTestai{
    static ListKTUx<Automobilis> bandomieji=new ListKTUx (new Automobilis());

    public static void metodoParinkimas(){
//        tikrintiAtskirusAuto();
//        formuotiAutoSąrašą();
//        peržiūrėtiSąrašą();
//        papildytiSąrašą();
//        patikrintiApskaitą();
        patikrintiRūšiavimą();
    }

    public static void tikrintiAtskirusAuto() {
        Automobilis a1 = new Automobilis("Renault","Laguna",1997,50000,1700);
        Automobilis a2 = new Automobilis("Renault","Megane",2001,20000,3500);
        Automobilis a3 = new Automobilis("Toyota","Corolla",2001,20000,8500.8);
        Automobilis a4 = new Automobilis("Renault Laguna 2001 115900 7500");
        Automobilis a5 = new Automobilis("Renault Megane 1946 365100 9500");
        Automobilis a6 = new Automobilis("Honda   Civic  2007  36400 8500.3");

        Ks.oun(a1);
        Ks.oun(a2);
        Ks.oun(a3);
        Ks.oun("Pirmų 3 auto ridos vidurkis= "+
                (a1.getRida()+a2.getRida()+a3.getRida())/3);
        Ks.oun(a4);
        Ks.oun(a5);
        Ks.oun(a6);
        Ks.oun("Kitų 3 auto kainų suma= "+
                (a4.getKaina()+a5.getKaina()+a6.getKaina()));
    }
    public static void formuotiAutoSąrašą() {
        Automobilis a1 = new Automobilis("Renault","Laguna",1997,50000,1700);
        Automobilis a2 = new Automobilis("Renault","Megane",2001,20000,3500);
        Automobilis a3 = new Automobilis("Toyota","Corolla",2001,20000,8500.8);
        bandomieji.add(a1);
        bandomieji.add(a2);
        bandomieji.add(a3);
        bandomieji.println("Pirmi 3 auto");
        bandomieji.println("Pirmi 3 auto");
        bandomieji.add("Renault Laguna 2001 115900 7500");
        bandomieji.add("Renault Megane 1946 365100 9500");
        bandomieji.add("Honda   Civic  2007  36400 8500.3");

        bandomieji.println("Visi 6 auto");
        Ks.oun("Pirmų 3 auto ridos vidurkis= "+
                (bandomieji.get(0).getRida()+bandomieji.get(1).getRida()+
                 bandomieji.get(2).getRida())/3);

        Ks.oun("Kitų 3 auto kainų suma= "+
                (bandomieji.get(3).getKaina()+bandomieji.get(4).getKaina()+
                 bandomieji.get(5).getKaina()));
//        bandomieji.add(0, new Automobilis("Mazda","6",2007,50000,27000));
//        bandomieji.add(6, new Automobilis("Hyundai","Lantra",1998,9500,777));
//        bandomieji.set(4, a3);
//        bandomieji.println("Po įterpimų");
//        bandomieji.remove(7);
//        bandomieji.remove(0);
//        bandomieji.println("Po išmetimų");
//        bandomieji.remove(0); bandomieji.remove(0); bandomieji.remove(0);
//        bandomieji.remove(0); bandomieji.remove(0); bandomieji.remove(0);
//        bandomieji.println("Po visų išmetimų");
//        bandomieji.remove(0);
//        bandomieji.println("Po visų išmetimų");
   }
    public static void peržiūrėtiSąrašą(){
        int sk=0;
        for (Automobilis a: bandomieji){
            if (a.getMarkė().compareTo("Renault")==0)
                sk++;
        }
        Ks.oun("Renault automobilių yra = "+sk);
    }
    public static void papildytiSąrašą(){
        for (int i=0; i<12; i++){
            bandomieji.add(new Automobilis("Ford", "Focus",
                    2009-i, 40000+i*10000, 36000-i*2000));
        }
        bandomieji.add("Ford Mondeo  2009 37000 36000.0");
        bandomieji.add("Fiat Bravo   2008 27000 36000.0");
        bandomieji.add("Ford Fiesta  2009 37000 16000.0");
        bandomieji.add("Audi A6      2006 87000 36000.0");
        bandomieji.println("Testuojamų automobilių sąrašas");
        bandomieji.save("ban.txt");
    }
    public static void patikrintiApskaitą(){
        AutoApskaita aps = new AutoApskaita();
        
        aps.visiAuto.load("ban.txt");
        aps.visiAuto.println("Bandomasis rinkinys");

        bandomieji = aps.atrinktiNaujusAuto(2001);
        bandomieji.println("Pradedant nuo 2001");

        bandomieji = aps.atrinktiPagalKainą(3000, 10000);
        bandomieji.println("Kaina tarp 3000 ir 10000");

        bandomieji = aps.maksimaliosKainosAuto();
        bandomieji.println("Patys brangiausi");

        bandomieji = aps.atrinktiMarkęModelį("F");
        bandomieji.println("Turi būti tik Fiatai ir Fordai");

        bandomieji = aps.atrinktiMarkęModelį("Ford M");

        bandomieji.println("Turi būti tik Ford Mondeo");
        int sk=0;
        for (Automobilis a: bandomieji){
                sk++;    // testuojame ciklo veikimą
        }
        Ks.oun("Ford Mondeo kiekis = "+sk);
    }
    public static void patikrintiRūšiavimą(){
        AutoApskaita aps = new AutoApskaita();

        aps.visiAuto.load("ban.txt");
        Ks.oun("========"+aps.visiAuto.get(0));
        aps.visiAuto.println("Bandomasis rinkinys");
        aps.visiAuto.sort(Automobilis.pagalMarkęModelį);
        aps.visiAuto.println("Rūšiavimas pagal Markę ir Modelį");
        aps.visiAuto.sort(Automobilis.pagalKainą);
        aps.visiAuto.println("Rūšiavimas pagal kainą");
        aps.visiAuto.sort(Automobilis.pagalMetusKainą);
        aps.visiAuto.println("Rūšiavimas pagal Metus ir Kainą");
        aps.visiAuto.sort(tvarkaPagalRidą);
        aps.visiAuto.println("Rūšiavimas pagal Ridą");
        aps.visiAuto.sort();
        aps.visiAuto.println("Rūšiavimas pagal compareTo - Kainą");
    }

    static Comparator tvarkaPagalRidą = new Comparator() {
       public int compare(Object o1, Object o2) {
          int r1 = ((Automobilis) o1).getRida();
          int r2 = ((Automobilis) o2).getRida();
          // rida atvirkščia mažėjančia tvarka, pradedant nuo didžiausios
          if(r1<r2) return 1;
          if(r1>r2) return -1;
          return 0;
       }
    };

}
