package Pantalla;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Pantalla_Juego2 {

	private JFrame frame;
	private Integer i = 4;
	private Integer j = 3;
	ArrayList<String> Pistas = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pantalla_Juego2 window = new Pantalla_Juego2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Pantalla_Juego2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		String fruta=SeleccionFruta();
		Pistas = listaPistas(fruta);


		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblJuegoAhorcado = new JLabel("Juego AdivinaPalabra");
		lblJuegoAhorcado.setBounds(10, 11, 106, 14);
		frame.getContentPane().add(lblJuegoAhorcado);

		JLabel lblAdivinaLaFruta = new JLabel("Adivina la fruta que estoy pensando");
		lblAdivinaLaFruta.setBounds(120, 11, 190, 14);
		frame.getContentPane().add(lblAdivinaLaFruta);

		JEditorPane EPRespuesta = new JEditorPane();
		EPRespuesta.setBounds(10, 49, 106, 20);
		frame.getContentPane().add(EPRespuesta);

		JLabel lblRespuesta = new JLabel("Respuesta");
		lblRespuesta.setBounds(10, 36, 61, 14);
		frame.getContentPane().add(lblRespuesta);

		JLabel lblPistas = new JLabel("Pistas");
		lblPistas.setBounds(10, 80, 46, 14);
		frame.getContentPane().add(lblPistas);

		JPanel PanelDePistas = new JPanel();
		PanelDePistas.setBounds(10, 141, 414, 109);
		frame.getContentPane().add(PanelDePistas);
		PanelDePistas.setLayout(null);

		JLabel lbPista1 = new JLabel("");
		lbPista1.setBounds(10, 11, 394, 14);
		PanelDePistas.add(lbPista1);

		JLabel lbPista2 = new JLabel("");
		lbPista2.setBounds(10, 36, 394, 14);
		PanelDePistas.add(lbPista2);

		JLabel lbPista3 = new JLabel("");
		lbPista3.setBounds(10, 61, 394, 14);
		PanelDePistas.add(lbPista3);

		JLabel lbPista4 = new JLabel("");
		lbPista4.setEnabled(false);
		lbPista4.setBounds(10, 84, 404, 14);
		PanelDePistas.add(lbPista4);

		JPanel PanelInformacion = new JPanel();
		PanelInformacion.setBounds(305, 11, 119, 122);
		frame.getContentPane().add(PanelInformacion);
		PanelInformacion.setLayout(null);

		JLabel lblNewLabel = new JLabel("Informacion");
		lblNewLabel.setBounds(10, 0, 99, 14);
		PanelInformacion.add(lblNewLabel);

		JLabel lblOportunidades = new JLabel("Oportunidades");
		lblOportunidades.setBounds(10, 25, 71, 14);
		PanelInformacion.add(lblOportunidades);

		JLabel lbNOportunidades = new JLabel("N");
		lbNOportunidades.setBounds(92, 25, 17, 14);
		PanelInformacion.add(lbNOportunidades);

		JLabel lblPistas_1 = new JLabel("Pistas");
		lblPistas_1.setBounds(10, 43, 71, 14);
		PanelInformacion.add(lblPistas_1);

		JLabel Npistas = new JLabel("N");
		Npistas.setBounds(92, 43, 17, 14);
		PanelInformacion.add(Npistas);

		JButton btnSLR = new JButton("Se la respuesta");
		btnSLR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String respuesta = EPRespuesta.getText().toLowerCase();

				if (!respuesta.equals(fruta)) {
					j--;
					lbNOportunidades.setText((j).toString());

					if (j < 0) {
						JOptionPane.showMessageDialog(frame, "Perdiste");
						frame.dispose();
						Pantalla_inicio.main(null);

					}

				} else {
					JOptionPane.showMessageDialog(frame, "Ganaste, felicidades.");
					frame.dispose();
					Pantalla_inicio.main(null);
					
				
					
				}

			}
		});
		btnSLR.setBounds(126, 46, 112, 23);
		frame.getContentPane().add(btnSLR);

		JButton btnNewButton = new JButton("Solicitar pista");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				switch (i) {
				case 3:
					lbPista2.setText(Pistas.get(i - 1));
					Npistas.setText("2");

					break;
				case 2:
					lbPista3.setText(Pistas.get(i - 1));
					Npistas.setText("1");
					break;
				case 1:
					lbPista4.setText(Pistas.get(i - 1));
					Npistas.setText("0");
					break;
				}
				i--;

			}
		});
		btnNewButton.setBounds(10, 110, 106, 23);
		frame.getContentPane().add(btnNewButton);
		
		//Estado inicial

		Npistas.setText("3");
		--i;
		lbPista1.setText(Pistas.get(i));
		lbNOportunidades.setText("3");

	}
	public String SeleccionFruta() {
		ArrayList<String> frutas = new ArrayList<String>();
		frutas.add("piña");
		frutas.add("papaya");
		frutas.add("mora");
		frutas.add("granada");
		frutas.add("manzana");
		frutas.add("tomate");
		String fruta= frutas.get((int) (Math.random() * 6));
		return fruta;
	}
	
	public ArrayList<String> listaPistas(String a){
		ArrayList<String> pistaspiña = new ArrayList<String>();
		pistaspiña.add("Tiene 4 letras");
		pistaspiña.add("Comienza con P");
		pistaspiña.add("Es Dulce");
		pistaspiña.add("Su pulpa es amarilla");

		ArrayList<String> pistaspapaya = new ArrayList<String>();
		pistaspapaya.add("Tiene 6 letras");
		pistaspapaya.add("Comienza con P");
		pistaspapaya.add("Es Dulce");
		pistaspapaya.add("Su pulpa es amarilla");

		ArrayList<String> pistasmora = new ArrayList<String>();
		pistasmora.add("Tiene 4 letras");
		pistasmora.add("Comienza con M");
		pistasmora.add("Es Dulce");
		pistasmora.add("Su pulpa es Morada");
		
		ArrayList<String> pistasgranada = new ArrayList<String>();
		pistasgranada.add("Tiene 7 letras");
		pistasgranada.add("Comienza con G");
		pistasgranada.add("Es Dulce");
		pistasgranada.add("Su pulpa es roja y en cositos raros y peque�os");
		
		ArrayList<String> pistasmanzana = new ArrayList<String>();
		pistasmanzana.add("Tiene 7 letras");
		pistasmanzana.add("Comienza con M");
		pistasmanzana.add("Es Dulce");
		pistasmanzana.add("Su pulpa es blanca y cascara roja o verde");
		
		ArrayList<String> pistastomate = new ArrayList<String>();
		pistastomate.add("Tiene 6 letras");
		pistastomate.add("Comienza con T");
		pistastomate.add("Es Dulce");
		pistastomate.add("Su pulpa es roja");

		switch (a) {
		case "piña":
			Pistas = pistaspiña;
			break;
		case "papaya":
			Pistas = pistaspapaya;
			break;
		case "mora":
			Pistas = pistasmora;
			break;
		case "granada":
			Pistas = pistasgranada;
			break;
		case "manzana":
			Pistas = pistasmanzana;
			break;
		case "tomate":
			Pistas = pistastomate;
			break;
		}
		return Pistas;
		
	}

}
