package Pantalla;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import entidad.Ladrillo;
import entidad.Punto;

public class Pantalla_Juego1 {
	static ArrayList<Ladrillo> objetoLadrillosPantalla = new ArrayList<Ladrillo>();
	static ArrayList<JButton> ladrillobotones = new ArrayList<JButton>();
	Punto p = new Punto();
	int puntuacion = -220;

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
		JPanel panel = initialize();
		Integer i = 15;
		generarLadrillos(panel);
		while (i != 0) {
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
		Personaje.setBounds(210, 219, 10, 10);
		panel.add(Personaje);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(331, 0, 103, 19);
		panel.add(panel_2);

		JLabel lblPuntuacion = new JLabel("Puntuacion");
		panel_2.add(lblPuntuacion);
		lblPuntuacion.setBackground(new Color(255, 255, 255));
		
				JLabel lblNewLabel = new JLabel("0");
				panel_2.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 255, 0));
		panel_1.setBounds(0, 240, 434, 21);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JButton btnNewButton_1 = new JButton("Empezar(usa d o s para mover)");
		btnNewButton_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_D) {
					if(p.getPosicionX()>415){}
					else{
					p.setPosicionX(p.getPosicionX() + 10);}
				}
				if (e.getKeyCode() == KeyEvent.VK_A) {
					if(p.getPosicionX()<0){}
					else{
					p.setPosicionX(p.getPosicionX() - 10);}
				}

				Personaje.setBounds(p.getPosicionX(), 219, 10, 10);
				bajarladrillo(objetoLadrillosPantalla, ladrillobotones);
				generarLadrillos(panel);
				generarLadrillos(panel);
				generarLadrillos(panel);
				generarLadrillos(panel);
				comprobarMuerte(p, objetoLadrillosPantalla, ladrillobotones);
				metodoPuntuacion(lblNewLabel,panel);
				
			}
		});
		btnNewButton_1.setBounds(0, 0, 434, 23);
		panel_1.add(btnNewButton_1);
		return panel;
	}

	protected void metodoPuntuacion(JLabel lblNewLabel,JPanel panel) {
		puntuacion = puntuacion + 10;
		if (puntuacion < 0) {
			lblNewLabel.setText(String.valueOf(0));
		} else {
			lblNewLabel.setText(String.valueOf(puntuacion));}
		if (puntuacion>1000 && puntuacion<1400) {
			panel.setBackground(new Color(59, 147, 225));
		}
		if (puntuacion>2000 && puntuacion<2400) {
			panel.setBackground(new Color(145, 202, 222));
		}
		if (puntuacion>3000 && puntuacion<3400) {
			panel.setBackground(new Color(239, 127, 26));	
		}
		if (puntuacion>4000 && puntuacion<4400) {
			panel.setBackground(new Color(13, 12, 70));	
		}
		if (puntuacion>5000 && puntuacion<5400) {
			panel.setBackground(new Color(0, 0, 0));	
		}
		
	}

	protected void comprobarMuerte(Punto p, ArrayList<Ladrillo> ladrillos, ArrayList<JButton> ladrillobotones2) {
		Integer posicionIz = p.getPosicionX(), posicionDe = p.getPosicionX() + 10;
		Integer posicionLaIz, posicionLaDe;
		int iteracion = 0, numeroLadrillos = ladrillos.size();

		while (numeroLadrillos != iteracion) {
			Ladrillo lad = ladrillos.get(iteracion);
			if (lad.getPosiciony() > 219 && lad.getPosiciony() < 229) {
				posicionLaIz = lad.getPosicionx();
				posicionLaDe = lad.getPosicionx() + lad.getLargo();
				if (posicionIz < posicionLaDe && posicionDe > posicionLaIz) {
					if (lad.getTipo().equals(0)) {
						JOptionPane.showMessageDialog(frame, "Perdiste");
						reiniciar(objetoLadrillosPantalla, ladrillobotones, iteracion);
						frame.dispose();
						Pantalla_inicio.main(null);
						break;
					}
				}
				if (posicionIz < posicionLaDe && posicionDe > posicionLaIz) {
					if (lad.getTipo().equals(1)) {
						puntuacion = puntuacion + 300;
					}
				}

			}
			iteracion++;
		}

	}

	private void reiniciar(ArrayList<Ladrillo> ladrillos, ArrayList<JButton> ladrillobotones2, int iteracion) {
		ladrillos.clear();
		ladrillobotones2.clear();
		iteracion = 0;
	}

	private void bajarladrillo(ArrayList<Ladrillo> ladrillosPantalla, ArrayList<JButton> ladrillobotones) {
		int j = 0, lista = ladrillosPantalla.size();
		do {
			Ladrillo lO = ladrillosPantalla.get(j);
			lO.setPosiciony(lO.getPosiciony() + 10);

			JButton LP = ladrillobotones.get(j);
			LP.setBounds(lO.getPosicionx(), lO.getPosiciony(), lO.getLargo(), lO.getLargo());

			j++;

		} while (j != lista);

		frame.repaint();

	}

	private void generarLadrillos(JPanel panel) {
		Integer posibilidad = (int) (Math.random() * 20);
		Ladrillo l = null;

		if (posibilidad > 0) {
			Integer posicionInicial = (int) (Math.random() * 43) * 10;

			l = new Ladrillo(10, posicionInicial, 0);
			l.setTipo(0);
			JButton ladrillo = new JButton("");
			ladrillo.setBounds(l.getPosicionx(), 0, l.getLargo(), l.getLargo());
			ladrillo.setBackground(new Color(225, 0, 0));
			ladrillo.setVisible(true);
			panel.add(ladrillo);

			objetoLadrillosPantalla.add(l);
			ladrillobotones.add(ladrillo);
		}
		if (posibilidad == 0) {
			Integer posicionInicial = (int) (Math.random() * 43) * 10;

			l = new Ladrillo(10, posicionInicial, 0);
			l.setTipo(1);
			JButton ladrillo = new JButton("");
			ladrillo.setBounds(l.getPosicionx(), 0, l.getLargo(), l.getLargo());
			ladrillo.setBackground(new Color(0, 255, 0));
			ladrillo.setVisible(true);
			panel.add(ladrillo);

			objetoLadrillosPantalla.add(l);
			ladrillobotones.add(ladrillo);
		}
	}
}
