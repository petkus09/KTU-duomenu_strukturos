package studijosKTU;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MapKTUx<Key, Value> extends MapKTU<Key, Value> implements MapADTx<Key, Value> {

    private Key baseKey;       // bazinis objektas skirtas naujų kūrimui
    private Value baseObj;       // bazinis objektas skirtas naujų kūrimui

    public MapKTUx(Key baseKey, Value baseObj) { // konstruktorius su bazinio objekto
        this(baseKey, baseObj, DEFAULT_HASH_TYPE);
    }

    public MapKTUx(Key baseKey, Value baseObj, HashType ht) { // konstruktorius su bazinio objekto
        this(baseKey, baseObj, DEFAULT_INITIAL_CAPACITY, ht);
    }

    public MapKTUx(Key baseKey, Value baseObj, int initialCapacity, HashType ht) { // konstruktorius su bazinio objekto
        this(baseKey, baseObj, initialCapacity, DEFAULT_LOAD_FACTOR, ht);
    }

    public MapKTUx(Key baseKey, Value baseObj, int initialCapacity, float loadFactor, HashType ht) { // konstruktorius su bazinio objekto
        super(initialCapacity, loadFactor, ht);
        this.baseKey = baseKey;     // fiksacija dėl naujų elementų kūrimo
        this.baseObj = baseObj;     // fiksacija dėl naujų elementų kūrimo
    }

    //Jei turime failą, kuriame saugomos raktas-reikšmė poros, šia vieta reikėtų
    //atitinkamai modifikuoti
    @Override
    public Value put(String dataString) {        // sukuria elementą iš String
        return put((Key) dataString, (Value) dataString);
    }

    @Override
    public void load(String fName) {//suformuoja sąrašą iš fName failo
        clear();
        if (fName.length() == 0) {
            return;
        }
        if ((baseKey == null) || (baseObj == null)) {          // elementų kūrimui reikalingas baseObj
            Ks.ern("Naudojant load-metodą, "
                    + "reikia taikyti konstruktorių = new ListKTU(new Data())");
            System.exit(0);
        }
        try {
            (new File(Ks.getDataFolder())).mkdir();
            String fN = Ks.getDataFolder() + File.separatorChar + fName;
            BufferedReader fReader =
                    new BufferedReader(new FileReader(new File(fN)));
            String dLine;
            while ((dLine = fReader.readLine()) != null) {
                put(dLine);
            }
            fReader.close();
        } catch (FileNotFoundException e) {
            Ks.ern("Duomenų failas " + fName + " nerastas");
            System.exit(0);
        } catch (IOException e) {
            Ks.ern("Failo " + fName + " skaitymo klaida");
            System.exit(0);
        }
    }

    @Override
    public void save(String fName) {    // išsaugoja sąrašą faile fName
        PrintWriter fWriter = null;     // tekstiniu formatu
        try {                           // tinkamu vėlesniam skaitymui
            // jei vardo nėra - failas neformuojamas
            if (fName.equals("")) {
                return;
            }
            String fN = Ks.getDataFolder() + File.separatorChar + fName;
            fWriter = new PrintWriter(new FileWriter(new File(fN)));
            for (int i = 0; i < getModel("=").getRowCount(); i++) {
                for (int j = 0; j < getModel("=").getColumnCount(); j++) {
                    String str = getModel("").getValueAt(i, j).toString();
                    if (!str.equals("")) {
                        fWriter.println(str);
                    }
                }
            }
            fWriter.close();
        } catch (IOException e) {
            Ks.ern("!!! Klaida formuojant " + fName + " failą.");
            System.exit(0);
        }
    }

    @Override
    public void println() {  //Atvaizdis spausdinamas į Ks.ouf("");
        if (super.isEmpty()) {
            Ks.oun("Atvaizdis yra tuščias");
        } else {
            for (int i = 0; i < getModel("=").getRowCount(); i++) {
                for (int j = 0; j < getModel("=").getColumnCount(); j++) {
                    String str = (j == 0) ? "%6s" : "%15s";
                    Ks.ouf(str, getModel("=").getValueAt(i, j) + " |");
                }
                Ks.oufln("");
            }
        }
        Ks.oufln("****** Bendras porų kiekis yra " + super.size());
    }

    @Override
    public void println(String title) { // spausdinant galima nurodyti antraštę
        Ks.ounn("========" + title + "=======");
        println();
        Ks.ounn("======== Atvaizdžio pabaiga =======");
    }
}