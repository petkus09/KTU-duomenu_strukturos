/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Petkus;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import studijosKTU.DataKTU;
import studijosKTU.Ks;

/**
 *
 * @author Tautvydas
 */
public class Book implements DataKTU{
//------------------------------------------------------------Ribinės konstantos
    static private String minYear= "1900-01-01";
    static private int minDays =    7;
    static private int maxDays = 365;
//------------------------------------------------------------Duomenų kintamieji    
    private int Year;
    private String Name;
    private String Author;
    private String PickDate;
    private String User;
    private int TimePeriod;
    private boolean ReturnCondition;
    public Book (){};
    public Book(int Year, String Name, String Author, String PickDate, String User, int TimePeriod) {
        this.Year = Year;
        this.Name = Name;
        this.Author = Author;
        this.PickDate = PickDate;
        this.User = User;
        this.TimePeriod = TimePeriod;
        this.ReturnCondition = false;
        validate();
    }
    
    
     public Book (String e){
        this.fromString(e);
    }
    public DataKTU create(String dataString) {
        return new Book(dataString);
    }
    public String validate() {
        String klaidosTipas="";
        if (!PickDate.equals(DataCorrecting(PickDate)))
        {   
           klaidosTipas="Invalid Book pick date, correcting automatically. ";
        }
        if (TimePeriod > maxDays || TimePeriod < minDays)
        {
            klaidosTipas+="Invalid Time period to take the book, correcting automatically. ";
        }
        DataCorrecting(PickDate);
        return klaidosTipas;
    }
    public void fromString(String autoD){
        try {
            Scanner ed=new Scanner(autoD);
            Year   =ed.nextInt();
            Name =ed.next();
            Author =ed.next();
            PickDate   =ed.next();
            User = ed.next();
            TimePeriod = ed.nextInt();
            validate();
        } catch (InputMismatchException  e) {
            Ks.ern("Bad book data format -> "+ autoD);
        } catch (NoSuchElementException e) {
            Ks.ern("Lack of book data -> "+ autoD);
        }
    }
   @Override
    public int compareTo(Object a) { 
        int OtherTimePeriod = ((Book)a).getTimePeriod();
        String OtherPickDate = ((Book)a).getPickDate();
        if (TimePeriod > OtherTimePeriod) 
        {
            return +1;
        }
        else if (TimePeriod < OtherTimePeriod)
             {
                return -1;
             }
             else if (DateComparing(PickDate, OtherPickDate))
             {
                return +1;
             }
                  else
             {
                     return -1;
             }
    }
    public static Comparator<Book> ByAuthor =
              new Comparator<Book>() {
       public int compare(Book a1, Book a2) {
          int cmp = a1.getAuthor().compareTo(a2.getAuthor());
          if(cmp!=0) {return cmp;}
          return a1.getAuthor().compareTo(a2.getAuthor());
       }
    };
    public static Comparator byYear = new Comparator() {
       public int compare(Object o1, Object o2) {
          int k1 = ((Book) o1).getYear();
          int k2 = ((Book) o2).getYear();
          if(k1<k2) {return -1;}
          if(k1>k2) {return 1;}
          return 0;
       }
    };
    public static Comparator byTimePeriodandPickDate = new Comparator() {
       public int compare(Object o1, Object o2) {
          Book a1 = (Book) o1;
          Book a2 = (Book) o2;
          if(a1.getTimePeriod()>a2.getTimePeriod()) {return 1;}
          if(a1.getTimePeriod()<a2.getTimePeriod()) {return -1;}
          boolean t = a1.DateComparing(a1.getPickDate(), a2.getPickDate());
          if(t) {return 1;}
          if(!t && !a1.getPickDate().equals(a2.getPickDate())) {return -1;}
          return 0;
       }
    };

   @Override
   public boolean equals(Object o){
        return this.toString().equals(((Book)o).toString());
    }
   @Override
    public String toString(){
        return String.format("%6d %-25s %-20s %-12s %-20s %4d",
                             Year, Name, Author, PickDate, User, TimePeriod);
    };

