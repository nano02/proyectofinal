package interfaz;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.DAOs.DAOGuachera;
import com.DAOs.DAOUsuario;
import com.cliente.EJBLocator;
import com.enums.PerfilUsuario;
import com.excepciones.TamboException;
import com.models.Guachera;
import com.models.Usuario;
import com.services.TamboBeanRemote;

import javax.swing.JComboBox;
import javax.ejb.EJB;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;

public class FrameAltaUsuario {

	private JFrame frmAltaUsuario;
	private JTextField txtNombreUsuario;
	private JTextField txtApellidoUsuario;
	private JPasswordField passClave;
	private JPasswordField passReClave;

	@EJB DAOGuachera daoGuachera;
	@EJB DAOUsuario daoUsuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAltaUsuario window = new FrameAltaUsuario();
					window.frmAltaUsuario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameAltaUsuario() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAltaUsuario = new JFrame();
		frmAltaUsuario.setIconImage(Toolkit.getDefaultToolkit().getImage(FrameAltaUsuario.class.getResource("/pic/logoCS.jpeg")));
		frmAltaUsuario.setTitle("Alta de usuario");
		frmAltaUsuario.setBounds(100, 100, 450, 320);
		frmAltaUsuario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAltaUsuario.getContentPane().setLayout(null);

		txtNombreUsuario = new JTextField();
		txtNombreUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombreUsuario.setToolTipText("Ingresar el nombre del usuario.");
		txtNombreUsuario.setBounds(181, 25, 211, 20);
		frmAltaUsuario.getContentPane().add(txtNombreUsuario);
		txtNombreUsuario.setColumns(10);

		txtApellidoUsuario = new JTextField();
		txtApellidoUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		txtApellidoUsuario.setToolTipText("Ingresar el apellido del usuario.");
		txtApellidoUsuario.setColumns(10);
		txtApellidoUsuario.setBounds(181, 56, 211, 20);
		frmAltaUsuario.getContentPane().add(txtApellidoUsuario);

		JComboBox cBoxPerfilUsuario = new JComboBox();
		cBoxPerfilUsuario.setModel(new DefaultComboBoxModel(PerfilUsuario.values()));
		cBoxPerfilUsuario.setToolTipText("Seleccionar el perfil del usuario a ingresar.");
		cBoxPerfilUsuario.setBounds(181, 85, 151, 20);
		frmAltaUsuario.getContentPane().add(cBoxPerfilUsuario);

		JButton btnAceptarUsuario = new JButton("Aceptar");

		btnAceptarUsuario.setToolTipText("Terminar con el ingreso del usuario.");
		btnAceptarUsuario.setBounds(303, 241, 89, 23);
		frmAltaUsuario.getContentPane().add(btnAceptarUsuario);

		

		JLabel lblNombreUsuario = new JLabel("Nombre:");
		lblNombreUsuario.setBounds(27, 28, 94, 14);
		frmAltaUsuario.getContentPane().add(lblNombreUsuario);

		JLabel lblApellidoUsuario = new JLabel("Apellido:");
		lblApellidoUsuario.setBounds(27, 57, 94, 14);
		frmAltaUsuario.getContentPane().add(lblApellidoUsuario);

		JLabel lblPerfil = new JLabel("Perfil:");
		lblPerfil.setBounds(27, 88, 94, 14);
		frmAltaUsuario.getContentPane().add(lblPerfil);

		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(27, 122, 72, 14);
		frmAltaUsuario.getContentPane().add(lblUsuario);

		JLabel lblContrasenia = new JLabel("Contrase\u00F1a:");
		lblContrasenia.setBounds(27, 154, 94, 14);
		frmAltaUsuario.getContentPane().add(lblContrasenia);

		JLabel lblVerificarContrasenia = new JLabel("Reingrese la contrase\u00F1a:");
		lblVerificarContrasenia.setBounds(27, 198, 144, 14);
		frmAltaUsuario.getContentPane().add(lblVerificarContrasenia);

		JLabel lblDescContrasenia = new JLabel("Alfanum\u00E9rica, largo m\u00EDnimo 8 d\u00EDgitos y m\u00E1ximo 16.");
		lblDescContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblDescContrasenia.setBounds(181, 170, 243, 14);
		frmAltaUsuario.getContentPane().add(lblDescContrasenia);

		JLabel lbUsuario = new JLabel("Nombre de Usuario");
		lbUsuario.setBounds(181, 120, 151, 20);
		frmAltaUsuario.getContentPane().add(lbUsuario);

		passClave = new JPasswordField();
		passClave.setToolTipText("Ingrese la contrase\u00F1a que desea para el usuario.");
		passClave.setEchoChar('*');
		passClave.setHorizontalAlignment(SwingConstants.LEFT);
		passClave.setBounds(181, 151, 211, 20);
		frmAltaUsuario.getContentPane().add(passClave);

