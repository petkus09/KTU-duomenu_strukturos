import java.util.Arrays;

/**
 * Šioje klasėje parodoma kaip suformuojami pilni masyvai.
 * Pateikiamas jų perrinkimas (spausdinimas) ciklo for-each pagalba.
 * Tai yra patogus būdas pilnų masyvų ar kolekcijų peržiūrai.
 */
public class Demo3MasyvaiPerziura {

    /**
     * Sukuriami statiniai vienmatis ir dvimatis masyvai apie automobilius.
     */
    String[] automarkės = {
        "mazda", "ford", "saab", "honda", "renault", "peugeot"
    };
    String[][] automodeliai = {
        {"121", "323", "626", "MX6"},
        {"fiesta", "escort", "focus", "sierra", "mondeo"},
        {"92", "96"},
        {"accord", "civic", "jazz"},
        {"laguna", "megane", "twingo", "scenic"},
        {"206", "207", "307"}};

// -----------------------------------------------------------------------------
    /**
     * Demonstravimui reikalingiems metodams komentarai yra nuimami.
     * Laikinai nereikalingiems - komentarai yra uždedami.
     */
    public void metodoParinkimas(){
        System.out.println("****** Klasė Demo3MasyvaiPerziura **********");
        met1MasyvasSpausdinimui();
        met2StringMasyvas();
        met3DvimatisMasyvas();
        met4MasyvaiKartu();
        met5MėnesiųDienos();
    }
 // -----------------------------------------------------------------------------
   /**
     * Sukuriamas skaičių masyvas, kuris visas atspausdinamassu for ciklu.
     * Po to masyvas surūšiuojamas ir vėl pateikiamas analizei.
     */
    public  void met1MasyvasSpausdinimui(){
        System.out.println("===== metodas1 Skaičių masyvas spausdinimui ");
        int skaičiai[]={9,88,777,6666,55555,4444,333,22,1};
        for (int sk : skaičiai) {
            System.out.print(" "+sk);
        }
        System.out.println("\nO dabar masyvą surūšiuosime");
        Arrays.sort(skaičiai);
        for (int sk : skaičiai) System.out.print(" "+sk);
        System.out.println("\n Dabar pasinaudosime Arrays metodu toString");
        System.out.println(Arrays.toString(skaičiai));
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Spausdinamas sunumeruotas statinis String masyvas apie automarkes.
     * Antrojo ciklo atveju tekstai pateikiami didžiosiomis raidėmis.
     */
    public  void met2StringMasyvas(){
        System.out.println("===== metodas2 String masyvas ");
        int nr=0;  // numeruosime eilutes
        for(String a: automarkės)
            System.out.println(""+(++nr)+"-oji mašina="+a);
        nr=0;
        for(String a: automarkės)
            System.out.println(""+(++nr)+"-OJI MAŠINA="+a.toUpperCase());
        System.out.println(Arrays.toString(automarkės));
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Spausdinamas vien tik dvimatis masyvas apie automodelius.
     */
    public  void met3DvimatisMasyvas(){
        System.out.println("===== metodas3 Dvimatis masyvas ");
        for(String[] am: automodeliai){  // ciklas per 2mačio eilutes
            for(String a: am)            // ciklas eiluteje
                System.out.print(a+" ");
            System.out.println("   Viso "+am.length+" pavadinimai");
        }
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Sinchroniškai spausdinamas vienmatis masyvas ir jam
     * atitinkanti dvimačio masyvo eilutė.
     */
    public  void met4MasyvaiKartu(){
        System.out.println("===== metodas4 Vienmatis ir dvimatis kartu");
        int ai=0;   // automatkės indeksas
        for(String[] am: automodeliai){
            System.out.print(automarkės[ai++]+" : ");
            for(String a: am)
              System.out.print(a+" ");
            System.out.println("   Viso "+am.length+" pavadinimai");
        }
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Sukuriamas mėnesio dienų masyvas. Jis klonuojmas į keliamuosius ir
     * keičiamas vasario dienų skaičius. Po to šie masyvai spausdinami.
     */
    public void met5MėnesiųDienos(){
        System.out.println("===== metodas5 MėnesiųDienos ");
        int[] mDienos={31,28,31,30,31,30,31,31,30,31,30,31};
        int[] keliamieji=mDienos.clone();
        keliamieji[1]=29;
        int mėnesioNr=0;
        int visoD=0;        
        for (int md : mDienos){
            System.out.printf("Mėnuo %2d Dienų sk. %2d Nuo metų pradžios %3d\n",
                    ++mėnesioNr, md, (visoD+=md));
        }
        System.out.println("========== Toliau spausdinami keliamieji metai");
        mėnesioNr=0;
        visoD=0;
        for (int md : keliamieji){
            System.out.printf("Mėnuo %2d Dienų sk. %2d nuo metų pradžios %3d\n",
                    ++mėnesioNr, md, visoD+=md);
        }
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Sukuriate savo pasirinktus masyvus ir atliekate operacijas su jais
     */
    public void met6(){
        System.out.println("===== metodas6 ... ");

        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Sukuriate savo pasirinktus masyvus ir atliekate operacijas su jais
     */
    public void met7(){
        System.out.println("===== metodas7 ... ");

        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
}
