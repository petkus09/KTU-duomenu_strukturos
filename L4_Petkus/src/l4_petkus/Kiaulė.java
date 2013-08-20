package l4_petkus;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Kiaulė extends JFrame implements ActionListener {
    
    
    
    JTextField tfMetai= new JTextField("2010", 8);
    JTextField tfPavadinimas= new JTextField("Knygos Pavadinimas", 20);
    JTextField tfAutorius= new JTextField("Autorius", 18);
    JTextField tfRegistroDiena= new JTextField("2010-01-01", 10);
    JTextField tfVartotojas= new JTextField("Klientas", 18);
    JTextField tfLaikotarpis= new JTextField("31", 3);
    JTextField tfDabartinėData= new JTextField("2012-10-31", 10);
    JTextField tfSKaitytojųKiekis= new JTextField("", 3);
    JTextField tfSenuAtrinkimoRiba= new JTextField("1990", 4);

    JButton jbŽaisti=         new JButton("Pradėti Žaidimą");
    JPanel ŽaidimoInfo =  new JPanel();
    JPanel Žaidėjas1=     new JPanel();
    JPanel Žaidėjas2=     new JPanel();
    JPanel Statistika =   new JPanel();
    JPanel ŽaidimoPradžia =      new JPanel();
    JPanel Žaidėjas1Švieslentė =   new JPanel();
    JPanel Žaidėjas2Švieslentė =   new JPanel();
    JPanel panDuomenys=    new JPanel();
    JPanel panAsmensDuo =  new JPanel();
    JPanel panRez =        new JPanel();
    JPanel panMygt =       new JPanel();

    JScrollPane ŽaidimoLangas = new JScrollPane();
    JTextArea Žaidėjas1RezLangas= new JTextArea(2,60);
    JScrollPane Žaidėjas1Rez= new JScrollPane(Žaidėjas1RezLangas);
    JTextArea Žaidėjas2RezLangas= new JTextArea(2,60);
    JScrollPane Žaidėjas2Rez= new JScrollPane(Žaidėjas2RezLangas);

    public Kiaulė() {
        Container vidus = getContentPane();
        vidus.setLayout(new BoxLayout(vidus, BoxLayout.Y_AXIS));
        vidus.add(ŽaidimoInfo);
        vidus.add(Žaidėjas1);
        vidus.add(Žaidėjas2);
        vidus.add(panDuomenys);
        jbŽaisti.addActionListener(new PradėtiŽaidimą());
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        išdėstymas();
        kosmetika();
        setVisible(true);
        pack();
    }
    public void išdėstymas() {
        setLocation(500, 20);
        // sudedame duomenis į paneles
        ŽaidimoInfo.add(ŽaidimoPradžia);
        ŽaidimoInfo.add(ŽaidimoLangas);
        ŽaidimoPradžia.add(jbŽaisti);
        ŽaidimoInfo.add(Statistika);
        Žaidėjas1.add(Žaidėjas1Rez);
        Žaidėjas2.add(Žaidėjas2Rez);
        panDuomenys.add(panAsmensDuo);
        panDuomenys.add(panRez);
        panDuomenys.add(panMygt);
        ŽaidimoInfo.setLayout(new BoxLayout(ŽaidimoInfo, BoxLayout.X_AXIS));

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
    }

    public void kosmetika() {
        setTitle("Knygų sąrašų demo - KTU IF 2012");
        ŽaidimoInfo.setBorder(new TitledBorder(
                "Žaidimas"));
        ŽaidimoInfo.setBackground(Color.LIGHT_GRAY);
        Žaidėjas1.setBorder(new TitledBorder(
                "Žaidėjas Nr. 1"));
        Žaidėjas1.setBackground(Color.red);
        Žaidėjas2.setBorder(new TitledBorder(
                "Žaidėjas Nr. 2"));
        Žaidėjas2.setBackground(Color.blue);
        Statistika.setBorder(new TitledBorder(
                "Statistika"));
        Statistika.setBackground(Color.gray);
        ŽaidimoPradžia.setBorder(new TitledBorder(
                "Žaidimo Pradžia"));
        ŽaidimoPradžia.setBackground(Color.gray);
        panDuomenys.setBorder(new TitledBorder("Knygų duomenys"));
        panDuomenys.setBackground(Color.lightGray);
        panAsmensDuo.setBackground(Color.white);
        panRez. setBackground(Color.white);
        panMygt.setBackground(Color.white);
    }

    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public class PradėtiŽaidimą implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) {
                
      }
    }
}
