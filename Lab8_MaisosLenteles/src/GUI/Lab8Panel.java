package GUI;

import GUI.KsSwing.MyException;
import Lab8Petkus.BookClass;
import Lab8Petkus.KnyguRegistracija;
import Lab8Petkus.LentelėMaišai;
import LaboraiDemo.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ResourceBundle;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import studijosKTU.*;

/**
 * @author darius.matulis@ktu.lt
 */
//Lab8 panelis
public class Lab8Panel extends JPanel implements ActionListener {

//                              Lab8Panel (BorderLayout)
//  |--------West--------|---------------------Center-----------------------|
//  | |~~~~~~~~~~~~~~~~| | |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~| |
//  | |   scrollLeft   | | |                                              | |
//  | |                | | |                                              | |
//  | |                | | |                 scrollTable                  | |
//  | |                | | |                                              | |
//  | |                | | |                                              | |
//  | |                | | |                                              | |
//  | |                | | |                                              | | 
//  | |                | | |                                              | | 
//  | | |------------| | | |                                              | |
//  | | | panButtons | | | |                                              | |
//  | | |            | | | |                                              | |
//  | | |------------| | | |                                              | | 
//  | |~~~~~~~~~~~~~~~~| | |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~| | 
//  |-------------------------------South-----------------------------------|
//  | |~~~~~~~~~~~~~~~~~~~~~~~~~panParam123Events~~~~~~~~~~~~~~~~~~~~~~~~~| |
//  | | |----------scrollParam123-----------| |------scrollEvents-------| | |
//  | | | |~~~~~~~~~||~~~~~~~~~||~~~~~~~~~| | |                         | | | 
//  | | | |panParam1||panParam2||panParam3| | |                         | | |
//  | | | |         ||         ||         | | |                         | | |
//  | | | |~~~~~~~~~||~~~~~~~~~||~~~~~~~~~| | |                         | | |
//  | | |-----------------------------------| |-------------------------| | |
//  | |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~| |
//  |-----------------------------------------------------------------------|   
    public Lab8Panel(ResourceBundle rb) {
        this.rb = rb;
        initComponents();
    }

