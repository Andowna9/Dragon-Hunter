import edu.princeton.cs.introcs.Draw;

public class Tile {
	
	private int size;
	
	private int x;
	
	private int y;
	
	public Tile (int size,int x, int y) {
		
		this.size = size;
		
		this.x = x;
		
		this.y = y;
	
	}
	
	public void draw(Draw window) {
		
		window.filledSquare(x, y, size/2);
	}
	
	public int topPart() {
		
		return y + size/2;
	}

}
