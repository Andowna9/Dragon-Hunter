import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Menu extends State {
	
	private JFrame frame;
	
	private MenuPanel gPanel;
	
	private JButton youtube;
	

	
	public Menu(GameStateManager gsm) {
		
		this.gsm = gsm;
		
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
	public void init() {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				frame = new JFrame();
				
				// Botones
				
				youtube = new JButton();
				
				// Propiedades básicas de la ventana de Swing
				
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				frame.setTitle("DRAGON HUNTER");
				
				
				gPanel = new MenuPanel(gsm);
				
				frame.setContentPane(gPanel);
				
				frame.addKeyListener(gPanel);
				
				ImageIcon icon = new ImageIcon("resources/icon.jpg");
				
				frame.setIconImage(icon.getImage());
				
				frame.setSize(800,600);
				
				frame.setResizable(false);
				
				frame.setLocationRelativeTo(null); // Localización en el centro de la pantalla
				
				
				//this.add(youtube,BorderLayout.SOUTH);
				
				frame.setVisible(true);
				
				
				
			}
		});
		
	}
	
	public void close() {
		
		frame.dispose();
	}
	



}
