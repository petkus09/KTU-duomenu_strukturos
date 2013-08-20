
package Lab6Petkus_OldVersion;
import java.io.*;
import java.util.LinkedList;

public class ObjektuRasymas {
    private ObjectOutputStream out;

        public void IrasymoVeiksmai(String Failas, LinkedList<Book> knygos, String Zinute){
            openFile(Failas);
            outObjects(knygos, Zinute);
            closeFile();
        }
	public void openFile(String Failas) {
		try {
		       out = new ObjectOutputStream(
				    new FileOutputStream( Failas ));
		} catch (IOException e) {
		      System.err.println("Klaida paruošiant failą");
		}
	}
	public void outObjects(LinkedList<Book> knygos, String Zinute) {
                System.out.println("---------------------------------- /n");
                System.out.println(Zinute + "/n");
		try {
		      for (Book kl : knygos ) {
			System.out.print(kl.toString());
		      }
		      out.writeObject(knygos);
		}
                catch (NotSerializableException e) {
			System.err.println("Neįdiegtas Serializable interfeisas");
		} catch (IOException e) {
			System.err.println("Kita serializacijos klaida");
		}
                System.out.println("---------------------------------- /n");
	}

	public void closeFile()	{
		try {
		      if (out != null ) {
			 out.close();
		      }
		} catch ( IOException e ) {
			System.err.println( "Klaida uždarant failą." );
			System.exit( 1 );
		}
	}
}
