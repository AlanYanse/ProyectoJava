package eljuego;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import java.awt.Font;

import javax.swing.*;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PantallaSplash mi_splash = new PantallaSplash();
		mi_splash.setVisible(true);
		
		
	}

}

class Juego extends JFrame{
	
	private JPanel panel_fondito, regalo_panel, duendes_panel, panel_info;
	private JLabel imagen_fondo, imagen_manuf, imagen_comi, imagen_trineo, counter_label, coin_label;
	private JLabel count_manuf_label, count_comi_label, count_trineo_label, info_manu, info_comi, info_trineo;
	private JLabel veloc_label, carga_label;
	private JButton regalito, entregar, contra_manu, contra_comi, compra_trineo;
	private int counter_regalito, coin, cap_carga, count_manuf, count_comi, count_trineo, timer_speed;
	private double vel_produc;
	private int precio_manuf, precio_comi, precio_trineo;
	private boolean interruptor = false;
	private boolean timer_on = false;
	private JProgressBar barra_progres;
	RegaloHandler rHandler = new RegaloHandler();
	Thread t;
	Timer timer;
	
	public Juego() {
		
		counter_regalito = 0;
		coin = 0;
		cap_carga = 50;
		count_manuf = 0;
		count_comi = 0;
		count_trineo = 0;
		precio_manuf = 5;
		precio_comi = 15;
		precio_trineo = 100;
		
		this.setTitle("Espíritu Navideño");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		inicCompPanelFondo();
		inicPanelesFront();
		inicPanelInfo();		
		
		this.add(panel_info);
		this.add(duendes_panel);
		this.add(regalo_panel);
		this.add(panel_fondito);
		
	}
	
	private void inicCompPanelFondo() {
		
		panel_fondito = new JPanel();
		panel_fondito.setLayout(null);
		
		imagen_fondo = new JLabel(new ImageIcon("fondo.png"));
		imagen_fondo.setLayout(null);
		imagen_fondo.setBounds(1, 1, 800, 600);
		panel_fondito.add(imagen_fondo);
		
	}
	
