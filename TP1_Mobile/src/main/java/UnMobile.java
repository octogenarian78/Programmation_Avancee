

import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable
{
	int saLargeur, saHauteur, sonDebDessin;
	int sonPas = 10, sonTemps=50, sonCote=40;

	UnMobile(int telleLargeur, int telleHauteur)
	{
		super();
		saLargeur = telleLargeur;
		saHauteur = telleHauteur;
		setSize(telleLargeur, telleHauteur);
		sonTemps = (int) Math.random() * 50 + 50;
	}

	public void run()
	{
		int i = 0;
		while(i<100) {
			for (sonDebDessin = 0; sonDebDessin + sonCote< saLargeur - sonPas; sonDebDessin += sonPas) {
				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}

			for (;sonDebDessin > -sonPas; sonDebDessin -= sonPas) {
				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}
			i ++;
		}

	}


	public void paintComponent(Graphics telCG)
	{
		super.paintComponent(telCG);
		telCG.fillRect(sonDebDessin, saHauteur/2, sonCote, sonCote);
	}
}