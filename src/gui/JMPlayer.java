package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class JMPlayer extends JFrame {
	// SelectionListener wird erstellt, um in Tabelle angeklickte Titel zu
	// erkennen
	private MusicTableSelectionListener tsl = new MusicTableSelectionListener();
	private BtnActionListener bal = new BtnActionListener();

	// Die Column-Titles, und die Dummy TableDaten werden erstellt
	private String[] title1 = new String[] { "Titel", "Interpret", "Genre",
			"Dauer" };
	private String[][] data1 = new String[][] {
			{ "Reich und Sexy", "Die Toten Hosen", "Rock", "3:25" },
			{ "Blue", "Eifel 65", "Pop", "3:22" },
			{ "Türlich Türlich", "Das Bo", "Hip-Hop", "3:45" },
			{ "Flat Beat", "Mr. Oreo", "Dance", "3:22" },
			{ "Westerland", "Die Ärzte", "Alternativ", "3:22" } };
	private DefaultTableModel bibliothekModel = new DefaultTableModel(data1,
			title1);

	private String[] title2 = new String[] { "Pfad", "Pfad ASCII" };
	private String[][] data2;
	private DefaultTableModel pfadModel;

	// Tabelle wird erstellt und Daten werden übergeben
	private MusicTable table = new MusicTable(bibliothekModel);

	// Alle Steuerelemente werden erstellt
	private JPanel northPanel = new JPanel(new GridLayout(2, 1));
	private JTextField anzeige = new JTextField();
	private JPanel btnPanel = new JPanel(new FlowLayout());
	private JButton play = new JButton("Play");
	private JButton pause = new JButton("Pause");
	private JButton stopp = new JButton("Stopp");
	private JButton next = new JButton("Next");
	private JButton back = new JButton("Back");

	private JMenuBar myMenu = new JMenuBar();
	private JMenu myReiter1 = new JMenu("Datei");
	private JMenuItem openFile = new JMenuItem("Ordner öffnen");

	private String path;
	private File f;
	private File[] fileArray;
	private JFileChooser chooser = new JFileChooser();

	JFXPanel fxPanel = new JFXPanel();
	String pfad = "file:/Users/Guest/test/01Cantina.mp3";
	Media song = new Media(pfad);
	MediaPlayer masterPlayer = new MediaPlayer(song);

	// Konstruktor für JMPlayer,
	public JMPlayer(String name) {

		// masterPlayer.play();
		// Fenster Titel wird gesetzt
		setTitle(name);
		// Buttons bekommen den Action listener bal zugewiesen
		play.addActionListener(bal);
		pause.addActionListener(bal);
		stopp.addActionListener(bal);
		next.addActionListener(bal);
		back.addActionListener(bal);

		table.getSelectionModel().addListSelectionListener(tsl);

		btnPanel.add(play);
		btnPanel.add(pause);
		btnPanel.add(stopp);
		btnPanel.add(next);
		btnPanel.add(back);
		northPanel.add(anzeige);
		northPanel.add(btnPanel);
		// table bekommt den ListSelectionlistener zugewiesen

		openFile.addActionListener(bal);
		myReiter1.add(openFile);
		myMenu.add(myReiter1);
		setJMenuBar(myMenu);

		setLayout(new BorderLayout());
		add(northPanel, BorderLayout.NORTH);
		getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

	public Object getMyFrame() {
		return this;
	}

	public static void main(String[] args) {
		JMPlayer myPlayer = new JMPlayer("JMPlayer");
	}

	/*
	 * Listener der aktiviert wird, wenn eine Auswahl in der Tabelle über Maus
	 * oder Tastatur getroffen wird
	 */
	private class MusicTableSelectionListener implements ListSelectionListener {
		JFXPanel fxPanel = new JFXPanel();
		String bip = "file:/Users/Guest/test/01Cantina.mp3";
		Media hit = new Media(bip);
		MediaPlayer mediaPlayer = new MediaPlayer(hit);

		public void valueChanged(ListSelectionEvent e) {
			System.out.println(e.getSource());
			System.out.println(table.getSelectedRow());
			int selectedRow = table.getSelectedRow();
			String anzeigeText = "";
			// Schleifendurchlauf um alle Spalten der Tabelle auszulesen
			for (int i = 0; i < table.getColumnCount(); i++) {
				anzeigeText = anzeigeText
						+ String.valueOf(table.getValueAt(selectedRow, i) + " ");
			}
			anzeige.setText(anzeigeText);
			hit = new Media(String.valueOf(table.getValueAt(selectedRow, 1)));
			mediaPlayer.stop();
			mediaPlayer = new MediaPlayer(hit);
			masterPlayer = mediaPlayer;
			mediaPlayer.play();
			System.out.println(hit.getMetadata());
			System.out.println(hit.getDuration());
			System.out.println(mediaPlayer);
			System.out.println(mediaPlayer.getMedia().getMetadata());
			anzeige.setText(String.valueOf(mediaPlayer.getMedia().getMetadata()
					.get("title")));
		}
	}

	private class BtnActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == play) {
				// Musik startet
				masterPlayer.play();
				System.out.println("Musik startet");
			} else if (e.getSource() == pause) {
				// Musik pausiert
				masterPlayer.pause();
				System.out.println("Musik pausiert");
			} else if (e.getSource() == stopp) {
				// Musik stoppt
				masterPlayer.stop();
				System.out.println("Musik stoppt");
			} else if (e.getSource() == next) {
				// Nächster Song
				int zeilenZeiger = table.getSelectedRow();
				table.setRowSelectionInterval(zeilenZeiger + 1,
						zeilenZeiger + 1);
				System.out.println("Nächster Song");
			} else if (e.getSource() == back) {
				// Vorheriger Song
				int zeilenZeiger = table.getSelectedRow();
				table.setRowSelectionInterval(zeilenZeiger - 1,
						zeilenZeiger - 1);
				System.out.println("Vorheriger Song");
			} else if (e.getSource() == openFile) {
				// Ordner öffnen
				// Dialog zum Oeffnen von Dateien anzeigen
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int auswahl = chooser.showOpenDialog(null);
				if (auswahl == JFileChooser.APPROVE_OPTION) {
					// Ausgabe der ausgewaehlten Datei
					f = new File(chooser.getSelectedFile().getPath());
					fileArray = f.listFiles();
					String[] test = new String[] { "Pfad" };
					int musicFileCounter = 0;
					for (int i = 0; i < fileArray.length; i++) {
						if (fileArray[i].getName().endsWith(".mp3") == true
								|| fileArray[i].getName().endsWith(".m4a") == true
								|| fileArray[i].getName().endsWith(".mp3") == true
								|| fileArray[i].getName().endsWith(".wav") == true) {
							musicFileCounter++;
						}
					}
					data2 = new String[musicFileCounter][2];
					musicFileCounter = 0;
					for (int i = 0; i < fileArray.length; i++) {
						System.out.println(fileArray[i]);
						System.out.println(fileArray[i].getName().endsWith(
								".mp3"));
						if (fileArray[i].getName().endsWith(".mp3") == true
								|| fileArray[i].getName().endsWith(".m4a") == true
								|| fileArray[i].getName().endsWith(".mp3") == true
								|| fileArray[i].getName().endsWith(".wav") == true) {
							path = fileArray[i].toURI().toASCIIString();
							data2[musicFileCounter][0] = fileArray[i].getName();
							data2[musicFileCounter][1] = path;
							System.out.println(data2[musicFileCounter][0]);
							musicFileCounter++;
						}
					}
					pfadModel = new DefaultTableModel(data2, title2);
					table.getSelectionModel().removeListSelectionListener(tsl);
					table.setModel(pfadModel);
					table.getSelectionModel().addListSelectionListener(tsl);

					// System.out.println("Die zu öffnende Datei ist: "
					// + chooser.getSelectedFile().getPath());
					// bip = "file:" + chooser.getSelectedFile().getPath();
					// bip = bip.replace(" ", "%20");
					// anzeige.setText(chooser.getSelectedFile().getPath());
					// File file = new
					// File(chooser.getSelectedFile().getPath());
					// String path = file.toURI().toASCIIString();
					// System.out.println(path);
					// System.out.println(bip);
					// hit = new Media(path);
					// mediaPlayer = new MediaPlayer(hit);
				}
				// System.out.println(getMyFrame());
				System.out.println("Ordner öffnen");
			}
		}
	}
}