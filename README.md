[<img src="https://img.shields.io/travis/playframework/play-scala-starter-example.svg"/>](https://travis-ci.org/playframework/play-scala-starter-example)

# HTWG 2048

## Web-Technologien Projekt von Max Roth und Nicolai Stephan

2048 wird auf einem Spielfeld mit 4×4 Kästchen gespielt, auf dem sich Kacheln befinden.

Am Anfang befinden sich auf dem Spielfeld zwei zufällige Kacheln, die jeweils eine 2 oder eine 4 tragen. Mit den Pfeiltasten (oben, unten, rechts, links) oder mithilfe der Buttons bewegt der Spieler die Kacheln auf dem Spielfeld, wobei sich bei jedem Zug alle Kacheln so weit wie möglich bewegen, als ob sie auf dem in die jeweilige Richtung gekippten Spielfeld rutschen würden. Stoßen dabei zwei Kacheln mit der gleichen Zahl aneinander, verschmelzen sie zu einer Kachel mit der Summe der beiden Kacheln. Zusätzlich entsteht mit jedem Zug in einem leeren Feld eine zufällige Kachel mit einer 2 oder 4.

Das Ziel des Spiels ist das Bilden einer Kachel mit der Zahl 2048. Dann hat der Spieler das Spiel gewonnen, kann allerdings auch noch weiter spielen. Das Spiel endet, wenn alle Felder mit Kacheln belegt sind und der Spieler keinen Zug mehr machen kann.

Zusätzlich gibt es eine Punktzahl, die bei Spielbeginn bei Null startet und bei jeder Kollision um den Wert der Zahl der kollidierenden Kacheln erhöht wird. Sie wird dem Spieler zusammen mit dessen Höchstpunktzahl angezeigt. Die höchste erreichbare Kachel hat den Wert 131.072, die maximale mögliche Punktzahl beträgt 3.932.100.
