package Laboras2demo;
import studijosKTU.*;

public class AutoApskaita {

   public ListKTUx<Automobilis> visiAuto = new ListKTUx(new Automobilis());

   public ListKTUx<Automobilis> atrinktiNaujusAuto(int nRiba) {
      ListKTUx<Automobilis> naujiAuto = new ListKTUx(new Automobilis());
      // blogai (neefektyviai) organizuotos peržiūros pavyzdys
      for (int i = 0; i < visiAuto.size(); i++) {
         Automobilis a= visiAuto.get(i);
         if (a.getGamMetai() >= nRiba) {
            naujiAuto.add(a);
         }
      }
      return naujiAuto;
   }

   public ListKTUx<Automobilis> atrinktiPagalKainą(int riba1, int riba2) {
      ListKTUx<Automobilis> vidutiniaiAuto = new ListKTUx(new Automobilis());
      // normaliai organizuotos peržiūros pavyzdys
      for (Automobilis a = visiAuto.get(0); a != null; a = visiAuto.getNext()) {
         if (a.getKaina() >= riba1 && a.getKaina() <= riba2) {
            vidutiniaiAuto.add(a);
         }
      }
      return vidutiniaiAuto;
   }

   public ListKTUx<Automobilis> maksimaliosKainosAuto() {
      ListKTUx<Automobilis> brangiausiAuto = new ListKTUx(new Automobilis());
      // formuojamas sąrašas su maksimalia reikšme
      double maxKaina = 0;
      for (Automobilis a = visiAuto.get(0); a != null; a = visiAuto.getNext()){
         double kaina=a.getKaina();
         if (kaina >= maxKaina) {
            if (kaina > maxKaina) {
               brangiausiAuto.clear();
               maxKaina = kaina;
            }
            brangiausiAuto.add(a);
         }
      }
      return brangiausiAuto;
   }

   public ListKTUx<Automobilis> atrinktiMarkęModelį(String modelioKodas) {
      ListKTUx<Automobilis> firminiaiAuto = new ListKTUx(new Automobilis());
      for (Automobilis a = visiAuto.get(0); a != null; a = visiAuto.getNext()){
         String pilnasModelis=a.getMarkė()+" "+a.getModelis();
         if (pilnasModelis.startsWith(modelioKodas)) {
            firminiaiAuto.add(a);
         }
      }
      return firminiaiAuto;
   }
}
