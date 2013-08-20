package Lab7Petkus;

import Lab7Petkus.*;
import studijosKTU.*;

public class KnyguApskaita {

    public static SetADT<String> KnyguMetai(BookClass[] Knygos) {
        SetADT<BookClass> uni = new BstSetKTU<BookClass>(BookClass.byYear);
        SetADT<String> kart = new BstSetKTU<String>();
        for (BookClass a : Knygos) {
            if (!uni.add(a)) {
                kart.add(Integer.toString(a.getYear()));
            }
        }
        return kart;
    }
}
