import java.util.Arrays;
import java.text.Collator;
import java.util.Locale;
import java.util.Scanner;
/**
 * Šioje klasėje išbandoma kaip apdoroti tekstus bei duomenis lietuviškoje
 * bei kitų valstybių aplinkose.
 */
public class Demo5LokalesLietuvybe {
    /**
     * Demonstravimui reikalingiems metodams komentarai yra nuimami.
     * Laikinai nereikalingiems - komentarai yra uždedami.
     */
    public static void metodoParinkimas(){
        System.out.println("****** Klasė Demo5LokalesLietuvybe **********");
        met1LietuviškiŽodžiai();
//        met2LokalizuotasSkaičiųPateikimas();
//        met3LokalizuotasSkaičiųSkaitymas();
//        met4GalimosLokalės();
    }
// -----------------------------------------------------------------------------
/**
 * Išbandomas rūšiavimas su lietuviškais žodžiais (raidėmis),
 * panaudojant įvairias lokales.
 */
    public static void met1LietuviškiŽodžiai(){
        System.out.println("=== metodas1 Rūšiavimas su lietuviškais žodžiais");
        String[] z0={"efektas", "įvartis", "ūdra", "ąsotis", "šienas",
               "ąžuolas", "čerpė", "ėdžios", "indėlis", "yla", "avilys"
        };
        // Pasiriuošiame sulyginimui pagal skirtingas kalbas
        Collator defaultLyginimas   =Collator.getInstance();
        Collator lithuanianLyginimas=Collator.getInstance(new Locale("LT"));
        Collator americanLyginimas  =Collator.getInstance(Locale.US);
        String[]z1=z0.clone();   // pasiruošiame masyvus
        String[]z2=z0.clone();   // skirtingiems rūšiavimams
        String[]z3=z0.clone();   // visi jie rodo į tuos pačius žodžius
        String[]z4=z0.clone();
        Arrays.sort(z1);  // Rūšiavimas be Collator - visos lietuviškos gale
        Arrays.sort(z2, defaultLyginimas);
        Arrays.sort(z3, lithuanianLyginimas);
        Arrays.sort(z4, americanLyginimas);
        System.out.printf("%-12s%-14s%-14s%-14s%-14s\n",
                "Pradinis", "Be collator", "Pagal default",
                "Lietuviškai", "Amerikoniškai");
        for (int i=0; i<z0.length; i++)
            System.out.printf("%-12s%-14s%-14s%-14s%-14s\n",
                    z0[i],z1[i],z2[i],z3[i], z4[i]);
        System.out.println("*** Atkreipkite dėmesį į žodžio yla vietą ...");
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Išbandomas didelių skaičių vaizdavimas panaudojant triadas
     * bei dešimatainės dalies vaizdavimo valdymas.
     * Tai yra bandomos išplėstinės printf() galimybės.
     */
    public static void met2LokalizuotasSkaičiųPateikimas(){
        System.out.println("===== metodas2 Lokalizuotas Skaičių Pateikimas");
        Locale vDE= new Locale("DE");  // vokiškai
        Locale vLT= new Locale("LT");  // lietuviškai
        Locale vUS= Locale.US;         // amerikoniškai
        double bandomasis = 123456789.86148;
        System.out.printf(    "PagalNustatymą  %,16.4f\n",bandomasis);
        Locale.setDefault(vUS);
        System.out.printf(    "KeičiamNustatymą%,16.4f\n",bandomasis);
        System.out.printf(vDE,"Vokiškai        %,16.4f\n",bandomasis);
        System.out.printf(vLT,"Lietuviškai     %,16.4f\n",bandomasis);
        System.out.printf(vUS,"Amerikoniškai   %,16.4f\n",bandomasis);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Išbandomas lokalizuotas skaičių skaitymas su skirtingomis lokalėmis
     * panaudojant Scanner klasės objektus.
     * Priklausomai nuo nustatytos lokalės gali kilti klaidos situacija.
     */
    public static void met3LokalizuotasSkaičiųSkaitymas(){
        System.out.println("===== metodas3 Lokalizuotas Skaičių Skaitymas");
        String s="     1234567.1234    ";
        double d=Double.parseDouble(s); // Veikia kietai - keisti negalima
        String sUS="   1,234,567.1234 xxx ";
        String sDE="   1.234.567,1234 xxx ";
        String sLT="   1 234 567,1234 xxx ";

        Scanner t1= new Scanner(sLT);
        d=t1.nextDouble();
        System.out.println(sLT+" su default scanner="+d);
        Locale.setDefault(Locale.GERMANY);
        Scanner t2= new Scanner(sDE);
        d=t2.nextDouble();
        System.out.println(sDE+" su GERMANY scanner="+d);
        Scanner t3= new Scanner(sUS);
        t3.useLocale(Locale.US);
        d=t3.nextDouble();
        System.out.println(sUS+" su US scanner=     "+d);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Sužinome apie visas galimas standartų komiteto ISO patvirtintas lokales.
     */
    public static void met4GalimosLokalės(){
        System.out.println("===== metodas4 Analizuojame Galimas Lokales");
          Locale[] ll=Locale.getAvailableLocales();
          for (Locale l1 : ll) {
          System.out.printf("%-3s %-18s %-12s %-8s %-8s \n",
                  l1.getCountry(),
                  l1.getDisplayCountry(),
                  l1.getDisplayLanguage(),
                  l1.getISO3Country(),
                  l1.getISO3Language()
                  );
        }
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Atliekate savo pasirinktus demo skaičiavimus, susijusius su lokalėmis
     */
    public static void met5(){
        System.out.println("===== metodas5 ... ");

        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
}
