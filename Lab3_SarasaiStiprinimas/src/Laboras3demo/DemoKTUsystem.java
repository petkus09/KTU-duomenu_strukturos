package Laboras3demo;
import studijosKTU.*;
/**
 *
 * @author eimutis
 */
public class DemoKTUsystem {
    public static void metodoParinkimas(){
//        demoDialogas();
        demoSkaičiavimai1();
//        demoSkaičiavimai2();
//        demoSkaičiavimai3();
    }
    static void demoDialogas(){
        String vardas = Ks.giveString("Koks Jūsų vardas? ");
        String pavardė = Ks.giveString("Kokia Jūsų pavardė? ");
        int amžius = Ks.giveInt("Koks Jūsų amžius? ", 16, 24);
        double mok = Ks.giveDouble("Kiek pageidaujate mokėti?",15.6,24.8);

        Ks.oun("Naujas studijų klausytojas įregistruotas");
        Ks.oun("Duomenys yra "+vardas+" "+ pavardė);
        Ks.oun("Gimimo metai yra "+(2010-amžius));
        Ks.oun("Už mokslą mokėsite "+ mok +" Lt" );
    }
    static void demoSkaičiavimai1(){
        do{
            double a = Ks.giveDouble("Įveskite skaičių A ", 10, 120);
            double b = Ks.giveDouble("Įveskite skaičių B ");
            Ks.oun("Rezultatas A+B= " +(a+b));
            Ks.oun("Rezultatas A-B= " +(a-b));
            Ks.oun("Rezultatas A*B= " +(a*b));
            Ks.oun("Rezultatas A/B= " +(a/b));
        }while(Ks.giveInt("Ar norite tęsti 0/1 ?")==1);
    }
    static void demoSkaičiavimai2(){
        String dialogas = "Pasirinkite skaičiavimus: "+
                "1-sin(x); 2-cos(x); 3-tg(x); 0-baigti;";
//                "1-sin(x); 2-cos(x); 3-tg(x); 0-baigti;";
        int varNr;
        while((varNr = Ks.giveInt(dialogas)) != 0){
            double x = Ks.giveDouble("Įveskite kampo dydį laipsniais ",0,180);
            double r=0;  // rezultatas
            switch (varNr) {
                case 1: r=Math.sin(Math.toRadians(x)); break;
                case 2: r=Math.cos(Math.toRadians(x)); break;
                case 3: r=Math.tan(Math.toRadians(x));
            }
            Ks.oun("Rezultatas = " + r);
        }
    }
    static void demoSkaičiavimai3(){

    }
}
