
package Lab6Petkus_OldVersion;
import java.io.*;
import java.util.LinkedList;

public class ObjektuSkaitymas {
    private ObjectInputStream read;
    
        public void SkaitymoVeiksmai(String Failas, LinkedList<Book> knygos){
            openFile(Failas);
            loadObjects(knygos);
            closeFile();
        }
	public void openFile(String Failas) {
		try {
		      read = new ObjectInputStream(
					new FileInputStream( Failas ));
		} catch (IOException e) {
		      System.err.println("Klaida paruošiant failą");
		}
	}

	public void loadObjects(LinkedList<Book> knygos) {
		Book klientai[ ];
		try {
		      klientai = (Book[ ]) read.readObject();

		      for (Book kl : klientai ) {
			System.out.println(kl);
                        knygos.add(kl);
		      }
		}
                catch (EOFException e ){
			return;
		} catch (ClassNotFoundException e) {
			System.err.println("Objektas nenuskaitytas");
		} catch (IOException e) {
			System.err.println("Klaida skaitant failą");
		}
	}

	public void closeFile()	{
		try {
			if ( read != null ) {
				read.close();
			}
		} catch ( IOException e ) {
			System.err.println( "Klaida uždarant failą." );
			System.exit( 1 );
		}
	}

}
