
package Lab6Petkus_OldVersion;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;


public class LibraryMethods {
    static LinkedList<Book> test=new LinkedList<Book>();
    static ObjektuRasymas Irasymas = new ObjektuRasymas();
    public static void MethodOptions(){
        BooksCheck();
        FormBookList();
        OldestBooks();
        CheckSorting();
        //LibraryInterface.ClientInterface();
        //LibrarySpeedTest.ResearchChoices();
    }
    public static void BooksCheck() {
        Book a1 = new Book(2000, "Knyga Nr. 1", "Autorius Nr. 1", "2000-06-23", "Lankytojas Nr. 1", 31);
        Book a2 = new Book(2001, "Knyga Nr. 2", "Autorius Nr. 2", "2001-03-12", "Lankytojas Nr. 2", 62);
        Book a3 = new Book(2002, "Knyga Nr. 3", "Autorius Nr. 3", "2011-01-01", "Lankytojas Nr. 1", 7);
        Book a4 = new Book(2000, "Knyga Nr. 1", "Autorius Nr. 1", "2000-06-23", "Lankytojas Nr. 3", 4);
        Book a5 = new Book(2009, "Knyga Nr. 4", "Autorius Nr. 4", "1980-12-23", "Lankytojas Nr. 4", 1000);
        Book a6 = new Book(1980, "Knyga Nr. 5", "Autorius Nr. 5", "1992-03-15", "Lankytojas Nr. 1", 100);
        LinkedList<Book> Bandymas1 = new LinkedList<Book>();
        Bandymas1.add(a1);
        Bandymas1.add(a2);
        Bandymas1.add(a3);
        Bandymas1.add(a4);
        Bandymas1.add(a5);
        Bandymas1.add(a6);
        Irasymas.IrasymoVeiksmai("BookResults.txt", Bandymas1, "Pirmas nuskaitymo bandymas");
    }
    public static void FormBookList() {
        Book a1 = new Book(2000, "Knyga Nr. 1", "Autorius Nr. 1", "2000-06-23", "Lankytojas Nr. 1", 31);
        Book a2 = new Book(2001, "Knyga Nr. 2", "Autorius Nr. 2", "2001-03-12", "Lankytojas Nr. 2", 62);
        Book a3 = new Book(2002, "Knyga Nr. 3", "Autorius Nr. 3", "2011-01-01", "Lankytojas Nr. 1", 7);
        Book a4 = new Book(2000, "Knyga Nr. 1", "Autorius Nr. 1", "2000-06-23", "Lankytojas Nr. 1", 31);
        LinkedList<Book> Bandymas2 = new LinkedList<Book>();
        Bandymas2.add(a1);
        Bandymas2.add(a2);
        Bandymas2.add(a3);
        Bandymas2.add(a4);
        Irasymas.IrasymoVeiksmai("BookResults.txt", Bandymas2, "Nepilnas sarasas:");
        Book a5 = new Book(2001, "Knyga Nr. 2", "Autorius Nr. 2", "2001-03-12", "Lankytojas Nr. 2", 62);
        Book a6 = new Book(1980, "Knyga Nr. 5", "Autorius Nr. 5", "1992-03-15", "Lankytojas Nr. 1", 100);
        Bandymas2.add(a5);
        Bandymas2.add(a6);
        test = Bandymas2;
        Irasymas.IrasymoVeiksmai("BookResults.txt", Bandymas2, "Papildytas sarasas:");
        Collections.sort(Bandymas2, Book.ByAuthor);
        Irasymas.IrasymoVeiksmai("BookResults.txt", Bandymas2, "Surikiuotas pagal autorius:");
   }
    public static void OldestBooks(){
        LinkedList<Book> OldBooks = new LinkedList<Book>();
        
       for (Book a : test) {
         if (a.getYear() < 1990) {
            OldBooks.add(a);
         }
      }
      Irasymas.IrasymoVeiksmai("BookResults.txt", OldBooks, "Pasenusios knygos (iki 1990):");
    }
    public static void CheckSorting(){
        LinkedList<Book> testavimas = new LinkedList<Book>();
        ObjektuSkaitymas Skaitymas = new ObjektuSkaitymas();
        Skaitymas.SkaitymoVeiksmai("Books.txt", testavimas);
        /*Irasymas.IrasymoVeiksmai("BookResults.txt", testavimas, "Pradiniai Duomenys:");
        Collections.sort(testavimas, Book.ByAuthor);
        Irasymas.IrasymoVeiksmai("BookResults.txt", testavimas, "Sort by Author:");
        Collections.sort(testavimas, Book.byYear);
        Irasymas.IrasymoVeiksmai("BookResults.txt", testavimas, "Sort By Year:");
        Collections.sort(testavimas, Book.byTimePeriodandPickDate);
        Irasymas.IrasymoVeiksmai("BookResults.txt", testavimas, "Sort by Time Period and Pick Date:");
        Collections.sort(testavimas);
        Irasymas.IrasymoVeiksmai("BookResults.txt", testavimas, "Sort By compareT:");*/
    }

    static Comparator ByYear = new Comparator() {
       public int compare(Object o1, Object o2) {
          int r1 = ((Book) o1).getYear();
          int r2 = ((Book) o2).getYear();
          if(r1<r2) {return 1;}
          if(r1>r2) {return -1;}
          return 0;
       }
    };
    
    
    
    
}
