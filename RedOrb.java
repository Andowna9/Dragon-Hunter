
public class RedOrb extends Entity {
	
	// Número de puntos que el jugador recibe por cogerlo
	
	private int score;
	
	private String image;
	
	public RedOrb(String filename) {
		
		this.image = image;
		
		score = 3;
		
		
	}
	
	
	public void update() {
		
		dx = -3;
		
		
	}
	
	public void draw() {
		
		window.picture(x, y, image);
	}

}
