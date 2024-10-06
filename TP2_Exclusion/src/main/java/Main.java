import java.io.*;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;
import java.lang.String;

public class Main {

	public static void main(String[] args) {

		Cemaphore cemaphoreBinaire = new CemaphoreBinaire(1);
		// TODO Auto-generated method stub
		Affichage TA = new Affichage("AAAAA",cemaphoreBinaire);
		Affichage TB = new Affichage("BBBBBBBBBBBB",cemaphoreBinaire);
		Affichage TC = new Affichage("CCCCCCCCCCC",cemaphoreBinaire);
		Affichage TD = new Affichage("DDDD",cemaphoreBinaire);

		TB.start();

		TA.start();

		TC.start();

		TD.start();
	}

}
