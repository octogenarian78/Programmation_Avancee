

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

class UneFenetre extends JFrame
{
    ArrayList mesTaches;
    private final int LARG=1200, HAUT=750;
    int NbMobile;

    public UneFenetre(int nbMobile)
    {
        // TODO
        // ajouter sonMobile a la fenetre
        // creer une thread laThread avec sonMobile
        // afficher la fenetre
        // lancer laThread
        super("Mobile");
        NbMobile=nbMobile;
        Container container=getContentPane();
        mesTaches=new ArrayList<>();
        Cemaphore cemaphore = new CemaphoreGen(100);
        for (int i = 0; i < nbMobile; i++){

            UnMobile sonMobile = new UnMobile(LARG,HAUT/nbMobile,cemaphore);
            Thread laTache = new Thread(sonMobile);
            mesTaches.add(laTache);

            container.setLayout (new GridLayout(i+1, 2));


            container.add(sonMobile);

            laTache.start();
        }












        setSize(LARG,HAUT);
        setVisible(true);


//        laTache.suspend();
//        laTache.resume();


    }
}
