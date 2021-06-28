package vue;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
/**
 * Classe qui permet de cr�er le panel qui contient tous les autres.
 *
 */
public class PanelPrincipal extends  JPanel{
	//Panel � trois r�gions : Calendrier / Selection de la date / Affichage des infos
	//On divise en trois Panels PanelAffichage, PanelCalendrier, PanelChoix
	
	
	//D�claration des trois panels principaux qui composent le panelPrincipal
	PanelAffichage panelAffichage;
	PanelChoix panelChoix;
	PanelCalendrier panelCalendrier;

	/**
	 * Constructeur qui permet d'instancier des panelPrincipal � sans param�tre
	 */
	public PanelPrincipal() {
		//D�finition du layout
		setLayout(new BorderLayout(15,15));
		
		//Instanciation des trois panels principaux
		panelAffichage = new PanelAffichage();
		panelChoix = new PanelChoix();
		panelCalendrier = new PanelCalendrier();
		
		//Param�trage de la couleur de fond du panelPrincipal
		setBackground(new Color(3,34, 76));		
		
		//Ajout des trois panneaux qui le composent � leur place respectives
		add(BorderLayout.CENTER, panelAffichage);
		add(BorderLayout.EAST, panelChoix);
		add(BorderLayout.WEST,panelCalendrier);
	}
	
	
	public PanelAffichage getPanelAffichage() {
		return panelAffichage;
	}

	public PanelChoix getPanelChoix() {
		return panelChoix;
	}
	public PanelCalendrier getPanelCalendrier() {
		return panelCalendrier;
	}
}
