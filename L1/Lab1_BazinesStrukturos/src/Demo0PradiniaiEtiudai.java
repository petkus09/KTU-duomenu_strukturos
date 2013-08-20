import java.util.Arrays;
/**
 * Tai yra klasė, turinti tik statinius metodus.
 * Joje išbandomos ir demonstruojamos Java kalbos savybės,
 * parodomi panašumai su C++ kalba, o taip pat ir Javos privalumai.
 * Klasėje nenaudojamas duomenų įvedimas iš failo,
 * metodų veikimas yra išbandomas, paduodant jau užpildytus masyvus.
 *
 * @author Eimutis Karčiauskas, KTU programinės įrangos katedra
 */
public class Demo0PradiniaiEtiudai {
// -----------------------------------------------------------------------------
    /**
     * Nagrinėjamos operacijos, kurių pagalba peržiūrimi masyvai.
     * Demonstruojamas darbas su pilnai užpildytais masyvais.
     */
    public void elementųSuma(int[] a) {
        System.out.println("===== int skaičių masyvo suma ========");
        // klasikinis peržiūros varijantas
        int n = a.length;
        int suma = 0;
        for (int i = 0; i < n; i++) {
            suma += a[i];
        }
        // varijantas su ciklu for-each
        int išViso = 0;
        for (int a1 : a) išViso += a1;     
        
        System.out.println("Pradinis masyvas = " + Arrays.toString(a));
        System.out.println(" Suma pagal 1 varijantą= " + suma);
        System.out.println(" Suma pagal 2 varijantą= " + išViso);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Demonstruojama galimybė to paties metodo vardo su kito tipo parametru
     */
    public void elementųSuma(double[] a) {
        System.out.println("===== double skaičių masyvo suma ========");
        // klasikinis peržiūros varijantas
        int n = a.length;
        double suma = 0;
        for (int i = 0; i < n; i++) {
            suma += a[i];
        }
        // varijantas su ciklu for-each
        double išViso = 0;
        for (double skaičius : a) išViso += skaičius;
        
        System.out.println("Pradinis masyvas = " + Arrays.toString(a));
        System.out.println(" Suma pagal 1 varijantą= " + suma);
        System.out.println(" Suma pagal 2 varijantą= " + išViso);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Nagrinėjama min radimo operacija, kurios metu peržiūrimi masyvai.
     * Demonstruojamas darbas su pilnai užpildytu int masyvu.
     */
    public void minElementas(int[] a) {
        System.out.println("===== min skaičius ir jo indeksas ========");
        // klasikinis peržiūros varijantas
        int min = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i]<min) {
                min = a[i];
                index = i;
            }
        }      
        System.out.println("Pradinis masyvas = " + Arrays.toString(a));
        System.out.println(" Min elementas = " + min +
                           " jo indeksas = " + index);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    /**
     * Nagrinėjama max radimo operacija, kurios metu peržiūrimi masyvai.
     * Demonstruojamas darbas su pilnai užpildytu double masyvu.
     */
    public void maxElementas(double[] a) {
        System.out.println("===== max skaičius ir jo indeksas ========");
        // klasikinis peržiūros varijantas
        double max = Double.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i]>max) {
                max = a[i];
                index = i;
            }
        }
        System.out.println("Pradinis masyvas = " + Arrays.toString(a));
        System.out.println(" Max elementas = " + max +
                           " jo indeksas = " + index);
        System.out.println("Patikrinkite, ar tokių rezultatų tikėjotės ??\n");
    }
// -----------------------------------------------------------------------------
    public static void metodųIšbandymas(){
        System.out.println("****** Klasė Demo0PradiniaiEtiudai **********");
        int[] a1 = {2, 7, -11};
        int[] a2 = {100, 1, 2, 3, 4, 5, 6, 7};
        int[] a3 = { -11};  // tikrinsime su vieno elemento masyvu
        int[] a4 = { };     // galimas ir tuščias masyvas
        double[] b1 = {1.1, 2.2, 3.3};
        double[] b2 = {1/5, 2/5, 1.0/3, 2.0/3, 1/7.0, 2/7.0, 3/7.0, 4/7.0};

        Demo0PradiniaiEtiudai p = new Demo0PradiniaiEtiudai();
        p.elementųSuma(a1);
        p.elementųSuma(a2);
        p.elementųSuma(a3);
        p.elementųSuma(a4);
        p.elementųSuma(b1);
        p.elementųSuma(b2);

        p.minElementas(a1);
        p.minElementas(a2);
        p.minElementas(a3);
        p.minElementas(a4);
        p.maxElementas(b1);
        p.maxElementas(b2);
    }
// -----------------------------------------------------------------------------
}
