import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Fenster extends JPanel implements MouseListener, MouseMotionListener, Runnable{

	private static final long serialVersionUID = 1L;
	public static ArrayList<Figuren> alleFiguren = new ArrayList<>();
	public static ArrayList<Figuren> FigurenKopieren = new ArrayList<>();
	public boolean klicken;
	public int XKor,YKor;	
	public Figuren figur;
	public boolean freiesFeld;
	public boolean bewegen;
	public static int weiss = 1;
	public static int schwarz =0;
	public static Figuren Rochade;
	public Figuren Schach;
	public int jetzt = weiss;
	Fenster(){
		this.setPreferredSize(new Dimension(800,800));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		test();
//		BilderEinfuegen();
		AlleFigurenKopieren(alleFiguren,FigurenKopieren);
	}
	public void test(){
		alleFiguren.add(new Bauer(0,6,weiss));
		alleFiguren.add(new Bauer(1,6,weiss));
		alleFiguren.add(new Bauer(2,6,weiss));
		alleFiguren.add(new Bauer(3,6,weiss));
		alleFiguren.add(new Bauer(4,6,weiss));
		alleFiguren.add(new Bauer(5,6,weiss));
		alleFiguren.add(new Bauer(6,6,weiss));
		alleFiguren.add(new Bauer(7,6,weiss));
		alleFiguren.add(new Bauer(0,1,schwarz));
		alleFiguren.add(new Bauer(1,1,schwarz));
		alleFiguren.add(new Bauer(2,1,schwarz));
		alleFiguren.add(new Bauer(3,1,schwarz));
		alleFiguren.add(new Bauer(4,1,schwarz));
		alleFiguren.add(new Bauer(5,1,schwarz));
		alleFiguren.add(new Bauer(6,1,schwarz));
		alleFiguren.add(new Bauer(7,1,schwarz));
		
		alleFiguren.add(new Springer(1,7,weiss));
		alleFiguren.add(new Springer(6,7,weiss));
		alleFiguren.add(new Springer(1,0,schwarz));
		alleFiguren.add(new Springer(6,0,schwarz));
		alleFiguren.add(new Turm(0,7,weiss));
		alleFiguren.add(new Turm(7,7,weiss));
		alleFiguren.add(new Turm(0,0,schwarz));
		alleFiguren.add(new Turm(7,0,schwarz));

		
		alleFiguren.add(new Konig(4,0,schwarz));
		alleFiguren.add(new Konig(4,7,weiss));
	}
	public void BilderEinfuegen(){
		alleFiguren.add(new Bauer(0,6,weiss));
		alleFiguren.add(new Bauer(1,6,weiss));
		alleFiguren.add(new Bauer(2,6,weiss));
		alleFiguren.add(new Bauer(3,6,weiss));
		alleFiguren.add(new Bauer(4,6,weiss));
		alleFiguren.add(new Bauer(5,6,weiss));
		alleFiguren.add(new Bauer(6,6,weiss));
		alleFiguren.add(new Bauer(7,6,weiss));
		alleFiguren.add(new Bauer(0,1,schwarz));
		alleFiguren.add(new Bauer(1,1,schwarz));
		alleFiguren.add(new Bauer(2,1,schwarz));
		alleFiguren.add(new Bauer(3,1,schwarz));
		alleFiguren.add(new Bauer(4,1,schwarz));
		alleFiguren.add(new Bauer(5,1,schwarz));
		alleFiguren.add(new Bauer(6,1,schwarz));
		alleFiguren.add(new Bauer(7,1,schwarz));
		
		alleFiguren.add(new Springer(1,7,weiss));
		alleFiguren.add(new Springer(6,7,weiss));
		alleFiguren.add(new Springer(1,0,schwarz));
		alleFiguren.add(new Springer(6,0,schwarz));
		
		alleFiguren.add(new Turm(0,7,weiss));
		alleFiguren.add(new Turm(7,7,weiss));
		alleFiguren.add(new Turm(0,0,schwarz));
		alleFiguren.add(new Turm(7,0,schwarz));
		
		alleFiguren.add(new Laufer(2,7,weiss));
		alleFiguren.add(new Laufer(5,7,weiss));
		alleFiguren.add(new Laufer(2,0,schwarz));
		alleFiguren.add(new Laufer(5,0,schwarz));
		
		alleFiguren.add(new Dame(3,0,schwarz));
		alleFiguren.add(new Dame(3,7,weiss));
		
		alleFiguren.add(new Konig(4,0,schwarz));
		alleFiguren.add(new Konig(4,7,weiss));

	}
	
	public void Spiel(){
		if(klicken == true){
			if (figur == null){
				for(Figuren f: FigurenKopieren) {
					if(f.farbe == jetzt &&f.spalte == XKor/100&& f.reihe == YKor/100) {
						figur = f;
					}
				}	
			}else {
				bewegen = false;
				freiesFeld = false;
				AlleFigurenKopieren(alleFiguren,FigurenKopieren);
				
				if(Rochade !=null) {
					Rochade.spalte = Rochade.alteSpalte;
					Rochade.XKoordinaten = Rochade.holXKoordinaten(Rochade.spalte);
					Rochade = null;
				}
				//Bild von mitte ziehen
				figur.XKoordinaten = XKor-50;
				figur.yKoordinaten = YKor-50;
				// Figuren in felder setzen
				figur.spalte = figur.Spalte(figur.XKoordinaten);
				figur.reihe = figur.Reihe(figur.yKoordinaten);
				
				if(figur.Bewegung(figur.spalte, figur.reihe)==true) {	
					bewegen = true;
					if(figur.ziel != null) {
						FigurenKopieren.remove(figur.ziel.getIndex());
					}
					Rochade();
					if(KonigBewegung(figur) == false && Schach2() == false) {
						freiesFeld = true;
					}
				}
			}
		}	
		if(klicken == false) {
			if(figur != null) {
				if(freiesFeld == true) {
					AlleFigurenKopieren(FigurenKopieren,alleFiguren);
					figur.aktualisieren();
					if(Rochade !=null) {
						Rochade.aktualisieren();
					}
					if(Schach()) {}
					Runde();		
				}else {
					AlleFigurenKopieren(alleFiguren,FigurenKopieren);
					figur.zurucksetzen();
					figur = null;
				}
			}
		}
	}
	public boolean Schach() {
		Figuren konig = holKonig(true);
		
		if(figur.Bewegung(konig.spalte, konig.reihe)){
			Schach = figur;
			return true;
		}else {
			Schach = null;
		}
		return false;
	}
	public Figuren holKonig(boolean etwas) {
		//diese boolean etwas ist um zu prüfen ob es der gegnerische König ist oder unser
		Figuren konig = null;
		
		for(Figuren f: FigurenKopieren) {
			if(etwas ==true) {
				if(f.getID() == 1 && f.farbe != jetzt) {
					konig = f;
				}
			}else {
				if(f.getID()  == 1 && f.farbe == jetzt) {
					konig = f;
				}
			}
		}
		return konig;
	}
	public boolean Schach2(){
		Figuren konig = holKonig(false);
		for(Figuren f : FigurenKopieren){
			if(f.farbe != konig.farbe && f.Bewegung(konig.spalte, konig.reihe)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean KonigBewegung(Figuren konig){
		if(konig.getID() == 1) {
			for(Figuren f:FigurenKopieren) {
				if(f !=konig && f.farbe != konig.farbe && f.Bewegung(konig.spalte, konig.reihe)) {
					return true;
				}
			}
		}
		return false;
	}
	public void Rochade() {
		if(Rochade != null) {
			if(Rochade.spalte == 0) {
				Rochade.spalte = Rochade.spalte +3;
			}else if(Rochade.spalte == 7){
				Rochade.spalte= Rochade.spalte-2;
			}
			Rochade.XKoordinaten = Rochade.holXKoordinaten(Rochade.spalte);
		}
	}
	public void Runde() {
		if(jetzt == weiss) {
			jetzt = schwarz;
		}else {
			jetzt =weiss;
		}
		figur = null;
	}
	public void AlleFigurenKopieren(ArrayList<Figuren> vonWo, ArrayList<Figuren> ziel){
		ziel.clear();
		for (int i = 0; i < vonWo.size(); i++){
			ziel.add(vonWo.get(i));
		}
	}
	public void paint(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D)g;
				
		for(int x = 0;x<8;x++) {
			for(int y = 0;y<8;y++) {
				
				if((x+y)%2==0) {
					g2.setColor(new Color(241, 217, 192));//hell
				}else {
					g2.setColor(new Color(169, 122, 101));//dunkel
				}
				//x,y,breite,höhe
				g2.fillRect(x*100, y*100,100, 100);	
			}
		}
		for(Figuren f : alleFiguren) {
			g2.drawImage(f.Bild, f.XKoordinaten, f.yKoordinaten, 100,100,null);
		}
	}
	@Override
	public void run() {
//		diesen code habe von https://www.quora.com/What-is-a-game-loop-in-Java-What-does-it geholt 07.04.2024
		double timeChange = 0; 
		double time1 = System.nanoTime(); 
		double time2; 
		 
		while(true){ 
			time2 = System.nanoTime(); 
			timeChange += (time2-time1 ) / (1000000000/60); 
			time1 = time2;
			if(timeChange > 1) { 
				 Spiel();
				 repaint();
				 timeChange--;
				}
		}
}
	@Override
	public void mouseDragged(MouseEvent e){
		XKor = e.getX();
		YKor = e.getY();
	}
	@Override
	public void mouseMoved(MouseEvent e){
		XKor = e.getX();
		YKor = e.getY();
	}
	@Override
	public void mousePressed(MouseEvent e){
		klicken = true;
	}
	@Override
	public void mouseReleased(MouseEvent e){
		klicken = false;
	}
	//die werden gar nicht benutzt!!!
	@Override
	public void mouseClicked(MouseEvent e){
		
	}
	@Override
	public void mouseEntered(MouseEvent e){
		
	}
	@Override
	public void mouseExited(MouseEvent e){
	}	
}