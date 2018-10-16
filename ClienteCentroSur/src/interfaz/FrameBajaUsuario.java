package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.DAOs.DAOUsuario;
import com.models.Usuario;

import javax.swing.JComboBox;
import javax.ejb.EJB;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameBajaUsuario {

	private JFrame frmEliminarUsuario;
	private JTextField txtApellidoUsuario;

	@EJB DAOUsuario daoUsuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameBajaUsuario window = new FrameBajaUsuario();
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
	public FrameBajaUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEliminarUsuario = new JFrame();
		frmEliminarUsuario.setIconImage(Toolkit.getDefaultToolkit().getImage(FrameBajaUsuario.class.getResource("/pic/logoCS.jpeg")));
		frmEliminarUsuario.setResizable(false);
		frmEliminarUsuario.setTitle("Eliminar usuario");
		frmEliminarUsuario.setBounds(100, 100, 428, 227);
		frmEliminarUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEliminarUsuario.getContentPane().setLayout(null);
		
		txtApellidoUsuario = new JTextField();
		txtApellidoUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		txtApellidoUsuario.setToolTipText("Ingresar el apellido del usuario.");
		txtApellidoUsuario.setColumns(10);
		txtApellidoUsuario.setBounds(22, 53, 231, 20);
		frmEliminarUsuario.getContentPane().add(txtApellidoUsuario);
		
		JButton btnAceptarElimUsuario = new JButton("Aceptar");
		btnAceptarElimUsuario.setToolTipText("Eliminar el usuario seleccionado.");
		btnAceptarElimUsuario.setBounds(310, 140, 89, 23);
		frmEliminarUsuario.getContentPane().add(btnAceptarElimUsuario);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setToolTipText("Volver a la anterior pantalla.");
		btnCancelar.setBounds(130, 140, 89, 23);
		frmEliminarUsuario.getContentPane().add(btnCancelar);
		
		JLabel lblDescBuscadorUsuario = new JLabel("Por favor, ingrese el usuario o apellido.");
		lblDescBuscadorUsuario.setBounds(22, 28, 377, 14);
		frmEliminarUsuario.getContentPane().add(lblDescBuscadorUsuario);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(24, 92, 94, 14);
		frmEliminarUsuario.getContentPane().add(lblUsuario);
		
		JComboBox<String> cboxUsuario = new JComboBox<String>();
		cboxUsuario.setToolTipText("Seleccionar el usuario a eliminar.");
		cboxUsuario.setBounds(128, 90, 271, 20);
		frmEliminarUsuario.getContentPane().add(cboxUsuario);
		
		JButton btnBuscarUsuario = new JButton("Buscar usuario");
		btnBuscarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombreUsuario = txtApellidoUsuario.getText().toUpperCase();
				try {
					Usuario usuario = daoUsuario.find(nombreUsuario);
					cboxUsuario.addItem(usuario.getUsuario());
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			
			}
		});
		btnBuscarUsuario.setToolTipText("Busca al usuario que se desea modificar por su usuario o apellido.");
		btnBuscarUsuario.setBounds(268, 52, 131, 23);
		frmEliminarUsuario.getContentPane().add(btnBuscarUsuario);
	}
}
