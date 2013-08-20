/**
 * Sukuriami klasių Klijentas objektai. Imituojami Lietvoje žinomų bankų kodai.
 * Atliekamos manipuliacijos su sukurtais objektais.
 * Tiriamas klasių metodų veikimas.
 */
public class Demo4NuosaviObjektai {
// -----------------------------------------------------------------------------
    /**
     * Demonstravimui reikalingiems metodams komentarai yra nuimami.
     * Laikinai nereikalingiems - komentarai yra uždedami.
     */
    public void metodoParinkimas(){
        System.out.println("****** Klasė Demo4NuosaviObjektai **********");
        met1PavieniaiKlientai();
        met2KlientųMasyvas();
        met3GeneruojamasKlientųMasyvas();
        met4();
    }
// -----------------------------------------------------------------------------
    /**
     * Sukuriami objektai, jie spausdinami ir skaičiuojamos reikšmės.
     */
    public void met1PavieniaiKlientai(){
        System.out.println("===== metodas1 Pavieniai Klijentai");

        Klijentas b1= new Klijentas("SEB268", 32, 443.60);
        Klijentas b2= new Klijentas("SEB476", 42, 533.20);
        Klijentas b3= new Klijentas("SWE293", 12,  23.10);

        // spausdinant suveikia metodas toString()
        System.out.println("Atskiras 1-as klijentas -> "+b1);
        System.out.println("Atskiras 2-as klijentas -> "+b2);
        System.out.println("Atskiras 3-as klijentas -> "+b3);

        double sumaInd= b1.getIndėlis()+b2.getIndėlis()+b3.getIndėlis();
        System.out.println("Indėlių suma ="+sumaInd);

        int sumaAmž= b1.getAmžius()+b2.getAmžius()+b3.getAmžius();
        System.out.println("Klijentų amžiaus vidurkis = "+sumaAmž/3.0);

        String kodai1 = b1.getKodas()+" "+b2.getKodas()+" "+b2.getKodas();
        String kodai2 = ""+b1+" || "+b2+" || "+b3;
        System.out.println("kodai1 = "+kodai1);
        System.out.println("kodai2 = "+kodai2);

        // demonstruojamas aliasing efektas,
        // kai dviejų ar daugiau objektų rodyklės rodo į tą patį objektą
        b2=b1;
        b1.keistiIndėlį(1000);  // pakeičiame tik vieno klijento indėlį
        System.out.println("1-as klijentas po keitimo b2=b1; -> "+b1);
        System.out.println("2-as klijentas po keitimo b2=b1; -> "+b2);
        System.out.println("3-as klijentas po keitimo b2=b1; -> "+b3);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Formuojamas klijentų objektų masyvas. Todėl jų analizei galima
     * naudoti ciklo sakinį.
     */
    public void met2KlientųMasyvas(){
        System.out.println("===== metodas2 Klientų Masyvas ");
        Klijentas[] klijentai = {
          new Klijentas("kodas579", 19, 2310.55),
          new Klijentas("kodas123", 29, 3100.00),
          new Klijentas("kodasx29", 16,   90.45),
          new Klijentas("kodasy79", 35, 5610.11)
        };
        double suma=0.0;
        for (Klijentas k1 : klijentai) {
            System.out.println("Mūsų klijentai : "+k1);
            suma+=k1.getIndėlis();
        }
        System.out.println("Visų klijentų indėlių suma =   "+suma);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Pagal tam tikrą dėsnį generuojame klijentų masyvą.
     * Po to jis klonuojamas, keičiamas indėlio dydis.
     * Spausdinant nagrinėjame pasikeitimus.
     */
    public void met3GeneruojamasKlientųMasyvas(){
        System.out.println("===== metodas3 Generuojamas Klijentų Masyvas ");
        Klijentas[] a=new Klijentas[10];
            // Generuojame 10 klijentų masyvą
            // Dirbtinis kodas didėja, amžius mažėja, indėlis didėja +11.25
        for(int i=0;i<10;i++)
            a[i]=new Klijentas("NORD"+i,34-i,120+i*11.25);
        // Masyvą B klonuojame, tuo tarpu rodyklės į objektus lieka tos pačios
        Klijentas[] b=a.clone();
        // Todėl keičiant vieną, keičiasi ir kitas
        for(Klijentas a1:a)
            a1.keistiIndėlį(100);
        for(Klijentas a1:a)
            System.out.println("Masyvas A : "+a1);
        System.out.println("===================");
        for(Klijentas a1:b)
            System.out.println("Masyvas B : "+a1);
        double sumaIndėlių = 0;
        int sumaAmžiaus = 0;
        for(Klijentas a1:a) {
            sumaIndėlių += a1.getIndėlis();
            sumaAmžiaus += a1.getAmžius();
        }
        System.out.println("Visų klijentų indėlių suma = " + sumaIndėlių);
        System.out.println("Klijentų amžiaus vidurkis = "
                           + (sumaAmžiaus / a.length) );
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Suformuokite papildomus metodus, panaudojant klasės Klijentas objektus.
     */
    public void met4(){
        System.out.println("===== metodas4 ... ");
        Klijentas[] klijentai = {
          new Klijentas("kodas579", 19, 2310.55),
          new Klijentas("kodas123", 29, 3100.00),
          new Klijentas("kodasx29", 16,   90.45),
          new Klijentas("kodasy79", 35, 5610.11)
        };
        Klijentas c;
        for (int i = 0; i < klijentai.length - 1; i++)
            for (int j = i + 1; j < klijentai.length; j++)
                if (klijentai[i].getAmžius() > klijentai[j].getAmžius()){ 
                    c = klijentai[i];
                    klijentai[i] = klijentai[j];
                    klijentai[j] = c;
                }
        for (int i = 0; i < klijentai.length; i++)
            System.out.println("Pirmas elementas:" +klijentai[i].toString());
    }
}