    public int getYear() {
        return Year;
    }

    public String getName() {
        return Name;
    }

    public String getAuthor() {
        return Author;
    }

    public String getPickDate() {
        return PickDate;
    }

    public String getUser() {
        return User;
    }
    
    public boolean isReturnCondition() {
        return ReturnCondition;
    }

    public int getTimePeriod() {
        return TimePeriod;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public void setPickDate(String PickDate) {
        this.PickDate = PickDate;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public void setTimePeriod(int TimePeriod) {
        this.TimePeriod = TimePeriod;
    }
//------------------------------------------------------------------Methods----- 
    private boolean DateComparing(String a, String b)
    {
        boolean Result;
          Scanner sca=new Scanner(a.replace("-", " "));
          Scanner scb=new Scanner(b.replace("-", " "));
          int Sa = sca.nextInt();
          int Sb = scb.nextInt();
          if (Sa > Sb){
              Result = true;
          }
          else if(Sa < Sb){
              Result = false;
            }
          else {
                Sa = sca.nextInt();
                Sb = scb.nextInt();
                if (Sa > Sb){ 
                    Result = true;
                }
                else if(Sa < Sb){
                    Result = false;
                }
                else {
                    Sa = sca.nextInt();
                    Sb = scb.nextInt();
                    if (Sa >= Sb){ 
                        Result = true;
                    }
                    else {
                        Result = false;
                    }
                }
            }
          return Result;
    }
//------------------------------------------------------------------------------
    private String DataCorrecting(String a)
    {
        if (!DateComparing(a, minYear)){
            a = minYear;
        }
        if (TimePeriod < minDays)
        {
            TimePeriod = minDays;
        }
        if (TimePeriod > maxDays)
        {
            TimePeriod = maxDays;
        }
        else{
            Scanner sca=new Scanner(a.replace("-", " "));
            sca.nextInt();
            int Sa = sca.nextInt();
            if (Sa > 12){
              a = Replace("month", a, 12);
            }
            else if (Sa < 1){
              a = Replace("month", a, 1);  
            }
            else{
              Sa = sca.nextInt();
              if (Sa > 31){
                a = Replace("day", a, 31);
              }
              else if (Sa < 1){
                a = Replace("day", a, 1);  
              }
            }
        }
        return a;
    }
//------------------------------------------------------------------------------
    private String Replace(String Element, String a, int i)
    {
        String b = a;
        if (Element.equals("year")){
            Scanner scb = new Scanner(a.replace("-", " "));
            scb.nextInt();
            b += i + "-";
            int Sb = scb.nextInt();
            b += Sb + "-";
            Sb = scb.nextInt();
            b += Sb;
        }
        else if (Element.equals("month")){
            Scanner scb = new Scanner(a);
            int Sb = scb.nextInt();
            b += Sb + "-";
            scb.nextInt();
            b += i + "-";
            Sb = scb.nextInt();
            b += Sb;
        }
        else if (Element.equals("day")){
            Scanner scb = new Scanner(a);
            int Sb = scb.nextInt();
            b += Sb + "-";
            Sb = scb.nextInt();
            b += Sb + "-";
            scb.nextInt();
            b += i;
        }
        return b;
    }
    public boolean Return(String a)
    {
        Scanner sc = new Scanner(a.replace("-", " "));
        int Year = sc.nextInt();
        int Month = sc.nextInt();
        int Day = sc.nextInt();
        
        Scanner Picksc = new Scanner(PickDate.replace("-", " "));
        int PickYear = Picksc.nextInt();
        int PickMonth = Picksc.nextInt();
        int PickDay = Picksc.nextInt();
        
        long currentDate = Day + ((Month - 1) * 30) + ((Year - 1) * 365);
        long returnDate = PickDay + ((PickMonth - 1) * 30) + ((PickYear - 1) * 365) + TimePeriod;
        
        if (currentDate >= returnDate)
        {
            ReturnCondition = true;
            return isReturnCondition();
        }
        else 
        {
            ReturnCondition = false;
            return isReturnCondition();
        }
    }
}

