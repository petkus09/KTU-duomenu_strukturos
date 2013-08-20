import java.util.Scanner;
import java.util.StringTokenizer;
/**
 * Šioje klasėje išbandomi ir demonstruojami String klasės metodai.
 * Jų pagalba greitai ir patogiai galima atlikti įvairius veiksmus su tekstais.
 *
 */
public class Demo2SimboliaiTekstai {
    /**
     * Demonstravimui reikalingiems metodams komentarai yra nuimami.
     * Laikinai nereikalingiems - komentarai yra uždedami.
     */
// -----------------------------------------------------------------------------
    public static void metodoParinkimas(){
        System.out.println("****** Klasė Demo2SimboliaiTekstai **********");
      //  met0UniCodeSimboliai();
     //   met1Pakeisti_MB_GB();
      //  met2ElektroninisPaštas();
      //  met3TarpSkliaustų();
        met4SimboliųKodai();
      //  met5StringTokenizer();
      //  met6Scanner();
      //  met7FormatuotiDuomenis();
      //  met8();
        met9();
    }
// -----------------------------------------------------------------------------
    /**
     * Susipažįstame su įvairiais simboliais iš http://unicode.org/charts/
     * juos patogu surašyti panaudojant WORD programą ir Insert Symbol
     */
    public static void met0UniCodeSimboliai(){
        System.out.println("=== metodas0 susipažinimas su UniCode simboliais");

        String s="☺☻☼♀♂♠♣♥♦♪♫▲►▼◄●®©±µ£¥§ΞΨξ";
        System.out.println("pradinė:  "+s);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Išbandomas teksto fragmentų pakeitimas.
     */
    public static void met1Pakeisti_MB_GB(){
        System.out.println("===== metodas1 Pakeisti_MB_GB ");

        String s="2.68MB 59MB 45 mB 99mb 7kb 11MB";
        String t=s.replace("MB", "GB");
        System.out.println("pradinė:  "+s+
                         "\npakeista: "+t);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     *  Išskiriamos atskiros elektroninio pašto adreso komponentės.
     */
    public static void met2ElektroninisPaštas(){
        System.out.println("===== metodas2 ElektroninisPaštas ");
        String eMail="lauras.lapelis@stud.ktu.lt";
//        String eMail="tarzanas94@yahoo.com"; // varijantas pakeitimui
        int k=eMail.indexOf('@');
        String vardas=eMail.substring(0, k);
        System.out.println(eMail+"\nVardas: "+vardas+"\n(@)indeksas "+k+
            "\nDomenas: "+eMail.substring(eMail.lastIndexOf('.')+1));

        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Išskiriami teksto fragmentai, esantys tarp skliaustų.
     */
    public static void met3TarpSkliaustų(){
        System.out.println("===== metodas4 TarpSkliaustų ");

        String s="svoris(3,65kg)  ūgis(53cm)";
        int k1=s.indexOf('(');
        int k2=s.indexOf(')', k1);
        int k3=s.indexOf('(', k2);
        int k4=s.indexOf(')', k3);
        String t=s.substring(k1+1, k2)+" "+s.substring(k3+1, k4);
        System.out.println("Pradinė: "+s+
                  "\nTarp skliaustų: "+t);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
   /**
    * Demonstruojamas simbolių kodų gavimas ir jų reikšmės.
    * Atkreipkite dėmesį į didžiųjų ir mažųjų bei lietuviškų raidžių ypatybes.
    */
    public  static void met4SimboliųKodai(){
        System.out.println("===== metodas1 - Analizuojame kodavimą");
        String e="aAbBčČ";
        System.out.println("String e=\"aAbBčČ\";");
        System.out.println("Kodas "+e.substring(0,1)+"="+e.codePointAt(0));
        System.out.println("Kodas "+e.substring(1,2)+"="+e.codePointAt(1));
        System.out.println("Kodas "+e.substring(2,3)+"="+e.codePointAt(2));
        System.out.println("Kodas "+e.substring(3,4)+"="+e.codePointAt(3));
        System.out.println("Kodas "+e.substring(4,5)+"="+e.codePointAt(4));
        System.out.println("Kodas "+e.substring(5,6)+"="+e.codePointAt(5));
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Išbandomas teksto skaidymas į fragmentus skyriklių pagalba.
     * Po to teksto fragmentai traktuojami kaip savarankiškos eilutės.
     */
    public static void met5StringTokenizer(){
        System.out.println("===== metodas5 StringTokenizer ");
        String analizė="Simas-Jonas;  x=600+90, Mėnuo::::Gruodis=24 ++Adios--";
        System.out.println("Tekstas analizei -> "+ analizė);
        // pagal nutylėjimą skyriklis yra tarpas
        StringTokenizer p= new StringTokenizer(analizė);
        String p1=p.nextToken();
        String p2=p.nextToken();
        String p3=p.nextToken();
        System.out.println("Atskira dalelė (tarp tarpų) -> "+p1);
        System.out.println("Atskira dalelė (tarp tarpų) -> "+p2);
        System.out.println("Atskira dalelė (tarp tarpų) -> "+p3);
        String skyrikliai=" :;,";
        System.out.println("Analizuojame su kitais skyrikliais"+skyrikliai);
        StringTokenizer s= new StringTokenizer(analizė,skyrikliai);
        System.out.println("Atskira dalelė -> "+s.nextToken());
        System.out.println("Atskira dalelė -> "+s.nextToken());
        System.out.println("Atskira dalelė -> "+s.nextToken());
        System.out.println("Atskira dalelė -> "+s.nextToken());
        System.out.println("Atskira dalelė -> "+s.nextToken());
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Scanner klasės objektai turi metodus ne tik atskiriems teksto
     * fragmentams išskirti, bet ir tiesiogiai gauti skaičių reikšmes.
     */
    public static void met6Scanner(){
        System.out.println("===== metodas6 Scanner ");
        // kai lietuvybė - dešimtosios dalys skiriamos kableliu
        // kai jos nėra - tada tašku
        String analizė="Simas Jonaitis 1988 5800,36 246,18";
        System.out.println("Tekstas analizei -> "+ analizė);
        Scanner sc=new Scanner(analizė);
        String vardas   = sc.next();
        String pavardė  = sc.next();
        int gimMetai    = sc.nextInt();
        double alga     = sc.nextDouble();
        double atskaito = sc.nextDouble();
        System.out.println("Rezultatas atvirkščiai -> "+
                atskaito+" "+alga+" "+gimMetai+" "+pavardė+" "+vardas);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /*
     * Išbandomi String.format() ir printtf() metodai,
     * kurių pagalba yra tvarkingai pateikiami duomenys ir rezultatai.
     */
    public static void met7FormatuotiDuomenis(){
        System.out.println("===== metodas7 Formatuoti Duomenis ");
        int a=1234;
        double b=12345.678;
        String e=String.format("Rezultatai: a=%5d ir b=%10.2f", a, b);
        System.out.println(e);
        System.out.printf("Rezultatai: a=%5d ir b=%10.2f\n", a, b);
        String f=String.format("Rezultatai: a=%5d ir b=%10.2f", ++a, ++b);
        System.out.println(f);
        System.out.printf("Rezultatai: a=%5d ir b=%10.2f\n", ++a, ++b);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Atliekate savo pasirinktus demo skaičiavimus su tekstais ir simboliais
     */
    public static void met8(){
        System.out.println("===== metodas6 ... ");
        String Tekstas ="Bandymas";
        int n = Tekstas.length();
         System.out.println("Zodis: " +Tekstas+", zodzio ilgis "+n);
        String a = Tekstas.substring(0, n/2);
        String b = Tekstas.substring(n/2, n);
        String NaujasZodis = b + a;
        System.out.println("Sukeistas zodis: " +NaujasZodis);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Atliekate savo pasirinktus demo skaičiavimus su  tekstais ir simboliais
     */
    public static void met9(){
        System.out.println("===== metodas7 ... ");
        boolean Result = false;
          Scanner sca=new Scanner("1988-01-23".replace("-", " "));
          Scanner scb=new Scanner("1988-02-22".replace("-", " "));
          int Sa = sca.nextInt();
          int Sb = scb.nextInt();
          if (Sa > Sb){
              Result = true;
          }
          else if(Sa < Sb){
              Result = false;
            }
          else {
                Sa = sca.nextInt();
                Sb = scb.nextInt();
                if (Sa > Sb){ 
                    Result = true;
                }
                else if(Sa < Sb){
                    Result = false;
                }
                else {
                    Sa = sca.nextInt();
                    Sb = scb.nextInt();
                    if (Sa >= Sb){ 
                        Result = true;
                    }
                    else {
                        Result = false;
                    }
                }
            }
        System.out.println(Result);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n" + Result);
    }
// -----------------------------------------------------------------------------
}
