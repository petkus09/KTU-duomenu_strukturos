package Lab5Petkus;


import dialogai.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import studijosKTU.ListKTUx;

public class KnyguAnalizė extends JFrame implements ActionListener {
    
    static Library ap = new Library();
    
    
    JTextField tfMetai=   new JTextField("2010", 8);
    JTextField tfPavadinimas=  new JTextField("Knygos Pavadinimas", 20);
    JTextField tfAutorius= new JTextField("Autorius", 18);
    JTextField tfRegistroDiena=     new JTextField("2010-01-01", 10);
    JTextField tfVartotojas= new JTextField("Klientas", 18);
    JTextField tfLaikotarpis= new JTextField("31", 3);
    JTextField tfDabartinėData= new JTextField("2012-10-31", 10);
    JTextField tfSKaitytojųKiekis= new JTextField("", 3);
    JTextField tfSenuAtrinkimoRiba = new JTextField("1990", 4);

    JButton jbReg=         new JButton("Registruoti");
    JButton jbTvarkyti=    new JButton("Tvarkyti pagal Autorių");
    JButton jbSenosKnygos=    new JButton("Atrinkti pasenusias knygas");
    JButton jbGrąžinimas=    new JButton("Atrinkti Skolininkus");
    JPanel panKnygųSar=     new JPanel();
    JPanel panDuomenys=    new JPanel();
    JPanel panAsmensDuo =  new JPanel();
    JPanel panRez =        new JPanel();
    JPanel panMygt =       new JPanel();

    JTextArea VisųKnygųZona= new JTextArea(20,44);
    JScrollPane scrVisZona= new JScrollPane(VisųKnygųZona);
    JTextArea atrinktųZona= new JTextArea(20,44);
    JScrollPane scrAtrZona= new JScrollPane(atrinktųZona);

    public KnyguAnalizė() {
        Container vidus = getContentPane();
        vidus.setLayout(new BoxLayout(vidus, BoxLayout.Y_AXIS));
        vidus.add(panKnygųSar);
        vidus.add(panDuomenys);
        jbReg.addActionListener(new registracijosVeiksmai());
        jbTvarkyti.addActionListener(new tvarkymoVeiksmai());
        jbSenosKnygos.addActionListener(new SenuKnyguVeiksmai());
        jbGrąžinimas.addActionListener(new SkolininkuVeiksmai());
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        išdėstymas();
        kosmetika();
        setVisible(true);
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
        panAsmensDuo.add(new JLabel("Metai"), dėsnis);
        panAsmensDuo.add(new JLabel("Pavadinimas"), dėsnis);
        panAsmensDuo.add(new JLabel("Autorius"), dėsnis);
        panAsmensDuo.add(new JLabel("Ėmimo data"), dėsnis);
        panAsmensDuo.add(new JLabel("Skaitytojas"), dėsnis);
        panAsmensDuo.add(new JLabel("Laikotarpis"), dėsnis);
        panRez.add(new JLabel("Visų skaitytojų kiekis"), dėsnis);
        panRez.add(new JLabel("Dabartinė data"), dėsnis);
        panRez.add(new JLabel("Senų knygų atrinkimo riba"), dėsnis);

        dėsnis.anchor = GridBagConstraints.LINE_START;
        dėsnis.gridx = 1;

        panAsmensDuo.add(tfMetai, dėsnis);
        panAsmensDuo.add(tfPavadinimas, dėsnis);
        panAsmensDuo.add(tfAutorius, dėsnis);
        panAsmensDuo.add(tfRegistroDiena, dėsnis);
        panAsmensDuo.add(tfVartotojas, dėsnis);
        panAsmensDuo.add(tfLaikotarpis, dėsnis);

        panRez.add(tfSKaitytojųKiekis, dėsnis);
        panRez.add(tfDabartinėData, dėsnis);
        panRez.add(tfSenuAtrinkimoRiba, dėsnis);

        panMygt.setLayout(new BoxLayout(panMygt, BoxLayout.Y_AXIS));
        panMygt.add(jbReg);
        panMygt.add(jbTvarkyti);
        panMygt.add(jbSenosKnygos);
        panMygt.add(jbGrąžinimas);
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public class registracijosVeiksmai implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) {
                tfSKaitytojųKiekis.setText(String.format("%3d", ++eilėsNr));
                Book a = new Book(Integer.valueOf(tfMetai.getText().toString()),
                    tfPavadinimas.getText().toString(),
                    tfAutorius.getText().toString(),
                    tfRegistroDiena.getText().toString(),
                    tfVartotojas.getText().toString(),
                    Integer.valueOf(tfLaikotarpis.getText().toString()));
                ap.allBooks.add(a);
                VisųKnygųZona.append(
                        String.format("%3d", eilėsNr)+": "+
                        a.toString()+"\n");
      }
    }
    public class tvarkymoVeiksmai implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
                atrinktųZona.setText("Tvarkyti pagal autoriu:"+ "\n");
                ListKTUx<Book> BookList = new ListKTUx<Book>(new Book());
                BookList = ap.allBooks.clone();
                BookList.sortBurbuliuku(Book.ByAuthor);
                int i = -1;
                for (Book a : BookList){
                i++;
                atrinktųZona.append(
                        String.format("%3d", i)+": "+ 
                        a.toString() +"\n");
                }
        }
    }
    public class SenuKnyguVeiksmai implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            atrinktųZona.setText("Pasenusios knygos:"+ "\n");
            ListKTUx<Book> BookList = ap.ListOfBooks(0, Integer.valueOf(tfSenuAtrinkimoRiba.getText().toString()));
            int i = 0;
            for (Book a : BookList){
            i++;
            atrinktųZona.append(
                    String.format("%3d", i)+": "+ 
                    a.toString() +"\n");
            }
        }
    }
    public class SkolininkuVeiksmai implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            atrinktųZona.setText("Skolininku sarasas:"+ "\n");
            ListKTUx<Book> BookList = ap.BooksToReturn(tfDabartinėData.getText().toString());
            int i = 0;
            for (Book a : BookList){
            i++;
            atrinktųZona.append(
                    String.format("%3d", i)+": "+ 
                    a.toString() +"\n");
            }
        }
    }
}
