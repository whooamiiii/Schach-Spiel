
public class Bauer extends Figuren{
	
	Bauer(int s, int r, int f) {
		super(s, r, f);
		Bild();
		this.ID = 7;
	}
	public int farbePruefen;
	public void Bild() {
		if(farbe == Fenster.weiss) {
			Bild = holBild("/Figuren/WPawn");
		}else Bild = holBild("/Figuren/BPawn");
	}
	public boolean Bewegung(int ZielSpalte, int ZielReihe) {
		//Der Bauer kann am Anfang 2 Felder nach vorne gehen oder nur 1
		//wenn eine Figur vor ihm steht, kann er die nicht schlagen und er kann sich auch nd mehr bewegen
		//er schlägt nur diagonal
		//Die weiße Bauern bewegen sich nach oben und die schwarzen nach unten
		
		if(pruefen(ZielSpalte,ZielReihe) ==false) {
			Check();
			ziel = holZiel(ZielSpalte,ZielReihe);
			
			int EinFeld = alteReihe + farbePruefen;
			int ZweiFelder = alteReihe +farbePruefen*2;
			
			if(ZielSpalte == alteSpalte && ZielReihe == EinFeld && ziel == null) {
				return true;
			}
			if(ZielSpalte == alteSpalte && ZielReihe == ZweiFelder && ziel == null && bewegt==false) {
				return true;
			}
			//schlagen
			//hier muss die differenz von neue Spalte und alte 1 sein
			int diff = Math.abs(ZielSpalte-alteSpalte);
			if(diff ==1 && ZielReihe == alteReihe + farbePruefen && ziel !=null
					&& ziel.farbe != farbe) {
				return true;
			}
		}
		return false;
	}
	public int Check(){
		//hier wird geprüft ob es ein weißer Bauer ist oder schwarzer
		if(farbe ==Fenster.weiss) {
			farbePruefen = -1;
		}else {
			farbePruefen = 1;
		}	
		return farbePruefen;
	}
	public boolean pruefen(int ZielSpalte, int ZielReihe){
		//hier wird geprüft ob es das gleiche Feld ist
		if(ZielSpalte == alteSpalte && ZielReihe == alteReihe ) {
			return true; 
		}
		return false;
	}
}