import static java.lang.Math.*;
    // importo sakinyje žodelis static leidžia toliau nenaudoti priešdėlio Math.
/**
 * Tai yra klasė, turinti tik statinius metodus.
 * Joje išbandomos ir demonstruojamos tiek sveikų, tiek realių skaičių savybės
 * Spausdinant eilutės pradžioje pakartojamas programinis kodas,
 * kad rezultatų sraute būtų aiškiau kokie veiksmai buvo atlikti.
 */
public class Demo1SkaiciuSavybes {

    /**
     * Demonstravimui reikalingiems metodams komentarai yra nuimami.
     * Laikinai nereikalingiems - komentarai yra uždedami.
     */
    public static void metodoParinkimas(){
        System.out.println("****** Klasė Demo1SkaiciuSavybes **********");
        //met1SkaičiaiInt();
       // met2DoubleMatematika();
        //met2DoubleMatematikaB();
       // met3TikslumasBegalybė();
       // met4SkirtingiTipai();
       // met5GautiIšTeksto();
       // met6PateiktiĮTekstą();
       // met7RibinėsReikšmės();
        met8();
    }
// -----------------------------------------------------------------------------
    /**
     * Prisimenamos operacijos, kurios yra bendros su C ir C++ kalba.
     * Demonstruojamas int kintamųjų aprašymas ir operacijos su jais.
     */
    public static void met1SkaičiaiInt() {
        System.out.println("===== metodas1 - bandome su int skaičiais");
        int a = 99, b=55, c, d;
                        System.out.println("int a,b; -> "+a+" "+b);
        c=a++; d=b--;
                        System.out.println("c=a++; d=b--;  -> "+c+" "+d);
        c=a++; d=b--;
                        System.out.println("c=a++; d=b--;  -> "+c+" "+d);
        ++a; --b;
                        System.out.println("++a; --b; -> "+a+" "+b);
        ++a; --b;
                        System.out.println("++a; --b; -> "+a+" "+b);
        a += 25;
                        System.out.println("a += 25; -> "+a);
        a *= 4;
                        System.out.println("a *= 4; -> "+a);
        a /= 10;
                        System.out.println("a /= 10; -> "+a);
        int n= 127/10;
                        System.out.println("short n= 127/10; -> "+n);
        int m= 39%7;
                        System.out.println("short m= 39%7; -> "+m);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Demonstruojamas realių skaičių užrašymas ir kai kurios
     * matematinės konstantos bei funkcijos.
     * Studentams rekomenduojama pasirinkti 3 matematines tapatybes
     * (iš jiems žinomų algebros, geometrijos ar trigonometrijos)
     * ir atskirai paskaičiuoti jų kairiąsias ir dešiniąsias puses.
     */
    public static void met2DoubleMatematika(){
        System.out.println("===== metodas2 - bandome šiek tiek matematikos");
        double a=3.14, b=2.7, c, d;
                    System.out.println("double a, b; -> "+a+" "+b);
        c=Math.PI; d=Math.E;
                    System.out.println("c=Math.PI; d=Math.E; -> "+c+" "+d);
        d=Math.exp(1);
                    System.out.println("d=Math.exp(1); -> "+d);
        a=Math.toDegrees(c);
                    System.out.println("a=Math.toDegrees(c); -> "+a);
        b=Math.toDegrees(Math.asin(0.5));
                    System.out.println("b=Math.toDegrees(Math.asin(0.5)); -> "+b);
        c=Math.pow(4,3);
                    System.out.println("c=Math.pow(4,3); -> "+c);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
    public static void met2DoubleMatematikaB(){
        System.out.println("===== metodas2B - matematika be Math");
        double a=3.14, b=2.7, c, d;
                    System.out.println("double a, b; -> "+a+" "+b);
        c=PI; d=E;
                    System.out.println("c=PI; d=E; -> "+c+" "+d);
        d=exp(1);
                    System.out.println("d=exp(1); -> "+d);
        a=toDegrees(c);
                    System.out.println("toDegrees(c); -> "+a);
        b=toDegrees(asin(0.5));
                    System.out.println("b=toDegrees(asin(0.5)); -> "+b);
        c=pow(4,3);
                    System.out.println("c=pow(4,3); -> "+c);
       // double x=1.234;
        float x = (float)1.234;
        float y1 = (float)(sin(x)*sin(x)+cos(x)*cos(x));
        double y2 = sin(x)*sin(x)+cos(x)*cos(x);
        System.out.println("sin^2+cos^2= (float)"+y1 + "  (double)" + y2);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Įsitikiname, kad realių skaičių aritmetikoje yra tikslumo problemų.
     * Todėl reikia būti atidiems vykdant lygininmo operaciją.
     * Išbandome begalybės atsiradimą ir panaudojimą.
     */
    public static void met3TikslumasBegalybė(){
        System.out.println("===== metodas3 !!!! tikslumo problemos !!!!!");
        System.out.println("(0.333+0.333+0.333) -> "+(0.333+0.333+0.333));
        double t1_9 = 1.0/9.0;   // viena devintoji
        double t2_3a = 2.0/3.0;  // dvi trečiosios
        double t2_3b = t1_9+t1_9+t1_9+t1_9+t1_9+t1_9; // turi būti 2/3
        if(t2_3a==t2_3b)
            System.out.println("LyguLygu");  // deja taip nėra
        else
            System.out.println("Nelygu -> "+t2_3a+"  "+t2_3b);
        // begalybė ir dalyba iš nulio taip pat yra galima
        double x=5.6/0.0;
        double y= Math.toDegrees(Math.atan(x));
        System.out.println("x=5.6/0.0; "+x+"  atan(x)="+y);
        System.out.println("Math.sqrt(9)= "+Math.sqrt(9));
        System.out.println("Math.sqrt(-9)= "+Math.sqrt(-9));
        System.out.println("d_max="+(Double.MAX_VALUE)+
                           " 3*d_max="+(Double.MAX_VALUE*3));
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Išbandome skirtingų tipų skaičių priskyrimus ir pasekmes.
     */
    public static void met4SkirtingiTipai(){
        System.out.println("===== metodas4: operacijos su Skirtingais Tipais");
        int n=67 + 1024 ;
        byte m= (byte)n;
        System.out.println("int ir byte  "+n+"  "+m);
        int i = 2*Short.MAX_VALUE;
        short s=(short)i;
        System.out.println("int ir short  "+i+"  "+s);
        System.out.println("(4/3)  "+(4/3));
        System.out.println("(float)(4/3)  "+(float)(4/3));
        System.out.println("(4/3.)  "+(4/3.));
        System.out.println("(4./3)  "+(4./3));
        System.out.println("(float)(1/3.)  "+(float)(4/3.));
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Skaičių gavimas iš teksto yra universalus būdas įvedimo operacijose.
     */
    public static void met5GautiIšTeksto(){
        System.out.println("===== metodas5 skaičių gavimas iš teksto");
        String e="-123456";
        int a = Integer.parseInt(e);
                System.out.println("eilute ir int "+e+" "+a);
        String f="+1234.560001";
        float d= Float.parseFloat(f);
                System.out.println("eilute ir float "+f+" "+d);
        String g="+1234.56e-9";
        d= Float.parseFloat(g);
                System.out.println("eilute ir float "+g+" "+d);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
/**
     * Taip pat ir visi rezultatai pateikiami teksto pavidalu.
     * Labai patogu skaičių jungti prie String su + operacija.
     * Pateikiant kelis skaičius tarp + ženklų, jie nesisumuoja, o klijuojasi.
     */
    public static void met6PateiktiĮTekstą(){
        System.out.println("===== metodas6 skaičių pateikimas į tekstą");
        int a=543;
        int b=21;
        String p = Integer.toString(a);
        String r = Integer.toString(b);
                System.out.println("Pradinės reikšmės int a=543; int b=21;");
                System.out.println("Integer.toString(a) ir (b) -> "+p+r);
        String e = "Pradinės reikšmės=" + a + b;
                System.out.println("String e= ... +a+b; -> "+e);
        e = "" +5+4+3+2+1;
                System.out.println("e=\"\"+5+4+3+2+1; -> "+e);
        e = "" + (5+4+3+2+1);
                System.out.println("e=\"\"+(5+4+3+2+1); -> "+e);
        double d = 123.4e-8;
        e = "" + d;
                System.out.println("double d=12.3e-8;e=\"\"+d; -> "+e);

        // spausdinant duomenis į lenteles patogiausia su printf() !!!! ******
        d = 123.4e-2;
        System.out.printf("|----------------|\n");
        System.out.printf("| %4d | %7.2f |\n", a, d);
        System.out.printf("| %4d | %7.2f |\n", b, d/9.99);
        System.out.printf("| %4d | %7.2f |\n", b*100, d*100);
        System.out.printf("|----------------|\n");
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Išbandome perėjimą už ribinių reikšmių. Įsitikiname,
     * kad rezultatai yra gaunami kitaip nei įprastinėje aritmetikoje.
     */
    public static void met7RibinėsReikšmės(){
        System.out.println("===== metodas7 - tiriamos Ribinės Reikšmės");
        Short c=Short.MAX_VALUE-1;
        System.out.println("c=Short.MAX_VALUE-1; -> "+c);
        System.out.println("++c; -> " + (++c) );
        System.out.println("++c; -> " + (++c) );
        System.out.println("++c; -> " + (++c) );
        c=Short.MIN_VALUE;
        System.out.println("c=Short.MIN_VALUE; -> "+c);
        System.out.println("--c; -> " + (--c) );
        System.out.println("--c; -> " + (--c) );
        byte g=(byte)(13*20);
        System.out.println("byte g=(byte)(13*20);  " + g);
        int d=Integer.MAX_VALUE;
        int e=d*2;
        System.out.println("d=Integer.MAX_VALUE; "+d+" ir MAX*2= "+e);
        d=Integer.MIN_VALUE+1;
        e=d*4;
        System.out.println("d=Integer.MIN_VALUE; "+d+" ir MIN*4= "+e);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Atliekate savo pasirinktus demo skaičiavimus su sveikais skaičiais
     */
    public static void met8(){
        System.out.println("===== metodas6 ... ");
        int Kampas = 250;
        System.out.println("Kampas yra: "+ (Kampas));
        double KampasRadianais = Kampas * PI / 180.0;
        System.out.println("Kampas radianais yra: "+(float)(KampasRadianais));
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Atliekate savo pasirinktus demo skaičiavimus su double skaičiais
     * tam tinka įvairių matematinių tapatybių patikrinimas
     */
    public static void met9(){
        System.out.println("===== metodas7 ... ");
        
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
}
