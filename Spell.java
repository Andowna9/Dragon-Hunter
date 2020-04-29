
public class Spell extends Entity {
	
	public Spell() {
		
		height = 100;
		
		width = 100;
		
		attackDamage = 1;
		
		moveSpeed = 4.8;
		
		jumping = false;
		
		init();
		
	}
	
	public void init() {
		
		// Cargamos los sprites
		
		loadSprites("resources/sprites/Spell/");
		
		// Iicializamos la animación
		
		animation = new Animation();
		
		currentAction = Action.SPELL;
		
		animation.setFrames(sprites.get(currentAction));
		
		animation.setDelay(100);
		
		
	}
	
	public void update() {
		
		
		if (!jumping) {
			
			dy = moveSpeed;
		}
		
		
		
		animation.update();
		
		y += dy;
		
		
		
	}
	
	public void draw() {
		
		window.picture(x, y, animation.getFrame(),60,60);
	}

}
