package Pantalla;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Pantalla_juego3 {
	int i = 0, j = 0, k = 0;
	ArrayList<Integer> opciones = new ArrayList<Integer>();

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pantalla_juego3 window = new Pantalla_juego3();
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
	public Pantalla_juego3() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panelMostrar = new JPanel();
		panelMostrar.setBounds(0, 128, 434, 133);
		frame.getContentPane().add(panelMostrar);
		panelMostrar.setLayout(null);

		JLabel TextoAMostrar = new JLabel("");
		TextoAMostrar.setBounds(0, 0, 434, 133);
		panelMostrar.add(TextoAMostrar);

		JPanel PanelDeDatos = new JPanel();
		PanelDeDatos.setBounds(140, 0, 294, 129);
		frame.getContentPane().add(PanelDeDatos);

		JPanel panelOpciones = new JPanel();
		panelOpciones.setBounds(0, 0, 142, 129);
		frame.getContentPane().add(panelOpciones);
		panelOpciones.setLayout(null);

		JCheckBox chckbxHoroscopoAlq = new JCheckBox("Alquimista");
		chckbxHoroscopoAlq.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				metodoAgregarYQuitar(1,i);
				if (i==0) {i++;}
				else {i=0;}
				metodoClick(TextoAMostrar);

			}
		});
		chckbxHoroscopoAlq.setBounds(6, 32, 97, 23);
		panelOpciones.add(chckbxHoroscopoAlq);

		JCheckBox chckbxArabe = new JCheckBox("Arabe");
		chckbxArabe.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				metodoAgregarYQuitar(2,j);
				if (j==0) {j++;}
				else {j=0;}
				metodoClick(TextoAMostrar);
			}
		});
		chckbxArabe.setBounds(6, 53, 97, 23);
		panelOpciones.add(chckbxArabe);

		JCheckBox chckbxMaya = new JCheckBox("Maya");
		chckbxMaya.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				metodoAgregarYQuitar(3,k);
				if (k==0) {k++;}
				else {k=0;}
				metodoClick(TextoAMostrar);
			}
		});
		chckbxMaya.setBounds(6, 75, 97, 23);
		panelOpciones.add(chckbxMaya);

		JLabel lblSeleccionaLosHoroscopos = new JLabel("Selecciona los horoscopos");
		lblSeleccionaLosHoroscopos.setBounds(6, 11, 221, 14);
		panelOpciones.add(lblSeleccionaLosHoroscopos);

		JLabel lblQueQuieresVer = new JLabel("que quieres ver");
		lblQueQuieresVer.setBounds(6, 21, 221, 14);
		panelOpciones.add(lblQueQuieresVer);
	}

	protected void metodoAgregarYQuitar(int l, int h) {
		if (h == 0) {
			opciones.add(l);
			opciones.sort(null);
		} else {
			int f = 0;
			while (f != opciones.size()) {
				if (opciones.get(f).equals(l)) {
					opciones.remove(f);
				}
			}
		}

	}

	protected void metodoClick(JLabel textoAMostrar) {
		textoAMostrar.setText(opciones.toString());
		
		
		
		
	}

}
