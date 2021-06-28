package vue;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CelluleRenderer extends JLabel implements TableCellRenderer{

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		Double txt = (Double) value;
		
		if(column == 0) {
			super.setForeground(new java.awt.Color(180, 100, 40));
		}
		return this;	
	}
	public CelluleRenderer () {
		super();
		setOpaque (true);
		setHorizontalAlignment(JLabel.CENTER);
		this.setForeground(new java.awt.Color(180, 100, 40));

	}

}
