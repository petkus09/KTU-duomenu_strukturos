package Lab5Petkus;

import dialogai.*;
import suMeniu.SasajaSuMeniu;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Pagrindinis sąsajos langas su dialoginių langų demonstracija
 *
 * @author eimutis
 */
public class PradinisLangas extends JFrame implements ActionListener {

    JButton jbPirmas= new JButton("Pats pirmasis mygtukas ");
    JButton jbAritm1= new JButton("Aritmetikos Demo 1");
    JButton jbAritm2= new JButton("Aritmetikos Demo 2");
    JButton jbKnygos= new JButton("Knygų analizė");
    JButton jbApie = new JButton("Apie");

    public PradinisLangas() {
        jbPirmas.addActionListener(this);
        jbAritm1.addActionListener(this);
        jbAritm2.addActionListener(this);
        jbKnygos.addActionListener(this);
        jbApie.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setTitle("L5 darbo demo variantas");
        išdėstymas();
        setVisible(true);
    }
    public void išdėstymas() {
        setLocation(400, 300);
        Container vidus = getContentPane();
        vidus.setBackground(Color.LIGHT_GRAY);
//        vidus.setLayout(new BorderLayout());
        vidus.setLayout(new FlowLayout());
//        vidus.setLayout(new BoxLayout(vidus, BoxLayout.Y_AXIS));
        vidus.add(jbPirmas, BorderLayout.NORTH);
        vidus.add(jbAritm1, BorderLayout.EAST);
        vidus.add(jbAritm2, BorderLayout.WEST);
        vidus.add(jbKnygos, BorderLayout.SOUTH);
        vidus.add(jbApie, BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent event) {
       Object mygtukasX = event.getSource();
       if(mygtukasX==jbPirmas){
          JOptionPane.showMessageDialog (this,"Jau veikia pirmasis mygtukas");
          return;
       }
       if(mygtukasX==jbAritm1) new Demo_Nr_1();
       if(mygtukasX==jbAritm2) new Demo_Nr_2();
       if(mygtukasX==jbKnygos)  new KnyguAnalizė();
       if(mygtukasX==jbApie) JOptionPane.showMessageDialog (this, "Šią programą"
               + " sukūrė IF-1/9 gr. studentas Tautvydas Petkus\n  Programa gali"
               + " registruoti sąrašus bibliotekoje, juos atrinkitėti ir "
               + "pavaizduoti vartotojo sąsajos Swing galimybes\n\n"
               + "2012 Kaunas",
                  "Vartotojo Sasaja - Lab5Petkus", JOptionPane.INFORMATION_MESSAGE);

    }
    public static void main(String[] args) {
        new PradinisLangas();
    }

}
