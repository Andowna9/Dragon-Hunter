
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

// Clase que utilizaremos para guardar la mejor puntuación y el nombre del usuario

public class HighScore implements Serializable {
	

	private static final long serialVersionUID = 1L;

	private int score;
	
	private String playerName;
	
	public HighScore(int score, String playerName) {
		
		this.score = score;
		
		this.playerName = playerName;
		
	}
	
	public HighScore() {
		
		this.score = 0;
		
		this.playerName = "";
	}
	
	public void setName(String n) {
		
		this.playerName = n;
	}
	
	
	public void setScore(int s) {
		
		this.score = s;
	}
	
	public int getScore() {
		
		return score;
	}
	
	public String getPlayerName() {
		
		return playerName;
	}
	
	// Guardamos los datos
	
	public void save (String filename) {
		
		FileOutputStream fos;
		
		ObjectOutputStream oos;
		
		try {
			
			fos = new FileOutputStream(filename);
			
			oos = new ObjectOutputStream(fos);
			
			oos.writeObject(this);
			
			oos.close();
			
			fos.close();
			
		
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			

		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
	// Leemos los datos
	
	public void load(String filename) {
		
		FileInputStream fis;
		
		ObjectInputStream ois;
		
		try {
			
			fis = new FileInputStream(filename);
			
			ois = new ObjectInputStream(fis);
			
			HighScore h = (HighScore) ois.readObject();
			
			this.setName(h.playerName);
			
			this.setScore(h.score);
			
			ois.close();
			
			fis.close();
		
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		
		
	}
	
	public static void deleteFileContents(String filename) {
		
		PrintWriter pw;
		
		try {
			
			pw = new PrintWriter(filename);
			
			pw.write("");
			
			pw.close();
			
		
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		
	}

	@Override
	public String toString() {
		return playerName + ":  " + score + " points." ;
	}
	
	

}
