# Stadium-Protocol
Java: Anwendung von Threads und Sockets unter einem eigenen (vom Studium bekommen) Protokolls. 
Alle Benennungen musst unter Anweisung vom Professor so gewählt werden (->Evaluierung).
Hierbei wurde das LB04_Stadium Protocol vom Professor zur Verfügung gestellt.
Aufgabe: Ein Zutritts-/Ticketingsystem für ein Fußballstadion zu erstellen. Man bekommt eine CSV-Datei mit den gültigen Tickets.
Ticketdaten: 
•	Ticket ID
•	Sektor, in dem das Ticket gültig ist 
•	Datum ab dem das Ticket gültig ist 
•	Datum bis wann ein Ticket gültig ist

Details: 
Die Ticketdaten müssen beim Starten des Servers in die „Whitelist“ geladen werden. Wird ein Ticket verwendet, kommt es in die „Blacklist“. 
Client und Server kommunizieren über ein definiertes Protokoll, das folgende Nachrichten kennt: 
• RegisterGate: Wird ein Gate gestartet, verbindet es sich mit dem Server. Anschließend muss es ID des Sektors mitteilen, in dem es sich befindet. 
• KeepAlive: Ist ein Gate mit dem Server verbunden, schickt es „KeepAlive“ Nachrichten, um zu testen ob der Server verfügbar ist. 
Der Server muss diese Nachrichten innerhalb von fünf Sekunden wieder mit einem „KeepAlive“ bestätigen. 
Bekommt ein Gate keine oder eine zu späte Antwort, geht es in den Offline Modus. KeepAlives müssen parallel zum Ticketcheck gehandelt werden können. 
• TicketCheck: Wird ein Ticket an einem Gate gescannt (Eingabe der Ticket-ID über StdIn), schickt es dem Server die ID, um zu prüfen ob das Ticket Zutritt zum Stadium bekommt.
  • TicketCheckResponse: Nach einer TicketCheck Nachticht prüft der Server ob das Ticket gültig ist. Gültig heißt: 
  • Ticket ID ist in der Whitelist 
  • Ticket wurde noch nicht benutzt (Ticket ID ist nicht in der Blacklist) 
  • Zeitpunkt des Ticketscans ist zwischen den Gültigkeitsdaten 
  • Das Ticket ist für den Sektor, in dem es gescannt wurde, gültig Wie viele Gates im Stadium installiert werden sollen, 
    weiß der Auftraggeber nicht. Theoretisch könnten es unendlich viele sein.
