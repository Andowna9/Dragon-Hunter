import java.util.ArrayList;

public class GameStateManager extends State {
	
	
private ArrayList<State> states;

	
	
	private int currentState;
	
	// Cada estado posible ocupa una posición de la lista y ,por tanto, se le asigna un índice
	
	public static final int MENU = 0;
	
	public static final int LEVEL = 1;
	
	public GameStateManager() {
		
		states = new ArrayList<>();
		
		states.add(new Menu(this)); // Asociamos el gestor de estados para los estados, de tal forma que puedan acceder a sus métodos
		
		states.add(new Game(this));
		
		setState(MENU); // Por defecto, el estado inicial es el menú del juego
		
	}
	
	
	public void update() {
		
		states.get(currentState).update();
	}
	
	
	
	public void setState(int state) {
		
		currentState = state;
		
		states.get(currentState).init();
		
		
	}
	
	public void endState(int state) {
		
		states.get(currentState).close();
		
	}
	
	
	public void draw() {
		
		states.get(currentState).draw();
	}


	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	
	

}
