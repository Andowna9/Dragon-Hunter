

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;

import edu.princeton.cs.introcs.Draw;

public class Dragon extends Entity {
	
	// L�mites de movimiento a izquierda y derecha
	
	protected final int leftLimit = -100;  
	
	protected final int rightLimit = Game.WIDTH + 100;
	
	private ArrayList<FireBall> fireBalls;
	
	private int headPosition;
	
	private int damage;
	
	private Timer fireDeath;
	
	
	public Dragon() {
		
		super();
		
		fireDeath = new Timer();
		
		damage = 1;
		
		width = 172;
		
		height = 160;
		
		moveSpeed = 5;
		
		init();
		
		health = 3;
		
		Random bool = new Random(System.currentTimeMillis()); // Aumentamos la aleatoriedad haciendo que la semilla dependa del tiempo de ejecuci�n
		
		facingRight = bool.nextBoolean(); // Generamos un booleano (true o false)
		
		int firstX = (facingRight) ? rightLimit : leftLimit; // Operador ternario
		
		setPosition(firstX,500);
		
		fireBalls = new ArrayList<>();
		
	}
	
	private void fixBounds() {
		
		// El drag�n se mueve horizontalmente de un extremo a otro hasta que sea atrapado por el mago
		
		// A�adimos 100 p�xeles de margen para que el drag�n no gire hasta que est� fuera del campo de visi�n
		
		if (x < leftLimit) {
			
			facingRight = true;
			
			currentAction = Action.FLY_RIGHT;
			
			animation.setFrames(sprites.get(currentAction));
			
			animation.setDelay(100);
		}
		
		else if (x > rightLimit) {
			
			
			facingRight = false;
			
			currentAction = Action.FLY_LEFT;
			
			animation.setFrames(sprites.get(currentAction));
			
			animation.setDelay(100);
			
			
		}
	}
	
	
	private void init() {
		
		loadSprites("resources/sprites/Dragon/");
		
		animation = new Animation();
		
		currentAction = Action.FLY_LEFT;
		
		animation.setFrames(sprites.get(currentAction));
		
		animation.setDelay(100);
	
		
	}
	
	// M�todo para comprobar la vida del drag�n
	
	private void checkHealth() {
		
		if (health <= 0) {
			
			dead = true;
		}
	}
	
	public void update() {
		
		fixBounds();
		
		checkHealth();
		
		// Dependiendo de su sentido la velocidad cambia de signo
		
		if (facingRight) {
			
			dx = moveSpeed;
			
			headPosition = (int) x + width/2;
		}
		
		else {
			
			dx = -moveSpeed;
			
			headPosition = (int) x - width/2;
			
			
		}
		
		animation.update();
		
		// Creaci�n de bolas de fuego
		
		Player p = Game.magician;
		
		double distance = Math.abs(p.getX() - (headPosition));
		
		if (distance <= 4 ) {
			
			FireBall f = new FireBall();
			
			f.setPosition(facingRight ? (int) x + width/2 : (int) x - width/2 , (int)y - height / 3);
		
			fireBalls.add(f);
		
		}
		
		// Actualizaci�n de bolas de fuego
		
		for (FireBall f: fireBalls) {
			
			f.update();
		}
		
	
		
		// Actualizaci�n de la posici�n
		
		x += dx;
		
	}
	
	public void checkHit(Player p) {
		
		// Utilizamos un iterador para poder borrar elementos mientras recorremos la lista, sin producir bugs
		
		for (Iterator iterator = fireBalls.iterator(); iterator.hasNext();) {
			
			FireBall fireBall = (FireBall) iterator.next();
			
			// En caso de que haya colisi�n, eliminamos la bola de fuego actual y restamos el da�o del drag�n
			
			if (fireBall.checkCollision(p)) {
				
				p.setHit(damage);
				
				if (!fireBall.ground()) {
				
				iterator.remove();
				
				}
				
				
			}
			
			else if (fireBall.isDead()) {
				
				iterator.remove();
			}
			
			
			
		}
	
		
	}
	
	public void setHit(int damage) {
		
		health -= damage;
		
		
	}
	
	
	public void draw() {
		
		window.picture(x, y, animation.getFrame());
		
		// Mostramos la vida en todo momento para depurar errores que puedan surgir
		
		window.text(x, y, String.valueOf(health));
		
		for (FireBall f: fireBalls) {
			
			f.draw();
		}
		
	}
	
	public void kill() {
		
		dead = true;
	}
	
	public boolean isDead() {
		
		return dead;
	}

}
