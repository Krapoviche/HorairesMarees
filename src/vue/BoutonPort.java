package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

/**
 * Cette classe permet d'instancier des boutons qui sont liés à un nom de port
 *
 */
public class BoutonPort extends JButton{
	private String nomPort;
	
	/**
	 * Constructeur qui permet d'instancier des objets BoutonPort
	 * @param np Le nom du port lu
	 */
	public BoutonPort(String np) {
		super(np);
		this.nomPort = np;
		this.setSize(new Dimension(200,50));
		this.setPreferredSize(new Dimension(200,50));
		this.setBackground(new Color(255,200,120));
	}
	
	
	public String getPort() {
		return nomPort;
	}
}
