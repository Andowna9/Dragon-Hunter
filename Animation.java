
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Animation {

	// Determina la imagen actual

	private int currentFrame;

	// Lista que contiene todas las imágenes (rutas) de una acción del jugador

	private ArrayList <String> frames;
	
	private boolean finished;

	// Temporizador
	
	private Timer timer;

	private long delay;

	// Variable para saber cuando termina un ciclo de animación

	private boolean oneCicle;

	// Tiempo de inicio

	private long startTime;

	public Animation() {
		

	oneCicle = false;
	

	}

	public void setFrames(ArrayList<String> frames) {


	this.frames = frames;

	currentFrame = 0;

	oneCicle = false;

	startTime = System.nanoTime();
	


	

	}

	public void update() {

		if (delay == -1) return;
		

		long timePassed = (System.nanoTime() - startTime) / 1000000;

		if (timePassed > delay) {

			currentFrame++;

			startTime = System.nanoTime(); 
		} 
		
		if (currentFrame == frames.size()) {

			currentFrame = 0;

			oneCicle = true; 

		} 

	}
	
	public boolean hasOneCicle() {
		
		return this.oneCicle;
	}


	public void setCurrentFrame(int i) {

		currentFrame = i;

	}

	public String getFrame() {

		return frames.get(currentFrame);
	}
	
	public int getCurrentFrame() {
		
		return currentFrame;
	}

	public void setDelay(long d) {
		
		delay = d;
		

	}


	




}
