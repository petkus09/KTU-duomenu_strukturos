package Lab6Petkus;
import lab6_demo.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author Eimutis Karčiauskas
 */
public class Uzduotis {

    List<Failas> visiFailai    = new LinkedList<Failas>();
    List<Book> visiAutoriai = new LinkedList<Book>();

    public void rastiVisusFailus(int level, File dir) {
       // rekursyviai randa visus failus, pradedant nuo nurodyto folderio dir
        File[] folderioFailai = dir.listFiles();
        for (File f : folderioFailai) {
            if (f.isFile()) {
                visiFailai.add
                        (new Failas(f.getAbsolutePath(), f.length(), level));
            } else {
                rastiVisusFailus(level + 1, f);
            }
        }
    }
    public void skaičiuotiAtskirusFailųTipus() {
       // panaudojant Map struktūrą, randa skirtingus failų tipus
        Map<String, SumosFailoTipo> failoTipai =
                new TreeMap<String, SumosFailoTipo>();
        SumosFailoTipo sum1;

        for (Failas f : visiFailai) {
            String tipas = f.getTipas();
            if ((sum1 = failoTipai.get(tipas)) == null) {
                sum1 = new SumosFailoTipo();
                failoTipai.put(tipas, sum1);
            }
            sum1.kiekis++;
            sum1.ilgiaiSuma += f.length;
        }
        System.out.println("Visų failu tipų Map=\n" + failoTipai);
        System.out.println("\n O dabar pateikiama Map įėjimų aibė ");
        for (Map.Entry<String, SumosFailoTipo> ft : failoTipai.entrySet()) {
            System.out.printf("%8s=%s\n", ft.getKey(), ft.getValue());
        }
    }
    public void SkaičiuotiAtskirusAutorius() {
       // panaudojant Map struktūrą, randa skirtingus gamintojus
        Map<String, SumosAutoriaus> autoriai =
                new TreeMap<String, SumosAutoriaus>();
        SumosAutoriaus sum1 = null;
        for (Book a : visiAutoriai) {
            String autorius = a.getAuthor();
            if ((sum1 = autoriai.get(autorius)) == null) {
                autoriai.put(autorius, sum1 = new SumosAutoriaus());
                sum1.metPradzia = 3000;
                sum1.metPabaiga = 0;
            }
            sum1.kiekis++;
            if (sum1.metPradzia > a.getYear())
                { sum1.metPradzia = a.getYear();}
            if (sum1.metPabaiga < a.getYear())
                { sum1.metPabaiga = a.getYear();}
            if (a.isReturnCondition()) 
                {sum1.skolininkuKiekis++;}
        }
        System.out.println("Visų autorių Map=\n" + autoriai);
        System.out.println("\n O dabar pateikiama Map įėjimų aibė ");
        for (Map.Entry<String, SumosAutoriaus> aut : autoriai.entrySet()) {
            System.out.printf("%10s=%s", aut.getKey(), aut.getValue());
        }
    }
    public void skaitytiKnygųFailą(String fName) {
      if (fName.isEmpty()) return;
      try {
         BufferedReader fReader =
                 new BufferedReader(new FileReader(new File(fName)));
         String dLine;
         while ((dLine = fReader.readLine()) != null) {
            visiAutoriai.add(new Book(dLine));
         }
         fReader.close();
      } catch (FileNotFoundException e) {
         System.err.println("Duomenų failas " + fName + " nerastas");
      } catch (IOException e) {
         System.err.println("Failo " + fName + " skaitymo klaida");
      }
   }
   public void surinktiVisasKnygas() {
      // iš failų su išplėtimu .txt formuoja visų auto sąrašą
      for (Failas f : visiFailai)
         if (f.fullName.endsWith(".book")) {
            f.knygųSąrašoPradžia = visiAutoriai.size();
            skaitytiKnygųFailą(f.fullName);
            f.knygųKiekis = visiAutoriai.size() - f.knygųSąrašoPradžia;
         }
   }
   public void pateiktiKnygasPagalFailus(){
      // automobilių sąrašą pateikia pagal failus, panaudojant subList
      for(Failas f: visiFailai)
         if(f.knygųKiekis>0){
            System.out.println("****** Viso " + f.knygųKiekis+ " knygų" +
                    " yra faile: " + f.fullName);
            List<Book> t = visiAutoriai.subList(f.knygųSąrašoPradžia,
                                      f.knygųSąrašoPradžia + f.knygųKiekis);
            for(Book a:t)
               System.out.println("--- " + a);
         }
   }
   public void patikrintiMetodus(){
      // pradžioje surenka visus failus, esančius nurodytame folderyje
      rastiVisusFailus(0,new File(".")); // tai projekto folderis
      // gali būti ir bet koks kitas, rekomenduojama sukurti specialų
//      rastiVisusFailus(0,new File("D:/Studentas/SpecTyrimas/"));
      for(Failas f: visiFailai) // pateikiami visi rasti failai
          System.out.println(""+f);
      skaičiuotiAtskirusFailųTipus();

      surinktiVisasKnygas();
      System.out.println("\nVisų auto skaičius = " + visiAutoriai.size());
      pateiktiKnygasPagalFailus();
      SkaičiuotiAtskirusAutorius();
   }

class Failas {
    String fullName;
    long length;
    int level;
    int knygųKiekis;
    int knygųSąrašoPradžia;

    public Failas(String fullName, long length, int level) {
        this.fullName = fullName;
        this.length = length;
        this.level = level;
    }
    public String getTipas(){
       int t = fullName.lastIndexOf('.');
       return t>=0 && t<fullName.length()-1 ? fullName.substring(t+1) : "";
    }
    public String toString(){
       return String.format("%3d %8d %s", level, length, fullName);
    }
    }
class SumosFailoTipo {
   //  šios klasės objektuose kaupiamos failo tipo suminės charakteristikos
   int kiekis;
   long ilgiaiSuma;

   public String toString(){
      return "("+kiekis+" "+ilgiaiSuma+")";
   }
}
class SumosAutoriaus {
   // šios klasės objektuose kaupiamos auto gamintojo suminės charakteristikos
   public int kiekis;
   public int metPradzia ;
   public int metPabaiga;
   public int skolininkuKiekis;

   public String toString(){
      // pateikiamas kiekis ir kitų charakteristikų vidurkiai
      return String.format("(%4d %5d %5d %5d )\n", kiekis,
              metPradzia, metPabaiga, skolininkuKiekis);
   }
}

    public static void main(String[] args) {
      Locale.setDefault(Locale.US); // suvienodiname skaičių formatus
      Uzduotis uzd = new Uzduotis();       // 1-a dalis
      uzd.patikrintiMetodus();
      new KnyguAnalizė1();
      ObjektuAtmintiesTyrimas tyr = new ObjektuAtmintiesTyrimas();   // 3-a dalis
      tyr.atmintiesSunaudojimoTyrimas();
    }
}
