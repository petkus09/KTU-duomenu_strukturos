package Lab8Petkus;

import java.io.BufferedReader;
import studijosKTU.MapADT;
import java.util.Arrays;
import javax.swing.table.AbstractTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import studijosKTU.MapKTU;

/**
 *
 * @author ygis
 */
// MapADT interfeisą maišos lentele realizuojanti klasė
public class LentelėMaišai<Key, Value> implements MapADT<Key, Value> {
    
    //Maišos lentelė
    private Entry<Key, Value>[] table;
    //Lentelėje esančių raktas - reikšmė porų kiekis
    private int size = 0;
    //Apkrovimo faktorius
    private float loadFactor;
    //nėra grandinėlių
    private final int maxChainSize = 1;
    //Permaišymų kiekis
    private int rehashesCounter = 0;

    //Konstruktoriai
    public LentelėMaišai() {
        this(20);
    }

    public LentelėMaišai(int initialCapacity) {
        this(initialCapacity, 0.75f);
    }

    public LentelėMaišai(int initialCapacity, float loadFactor) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("talpa privalo būti teigiama");
        }
        if ((loadFactor <= 0) || (loadFactor > 1)) {
            throw new IllegalArgumentException("Apkrovimo faktoriaus intervalas [0;1)");
        }
        table = new Entry[initialCapacity];
        this.loadFactor = loadFactor;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public void clear() {
        Arrays.fill(table, null);
        size = 0;
        rehashesCounter = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private int hash(Key key) {
        return Math.abs(key.hashCode()) % table.length;
    }

    private void rehash() {
        LentelėMaišai<Key, Value> naujaLentele =
                new LentelėMaišai<Key, Value>(table.length * 2, loadFactor);
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                Entry<Key, Value> n = table[i];
                naujaLentele.put(n.key, n.value);
            }
        }
        table = naujaLentele.table;
        rehashesCounter++;
    }

    @Override
    public Value remove(Key key) {
        if (key == null) {
            throw new NullPointerException();
        }
        int index = hash(key);
        if (contains(key)) {
            Value salinamasis = table[index].value;
            table[index] = null;
            size--;
            return salinamasis;
        }
        return null;
    }

    public boolean containsValue(Value value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                if (table[i].value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void load(String fName) {//suformuoja sąrašą iš fName failo
        clear();
        if (fName.length() == 0) {
            return;
        }
        try {
            (new File("Duomenys")).mkdir();
            String fN = "Duomenys" + File.separatorChar + fName;
            BufferedReader fReader =
                    new BufferedReader(new FileReader(new File(fN)));
            String dLine;
            while ((dLine = fReader.readLine()) != null) {
                put(dLine);
            }
            fReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Duomenų failas " + fName + " nerastas");
            System.exit(0);
        } catch (IOException e) {
            System.err.println("Failo " + fName + " skaitymo klaida");
            System.exit(0);
        }
    }

    public Value put(String dataString) {        // sukuria elementą iš String
        return put((Key) dataString, (Value) dataString);
    }

    public void save(String fName) {    // išsaugoja sąrašą faile fName
        PrintWriter fWriter = null;     // tekstiniu formatu
        try {                           // tinkamu vėlesniam skaitymui
            // jei vardo nėra - failas neformuojamas
            if (fName.equals("")) {
                return;
            }
            String fN = "Duomenys" + File.separatorChar + fName;
            fWriter = new PrintWriter(new FileWriter(new File(fN)));
            String str;
            for (int i = 0; i < getModel("=").getRowCount(); i++) {
                for (int j = 0; j < getModel("=").getColumnCount(); j++) {
                    if (getModel("").getValueAt(i, j) == null) {
                        str = "";
                    } else {
                        str = getModel("").getValueAt(i, j).toString();
                    }
                    if (!str.equals("")) {
                        fWriter.println(str);
                    }
                }
            }
            fWriter.close();
        } catch (IOException e) {
            System.err.println("!!! Klaida formuojant " + fName + " failą.");
            System.exit(0);
        }
    }

    public int getTableCapacity() {
        return table.length;
    }

    public int getRehashesCounter() {
        return rehashesCounter;
    }
    
    public int getMaxChainSize() {
        return maxChainSize;
    }

    /**
     * Skaičiuojamas maišos lentelės masyvo tuščių elementų kiekis.
     * 	
     *
     */
    public int tusciuKiekis() {
        int kiekis = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                kiekis++;
            }
        }
        return kiekis;
    }

     /**
     * Skaičiuojamas maišos lentelės masyvo užpildytų elementų kiekis.
     * 	
     *
     */
    public int uzpildytuKiekis() {
        int kiekis = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                kiekis++;
            }
        }
        return kiekis;
    }

    @Override
    public Value get(Key key) {
        if (key == null) {
            throw new NullPointerException();
        }
        int index = hash(key);
        if (table[index] != null) {
            return table[index].value;
        }
        return null;
    }

    // Maišos lentelė papildoma nauja pora,
    // kolizijas sprendžiant dvigubos maišos atviros adresacijos metodu
    @Override
    public Value put(Key key, Value value) {
        if ((key == null) || (value == null)) {
            throw new NullPointerException();
        }
        int index = findPosition(key);
        if (index == -1) {
            rehash();
            put(key, value);
            return value;
        }
        if (table[index] == null) {
            table[index] = new Entry(key, value);
            size++;
            if (size > table.length * loadFactor) {
                rehash();
            } else {
                table[index].value = value;
            }
        }
        return value;
    }

    // Dvigubos maišos atviros adresacijos metodas
    private int findPosition(Key key) {
        int index = hash(key);
        int index0 = index;
        for (int i = 0; i < table.length; i++) {
            if (table[index] == null || table[index].key.equals(key)) {
                return index;
            }
            index = (index0 + ((i + 1) * hash2(key))) % table.length;
        }
        return -1;
    }

    // Antroji maišos funkcija
    private int hash2(Key key) {
        return 11 - (Math.abs(key.hashCode()) % 11);
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                result += table[i].toString();
            }
        }
        return result;
    }

    /**
     * Grąžina maišos lentelės modelį, skirtą atvaizdavimui JTable objekte
     * @param delimiter Celės elemento kirtiklis
     * @return Grąžina AbstractTableModel klasės objektą.
     */
    public HashTableModel<Key, Value> getModel(String delimiter) {
        return new HashTableModel<Key, Value>(delimiter);
    }

    public int SkolininkuKiekis(String dataS) {
        int kiekis = 0;
        int n = table.length;
        for (int i = 0; i < n; i++){
            if (table[i] != null){
                String klase = table[i].value.toString();
                BookClass knyga = new BookClass();
                knyga.fromString2(klase);
                if (knyga.Return(dataS))
                    kiekis++;
                }
            }
        return kiekis;
    }
    
    public double vidGrandIlgis() {
        
        return 1;
    }

    //Lentelės modelio klasė
    public class HashTableModel<Key, Value> extends AbstractTableModel {

        private String delimiter;

        public HashTableModel(String delimiter) {
            this.delimiter = delimiter;
        }

        @Override
        public String getColumnName(int col) {
            return (col == 0) ? "#" : "(" + --col + ")";
        }

        @Override
        public Object getValueAt(int row, int col) {
            if (col == 0) {
                return "[" + row + "]";
            } else if (table[row] != null) {
                return table[row].toString();
            }
            return null;
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public int getRowCount() {
            return table.length;
        }

        private String split(String s, String delimiter) {
            int k = s.indexOf(delimiter);
            if (k <= 0) {
                return s;
            }
            return s.substring(0, k);
        }
    }

    class Entry<Key, Value> {

        //Raktas
        Key key;
        //Reikšmė
        Value value;

        Entry() {
        }

        Entry(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}