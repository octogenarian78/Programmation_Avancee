package main.java;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		ArrayList<String> Lettres = new ArrayList<>();

		Lettres.add("b");
		Lettres.add("c");
		Lettres.add("d");
		Lettres.add("e");
		Lettres.add("f");
		Lettres.add("q");
		Lettres.add("j");
		Lettres.add("w");

		BAL bal = new BAL();
		Destinataire destinataire = new Destinataire(bal);
		Facteur facteur = new Facteur(bal,Lettres);

		facteur.start();
		destinataire.start();


	}

}
