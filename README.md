<div style="text-align: center;">

# Compte Rendu : <br> Programmation  Avancée

</div>

<p style="color: yellow">à compléter (c'est l'intro)</p>

## Semaine 1  ( 20 septembre )

Pour le premier TP, on a commencé le TP1_Mobile. Avant de commencer ce TP, nous avons analysé les différentes classe, pour cela, on a réalisé un diagramme UML :

<div style="text-align: center;">
    <img src="https://cdn.discordapp.com/attachments/1172662570369433670/1295773006039093358/image.png?ex=670fde26&is=670e8ca6&hm=fd7953d0f869157b28ae594746d04641997a76ce8f43e16ca2b8532c326bddf4" style="width: 90%;"> diagramme UML du TP1_Mobile </img>
</div>
<br>

Sur le diagramme UML ci-dessus, on peut y voir les classe **TpMobile**, **UneFenetre** et **UnMobile** qui sont les principales classes de ce TP, on y voit aussi la classe **Thread** et l'interface **Runnable**.
On voit, sur le diagramme, que la classe **TpMobile** a une méthode ***Main()*** qui va créer un objet de la classe **UneFenetre**.<br>
On remarque aussi les différentes relations de la classe **UneFenetre**, cette classe possède trois relations qui sont une relation d'héritage, la classe **UneFenetre** hérite de la classe **JFrame**, une dépendance fonctionnelle avec la classe **Thread**, car **UneFenetre** utilise **Thread**, et enfin, il y a une relation d'association entre **UneFenetre** et **UnMobile**, **UneFenetre** possède un objet de type **UnMobile** appelé *sonMobile*.<br>
Les relations de la classe **UnMobile** sont une relation d'héritage de la classe **JPanel** et une implémentation de l'interface **Runnable**.

La classe **Thread** est une classe, qui comme son nom l'indique, nous permet de créer des *threads* qui sont, en gros, le nom que l'on va donner à différents processus qui peuvent s'exécuter en même temps en fonction du nombre de cœurs du processeur.

### Le TP : 

Le TP que nous allons réaliser a pour objectif, pour ce cours, de créer un thread qui va exécuter un mobile (un carré qui fait un aller-retour dans une fenêtre).<br>

Pour commencer, dans notre classe **UneFenetre**, on va ajouter *sonMobile* à la fenêtre, pour cela rien de plus simple, on appel le constructeur de la classe **JFrame** (classe mère de **UneFenetre**) avec ```super()```, puis on créer un conteneur et enfin on créer l'attribut *sonMobile* et on l'ajoute au conteneur ce qui nous donne ce code :
 ```java
super("Mobile");
Container container=getContentPane();
sonMobile = new UnMobile(LARG,HAUT);
container.add(sonMobile);
```
Une fois qu'on a créé le mobile il ne nous reste plus qu'à l'ajouter à un thread, pour cela on va créer un thread et lui ajouter le mobile avec le code suivant : 
```java
Thread laTache = new Thread(sonMobile);
```
on va ensuite démarer la tache avec, dans notre cas, ```laTache.start()``` ce qui va avoir pour effet d'utiliser la méthode ```run()``` de l'interface **Runnable** et qui va donc, pour l'instant faire se déplacer notre mobile de droite à gauche. <br>


## Semaine  ( 27 septembre )

Comme dit la semaine dernière, le mobile ne va que de gauche à droite, alors que nous voulons qu'il fasse un allé-retour, ce problème vient du fait que notre méthode ```run()``` de la classe **UnMobile** ressemble à ça: 
```java
public void run()
    {
	for (sonDebDessin=0; sonDebDessin < saLargeur - sonPas; sonDebDessin+= sonPas)
	    {
		repaint();
		try{Thread.sleep(sonTemps);}
		catch (InterruptedException telleExcp)
		    {telleExcp.printStackTrace();}
	    }
    }
```

comme vous pouvez le voir il n'y a qu'une seul boucle qui fait avancer le mobile donc il nous faut juste ajouter une deuxième boucle qui fait aller notre mobile dans l'autre sens, la voici : 
```java
for (;sonDebDessin > -sonPas; sonDebDessin -= sonPas) {
				repaint();
				try {
					Thread.sleep(sonTemps);
				} catch (InterruptedException telleExcp) {
					telleExcp.printStackTrace();
				}
			}
```

Une fois cela fait, on a essayé de mettre un bouton pour pouvoir stopper et relancer le mobile.
Pour faire ça, on va créer un bouton au quel on va ajouter une fonction qui va modifier la valeur d'un entier ce qui va nous permettre de savoir l'état du mobile
```java
JButton sonBouton = new JButton("start/stop");

        int run = 0;

        sonBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(run == 0){
                    laTache.suspend();
                    int run = 1;
                }
                else{
                    laTache.resume();
                    int run = 0;
                }
            }
        });


        container.add(sonBouton);
        container.add(sonMobile);
```
pour stopper le mobile, on utilise la méthode ```suspend()``` de la classe **Thread** et pour le relancer on va utiliser la méthode ```resume()```.<br>
Quand on exécute le programme et que l'on appuie sur le bouton on obtient une erreur (trop longue pour être mise) qui nous dit que la méthode ```suspend()``` n'est plus supportée, ce qui peut être dû au fait que cette méthode et trop ancienne.<br>

