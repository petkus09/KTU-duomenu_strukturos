package dialogai;


/**
 *
 * @author KTU
 */
import javax.swing.*;
import java.io.*;

public class AutomobiliuApskaita {
	private String s1;
	private String s2;
	private String s3;

	/**
	 * Tai tik metodo imitacija - panaudokite L2/L3 darbų atrinkimo metodus
	 *
	 * @param taLaukas JTextArea klasės objektas
	 */
	public void pagalKaina() {
		s1 = "Renault	Laguna	1997	50000	1700";
		s2 = "Renault	Megane	2001	20000	3500";
		s3 = "Volga	24	1980	99000	1100";
	}

	/**
	 * Išvedimas į JTextArea lauką
	 * 
	 * @param taLaukas JTextArea klasės objektas
	 */
	public void isvedimas(JTextArea taLaukas) {
		taLaukas.append("\n     Pagal kaina:\n");
		ouSwn(s1, taLaukas);
		ouSwn(s2, taLaukas);
		ouSwn(s3, taLaukas);
	}

	/**
	 * Tai jau turėtų būti naujas sistemines klases Ks metodas
	 *	(arba naujos studijosKTU paketo klasės KsSwing metodas)
	 * @param obj išvedamas objektas
	 * @param ta JTextArea klasės objektas
	 */
    static public void ouSwn(Object obj, JTextArea ta) {
		ta.append(obj.toString() + "\n");
    }
	//-----------------------------------------------------------------------------------

	/**
	 * Failo skaitymas ir jo turinio išvedimas į JTextArea
	 * @param fName File klasės objektas
	 * @param ta JTextArea klasės objektas
	 */
    public void loadAndPrint(File fName, JTextArea ta) {
        try {
            BufferedReader fReader =  new BufferedReader(new FileReader(fName));
            String line;
			ta.append("\n     Duomenys is failo <" + fName.getName() + ">\n");

            while ((line = fReader.readLine()) != null) {
				ta.append(line + "\n");
            }
            fReader.close();
        } catch (IOException e) {
            ta.append("\n!!! Failo " + fName.getName() + " skaitymo klaida");
        }
	}
}
