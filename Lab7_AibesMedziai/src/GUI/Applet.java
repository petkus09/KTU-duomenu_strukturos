package GUI;

import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;
import javax.swing.*;
import studijosKTU.Ks;

/**
 * @author matas
 */
public class Applet extends JApplet {

    private ResourceBundle rb = ResourceBundle.getBundle("GUI.MyResources");

    @Override
    public void init() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.invokeAndWait(new Runnable() {

                @Override
                public void run() {
                    add(new Lab7Panel(rb));
                }
            });
        } catch (InterruptedException ex) {
            Ks.ou(ex.getMessage());
        } catch (InvocationTargetException ex) {
            Ks.ou(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Ks.ou(ex.getMessage());
        } catch (InstantiationException ex) {
            Ks.ou(ex.getMessage());
        } catch (IllegalAccessException ex) {
            Ks.ou(ex.getMessage());
        } catch (UnsupportedLookAndFeelException ex) {
            Ks.ou(ex.getMessage());
        }
    }
}
