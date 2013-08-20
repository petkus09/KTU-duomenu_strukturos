package studijosKTU;

/**
 * @author matas
 */
public interface MapADT<Key, Value> {

    /**
     * Patikrinama ar atvaizdis yra tuščias.
     * @return true, jei tuščias	
     */
    public boolean isEmpty();

    /**
     * Grąžinamas atvaizdyje esančių porų kiekis.
     * 	
     */
    public int size();

    /**
     * Išvalomas atvaizdis.
     * 	
     */
    public void clear();

    /**
     * Atvaizdis papildomas nauja pora.
     * 	@param key raktas, 
     *  @param value reikšmė.
     */
    public Value put(Key key, Value value);

    /**
     * Grąžinama atvaizdžio poros reikšmė.
     * 	
     * @param key raktas.
     */
    public Value get(Key key);

    /**
     * Iš atvaizdžio pašalinama pora.
     * 	
     * @param key raktas.
     */
    public Value remove(Key key);

    /**
     * Patikrinama ar atvaizdyje egzistuoja pora su raktu key.
     * 	
     * @param key raktas.
     */
    public boolean contains(Key key);
}