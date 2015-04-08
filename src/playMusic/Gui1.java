package playMusic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Gui1 {

	public static void main(String[] args) {

		Gui1 meinegui = new Gui1();
	}

	public Gui1() {
		JFrame meinFrame = new JFrame();
		meinFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Panel1 meinPanel = new Panel1();

		/*
		 * 5 Button werden initialisiert und jeweils mit einem ActionListener
		 * versehen
		 */
		for (int i = 1; i < 6; i++) {
			JButton meinButton = new JButton("" + i);
			meinPanel.add(meinButton);

			ActionListener ad = new ActionListener() {
				public void actionPerformed(ActionEvent ae) {

					/*
					 * Der Text der auf dem Button steht wird abgelesen und in
					 * einer Variablen gespeicher. Diese wird später für die
					 * SQL-Abfrage benötigt.
					 */
					System.out.println("Die Zahl ist " + meinButton.getText());
					String text = meinButton.getText();
					Connection con = null;
					Statement stmt;
					ResultSet rs;
					try {
						/*
						 * Verbindung zu mysql Datenbank durch jdbc aufbauen
						 * Datenbank hat Namen "java_music_projekt", liegt auf
						 * Server localhost(127.0.0.1) und läuft auf Port 3306
						 * Benutzername für Anmeldung ist momentan root ohne
						 * Passwort
						 */
						con = DriverManager
								.getConnection("jdbc:mysql://localhost:3306/java_music_project?user=root&password=");
						/*
						 * Statement erstellen um mit der Datenbank zu
						 * kommunizieren
						 */

						stmt = con.createStatement();
						/*
						 * SQL-Befehl wird an die Datenbank gesendet // Methode
						 * executeQuery liefert als Ergebnis ein ResultSet,
						 * abhängig // vom SQL-Befehl
						 */

						/*
						 * Hier wird die Tabelle Lieder durchsucht, bei dem der
						 * Interpret mit der gedrückten Taste übereinstimmt
						 */
						
						rs = stmt
								.executeQuery("select * from lieder where interpret="
										+ text);
						/*
						 * Kompletter ResultSet wird durchsucht, Zeile für
						 * Zeile, bis kein Datensatz mehr gefunden wird
						 */
						while (rs.next()) {

							try {

								/*
								 * Inhalt der Spalte 7 auf die der SQL-Select
								 * zutrifft wird ausgegeben
								 */
								System.out.print(rs.getString(7) + "\n");
								/*
								 * Eine Instanz des MP3-Spielers wird erzeugt
								 * und der zuvor ermittelte Inhalt der Spalte
								 * übergeben
								 */
								mp3Spielen spieler = new mp3Spielen(
										rs.getString(7));

							} catch (Exception e) {
								/*
								 * Einfügen von einer Leerzeile, wenn Zeile
								 * komplett // ausgelesen
								 */
								System.out.println("");

							}
						}

						/*
						 * ResultSet wird geschlossen
						 */
						rs.close();
						/*
						 * Statement wird geschlossen
						 */
						stmt.close();
					} catch (SQLException e) {
						/*
						 * Fehlermeldung wird ausgegeben
						 */
						e.printStackTrace();
					}

					finally {
						/*
						 * Das schließen der Verbindung kann fehlschlagen, falls
						 * es keine // Verbindung gibt, deswegen mit
						 * try-catch-Block umschlossen
						 */
						try {
							/*
							 * Verbindung wird geschlossen // Dies soll auf
							 * jeden Fall statt finden, deswegen wird es im //
							 * finally-Block abgehandelt
							 */
							con.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}

				}

			};
			meinButton.addActionListener(ad);
		}
		meinFrame.add(meinPanel);
		meinFrame.pack();
		meinFrame.setVisible(true);
	}

}
