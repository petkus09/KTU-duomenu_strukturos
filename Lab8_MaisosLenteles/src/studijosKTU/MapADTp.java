/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studijosKTU;

import studijosKTU.MapKTU.HashTableModel;

/**
 *
 * @author matas
 */
public interface MapADTp<Key, Value> extends MapADT<Key, Value> {

    /**
     * Grąžina maksimalų grandinėlės ilgį.
     * 	
     * @return Maksimalus grandinėlės ilgis.
     */
    public int getMaxChainSize();

    /**
     * Grąžina maišos lentelę formuojant įvykusių permaišymų kiekį.
     *
     * @return Permaišymų kiekis.
     */
    public int getRehashesCounter();

    /**
     * Grąžina maišos lentelės talpą.
     * 	
     * @return Maišos lentelės talpa.
     */
    public int getTableCapacity();

    /**
     * Grąžina paskutinės papildytos grandinėlės indeksą.
     * 	
     * @return Paskutinės papildytos grandinėlės indeksas.
     */
    public int getLastUpdatedChain();

    /**
     * Grąžina grandinėlių kiekį.
     * 	
     * @return Grandinėlių kiekis.
     */
    public int getChainsCounter();

    /**
     * Grąžina maišos lentelės modelį, skirtą atvaizdavimui JTable objekte
     * @param delimiter Celės elemento kirtiklis
     * @return Grąžina AbstractTableModel klasės objektą.
     */
    public HashTableModel getModel(String delimiter);
}
