package dialogai;

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
    JButton jbAritm= new JButton("Aritmetikos Demo");
    JButton jbDalyv= new JButton("Dalyvių registracija");
    JButton jbAuto= new JButton("Auto analizė");
    JButton jbPatys= new JButton("Sąsaja su meniu");

    public PradinisLangas() {
        jbPirmas.addActionListener(this);
        jbAritm.addActionListener(this);
        jbDalyv.addActionListener(this);
        jbAuto.addActionListener(this);
        jbPatys.addActionListener(this);
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
        vidus.add(jbAritm, BorderLayout.EAST);
        vidus.add(jbDalyv, BorderLayout.SOUTH);
        vidus.add(jbAuto, BorderLayout.WEST);
        vidus.add(jbPatys, BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent event) {
       Object mygtukasX = event.getSource();
       if(mygtukasX==jbPirmas){
          JOptionPane.showMessageDialog (this,"Jau veikia pirmasis mygtukas");
          return;
       }
       if(mygtukasX==jbAritm) new AritmetikosDemo();
       if(mygtukasX==jbDalyv) new DalyviuRegistracija();
       if(mygtukasX==jbAuto)  new AutoAnalize();
       if(mygtukasX==jbPatys)
          JOptionPane.showMessageDialog (this, "Šis demo variantas randasi atskirame pakete 'suMeniu'\n  (failas \"SasajaSuMeniu.java\")",
                  "Vartotojo Sasaja - Demo", JOptionPane.INFORMATION_MESSAGE);

    }
    public static void main(String[] args) {
        new PradinisLangas();
    }

}
