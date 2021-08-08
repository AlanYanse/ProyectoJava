package eljuego;
import javax.swing.*;

public class PantallaSplash extends JDialog {
	
	private JLabel imagen_splash, porcentaje;
	private JProgressBar bar_splash;
	private JPanel panel_splash;
	private Thread t_splash;
	
	public PantallaSplash() {
		
		
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		
		
		iniciandoComp();
		iniciarHilo();
		
		this.add(panel_splash);
		
	}
	
	private void iniciandoComp() {
		
		panel_splash = new JPanel();
		panel_splash.setLayout(null);
		panel_splash.setBounds(0, 0, 800, 600);
		
		imagen_splash = new JLabel(new ImageIcon ("pantalla splash.png"));
		imagen_splash.setBounds(0, 0, 800, 600);
		panel_splash.add(imagen_splash);
		
		bar_splash = new JProgressBar(0, 100);
		bar_splash.setBounds(325, 400, 150, 10);
		panel_splash.add(bar_splash);
	}
	
	private void iniciarHilo() {
		
		t_splash = new Thread(new Runnable() {
			
			
			int x = 0;

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				try {
					while (x<=100) {
						
						bar_splash.setValue(x);
						x++;
						
						Thread.sleep(100);
					}
					
					dispose();
					
					Juego espirituNavideño = new Juego();
					espirituNavideño.setVisible(true);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			
		});
		
		t_splash.start();
		
	}

}
