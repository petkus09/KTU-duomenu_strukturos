package Laboras2demo;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import studijosKTU.*;

/**  @author EK */
public class Automobilis implements DataKTU{

    // bendri duomenys visiems automobiliams (visai klasei)
    static private int priimtinųMetųRiba=1990;
    static private int dabartiniaiMetai =2010;
    static private double mažiausiaKaina =    100.0;
    static private double didžiausiaKaina= 333000.0;

    // kiekvieno automobilio individualūs duomenys
    private String markė;
    private String modelis;
    private int gamMetai;
    private int rida;
    private double kaina;

    public Automobilis (){};
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
    public DataKTU create(String dataString) {
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
    public String toString(){
        return String.format("%-10s %-10s %4d %6d %7.1f",
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
}
