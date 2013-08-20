package LaboraiDemo;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import studijosKTU.*;

/**  @author EK */
public class Automobilis implements DataKTU {

    // bendri duomenys visiems automobiliams (visai klasei)
    static private int priimtinųMetųRiba = 1990;
    static private int dabartiniaiMetai = 2010;
    static private double mažiausiaKaina = 100.0;
    static private double didžiausiaKaina = 333000.0;
    private String marke = "";
    private String modelis = "";
    private int gamMetai = -1;
    private int rida = -1;
    private double kaina = -1.0;

    public Automobilis() {
    }

    public Automobilis(String markė, String modelis,
            int gamMetai, int rida, double kaina) {
        this.marke = markė;
        this.modelis = modelis;
        this.gamMetai = gamMetai;
        this.rida = rida;
        this.kaina = kaina;
        validate();
    }

    public Automobilis(String e) {
        this.fromString(e);
    }

    public Automobilis create(String dataString) {
        return new Automobilis(dataString);
    }

    public String validate() {
        String klaidosTipas = "";
        assert (gamMetai < priimtinųMetųRiba || gamMetai > dabartiniaiMetai) :
                klaidosTipas = "Blogai nurodyti gamybos metai; ";
        assert (kaina < mažiausiaKaina || kaina > didžiausiaKaina) :
                klaidosTipas += "Kaina už leistinų ribų; ";
        return klaidosTipas;
    }

    public void fromString(String autoD) {
        try {   // ed - tai elementarūs duomenys, atskirti tarpais
            Scanner ed = new Scanner(autoD);
            marke = ed.next();
            modelis = ed.next();
            gamMetai = ed.nextInt();
            setRida(ed.nextInt());
            setKaina(ed.nextDouble());
            validate();
        } catch (InputMismatchException e) {
            Ks.ern("Blogas duomenų formatas apie auto -> " + autoD);
        } catch (NoSuchElementException e) {
            Ks.ern("Trūksta duomenų apie auto -> " + autoD);
        }
    }

    @Override
    public String toString() {
        return marke + "_" + modelis + ":" + gamMetai + " " + getRida() + " "
                + String.format("%4.1f", kaina);
    }

    public String getMarkė() {
        return marke;
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

    public void setRida(int rida) {
        this.rida = rida;
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null)
                || !(o instanceof Automobilis)) {
            return false;
        }
        if (o == this) {
            return true;
        }
        Automobilis auto = (Automobilis) o;
        return (marke.equals(auto.marke))
                && (modelis.equals(auto.modelis))
                && (gamMetai == auto.gamMetai)
                && (rida == auto.rida)
                && (kaina == auto.kaina);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.marke != null ? this.marke.hashCode() : 0);
        hash = 89 * hash + (this.modelis != null ? this.modelis.hashCode() : 0);
        hash = 89 * hash + this.gamMetai;
        hash = 89 * hash + this.rida;
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.kaina)
                ^ (Double.doubleToLongBits(this.kaina) >>> 32));
        return hash;
    }
}
