package dialogai;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class AritmetikosDemo extends JFrame implements ActionListener {

    JTextField tfA=      new JTextField("36", 4);  // pradiniai duomenys
    JTextField tfB=      new JTextField("12", 4);  // pradiniai duomenys
    JTextField tfSuma=   new JTextField("  ", 8);  // rezultatų stulpelis
    JTextField tfSkirt=  new JTextField("  ", 8);  // rezultatų stulpelis
    JTextField tfDaugyba=new JTextField("  ", 8);  // rezultatų stulpelis
    JTextField tfDalyba =new JTextField("  ", 8);  // rezultatų stulpelis
    JButton jbSkaičiuoti=new JButton("Skaičiuoti");
    JPanel panParametrai=new JPanel();
    JPanel panPrad =     new JPanel();
    JPanel panRez =      new JPanel();
    JPanel panMygt =     new JPanel();

    public AritmetikosDemo() {
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
        panPrad.add(new JLabel("Kintamasis A"), dėsnis);
        panPrad.add(new JLabel("Kintamasis B"), dėsnis);
        panRez.add(new JLabel("REZ_A + B"), dėsnis);
        panRez.add(new JLabel("REZ_A - B"), dėsnis);
        panRez.add(new JLabel("REZ_A * B"), dėsnis);
        panRez.add(new JLabel("REZ_A / B"), dėsnis);

        dėsnis.anchor = GridBagConstraints.LINE_START;
        dėsnis.gridx = 1;

        panPrad.add(tfA, dėsnis);
        panPrad.add(tfB, dėsnis);

        panRez.add(tfSuma, dėsnis);
        panRez.add(tfSkirt, dėsnis);
        panRez.add(tfDaugyba, dėsnis);
        panRez.add(tfDalyba, dėsnis);

        panMygt.add(jbSkaičiuoti);
    }

    public void kosmetika() {
        setTitle("Aritmetikos demo - KTU IF 2010");
        panParametrai.setBorder(new TitledBorder("Uždavinio parametrai"));
        panParametrai.setBackground(Color.yellow);
        panPrad.setBackground(Color.green);
        panRez. setBackground(Color.lightGray);
        panMygt.setBackground(Color.magenta);
    }

    public void actionPerformed(ActionEvent event) {
        try {
            double a = Double.parseDouble(tfA.getText());
            double b = Double.parseDouble(tfB.getText());
            tfSuma.setText(String.format("%7.2f", a + b));
            tfSkirt.setText(String.format("%7.2f", a - b));
            tfDaugyba.setText(String.format("%7.2f", a * b));
            tfDalyba.setText(String.format("%7.2f", a / b));
        } catch (NumberFormatException exc) {
            JOptionPane.showMessageDialog (this,"Neteisingas formatas A ar B");
        }
    }
}
