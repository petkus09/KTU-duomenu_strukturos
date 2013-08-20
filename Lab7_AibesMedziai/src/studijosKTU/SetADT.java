package studijosKTU;

//Interfeisu aprašomas Aibės ADT.
public interface SetADT<Data> extends Iterable<Data> {

    /**
     * Patikrinama ar aibė tuščia.
     * @return Grąžinama true, jei aibė tuščia.
     */
    boolean isEmpty();
    /**
     * Grąžinamas aibėje esančių elementų kiekis.
     * @return Grąžinamas aibėje esančių elementų kiekis.
     */
    int size();

    /**
     * Išvaloma aibė.
     */
    void clear();

    /**
     * Aibė papildoma nauju elementu ir grąžinama true.
     * @return Aibė papildoma nauju elementu ir grąžinama true.
     */
    boolean add(Data data);

    /**
     * Pašalinamas elementas data iš aibės.
     * @return Gražinama true, pašalinus elementą iš aibės.
     */
    boolean remove(Data data);
      
    /**
     * Patikrinama ar aibėje egzistuoja elementas data.
     * @return Grąžinama true, jei aibėje egzistuoja elementas data.
     */
    boolean contains(Data data);
    /**
     * Grąžinamas aibės elementų masyvas.
     * @return Grąžinamas aibės elementų masyvas.
     */
    Object[] toArray();
}
