package lab6_demo;
import java.io.*;
import java.util.*;
/**
 *
 * @author Eimutis Karčiauskas
 */
public class Uzduotis {

    List<Failas> visiFailai    = new LinkedList<Failas>();
    List<Automobilis> visiAuto = new LinkedList<Automobilis>();

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
    public void skaičiuotiAtskirusGamintojus() {
       // panaudojant Map struktūrą, randa skirtingus gamintojus
        Map<String, SumosGamintojo> gamintojai =
                new TreeMap<String, SumosGamintojo>();
        SumosGamintojo sum1 = null;

        for (Automobilis a : visiAuto) {
            String markė = a.getMarkė();
            if ((sum1 = gamintojai.get(markė)) == null) {
                gamintojai.put(markė, sum1 = new SumosGamintojo());
            }
            sum1.kiekis++;
            sum1.metaiSuma += a.getGamMetai();
            sum1.ridaSuma += a.getRida();
            sum1.kainaSuma += a.getKaina();
        }
        System.out.println("Visų gamintojų Map=\n" + gamintojai);
        System.out.println("\n O dabar pateikiama Map įėjimų aibė ");
        for (Map.Entry<String, SumosGamintojo> gam : gamintojai.entrySet()) {
            System.out.printf("%10s=%s", gam.getKey(), gam.getValue());
        }
    }
    public void skaitytiAutomobiliųFailą(String fName) {
      if (fName.isEmpty()) return;
      try {
         BufferedReader fReader =
                 new BufferedReader(new FileReader(new File(fName)));
         String dLine;
         while ((dLine = fReader.readLine()) != null) {
            visiAuto.add(new Automobilis(dLine));
         }
         fReader.close();
      } catch (FileNotFoundException e) {
         System.err.println("Duomenų failas " + fName + " nerastas");
      } catch (IOException e) {
         System.err.println("Failo " + fName + " skaitymo klaida");
      }
   }
   public void surinktiVisusAuto() {
      // iš failų su išplėtimu .txt formuoja visų auto sąrašą
      for (Failas f : visiFailai)
         if (f.fullName.endsWith(".automoto")) {
            f.autoSąrašoPradžia = visiAuto.size();
            skaitytiAutomobiliųFailą(f.fullName);
            f.autoKiekis = visiAuto.size() - f.autoSąrašoPradžia;
         }
   }
   public void pateiktiAutoPagalFailus(){
      // automobilių sąrašą pateikia pagal failus, panaudojant subList
      for(Failas f: visiFailai)
         if(f.autoKiekis>0){
            System.out.println("****** Viso " + f.autoKiekis+ " automobiliai" +
                    " yra faile: " + f.fullName);
            List<Automobilis> t = visiAuto.subList(f.autoSąrašoPradžia,
                                      f.autoSąrašoPradžia + f.autoKiekis);
            for(Automobilis a:t)
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

      surinktiVisusAuto();
      System.out.println("\nVisų auto skaičius = " + visiAuto.size());
      pateiktiAutoPagalFailus();
      skaičiuotiAtskirusGamintojus();
   }

class Failas {
    String fullName;
    long length;
    int level;
    int autoKiekis;
    int autoSąrašoPradžia;

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
class SumosGamintojo {
   // šios klasės objektuose kaupiamos auto gamintojo suminės charakteristikos
   public int kiekis;
   public long metaiSuma;
   public long ridaSuma;
   public double kainaSuma;

   public String toString(){
      // pateikiamas kiekis ir kitų charakteristikų vidurkiai
      return String.format("(%4d %8.1f %10.1f %10.1f )\n", kiekis,
              metaiSuma/(double)kiekis, ridaSuma/(double)kiekis,
              kainaSuma/(double)kiekis);
   }
}

    public static void main(String[] args) {
      Locale.setDefault(Locale.US); // suvienodiname skaičių formatus
      Uzduotis uzd = new Uzduotis();       // 1-a dalis
      uzd.patikrintiMetodus();
//      new RegistracijosForma();            // 2-a dalis
      ObjektuAtmintiesTyrimas tyr = new ObjektuAtmintiesTyrimas();   // 3-a dalis
//      tyr.atmintiesSunaudojimoTyrimas();
    }
}
