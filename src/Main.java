import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Fenster fenster = new Fenster();
		
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(fenster);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		fenster.run();
	}
}	