package vue;

import java.awt.Color;
import java.io.File;

import javax.swing.JFrame;
import controleur.controleur;
import outils.EcritureFichierSer;
import outils.LectureFichierSer;

/**
 * Cette classe permet d'instancier des objets fenetreMere qui forment le conteneur principal du programme (la fen�tre). Cette classe contient �galement le main du programme.
 *
 */
public class FenetreMere extends JFrame {
	PanelPrincipal contentPane;
	
	/**
	 * Unique constructeur de la classe, permet l'instanciation d'une fen�tre en traitant les donn�es au d�marrage
	 */
	public FenetreMere() {
		//�criture des fichiers objets qui n'existent pas encore
		EcritureFichierSer.ecrireDemarrage();
		//Pareil, pour les donn�es heure par heure
		EcritureFichierSer.ecrireDemarrageHeureParHeure();
		
		
		//D�finition du conteneur principal
		contentPane = new PanelPrincipal();
		
		//D�finition du controleur, constructeur sur la FenetreMere qui l'instancie
		new controleur(this);
		setContentPane(contentPane);
		
		//Param�trage de la fen�tre
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
