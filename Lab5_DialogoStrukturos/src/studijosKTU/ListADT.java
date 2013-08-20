package studijosKTU;
/**
 * Tai pirmosios kolekcijų duomenų struktūros klasės interfeisas,
 * apibrėžiantis operacijas, kurių pagalba atskiri objektai apjungiami
 * į vieną visumą - sąrašą.
 * Objektai, kurie bus dedami į sąrašą, turi tenkinti interfeisą dataKTU.
 *
 * @užduotis Peržiūrėkite ir išsiaiškinkite pateiktus metodus.
 *
 * @author Eimutis Karčiauskas, KTU programinės įrangos katedra
 */
public interface ListADT<Data> {

    /**
     * Appends the specified element to the end of this list.
     * @param data element to be appended to this list
     */
    boolean add(Data data);
    boolean add(int k, Data data);
    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    Data remove(int k);
    int size();
    boolean isEmpty();
    /**
     * Removes all of the elements from this list.
     */
    void clear();
    /**
     * Positional Access Operations.
     * Returns the element at the specified position in this list.
     *
     * @param k index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    Data get(int k);
    Data getNext();
    /**
     * Replaces the element at the specified position in this list with the
     * specified element.
     *
     * @param k index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    Data set(int k, Data d);
}
