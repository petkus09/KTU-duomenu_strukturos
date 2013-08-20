package Lab6Petkus;

import lab6_demo.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class ObjektuAtmintiesTyrimas {

    private int memUsed;

    private void memDifference(String rem) {
        System.gc();
        System.gc();
        System.gc();
        long memTotal = Runtime.getRuntime().totalMemory();
        long memFree = Runtime.getRuntime().freeMemory();
        int memUsed1 = (int) (memTotal - memFree);
        System.out.println(String.format(rem + " užima atminties =\t%,6d ",
                (memUsed1 - memUsed)));
        memUsed = memUsed1;
    }

    public void atmintiesSunaudojimoTyrimas() {
        memDifference("Pradinis kodas");
        // pradžioje užkraunamos tiriamos klasės
        byte[] b = new byte[2];
        int[] k = new int[2];
        Integer[] j = new Integer[2];
        String[] s = new String[2];
        LinkedList<Integer> x = new LinkedList<Integer>();
        LinkedList<String> y = new LinkedList<String>();
        y.add(new String("abc6789"));

        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(11);
        memDifference("Klasių užkrovimas");
        memDifference("Klasių užkrovimas");
        memDifference("Klasių užkrovimas");
        // tyrimas prasideda nuo čia
        byte[] b1 = new byte[1000];                   // byte
        memDifference("byte[] b1=new byte[1000];");
        byte[] b2 = new byte[1000];
        memDifference("byte[] b2=new byte[1000];");
        byte[] b3 = new byte[1000];
        memDifference("byte[] b3=new byte[1000];");
        int[] k1 = new int[1000];
        memDifference("int[] k1=new int[1000];");   // int
        int[] k2 = new int[1000];
        memDifference("int[] k2=new int[1000];");
        int[] k3 = new int[1000];
        memDifference("int[] k3=new int[1000];");
        Integer[] j1 = new Integer[1000];                // Integer
        memDifference("Integer[] j1= new Integer[1000];");
        Integer[] j2 = new Integer[1000];
        memDifference("Integer[] j2= new Integer[1000];");
        Integer[] j3 = new Integer[1000];
        memDifference("Integer[] j3= new Integer[1000];");
        for (int i = 0; i < 1000; i++) {
            j1[i] = new Integer(i);
        }
        memDifference("for (int i=0;i<1000;) j1[i]= new Integer(i);");
        for (int i = 0; i < 1000; i++) {
            j2[i] = new Integer(i);
        }
        memDifference("for (int i=0;i<1000;) j2[i]= new Integer(i);");
        for (int i = 0; i < 1000; i++) {
            j3[i] = new Integer(i);
        }
        memDifference("for (int i=0;i<1000;) j3[i]= new Integer(i);");
        String[] s1 = new String[1000];                // String
        memDifference("String[] j1= new String[1000];");
        String[] s2 = new String[1000];
        memDifference("String[] j2= new String[1000];");
        String[] s3 = new String[1000];
        memDifference("String[] j3= new String[1000];");
        for (int i = 0; i < 1000; i++) {
            s1[i] = new String("abc");
        }
        memDifference("for (int i=0;i<1000;) j1[i]= new String(abc);");
        for (int i = 0; i < 1000; i++) {
            s2[i] = new String("abc" + i);
        }
        memDifference("for (int i=0;i<1000;) j2[i]= new String(abc+i);");
        for (int i = 0; i < 1000; i++) {
            s3[i] = new String("abc" + i + 7000);
        }
        memDifference("for (int i=0;i<1000;) j3[i]= new String(i+7000);");

        LinkedList<Integer> r = new LinkedList<Integer>();
        for (int i = 0; i < 1000; i++) {
            r.add(null);
        }
        memDifference("new LinkedList<Integer>(1000*null);");
        for (int i = 0; i < 1000; i++) {
            r.set(i, new Integer(i));
        }
        Map<String, Integer> t = new TreeMap<String, Integer>();
        for (int i = 0; i < 1000; i++) {
            t.put(String.valueOf(i), null);
        }
        memDifference("new TreeMap<String, Integer>(i, null);");
        
        memDifference("new LinkedList<Integer>(1000*Integer);");
        ArrayList<Integer> a1 = new ArrayList<Integer>(1000);
        memDifference("ArrayList<Integer> a1= new ArrayList<Integer>(1000);");
        ArrayList<String> a2 = new ArrayList<String>(1000);
        memDifference("ArrayList<String> a2= new ArrayList<String>(1000);");
    }
}
