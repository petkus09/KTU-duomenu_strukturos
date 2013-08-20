import java.util.Arrays;
/*
 * Reikia suprogramuoti pasirinktinai vieną iš savo pirmo kurso užduočių
 * Reikia galimai daugiau išnaudoti C++ ir Javos panašumus,
 * galima netgi perkelti tam tikrus teksto fragmentus.
 *
 * Sudarytų metodų tikrinimui įvedimas iš failo nenaudojamas.
 * Metodų veikimas tikrinamas specialiu bandymo metodu.
 *
 * @author Eimutis Karčiauskas
 */

// -----------------------------------------------------------------------------
//  Sąlygos pavyzdys *** Duotas realių skaičių masyvas. Rasti:
//    1. kiek skaičių yra daugiau už vidurkį;
//    2. didžiausą ir mažiausią skaičių sukeisti vietomis.

public class PirmoKursoUzdavinys {
// -----------------------------------------------------------------------------
    public int kiekDaugiauUžVidurkį(double[] a) {
        double suma = 0;
        for (double a1 : a) suma += a1;
        double vidurkis = suma / a.length;
        int kiek = 0;
        for (double a1 : a) {
            if (a1 > vidurkis)
                kiek++ ;
        }
        return kiek;
    }
// -----------------------------------------------------------------------------
    public void sukeistiDidžiausąMažiausią(double[] a) {
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        int indMax = 0;
        int indMin = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i]>max) {
                max = a[i]; indMax = i;
            }
            if (a[i]<min) {
                min = a[i]; indMin = i;
            }
        }
        double temp = a[indMin];
        a[indMin] = a[indMax];
        a[indMax] = temp;
    }
// -----------------------------------------------------------------------------
    public static void metodųIšbandymas(){
        System.out.println("****** Klasė PirmoKursoUzdavinys **********");
        double[] b1 = {1.1, 2.2, 4.4};
        double[] b2 = {1/5, 1.0/3, 2.0/3, 1/6.0, 2/6.0, 3/6.0 };
        PirmoKursoUzdavinys p = new PirmoKursoUzdavinys();
        System.out.println("Pradinis masyvas = "  + Arrays.toString(b1));
        int k = p.kiekDaugiauUžVidurkį(b1);
        p.sukeistiDidžiausąMažiausią(b1);
        System.out.println("Už vidurkį daugiau = " + k);
        System.out.println("Sukeistas masyvas = "  + Arrays.toString(b1));

        System.out.println("Pradinis masyvas = "  + Arrays.toString(b2));
        k = p.kiekDaugiauUžVidurkį(b2);
        p.sukeistiDidžiausąMažiausią(b2);
        System.out.println("Už vidurkį daugiau = " + k);
        System.out.println("Sukeistas masyvas = "  + Arrays.toString(b2));
    }
// -----------------------------------------------------------------------------
}