## Semaine 3 ( 4 octobre )
Cette semaine on va faire le TP2_Exclision, on va aussi introduire le concepte de sémaphore.<br>

Ce TP compte quatre classes : **Main** la classe main, **Affichage** une qui hérite de **Thread**, **Cemaphore** une classe abstraite et **CemaphoreBinaire** qui hérite de **Cemaphore**.<br>
Dans ce TP la classe **Main** va créer plusieurs objets de la classe **Affichage**, objets qui vont afficher des suites d'une même lettre, puis va les démarrer, notre but est de faire en sorte que chaque suite de lettres s'affiche l'une après l'autre (ne pas avoir "ABABA" mais plutôt "AAABB"). Le problème étants que les threads s'exécutent en même temps, les exécutions vont s'emmêler, pour régler ce problème, on va définir une ressource critique, qui est une portion du code où seulement un seul thread pourra y accéder à la fois.<br>
Si on analyse le code de la méthode ```run()``` de la classe **Affichage**
```java
public void run(){

	for (int i=0; i<texte.length(); i++){
		System.out.print(texte.charAt(i));
		try {sleep(100);} catch(InterruptedException e){};
	}

}
```
On remarque que la ressource critique se trouve être la boucle ```for```, une astuce pour régler notre problème serai d'utiliser la méthode ```synchronized``` qui fait en sorte que seulement un thread puisse accéder à une zone définie, dans notre cas on peut soit l'utiliser autour de la boucle ```for``` soit directement sur la méthode
```java
public synchronized void run() //utilisation sur la méthode
```
```java
public void run(){
	synchronized(this){ //utilisation sur la boucle for
		for (int i=0; i<texte.length(); i++){
			System.out.print(texte.charAt(i));
			try {sleep(100);} catch(InterruptedException e){};
		}
	}
}
```
<br>

L'autre option qui s'offre à nous est d'utiliser un sémaphore, qui est une sorte de vigile qui va donner l'accès d'une ressource critique à un nombre de threads défini.<br>
 Si on veut utiliser un sémaphore on va pouvoir utiliser les classes **Cemaphore** et **CemaphoreBinaire**, pour ça on va modifier la classe **Affichage** en lui ajoutant un attribut de type **Cemaphore** ainsi que sont constructeur, on va ensuite, dans la méthode ```run()```, utiliser avant la boucle la méthode ```syncWait()```, qui va, dés qu'un thread entre dans la ressource critique, dire aux autres threads d'attendre, puis on va utiliser la méthode ```syncSignal()``` à la fin de la boucle pour pouvoir dire au sémaphore de permettre l'accés e la ressource critique à un autre thread.

 ## Semaine 4 ( 11 octobre )

L'objectif de cette semaine est de mettre en place une ressource critique sur le TP1_Mobile, en gros, on va dire que sur le deuxième tiers de la fenêtre seul un nombre limité de mobile peuvent circuler en même temps.<br>
Avant toute chose, on va devoir diviser notre fenêtre en trois zones, pour cela, on va découper les deux boucles qui permettent à nos mobiles de faire leurs aller-retour en trois boucles chacune ce qui va nous donner ce code 
```java
public void run()
	{

	while(true) {
		for (sonDebDessin = 0; sonDebDessin + sonCote< saLargeur/3 - sonPas; sonDebDessin += sonPas) {
			repaint();
			try {
				Thread.sleep(sonTemps);
				}catch (InterruptedException telleExcp) {
				telleExcp.printStackTrace();
			}
		}
		for (; sonDebDessin + sonCote< 2*saLargeur/3 - sonPas; sonDebDessin += sonPas) {
			repaint();
			try {
				Thread.sleep(sonTemps);
			} catch (InterruptedException telleExcp) {
				telleExcp.printStackTrace();
			}
		}

		for (; sonDebDessin + sonCote< saLargeur - sonPas; sonDebDessin += sonPas) {
			repaint();
			try {
				Thread.sleep(sonTemps);
			} catch (InterruptedException telleExcp) {
				telleExcp.printStackTrace();
			}

		}
```
(le code ci-dessus représente uniquement l'aller)<br>

Maintenant, que ce découpage est fait, on définir en tant que section critique la seconde boucle de l'aller et aussi du retour.<br>
Si on veut pouvoir limiter le nombre de mobiles passant par cette zone, on pourra utiliser la classe **Cemaphore** du TP2_Exclusion ainsi qu'une classe **CemaphoreGen** qui hérite de **Cemaphore**, mais qui différemment de **CemaphoreBinaire** peut laisser passer plusieurs threads en même temps. Il nous suffira juste comme sur la TP2 d'ajouter dans notre classe **Mobile** un attribut de type **Cemaphore** et de le faire pour le constructeur (je ne l'ai pas dit la semaine dernière, mais tous les mobiles doivent avoir le même sémaphore), puis on utilise les méthode ```syncWait()``` (avant la section critique) et ```syncSignal()``` après les sections critique, et avec ça, on peut réguler le nombre de mobiles qui passent dans le second tiers de l'écran.
