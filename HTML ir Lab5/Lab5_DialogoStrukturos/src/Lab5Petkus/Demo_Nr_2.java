package Lab5Petkus;


import dialogai.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Demo_Nr_2 extends JFrame implements ActionListener {

    JTextField tfVardas=   new JTextField("Vardelis", 14);
    JTextField tfPavardė=  new JTextField("Pavardaitis", 14);
    JTextField tfGrupė= new JTextField("0", 4);
    JTextField Pažymys=  new JTextField("0", 3);
    JTextField Vidurkis= new JTextField("0", 6);
    JTextField Skaicius = new JTextField("0", 3);

    JButton jbReg=         new JButton("Registruoti");
    JPanel panDalyviai=    new JPanel();
    JPanel panDuomenys=    new JPanel();
    JPanel panAsmensDuo =  new JPanel();
    JPanel panRez =        new JPanel();
    JPanel panMygt =       new JPanel();

    JTextArea StudentųZona= new JTextArea(12,24);
    JScrollPane scrDalZona=new JScrollPane(StudentųZona);

    public Demo_Nr_2() {
        Container vidus = getContentPane();
        vidus.setLayout(new BoxLayout(vidus, BoxLayout.Y_AXIS));
        vidus.add(panDalyviai);
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
        panDalyviai.add(scrDalZona);
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
        panAsmensDuo.add(new JLabel("Vardas"), dėsnis);
        panAsmensDuo.add(new JLabel("Pavardė"), dėsnis);
        panAsmensDuo.add(new JLabel("Grupė"), dėsnis);
        panAsmensDuo.add(new JLabel("Pažymys"), dėsnis);
        panRez.add(new JLabel("Studentų skaičius"), dėsnis);
        panRez.add(new JLabel("Vidurkis"), dėsnis);

        dėsnis.anchor = GridBagConstraints.LINE_START;
        dėsnis.gridx = 1;

        panAsmensDuo.add(tfVardas, dėsnis);
        panAsmensDuo.add(tfPavardė, dėsnis);
        panAsmensDuo.add(tfGrupė, dėsnis);
        panAsmensDuo.add(Pažymys, dėsnis);
        panRez.add(Skaicius, dėsnis);
        panRez.add(Vidurkis, dėsnis);

        panMygt.add(jbReg);
    }

    public void kosmetika() {
        setTitle("Sąrašai su JTextArea demo - KTU IF 2010");
        panDalyviai.setBorder(new TitledBorder("Varžybų dalyvių sąrašas"));
        panDalyviai.setBackground(Color.lightGray);
        panDuomenys.setBorder(new TitledBorder("Dalyvių duomenys"));
        panDuomenys.setBackground(Color.white);
        panAsmensDuo.setBackground(Color.white);
        panRez. setBackground(Color.lightGray);
        panMygt.setBackground(Color.lightGray);
    }
    private double visaSuma=0;
    private int eilėsNr=0;
    public void actionPerformed(ActionEvent e) {
        try {
            double mok = Double.parseDouble(Pažymys.getText());
            visaSuma+=mok;
            Skaicius.setText(String.format("%3d", ++eilėsNr));
            Vidurkis.setText(String.format("%7.2f", visaSuma / eilėsNr));
            StudentųZona.append(
                    String.format("%3d", eilėsNr)+": "+
                    tfVardas.getText()+ " "+
                    tfPavardė.getText()+ " "+
                    tfGrupė.getText()+" " +
                    Pažymys.getText() + "\n");
        } catch (NumberFormatException exc) {
            JOptionPane.showMessageDialog (this,"Klaidingas mokesčio formatas");
        }
    }
}
