import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
/**
 * Klasė skirta įvairių būdų, skirtų laiko apdorimui ir pateikimui, išbandyti.
 */
public class Demo6LaikasDatos {  
    static Locale vLT=new Locale("LT");
    static Locale vLV=new Locale("LV");
    static Locale vPL=new Locale("PL");
    static Locale vIT=new Locale("IT");
    static Locale vRU=new Locale("RU");
// -----------------------------------------------------------------------------
    /**
     * Demonstravimui reikalingiems metodams komentarai yra nuimami.
     * Laikinai nereikalingiems - komentarai yra uždedami.
     */
    public static void metodoParinkimas(){
        System.out.println("****** Klasė Demo6LaikasDatos **********");
        met1SkaičiavimųTrukmė();
        met2KiekDabarLaiko();
        met3DatosNustatymasLinkAteities();
        met4DatųSkirtumasNuoPraeities();
        met5LaikoFormatai();
    }
// -----------------------------------------------------------------------------
    /**
     * Išbandomas laiko intervalo matavimas milisekundėmis ir nanosekundėmis.
     * Pakeiskite skaičiavimų dalį bei apimtį, ištirkite rezultatus.
     * Palyginkite kompiuterių su skirtingais dažniais gautus rezultatus.
     */
    public static void met1SkaičiavimųTrukmė(){
        System.out.println("===== metodas1 Skaič. trukmė 100 milijonų cikle");
        long n = 100000000; // 100 milijonų
        long start = System.currentTimeMillis();
        long nano1 = System.nanoTime();
        long suma=0;
        for(int i=0;i<n;i++)
            suma+=i;
        long finish = System.currentTimeMillis();
        long nano2 = System.nanoTime();
        System.out.println("Rezultatas = "+suma);
        System.out.println("Skaičiavimai užtruko "+(finish-start)+" miliSec");
        System.out.println("Skaičiavimai užtruko "+(nano2-nano1) +" nanoSec");
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Išbandomas paprastas laiko sužinojimo ir pateikimo būdas
     */
    public static void met2KiekDabarLaiko(){
        System.out.println("===== metodas2 Kiek Dabar Laiko ?");
        Date dabar = new Date();
        System.out.println("Dabartinis laikas yra: "+dabar);
        System.out.println("Dabartinis laikas yra: "+dabar);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Išbandomas naujos datos skaičiavimo būdas po nurodyto dienų skaičiaus.
     */
    public static void met3DatosNustatymasLinkAteities(){
        System.out.println("===== metodas3 Datos Nustatymas Link Ateities");
        int dienųSk= 4;
        // toliau laikas skaičiuojamas milisekundėmis
        long intervalasMs= dienųSk*(1000*60*60*24);
        Date dabar = new Date();
        long naujaData= dabar.getTime()+intervalasMs;
        Date dienaX = new Date(naujaData);
        System.out.println("Po "+dienųSk+" dienų - data bus");
        System.out.println(dienaX);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Išbandomas laiko intervalo skaičiavimo būdas, panaudojant
     * datos gavimą iš teksto pagal nurodytą formatą.
     * Išbandykite įvairius formatus.
     */
    public static void met4DatųSkirtumasNuoPraeities() {
        System.out.println("===== metodas4 Datų Skirtumas Nuo Praeities");
        DateFormat df = new SimpleDateFormat("yyyy MM dd");
        String pradinėsDatosTekstas = " 2010 05 08";
        try {  // išimant datą iš teksto būtina įvertinti ParseException
            Date date1 = df.parse(pradinėsDatosTekstas);
            Date dabar = new Date();
            // skirtumas pradžioje paskaičiuojamas milisekundėmis
            long skirtumasMs = dabar.getTime() - date1.getTime();
            long skirtumasDienomis = skirtumasMs / (1000L * 60L * 60 * 24);
            System.out.println("Nuo " + date1 + " praėjo jau " +
                                        skirtumasDienomis + " dienos");
        } catch (ParseException e) {
            System.err.println("Datos formatas turi buti yyyy MM dd");
        }
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Išbandomi įvairūs datos ir laiko pateikimo formatai.
     */
    public static void met5LaikoFormatai(){
        System.out.println("===== metodas5 Kiek Dabar Laiko ?");
        Date dabar = new Date();
        Format f0 = new SimpleDateFormat("EEEE yyyy MMMM dd HH-mm:ss");
        Format f1 = new SimpleDateFormat("yyyy/MMM/dd HH:mm:ss EEEE",  vLT);
        Format f2 = new SimpleDateFormat("EEEE yy-MMMM-dd HH:mm:ss",   vLV);
        Format f3 = new SimpleDateFormat("EEEE yyyy/MMMM/dd HH:mm:ss", vPL);
        Format f4 = new SimpleDateFormat("EEEE dd/MMMM/yyyy HH:mm:ss", vIT);
        Format f5 = new SimpleDateFormat("EEEE yyyy/MMMM/dd HH:mm:ss", vRU);
        System.out.println(f0.format(dabar));
        System.out.println(f1.format(dabar));
        System.out.println(f2.format(dabar));
        System.out.println(f3.format(dabar));
        System.out.println(f4.format(dabar));
        System.out.println(f5.format(dabar));
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Atliekate savo pasirinktus laiko ir datos demo skaičiavimus
     */
    public static void met6(){
        System.out.println("===== metodas6 ... ");

        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
}
