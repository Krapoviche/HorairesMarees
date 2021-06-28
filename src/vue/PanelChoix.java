package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controleur.controleur;

/**
 * Cette classe permet d'instancier le panel se situant sur la droit du logiciel, ce dernier permet de selectionner le port qu'on souhaite �tudier.
 *
 */
public class PanelChoix extends JPanel{
	
	//D�finition du tableau de boutonsPort
	BoutonPort[] boutonsPort;
	
	//Instanciation du panel qui les contiendra
	JPanel pan =new JPanel();
	
	/**
	 * Constructeur qui permet d'instancier le panelChoix sans param�tres
	 */
	public PanelChoix() {
		
		//Instantiation des contraintes
		GridBagConstraints contraintesPan =new GridBagConstraints();
		
		//Param�trage du layout pour le panel qui contiendra les boutons
		pan.setLayout(new GridBagLayout());
		
		//On d�finit le dossier qu'on va lire pour extraire les noms des ports
		File serFolder = new File("data_ports/datas_ser/");
		File[] serFiles = serFolder.listFiles();
		
		//Param�trage du panel
		pan.setPreferredSize(new Dimension(serFiles.length*10,20));
		pan.setBackground(new Color(3,34, 76));
		contraintesPan.fill = GridBagConstraints.BOTH;		

		//D�finition du layout pour le panel principal
		setLayout(new BorderLayout());
		
		
		//Pour chaque port lu on cr�� un bouton
		boutonsPort = new BoutonPort[serFiles.length];
		for(int i = 0 ; i < serFiles.length ; i++) {
			boutonsPort[i] = new BoutonPort(serFiles[i].getName().split(".ser")[0]);
			contraintesPan.gridy+=1;
			pan.add(boutonsPort [i],contraintesPan);
		}
		this.add(pan);
	}
	
	public BoutonPort[] getBoutonsPort() {
		return boutonsPort;
	}
	
	/**
	 * M�thode qui permet d'�couter les actions et de les envoyer au controleur
	 * @param c Le controleur
	 */
	public void recordListener(controleur c) {
		for(int i = 0 ; i < boutonsPort.length ; i++){
			boutonsPort[i].addActionListener(c);
		}
	}
}
