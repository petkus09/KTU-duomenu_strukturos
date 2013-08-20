package studijosKTU;

/*
 * interfeisas, kurį turi tenkinti studentų kuriamos duomenų klasės
 * @author EK
 */
public interface DataKTU {

    DataKTU create(String dataString); // sukuria naują objektą iš eilutės

    String validate();                // patikrina objekto reikšmes

    void fromString(String e);         // suformuoja  objektą iš eilutės

    @Override
    String toString();                 // atvaizduoja objektą į eilutę
}
