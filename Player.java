
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import edu.princeton.cs.introcs.Draw;
import edu.princeton.cs.introcs.StdDraw;

	public class Player extends Entity {
		
		//private boolean moving;
		
		private boolean attacking;
		
		private boolean left;
		
		private boolean right;
		
		private String lastKey;
		
		private double maxJumpSpeed;
		
		private int numJumps;
		
		private boolean fire;
		
		private int wandPosition;
		
		// Acciones posibles del jugador
		
		private ArrayList<Spell> spells;
		
		private Spell currentSpell;



		public Player() {
			
			super();
			
			health = 3;
			
			height = 120;
			
			width = 30;
			
			facingRight = true;
			
			moveSpeed = 5.2;
			
			stopSpeed = 6;
			
			maxSpeed = 12.5;
			
			jumpStart = 30;
			
			fallSpeed = 18;
			
			x = 200;
			
			y = 200;
			
			dx = 0;
			
			dy = 0;
			
			falling = false;
			
			maxFallSpeed = 40;
			
			stopJumpSpeed = 3;
			
			right = false;
			
			left = false;
			
			jumping = false;
			
			spells = new ArrayList<>();
			
			attackRange = 30;
			
			fire = true;

			init();

		}
		
		public ArrayList<Spell> getSpells() {
			
			return spells;
		}
		
		private void fixBounds() {
			
			// El jugador no puede salir de la pantalla horizontalmente
			
			if (x - 40 <= 0) {
				
				x = 40;
			}
			
			else if (x + 40 >= Game.WIDTH) {
				
				x = Game.WIDTH - 40;
				
			}
			
			// Colisión con el suelo
			
			
			floorCollision();
			
			// Eliminación de hechizos que están fuera de la pantalla
			
			for (Iterator iterator = spells.iterator(); iterator.hasNext();) {
				Spell spell = (Spell) iterator.next();
				
				if (spell.getY() > Game.HEIGHT) {
					
					iterator.remove();
				}
				
			}
			
		}

		public void init() {
			
			// Cargamos los sprites contenidos en el directorio indicado (relativo al proyecto)
			
			 loadSprites("resources/sprites/Magician/");

			// Inicimos la animación de reposo

			animation = new Animation();
			
			currentAction = Action.IDLE;
			
			animation.setFrames(sprites.get(currentAction));
			
			animation.setDelay(-1);

		}
		
		
		// Método para obtener la posición del jugador después de comprobar el estado de las teclas
		
		private void getNextPosition() {
			
		
			
			// MOVIMENTO EN EJE X
			
			// Aceleración hasta alcanzar la velocidad máxima para MOVE_LEFT Y MOVE_RIGHT
			
			if (left) {
				
				dx -= moveSpeed;
				
				if (dx < -maxSpeed) {
					
					dx = -maxSpeed;
				}
				
				
			}
			
			else if (right) {
				
				dx += moveSpeed;
				
				if (dx > maxSpeed) {
					
					dx = maxSpeed;
				}
				
				
			}
			
			// Si deja de estar en moviento, se frena
			
			else {
				
				if (dx > 0) {
					
					dx -= stopSpeed;
					
					if (dx < 0) {
						
						dx = 0;
					}
					
					
				}
				
				else if (dx < 0) {
					
					dx += stopSpeed;
					
					if (dx > 0) {
						
						dx = 0;
					}
					
					
				}
			}
			
			
			// MOVIMIENTO EN EL EJE Y
			
		
			
			
			if (jumping && !falling) {
				
				dy = jumpStart;
				
				falling = true;
				
			}
			
			
			
			if (falling) {
				
				if (dy <= 0) {
					
					dy -= fallSpeed;
					
					jumping = false;
					
					numJumps = 0;
				}
				
				else if (dy > 0) { // && !jumping
					
					dy -= stopJumpSpeed;
				}
				
				if (dy < -maxFallSpeed) {
					
					dy = -maxFallSpeed;
				
				}
			
			}
			
			// Actualización de posición
			
			x += dx;
			
			y += dy;
			
			
		
		}
		
		private void spellAvailable() {
			
			if (currentSpell != null && currentSpell.getY() > y + attackRange) {
				
				fire = true;
			}
			
		
		}

		public void update() {
			
			checkState();
			
			checkKeys();
			
			getNextPosition();
			
			fixBounds();
			
			
			//ATACKING
			
			if (attacking && fire) {
				
				// Creamos un nuevo hechizo, lo colocamos cerca del bastón del mago y lo añadimos a la lista
				
				Spell spell = new Spell();
				
				currentSpell = spell;
				
				spell.setPosition((int) x, (int)y);
				
				spells.add(spell);
				
				fire = false;
				
				
			}
			
			// FALLING
			
			if (dy < 0) {
				
				if (currentAction != Action.IDLE) {
					
					currentAction = Action.IDLE;
					
					animation.setFrames(sprites.get(currentAction));
					
					setOrientation();
					
					animation.setDelay(-1);
					
				}
			}
			
			// JUMPING
			
			else if (dy > 0) {
				
				if (facingRight) {
					
					if (currentAction != Action.JUMP_RIGHT) {
					
						currentAction = Action.JUMP_RIGHT;
					
						animation.setFrames(sprites.get(currentAction));
					
						animation.setDelay(100);
					
					}
					
				
					
					
				}
				
				else {
					
					if (currentAction != Action.JUMP_LEFT) {
						
						currentAction = Action.JUMP_LEFT;
					
						animation.setFrames(sprites.get(currentAction));
						
						animation.setDelay(100);
				
					}
					
					
				if (animation.getCurrentFrame() == 1) {
					
					animation.setDelay(-1); 
				} 
					
					
				}
				
				
				
				/*if (currentAction != Action.JUMP_LEFT) {
					
					currentAction = Action.JUMP_LEFT;
					
					animation.setFrames(sprites.get(currentAction));
					
					animation.setDelay(1000);
				}
				
				if (animation.hasOneCicle()) {
					
					animation.setDelay(-1);
				} */
				
				
			}
			
			else if (left || right) {
				
				if (left) {
					
					if (currentAction != Action.MOVE_LEFT) {
						
						currentAction = Action.MOVE_LEFT;
						
						animation.setFrames(sprites.get(currentAction));
						
						animation.setDelay(10);
					}
					
				}
				
				else if (right) {
					
					if (currentAction != Action.MOVE_RIGHT) {
						
						currentAction = Action.MOVE_RIGHT;
						
						animation.setFrames(sprites.get(currentAction));
						
						animation.setDelay(10);
					}
					
					
				}
				
				
			}
			
			
			else if (!dead) {
				
				if (currentAction != Action.IDLE) {
					
					currentAction = Action.IDLE;
					
					animation.setFrames(sprites.get(currentAction));
					
					setOrientation();
					
					animation.setDelay(-1);
				}
				
				
			}
			
	
			
			animation.update();
			
			// Actualización de los hechizos
			
			for (Spell spell: spells) {
				
				spell.update();
			}

		}
			
		
	
			
			
			/*if (!window.isKeyPressed(KeyEvent.VK_LEFT) && !window.isKeyPressed(KeyEvent.VK_RIGHT)) {
				
				if (currentAction != Action.IDLE) {
				
				dx = 0;
				
				left = false;
				
				right = false;
				
				currentAction = Action.IDLE;
				
				animation.setFrames(sprites.get(currentAction));
				
				 if (facingRight) {
					
					animation.setCurrentFrame(0);
					}
				
				else { 
					
					animation.setCurrentFrame(1);
				} 
				
				animation.setDelay(-1);
				
				
				}
				
			} */
			
			
		
			
			
			
			
		private void checkKeys() {
			
			if (!dead) {
			
			
			if (window.isKeyPressed(KeyEvent.VK_LEFT)) {
				
				setLeft(true);
				
				facingRight = false;
				
			
				
			}
			
			else {
				
				
				
				setLeft(false);
				
				
			}
			
			if (window.isKeyPressed(KeyEvent.VK_RIGHT)) {
				
				setRight(true);
				
				facingRight = true;
				
				
			}
			
			else {
				
				setRight(false);
				
				
			}
			
			if (window.isKeyPressed(KeyEvent.VK_SPACE)) {
				
				

				setJumping(true);
				
				
			
			}
			
			else {
				
				setJumping(false);
			}
			
			if (window.isKeyPressed(KeyEvent.VK_F)) {
				
				
				setAttacking(true);
				
				spellAvailable();
				
				
			}
			
			else {
				
				setAttacking(false);
			}
			
			}
			
			
			
			
		}
		
		public void setDead() {
			
			dead = true;
		}
		
		private void checkState() {
			
			if (dead) {
				
				
				if (currentAction != Action.DEATH) {
				
					currentAction = Action.DEATH;
				
					animation.setFrames(sprites.get(Action.DEATH));
				
					animation.setDelay(10);
				
				
					
				}
				
				else if (animation.getCurrentFrame() == 2)
					
					animation.setDelay(-1);
					
				}
					
					
					
					
				}
				
			
				
				
		
		
		
		private void setOrientation() {
			
			if (facingRight) {
				
				animation.setCurrentFrame(0);
				
			}
			
			else {
				
				animation.setCurrentFrame(1);
				
				
			}
		}
		
		public void checkHit(Dragon d) {
			
			for (Iterator iterator = spells.iterator(); iterator.hasNext();) {
				Spell spell = (Spell) iterator.next();
				
				if (spell.checkCollision(d)) {
					
					
					d.setHit(spell.attackDamage);
					
				}
				
			}
		}
		
		
		public void setHit(int i) {
			
			health -= i;
		}
		
		public void drawHealth() {
			
			window.text(Game.WIDTH - 100, 35, "Hp: " + health);
		}


		public void draw() {
			

			window.picture(x, y, animation.getFrame());
			
			// Dibujado de hechizos
			
			for (Spell spell: spells) {
				
				spell.draw();
			}
			
			
			

		}
		
		public void die() {
			
			animation.setFrames(sprites.get(Action.DEATH));
			
			animation.setDelay(1000);
			
		}
		
		public void setJumping(boolean b) {
			
			jumping = b;
		}
		
		public void setLeft(boolean b) {
			
			left = b;
		}
		
		public void setRight(boolean b) {
			
			right = b;
		}
		
		public double getY() {
			
			return y;
		}
		
		public void setDy(int i) {
			
			dy = i;
		}
		
		public void setY(double i) {
			
			y = i;
		}
		
		public boolean getJumping() {
			
			return jumping;
		}
		
		public double getDy()  {
			
			return dy;
		}
		
		public void setFalling() {
			
			falling = false;
			
		}
		
		public void setAttacking(boolean b) {
			
			attacking = b;
		}
		
		public boolean getAttacking() {
			
			return attacking;
		}

}
