package Laboras3demo;
import Lab2Petkus.LibraryMethods;
import java.util.Locale;
import studijosKTU.*;

/*
 * Darbo Atlikimo tvarka - čia yra pradinė klasė:
 */

public class ValdymoModulis {
    static AutoApskaita ap = new AutoApskaita();

   public static void main(String[] args) {
      Locale.setDefault(Locale.US); // suvienodiname skaičių formatus

//      DemoKTUsystem.metodoParinkimas();
//      AutomobiliuTestai.metodoParinkimas();
//      bendravimasSuKlientu();
//      GreitaveikosTyrimas.tyrimoPasirinkimas();
        LibraryMethods.MethodOptions();
   }

   public static void bendravimasSuKlientu() {
      ListKTUx<Automobilis> atranka = null;
//              new ListKTUx<Automobilis>(new Automobilis());
      int varNr;  // skaičiavimo varijantas pasirenkamas nurodant jo numerį
      String dialogas = "Pasirinkimas: "
            + "1-skaityti iš failo; 2-papildyti sąrašą; "
            + "3-naujų atranka;\n    4-atranka pagal kainą; "
            + "5-brangiausi auto; 6-pagal markę; 0-baigti skaičiavimus > ";
      while ((varNr = Ks.giveInt(dialogas,0,6)) != 0) {
         if (varNr == 1) {
            ap.visiAuto.load(Ks.giveFileName());
            ap.visiAuto.println("Visų automobilių sąrašas");
         } else {
            if (varNr == 2) {
               String autoD = Ks.giveString("Nurodykite auto markę, "+
                            "modelį, gamybos metus, ridą ir kainą\n ");
               Automobilis a=new Automobilis(autoD);
               String klaidosPožymis=a.validate();
               if (klaidosPožymis.length()==0)
                   ap.visiAuto.add(a);
               else
                 Ks.oun("Automobilis į sąrašą nepriimtas "+klaidosPožymis);
            } else {  // toliau vykdomos atskiri atrankos metodai
               switch (varNr) {
                  case 3:
                     int nR = Ks.giveInt("Nurodykite naujų auto metų ribą: ");
                     atranka = ap.atrinktiNaujusAuto(nR);
                     break;
                  case 4:
                     int r1 = Ks.giveInt("Nurodykite apatinę kainos ribą: ");
                     int r2 = Ks.giveInt("Nurodykite viršutinę kainos ribą: ");
                     atranka = ap.atrinktiPagalKainą(r1, r2);
                     break;
                  case 5:
                     atranka = ap.maksimaliosKainosAuto();
                     break;
                  case 6:
                     String markė=Ks.giveString("Nurodykite norimą markę ir"+
                             " modelį, atskirtus tarpu: ");
                     atranka = ap.atrinktiMarkęModelį(markė);
                     break;
               }
               atranka.println("Štai atrinktų automobilių sąrašas");
               atranka.save(Ks.giveString
                     ("Kur saugoti atrinktus auto (jei ne-tai ENTER) ? "));
            }
         }
      }
   }
}
