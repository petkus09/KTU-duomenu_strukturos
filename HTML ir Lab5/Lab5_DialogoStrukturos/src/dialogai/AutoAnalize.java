package dialogai;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class AutoAnalize extends JFrame implements ActionListener {

    JTextField tfMarkė=   new JTextField("Renault", 8);
    JTextField tfModelis=  new JTextField("Megane", 8);
    JTextField tfGamMetai= new JTextField("2007", 4);
    JTextField tfRida=     new JTextField("9800", 5);
    JTextField tfVisoAuto= new JTextField("0", 3);
    JTextField tfVisoRida= new JTextField("0", 5);
    JTextField tfVisaSuma= new JTextField("0", 6);

    JButton jbReg=         new JButton("Registruoti");
    JButton jbAtrinkti=    new JButton("Atrinkti");
    JButton jbPridėti=     new JButton("Pridėti");
    JPanel panKnygSar=     new JPanel();
    JPanel panDuomenys=    new JPanel();
    JPanel panAsmensDuo =  new JPanel();
    JPanel panRez =        new JPanel();
    JPanel panMygt =       new JPanel();

    JTextArea visųAutoZona= new JTextArea(12,24);
    JScrollPane scrVisZona= new JScrollPane(visųAutoZona);
    JTextArea atrinktųZona= new JTextArea(12,24);
    JScrollPane scrAtrZona= new JScrollPane(atrinktųZona);

    public AutoAnalize() {
        Container vidus = getContentPane();
        vidus.setLayout(new BoxLayout(vidus, BoxLayout.Y_AXIS));
        vidus.add(panKnygSar);
        vidus.add(panDuomenys);
        jbReg.addActionListener(this);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        išdėstymas();
        kosmetika();
        setVisible(true);
        pack();
    }
    public void išdėstymas() {
        setLocation(500, 20);
        // sudedame duomenis į paneles
        panKnygSar.add(scrVisZona);
        panKnygSar.add(scrAtrZona);
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
        panAsmensDuo.add(new JLabel("Markė"), dėsnis);
        panAsmensDuo.add(new JLabel("Modelis"), dėsnis);
        panAsmensDuo.add(new JLabel("Gam.metai"), dėsnis);
        panAsmensDuo.add(new JLabel("Rida (km)"), dėsnis);
        panRez.add(new JLabel("Visų auto kiekis"), dėsnis);
        panRez.add(new JLabel("Bendra rida"), dėsnis);

        dėsnis.anchor = GridBagConstraints.LINE_START;
        dėsnis.gridx = 1;

        panAsmensDuo.add(tfMarkė, dėsnis);
        panAsmensDuo.add(tfModelis, dėsnis);
        panAsmensDuo.add(tfGamMetai, dėsnis);
        panAsmensDuo.add(tfRida, dėsnis);

        panRez.add(tfVisoAuto, dėsnis);
        panRez.add(tfVisoRida, dėsnis);

        panMygt.setLayout(new BoxLayout(panMygt, BoxLayout.Y_AXIS));
        panMygt.add(jbReg);
        panMygt.add(jbAtrinkti);
        panMygt.add(jbPridėti);
    }

    public void kosmetika() {
        setTitle("Automobilių sąrašų demo - KTU IF 2010");
        panKnygSar.setBorder(new TitledBorder(
                "Visų automobilių ir atrinktų automobilių sąrašai"));
        panKnygSar.setBackground(Color.yellow);
        panDuomenys.setBorder(new TitledBorder("Automobilių duomenys"));
        panDuomenys.setBackground(Color.lightGray);
        panAsmensDuo.setBackground(Color.green);
        panRez. setBackground(Color.gray);
        panMygt.setBackground(Color.magenta);
    }
    private int eilėsNr=0;
    private double visaRida=0;
    public void actionPerformed(ActionEvent e) {
        try {
            double rida = Double.parseDouble(tfRida.getText());
            visaRida+=rida;
            tfVisoAuto.setText(String.format("%3d", ++eilėsNr));
            tfVisoRida.setText(String.format("%7.0f", visaRida));
            visųAutoZona.append(
                    String.format("%3d", eilėsNr)+": "+
                    tfMarkė.getText()+ " "+
                    tfModelis.getText()+ " "+
                    tfGamMetai.getText()+" "+
                    tfRida.getText()+"\n");
        } catch (NumberFormatException exc) {
            JOptionPane.showMessageDialog (this,"Klaidingas mokesčio formatas");
        }
    }
}
