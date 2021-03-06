package Pantalla;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

public class Pantalla_inicio {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pantalla_inicio window = new Pantalla_inicio();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public Pantalla_inicio() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(64, 224, 208));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel PanelOpciones = new JPanel();
		PanelOpciones.setBackground(new Color(107, 142, 35));
		PanelOpciones.setBounds(10, 11, 414, 186);
		frame.getContentPane().add(PanelOpciones);
		PanelOpciones.setLayout(null);
		
		JButton btnJuego = new JButton("Juego1");
		btnJuego.setIcon(new ImageIcon("C:\\Users\\curso\\JAVA-WORKSPACE\\A\u00F1os\\src\\Img\\Bloques.png"));
		btnJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				Pantalla_Juego1.main(null);
				frame.setVisible(false);

			}
		});
		btnJuego.setBounds(10, 11, 123, 164);
		PanelOpciones.add(btnJuego);
		
		JButton btnJuego_1 = new JButton("Juego2");
		btnJuego_1.setIcon(new ImageIcon("C:\\Users\\curso\\JAVA-WORKSPACE\\A\u00F1os\\src\\Img\\Frutas.png"));
		btnJuego_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pantalla_Juego2.main(null);
				frame.setVisible(false);

			}
		});
		btnJuego_1.setBounds(143, 11, 123, 164);
		PanelOpciones.add(btnJuego_1);
		
		JButton btnJuego_3 = new JButton("Juego3");
		btnJuego_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frame, "Aun En Obras");
			}
		});
		btnJuego_3.setBounds(276, 11, 123, 164);
		PanelOpciones.add(btnJuego_3);
		
		JPanel PanelSalir = new JPanel();
		PanelSalir.setBackground(new Color(255, 215, 0));
		PanelSalir.setBounds(10, 208, 414, 42);
		frame.getContentPane().add(PanelSalir);
		PanelSalir.setLayout(null);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Aun En Obras");
			}
		});
		btnSalir.setBounds(10, 0, 123, 42);
		PanelSalir.add(btnSalir);
		
		JButton btnOpciones = new JButton("Opciones");
		btnOpciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Aun En Obras");
			}
		});
		btnOpciones.setBounds(276, 0, 123, 42);
		PanelSalir.add(btnOpciones);
	}
}
