//JMProjekt
//Dokumentation für das Projekt

Regelwerk für die Erstellung von Quellcode im Projekt:

	1. Klassen- und Methodennamen auf englisch
	2. Kommentare auf deutsch, jede Zeile Code sollte auskommentiert sein
	3. Nicht unnötig verkürzen → Lesbarkeit geht vor
		Negativ-Beispiel:  if (boolean) ist schlecht{ 
		Besser: if (boolean = true)
	4. Mehrfachverschachtelung vermeiden, auch hier geht die Lesbarkeit vor
	5. Doppelten Code vermeiden bzw. beseitigen (Flo)
	6. Funktionalität einer Klasse immer ganz unten im Textdokument programmieren
	   (z.B.: Methoden und Actionlistener)
	7. Übergabeparameter mit sprechender Namensgebung
	   z.B.: String vorname, String nachname
	8. Variablennamen immer klein beginnen und bei zusammengesetzten Wörtern 
	   Anfangsbuchstaben in der Mitte groß (z.B.: datumEinschulung) 
	9. Quellcode immer einrücken, sodass man die unterschiedlichen Blöcke erkennen 

Einstellungen zur Einhaltung des Regelwerks:

Window -> Preferences -> Java -> Editor -> Save Actions -> Format Source Code

Wenn dieses Kästchen aktiviert wird, können beim Speichern automatisch Leerzeilen gelöscht 
und Blöcke eingerückt werden