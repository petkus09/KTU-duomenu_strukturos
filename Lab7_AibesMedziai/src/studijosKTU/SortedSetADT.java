package studijosKTU;

import Lab7Petkus.BookClass;
import java.util.*;

public interface SortedSetADT<Data> extends SetADT<Data> {

    /**
     * Grąžinamas aibės poaibis iki elemento data.
     * @return Grąžinamas aibės poaibis iki elemento data.
     */
    SetADT<Data> headSet(Data data);

    /**
     * Grąžinamas aibės poaibis nuo elemento data1 iki data2.
     * @return Grąžinamas aibės poaibis nuo elemento data1 iki data2.
     */
    SetADT<Data> subSet(Data data1, Data data2);

    /**
     * Grąžinamas aibės poaibis iki elemento data.
     * @return Grąžinamas aibės poaibis nuo elemento data.
     */
    SetADT<Data> tailSet(Data data);

    /**
     * Grąžinamas atvirkštinis iteratorius.
     * @return Grąžinamas atvirkštinis iteratorius.
     */
    Iterator<Data> descendingIterator();
    
    int MedzioDydis();
    
    int SkoluIsmusinejimas(String data);
}