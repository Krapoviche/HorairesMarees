package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CelluleRenderer extends JLabel implements TableCellRenderer{

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		if(value.getClass() == Double.class) {
			Double texte = (Double) value;
	        setText(Double.toString(texte));
		}
		else {
			String texte = (String) value;
	        setText(texte);
		}
		if(isSelected) {
			setForeground(new Color(255,255,255));
		}
		else{
			this.setForeground(new Color(180, 100, 40));
		}
		
		if(row %2 == 0) {
			setBackground(new Color(3,34, 76));
		}
		else {
			setBackground(new Color(135, 206, 250));
		}

		return this;	
	}
	public CelluleRenderer () {
		super();
		setOpaque (true);
		setHorizontalAlignment(JLabel.CENTER);
		this.setForeground(new java.awt.Color(180, 100, 40));
		this.setFont(new Font("Arial", Font.BOLD, 14));

	}

}
