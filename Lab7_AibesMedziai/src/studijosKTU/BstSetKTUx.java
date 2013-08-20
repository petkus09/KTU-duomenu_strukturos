package studijosKTU;

import Lab7Petkus.BookClass;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;

public class BstSetKTUx<Data extends DataKTU & Comparable<Data>> extends BstSetKTU<Data>
        implements SortedSetADTx<Data> {

    private Data baseObj;       // bazinis objektas skirtas naujų kūrimui

    public BstSetKTUx(Data baseObj) { // konstruktorius su bazinio objekto
        super();
        this.baseObj = baseObj;     // fiksacija dėl naujų elementų kūrimo
    }

    public BstSetKTUx(Data baseObj, Comparator<Data> c) { // konstruktorius su bazinio objekto
        super(c);
        this.baseObj = baseObj;     // fiksacija dėl naujų elementų kūrimo
    }

    @Override
    public void add(String dataString) {        // sukuria elementą iš String
        super.add((Data) baseObj.create(dataString)); // ir įdeda jį į pabaigą
    }

    @Override
    public void load(String fName) {//suformuoja sąrašą iš fName failo
        clear();
        if (fName.length() == 0) {
            return;
        }
        if (baseObj == null) {          // elementų kūrimui reikalingas baseObj
            Ks.ern("Naudojant load-metodą, "
                    + "reikia taikyti konstruktorių = new SetBstKTU(new Data())");
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
            Ks.ern("Tinkamas duomenų failas " + fName + " nerastas");
        } catch (IOException e) {
            Ks.ern("Failo " + fName + " skaitymo klaida");
        }
    }
//==============================================================================
/* Papildomas metodas, išvedantis aibės elementus į vieną String eilutę.
     * String eilutė formuojama atliekant elementų postūmį nuo krašto,
     * priklausomai nuo elemento lygio medyje. Galima panaudoti spausdinimui į
     * ekraną ar failą tyrinėjant medžio algoritmų veikimą.
     *
     * @author E. Karčiauskas
     */
//==============================================================================
    // Medžio vaizdavimas simboliais, žiūr.: unicode.org/charts/PDF/U2500.pdf
    // Tai 4 galimi terminaliniai simboliai medžio šakos gale
    private static final String[] term = {"\u2500", "\u2534", "\u252C", "\u253C"};
    private static final String rightEdge = "\u250C";
    private static final String leftEdge = "\u2514";
    private static final String endEdge1 = "\u25AA";
    private static final String endEdge2 = "\u25CF";
    private static final String vertical = "\u2502  ";
    private String horizontal;
    private String endEdge;

    @Override
    public String toVisualizedString(String treeDrawType, String dataCodeDelimiter) {
        horizontal = term[0] + term[0];
        endEdge = (treeDrawType.equals("Kvadratas")) ? endEdge1 : endEdge2;
        return "\n" +toTreeDraw(getRoot(), ">", "", dataCodeDelimiter);
    }

    private String toTreeDraw(BstNode<Data> node, String edge, String indent, String dataCodeDelimiter) {
        if (node == null) {
            return "";
        }
        String step = (edge.equals(leftEdge)) ? vertical : "   ";
        String result = toTreeDraw(node.right, rightEdge, indent + step, dataCodeDelimiter);
        int t = (node.right != null) ? 1 : 0;
        t = (node.left != null) ? t + 2 : t;
        result += indent + edge + horizontal + term[t] + endEdge
                + split(node.data.toString(), dataCodeDelimiter) + "\n";
        step = (edge.equals(rightEdge)) ? vertical : "   ";
        result += toTreeDraw(node.left, leftEdge, indent + step, dataCodeDelimiter);
        return result;
    }

    private String split(String s, String dataCodeDelimiter) {
        int k = s.indexOf(dataCodeDelimiter);
        if (k <= 0) {
            return s;
        }
        return s.substring(0, k);
    }
    
    
}