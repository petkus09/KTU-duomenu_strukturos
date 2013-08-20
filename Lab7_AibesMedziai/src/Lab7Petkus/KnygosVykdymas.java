package Lab7Petkus;

import Lab7Petkus.*;
import GUI.*;
import java.util.Locale;
/*
 * Darbo Atlikimo tvarka - čia yra pradinė klasė:
 */
public class KnygosVykdymas {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US); // suvienodiname skaičių formatus
        KnyguTestai.KnyguAibesTestas();
        MainWindow.createAndShowGUI();
    }
}