import java.util.TimerTask;

import edu.princeton.cs.introcs.Draw;

public class CountDown extends TimerTask {
	
	
	private int seconds;
	
	public CountDown(int seconds) {
		
		this.seconds = seconds + 1;
		
	}

	@Override
	public void run() {
		
		if (seconds > 0) {
			
			seconds--;
		}
		
		else {
			
			// Fin de la cuenta atrás
			
			Game.die();
			
			try {
				Thread.sleep(5000);
			}
			
			catch (Exception e) {
				
			   e.printStackTrace();
				
			}
			
			System.exit(0);
			
			
		}
		
		
		
	}
	
	
	public int getSecondsLeft() {
		
		return seconds;
	}
	

	
	

}