		passReClave = new JPasswordField();
		passReClave.setToolTipText("Reingrese la contrase\u00F1a que desea para el usuario.");
		passReClave.setEchoChar('*');
		passReClave.setHorizontalAlignment(SwingConstants.LEFT);
		passReClave.setBounds(181, 195, 211, 20);
		frmAltaUsuario.getContentPane().add(passReClave);
		
		JLabel label = new JLabel("*");
		label.setBounds(397, 28, 15, 17);
		frmAltaUsuario.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("*  (Campos Obligatorios)");
		label_1.setBounds(27, 245, 118, 14);
		frmAltaUsuario.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("*");
		label_2.setBounds(397, 59, 15, 17);
		frmAltaUsuario.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("*");
		label_3.setBounds(336, 87, 15, 17);
		frmAltaUsuario.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("*");
		label_4.setBounds(397, 154, 15, 17);
		frmAltaUsuario.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("*");
		label_5.setBounds(397, 198, 15, 17);
		frmAltaUsuario.getContentPane().add(label_5);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null,"¿Desea salir de la aplicación?", "Atención", JOptionPane.YES_NO_OPTION);
				if(JOptionPane.YES_OPTION==resp){
					frmAltaUsuario.dispose();
					FrameLogin abrirPrincial = new FrameLogin(); 
				}
				else if(JOptionPane.NO_OPTION==resp){
					txtNombreUsuario.setText("");
					txtApellidoUsuario.setText("");
					cBoxPerfilUsuario.setSelectedIndex(0);
					passClave.setText("");
					passReClave.setText("");
					lbUsuario.setText("Nombre de Usuario");
				}
			}
		});
		btnCancelar.setToolTipText("Volver a la pantalla anterior.");
		btnCancelar.setBounds(181, 241, 89, 23);
		frmAltaUsuario.getContentPane().add(btnCancelar);


		btnAceptarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TamboBeanRemote tamboBean;
				boolean usuarioExiste = false;
				String nombre    = txtNombreUsuario.getText().toUpperCase();
				String apellido  = txtApellidoUsuario.getText().toUpperCase();
				PerfilUsuario perfil = (PerfilUsuario)cBoxPerfilUsuario.getSelectedItem();
				String clave = passClave.getText().toUpperCase();
				String reingresoClave = passReClave.getText().toUpperCase();
				String nombreUsuario = nombre + "." + apellido;
				lbUsuario.setText(nombreUsuario);	
				Usuario usuarioAux = new Usuario();	
				
				try {
					tamboBean = EJBLocator.getInstance().lookup(TamboBeanRemote.class);
					usuarioAux = tamboBean.buscarUsuario(nombreUsuario);
				} catch (NamingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (TamboException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Alta de usuario",
							JOptionPane.INFORMATION_MESSAGE);
				}
				if (usuarioAux != null) {
					JOptionPane.showMessageDialog(null, "Usuario ya existente, por favor revise sus datos. Si no se trata de un error, ingrese en el campo 'Apellido': primerApellidoPrimerLetraSegundoApellido", "Usuario",
							JOptionPane.INFORMATION_MESSAGE);
					usuarioExiste = true;													
				}
				
				if (usuarioExiste){
					int largo = apellido.length();
					nombreUsuario = nombre.concat(".") + apellido.substring(0, largo-2).concat(".") + apellido.substring(largo-1);	
				}
							
				Usuario usuario = new Usuario(nombreUsuario, nombre, apellido, perfil, clave);
				
				try {
					if(!clave.equals(reingresoClave)){
						JOptionPane.showMessageDialog(null, "Las contraseñas ingresadas no coinciden", "Usuario",
								JOptionPane.INFORMATION_MESSAGE);	
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage() , "Alta usuario",
							JOptionPane.INFORMATION_MESSAGE);	
				}
				
				if (usuarioAux == null && clave.equals(reingresoClave)) {
					try {
						tamboBean = EJBLocator.getInstance().lookup(TamboBeanRemote.class);
						tamboBean.altaUsuario(usuario);
						JOptionPane.showMessageDialog(null, "Usuario creado correctamente", "Usuario",
								JOptionPane.INFORMATION_MESSAGE);	
						txtNombreUsuario.setText("");
						txtApellidoUsuario.setText("");
						cBoxPerfilUsuario.setSelectedIndex(0);
						passClave.setText("");
						passReClave.setText("");
						lbUsuario.setText("Nombre de Usuario");
					} catch (NamingException e1) {
						e1.printStackTrace();
					} catch (TamboException e1) {
						e1.printStackTrace();JOptionPane.showMessageDialog(null, e1.getMessage(), "Alta de usuario",
								JOptionPane.INFORMATION_MESSAGE);
					}
					
				}					

				
			}
		});
		
		frmAltaUsuario.setVisible(true);
	}
}