    private void initComponents() {
        //======================================================================
        // Formuojamas rožinės spalvos panelis (kairėje pusėje)
        //======================================================================
        lbl1 = new JLabel(rb.getStringArray("lblNames")[0]);
        lbl2 = new JLabel(rb.getStringArray("lblNames")[1]);
        lbl3 = new JLabel(rb.getStringArray("lblNames")[2]);
        //Užpildomi JComboBox'ai
        for (String s : rb.getStringArray("cmbCollisionTypes")) {
            cmbCollisionTypes.addItem(s);
        }
        cmbCollisionTypes.addActionListener(this);
        for (String s : rb.getStringArray("cmbHashFunctions")) {
            cmbHashFunctions.addItem(s);
        }
        cmbHashFunctions.addActionListener(this);

        // Formuojamas mygtukų tinklelis (mėlynas). Naudojama klasė MyPanels.
        panButtons = new MyPanels(rb.getStringArray("btnLabels"), 4, 0);
        for (JButton btn : panButtons.getButtons()) {
            btn.addActionListener(this);
        }
        enableButtons(false);

        // Viskas sudedama į vieną (rožinės spalvos) panelį
        panLeft.setLayout(new BoxLayout(panLeft, BoxLayout.Y_AXIS));
        for (JComponent comp : new JComponent[]{lbl1, cmbCollisionTypes, lbl2,
                    cmbHashFunctions, lbl3, taInput, panButtons}) {
            comp.setAlignmentX(JComponent.LEFT_ALIGNMENT);
            panLeft.add(Box.createRigidArea(new Dimension(0, 2)));
            panLeft.add(comp);
        }
        //======================================================================
        // Formuojama pirmoji parametrų lentelė (geltona). Naudojame klasę MyPanels.
        //======================================================================        
        panParam1 = new MyPanels(rb.getStringArray("lblParams1"),
                rb.getStringArray("tfParams1"), TF_WIDTH); //5 - tf plotis
        //..kai kurie prametrai plačiau paaiskinami tooltips'uose.. 
        panParam1.getTfOfTable()[2].setToolTipText(rb.getStringArray("toolTips")[0]);
        panParam1.getTfOfTable()[5].setToolTipText(rb.getStringArray("toolTips")[1]);
        //======================================================================
        // Formuojama antroji parametrų lentelė (oranžinė). Naudojame klasę MyPanels.
        //======================================================================        
        panParam2 = new MyPanels(rb.getStringArray("lblParams2"),
                rb.getStringArray("tfParams2"), TF_WIDTH);
        //======================================================================
        // Formuojama trečioji parametrų lentelė (geltona). Naudojame klasę MyPanels.
        //======================================================================              
        panParam3 = new MyPanels(rb.getStringArray("lblParams3"),
                rb.getStringArray("tfParams3"), TF_WIDTH);
        //======================================================================
        // Visų trijų parametrų lentelių paneliai sudedami į šviesiai pilką panelį
        //======================================================================
        panParam123.add(panParam1);
        panParam123.add(panParam2);
        panParam123.add(panParam3);
        //======================================================================
        // Toliau suformuojamas panelis iš šviesiai pilko panelio ir JTextArea, 
        // kuriame atvaizduojama programos vykdymo eiga
        //======================================================================
        GroupLayout gl = new GroupLayout(panParam123Events);
        panParam123Events.setLayout(gl);
        gl.setHorizontalGroup(
                gl.createSequentialGroup().
                addComponent(scrollParam123,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE,
                Short.MAX_VALUE).
                addComponent(scrollEvents,
                GroupLayout.DEFAULT_SIZE,
                GroupLayout.PREFERRED_SIZE,
                Short.MAX_VALUE));
        gl.setVerticalGroup(
                gl.createSequentialGroup().
                addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER).
                addComponent(scrollParam123).
                addComponent(scrollEvents)));

        //Kad prijungiant tekstą prie JTextArea vaizdas visada nušoktų į apačią
        scrollEvents.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {

            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                taEvents.select(taEvents.getCaretPosition()
                        * taEvents.getFont().getSize(), 0);
            }
        });
        appearance();
        //======================================================================
        // Suformuojamas bendras Lab8 panelis
        //======================================================================
        setLayout(new BorderLayout());
        add(scrollLeft, BorderLayout.WEST);
        add(scrollTable, BorderLayout.CENTER);
        add(panParam123Events, BorderLayout.SOUTH);
    }

    //Kosmetika
    private void appearance() {
        int counter = 0;
        //Objektų rėmeliai
        for (JComponent comp : new JComponent[]{panLeft, scrollTable,
                    panParam123, scrollEvents}) {
            TitledBorder tb = new TitledBorder(rb.getStringArray("lblBorders")[counter++]);
            tb.setTitleFont(new Font(Font.SANS_SERIF, Font.BOLD, 11));
            comp.setBorder(tb);
        }
        scrollTable.getViewport().setBackground(Color.white);
        panParam1.setBackground(new Color(255, 255, 153));//Gelsva
        panParam2.setBackground(Color.orange);
        panParam3.setBackground(new Color(255, 255, 153));
        panLeft.setBackground(Color.pink);
        panButtons.setBackground(new Color(112, 162, 255));//Blyškiai mėlyna
        panParam123.setBackground(Color.lightGray);
        cmbCollisionTypes.setEditable(false);
        cmbHashFunctions.setEditable(false);
        taInput.setBackground(Color.white);
        //Antra parametrų lentelė (oranžinė) bus neredaguojama
        for (JTextField comp : panParam2.getTfOfTable()) {
            comp.setEditable(false);
        }
        for (JTextArea ta : new JTextArea[]{taInput, taEvents}) {
            ta.setBackground(Color.white);
            ta.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        }
        taEvents.setEditable(false);
        scrollEvents.setPreferredSize(new Dimension(350, 0));
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            System.gc();
            System.gc();
            System.gc();                      
            taEvents.setBackground(Color.white);
            Object source = ae.getSource();
            if (source.equals(panButtons.getButtons()[0])) {
                mapGeneration(null);
            }
            if (source.equals(panButtons.getButtons()[1])) {
                mapAdd();
            }
            if (source.equals(panButtons.getButtons()[2])) {
                mapEfficiency();
            }
            if (source.equals(panButtons.getButtons()[3])) {
                naikinimas();
            }
            if (source.equals(cmbCollisionTypes) || source.equals(cmbHashFunctions)) {
                enableButtons(false);
            }
        } catch (MyException e) {
            if (e.getCode() >= 0 && e.getCode() < panParam1.getParametersOfTable().length) {
                panParam1.getTfOfTable()[e.getCode()].setBackground(Color.red);
            }
            //Specialus atvejis kai parametrais sugeneruotos aibės imtis  
            //nustatoma  didesnė negu sugeneruota aibė
            if (e.getCode() == 100) {
                panParam1.getTfOfTable()[0].setBackground(Color.red);
                panParam1.getTfOfTable()[1].setBackground(Color.red);
            }
            taEvents.setBackground(Color.pink);
            KsSwing.oun(taEvents, e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            KsSwing.ounerr(taEvents, rb.getStringArray("msgs")[8]);
            e.printStackTrace(System.out);
        } catch (IllegalArgumentException e) {
            KsSwing.ounerr(taEvents, rb.getStringArray("msgs")[8]);
            e.printStackTrace(System.out);
        } catch (NullPointerException e) {
            KsSwing.ounerr(taEvents, rb.getStringArray("msgs")[8]);
            e.printStackTrace(System.out);
        } catch (UnsupportedOperationException e) {
            KsSwing.ounerr(taEvents, e.getMessage());
        } catch (Exception e) {
            KsSwing.ounerr(taEvents, rb.getStringArray("msgs")[8]);
            e.printStackTrace(System.out);
        }
        
    }

    public void mapGeneration(String fName) throws MyException {
        enableButtons(false);
        //Duomenų nuskaitymas iš lentelių
        readParameters();
        switch (cmbCollisionTypes.getSelectedIndex()) {
            case 0:
                map = new MapKTUx<String, BookClass>(new String(), new BookClass(), initialCapacity, loadFactor, ht);
                if (fName == null) {
                bookArray = registracija.generavimasIrGrazinimas(sizeOfSet, sizeOfGenSet);
                for (BookClass a : bookArray) {
                    map.put(
                           "" + registracija.formuotiKnygosID() //raktas
                           , a);
                    KsSwing.oun(taEvents, a, rb.getStringArray("msgs")[1]);
                    }
                    enableButtons(true);
                } 
                else {
                    map.load(fName);
                    enableButtons(false);
                    KsSwing.oun(taEvents, rb.getStringArray("msgs")[6]);
                }
            table.setMyTable(map, map.getMaxChainSize(), map.getTableCapacity(), colWidth, delimiter, panParam3.getTfOfTable()[3].getText());
            updateParameters(false);
            break;
            //...
            //Programuojant kitus kolizijų sprendimo metodus reikia papildyti switch sakinį
            case 1:
                map1 = new LentelėMaišai<String, BookClass>();
                if (fName == null) {
                    bookArray = registracija.generavimasIrGrazinimas(sizeOfSet, sizeOfGenSet);
                    for (BookClass a : bookArray) {
                        map1.put(registracija.formuotiKnygosID() //raktas
                                , a);
                        KsSwing.oun(taEvents, a, rb.getStringArray("msgs")[1]);
                    }
                    enableButtons(true);
                } else {
                    map1.load(fName);
                    enableButtons(false);
                    KsSwing.oun(taEvents, rb.getStringArray("msgs")[6]);
                }
                table.setMyTable1(map1, map1.size(), colWidth, delimiter);
                updateParameters(false);
                break;
            default:
                enableButtons(false);
                throw new MyException(rb.getStringArray("msgs")[0]);
        }
        //Išvedami rezultatai
    }

    public void mapAdd() throws MyException {
        BookClass a = registracija.imtiIsBazes();
        map.put(
                "" + registracija.formuotiKnygosID() //raktas
                , a);
        table.setMyTable(map, map.getMaxChainSize(), map.getTableCapacity(), colWidth, delimiter, panParam3.getTfOfTable()[3].getText());
        updateParameters(true);
        KsSwing.oun(taEvents, a, rb.getStringArray("msgs")[1]);
    }

    public void mapEfficiency() {
        KsSwing.oun(taEvents, "", rb.getStringArray("msgs")[2]);
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                GreitaveikosTyrimas gt = new GreitaveikosTyrimas(taEvents, panButtons.getButtons(), rb);
                gt.start();
            }
        });
    }
    
    public void naikinimas(){
        switch (cmbCollisionTypes.getSelectedIndex()) {
            case 0:
                map.remove(panParam3.getTfOfTable()[4].getText());
                table.setMyTable(map, map.getMaxChainSize(), map.getTableCapacity(), colWidth, delimiter, panParam3.getTfOfTable()[3].getText());
                updateParameters(false);
                break;
            case 1:
                map1.remove(panParam3.getTfOfTable()[4].getText());
                table.setMyTable1(map1, map1.size(), colWidth, delimiter);
                updateParameters(false);
                break;
        }
    }
    
    private void enableButtons(boolean enable) {
        for (int i : new int[]{1, 3}) {
            panButtons.getButtons()[i].setEnabled(enable);
        }
    }

    private void readParameters() throws MyException {
        int i = 0;
        for (JTextField tf : panParam1.getTfOfTable()) {
            tf.setBackground(Color.WHITE);
        }
        String[] parameters = panParam1.getParametersOfTable();
        try {
            //Pakeitimas (replace) tam, kad sukelti situaciją esant
            //neigiamam skaičiui
            sizeOfSet = Integer.parseInt(parameters[i].replace("-", "x"));
            sizeOfGenSet = Integer.parseInt(parameters[++i].replace("-", "x"));
            delimiter = parameters[++i];
            colWidth = Integer.parseInt(parameters[++i].replace("-", "x"));
            initialCapacity = Integer.parseInt(parameters[++i].replace("-", "x"));
            loadFactor = Float.parseFloat(parameters[++i].replace("-", "x"));
            if (loadFactor == 0 || colWidth == 0) {
                throw new MyException(rb.getStringArray("errMsgs1")[i] + ": " + parameters[i], i);
            }
            switch (cmbHashFunctions.getSelectedIndex()) {
                case 0:
                    ht = MapKTU.HashType.Division;
                    break;
                case 1:
                    ht = MapKTU.HashType.Multiplication;
                    break;
                case 2:
                    ht = MapKTU.HashType.JavaCollectionsF;
                    break;
                default:
                    ht = MapKTU.HashType.Division;
                    break;
            }
        } catch (NumberFormatException e) {
            throw new MyException(rb.getStringArray("errMsgs1")[i] + ": " + parameters[i], i);
        }
    }

    //colorize - ar spalvinti parametrus raudonai?
    private void updateParameters(boolean colorize) {
        String[] parameters = new String[]{
            map.size() + "",
            map.getTableCapacity() + "",
            map.getMaxChainSize() + "",
            map.getRehashesCounter() + "",
            map.getLastUpdatedChain() + "",
            //Užimtų maišos lentelės elementų skaičius %            
            String.format("%3.2f", (double) map.getChainsCounter() / map.getTableCapacity() * 100) + "%"
        //.. naujus parametrus tęsiame čia ..
        };
        for (int i = 0; i < parameters.length; i++) {
            String str = panParam2.getTfOfTable()[i].getText();
            if ((!str.equals(parameters[i]) && !str.equals("") && colorize)) {
                panParam2.getTfOfTable()[i].setForeground(Color.RED);
            } else {
                panParam2.getTfOfTable()[i].setForeground(Color.BLACK);
            }
            panParam2.getTfOfTable()[i].setText(parameters[i]);
        }
        String dataS = panParam3.getTfOfTable()[2].getText().toString();
         switch (cmbCollisionTypes.getSelectedIndex()) {
            case 0:
                panParam3.getTfOfTable()[0].setText(Double.toString((double)(((int)(map.vidGrandIlgis()*100.0))/100.0)));
                panParam3.getTfOfTable()[1].setText(Integer.toString(map.tusciuKiekis()));
                int skolininkaiK1 = map.SkolininkuKiekis(dataS);
                panParam3.getTfOfTable()[3].setText(Integer.toString(skolininkaiK1));
                break;
            case 1:
                panParam3.getTfOfTable()[0].setText(Double.toString((double)(((int)(map1.vidGrandIlgis()*100.0))/100.0)));
                panParam3.getTfOfTable()[1].setText(Integer.toString(map1.tusciuKiekis()));
                int skolininkaiK2 = map1.SkolininkuKiekis(dataS);
                panParam3.getTfOfTable()[3].setText(Integer.toString(skolininkaiK2));
                break;
         }
        
    }
    

    public JTextArea getTaEvents() {
        return taEvents;
    }
    private static final int TF_WIDTH = 6;
    private ResourceBundle rb;
    private MapKTU.HashType ht = MapKTU.HashType.Division;
    private JLabel lbl1, lbl2, lbl3;
    private JComboBox cmbCollisionTypes = new JComboBox();
    private JComboBox cmbHashFunctions = new JComboBox();
    private JTextArea taInput = new JTextArea();
    private MyTable table = new MyTable();
    private JScrollPane scrollTable = new JScrollPane(table);
    private JPanel panParam123 = new JPanel();
    private JScrollPane scrollParam123 = new JScrollPane(panParam123);
    private JPanel panParam123Events = new JPanel();
    private JTextArea taEvents = new JTextArea();
    private JScrollPane scrollEvents = new JScrollPane(taEvents);
    private MyPanels panParam1, panParam2, panParam3, panButtons;
    private JPanel panLeft = new JPanel();
    private JScrollPane scrollLeft = new JScrollPane(panLeft);
    private static BookClass[] bookArray;
    private MapADTx<String, BookClass> map;
    private LentelėMaišai<String, BookClass> map1;
    private int sizeOfSet, sizeOfGenSet, colWidth, initialCapacity;
    private float loadFactor;
    private String delimiter = "";
    private KnyguRegistracija registracija = new KnyguRegistracija();
}