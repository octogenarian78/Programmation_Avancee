

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

class UneFenetre extends JFrame
{
    ArrayList mesTaches;
    private final int LARG=400, HAUT=250;
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

        for (int i = 0; i < nbMobile; i++){

            UnMobile sonMobile = new UnMobile(LARG,HAUT/nbMobile);
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
