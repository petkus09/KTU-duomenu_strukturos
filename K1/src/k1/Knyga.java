package k1;

import java.util.Comparator;

public class Knyga implements Comparable {
    private String leidykla;
    private String autorius;
    private int puslapiu;
    private double kaina;

    public Knyga(String leidykla, String autorius, int puslapiu, double kaina) {
        this.leidykla = leidykla;
        this.autorius = autorius;
        this.puslapiu = puslapiu;
        this.kaina = kaina;
    }

    public String getLeidykla() {
        return leidykla;
    }

    public String getAutorius() {
        return autorius;
    }

    public int getPuslapiu() {
        return puslapiu;
    }

    public double getKaina() {
        return kaina;
    }
    
    @Override    
    public String toString() {
        return String.format("%-15s %-15s %6d %7.2f", leidykla, autorius, puslapiu, kaina);
    }
    
    @Override
    public int compareTo(Object k){
        double kainaKita = ((Knyga)k).getKaina();
        if (kaina<kainaKita) return -1;
        if (kaina>kainaKita) return +1;
        return 0;
    }
    
     public static Comparator <Knyga>pagalKelis = new Comparator<Knyga>() {
     @Override
     
     public int compare(Knyga a1, Knyga a2) {
         int cmp = a1.getAutorius().compareTo(a2.getAutorius());
         if (cmp != 0) return cmp;
         if(a1.getPuslapiu()>a2.getPuslapiu()) return 1;
         if(a1.getPuslapiu()<a2.getPuslapiu()) return -1;
         if(a1.getKaina()>a2.getKaina()) return 1;
         if(a1.getKaina()<a2.getKaina()) return -1;
         return 0;
     }
     
     };
      
}
