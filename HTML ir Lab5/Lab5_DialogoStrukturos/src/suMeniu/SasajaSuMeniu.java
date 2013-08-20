package suMeniu;

/**
 *
 * @author  Aleksas
 */
import dialogai.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;

/**
 * Sąsajos pavyzdys su meniu ir JTextArea elementu duomenu/rezultatų išvedimui
 *
 * !!!! Tai tik pavyzdys, todėl galima laisvai fantazuoti.
 * Panaudokite JTable elementų išvedimui, išplėskite meniu ir t.t
 *
 * @author Aleksas
 */
public class SasajaSuMeniu extends JFrame {

    private JMenuBar meniuBaras = new JMenuBar();
	private Container vidus;
    private JLabel laPiešinys = new JLabel("pradinis piešinukas");
    private Dimension žymėsDydis;
    private JTextArea taInformacija = new JTextArea(15, 30);
    private JScrollPane zona = new JScrollPane(taInformacija);
    private JLabel laAntraste = new JLabel("Rezultatai");
    private JPanel paPiešinukas = new JPanel();
    private boolean rodytiPiešinuką = true;
    private JFrame kviečiantisObjektas = this;
    private JPanel pa1 = new JPanel();
    private AutomobiliuApskaita	apskaita = new AutomobiliuApskaita();

