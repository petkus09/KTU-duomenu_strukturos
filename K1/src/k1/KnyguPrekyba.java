package k1;

import java.util.Collections;
import java.util.LinkedList;

public class KnyguPrekyba {
    LinkedList<Knyga> knygos = new LinkedList<Knyga>();
    LinkedList<Knyga> naujas = new LinkedList<Knyga>();
    
    
    public void metodai() {
        masyvas();
        rastiPlonasKnygas(100);
        tvarkaPagalKaina();
        tvarkaPagalAutPslLt();
           
    }
    
    public void masyvas() {

        knygos.add(new Knyga("Vaga", "Vaižgantas", 49, 19.55));
        knygos.add(new Knyga("Vaga", "Remarkas", 86, 27.55));
        knygos.add(new Knyga("AlmaLitera", "Avyžius", 207, 23.55));
        knygos.add(new Knyga("Vaga", "Avyžius", 127, 9.55));
        knygos.add(new Knyga("Vaga", "Baltušis", 99, 19.55));
        knygos.add(new Knyga("Mintis", "Remarkas", 123, 7.55));
        knygos.add(new Knyga("Mintis", "Remarkas", 207, 19.55));
        knygos.add(new Knyga("Mintis", "Avyžius", 127, 8.55));
        knygos.add(new Knyga("Mintis", "Avyžius", 384, 19.55));       
        
    }
    
    public void rastiPlonasKnygas(int riba) {
        for(Knyga k : knygos) {
            if(k.getPuslapiu() > riba)
                naujas.add(k);
        }
        System.out.println("Ploniausios:");
        for(Knyga a : naujas)
            System.out.println(a.toString());
        System.out.println("--------------");
    }
    public void tvarkaPagalKaina() {
        Collections.sort(knygos);
        
        System.out.println("Didėjančia kaina:");
        for(Knyga a : knygos)
            System.out.println(a.toString());
        System.out.println("---------------");
    }
    
    public void tvarkaPagalAutPslLt() {
       
     Collections.sort(knygos, Knyga.pagalKelis);
        
        System.out.println("Pagal kelis:");
        for(Knyga a : knygos)
            System.out.println(a.toString());
        System.out.println("---------------");
    }
}
