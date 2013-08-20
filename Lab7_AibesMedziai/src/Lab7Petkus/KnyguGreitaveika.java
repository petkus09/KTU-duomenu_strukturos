package Lab7Petkus;

import Lab7Petkus.*;
import GUI.*;
import GUI.KsSwing.MyException;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JTextArea;
import studijosKTU.*;

public class KnyguGreitaveika extends Thread {
    
    private static ResourceBundle rb;
    static JTextArea taOutput;
    static JButton[] btns;
    static String[] tyrimųVardai = {"addBstRec", "addBstIte", "addAvlRec", "removeBst"};
    static int[] tiriamiKiekiai = {1000, 2000, 4000, 8000};
    static SortedSetADTx<BookClass> PirmojiSerija = new BstSetKTUx(new BookClass(), BookClass.byYear);
    static SortedSetADTx<BookClass> AntrojiSerija = new BstSetKTUx2(new BookClass());
    static SortedSetADTx<BookClass> TrečiojiSerija=  new AvlSetKTUx(new BookClass());
    
    public KnyguGreitaveika(JTextArea taOutput, JButton[] btns, ResourceBundle rb) {
        KnyguGreitaveika.taOutput = taOutput;
        KnyguGreitaveika.btns = btns;
        KnyguGreitaveika.rb = rb;
    }

    @Override
    public void run() {
        for (JButton btn : btns) {
            btn.setEnabled(false);
        }
        SisteminisTyrimas();
        for (JButton btn : btns) {
            btn.setEnabled(true);
        }
    }

    public void SisteminisTyrimas() {
        try {
            Timekeeper tk = new Timekeeper(tiriamiKiekiai, taOutput);
            for (int k : tiriamiKiekiai) {
                BookClass[] BookMas = KnyguRegistratura.generuotiIrIsmaisyti(3 * k, k, 1.0);
                PirmojiSerija.clear();
                AntrojiSerija.clear();
                TrečiojiSerija.clear();
                tk.startAfterPause();
                tk.start();
                for (BookClass a : BookMas) {
                    PirmojiSerija.add(a);
                }
                tk.finish(tyrimųVardai[0]);
                for (BookClass a : BookMas) {
                    AntrojiSerija.add(a);
                }
                tk.finish(tyrimųVardai[1]);
                for (BookClass a : BookMas) {
                    TrečiojiSerija.add(a);
                }
                tk.finish(tyrimųVardai[2]);
                for (BookClass a : BookMas) {
                    PirmojiSerija.remove(a);
                }
                tk.finish(tyrimųVardai[3]);
                tk.seriesFinish();
            }
        } catch (MyException e) {
           if (e.getCode() >= 0 && e.getCode() <= 3) {
                KsSwing.ounerr(taOutput, rb.getStringArray("errMsgs2")[e.getCode()] + ": " + e.getMessage());
            } else if (e.getCode() == 4) {
                KsSwing.ounerr(taOutput, rb.getStringArray("msgs")[2]);
            } else {
                KsSwing.ounerr(taOutput, e.getMessage());
            }
        }
    }
}
