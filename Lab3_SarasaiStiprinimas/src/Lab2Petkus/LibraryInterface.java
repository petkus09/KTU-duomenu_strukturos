/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Petkus;

import studijosKTU.Ks;
import studijosKTU.ListKTUx;

/**
 *
 * @author Tautvydas
 */
public class LibraryInterface {
    static ListKTUx<Book> bandomieji=new ListKTUx (new Book());
    static Library ap = new Library();
//------------------------------------------------------------------------------
    public static void ClientInterface() {
      ListKTUx<Book> BookList = 
              new ListKTUx<Book>(new Book());
      int varNr;  
      String dialog = "Options: "
            + "1-Read from a file; 2-expand the list; "
            + "3-books to be returned;\n    4-list of books ; "
            + "5-sort by Author; 0-End> ";
      while ((varNr = Ks.giveInt(dialog,0,5)) != 0) {
         if (varNr == 1) {
            ap.allBooks.load(Ks.giveFileName());
            ap.allBooks.println("List of all books");
         } else {
            if (varNr == 2) {
               String autoD = Ks.giveString("Type in Year, "+
                            "Name, Author, Pick Date, User and Time Period\n ");
               Book a=new Book(autoD);
               String Error=a.validate();
               if (Error.length()==0)
                   ap.allBooks.add(a);
               else
                 Ks.oun("Book was not accepted "+Error);
            } else { 
               switch (varNr) {
                  case 3:
                     String autoD = Ks.giveString("Type in the current day +"
                             + "YYYY-MM-DD\n ");
                     BookList = ap.BooksToReturn(autoD);
                     break;
                  case 4:
                     int r1 = Ks.giveInt("Type the start year YYYY: ");
                     int r2 = Ks.giveInt("Type the end year YYYY: ");
                     BookList = ap.ListOfBooks(r1, r2);
                     break;
                  case 5:
                     String Author = Ks.giveString("Enter the Author: ");
                     BookList = ap.PickByAuthor(Author);
                     break;
               }
               BookList.println("List of collected books:");
               BookList.save(Ks.giveString
                     ("Save (Cancel - ENTER) ? "));
            }
         }
      }
   }
}
   