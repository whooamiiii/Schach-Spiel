
public class Laufer extends Figuren{

	 Laufer(int s, int r, int f){
	 	 super(s, r, f);
	 	 Bild();
	 	 this.ID = 5;
	 }
	 public void Bild(){
	 	 if(farbe == Fenster.weiss) {
	 	 	 Bild = holBild("Figuren/WBishop");
	 	 }else Bild = holBild("/Figuren/BBishop");
	 }	 
	 public boolean Bewegung(int ZielSpalte, int ZielReihe){
	 	 //der Läufer kann sich diagonal in alle Richtungen beliebig weit auf freie Felder
	 	 // hier muss die differenz von Anfangsposition der Spalte gleich wie die Differenz von Anfangsposition der Reihe
	 	 int SpalteDiff = Math.abs(ZielSpalte-alteSpalte);
	 	 int ReiheDiff = Math.abs(ZielReihe-alteReihe);
	 	 if(bound(ZielSpalte, ZielReihe)==true) {
	 	 	 if(pruefen(ZielSpalte,ZielReihe) == false && SpalteDiff == ReiheDiff && freiesFeld(ZielSpalte,ZielReihe)) {
	 	 	 	 if(ObenLinks(ZielSpalte,ZielReihe) == false && UntenRechts(ZielSpalte,ZielReihe) == false) {
	 	 	 	 	 return true;
	 	 	 	 }
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
	 public boolean pruefen(int ZielSpalte, int ZielReihe){
	 	 //hier wird geprüft ob es das gleiche Feld ist
	 	 if(ZielSpalte == alteSpalte && ZielReihe == alteReihe ) {	 
	 	 	 return true; 
	 	 }
	 	 return false;
	 }	 
	 public boolean ObenLinks(int ZielSpalte, int ZielReihe){
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
	 public boolean UntenRechts(int ZielSpalte,int ZielReihe){
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