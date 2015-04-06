package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JMPlayer extends JFrame {
	// SelectionListener wird erstellt, um in Tabelle angeklickte Titel zu
	// erkennen
	private MusicTableSelectionListener tsl = new MusicTableSelectionListener();
	private BtnActionListener bal = new BtnActionListener();

	// Die Column-Titles, und die Dummy TableDaten werden erstellt
	private String[] title = new String[] { "Titel", "Interpret", "Genre",
			"Dauer" };
	private String[][] data = new String[][] {
			{ "Reich und Sexy", "Die Toten Hosen", "Rock", "3:25" },
			{ "Blue", "Eifel 65", "Pop", "3:22" },
			{ "Türlich Türlich", "Das Bo", "Hip-Hop", "3:45" },
			{ "Flat Beat", "Mr. Oreo", "Dance", "3:22" },
			{ "Westerland", "Die Ärzte", "Alternativ", "3:22" } };
	// Tabelle wird erstellt und Daten werden übergeben
	private MusicTable table = new MusicTable(this, data, title);

	// Alle Steuerelemente werden erstellt
	private JPanel northPanel = new JPanel(new GridLayout(2, 1));
	private JTextField anzeige = new JTextField();
	private JPanel btnPanel = new JPanel(new FlowLayout());
	private JButton play = new JButton("Play");
	private JButton pause = new JButton("Pause");
	private JButton stopp = new JButton("Stopp");
	private JButton next = new JButton("Next");
	private JButton back = new JButton("Back");

	// Konstruktor für JMPlayer,
	public JMPlayer(String name) {
		// Fenster Titel wird gesetzt
		setTitle(name);
		// Buttons bekommen den Action listener bal zugewiesen
		play.addActionListener(bal);
		pause.addActionListener(bal);
		stopp.addActionListener(bal);
		next.addActionListener(bal);
		back.addActionListener(bal);

		btnPanel.add(play);
		btnPanel.add(pause);
		btnPanel.add(stopp);
		btnPanel.add(next);
		btnPanel.add(back);
		northPanel.add(anzeige);
		northPanel.add(btnPanel);
		// table bekommt den ListSelectionlistener zugewiesen
		table.getSelectionModel().addListSelectionListener(tsl);

		setLayout(new BorderLayout());
		add(northPanel, BorderLayout.NORTH);
		getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		JMPlayer myPlayer = new JMPlayer("JMPlayer");
	}

	/*
	 * Listener der aktiviert wird, wenn eine Auswahl in der Tabelle über Maus
	 * oder Tastatur getroffen wird
	 */
	private class MusicTableSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			System.out.println(e.getSource());
			System.out.println(table.getSelectedRow());
			int selectedRow = table.getSelectedRow();
			String anzeigeText = "";
			for (int i = 0; i < table.getColumnCount(); i++) {
				anzeigeText = anzeigeText
						+ String.valueOf(table.getValueAt(selectedRow, i) + " ");
			}
			anzeige.setText(anzeigeText);
		}
	}

	private class BtnActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == play) {
				// Musik startet
				System.out.println("Musik startet");
			} else if (e.getSource() == pause) {
				// Musik pausiert
				System.out.println("Musik pausiert");
			} else if (e.getSource() == stopp) {
				// Musik stoppt
				System.out.println("Musik stoppt");
			} else if (e.getSource() == next) {
				// Nächster Song
				System.out.println("Nächster Song");
			} else if (e.getSource() == back) {
				// Vorheriger Song
				System.out.println("Vorheriger Song");
			}

		}

	}
}