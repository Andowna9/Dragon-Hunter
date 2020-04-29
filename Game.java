

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;

import edu.princeton.cs.introcs.Draw;

import edu.princeton.cs.introcs.StdDraw;

public class Game extends State {
	
	//static ArrayList<Tile> tiles;
	
	//static ArrayList<RedOrb> orbs;
	
	private Thread t;
	
	static ArrayList<Dragon> dragons;
	
	static Draw window;
	
	static CountDown countdown;
	
	static Font font;
	
	static Dragon redDragon; // IMPORTANTE!: *Cambiar por ArrayList*
	
	static Dimension screenSize;
	
	static Player magician;
	
	static String background;
	
	static int score = 0;
	
	public static final int WIDTH = 800;
	
	public static final int HEIGHT = 600;
	
	public static final long FPS = 60;
	
	private static long targetTime = 1000 / FPS; //Tiempo para mostrar cada cuadro en milisegundos
	
	public Game(GameStateManager gsm) {
		
		this.gsm = gsm;
		
		
	}
	
	
	public static void drawTimer(String text) {
		
		
		window.text(200, 700, text);
		
	
	}
	
	public static void drawScore() {
		
		window.text(WIDTH-150, 550, "Score: " + String.valueOf(score));
		
	}
	
	public static void die() {
		
		magician.setDead();
	}
	
	
	public static void initGame() {
		
		//tiles = new ArrayList<>();
	
		
		
		countdown = new CountDown(300);
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		window = new Draw("LEVEL");
		
		magician = new Player();
		
		redDragon = new Dragon();
		
		dragons = new ArrayList<>();
		
		dragons.add(redDragon);
		
		background = "resources/forest.png";
		
		window.setCanvasSize(WIDTH,HEIGHT);
		
		int x = (screenSize.width - WIDTH) / 2;
		
		int y = (screenSize.height - HEIGHT) / 3;
		
		window.setLocationOnScreen(x, y);
		
		window.setXscale(0,WIDTH);
		
		window.setYscale(0,HEIGHT);	
		
		window.enableDoubleBuffering();
		
		window.setPenColor(Color.WHITE);
		
		font = new Font("Arial", Font.BOLD, 50);
		
		window.setFont(font);
		
		/* for (int i = 0; i <= Game.WIDTH; i += 50) {
			
			Tile t = new Tile(50,i,120);
			
			t.draw(window);
			
			tiles.add(t);
		} */
		
	}
	

	@Override
	public void init() {
		
		t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				initGame();
				
				Timer timer = new Timer();
				
				timer.schedule(countdown, 0,1000);
				
				long startTime;
				
				long timePassed;
				
				long wait;
				
				while (true) {
					
					startTime = System.nanoTime();
					
					// Colisiones
					
					redDragon.checkHit(magician);
					
					// Hechizos del mago
					
					for (Dragon dragon: dragons) {
						
						for (Iterator iterator = magician.getSpells().iterator(); iterator.hasNext();) {
							
							Spell spell = (Spell) iterator.next();
							
							if (spell.checkCollision(dragon)) {
								
								iterator.remove();
							}
							
						}
					}
					
					// Actualizar 
					
					magician.update();
					
					for (Dragon dragon: dragons) {
						
						dragon.update();
					}
					
					/*if (magician.getDy() < 0) {
					
					for (Tile t: tiles) {
						
						if (magician.getY() - 60 < t.topPart()) {
							
							magician.setDy(0);
							
							magician.setY(t.topPart() + 70);
							
							magician.setFalling();
						}
					}
					
					}*/
					
					
					// Dibujar
					
					window.clear(Color.BLACK);
					
					window.picture(400, 300, background);
					
					/* for (Tile t: tiles) {
						
						t.draw(window);
						
						
					} */
					
					
					
					magician.draw();
					
					for (Dragon dragon: dragons) {
						
						dragon.draw();
					}
					
					// Segundos restantes
					
					
					window.text(50, WIDTH - font.getSize() * 5,String.valueOf(countdown.getSecondsLeft()));
					
					drawScore();
					
					magician.drawHealth();
					
					
					window.show();
					
					// Comprobamos la vida del mago
					
					if (magician.getHealth() <= 0) {
						
						magician.setDead();
						
						dragons.remove(redDragon);
						
						gsm.setState(GameStateManager.MENU);
						
						break;
						
					}
					
					
					
					timePassed = System.nanoTime() - startTime;
					
					wait = targetTime - timePassed / 1000000;
					
					if (wait < 0) wait = 5;
					
					try {
						
						Thread.sleep(wait);
					
					} catch (InterruptedException e) {
					
						e.printStackTrace();
					}
					
					
				}
				
				t.interrupt();
				
			}
			
		
			
			
		});
		
		
		t.start();
		

		
		

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}




}
