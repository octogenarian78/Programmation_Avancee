package main.java;

public class BAL {
    String lettre = null;

    public BAL() {}

    public synchronized void deposer(String lettre) {
        System.out.println(1);
        try {
            while (this.lettre != null) {
                wait();
            }
            this.lettre = lettre;
            System.out.println("Dépot de lettre");
            notify();


        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void retirer(){
        System.out.println(2);
        try {
            while (lettre == null) {
                wait();
            }
            System.out.println("la lettre est " + lettre);
            lettre = null;
            notify();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

//    public synchronized void verifLettre() {
//        if (lettre.toUpperCase() == "Q") {
//
//        }
//    }
}
