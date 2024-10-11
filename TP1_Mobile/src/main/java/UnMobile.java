

import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable
{
	int saLargeur, saHauteur, sonDebDessin;
	int sonPas = 10, sonCote=40;
	int sonTemps;
	Cemaphore cemaphore;

	UnMobile(int telleLargeur, int telleHauteur, Cemaphore semaphore)
	{
		super();
		saLargeur = telleLargeur;
		saHauteur = telleHauteur;
		setSize(telleLargeur, telleHauteur);
		//sonTemps = (int)(Math.random() * 350);
		sonTemps = 500;
		cemaphore = semaphore;
	}

	public void run()
	{

		while(true) {
			for (sonDebDessin = 0; sonDebDessin + sonCote< saLargeur/3 - sonPas; sonDebDessin += sonPas) {
				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}
			cemaphore.syncWait();
			for (; sonDebDessin + sonCote< 2*saLargeur/3 - sonPas; sonDebDessin += sonPas) {
				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}
			cemaphore.syncSignal();

			for (; sonDebDessin + sonCote< saLargeur - sonPas; sonDebDessin += sonPas) {
				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}

			}

			for (;sonDebDessin > 2*saLargeur/3; sonDebDessin -= sonPas) {
				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}
			cemaphore.syncWait();
			for (;sonDebDessin > saLargeur/3; sonDebDessin -= sonPas) {
				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}
			cemaphore.syncSignal();

			for (;sonDebDessin > -sonPas; sonDebDessin -= sonPas) {
				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}

		}

	}


	public void paintComponent(Graphics telCG)
	{
		super.paintComponent(telCG);
		telCG.fillRect(sonDebDessin, saHauteur/2, sonCote, sonCote);
	}
}
