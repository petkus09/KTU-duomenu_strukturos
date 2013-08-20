/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package studijosKTU;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author eimutis
 */
public class ListKTUx<Data extends DataKTU> extends ListKTU<Data> {
    private Data baseObj;       // bazinis objektas skirtas naujų kūrimui

    public ListKTUx(Data baseObj) { // konstruktorius su bazinio objekto
        this.baseObj = baseObj;     // fiksacija dėl naujų elementų kūrimo
    }
    public void add(String dataString) {        // sukuria elementą iš String
        add((Data) baseObj.create(dataString)); // ir įdeda jį į pabaigą
    }
    public void load(String fName) {//suformuoja sąrašą iš fName failo
        clear();
        if(fName.length()==0)return;
        if(baseObj==null){          // elementų kūrimui reikalingas baseObj
            Ks.ern("Naudojant load-metodą, "+
                "reikia taikyti konstruktorių = new ListKTU(new Data())");
            System.exit(0);
        }
        try {
            (new File(Ks.getDataFolder())).mkdir();
            String fN = Ks.getDataFolder() + File.separatorChar + fName;
            BufferedReader fReader =
                    new BufferedReader(new FileReader(new File(fN)));
            String dLine;
            while ((dLine = fReader.readLine()) != null) {
                add(dLine);
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
    public void save(String fName) {    // išsaugoja sąrašą faile fName
        PrintWriter fWriter = null;     // tekstiniu formatu
        try {                           // tinkamu vėlesniam skaitymui
            // jei vardo nėra - failas neformuojamas
            if (fName.equals("")) return;

            String fN = Ks.getDataFolder() + File.separatorChar + fName;
            fWriter = new PrintWriter(new FileWriter (new File(fN)));
            for (Data d1 = super.get(0); d1 != null; d1=super.getNext()) {
                fWriter.println(d1.toString());
            }
            fWriter.close();
        } catch (IOException e) {
            Ks.ern("!!! Klaida formuojant " + fName + " failą.");
            System.exit(0);
        }
    }
    public void println() {  // sąrašas spausdinamas į Ks.oln("");
        int eilNr=0;
        if (super.isEmpty()){
            Ks.oun("Sąrašas yra tuščias");
        }else
           for (Data d1 = super.get(0); d1 != null; d1=super.getNext()) {
           String printData=String.format("%3d: %s ", eilNr++, d1.toString());
           String badData=d1.validate();
           Ks.oun (printData + badData);
        }
        Ks.oun("****** Bendras elementų kiekis yra "+super.size());
    }
    public void println(String title) { // spausdinant galima nurodyti antraštę
        Ks.oun("========"+title+"=======");
        println();
        Ks.oun("======== Sąrašo pabaiga =======");
    }

}
