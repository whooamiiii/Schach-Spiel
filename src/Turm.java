
public class Turm extends Figuren {

	Turm(int s, int r, int f) {
		super(s, r, f);
		Bild();
		this.ID = 3;
	}
	public void Bild() {
		if(farbe == Fenster.weiss) {
			Bild = holBild("Figuren/WRook");
		}else Bild = holBild("/Figuren/BRook");
	}
	public boolean Bewegung(int ZielSpalte, int ZielReihe) {
		//der Turm kann sich horizontal oder vertikal über eine beliebige Anzahl von Feldern bewegen
		// hier muss entweder die Spalte geleich bleiben oder die Reihe
		// weil wenn er sich horizontal bewegt, bleibt ja die Reihe gleich nur die Spalte ändert sich und umkehert
		
		if(pruefen(ZielSpalte,ZielReihe) == false && ZielSpalte == alteSpalte && freiesFeld(ZielSpalte,ZielReihe) ||
			pruefen(ZielSpalte,ZielReihe) == false &&ZielReihe == alteReihe && freiesFeld(ZielSpalte,ZielReihe)) {
			
			if(Rechts(ZielSpalte,ZielReihe) == false && Links(ZielSpalte,ZielReihe) == false
					&& Oben(ZielSpalte,ZielReihe) == false && Unten(ZielSpalte,ZielReihe) == false) {
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
}