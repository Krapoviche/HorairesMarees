package vue;

import javax.swing.*;

import controleur.controleur;

//import Controleur.Controleur;

import java.awt.*;

/**
 * Classe qui instancie un PanelCalendrier qui contient un PanelCentreCalendrier et un PanelSudCalendrier.
 * Cette classe � �t� en partie d�velopp�e par l'�quipe p�dagogique de l'IUT de V�lizy - D�partement Informatique.
 */

public class PanelCalendrier extends JPanel{
	
	//Instantiation des quelques labels informatifs et de leur conteneur
	JPanel panelNord = new JPanel();
	JLabel labelInfosNord1 = new JLabel("Les infos mar�es concernent la p�riode du 01/06/2021 au 30/09/2021.");
	JLabel labelInfosNord2 = new JLabel("Les hauteurs de mer son compil�es pour les p�riodes du 01/01/2020 au 27/06/2021.");
	JLabel labelInfosNord3 = new JLabel("Seuls certains des ports ont des hauteurs de mer heures par heures.");
	
	//D�claration des deux Panels principaux du PanelCalendrier
	PanelCentreCalendrier panelCentre;
	PanelSudCalendrier panelSud;
	
	/**
	 * Contructeur qui permet d'instancier des objets PanelCalendrier, ne n�cessite pas de param�tres
	 */
	public PanelCalendrier() {
		
		//D�finition du layout
		setLayout(new BorderLayout(0,0));
		
		//Instanciation des panneaux principaux du calendrier, et ajout de ces derniers
		panelCentre = new PanelCentreCalendrier();
		panelSud = new PanelSudCalendrier();
		add(panelCentre, BorderLayout.CENTER);
		add(panelSud,BorderLayout.SOUTH);
		
		//D�finition de la couleurs du texte des messages informatifs
		labelInfosNord1.setForeground(new Color(255,200,120));
		labelInfosNord2.setForeground(new Color(255,200,120));
		labelInfosNord3.setForeground(new Color(255,200,120));
		
		//Param�trage et ajout du panelNord (conteneur des messages informatifs)
		panelNord.setBackground(new Color(3,34, 76));
		panelNord.setLayout(new BorderLayout());
		panelNord.add(labelInfosNord1,BorderLayout.NORTH);
		panelNord.add(labelInfosNord2,BorderLayout.CENTER);
		panelNord.add(labelInfosNord3,BorderLayout.SOUTH);
		add(panelNord,BorderLayout.NORTH);
	}
	
	
	public PanelSudCalendrier getPanelSud() {
		return panelSud;
	}
	public PanelCentreCalendrier getPanelCentre() {
		return panelCentre;
	}
	
	/**
	 * M�thode qui permet d'�couter les actions et de les envoyer au controleur
	 * @param parControleur Le controleur
	 */
	public void recordListener(controleur parControleur) {
		for(PanelMois panelMois : panelCentre.getPanelsMois()) {
			for(BoutonDate bouton : panelMois.boutonsDate)
				bouton.addActionListener(parControleur);
		}
		for(JButton bouton : panelSud.getButtons()) {
			bouton.addActionListener(parControleur);
		}
	}
}