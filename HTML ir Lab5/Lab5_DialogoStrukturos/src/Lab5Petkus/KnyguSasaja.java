package Lab5Petkus;

/**
 *
 * @author  Aleksas
 */
import suMeniu.*;
import dialogai.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import studijosKTU.ListKTUx;


public class KnyguSasaja extends JFrame {
    Object[] Objektas = new Object[]{"Metai", "Pavadinimas", "Autorius", "Emimo Data", "Skaitytojas", "Laikotarpis"};
    DefaultTableModel model = new DefaultTableModel(); 
    JTable table = new JTable(model); 
    
    
    //new Object[]{"Metai", "Pavadinimas", "Autorius", "Emimo Data", "Skaitytojas", "Laikotarpis"}
    private JMenuBar meniuBaras = new JMenuBar();
	private Container vidus;
    private JLabel laPiešinys = new JLabel("Studentų Biblioteka");
    private Dimension žymėsDydis;
    private JTextArea taInformacija = new JTextArea(15, 30);
    private JScrollPane zona = new JScrollPane(table);
    private JLabel laAntraste = new JLabel("Rezultatai");
    private JPanel paPiešinukas = new JPanel();
    private boolean rodytiPiešinuką = true;
    private JFrame kviečiantisObjektas = this;
    private JPanel pa1 = new JPanel();
    private KnyguApskaita apskaita = new KnyguApskaita();

  
    private class VeiksmaiAtidarantFailą implements ActionListener {
			public void actionPerformed( ActionEvent event ) {
				JFileChooser fc = new JFileChooser(".");
    
				fc.setDialogTitle("Atidaryti failą skaitymui");
				fc.setApproveButtonText("Atidaryti");
				int rez = fc.showOpenDialog(null);
				if (rez == JFileChooser.APPROVE_OPTION) {
	
					rodytiPiešinuką = false;
					if (!pa1.isShowing()) {
						vidus = getContentPane();
						vidus.setLayout(new BorderLayout());
						vidus.add(pa1);
					}

					File f1 = fc.getSelectedFile();
					apskaita.loadAndPrint(f1, model, taInformacija, table);
					paPiešinukas.setVisible(false);
                                        loadDataToTable(apskaita.allBooks, model);
                                        
                                        
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
    public KnyguSasaja() {
        meniuIdiegimas();
    }

    private void meniuIdiegimas() {
        setJMenuBar(meniuBaras);
                model.addColumn("Metai");
                model.addColumn("Pavadinimas");
                model.addColumn("Autorius");
                model.addColumn("Data");
                model.addColumn("Skaitytojas");
                model.addColumn("Laikotarpis");
		JMenu mFailai = new JMenu( "Failai" );
		meniuBaras.add(mFailai);
		JMenu mAuto	= new JMenu( "Knygų apskaita" );
		mAuto.setMnemonic( 'a' ); // Alt + a
		meniuBaras.add(mAuto);
                JMenu mAtranka	= new JMenu( "Atrankos" );
		meniuBaras.add(mAtranka);
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

		JMenuItem miSeni = new JMenuItem("Atrinkti Pasenusias knygas...");
		mAtranka.add(miSeni);
		miSeni.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent event ) {
                                String metai = JOptionPane.showInputDialog("Iveskite Metus");
				rodytiPiešinuką = false;
				paPiešinukas.setVisible(false);
                                
				apskaita.SenosKnygosFunkcija(metai);
				loadDataToTable(apskaita.SenosKnygos, model);

			}
		} );
                
                JMenuItem miSkola = new JMenuItem("Atrinkti Skolininkus...");
		mAtranka.add(miSkola);
		miSkola.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent event ) {
                                String Data = JOptionPane.showInputDialog("Iveskite dabartine data");
				rodytiPiešinuką = false;
				paPiešinukas.setVisible(false);
                                
				apskaita.SkolininkuAtranka(Data);
				loadDataToTable(apskaita.GrazinamosKnygos, model);

			}
		} );
                
		JMenuItem miMetai = new JMenuItem("Pagal metus…");
		mAuto.add(miMetai);
		miMetai.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent event ) {

				rodytiPiešinuką = false;
				paPiešinukas.setVisible(false);
                                
				apskaita.pagalKaina();
				loadDataToTable(apskaita.allBooks, model);

			}
		} );
                
                JMenuItem miAutorius = new JMenuItem("Pagal Autoriu…");
		mAuto.add(miAutorius);
		miAutorius.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent event ) {

				rodytiPiešinuką = false;

				paPiešinukas.setVisible(false);

				apskaita.pagalAutoriu();;
				loadDataToTable(apskaita.allBooks, model);
			}
		} );
                
                JMenuItem miData = new JMenuItem("Pagal Data ir Laikotarpi…");
		mAuto.add(miData);
		miData.addActionListener(new ActionListener() {
			public void actionPerformed( ActionEvent event ) {

				rodytiPiešinuką = false;

				paPiešinukas.setVisible(false);

				apskaita.pagalData();
				loadDataToTable(apskaita.allBooks, model);
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
				  "Šią programą"
               + " sukūrė IF-1/9 gr. studentas Tautvydas Petkus\n  Programa gali"
               + " registruoti sąrašus bibliotekoje, juos atrinkitėti ir "
               + "pavaizduoti vartotojo sąsajos Swing galimybes\n\n"
               + "2012 Kaunas",
                  "Apie...", JOptionPane.INFORMATION_MESSAGE );
			}
		} );

		// Sukuriamas JPanel elementas informacijai išvesti ir padedamas į JFrame langa
		// Pradžioje JPanel elementas nerodomas, o rodomas tik piesinukas.
		pa1.setLayout( new BorderLayout());
		pa1.add(laAntraste, BorderLayout.NORTH);
		pa1.add(zona, BorderLayout.CENTER);


		//setDefaultCloseOperation(Applet.); // kad veiktu lango uzdarymas ("kryžiukas")

		// A: Foninė lango spalva
		vidus = getContentPane();
                vidus.setBackground(Color.LIGHT_GRAY);

		// B: Foninis piešinukas, dedamas i lango centrą.
		vidus.setLayout(null);  // piešinukui nustatomas rankinis išdestymas - dydis ir vieta bus nustatoma metodu setLocation
		String failas = "piesinukai" + File.separatorChar + "Studentai.gif"; // failas ten kur class failai
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
        
        KnyguSasaja langas = new KnyguSasaja();
        langas.setSize(800, 500);
        langas.setLocation(200, 200);
        langas.setVisible(true);
    }
    
    private void loadDataToTable(ListKTUx<Book> Books, DefaultTableModel model){
        for (int i = model.getRowCount() - 1; i >= 0 ;i--){
            model.removeRow(i);
        }
        for (Book k : Books){
            Object [] dataRow = {k.getYear(), k.getName(), k.getAuthor(),
                k.getPickDate(), k.getUser(),
                k.getTimePeriod()};
            model.addRow(dataRow);
        }
    }

}
