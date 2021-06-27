package vue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import modele.ConstantesCalendrier;
import modele.Date;



/***
 * Panel qui permet de changer le PanelMois qu'on souhaite afficher
 *
 */
public class PanelSudCalendrier extends JPanel{


	String [] buttonNames = {"<<","<",">",">>"};
	JButton [] buttons = new JButton[4];
	JLabel labelMoisName = new JLabel();
	Date curDate = new Date();
	int n = curDate.getMois()-1;
	
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
	public PanelSudCalendrier() {
		setBackground(new Color(3,34, 76));;
		setLayout(new GridBagLayout());	
		GridBagConstraints contrainte = new GridBagConstraints();
		contrainte.insets = new Insets(10, 0, 10, 20);
		

		contrainte.gridx = 0;
		
		labelMoisName.setText(ConstantesCalendrier.MOIS[getN()]);
		labelMoisName.setForeground(new Color(255,200,120));
		add(labelMoisName,contrainte);

		contrainte.gridx += 1;
		for(int indice = 0 ; indice < buttons.length ; indice++) {
			buttons[indice] = new JButton(buttonNames[indice]);
			add(buttons[indice],contrainte);
			contrainte.gridx += 1;
			buttons[indice].setBackground(new Color(255,200,120));
		}
		buttons[1].setMnemonic(KeyEvent.VK_LEFT);
		buttons[2].setMnemonic(KeyEvent.VK_RIGHT);
		

	}
	public int getN() {
		return n;
	}
	public JButton[] getButtons() {
		return buttons;
	}
}
