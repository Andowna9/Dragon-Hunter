
public abstract class State {

	
	protected GameStateManager gsm;
	
	public abstract void update();
	
	public abstract void draw();
	
	public abstract void init();
	
	public abstract void close();
	
	
}
