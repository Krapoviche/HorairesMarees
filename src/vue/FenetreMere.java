package vue;

import java.awt.Color;
import java.io.File;

import javax.swing.JFrame;
import controleur.controleur;
import outils.EcritureFichierSer;
import outils.LectureFichierSer;

/**
 * Cette classe permet d'instancier des objets fenetreMere qui forment le conteneur principal du programme (la fenêtre). Cette classe contient également le main du programme.
 *
 */
public class FenetreMere extends JFrame {
	PanelPrincipal contentPane;
	
	/**
	 * Unique constructeur de la classe, permet l'instanciation d'une fenêtre en traitant les données au démarrage
	 */
	public FenetreMere() {
		//Écriture des fichiers objets qui n'existent pas encore
		EcritureFichierSer.ecrireDemarrage();
		//Pareil, pour les données heure par heure
		EcritureFichierSer.ecrireDemarrageHeureParHeure();
		
		
		//Définition du conteneur principal
		contentPane = new PanelPrincipal();
		
		//Définition du controleur, constructeur sur la FenetreMere qui l'instancie
		new controleur(this);
		setContentPane(contentPane);
		
		//Paramétrage de la fenêtre
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1300,600);setVisible(true);setLocation(50,100);
	}
	
	/**
	 * Lancement du programme, instancie simplement une FenetreMere
	 * @param args
	 */
	public static void main (String[] args) {
		new FenetreMere();
	}
	
	public PanelPrincipal getPanelPrincipal() {
		return contentPane;
	}
}
