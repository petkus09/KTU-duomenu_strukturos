 
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab6Petkus_OldVersion;

import java.util.Comparator;
import java.util.LinkedList;

/**
 *
 * @author Tautvydas
 */
public class Library {
    public LinkedList<Book> allBooks = new LinkedList<Book>();

   public LinkedList<Book> BooksToReturn(String Date) {
      LinkedList<Book> NeedToReturn = new LinkedList<Book>();
      
      for (Book a : allBooks) {
         a.Return(Date);
         if (a.Return(Date)) {
            NeedToReturn.add(a);
         }
      }
      return NeedToReturn;
   }

   public LinkedList<Book> ListOfBooks(int year1, int year2) {
      LinkedList<Book> RangeBooks = new LinkedList<Book>();
      for (Book a : allBooks) {
         if (a.getYear() >= year1 && a.getYear() <= year2) {
            RangeBooks.add(a);
         }
      }
      return RangeBooks;
   }

   public LinkedList<Book> PickByAuthor(String Author) {
      LinkedList<Book> AuthorBooks = new LinkedList<Book>();
      for (Book a : allBooks) {
         if (a.getAuthor().equals(Author)) {
            AuthorBooks.add(a);
         }
      }
      return AuthorBooks;
   }
   
   
}