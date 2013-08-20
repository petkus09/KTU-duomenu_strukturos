
package Lab2Petkus;

import studijosKTU.Ks;
import studijosKTU.ListKTUx;

public class LibraryMethods {
    static ListKTUx<Book> test=new ListKTUx (new Book());

    public static void MethodOptions(){
        BooksCheck();
        FormBookList();
        SortByAuthor();
        OldestBooks();
    }
    public static void BooksCheck() {
        Book a1 = new Book(2000, "Knyga Nr. 1", "Autorius Nr. 1", "2000-06-23", "Lankytojas Nr. 1", 31);
        Book a2 = new Book(2001, "Knyga Nr. 2", "Autorius Nr. 2", "2001-03-12", "Lankytojas Nr. 2", 62);
        Book a3 = new Book(2002, "Knyga Nr. 3", "Autorius Nr. 3", "2011-01-01", "Lankytojas Nr. 1", 7);
        Book a4 = new Book(2000, "Knyga Nr. 1", "Autorius Nr. 1", "2000-06-23", "Lankytojas Nr. 3", 4);
        Book a5 = new Book(2009, "Knyga Nr. 4", "Autorius Nr. 4", "1980-12-23", "Lankytojas Nr. 4", 1000);
        Book a6 = new Book(1980, "Knyga Nr. 5", "Autorius Nr. 5", "1992-03-15", "Lankytojas Nr. 1", 100);

        Ks.oun(a1);
        Ks.oun(a2);
        Ks.oun(a3);
        Ks.oun("First 3 authors:= "+
                a1.getAuthor()+a2.getAuthor()+a3.getAuthor());
        Ks.oun(a4);
        Ks.oun(a5);
        Ks.oun(a6);
        Ks.oun("Next 3 Book names:= "+
                a4.getName()+" " +a5.getName()+" "+a6.getName());
    }
    public static void FormBookList() {
        Book a1 = new Book(2000, "Knyga Nr. 1", "Autorius Nr. 1", "2000-06-23", "Lankytojas Nr. 1", 31);
        Book a2 = new Book(2001, "Knyga Nr. 2", "Autorius Nr. 2", "2001-03-12", "Lankytojas Nr. 2", 62);
        Book a3 = new Book(2002, "Knyga Nr. 3", "Autorius Nr. 3", "2011-01-01", "Lankytojas Nr. 1", 7);
        Book a4 = new Book(2000, "Knyga Nr. 1", "Autorius Nr. 1", "2000-06-23", "Lankytojas Nr. 1", 31);
        test.add(a1);
        test.add(a2);
        test.add(a3);
        test.add(a4);
        test.println("All Books:");
         for (Book a = test.get(0); a != null; a = test.getNext()) {
             Ks.oun(a.toString());
         }
        Book a5 = new Book(2001, "Knyga Nr. 2", "Autorius Nr. 2", "2001-03-12", "Lankytojas Nr. 2", 62);
        Book a6 = new Book(1980, "Knyga Nr. 5", "Autorius Nr. 5", "1992-03-15", "Lankytojas Nr. 1", 100);
        test.add(a5);
        test.add(a6);
        test.println("Expanded list:");
         for (Book a = test.get(0); a != null; a = test.getNext()) {
             Ks.oun(a.toString());
         }
         for (int i = 0; i < 6; i++)
         {
             Ks.oun("Elementas nr. "+ i+ " dubliuojasi: " + test.check(i, test.get(i)));
         }
   }
    public static void SortByAuthor(){
        int sk=0;
        for (Book a=test.get(1); a!=null; a=test.getNext()){
            if (a.getAuthor().compareTo("Autorius Nr. 1")==0)
            { sk++;}
        }
        Ks.oun("Books with author Autorius Nr. 1 number: "+sk);
    }
    public static void OldestBooks(){
        ListKTUx<Book> OldBooks = new ListKTUx(new Book());
        
       for (Book a = test.get(0); a != null; a = test.getNext()) {
         if (a.getYear() < 1990) {
            OldBooks.add(a);
         }
      }
      for (Book a = OldBooks.get(0); a != null; a = OldBooks.getNext()) {
          Ks.oun(a.toString());
      }
    }
}
