package gui;

import javax.swing.JTable;

public class MusicTable extends JTable {

	private JMPlayer myPlayer; // Tabelle wird mit dem Player verknüpft

	public MusicTable(JMPlayer myPlayer, String[][] data, String[] title) {
		super(data, title);
		this.myPlayer = myPlayer;
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}
};
