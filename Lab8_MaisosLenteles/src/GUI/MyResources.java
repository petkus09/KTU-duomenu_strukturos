/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.event.KeyEvent;
import java.util.ListResourceBundle;

/**
 * @author darius
 */
//Platesnėms studijoms:
//http://download.oracle.com/javase/6/docs/api/java/util/ListResourceBundle.html
//http://download.oracle.com/javase/tutorial/i18n/resbundle/concept.html
//Naudojame nustatytą lokalę.
public class MyResources extends ListResourceBundle {

    @Override
    public Object[][] getContents() {
        return contents;
    }
    Object[][] contents = {
        {"lblTitle", "KTU IF 2011. LD8. Maišos lentelių tiriamasis darbas"},
        {"lblAuthor", "<html><b>Autorius: Tautvydas Petkus, IF-1/9/x</b><br>email: "
            + "<FONT COLOR=BLUE>tautvydas.petkus@stud.ktu.lt</FONT></html>\n"
            + "Įrašykite savo rekvizitus."},
        {"lblMenus", new String[]{
                "Failas",
                "Pagalba"
            }},
        {"lblMenuItems", new String[][]{
                {"Atidaryti..", "Išsaugoti..", "Išeiti"},
                {"Apie.."}
            }},
        {"lblNames", new String[]{
                "Kolizijų sprendimo metodas",
                "Maišos funkcija",
                "Duomenų įvedimas"
            }},
        {"lblBorders", new String[]{
                "Programos vykdymas",
                "Atvaizdis maišos lentelėje",
                "Statiniai ir dinaminiai parametrai",
                "Programos įvykiai"
            }},
        {"cmbCollisionTypes", new String[]{
                "Atskiros grandinėlės",
                "Atv. adresacija. Tiesinis dėstymas",
                "Atv. adresacija. Kvadratinis dėstymas",
                "Atv. adresacija. Dviguba maiša"
            }},
        {"cmbHashFunctions", new String[]{
                "Dalyba",
                "Daugyba",
                "Iš JavaCollectionsFramework"
            }},
        {"btnLabels", new String[]{
                "Generuoti atvaizdį",
                "Papildyti atvaizdį iš aibės",
                "Greitaveikos tyrimas",
                "Naikinti"
            }},
        {"lblParams1", new String[]{
                "Sugeneruotos aibės imtis",
                "Sugeneruotos aibės dydis",
                "Celės teksto kirtiklis",
                "Celės plotis",
                "Pradinis maišos lentelės dydis",
                "Apkrovimo faktorius"
            }},
        {"tfParams1", new String[]{
                "10",
                "1000",
                ":",
                "450",
                "8",
                "0.75"
            }},
        {"errMsgs1", new String[]{
                "Netinkama sugeneruotos aibės imtis",
                "Netinkamas sugeneruotos aibės dydis",
                "",
                "Netinkamas celės plotis",
                "Netinkamas pradinis maišos lentelės dydis",
                "Netinkamas apkrovimo faktorius"
            }},
        {"lblParams2", new String[]{
                "Porų kiekis maišos lentelėje",
                "Maišos lentelės dydis",
                "Ilgiausia grandinėlė",
                "Permaišymų kiekis",
                "Paskutinio papildyto indeksas",
                "Maišos lentelės užpildymas"
            }},
        {"tfParams2", new String[]{
                "0",
                "0",
                "0",
                "0",
                "0",
                "0"
            }},
        {"lblParams3", new String[]{
                "Vidutinis grandinė ilgis",
                "Tuščių kiekis",
                "dabartinis mėnesis ir diena",
                "Skolininkų kiekis",
                "Elemento kodas"
            }},
        {"tfParams3", new String[]{"", "", "2012-12-19", "", "", ""}},
        {"errMsgs3", new String[]{""}},
        {"msgs", new String[]{
                "Dar neįdiegta.",
                "Atvaizdis papildytas pora iš sugeneruotos aibės",
                "Greitaveikos tyrimas",
                "Aibės imtis negali būti didesnė negu\nsugeneruotmos aibės dydis.",
                "Visa sugeneruota aibė patalpinta maišos lentelėje.",
                "Visa aibė jau išspausdinta",
                "Failas perskaitytas.",
                "Maksimalus elementų skaičius grandinėlėje",
                "Sisteminė klaida. Žiūrėti konsolėje"
            }},
        {"toolTips", new String[]{
                "Galima nustatyti skirtukus: dvitaškį(:), tarpą( ) arba lygybę(=)",
                "Apkrovimo faktorių nustatykite (0;1] ribose."
            }},
        {"delimiters", ": ="},
        {"keys", new int[][]{
                {KeyEvent.VK_O, KeyEvent.VK_S, KeyEvent.VK_X},
                {-1}
            }}
    };
}