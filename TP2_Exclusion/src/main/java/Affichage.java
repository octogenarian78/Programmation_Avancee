/**
 *
 */
import java.lang.String;
class Exclusion{}
public class Affichage extends Thread{
	String texte;
	Exclusion ex = new Exclusion();
	Cemaphore CemaphoreBinaire;


	public Affichage (String txt, Cemaphore cemaphoreBinaire){texte=txt;
		CemaphoreBinaire=cemaphoreBinaire;}

	public void run(){
		//la section critique ce code
		CemaphoreBinaire.syncWait();
		for (int i = 0; i < texte.length(); i++) {
			System.out.print(texte.charAt(i));
			try {
				sleep(100);
			} catch (InterruptedException e) {
			}

		}
		CemaphoreBinaire.syncSignal();
	}

}

