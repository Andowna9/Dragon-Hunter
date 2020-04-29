import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import edu.princeton.cs.introcs.Draw;

public abstract class Entity {
	
	// Ventana de dibujado (referencia est�tica y constante, com�n a todas las instancias)
	
	protected static final Draw window = Game.window;
	
	// Posici�n en y del borde superior del suelo
	
	protected final int FLOORLEVEL = 60; // Anterior valor: 125
	
	// Listado de acciones compartidas por todos los personajes
	
	protected enum Action {
		
		IDLE,
		
		MOVE_LEFT,
		
		MOVE_RIGHT,
		
		JUMP_LEFT,
		
		JUMP_RIGHT,
		
		SPELL,
		
		DEATH,
		
		FLY_LEFT,
		
		FLY_RIGHT,
		
		FIREBALL,
		
		FIRE

	}
	
	// Posici�n en ambos ejes
	
	protected double x;
	
	protected double y;
	
	// Vectores de movimiento (velocidad)
	
	protected double dx;
	
	protected double dy;
	
	// Control de de velocidad y aceleraci�n
	
	protected double moveSpeed;
	
	protected double stopSpeed;
	
	protected double maxSpeed;
	
	protected double jumpStart;
	
	protected double fallSpeed;
	
	protected double stopJumpSpeed;
	
	protected double maxFallSpeed;
	
	protected boolean facingRight;
	
	protected boolean falling;
	
	protected boolean jumping;
	
	// Booleano para comprobar el estado en todo momento (vivo o muerto)
	
	protected boolean dead;
	
	// Vida 
	
	protected int health;
	
	// Ataque
	
	protected int attackDamage;
	
	protected int attackRange;
	
	// Variables que se utilizaran para gestionar las animaciones de cada entidad
	
	protected HashMap <Action,ArrayList<String>> sprites;
	
	protected Action currentAction;
	
	protected Animation animation;
	
	// Tama�o de los sprites
	
	protected int height;
	
	protected int width;
	
	// Tama�o de ca
	
	
	// MODIFICAR CALCULANDO CORRECTAMENTE LA POSICI�N DEL RECT�NGULO DE COLISI�N
	
	public Rectangle getRectangle() {
		
		return new Rectangle((int)x-width/3,(int)y+height/3,width/4,height); // X e Y son las coordenadas de la esquina superior izquierda
	}
	
	// M�todo para comprobar colisiones entre objetos que heredan de Entity
	
	public boolean checkCollision(Entity e) {
		
		Rectangle r1 = this.getRectangle();
		
		Rectangle r2 = e.getRectangle();
		
		return r1.intersects(r2);
		
		
	}
	
	
	public void loadSprites(String path) {
		
		 sprites = new HashMap<>(); // Utilizamos el HashMap para poder acceder a cada grupo de sprites utilizando cada acci�n como clave
		 
		 // Recorremos cada posici�n de la enumeraci�n para ir a�adiendo las rutas de las im�genes 
		 
		 for (Action a: Action.values()) {
			 
			 ArrayList <String> images = new ArrayList<>();
			 
			 String folderName =  path + a.name(); // Obtenemos el nombre de la acci�n
			 
			 File directory = new File(folderName);
			 
			if (!directory.exists()) continue; // En caso de que no se encuentre la carpeta, querr� decir que no es una acci�n del personaje
			 
			 for (int i = 1; i <= directory.listFiles().length; i++) {
				 
				 String imagePath = folderName + "/k" + String.valueOf(i) + ".png";
				 
				 images.add(imagePath);
				 
			 }
			 
			 sprites.put(a,images);
			 
		 }
		
		
	}
	
	// Hacemos uso de integers ya que no se requiere demasiada exactitud al establecer la posici�n inicial
	
	public void setPosition(int x,int y) {
		
		this.x = x;
		
		this.y = y;
	}
	
	public void floorCollision() {
		
		if (y - height / 2 < FLOORLEVEL) {
			
			dy = 0;
			
			y = FLOORLEVEL + height / 2;
			
			falling = false;
			
		
			
		}
		
		else if (y - height / 2 > FLOORLEVEL && !falling && !jumping) {
			
			falling = true;
			
		}
		
	}
	
	public int getWidth() {
		
		return width;
	}
	
	public int getX() {
		
		return (int) x;
	}
	
	public double getY() {
		
		return y;
	}
	
	public int getHealth() {
		
		return health;
	}

}
