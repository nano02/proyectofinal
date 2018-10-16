package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;

public class FrameModificarUsuario {

	private JFrame frmModificarUsuario;
	private JTextField txtApellidoUsuario;
	private JTextField txtContrasenia;
	private JTextField txtReingresoContrasenia;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameModificarUsuario window = new FrameModificarUsuario();
					window.frmModificarUsuario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameModificarUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmModificarUsuario = new JFrame();
		frmModificarUsuario.setTitle("Modificar usuario");
		frmModificarUsuario.setBounds(100, 100, 450, 307);
		frmModificarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmModificarUsuario.getContentPane().setLayout(null);
		
		txtApellidoUsuario = new JTextField();
		txtApellidoUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		txtApellidoUsuario.setToolTipText("Ingresar el apellido del usuario.");
		txtApellidoUsuario.setColumns(10);
		txtApellidoUsuario.setBounds(22, 53, 231, 20);
		frmModificarUsuario.getContentPane().add(txtApellidoUsuario);
		
		txtContrasenia = new JTextField();
		txtContrasenia.setToolTipText("Ingresar la nueva contrase\u00F1a del usuario.");
		txtContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		txtContrasenia.setColumns(10);
		txtContrasenia.setBounds(127, 135, 276, 20);
		frmModificarUsuario.getContentPane().add(txtContrasenia);
		
		txtReingresoContrasenia = new JTextField();
		txtReingresoContrasenia.setToolTipText("Reingresar la nueva contrase\u00F1a del usuario.");
		txtReingresoContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		txtReingresoContrasenia.setColumns(10);
		txtReingresoContrasenia.setBounds(127, 179, 276, 20);
		frmModificarUsuario.getContentPane().add(txtReingresoContrasenia);
		
		JButton btnAceptarModUsuario = new JButton("Aceptar");
		btnAceptarModUsuario.setToolTipText("Terminar con la modificaci\u00F3n del usuario.");
		btnAceptarModUsuario.setBounds(314, 223, 89, 23);
		frmModificarUsuario.getContentPane().add(btnAceptarModUsuario);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Volver a la anterior pantalla.");
		btnCancelar.setBounds(127, 223, 89, 23);
		frmModificarUsuario.getContentPane().add(btnCancelar);
		
		JLabel lblDescBuscadorUsuario = new JLabel("Por favor, ingrese el usuario o apellido.");
		lblDescBuscadorUsuario.setBounds(22, 28, 365, 14);
		frmModificarUsuario.getContentPane().add(lblDescBuscadorUsuario);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUsuario.setBounds(24, 92, 93, 14);
		frmModificarUsuario.getContentPane().add(lblUsuario);
		
		JLabel lblNuevaContrasenia = new JLabel("Nueva contrase\u00F1a:");
		lblNuevaContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNuevaContrasenia.setBounds(23, 138, 94, 14);
		frmModificarUsuario.getContentPane().add(lblNuevaContrasenia);
		
		JLabel lblVerificarContrasenia = new JLabel("Verificar contrase\u00F1a:");
		lblVerificarContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblVerificarContrasenia.setBounds(23, 182, 102, 14);
		frmModificarUsuario.getContentPane().add(lblVerificarContrasenia);
		
		JComboBox comboBoxUsuario = new JComboBox();
		comboBoxUsuario.setToolTipText("Seleccione el usuario a modificar.");
		comboBoxUsuario.setBounds(128, 90, 275, 20);
		frmModificarUsuario.getContentPane().add(comboBoxUsuario);
		
		JLabel lblDescContrasenia = new JLabel("Alfanum\u00E9rica, largo m\u00EDnimo 8 d\u00EDgitos y m\u00E1ximo 16.");
		lblDescContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblDescContrasenia.setBounds(128, 159, 243, 14);
		frmModificarUsuario.getContentPane().add(lblDescContrasenia);
		
		JButton btnBuscarUsuario = new JButton("Buscar usuario");
		btnBuscarUsuario.setToolTipText("Busca al usuario que se desea modificar por su usuario o apellido.");
		btnBuscarUsuario.setBounds(268, 52, 135, 23);
		frmModificarUsuario.getContentPane().add(btnBuscarUsuario);
	}
}
