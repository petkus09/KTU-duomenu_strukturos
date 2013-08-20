 
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Petkus;

import java.util.Comparator;
import studijosKTU.Ks;
import studijosKTU.ListKTUx;

/**
 *
 * @author Tautvydas
 */
public class Library {
    public ListKTUx<Book> allBooks = new ListKTUx(new Book());

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

   public ListKTUx<Book> ListOfBooks(int year1, int year2) {
      ListKTUx<Book> RangeBooks = new ListKTUx(new Book());
      for (Book a : allBooks) {
         if (a.getYear() >= year1 && a.getYear() <= year2) {
            RangeBooks.add(a);
         }
      }
      return RangeBooks;
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
   
}