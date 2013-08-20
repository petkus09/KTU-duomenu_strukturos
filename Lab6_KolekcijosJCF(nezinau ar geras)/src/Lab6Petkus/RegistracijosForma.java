package Lab6Petkus;

import lab6_demo.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
/**
 *
 * @author Eimutis Karčiauskas
 */
public class RegistracijosForma extends JFrame
                                implements Serializable, ActionListener {

    JTextField tfAutoNr=      new JTextField("SKN379", 6);
    JTextField tfRegKiekis=   new JTextField("0", 5);
    JTextField tfNeregKiekis= new JTextField("0", 5);

    JButton jbReg=        new JButton("Registruoti iš sąrašo");
    JButton jbRasti=      new JButton("Rasti pagal auto numerį");
    JButton jbSkaityti=   new JButton("Skaityti iš teksto");
    JButton jbSaveObj=    new JButton("Saugoti objektus");
    JButton jbLoadObj=    new JButton("Skaityti objektus");
    JPanel panAutoSąr=    new JPanel();
    JPanel panDuomenys=   new JPanel();
    JPanel panRegNumeris= new JPanel();
    JPanel panRez =       new JPanel();
    JPanel panMygt =      new JPanel();

    JTextArea zonaNeregAuto= new JTextArea(24,30);
    JScrollPane scrNeregZona=new JScrollPane(zonaNeregAuto);
    JTextArea zonaRegAuto=   new JTextArea(24,34);
    JScrollPane scrRegZona=  new JScrollPane(zonaRegAuto);

    RegistracijaAuto rg = new RegistracijaAuto();

    public RegistracijosForma() {
        Container vidus = getContentPane();
        vidus.setLayout(new BoxLayout(vidus, BoxLayout.Y_AXIS));
        vidus.add(panAutoSąr);
        vidus.add(panDuomenys);
        jbReg.addActionListener(this);
        jbRasti.addActionListener(this);
        jbSkaityti.addActionListener(this);
        jbSaveObj.addActionListener(this);
        jbLoadObj.addActionListener(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setSize(400,600);
        išdėstymas();
        kosmetika();
        setVisible(true);
        // deserializuojame rg objektą su visomis struktūromis
        rg = (RegistracijaAuto) loadObject("temp.obj");
        if (rg==null) rg = new RegistracijaAuto();
        rodytiAutoRinkinius();
        pack();
    }
    private void išdėstymas() {
        setLocation(500, 20);
        // sudedame duomenis į paneles
        panAutoSąr.add(scrNeregZona);
        panAutoSąr.add(scrRegZona);
        panDuomenys.add(panRegNumeris);
        panDuomenys.add(panRez);
        panDuomenys.add(panMygt);

        GridBagLayout dėstymoBūdas = new GridBagLayout();
        GridBagConstraints dėsnis = new GridBagConstraints();
        panRegNumeris.setLayout(dėstymoBūdas);
        panRez.setLayout(dėstymoBūdas);

        dėsnis.fill = GridBagConstraints.NONE;
        dėsnis.insets = new Insets(5, 8, 0, 6);

        dėsnis.anchor = GridBagConstraints.LINE_END;
        dėsnis.gridy = GridBagConstraints.RELATIVE;
        dėsnis.gridx = 0;
        panRegNumeris.add(new JLabel("Auto Numeris"), dėsnis);
        panRez.add(new JLabel("Neregistruotų kiekis"), dėsnis);
        panRez.add(new JLabel("Registruotų kiekis"), dėsnis);

        dėsnis.anchor = GridBagConstraints.LINE_START;
        dėsnis.gridx = 1;

        panRegNumeris.add(tfAutoNr, dėsnis);

        panRez.add(tfNeregKiekis, dėsnis);
        panRez.add(tfRegKiekis, dėsnis);

        panMygt.setLayout(new BoxLayout(panMygt, BoxLayout.Y_AXIS));
        panMygt.add(jbReg);
        panMygt.add(jbRasti);
        panMygt.add(jbSkaityti);
        panMygt.add(jbSaveObj);
        panMygt.add(jbLoadObj);
    }

    private void kosmetika() {
        setTitle("Automobilių Queue ir Map struktūrų demo - KTU IF 2010");
        panAutoSąr.setBorder(new TitledBorder(
                "Neregistruotų automobilių eilė ir "+
                "registruotų automobilių Map struktūra"));
        panAutoSąr.setBackground(Color.yellow);
        panDuomenys.setBorder
                (new TitledBorder("Registracijos duomenys ir veiksmai"));
        panDuomenys.setBackground(Color.lightGray);
        panRegNumeris.setBackground(Color.green);
        panRez. setBackground(Color.gray);
        panMygt.setBackground(Color.magenta);
    }
    private void rodytiAutoRinkinius(){
        zonaNeregAuto.setText("");
        for(Automobilis a: rg.neregAuto)
            zonaNeregAuto.append(a.toString()+"\n");
        zonaRegAuto.setText("");
        for(Map.Entry<String, Automobilis> me: rg.regAuto.entrySet())
            zonaRegAuto.append(String.format("%8s=%s\n",
                                        me.getKey(), me.getValue()));
        tfNeregKiekis.setText(Integer.toString(rg.neregAuto.size()));
        tfRegKiekis.setText(Integer.toString(rg.regAuto.size()));
    }
    public void actionPerformed(ActionEvent e) {
       Object jbX = e.getSource();
       if(jbX==jbReg){
           String an = tfAutoNr.getText();
           if(rg.registruotiAuto(an))
                rodytiAutoRinkinius();
           else
                JOptionPane.showMessageDialog (this,"Registracija neįvyko:\n"+
                        "arba kartojasi auto numeris arba nėra neregistruotų");
       }
       if (jbX==jbRasti){
           String pag = tfAutoNr.getText();
           Automobilis a = rg.regAuto.get(pag);
           pag += a==null? " automobilis nerastas": "="+a;
           JOptionPane.showMessageDialog (this,pag);
       }
       if (jbX==jbSkaityti) {
           rg.neregAuto.clear();
           String ats = rg.skaitytiNeregAutos("ban.automoto");
           JOptionPane.showMessageDialog (this, ats);
           rodytiAutoRinkinius();
       }
       if (jbX==jbSaveObj) {
          saveObject(rg, "temp.obj");
       }
       if (jbX==jbLoadObj) {
          rg = (RegistracijaAuto) loadObject("temp.obj");
          rodytiAutoRinkinius();
       }
    }

    private void saveObject(Object obj, String fName) {
      try {
         FileOutputStream fos = new FileOutputStream(fName);
         ObjectOutputStream oos = new ObjectOutputStream(fos);
         oos.writeObject(obj);
         oos.flush();
         oos.close();
         fos.close();
      } catch (IOException ex) {
         JOptionPane.showMessageDialog(this, "Problemos saugojant Objektus");
      }
   }
    private Object loadObject(String fName) {
      Object obj = null;
      try {
        FileInputStream fis = new FileInputStream(fName);
        ObjectInputStream oin = new ObjectInputStream(fis);
        obj = oin.readObject();
      } catch (IOException ex) {
         JOptionPane.showMessageDialog(this, "Problemos skaitant Objektus");
          return null;
      } catch (ClassNotFoundException ex) {
         JOptionPane.showMessageDialog(this, "Nerasta objekto klasė");
          return null;
      }
      return obj;
    }

class RegistracijaAuto implements Serializable {

    Queue<Automobilis> neregAuto = new LinkedList<Automobilis>();
    Map<String,Automobilis> regAuto = new TreeMap<String, Automobilis>();

    public String skaitytiNeregAutos(String fName)    {
        if(fName.isEmpty())return "nenurodytas failo vardas";
        try {
            BufferedReader fReader =
                    new BufferedReader(new FileReader(new File(fName)));
            String dLine;
            while ((dLine = fReader.readLine()) != null) {
                neregAuto.add(new Automobilis(dLine));
            }
            fReader.close();
            return "OK - failas "+fName+" perskaitytas";
        } catch (FileNotFoundException e) {
            return ("Duomenų failas " + fName + " nerastas");
        } catch (IOException e) {
            return ("Failo " + fName + " skaitymo klaida");
        }
    }
    boolean registruotiAuto(String autoNr){
        if (regAuto.containsKey(autoNr)) return false;
        Automobilis a = neregAuto.poll();
        if (a==null) return false; // kai neregistruotų jau nėra
        regAuto.put(autoNr, a);
        return true;
    }
}
}
