package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnection {

	public static void main(String[] args) {
		/*
		 * Zeit zu Beginn des Programms wird gepseichert und Variablen
		 * deklariert
		 */
		long anfangszeit = (System.currentTimeMillis());
		Connection con = null;
		Statement stmt;
		ResultSet rs;
		try {
			/*
			 * Verbindung zu mysql Datenbank durch jdbc aufbauen Datenbank hat
			 * Namen "java_music_projekt", liegt auf Server localhost(127.0.0.1)
			 * und läuft auf Port 3306 Benutzername für Anmeldung ist momentan
			 * root ohne Passwort
			 */
			con = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/java_music_project?user=root&password=");
			/*
			 * Statement erstellen um mit der Datenbank zu kommunizieren
			 */

			stmt = con.createStatement();
			/*
			 * SQL-Befehl wird an die Datenbank gesendet // Methode executeQuery
			 * liefert als Ergebnis ein ResultSet, abhängig // vom SQL-Befehl
			 */
			rs = stmt.executeQuery("select * from interpret");
			/*
			 * Kompletter ResultSet wird durchsucht, Zeile für Zeile, bis kein
			 * Datensatz mehr gefunden wird
			 */
			while (rs.next()) {

				/*
				 * Trick um auf alle Spalten zugreifen zu können, auch wenn noch
				 * nicht bekannt ist, wie viele Spalten die Tabelle hat Es wird
				 * ein try-catch-Block um die for-Schleife gesetzt
				 */
				try {
					for (int i = 1;; i++) {
						/*
						 * Auslesen von Spalte i des ResultSet // ResultSet
						 * beginnt bei Spalte 1 nicht wie bei Arrays // mit 0
						 * !!!
						 */
						System.out.print(rs.getString(i) + "\t");
					}
				} catch (Exception e) {
					/*
					 * Einfügen von einer Leerzeile, wenn Zeile komplett //
					 * ausgelesen
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
			 * Das schließen der Verbindung kann fehlschlagen, falls es keine //
			 * Verbindung gibt, deswegen mit try-catch-Block umschlossen
			 */
			try {
				/*
				 * Verbindung wird geschlossen // Dies soll auf jeden Fall statt
				 * finden, deswegen wird es im // finally-Block abgehandelt
				 */
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		/*
		 * Zeit wird ausgelesen, nach dem das Programm ausgeführt wurde
		 */
		long endzeit = (System.currentTimeMillis());

		/*
		 * Benötigte Zeit für Programmablauf wird berechnet und ausgegeben
		 */
		long gesamtzeit = endzeit - anfangszeit;
		System.out.println("Gebrauchte Zeit in ms: " + gesamtzeit);
	}
}
