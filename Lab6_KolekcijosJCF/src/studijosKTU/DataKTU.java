package studijosKTU;
/*
 * interfeisas, kurį turi tenkinti studentų kuriamos duomenų klasės
 * @author EK
 */
//extends Comparable<DataKTU>
public interface DataKTU extends Comparable{
    DataKTU create(String dataString); // sukuria naują objektą iš eilutės
    String validate();                 // patikrina objekto reikšmes
    void fromString(String e);         // suformuoja reikšmes iš eilutės
    @Override
    String toString();                 // atvaizduoja objektą į eilutę
}
