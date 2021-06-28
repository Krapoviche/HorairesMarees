package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Cette classe permet d'instancier des renderer param�tr�s pour le tableau heure par heure de l'interface.
 *
 */
public class CelluleRenderer extends JLabel implements TableCellRenderer{

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		//Si l'objet en param�tre est un Double (cas o� les donn�es existent)
		if(value.getClass() == Double.class) {
			Double texte = (Double) value;
	        setText(Double.toString(texte));
		}
		//sinon
		else {
			String texte = (String) value;
	        setText(texte);
		}
		
		//On change la couleur du texte quand la ligne sur laquelle il est est selectionn�e
		if(isSelected) {
			setForeground(new Color(255,255,255));
		}
		
		//Sinon on lui met la couleur normale
		else{
			this.setForeground(new Color(180, 100, 40));
		}
		//On colore diff�remment une ligne sur deux pour la lisibilit� du tableau
		if(row %2 == 0) {
			setBackground(new Color(3,34, 76));
		}
		else {
			setBackground(new Color(135, 206, 250));
		}

		return this;	
	}
	/**
	 * 
	 * Constructeur qui va d�finit l'alignement dans la cellule, la couleur et la police du texte
	 */
	public CelluleRenderer () {
		super();
		setOpaque (true);
		setHorizontalAlignment(JLabel.CENTER);
		this.setForeground(new java.awt.Color(180, 100, 40));
		this.setFont(new Font("Arial", Font.BOLD, 14));

	}

}
