package Lab5Petkus;


import dialogai.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Demo_Nr_1 extends JFrame implements ActionListener {

    JTextField PradinisKampas=      new JTextField("90", 4);  // pradiniai duomenys
    JTextField Sinusas=   new JTextField("  ", 8);  // rezultatų stulpelis
    JTextField Kosinusas=  new JTextField("  ", 8);  // rezultatų stulpelis
    JTextField Tangentas=new JTextField("  ", 8);  // rezultatų stulpelis
    JTextField Kotangentas =new JTextField("  ", 8);  // rezultatų stulpelis
    JButton jbSkaičiuoti=new JButton("Skaičiuoti");
    JPanel panParametrai=new JPanel();
    JPanel panPrad =     new JPanel();
    JPanel panRez =      new JPanel();
    JPanel panMygt =     new JPanel();

    public Demo_Nr_1() {
        Container vidus = getContentPane();
        vidus.add(panParametrai);
        jbSkaičiuoti.addActionListener(this);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        išdėstymas();
        kosmetika();
        setVisible(true);
        pack();
    }
    static private int pradX=100;
    static private int pradY=50;
    public void išdėstymas() {
        setLocation(pradX+=30, pradY+=30);
        panParametrai.add(panPrad);
        panParametrai.add(panRez);
        panParametrai.add(panMygt);

        GridBagLayout dėstymoBūdas = new GridBagLayout();
        GridBagConstraints dėsnis = new GridBagConstraints();
        panPrad.setLayout(dėstymoBūdas);
        panRez.setLayout(dėstymoBūdas);

        dėsnis.fill = GridBagConstraints.NONE;
        dėsnis.insets = new Insets(5, 8, 0, 6);

        dėsnis.anchor = GridBagConstraints.LINE_END;
        dėsnis.gridy = GridBagConstraints.RELATIVE;
        dėsnis.gridx = 0;
        panPrad.add(new JLabel("Pradinis Kampas"), dėsnis);
        panRez.add(new JLabel("sin()"), dėsnis);
        panRez.add(new JLabel("cos()"), dėsnis);
        panRez.add(new JLabel("tg()"), dėsnis);
        panRez.add(new JLabel("ctg()"), dėsnis);

        dėsnis.anchor = GridBagConstraints.LINE_START;
        dėsnis.gridx = 1;

        panPrad.add(PradinisKampas, dėsnis);

        panRez.add(Sinusas, dėsnis);
        panRez.add(Kosinusas, dėsnis);
        panRez.add(Tangentas, dėsnis);
        panRez.add(Kotangentas, dėsnis);

        panMygt.add(jbSkaičiuoti);
    }

    public void kosmetika() {
        setTitle("Demo Nr 1 - KTU IF 2012");
        panParametrai.setBorder(new TitledBorder("Uždavinio parametrai"));
        panParametrai.setBackground(Color.lightGray);
        panPrad.setBackground(Color.white);
        panRez. setBackground(Color.white);
        panMygt.setBackground(Color.white);
    }

    public void actionPerformed(ActionEvent event) {
        try {
            double a = Double.parseDouble(PradinisKampas.getText()) / 180.0 * Math.PI;
            Sinusas.setText(String.format("%7.2f", Math.sin(a)));
            Kosinusas.setText(String.format("%7.2f", Math.cos(a)));
            Tangentas.setText(String.format("%7.2f", Math.tan(a)));
            Kotangentas.setText(String.format("%7.2f", 1/Math.tan(a)));
        } catch (NumberFormatException exc) {
            JOptionPane.showMessageDialog (this,"Neteisingas formatas A");
        }
    }
}
