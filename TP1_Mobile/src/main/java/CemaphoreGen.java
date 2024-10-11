public final class CemaphoreGen extends Cemaphore{

    int ValeurInitiale;
    public CemaphoreGen(int valeurInitiale) {
        super((valeurInitiale != 0) ? valeurInitiale:0);
        ValeurInitiale = valeurInitiale;

    }

    public final synchronized void syncSignal(){
        super.syncSignal();
        //System.out.print(valeur);
        if (valeur>ValeurInitiale) valeur = ValeurInitiale;
    }
}
