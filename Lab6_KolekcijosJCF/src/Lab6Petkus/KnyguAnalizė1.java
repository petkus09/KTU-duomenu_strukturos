package Lab6Petkus;


import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import lab6_demo.Automobilis;

public class KnyguAnalizė1 extends JFrame 
                        implements Serializable, ActionListener {
    
   // static Library ap = new Library();
    
    JTextField tfKnygosNr=      new JTextField("IF00001", 8);
    JTextField tfNeregSKaitytojųKiekis= new JTextField("", 3);
    JTextField tfRegSKaitytojųKiekis= new JTextField("", 3);

    JButton jbReg=         new JButton("Registruoti iš sąrašo");
    JButton jbSkaityti=   new JButton("Skaityti iš teksto");
    JButton jbSaveObj=    new JButton("Saugoti objektus");
    JButton jbLoadObj=    new JButton("Skaityti objektus");
    JButton jbAtranka=    new JButton("Atrinkti pagal kodą");
    JPanel panKnygųSar=    new JPanel();
    JPanel panDuomenys=    new JPanel();
    JPanel panAsmensDuo =  new JPanel();
    JPanel panRez =        new JPanel();
    JPanel panMygt =       new JPanel();

    JTextArea neregKnyguZona= new JTextArea(20,44);
    JScrollPane scrVisZona= new JScrollPane(neregKnyguZona);
    JTextArea regKnyguZona= new JTextArea(20,44);
    JScrollPane scrAtrZona= new JScrollPane(regKnyguZona);
    
    KnyguRegistracija rg = new KnyguRegistracija();

    public KnyguAnalizė1() {
        Container vidus = getContentPane();
        vidus.setLayout(new BoxLayout(vidus, BoxLayout.Y_AXIS));
        vidus.add(panKnygųSar);
        vidus.add(panDuomenys);
        jbReg.addActionListener(this);
        jbSkaityti.addActionListener(this);
        jbSaveObj.addActionListener(this);
        jbLoadObj.addActionListener(this);
        jbAtranka.addActionListener(this);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        išdėstymas();
        kosmetika();
        setVisible(true);
        rg = (KnyguRegistracija) loadObject("Knygos1.obj");
        if (rg==null) rg = new KnyguRegistracija();
        RodytiKnyguRinkinius();
        pack();
    }
    public void išdėstymas() {
        setLocation(500, 20);
        // sudedame duomenis į paneles
        panKnygųSar.add(scrVisZona);
        panKnygųSar.add(scrAtrZona);
        panDuomenys.add(panAsmensDuo);
        panDuomenys.add(panRez);
        panDuomenys.add(panMygt);

        GridBagLayout dėstymoBūdas = new GridBagLayout();
        GridBagConstraints dėsnis = new GridBagConstraints();
        panAsmensDuo.setLayout(dėstymoBūdas);
        panRez.setLayout(dėstymoBūdas);

        dėsnis.fill = GridBagConstraints.NONE;
        dėsnis.insets = new Insets(5, 8, 0, 6);

        dėsnis.anchor = GridBagConstraints.LINE_END;
        dėsnis.gridy = GridBagConstraints.RELATIVE;
        dėsnis.gridx = 0;
        panRez.add(new JLabel("Knygos registracijos kodas"), dėsnis);
        panRez.add(new JLabel("Visų neregistruotų skaitytojų kiekis"), dėsnis);
        panRez.add(new JLabel("Visų registruotų skaitytojų kiekis"), dėsnis);
        panRez.add(new JLabel("Dabartinė data"), dėsnis);
        panRez.add(new JLabel("Knygų atrinkimo metai"), dėsnis);

        dėsnis.anchor = GridBagConstraints.LINE_START;
        dėsnis.gridx = 1;

        panRez.add(tfKnygosNr, dėsnis);
        panRez.add(tfNeregSKaitytojųKiekis, dėsnis);
        panRez.add(tfRegSKaitytojųKiekis, dėsnis);

        panMygt.setLayout(new BoxLayout(panMygt, BoxLayout.Y_AXIS));
        panMygt.add(jbReg);
        panMygt.add(jbSkaityti);
        panMygt.add(jbSaveObj);
        panMygt.add(jbLoadObj);
        panMygt.add(jbAtranka);
    }

    public void kosmetika() {
        setTitle("Knygų sąrašų demo - KTU IF 2012");
        panKnygųSar.setBorder(new TitledBorder(
                "Visų knygų ir atrinktų knygų sąrašai"));
        panKnygųSar.setBackground(Color.lightGray);
        panDuomenys.setBorder(new TitledBorder("Knygų duomenys"));
        panDuomenys.setBackground(Color.lightGray);
        panAsmensDuo.setBackground(Color.white);
        panRez. setBackground(Color.white);
        panMygt.setBackground(Color.white);
    }
    private int eilėsNr=0;

    public void actionPerformed(ActionEvent e) {
        Object jbX = e.getSource();
        if(jbX==jbReg){
           String an = tfKnygosNr.getText();
           if(rg.registruotiKnygas(an)){
                RodytiKnyguRinkinius();
           }
           else
                JOptionPane.showMessageDialog (this,"Registracija neįvyko:\n"+
                        "arba kartojasi auto numeris arba nėra neregistruotų");
       }
       if (jbX==jbSkaityti) {
           rg.neregKnygos.clear();
           String ats = rg.skaitytiKnygas("Books.book");
           JOptionPane.showMessageDialog (this, ats);
           RodytiKnyguRinkinius();
       }
       if (jbX==jbSaveObj) {
          saveObject(rg, "Knygos1.obj");
       }
       if (jbX==jbLoadObj) {
          rg = (KnyguRegistracija) loadObject("Knygos1.obj");
          RodytiKnyguRinkinius();
       }
       if (jbX==jbAtranka){
           String pag = tfKnygosNr.getText();
           Book a = rg.regKnygos.get(pag);
           pag += a==null? " knyga nerasta": "="+a;
           JOptionPane.showMessageDialog (this,pag);;
       }
    }
    
     private void RodytiKnyguRinkinius(){
        neregKnyguZona.setText("");
        for(Book a: rg.neregKnygos)
            neregKnyguZona.append(a.toString()+"\n");
        regKnyguZona.setText("");
        for(Map.Entry<String, Book> me: rg.regKnygos.entrySet())
            regKnyguZona.append(String.format("%8s=%s\n",
                                        me.getKey(), me.getValue()));
        tfNeregSKaitytojųKiekis.setText(Integer.toString(rg.neregKnygos.size()));
        tfRegSKaitytojųKiekis.setText(Integer.toString(rg.regKnygos.size()));
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
    
    class KnyguRegistracija implements Serializable {

     Queue<Book> neregKnygos = new LinkedList<Book>();
     Map<String,Book> regKnygos = new TreeMap<String, Book>();

        public String skaitytiKnygas(String fName)    {
         if(fName.isEmpty())return "nenurodytas failo vardas";
         try {
             BufferedReader fReader =
                        new BufferedReader(new FileReader(new File(fName)));
             String dLine;
             while ((dLine = fReader.readLine()) != null) {
                 neregKnygos.add(new Book(dLine));
             }
             fReader.close();
             return "OK - failas "+fName+" perskaitytas";
            } catch (FileNotFoundException e) {
             return ("Duomenų failas " + fName + " nerastas");
            } catch (IOException e) {
             return ("Failo " + fName + " skaitymo klaida");
            }
        }
        boolean registruotiKnygas(String KnygNr){
            if (regKnygos.containsKey(KnygNr)) return false;
            Book a = neregKnygos.poll();
            if (a==null) return false; // kai neregistruotų jau nėra
            regKnygos.put(KnygNr, a);
            return true;
        }
    }
}
