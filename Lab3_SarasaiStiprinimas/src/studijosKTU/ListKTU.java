package studijosKTU;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tai pirmoji kompleksinės duomenų struktūros klasė, kuri leidžia
 * apjungti atskirus objektus į vieną visumą - sąrašą.
 * Objektai, kurie bus dedami į sąrašą, turi tenkinti interfeisą dataKTU.
 *
 * @užduotis Peržiūrėkite ir išsiaiškinkite pateiktus metodus.
 *
 * @author Eimutis Karčiauskas, KTU programinės įrangos katedra
 */

public class ListKTU<Data extends Comparable>
        implements ListADT<Data>, Iterable<Data>, Cloneable{

    private Node<Data> first;   // rodyklė į pirmą mazgą
    private Node<Data> last;    // rodyklė į paskutinį mazgą
    private Node<Data> current; // rodyklė į einamąjį mazgą, naudojama getNext
    private int size;           // sąrašo dydis, tuo pačiu elementų skaičius
    /**
     * Constructs an empty list.
     */
    public ListKTU() {
    }
    public boolean add(Data data) {      // įdeda elementą į sąrašo pabaigą
        if(data==null) return false;        // nuliniai objektai nepriimami
        if (first == null) {
            first = new Node<Data>(data, first);
            last = first;
        } else {
            Node<Data> e1 = new Node(data, null);
            last.next = e1;
            last = e1;
        }
        size++;
        return true;
    }
    public boolean add(int k, Data data){  // įterpia prieš k-ąją poziciją
        if(data==null) return false;       // nuliniai objektai nepriimami
        if (k<0||k>=size)return false;     // jei k yra blogas, grąžina null
        Node<Data> a = new Node(data, first.findNode(k));
        if (k == 0)
            first = a;
        else {
            first.findNode(k-1).next = a;
        }
        size++;
        return true;
    }
    public int size() {     // grąžinamas sąrašo dydis (elementų kiekis)
        return size;
    }
    public boolean isEmpty() {      // patikrina ar sąrašas yra tuščias
        return first == null;
    }
    public void clear() {
        size = 0;
        first = null;
        last = null;
        current = null;
    }
    public Data get(int k){           // grąžiną k-ojo elemento reikšmę
        if (k<0||k>=size)return null; // jei k yra blogas, gąžina null
        current=first.findNode(k);    // ir tuo pačiu nustato current
        return current.element;
    }
    public Data set(int k, Data data){   // suformuoja k-ojo elemento reikšmę
        if(data==null) return null;      // nuliniai objektai nepriimami
        if (k<0||k>=size)return null;
        current=first.findNode(k);
        current.element = data;
        return current.element;
    }
    public Data getNext(){       //pereina prie kitos reikšmės ir ją grąžina
        if(current==null) return null;
        current=current.next;
        if(current==null) return null;
        return current.element;
    }
    public Data remove(int k) {
        if (k<0||k>=size)return null;
       Node<Data> current = null;
       if (k == 0) {
           current = first;
           first = current.next;
           if (first == null)
               last = null;
       }
       else {
           Node<Data> ankstesnis = first.findNode(k-1);
           current = ankstesnis.next;
           ankstesnis.next = current.next;
           if (current.next == null)
               last = ankstesnis;
       }
       size--;
       return current.element;
    }
   @Override
    public Object clone(){
       ListKTU<Data> cl = null;
       try {
       cl = (ListKTU<Data>) super.clone();
       if(first==null) return cl;
       cl.first= new Node<Data>(this.first.element, null);
       Node<Data> e2= cl.first;
       for(Node<Data> e1=first.next; e1!=null; e1=e1.next){
          e2.next=new Node<Data>(e2.element, null);
          e2=e2.next;
          e2.element=e1.element;
       }
       cl.last=e2.next;
       cl.size=this.size;
       }catch (CloneNotSupportedException ex) {}
       return cl;
    }
    public Object[] toArray(){
        Object[] a = new Object[size];
        int i=0;
        for(Node e1=first; e1!=null; e1=e1.next)
            a[i++]=e1.element;
        return a;
    }
    public void sort(){
        Object[] a = this.toArray();
        Arrays.sort(a);
        int i=0;
        for(Node<Data> e1=first; e1!=null; e1=e1.next)
            e1.element=(Data)a[i++];
    }
    public void sort(Comparator c){
        Object[] a= this.toArray();
        Arrays.sort(a,c);
        int i=0;
        for(Node<Data> e1=first; e1!=null; e1=e1.next)
            e1.element=(Data)a[i++];
    }
   public void sortBurbuliuku() {
      if (first==null) return;
      boolean jauGerai = false;
      while (!jauGerai) {
         jauGerai=true;
         Node<Data> e1=first;
         Data t =null;
         for (Node<Data> e2 = first.next; e2 != null; e2 = e2.next) {
            if((e1.element).compareTo(e2.element)>0 ){
               t=e1.element; e1.element=e2.element; e2.element=t;
               jauGerai=false;
            }
            e1=e2;
         }
      }
   }
   public void sortBurbuliuku2() {
       Comparator c = new Comparator(){
           public int compare(Object o1, Object o2){
                return ((Data)o1).compareTo(o2);
           }
       };
       sortBurbuliuku(c);
   }
   public void sortBurbuliuku(Comparator c) {
      if (first==null) return;
      boolean jauGerai = false;
      while (!jauGerai) {
         jauGerai=true;
         Node<Data> e1=first;
         Data t =null;
         for (Node<Data> e2 = first.next; e2 != null; e2 = e2.next) {
            if(c.compare(e1.element, e2.element)>0 ){
               t=e1.element; e1.element=e2.element; e2.element=t;
               jauGerai=false;
            }
            e1=e2;
         }
      }
   }
    public Iterator<Data> iterator() {
        return new ListIteratorKTU();
    }
    class ListIteratorKTU implements Iterator<Data> {

        private Node<Data> iterPosition;
        ListIteratorKTU() {
            iterPosition = first;
        }
        public boolean hasNext() {
            return iterPosition!=null;
        }
        public Data next() {
            Data d= iterPosition.element;
            iterPosition = iterPosition.next;
            return d;
        }
        public void remove() {
           Node<Data> current = iterPosition;
           if (current.equals(first)){
               first = current.next;
           }
           else
           {
               last.next = current.next;
               current = null;
           }
           size--;
        }
        public Data remove(int k){
            if (k < 0 || k >= size){
            return null;
        }
        if (current == null){
            current = first;
        }
        if (size == 1){
            Data result = first.element;
            current = null;
            first = null;
            last = null;
            size = 0;
            return result;
        }
        else{
            if (k == 0){
                Data result = first.element;
                first = first.next;
                size--;
                return result;
            }
            else{
                if (k == size - 1){
                    Data result = last.element;
                    Node <Data> tmp = current.findNode(k - 1);
                    tmp.next = null;
                    size--;
                    return result;
                }
                else{
                    Data result = current.findNode(k-1).element;
                    Node <Data> tmp = current.findNode(k-1);
                    tmp.next = tmp.next.next;
                    size--;
                    return result;
                }
            }
        }
        }
    }

class Node<Data> {
         // vidinė klasė, užtikrinanti inkapsuliaciją
    public Data element;    // ji nematoma už klasės ListKTU ribų
    public Node<Data> next; // next - kaip įprasta - nuoroda į kitą mazgą

    Node(Data data, Node<Data> next) { //mazgo konstruktorius
        this.element = data;
        this.next = next;
    }
    public Node<Data> findNode(int k){ // suranda k-ąjį mazgą
        Node<Data> e=this;
        for(int i=0;i<k;i++){
           e=e.next;
        }
        return e;
    }
}
}