package studijosKTU;

import studijosKTU.*;

/**
 *
 * @author eimutis
 */
public class Timekeeper {
    int[] tyrimoImtis;

   public Timekeeper( int[] kiekiai) {
      this.tyrimoImtis = kiekiai;
      kiekioN = tyrimoImtis.length;
      laikai = new double[kiekioN][tyrimųNmax];
      startTimeTot=System.nanoTime();
   }

    private long startTime, finishTime;
    private long startTimeTot, finishTimeTot;
    private double sumTime;
    private int tyrimoInd;
    private int kiekioInd;
    private int tyrimųN;
    private int tyrimųNmax=30;
    private int kiekioN;
    double[][] laikai;
    private String tyrimųEilutė;
    private String duomFormatas="%8.4f ";
    private String normFormatas="%8.2f ";
    private String vardoFormatas="%8s ";
    private String kiekioFormatas="%8d(%2d) ";
    private String antraštė= "  kiekis(*k)";

    public void start(){
        tyrimoInd=0;
        if(kiekioInd>=kiekioN){
            Ks.ern("Duomenų kiekis keičiamas daugiau kartų nei buvo planuota");
            System.exit(0);
        }
        tyrimųEilutė=String.format(kiekioFormatas, tyrimoImtis[kiekioInd],
                            tyrimoImtis[kiekioInd] / tyrimoImtis[0]);
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        startTime=System.nanoTime();
    }
    public void startAfterPause(){
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        startTime=System.nanoTime();
    }
    public void finish(String vardas){
        finishTime=System.nanoTime();
        double t1 = (finishTime-startTime)/1e9;
        sumTime+=t1;
        if(startTime==0){
            Ks.ern("Metodas finish panaudotas be start metodo !!!");
            System.exit(0);
        }
        if(kiekioInd==0)
            antraštė+=String.format(vardoFormatas, vardas);
        if(tyrimoInd>=tyrimųNmax){
            Ks.ern("Jau atlikta daugiau tyrimų nei numatyta  !!!");
            System.exit(0);
        }
        laikai[kiekioInd][tyrimoInd++] = t1;
        tyrimųEilutė+=String.format(duomFormatas,t1);
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        Runtime.getRuntime().gc();
        startTime=System.nanoTime();
    }

    public void seriesFinish() {
        if (kiekioInd==0)
            Ks.oun(antraštė);
        Ks.oun(tyrimųEilutė);
        kiekioInd++;
        tyrimųN=tyrimoInd;
        if (kiekioInd == (kiekioN)) {
            summary();
        }
    }
    private void summary(){
       finishTimeTot=System.nanoTime();
       double totTime=(finishTimeTot-startTimeTot)/1e9;
       Ks.ouf("       Bendras tyrimo laikas %8.3f sekundžių \n", totTime);
       Ks.ouf("    Išmatuotas tyrimo laikas %8.3f sekundžių \n", sumTime);
       Ks.ouf("    t.y. %5.1f%% sudaro pagalbiniai darbai \n",
               (totTime-sumTime)/totTime*100);
       Ks.oun("");
       Ks.oun("Normalizuota (santykinė) laikų lentelė");
       Ks.oun(antraštė);
       double d1=laikai[0][0];
       for(int i=0; i<kiekioN;i++){
          tyrimųEilutė = String.format(kiekioFormatas, tyrimoImtis[i],
                  tyrimoImtis[i]/tyrimoImtis[0]);
          for(int j=0;j<tyrimųN;j++)
             tyrimųEilutė += String.format(normFormatas,laikai[i][j]/d1);
          Ks.oun(tyrimųEilutė);
       }
    }
}
