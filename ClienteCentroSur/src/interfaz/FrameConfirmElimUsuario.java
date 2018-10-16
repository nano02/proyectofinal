package interfaz;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;

public class FrameConfirmElimUsuario {

	private JFrame frmEliminarUsuario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameConfirmElimUsuario window = new FrameConfirmElimUsuario();
					window.frmEliminarUsuario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameConfirmElimUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEliminarUsuario = new JFrame();
		frmEliminarUsuario.setTitle("Eliminar usuario");
		frmEliminarUsuario.setBounds(100, 100, 390, 209);
		frmEliminarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEliminarUsuario.getContentPane().setLayout(null);
		
		JButton btnAceptarElimUsuario = new JButton("Aceptar");
		btnAceptarElimUsuario.setToolTipText("Eliminar el usuario seleccionado.");
		btnAceptarElimUsuario.setBounds(252, 103, 89, 23);
		frmEliminarUsuario.getContentPane().add(btnAceptarElimUsuario);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Volver a la anterior pantalla.");
		btnCancelar.setBounds(33, 103, 89, 23);
		frmEliminarUsuario.getContentPane().add(btnCancelar);
		
		JLabel lblNewLabel = new JLabel("\u00BFSeguro que desea eliminar al usuario seleccionado?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(23, 24, 318, 71);
		frmEliminarUsuario.getContentPane().add(lblNewLabel);
	}
}
