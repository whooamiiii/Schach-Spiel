
public class Konig extends Figuren{
	
	Konig(int s, int r, int f) {
		super(s, r, f);
		Bild();	
		this.ID = 1;
	}
	public void Bild() {
		if(farbe == Fenster.weiss) {
			Bild = holBild("Figuren/WKing");
		}else Bild = holBild("/Figuren/BKing");
	}
	public boolean Bewegung(int ZielSpalte, int ZielReihe) {
		//der König kann sich in jeder Richtung um ein Feld bewegen d.h. hier muss gelten: 
		//(ZielSpalte - aktSpalte) + (ZielReihe-aktReihe) == 1
		//OULR - Oben, Unten, Links, Rechts
		int OULR = Math.abs(ZielSpalte-alteSpalte) + Math.abs(ZielReihe - alteReihe);
		//für die diagonale Bewegung gilt die gleiche Regel, aber wir müssen es multiplizieren
		int Diagonal = Math.abs(ZielSpalte-alteSpalte) * Math.abs(ZielReihe-alteReihe);
		
		if(OULR == 1 && freiesFeld(ZielSpalte,ZielReihe) == true
				|| Diagonal == 1 && freiesFeld(ZielSpalte,ZielReihe) == true) {
				return true;
		}
		
		//Die Rochade ist nur möglich, wenn sowohl König als auch Turm noch nicht gezogen haben
		//und da dürfen keine Figuren ziwschen den beiden sein. die kann links oder rechts sein
		
		
		if(bewegt == false) {
			//rechte Rochade: hier muss die Spalte sich um 2 ändern und Reihe bleibt gleich
			int neueSpalte = alteSpalte+2;
			if(ZielSpalte == neueSpalte && ZielReihe == alteReihe && Rechts(ZielSpalte,ZielReihe) == false) {
				for(Figuren f : Fenster.FigurenKopieren) {
					//wenn es eine Figur gibt die sich 3 Felder nach Rechts befindet, gleiche Reihe und hat sich nicht beweg
					//dies soll der Turm sein!
					if(f.spalte == alteSpalte+3 && f.reihe == alteReihe && bewegt == false) {
						//dann wird diese Figur der Variable Rochade zugeordnet
						Fenster.Rochade = f;
						return true;
					}
				}
			}
			int neueLinkeSpalte = alteSpalte-2;
			//linke Rochade: hier muss sich die Spalte um 2 verringern, Reihe bleibt gelcih
			if(ZielSpalte ==neueLinkeSpalte && ZielReihe == alteReihe &&Links(ZielSpalte,ZielReihe) == false) {
				
				Figuren[] f= new Figuren[2];
				for(Figuren ff : Fenster.FigurenKopieren) {
					if(ff.spalte == alteSpalte-3 && ff.reihe == ZielReihe) {
						f[0] = ff;
					}
					if(ff.spalte == alteSpalte-4&& ff.reihe == ZielReihe) {
						f[1] = ff;
					}
					if(f[0] == null && f[1] != null && f[1].bewegt == false) {
						Fenster.Rochade = f[1];
						return true;
					}
				}
			}
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
}