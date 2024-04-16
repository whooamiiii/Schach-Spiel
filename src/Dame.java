
public class Dame extends Figuren {

	Dame(int s, int r, int f) {
		super(s, r, f);
		Bild();
		this.ID = 2;
	}
	public void Bild() {
		if(farbe == Fenster.weiss) {
			Bild = holBild("Figuren/WQueen");
		}else Bild = holBild("/Figuren/BQueen");
	}
	public boolean Bewegung(int ZielSpalte, int ZielReihe) {
		//Die Dame darf auf jedes freie Feld derselben Linie, Reihe oder Diagonale ziehen, ohne jedoch über andere Figuren zu springen
		//kurz gesagt, bewegt sich die Dame wie der Läufer und Turm zusammen
		// deshalb nutzen wir hier die gleiche Logik wie beim Turm und Läufer
		
		//Turm
		if(pruefen(ZielSpalte,ZielReihe) == false && ZielSpalte == alteSpalte && freiesFeld(ZielSpalte,ZielReihe) ||
				pruefen(ZielSpalte,ZielReihe) == false &&ZielReihe == alteReihe && freiesFeld(ZielSpalte,ZielReihe)) {
				
				if(Rechts(ZielSpalte,ZielReihe) == false && Links(ZielSpalte,ZielReihe) == false
						&& Oben(ZielSpalte,ZielReihe) == false && Unten(ZielSpalte,ZielReihe) == false) {
					return true;
				}
		}
		//Läufer
		int SpalteDiff = Math.abs(ZielSpalte-alteSpalte);
		int ReiheDiff = Math.abs(ZielReihe-alteReihe);
		
		if(pruefen(ZielSpalte,ZielReihe) == false && SpalteDiff == ReiheDiff && freiesFeld(ZielSpalte,ZielReihe)) {
			if(ObenDiagonal(ZielSpalte,ZielReihe) == false && UntenDiagonal(ZielSpalte,ZielReihe) == false) {
				return true;
			}
		}
		return false;
	}
	public boolean pruefen(int ZielSpalte, int ZielReihe) {
		//hier wird geprüft ob es das gleiche Feld ist
		if(ZielSpalte == alteSpalte && ZielReihe == alteReihe ) {
			return true; 
		}
		return false;
	}
	public boolean Rechts(int ZielSpalte,int ZielReihe) {
		for(int x = alteSpalte+1;x<ZielSpalte;x++) {
			for(Figuren f:Fenster.FigurenKopieren) {
				if(f.spalte == x && f.reihe == ZielReihe) {
					ziel = f;
					return true;
				}
			}
		}
		return false;
	}
	public boolean Links(int ZielSpalte,int ZielReihe) {
		for(int x = alteSpalte-1;x>ZielSpalte;x--) {
			for(Figuren f:Fenster.FigurenKopieren) {
				if(f.spalte == x && f.reihe == ZielReihe) {
					ziel = f;
					return true;
				}
			}
		}
		return false;
	}	
	public boolean Oben(int ZielSpalte,int ZielReihe) {
		for(int x = alteReihe-1;x>ZielReihe;x--) {
			for(Figuren f:Fenster.FigurenKopieren) {
				if(f.spalte == ZielSpalte && f.reihe == x) {
					ziel = f;
					return true;
				}
			}
		}
		return false;
	}
	public boolean Unten(int ZielSpalte,int ZielReihe) {
		for(int x = alteReihe+1;x<ZielReihe;x++) {
			for(Figuren f:Fenster.FigurenKopieren) {
				if(f.spalte == ZielSpalte && f.reihe == x) {
					ziel = f;
					return true;
				}
			}
		}
		return false;
	}
	public boolean ObenDiagonal(int ZielSpalte, int ZielReihe){
		if(ZielReihe <alteReihe) {
			for(int x = alteSpalte-1; x>ZielSpalte;x--) {
				for(Figuren f: Fenster.FigurenKopieren) {
					if(f.spalte == x && f.reihe == alteReihe - Math.abs(x-alteSpalte)) {
						ziel = f;
						return true;
					}
				}
			}
			for(int x = alteSpalte+1; x<ZielSpalte;x++) {
				for(Figuren f: Fenster.FigurenKopieren) {
					if(f.spalte == x && f.reihe == alteReihe - (Math.abs(x-alteSpalte))) {
						ziel = f;
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean UntenDiagonal(int ZielSpalte,int ZielReihe){
		if(ZielReihe >alteReihe) {
			for(int x = alteSpalte-1; x>ZielSpalte;x--){
				for(Figuren f: Fenster.FigurenKopieren){
					if(f.spalte == x && f.reihe == alteReihe + (Math.abs(x-alteSpalte))) {
						ziel = f;
						return true;
					}
				}
			}
			for(int x = alteSpalte+1; x<ZielSpalte;x++) {
				for(Figuren f: Fenster.FigurenKopieren) {
					if(f.spalte == x && f.reihe == alteReihe + (Math.abs(x-alteSpalte))) {
						ziel = f;
						return true;
					}
				}
			}
		}
		return false;
	}
}