package vue;

import java.awt.Color;

import javax.swing.JFrame;

import controleur.controleur;
import outils.EcritureFichierSer;

public class FenetreMere extends JFrame {
	PanelPrincipal contentPane;
	public FenetreMere() {
		
		EcritureFichierSer.ecrireDemarrage();
		
		contentPane = new PanelPrincipal();
		new controleur(this);
		setContentPane(contentPane);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1300,600);setVisible(true);setLocation(50,100);
	}
	public static void main (String[] args) {
		new FenetreMere();
	}
	
	public PanelPrincipal getPanelPrincipal() {
		return contentPane;
	}
}
