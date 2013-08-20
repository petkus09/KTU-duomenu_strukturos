package Lab5Petkus;


/**
 *
 * @author KTU
 */
import dialogai.*;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import studijosKTU.ListKTUx;

public class KnyguApskaita {
	public  ListKTUx<Book> allBooks = new ListKTUx(new Book());
        public  ListKTUx<Book> SenosKnygos = new ListKTUx(new Book());
        public  ListKTUx<Book> GrazinamosKnygos = new ListKTUx(new Book());
        static Library ap = new Library();

	/**
	 * Tai tik metodo imitacija - panaudokite L2/L3 darbų atrinkimo metodus
	 *
	 * @param taLaukas JTextArea klasės objektas
	 */
        
      public ListKTUx<Book> BooksToReturn(String Date) {
        ListKTUx<Book> NeedToReturn = new ListKTUx(new Book());
      
        for (Book a : allBooks) {
            a.Return(Date);
            if (a.Return(Date)) {
               NeedToReturn.add(a);
            }
         }
         return NeedToReturn;
     }
      
      public ListKTUx<Book> PickByAuthor(String Author) {
        ListKTUx<Book> AuthorBooks = new ListKTUx(new Book());
        for (Book a : allBooks) {
            if (a.getAuthor().equals(Author)) {
              AuthorBooks.add(a);
            }
         }
         return AuthorBooks;
      }
      
      
	public void pagalKaina() {
		allBooks.sortBurbuliuku(Book.byYear);
	}
        public void pagalAutoriu() {
		allBooks.sortBurbuliuku(Book.ByAuthor);
	}
        public void pagalData() {
		allBooks.sortBurbuliuku(Book.byTimePeriodandPickDate);
	}

	/**
	 * Išvedimas į JTextArea lauką
	 * 
	 * @param taLaukas JTextArea klasės objektas
	 */
	public void isvedimas(DefaultTableModel model) {
                for (Book a : allBooks){
                    model.addRow(new Object[]{a.getYear(), 
                        a.getName(), a.getAuthor(), a.getPickDate(), 
                        a.getUser(), a.getTimePeriod()});
                }
	}

	/**
	 * Tai jau turėtų būti naujas sistemines klases Ks metodas
	 *	(arba naujos studijosKTU paketo klasės KsSwing metodas)
	 * @param obj išvedamas objektas
	 * @param ta JTextArea klasės objektas
	 */
    static public void ouSwn(Object obj, JTextArea ta) {
		ta.append(obj.toString() + "\n");
    }
	//-----------------------------------------------------------------------------------

	/**
	 * Failo skaitymas ir jo turinio išvedimas į JTextArea
	 * @param fName File klasės objektas
	 * @param ta JTextArea klasės objektas
	 */
    public void loadAndPrint(File fName, DefaultTableModel model, JTextArea area, JTable table) {
        try {
            BufferedReader fReader =  new BufferedReader(new FileReader(fName));
            String line;
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
            table.getColumn("Laikotarpis").setCellRenderer( rightRenderer );
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
            table.getColumn("Metai").setCellRenderer( centerRenderer );
            while ((line = fReader.readLine()) != null) {
                                 Scanner scan = new Scanner(line);
                                   int Metai = scan.nextInt();
                                    String Pav = scan.next();
                                    String Autor = scan.next();
                                     String Data = scan.next();
                                     String Skaitytojas= scan.next();
                                    int Laikas = scan.nextInt();
                                    Book a = new Book(Metai, Pav, Autor, Data, Skaitytojas, Laikas);
                                    allBooks.add(a);
                                     model.addRow(new Object[]{a.getYear(), 
                        a.getName(), a.getAuthor(), a.getPickDate(), 
                        a.getUser(), a.getTimePeriod()});
                                    
                                
            }
            fReader.close();
        } catch (IOException e) {
            area.append("\n!!! Failo " + fName.getName() + " skaitymo klaida");
        }
	}
    
    
    public void SenosKnygosFunkcija(String metai) {
            SenosKnygos = ListOfBooks(0, Integer.valueOf(metai));
        }
    
    public ListKTUx<Book> ListOfBooks(int year1, int year2) {
      ListKTUx<Book> RangeBooks = new ListKTUx(new Book());
      for (Book a : allBooks) {
         if (a.getYear() >= year1 && a.getYear() <= year2) {
            RangeBooks.add(a);
         }
      }
      return RangeBooks;
   }
    
    public void SkolininkuAtranka(String Date) {
      
      for (Book a : allBooks) {
         a.Return(Date);
         if (a.Return(Date)) {
            GrazinamosKnygos.add(a);
         }
      }
   }
}