	private void inicPanelesFront() {
		
		regalo_panel = new JPanel();
		regalo_panel.setLayout(null);
		regalo_panel.setBounds(50, 120, 220, 350);
		regalo_panel.setOpaque(false);
		
		duendes_panel = new JPanel();
		duendes_panel.setLayout(null);
		duendes_panel.setBounds(320, 150, 420, 380);
	    duendes_panel.setOpaque(false);
		
		imagen_manuf = new JLabel(new ImageIcon("duendecillo manufacturero2.png"));
		imagen_manuf.setLayout(null);
		imagen_manuf.setBounds(1, 1, 75, 75);
		duendes_panel.add(imagen_manuf);
		
		imagen_comi = new JLabel(new ImageIcon("duendecillo comisionista.png"));
		imagen_comi.setLayout(null);
		imagen_comi.setBounds(1, 100, 75, 75);
		duendes_panel.add(imagen_comi);
		
		imagen_trineo = new JLabel(new ImageIcon("trineo.png"));
		imagen_trineo.setLayout(null);
		imagen_trineo.setBounds(1, 200, 75, 75);
		duendes_panel.add(imagen_trineo);
		
		count_manuf_label = new JLabel(count_manuf + "");
		count_manuf_label.setLayout(null);
		count_manuf_label.setOpaque(false);
		count_manuf_label.setBounds(90, 1, 75, 75);
		count_manuf_label.setBackground(Color.WHITE);
		duendes_panel.add(count_manuf_label);
		
		count_comi_label = new JLabel(count_comi + "");
		count_comi_label.setLayout(null);
		count_comi_label.setOpaque(false);
		count_comi_label.setBounds(90, 100, 75, 75);
		count_comi_label.setBackground(Color.WHITE);
		duendes_panel.add(count_comi_label);
		
		count_trineo_label = new JLabel(count_trineo + "");
		count_trineo_label.setLayout(null);
		count_trineo_label.setOpaque(false);
		count_trineo_label.setBounds(90, 200, 75, 75);
		count_trineo_label.setBackground(Color.WHITE);
		duendes_panel.add(count_trineo_label);
		
		info_manu = new JLabel();
		info_manu.setLayout(null);
		info_manu.setText("<html><p>DUENDECILLOS</p><p>MANUFACTUREROS</p><p>Aumentan la</p><p>Velocidad de</p><p>producción</p></html>");
		info_manu.setOpaque(false);
		info_manu.setBounds(178, 1, 120, 75);
		info_manu.setBackground(Color.WHITE);
		duendes_panel.add(info_manu);
		
		
		info_comi = new JLabel();
		info_comi.setLayout(null);
		info_comi.setText("<html><p>DUENDECILLOS</p><p>COMISIONISTA</p><p>Aumentan la</p><p>Capacidad de</p><p>carga</p></html>");
		info_comi.setOpaque(false);
		info_comi.setBounds(178, 100, 120, 75);
		info_comi.setBackground(Color.WHITE);
		duendes_panel.add(info_comi);
		
		info_trineo = new JLabel();
		info_trineo.setLayout(null);
		info_trineo.setText("<html><p>TRINEO</p><p>Aumenta la</p><p>Capacidad de</p><p>carga</p><p>aún más</p></html>");
		info_trineo.setOpaque(false);
		info_trineo.setBounds(178, 200, 120, 75);
		info_trineo.setBackground(Color.WHITE);
		duendes_panel.add(info_trineo);
		
			
		regalito = new JButton(new ImageIcon("regalito1.png"));
		regalito.setBounds(60, 60, 100, 100);
		regalito.addActionListener(rHandler);
		regalito.setActionCommand("regalito");
		regalo_panel.add(regalito);
		
		contra_manu = new JButton();
		contra_manu.setText("<html><p>PRECIO</p><p align=center>" + precio_manuf + "</p></html>");
		contra_manu.setBounds(310, 1, 80, 75);
		contra_manu.addActionListener(rHandler);
		contra_manu.setActionCommand("manuf");
		contra_manu.setEnabled(false);
		duendes_panel.add(contra_manu);
		
		contra_comi = new JButton("comi");
		contra_comi.setText("<html><p>PRECIO</p><p align=center>" + precio_comi + "</p></html>");
		contra_comi.setBounds(310, 100, 80, 75);
		contra_comi.addActionListener(rHandler);
		contra_comi.setActionCommand("comi");
		contra_comi.setEnabled(false);
		duendes_panel.add(contra_comi);
		
		compra_trineo = new JButton("trineo");
		compra_trineo.setText("<html><p>PRECIO</p><p align=center>" + precio_trineo + "</p></html>");
		compra_trineo.setBounds(310, 200, 80, 75);
		compra_trineo.addActionListener(rHandler);
		compra_trineo.setActionCommand("trineo");
		compra_trineo.setEnabled(false);
		duendes_panel.add(compra_trineo);
		
		entregar = new JButton("Entregar");
		entregar.setBounds(60, 195, 100, 50);
		entregar.addActionListener(rHandler);
		entregar.setActionCommand("entregar");
		entregar.setEnabled(false);
		regalo_panel.add(entregar);
		
		counter_label = new JLabel(counter_regalito + "");
		counter_label.setBounds(100, 1, 150, 50);
		counter_label.setFont(new Font("Arial Black", Font.PLAIN, 18));
		counter_label.setForeground(Color.BLACK);
		regalo_panel.add(counter_label);
		
				
		barra_progres = new JProgressBar(0, 10); // Seteo del rango, cambiar a 0,60 para que sean 5 min de espera.
		barra_progres.setBounds(35, 280, 150, 25);
		regalo_panel.add(barra_progres);
		
	}
	
	private void inicPanelInfo() {
		
		panel_info = new JPanel();
		panel_info.setLayout(null);
		panel_info.setBounds(0, 475, 800, 150);
		panel_info.setBackground(Color.WHITE);
		
		coin_label = new JLabel(coin + "");
		coin_label.setBounds(700, 20, 150, 50);
		coin_label.setFont(new Font("Arial Black", Font.PLAIN, 18));
		coin_label.setForeground(Color.GREEN);
		panel_info.add(coin_label);
		
		carga_label = new JLabel("<html><p>Cap. Carga</p><p align=center>" + cap_carga + "</p></html>");
		carga_label.setBounds(355, 1, 150, 80);
		carga_label.setFont(new Font("Arial", Font.PLAIN, 16));
		carga_label.setForeground(Color.BLACK);
		panel_info.add(carga_label);
		
		veloc_label = new JLabel("<html><p>Velc. Product</p><p align=center>" + vel_produc + "  R/seg</p></html>");
		veloc_label.setBounds(115, 1, 150, 80);
		veloc_label.setFont(new Font("Arial", Font.PLAIN, 16));
		veloc_label.setForeground(Color.BLACK);
		panel_info.add(veloc_label);
		
		
		
	}
	
	public class RegaloHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String accion = e.getActionCommand(); // esto va a comandar cuando pulse el botón
			
