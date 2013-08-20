/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studijosKTU;

/**
 *
 * @author matas
 */
public interface MapADTx<Key, Value> extends MapADTp<Key, Value> {

    public Value put(String dataString);

    public void load(String fName);

    public void save(String fName);

    public void println();

    public void println(String title);
    
    public double vidGrandIlgis();
    
    public int tusciuKiekis();
    
    public boolean containsValue(Value value);
    
    public int SkolininkuKiekis(String data);
    
}