    // sukuriame vidines klases, kai veiksmų yra nemažai ...
    private class VeiksmaiAtidarantFailą implements ActionListener {
			public void actionPerformed( ActionEvent event ) {
				JFileChooser fc = new JFileChooser(".");
                // "." tam, kad rodytų projekto katalogą
				fc.setDialogTitle("Atidaryti failą skaitymui");
				fc.setApproveButtonText("Atidaryti");
				int rez = fc.showOpenDialog(null);
				if (rez == JFileChooser.APPROVE_OPTION) {
					// veiksmai, kai pasirenkamas atsakymas “Open"
					rodytiPiešinuką = false;
					if (!pa1.isShowing()) {
						vidus = getContentPane();
						vidus.setLayout(new FlowLayout());
						vidus.add(pa1);
					}

					File f1 = fc.getSelectedFile();
					apskaita.loadAndPrint(f1, taInformacija);
					paPiešinukas.setVisible(false);
				} else if (rez == JFileChooser.CANCEL_OPTION) {
                        // rodyti pagrindinio lango centre
                    JOptionPane.showMessageDialog( kviečiantisObjektas,
                        "Skaitymo atsisakyta (paspaustas ESC arba Cancel)",
                        "Skaitymas - rašymas", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
        private class VeiksmaiGreitojiPagalba implements ActionListener {
			public void actionPerformed( ActionEvent event ) {
                // Galimos alternatyvos
//				String programa = System.getenv("windir") + File.separatorChar +
//                                "system32" + File.separatorChar + "calc.exe";
				String programa = "C:\\WINDOWS\\system32\\calc.exe";
				try {
					Process p = Runtime.getRuntime().exec(programa);
				} catch (IOException ex) {
					JOptionPane.showMessageDialog( kviečiantisObjektas,
                            "Vykdomasis failas <" + programa + "> nerastas",
                            "Klaida", JOptionPane.ERROR_MESSAGE );
				}
			}
		}
        private class VeiksmaiDokumentacija implements ActionListener {
			public void actionPerformed( ActionEvent event ) {
				File f = null;
				try {
					f = new File("dist\\javadoc\\index.html").getAbsoluteFile();
					Desktop.getDesktop().open(f);
					JOptionPane.showMessageDialog( kviečiantisObjektas,
                            "Dokumentacija sėkmingai atidaryta naršyklės lange",
                            "Informacija", JOptionPane.INFORMATION_MESSAGE );
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog( kviečiantisObjektas,
                            "Dokumentacijos failas <" + f.toString() +
                            "> nerastas(arba dokumentacija dar nesugeneruota)\n"
						, "Klaida", JOptionPane.ERROR_MESSAGE );
				} catch (IOException ex) {
					JOptionPane.showMessageDialog( kviečiantisObjektas,
                        "Dokumentacijos failo <" + f.toString() +
                        "> atidaryti nepavyko", "Klaida", JOptionPane.ERROR_MESSAGE );
				}
			}
		}
    public SasajaSuMeniu() {
        meniuIdiegimas();
    }

    private void meniuIdiegimas() {
        setJMenuBar(meniuBaras);
		JMenu mFailai = new JMenu( "Failai" );
		meniuBaras.add(mFailai);
		JMenu mAuto	= new JMenu( "Automobilių apskaita" );
		mAuto.setMnemonic( 'a' ); // Alt + a
		meniuBaras.add(mAuto);
		JMenu mPagalba = new JMenu( "Pagalba" );
		meniuBaras.add(mPagalba);
		//    Grupė  "Failai" :
		JMenuItem miSkaityti = new JMenuItem( "Skaityti iš failo..." );
		mFailai.add(miSkaityti);
		miSkaityti.addActionListener(new VeiksmaiAtidarantFailą());
		JMenuItem miBaigti = new JMenuItem( "Pabaiga" );
		miBaigti.setMnemonic( 'b' ); // Alt + b
		mFailai.add(miBaigti);
        // kadangi išėjimo iš programos metodas trumpas, rašome vietoje
		miBaigti.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent event ) {
				System.exit(0);
			}
		} );

		//	Grupė "Automobiliu apskaita"
		JMenuItem miKaina = new JMenuItem("Pagal kainą…");
		mAuto.add(miKaina);
		miKaina.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent event ) {

				rodytiPiešinuką = false;
				if (!pa1.isShowing()) {
					vidus = getContentPane();
					vidus.setLayout(new BorderLayout());
					vidus.add(pa1);
				}
				paPiešinukas.setVisible(false);

				apskaita.pagalKaina();
				apskaita.isvedimas(taInformacija);
			}
		} );


        //    Grupė  "Pagalba" :
		JMenuItem miDokumentacija = new JMenuItem( "Paketo Dokumentacija" );
		mPagalba.add(miDokumentacija);
		miDokumentacija.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1,
                ActionEvent.ALT_MASK));
		miDokumentacija.addActionListener(new VeiksmaiDokumentacija() );
		JMenuItem miGreitojiPagalba = new JMenuItem( "Greitoji Pagalba :-)" );
		mPagalba.add(miGreitojiPagalba);
		miGreitojiPagalba.addActionListener(new VeiksmaiGreitojiPagalba() );

		JMenuItem miApie = new JMenuItem( "Apie programą ..." );
		mPagalba.add(miApie);
		miApie.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent event ) {
				JOptionPane.showMessageDialog( kviečiantisObjektas,
				  "Demo programa sąsaja su meniu\nVersija 1.0\n2011 spalis",
                  "Apie...", JOptionPane.INFORMATION_MESSAGE );
			}
		} );

		// Sukuriamas JPanel elementas informacijai išvesti ir padedamas į JFrame langa
		// Pradžioje JPanel elementas nerodomas, o rodomas tik piesinukas.
		pa1.setLayout( new BorderLayout());
		pa1.add(laAntraste, BorderLayout.NORTH);
		pa1.add(zona, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // kad veiktu lango uzdarymas ("kryžiukas")

		// A: Foninė lango spalva
		vidus = getContentPane();
        vidus.setBackground(Color.LIGHT_GRAY);

		// B: Foninis piešinukas, dedamas i lango centrą.
		vidus.setLayout(null);  // piešinukui nustatomas rankinis išdestymas - dydis ir vieta bus nustatoma metodu setLocation
		String failas = "piesinukai" + File.separatorChar + "travelbug.gif"; // failas ten kur class failai
		laPiešinys.setIcon( new ImageIcon( getClass().getResource(failas ))) ;
		paPiešinukas.setBackground(Color.LIGHT_GRAY);
		paPiešinukas.add(laPiešinys);
		paPiešinukas.setBorder(new LineBorder(Color.GRAY));
		add(paPiešinukas);
		žymėsDydis = paPiešinukas.getPreferredSize();
		paPiešinukas.setSize(žymėsDydis); // kitaip getSize piešime duos 0,0
		validate(); // validate paparastai kviečiamas po konteinerio turinio pakeitimo (kontrolei ir "perpiešimui")
		this.addComponentListener(new JFrameLangoVieta()); // Kad "travelbug" išliktų centre keiciant lango matmenis.

    }

	// Ivykio realizaciaja su ComponentAdapter klase tam, kad neįdieginėt nereikalingų likusių trijų ComponentListener metodų
	private class JFrameLangoVieta extends ComponentAdapter {
		@Override
		public void componentResized(ComponentEvent e) {
			if (rodytiPiešinuką) {
				Dimension d = getSize();
				paPiešinukas.setLocation(d.width/2 - žymėsDydis.width/2,
								d.height/2 - žymėsDydis.height/2 - meniuBaras.getPreferredSize().height);
				validate();
				// P.S. Jei tiksliau centruoti, reiketų dar įvertinti ir JFrame lango titulinę eilutį
			} else
                paPiešinukas.setVisible(false);

		}
	}

    public static void main(String[] args) {
        SasajaSuMeniu langas = new SasajaSuMeniu();
        langas.setSize(500, 400);
        langas.setLocation(200, 200);
        langas.setTitle("LD5 demo: Vartotojo sąsajos su meniu pavyzdys");
        langas.setVisible(true);
    }

}
