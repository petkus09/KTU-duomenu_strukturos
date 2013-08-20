package Laboras3demo;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import studijosKTU.*;

/**  @author EK */
public class Automobilis implements DataKTU, Comparable {

    // bendri duomenys visiems automobiliams (visai klasei)
    static private int priimtinųMetųRiba = 1990;
    static private int dabartiniaiMetai  = 2010;
    static private double mažiausiaKaina =    100.0;
    static private double didžiausiaKaina= 333000.0;

    // kiekvieno automobilio individualūs duomenys
    private String markė;
    private String modelis;
    private int gamMetai;
    private int rida;
    private double kaina;

    public Automobilis() {
    }
    public Automobilis(String markė, String modelis,
                int gamMetai, int rida, double kaina){
        this.markė=markė;
        this.modelis=modelis;
        this.gamMetai=gamMetai;
        this.rida=rida;
        this.kaina=kaina;
        validate();
    }
    public Automobilis (String e){
        this.fromString(e);
    }
    public Automobilis create(String dataString) {
        return new Automobilis(dataString);
    }
    public String validate() {
        String klaidosTipas="";
        if (gamMetai < priimtinųMetųRiba || gamMetai > dabartiniaiMetai)
           klaidosTipas="Blogai nurodyti gamybos metai; ";
        if (kaina < mažiausiaKaina || kaina > didžiausiaKaina)
            klaidosTipas+="Kaina už leistinų ribų; ";
        return klaidosTipas;
    }
    public void fromString(String autoD){
        try {   // ed - tai elementarūs duomenys, atskirti tarpais
            Scanner ed=new Scanner(autoD);
            markė   =ed.next();
            modelis =ed.next();
            gamMetai=ed.nextInt();
            rida    =ed.nextInt();
            setKaina(ed.nextDouble());
            validate();
        } catch (InputMismatchException  e) {
            Ks.ern("Blogas duomenų formatas apie auto -> "+ autoD);
        } catch (NoSuchElementException e) {
            Ks.ern("Trūksta duomenų apie auto -> "+ autoD);
        }
    }
    @Override
    public String toString(){  // papildyta su autoRegNr
        return String.format("%-8s %-8s %4d %7d %8.1f",
               markė, modelis, gamMetai, rida, kaina);
    };
    public String getMarkė() {
        return markė;
    }
    public String getModelis() {
        return modelis;
    }
    public int getGamMetai() {
        return gamMetai;
    }
    public int getRida() {
        return rida;
    }
    public double getKaina() {
        return kaina;
    }
    public void setKaina(double kaina) {
        this.kaina = kaina;
    }
   @Override
    public int compareTo(Object a) { 
        // lyginame pagal svarbiausią požymį - kainą
        double kainaKita = ((Automobilis)a).getKaina();
        if (kaina<kainaKita) return -1;
        if (kaina>kainaKita) return +1;
        return 0;
    }
    public static Comparator<Automobilis> pagalMarkęModelį =
              new Comparator<Automobilis>() {
       public int compare(Automobilis a1, Automobilis a2) {
          // pradžioje pagal markes, o po to pagal modelius
          int cmp = a1.getMarkė().compareTo(a2.getMarkė());
          if(cmp!=0) return cmp;
          return a1.getModelis().compareTo(a2.getModelis());
       }
    };
    public static Comparator pagalKainą = new Comparator() {
       // sarankiškai priderinkite prie generic interfeiso
       public int compare(Object o1, Object o2) {
          double k1 = ((Automobilis) o1).getKaina();
          double k2 = ((Automobilis) o2).getKaina();
          // didėjanti tvarka, pradedant nuo mažiausios
          if(k1<k2) return -1;
          if(k1>k2) return 1;
          return 0;
       }
    };
    public static Comparator pagalMetusKainą = new Comparator() {
       // sarankiškai priderinkite prie generic interfeiso
       public int compare(Object o1, Object o2) {
          Automobilis a1 = (Automobilis) o1;
          Automobilis a2 = (Automobilis) o2;
          // metai mažėjančia tvarka, esant vienodiems lyginama kaina
          if(a1.getGamMetai()<a2.getGamMetai()) return 1;
          if(a1.getGamMetai()>a2.getGamMetai()) return -1;
          if(a1.getKaina()<a2.getKaina()) return 1;
          if(a1.getKaina()>a2.getKaina()) return -1;
          return 0;
       }
    };

}
