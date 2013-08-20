package GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.Arrays;
import javax.swing.*;

/**
 * @author darius.matulis@ktu.lt
 */
//Klase galima sukurti du panelius: parametru ivedimui ir mygtuku grupei
//sudaryti. Panelyje esanciu komponentu kiekis nustatomas parametrais.
public class MyPanels extends JPanel {

    private String[] lblTexts = null;
    private String[] tfTexts = null;
    private String[] btnNames = null;
    private JTextField[] tfs = null;
    private JButton[] btns = null;
    private JLabel[] lbls = null;
    private int columnWidth = 1;
    private int gridX = 1;
    private int gridY = 1;
    private SpringLayout.Constraints textFieldCons, labelCons;
    private static int spacing = 5;

    //Sukuriama parametrų lentelė (GridBag išdėstymo dėsnis)
    public MyPanels(String[] lblTexts, String[] tfTexts, int columnWidth) {
        if (lblTexts == null || tfTexts == null) {
            throw new NullPointerException();
        }
        this.lblTexts = lblTexts;
        this.columnWidth = columnWidth;
        if (lblTexts.length > tfTexts.length) {
            int i = tfTexts.length;
            tfTexts = Arrays.copyOf(tfTexts, lblTexts.length);
            Arrays.fill(tfTexts, i, lblTexts.length, "");
        }
        this.tfTexts = tfTexts;
        initTableOfParameters();
        //initTableOfParameters2();
    }

    //Sukuriamas mygtukų tinklelis (GridLayout išdėstymo dėsnis)
    public MyPanels(String[] btnNames, int gridX, int gridY) {
        if (btnNames == null) {
            throw new NullPointerException();
        }
        this.btnNames = btnNames;
        this.gridX = gridX;
        this.gridY = gridY;
        initGridOfButtons();
    }

    private void initTableOfParameters() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //Spacing'as tarp komponentų
        c.insets = new Insets(3, 6, 3, 4);
        //Lygiavimas į kairę
        c.anchor = GridBagConstraints.WEST;
        //Pasirenkamas pirmas stulpelis..
        c.gridx = 0;
        //..ir į jį sudedami labeliai
        for (int i = 0; i < lblTexts.length; i++) {
            add(new JLabel(lblTexts[i]), c);
        }
        //Pasirenkamas antras stulpelis..
        c.gridx = 1;
        //..ir į jį sudedami textfieldai
        tfs = new JTextField[lblTexts.length];
        for (int i = 0; i < tfs.length; i++) {
            tfs[i] = new JTextField(tfTexts[i], columnWidth);
            tfs[i].setHorizontalAlignment(JTextField.CENTER);
            tfs[i].setBackground(Color.WHITE);
            add(tfs[i], c);
        }
    }

    //Kam idomu - tokia pati parametrų lentelė, padaryta naudojant SpringLayout išdėstymo dėsnį
    private void initTableOfParameters2() {
        SpringLayout layout = new SpringLayout();
        setLayout(layout);
        Spring panelWidth = layout.getConstraint("East", this);
        lbls = new JLabel[lblTexts.length];
        tfs = new JTextField[lblTexts.length];
        for (int i = 0; i < lblTexts.length; i++) {
            lbls[i] = new JLabel(lblTexts[i]);
            add(lbls[i]);
            tfs[i] = new JTextField(tfTexts[i], columnWidth);
            tfs[i].setHorizontalAlignment(JTextField.CENTER);
            add(tfs[i]);
        }
        Spring maxLabel = Spring.constant(0);
        for (int i = 0; i < lbls.length; i++) {
            SpringLayout.Constraints con = layout.getConstraints(lbls[i]);
            maxLabel = Spring.max(maxLabel, con.getWidth());
        }
        for (int i = 0; i < lblTexts.length; i++) {
            labelCons = layout.getConstraints(lbls[i]);
            textFieldCons = layout.getConstraints(tfs[i]);
            int pad = (textFieldCons.getHeight().getValue() - labelCons.getHeight().getValue()) / 2;

            labelCons.setX(Spring.constant(spacing));
            textFieldCons.setX(Spring.sum(Spring.constant(spacing * 2), maxLabel));
            if (i == 0) {
                labelCons.setY(Spring.constant(spacing + pad));
                textFieldCons.setY(Spring.constant(spacing));
            } else {
                SpringLayout.Constraints tfConstraintsLast = layout.getConstraints(tfs[i - 1]);
                labelCons.setY(Spring.sum(Spring.constant(spacing + pad), tfConstraintsLast.getConstraint(SpringLayout.SOUTH)));
                textFieldCons.setY(Spring.sum(Spring.constant(spacing), tfConstraintsLast.getConstraint(SpringLayout.SOUTH)));
            }
            if (i != tfs.length - 1) {
                int reiksme = maxLabel.getValue();
                textFieldCons.setWidth(Spring.sum(Spring.constant(-reiksme - (3 * spacing)), panelWidth));
            }
        }
        SpringLayout.Constraints consParent = layout.getConstraints(this);
        consParent.setConstraint("East",
                Spring.sum(Spring.constant(spacing), textFieldCons.getConstraint(SpringLayout.EAST)));
        consParent.setConstraint("South",
                Spring.sum(Spring.constant(spacing), textFieldCons.getConstraint(SpringLayout.SOUTH)));
    }

    private void initGridOfButtons() {
        setLayout(new GridLayout(gridX, gridY, 3, 3));
        btns = new JButton[btnNames.length];
        for (int i = 0; i < btns.length; i++) {
            add(btns[i] = new JButton(btnNames[i]));
        }
    }

    /**
     * Gražinamas mygtukų tinklelio JButton objektų masyvas.
     * 	@throws NullPointerException Jei mygtukų tinklelis nebuvo sukurtas.
     */
    public JButton[] getButtons() {
        if (btns == null) {
            throw new NullPointerException("Null buttons array");
        } else {
            return btns;
        }
    }

    /**
     * Gražinamas parametrų lentelės parametrų String masyvas.
     * 	@throws NullPointerException Jei parametrų lentelė nebuvo sukurta.
     */
    public String[] getParametersOfTable() {
        if (tfs == null) {
            throw new NullPointerException("Null parameters of table");
        } else {
            for (int i = 0; i < tfs.length; i++) {
                tfTexts[i] = tfs[i].getText();
            }
        }
        return tfTexts;
    }

    /**
     * Gražinamas parametrų lentelės JTextField objektų masyvas.
     * 	@throws NullPointerException Jei parametrų lentelė nebuvo sukurta.
     */
    public JTextField[] getTfOfTable() {
        if (tfs == null) {
            throw new NullPointerException("Null table of parameters");
        } else {
            return tfs;
        }
    }
}