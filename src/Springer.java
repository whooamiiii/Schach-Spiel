 
public class Springer extends Figuren{

	 Springer(int s, int r, int f) {
	 	 super(s, r, f);	 
	 	 Bild();
	 	 this.ID = 6;
	 }

	 public void Bild() {
	 	 if(farbe == Fenster.weiss) {
	 	 	 Bild = holBild("/Figuren/WKnight");
	 	 }else Bild = holBild("/Figuren/BKnight");
	 }
	 public boolean Bewegung(int ZielSpalte, int ZielReihe) {
	 	 //Der Springer geht zwei Felder waagrecht  (rechts oder links) dann senkrecht (oben oder unten) um ein Fekd
	 	 //d.h. hier muss gelten Math.abs(ZielSpalte - alteSpalte) * Math.abs(ZielReihe - alteReihe) == 2
	 	 //wenn sein Ausgangsfeld 2|7 ist kann er sich z.B. zu 1|5 oder 3|5 gehen
	 	 //weil (1-2)*(5-7) = -1*-2 = 2
	 	 int pruefen = Math.abs(ZielSpalte - alteSpalte) * Math.abs(ZielReihe - alteReihe);
	 	 if(bound(ZielSpalte,ZielReihe)==true) {
	 	 	 if(pruefen ==2 && freiesFeld(ZielSpalte, ZielReihe)) {
	 	 	 	 return true;
	 	 	 }
	 	 }
	 	 return false;
	 }
	 public boolean bound(int ZielSpalte,int ZielReihe) {
	 	 if(ZielSpalte<=7) {
	 	 	 return true;
	 	 }
	 	 return false;
	 }
}