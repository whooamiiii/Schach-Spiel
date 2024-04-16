import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Figuren {
	public int reihe, alteReihe, spalte, alteSpalte, farbe, XKoordinaten, yKoordinaten;
	public BufferedImage Bild;
	public Figuren ziel;
	public boolean bewegt;
	public int ID;
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}

	Figuren(int s,int r, int f){
		this.spalte = s;
		this.reihe = r;
		this.farbe = f;
		
		alteSpalte = s;
		alteReihe = r;
		XKoordinaten = holXKoordinaten(s);
		yKoordinaten = holYKoordinaten(r);
	}
	
	public void aktualisieren() {
		XKoordinaten = holXKoordinaten(spalte);
		yKoordinaten = holYKoordinaten(reihe);
		alteSpalte = Spalte(XKoordinaten);
		alteReihe = Reihe (yKoordinaten);
		bewegt = true;
	}
	public void zurucksetzen() {
		spalte = alteSpalte;
		reihe = alteReihe;
		XKoordinaten = holXKoordinaten(spalte);
		yKoordinaten = holYKoordinaten(reihe);
		
	}
	
	public int getIndex() {
		for(int index = 0; index<Fenster.FigurenKopieren.size();index++) {
			if(Fenster.FigurenKopieren.get(index) == this) {
				return index;
			}
		}
		return 0;
	}
	public Figuren holZiel(int ZielSpalte, int ZielReihe){
		for(Figuren f: Fenster.FigurenKopieren ) {
			//prüfen welche figur die gleiche spalte und reihe hat, aber nicht unsere Figur ist
			if(f.spalte == ZielSpalte && f.reihe == ZielReihe && f != this) {
				//wenn ja,dann setz diese Figur (f) der Variabel (ziel) zu
				return f;
			}
		}
		return null;
	}
	public boolean freiesFeld(int ZielSpalte,int ZielReihe) {
		//hier prüfen wir ob das Feld wo die Figur hinwill frei ist oder besetzt mit unseren Figuren
		ziel = holZiel(ZielSpalte,ZielReihe);
		
		//prüfen ob Feld frei ist
		if(ziel == null) {
			//wenn es null ist, heisst dass das Feld frei ist
			return true;
		}else {
			//else, das Feld ist nicht frei
			//prüfen, ob es die gleiche farbe hat oder nicht
			if(ziel.farbe != this.farbe) {
				//wenn ja dann können wir hin
				return true;
			}else {
				//else nein, d.h. es ist eine von unseren Figuren
				ziel = null;
			}
		}
		return false;
	}
	public boolean Bewegung(int ZielSpalte, int ZielReihe) {
		return false;
	}	
	public BufferedImage holBild(String BildName) {
		BufferedImage Bild = null;
		
		try {
			Bild = ImageIO.read(getClass().getResourceAsStream(BildName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Bild;
	}	
	public int holXKoordinaten(int spalte) {
		spalte = spalte*100;
		return spalte;
	}
	public int holYKoordinaten(int reihe) {
		reihe = reihe*100;
		return reihe;
	}
	public int Spalte(int XKoordinaten) {
		XKoordinaten = (XKoordinaten+50)/100;
		return XKoordinaten;
	}
	public int Reihe(int YKoordinaten) {
		YKoordinaten = (YKoordinaten+50)/100;
		return YKoordinaten;
	}
}