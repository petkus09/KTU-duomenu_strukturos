package studijosKTU;

public interface SortedSetADTx<Data> extends SortedSetADT<Data> {

    public void add(String dataString);

    public void load(String fName);

    public String toVisualizedString(String treeDrawType, String dataCodeDelimiter);

    public Object clone();
   
    
}