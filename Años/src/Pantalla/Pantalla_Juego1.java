package Pantalla;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import entidad.Ladrillo;
import entidad.Punto;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Pantalla_Juego1 {
	static ArrayList<Ladrillo> objetoLadrillosPantalla =new ArrayList<Ladrillo>();
	static ArrayList<JButton> ladrillobotones = new ArrayList<JButton>();
	Punto p = new Punto();
	int puntuacion=0;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pantalla_Juego1 window = new Pantalla_Juego1();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Pantalla_Juego1() {
		JPanel panel= initialize();
		Integer i=15;
		while(i!=0) {
			generarLadrillos(panel);
			i--;
		}
				

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private JPanel initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GREEN);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		panel.setBounds(0, 0, 434, 229);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JButton Personaje = new JButton("");
		Personaje.setForeground(new Color(0, 0, 255));
		Personaje.setBackground(new Color(0, 0, 205));
		Personaje.setBounds(p.getPosicionX(), 219, 10, 10);
		panel.add(Personaje);
		
		JLabel lblNewLabel = new JLabel("0");
		lblNewLabel.setBounds(401, 0, 23, 14);
		panel.add(lblNewLabel);
		
		JLabel lblPuntuacion = new JLabel("Puntuacion");
		lblPuntuacion.setBounds(330, 0, 61, 14);
		panel.add(lblPuntuacion);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 255, 0));
		panel_1.setBounds(0, 240, 434, 21);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton btnNewButton = new JButton("<---");
		btnNewButton.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==e.VK_D) {
					p.setPosicionX(p.getPosicionX() + 10);
					Personaje.setBounds(p.getPosicionX(), 219, 10, 10);
					bajarladrillo(objetoLadrillosPantalla, ladrillobotones);
					generarLadrillos(panel);
					generarLadrillos(panel);
					generarLadrillos(panel);
					generarLadrillos(panel);
					comprobarMuerte(p, objetoLadrillosPantalla);
					puntuacion=puntuacion+10;
					lblNewLabel.setText(String.valueOf(puntuacion));
					
				}
				if(e.getKeyCode()==e.VK_A) {
					p.setPosicionX(p.getPosicionX() - 10);
					Personaje.setBounds(p.getPosicionX(), 219, 10, 10);
					bajarladrillo(objetoLadrillosPantalla, ladrillobotones);
					generarLadrillos(panel);
					generarLadrillos(panel);
					generarLadrillos(panel);
					generarLadrillos(panel);
					comprobarMuerte(p, objetoLadrillosPantalla);
					puntuacion=puntuacion+10;
					lblNewLabel.setText(String.valueOf(puntuacion));
					
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setPosicionX(p.getPosicionX() - 10);
				Personaje.setBounds(p.getPosicionX(), 219, 10, 10);
				bajarladrillo(objetoLadrillosPantalla, ladrillobotones);
				generarLadrillos(panel);
				generarLadrillos(panel);
				generarLadrillos(panel);
				generarLadrillos(panel);
				comprobarMuerte(p, objetoLadrillosPantalla);
				puntuacion=puntuacion+10;
				lblNewLabel.setText(String.valueOf(puntuacion));
			}
		});
		btnNewButton.setBounds(0, 0, 227, 23);
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("-->");
		btnNewButton_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==e.VK_D) {
					p.setPosicionX(p.getPosicionX() + 10);
					Personaje.setBounds(p.getPosicionX(), 219, 10, 10);
					bajarladrillo(objetoLadrillosPantalla, ladrillobotones);
					generarLadrillos(panel);
					generarLadrillos(panel);
					generarLadrillos(panel);
					generarLadrillos(panel);
					comprobarMuerte(p, objetoLadrillosPantalla);
					puntuacion=puntuacion+10;
					lblNewLabel.setText(String.valueOf(puntuacion));
					
				}
				if(e.getKeyCode()==e.VK_A) {
					p.setPosicionX(p.getPosicionX() - 10);
					Personaje.setBounds(p.getPosicionX(), 219, 10, 10);
					bajarladrillo(objetoLadrillosPantalla, ladrillobotones);
					generarLadrillos(panel);
					generarLadrillos(panel);
					generarLadrillos(panel);
					generarLadrillos(panel);
					comprobarMuerte(p, objetoLadrillosPantalla);
					puntuacion=puntuacion+10;
					lblNewLabel.setText(String.valueOf(puntuacion));
					
				}
			}
		});
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setPosicionX(p.getPosicionX() + 10);
				Personaje.setBounds(p.getPosicionX(), 219, 10, 10);
				bajarladrillo(objetoLadrillosPantalla, ladrillobotones);
				generarLadrillos(panel);
				generarLadrillos(panel);
				generarLadrillos(panel);
				generarLadrillos(panel);
				comprobarMuerte(p, objetoLadrillosPantalla);
				puntuacion=puntuacion+10;
				lblNewLabel.setText(String.valueOf(puntuacion));

			}
		});
		btnNewButton_1.setBounds(237, 0, 197, 23);
		panel_1.add(btnNewButton_1);
		return panel;
	}

	protected void comprobarMuerte(Punto p, ArrayList<Ladrillo> ladrillos) {
		Integer posicionIz=p.getPosicionX(), posicionDe=p.getPosicionX()+10;
		Integer posicionLaIz, posicionLaDe;
		int iteracion=0, numeroLadrillos=ladrillos.size();
		
		while(numeroLadrillos!=iteracion) {
			Ladrillo lad = ladrillos.get(iteracion);
			if (lad.getPosiciony()>219 &&  lad.getPosiciony()<229) {
				posicionLaIz=lad.getPosicionx();
				posicionLaDe=lad.getPosicionx()+lad.getLargo();
				if(posicionIz<posicionLaDe && posicionDe>posicionLaIz)
				{
					JOptionPane.showMessageDialog(frame, "Perdiste");
					frame.dispose();
					Pantalla_inicio.main(null);
				}
				
			}
			iteracion++;
		}
		
		
		
		
		
		
		
	}

	private void bajarladrillo(ArrayList<Ladrillo> ladrillosPantalla, ArrayList<JButton> ladrillobotones) {
		int j = 0, lista=ladrillosPantalla.size();
		do {
			Ladrillo lO = ladrillosPantalla.get(j);
			lO.setPosiciony(lO.getPosiciony() +10);
			
			JButton LP = ladrillobotones.get(j);
			LP.setBounds(lO.getPosicionx(), lO.getPosiciony(), lO.getLargo(), lO.getLargo());
			
			j++;
			
		}
		while(j!=lista);
		
		
		frame.repaint();
		
	}
	private void generarLadrillos(JPanel panel) {
		Integer posibilidad = (int) Math.random() * 3;
		Ladrillo l=null;

		if (posibilidad > -1) {
			Integer posicionInicial = (int) (Math.random() * 42)*10;

			l = new Ladrillo(10, posicionInicial, 0);

			JButton ladrillo = new JButton("");
			ladrillo.setBounds(l.getPosicionx(), 0, l.getLargo(), l.getLargo());
			ladrillo.setBackground(new Color(225, 0, 0));
			ladrillo.setVisible(true);
			panel.add(ladrillo);
			
			objetoLadrillosPantalla.add(l);
			ladrillobotones.add(ladrillo);
		}
	}
}
