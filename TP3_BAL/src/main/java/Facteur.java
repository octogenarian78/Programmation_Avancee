package main.java;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Facteur extends Thread {
    BAL bal;
    ArrayList<String> Lettres;

    public Facteur(BAL bal, ArrayList<String> Lettres) {
        this.bal = bal;
        this.Lettres = Lettres;
    }

    public void run() {
        for (int i=0; i<Lettres.size(); i++) {
            bal.deposer(Lettres.get(i));
        }
    }
}
