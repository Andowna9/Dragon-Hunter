import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.net.*;



public class MenuPanel extends JPanel implements KeyListener {
	
	// Paneles
	
	private JPanel optionsPanel;
	
	private JPanel linksPanel;
	
	// Botones
	
	JButton youtube;
	
	
	// Etiquetas de texto
	
	
	private JLabel title;
	
	
	// Fuentes, tamaño de texto y colores
	
	private Color titleColor;
	
	private int textSize;
	
	private Font textFont;
	
	private Color textColor;
	
	private Color selectedColor;
	
	private int titleSize;
	
	private Font titleFont;
	
	// Opciones del menú
	
	private String [] options = {"Jugar","Récord","Ayuda"};
	
	private int currentOption = 0;
	
	private JLabel [] optionLabels;
	
	private GameStateManager gsm;
	
	public MenuPanel(GameStateManager gsm) {
		
		this.gsm = gsm;
		
		// Establecemos un GridLayout con cuatro filas y una única columna
		
		this.setLayout(new BorderLayout());
		
		Image bg = Toolkit.getDefaultToolkit().createImage("resources/background.jpg");
		

		
		
		textSize = 20;
		
		titleColor = Color.GRAY;
		
		textColor = Color.WHITE;
		
		selectedColor = Color.YELLOW;
		
		
		

		titleSize = 50;
		
		textFont = new Font(Font.SANS_SERIF, Font.BOLD, textSize);
		
		titleFont = new Font(Font.DIALOG_INPUT,Font.BOLD,titleSize);
		
		// Botones
		
		ImageIcon icon = new ImageIcon("resources/menu/youtube_icon.png");
		
		Image img = icon.getImage();
		
		icon = new ImageIcon(img.getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		
		
		youtube = new JButton(icon);
		
		youtube.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				goToURL("https://www.youtube.com/channel/UCI9UyEzk-VNV_IYBcRrcEzw?view_as=subscriber");
			
				
			}
		});
		
		

		
		
		
		// Título
		
		title = new JLabel("Dragon Hunter",JLabel.CENTER);
		
		title.setForeground(titleColor);
		
		title.setFont(titleFont);
		
		//Paneles
		
		linksPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		optionsPanel = new JPanel(new GridLayout(4,1));
		
		optionsPanel.add(title);
		
		// Opciones
		
		optionLabels = new JLabel [options.length];
		
		for (int i = 0; i < options.length;i++) {
			
			JLabel l = new JLabel(options[i],JLabel.CENTER);
			
			l.setForeground(textColor);
			
			l.setFont(textFont);
			
			optionLabels[i] = l;
			
			optionsPanel.add(l);
			
		}
		
		// Por defecto la opción seleccionada es Jugar
		
		optionLabels[currentOption].setForeground(selectedColor);
		
		//linksPanel.add(youtube);
		
		optionsPanel.setBackground(Color.BLACK);
		
		
		linksPanel.setBackground(Color.BLACK);
		
		add(linksPanel, BorderLayout.SOUTH);

		
		add(optionsPanel);
		
	
		
		
		
	}
	
	// Método para dirigir al usuario a una página web
	
	public void goToURL(String URL) {
		
		// Si nos encontramos en un ordenador de escritorio, creamos una instancia Desktop
		
		if (Desktop.isDesktopSupported()) {
			
			Desktop desktop = Desktop.getDesktop();
			
			if (desktop.isSupported(Action.BROWSE)) {
		
				
				try {
					desktop.browse(new URI(URL));
				
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (URISyntaxException e) {
					
					e.printStackTrace();
				}
			}
		}
	}



	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		if (keyCode == KeyEvent.VK_ENTER) {
			
			// Jugar
			
			if (currentOption == 0) {
				
				
				gsm.endState(GameStateManager.MENU);
				
				gsm.setState(GameStateManager.LEVEL);
				
				
				
				
			}
			
			// Leer fichero y mostrar la mejor puntuación
			
			else if (currentOption == 1) {
				
				
			}
			
			// Mostrar mas información
			
			else {
				
				
			}
		}
		

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	
		
		
		int keyCode = e.getKeyCode();
			
			if (keyCode == KeyEvent.VK_UP) {
			
				
				optionLabels[currentOption].setForeground(textColor);
				
				currentOption--;
				
				if (currentOption < 0) {
					
					currentOption = options.length - 1;
				}
				
				optionLabels[currentOption].setForeground(selectedColor);
				
				
				
			}
			
			else if (keyCode == KeyEvent.VK_DOWN) {
				
				optionLabels[currentOption].setForeground(textColor);
				
				currentOption++;
				
				if (currentOption == options.length) {
					
					currentOption = 0;
				}
				
				optionLabels[currentOption].setForeground(selectedColor);
				
			}
			
		
		
		// Sobreercribimos el método paint, el cual será llamado automáticamente por Swing
		
	
		
			
	}



	@Override
	public void keyTyped(KeyEvent e) {
		
	
	}
	
	
}
