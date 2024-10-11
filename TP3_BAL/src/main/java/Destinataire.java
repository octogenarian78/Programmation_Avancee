package main.java;

public class Destinataire extends Thread{
    BAL bal;

    public Destinataire(BAL bal) {
        this.bal = bal;
    }

    public void run() {
        while(true){
            bal.retirer();
        }

    }
}
