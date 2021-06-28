package vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import modele.ConstantesCalendrier;
import modele.Date;



/***
 * Cette classe permet d'instancier le panel qui contient les boutons de navigation dans le CardLayout du calendrier.
 * Cette classe � �t� en partie d�velopp�e par l'�quipe p�dagogique de l'IUT de V�lizy - D�partement Informatique.
 */
public class PanelSudCalendrier extends JPanel{

	//D�finition des variables n�cessaires et du tableau de boutons
	String [] buttonNames = {"<<","<",">",">>"};
	JButton [] buttons = new JButton[4];
	JLabel labelMoisName = new JLabel();
	Date curDate = new Date();
	int n = curDate.getMois()-1;

	/**
	 * Constructeur qui permer d'instancier un PanelSudCalendrier sans param�tre
	 */
	public PanelSudCalendrier() {
		
		//Param�trage du layout, de ses contraintes et de la couleur de fond
		setBackground(new Color(3,34, 76));;
		setLayout(new GridBagLayout());	
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.insets = new Insets(10, 0, 10, 20);
		

		contrainte.gridx = 0;
		
		//D�finition du texte de base pour le label indiquant le mois courant du calendrier, on en change la couleur de police et on l'ajoute au panel
		labelMoisName.setText(ConstantesCalendrier.MOIS[getN()]);
		labelMoisName.setForeground(new Color(255,200,120));
		add(labelMoisName,contrainte);

		contrainte.gridx += 1;
		
		//Pour chaque bouton
		for(int indice = 0 ; indice < buttons.length ; indice++) {
			//Instanciation du bouton
			buttons[indice] = new JButton(buttonNames[indice]);
			//Ajout du bouton
			add(buttons[indice],contrainte);
			contrainte.gridx += 1;
			//Param�trage de la couleur de fond du bouton
			buttons[indice].setBackground(new Color(255,200,120));
		}
		
		//D�finition des mn�nomiques pour les boutons de changement unique de mois
		buttons[1].setMnemonic(KeyEvent.VK_LEFT);
		buttons[2].setMnemonic(KeyEvent.VK_RIGHT);
		

	}
	public int getN() {
		return n;
	}
	public JButton[] getButtons() {
		return buttons;
	}
	
	public String[] getButtonNames() {
		return buttonNames;
	}
	public JLabel getLabelMoisName() {
		return labelMoisName;
	}
	public Date getCurDate() {
		return curDate;
	}
	public void setN(int n) {
		this.n = n;
	}
}
