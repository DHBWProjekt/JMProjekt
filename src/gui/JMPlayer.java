package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class JMPlayer {
	public static void main(String[] args) {
		// Die Daten für das Table
		String[][] data = new String[][] {
				{ "Reich und Sexy", "Die Toten Hosen", "Rock", "3:25" },
				{ "Blue", "Eifel 65", "Pop", "3:22" },
				{ "Türlich Türlich", "Das Bo", "Hip-Hop", "3:45" },
				{ "Flat Beat", "Mr. Oreo", "Dance", "3:22" },
				{ "Westerland", "Die Ärzte", "Alternativ", "3:22" } };

		// Die Column-Titles
		String[] title = new String[] { "Titel", "Interpret", "Genre", "Dauer" };

		// Das JTable initialisieren
		// JTable table = new JTable( data, title );
		JPanel btnPanel = new JPanel(new FlowLayout());
		JPanel northPanel = new JPanel(new GridLayout(2, 1));
		northPanel.add(new JTextArea());
		btnPanel.add(new JButton("Play"));
		btnPanel.add(new JButton("Pause"));
		btnPanel.add(new JButton("Stopp"));
		btnPanel.add(new JButton("Next"));
		btnPanel.add(new JButton("Back"));
		// tblPanel.add(new JScrollPane(table));

		// Das JTable initialisieren
		JTable table = new JTable(data, title);

		JFrame frame = new JFrame("JMPlayer");
		frame.setLayout(new BorderLayout());
		northPanel.add(btnPanel);
		frame.add(northPanel, BorderLayout.NORTH);
		frame.getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}
}