			switch (accion) {
			
			case "regalito":
				
				if (interruptor==false) {
					
					counter_regalito ++;
					counter_label.setText(counter_regalito + "");
					
					if (counter_regalito>=cap_carga) entregar.setEnabled(true);
					
				}
												
				break;
				
			case "entregar":
				
				if (counter_regalito>=50) {
					
					if (interruptor == false) {
						
						interruptor = true;
						
						counter_regalito = counter_regalito - cap_carga;
						counter_label.setText(counter_regalito + "");
						iniciarEntrega();
					}
					
									
				}
				break;
				
			case "manuf":
				
				if (coin >= precio_manuf) {
					
					coin = coin - precio_manuf;
					precio_manuf = precio_manuf + 5;
					coin_label.setText(coin + "");
					contra_manu.setText("<html><p>PRECIO</p><p align=center>" + precio_manuf + "</p></html>");
										
					count_manuf ++;
					count_manuf_label.setText(count_manuf + "");
					vel_produc = vel_produc + 0.1;
					String s = String.format("%.1f", vel_produc);
					veloc_label.setText("<html><p>Velc. Product</p><p align=center>" + s + " R/seg</p></html>");
					
										
					timerUpdate();
				}
				if (coin < precio_manuf) contra_manu.setEnabled(false);
				if (coin < precio_comi)  contra_comi.setEnabled(false);
				if (coin < precio_trineo) compra_trineo.setEnabled(false);
				
				break;
				
			 case "comi":
				 
				 if(coin >= precio_comi) {
					 
					 coin = coin - precio_comi;
					 precio_comi = precio_comi + 5;
					 coin_label.setText(coin + "");
					 contra_comi.setText("<html><p>PRECIO</p><p align=center>" + precio_comi + "</p></html>");
					 
					 count_comi++;
					 count_comi_label.setText(count_comi + "");
					 cap_carga += 10;
					 carga_label.setText("<html><p>Cap. Carga</p><p align=center>" + cap_carga + "</p></html>");
				 }
				 if (coin < precio_comi) contra_comi.setEnabled(false);
				 if (coin < precio_manuf) contra_manu.setEnabled(false);
				 if (coin < precio_trineo) compra_trineo.setEnabled(false);
				 
				 break;
				 
			  case	"trineo":
				  
				  if (coin >= precio_trineo) {
					  
					  coin = coin - precio_trineo;
					  precio_trineo = precio_trineo + 100;
					  coin_label.setText(coin + "");
					  compra_trineo.setText("<html><p>PRECIO</p><p align=center>" + precio_trineo + "</p></html>");
					  
					  count_trineo++;
					  count_trineo_label.setText(count_trineo + "");
					  cap_carga += 100;
					  carga_label.setText("<html><p>Cap. Carga</p><p align=center>" + cap_carga + "</p></html>");
				  }
				  
				  if (coin < precio_trineo) compra_trineo.setEnabled(false);
				  if (coin < precio_comi) contra_comi.setEnabled(false);
			      if (coin < precio_manuf) contra_manu.setEnabled(false);
			      
			      break;
		
			}
			
	}
		
		
}
	
	public void iniciarEntrega() {
		
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				barra_progres.setStringPainted(true);
				
				int x = 0;
				
				if (counter_regalito<cap_carga) entregar.setEnabled(false);
				else entregar.setEnabled(true);
				
				
				
				while(x<=10) { // cambiar a 300 para que se haga  5min de espera.
					
					barra_progres.setValue(x);
					
					try {
						
						Thread.sleep(1000);
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
					x++;
					
				}
				
				if(interruptor) {
					
										
					coin = coin + cap_carga/5;
					coin_label.setText(coin + "");
					barra_progres.setValue(0);
					interruptor = false;
					
					
				}
				
				if(coin >= precio_manuf) contra_manu.setEnabled(true);
				
				if(coin >= precio_comi) contra_comi.setEnabled(true);
				
				if(coin >= precio_trineo) compra_trineo.setEnabled(true);
			}
			
		});
			
		t.start();
	}
	
	public void setTimer() {
		
		timer = new Timer(timer_speed, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
																
				counter_regalito ++;
				counter_label.setText(counter_regalito + "");
				
				if(counter_regalito >= cap_carga) entregar.setEnabled(true);				
			}
			
		});
	}
	
	public void timerUpdate() {
		
		if (timer_on == false) timer_on = true;
		else if (timer_on == true) timer.stop();
		
		double speed = 1/vel_produc*1000;
		timer_speed =(int) Math.round(speed);
		
		setTimer();
		timer.start();
	}

}
