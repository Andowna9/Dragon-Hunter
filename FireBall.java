import java.util.Timer;
import java.util.TimerTask;

import edu.princeton.cs.introcs.Draw;

public class FireBall extends Entity {
	
	private boolean ground; // Booleano para saber cuando se encuentra en tierra la bola de fuego
	
	private Timer deathTime;
	
	

	public FireBall() {
		
		super();
		
		deathTime = new Timer();
		
		ground = false;
		
		width = 20;
		
		height = 20;
	
		fallSpeed = 2.7;
		
		maxFallSpeed = 8.5;
		
		// Aplicamos gravedad a la bola de fuego desde el momento en que se lance por el drag�n
		
		falling = true;
		
		init();
		
	}
	
	private void init() {
		
		loadSprites("resources/sprites/Fireball/");
		
		animation = new Animation();
		
		currentAction = Action.FIREBALL;
		
		animation.setFrames(sprites.get(currentAction));
		
		animation.setDelay(100);
		
	}
	
	public void update() {
		
		
		floorCollision();
		
		
		// Si est�n cayendo las bolas de fuego, decrementamos la posici�n en el eje y hasta igualar la velocidad de ca�da m�xima
		
		if (falling) {
			
			if (dy <= 0) {
				
				dy -= fallSpeed;
				
				if (dy < -maxFallSpeed) {
					
					dy = -maxFallSpeed;
				}
				
				
				
				
			}
	
			
			
		}
		
		else {
			
			if (currentAction != Action.FIRE) {
			
				currentAction = Action.FIRE;
			
				animation.setFrames(sprites.get(currentAction));
			
				animation.setDelay(100);
				
				ground = true;
				
				deathTime.schedule(new TimerTask() {
					

					@Override
					public void run() {
						
						dead = true;
						
						
						
					}
					
					
				}, 5000);
				
				
			}
		}
		
		// Actualizamos posici�n
		
		y += dy;
		
		animation.update();
		
		
	}
	
	public boolean ground() {
		
		return ground;
	}
	
	
	public void draw() {
		
		window.picture(x, y, animation.getFrame());
		
		
	}
	
	public boolean isDead() {
		
		return dead;
	}


	


}
