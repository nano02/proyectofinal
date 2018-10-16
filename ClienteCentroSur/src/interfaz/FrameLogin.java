package interfaz;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;

import com.DAOs.DAOUsuario;
import com.excepciones.TamboException;
import com.models.Usuario;
import com.services.TamboBeanRemote;

import javax.swing.border.BevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class FrameLogin {

	private JFrame frmPrincipal;
	private JTextField txtUsuario;
	private JPasswordField passLogin;

	@EJB DAOUsuario daoUsuario;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameLogin window = new FrameLogin();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setResizable(false);
		frmPrincipal.setVisible(true);
		frmPrincipal.setTitle("CENTROSUR");
		frmPrincipal.setIconImage(Toolkit.getDefaultToolkit().getImage(FrameLogin.class.getResource("/pic/logoCS.jpeg")));
		frmPrincipal.setBounds(100, 100, 481, 317);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{55, 139, 137, 0, 48, 0};
		gridBagLayout.rowHeights = new int[]{33, 14, 14, 33, 20, 20, 41, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmPrincipal.getContentPane().setLayout(gridBagLayout);
		
				JLabel lblBienvenidos = new JLabel("\u00A1BIENVENIDOS!");
				GridBagConstraints gbc_lblBienvenidos = new GridBagConstraints();
				gbc_lblBienvenidos.anchor = GridBagConstraints.NORTH;
				gbc_lblBienvenidos.insets = new Insets(0, 0, 5, 5);
				gbc_lblBienvenidos.gridx = 2;
				gbc_lblBienvenidos.gridy = 1;
				frmPrincipal.getContentPane().add(lblBienvenidos, gbc_lblBienvenidos);
		
				JLabel lblNewLabel = new JLabel("Sistema de Gesti\u00F3n Operativa del Proceso de Crianza de Terneras de Tambo");
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridwidth = 3;
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 2;
				frmPrincipal.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
						
								JLabel lblNewLabel_1 = new JLabel("Usuario");
								GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
								gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
								gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
								gbc_lblNewLabel_1.gridx = 1;
								gbc_lblNewLabel_1.gridy = 4;
								frmPrincipal.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
				
						txtUsuario = new JTextField();
						txtUsuario.setToolTipText("Ingrese su nombre de usuario.");
						txtUsuario.setColumns(10);
						GridBagConstraints gbc_txtUsuario = new GridBagConstraints();
						gbc_txtUsuario.anchor = GridBagConstraints.NORTH;
						gbc_txtUsuario.fill = GridBagConstraints.HORIZONTAL;
						gbc_txtUsuario.insets = new Insets(0, 0, 5, 5);
						gbc_txtUsuario.gridx = 2;
						gbc_txtUsuario.gridy = 4;
						frmPrincipal.getContentPane().add(txtUsuario, gbc_txtUsuario);
		
				JLabel lblContrasea = new JLabel("Contrase\u00F1a");
				GridBagConstraints gbc_lblContrasea = new GridBagConstraints();
				gbc_lblContrasea.anchor = GridBagConstraints.EAST;
				gbc_lblContrasea.insets = new Insets(0, 0, 5, 5);
				gbc_lblContrasea.gridx = 1;
				gbc_lblContrasea.gridy = 5;
				frmPrincipal.getContentPane().add(lblContrasea, gbc_lblContrasea);
		
		JButton btnNewButton = new JButton("Acceso T\u00E9cnico");
		btnNewButton.setToolTipText("Acceso para los t\u00E9cnicos encargados de mantener el sistema.");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameAccesoTecnico at = new FrameAccesoTecnico();
			}

		});
		
				passLogin = new JPasswordField();
				passLogin.setToolTipText("Ingrese su contrase\u00F1a.");
				GridBagConstraints gbc_passLogin = new GridBagConstraints();
				gbc_passLogin.anchor = GridBagConstraints.NORTH;
				gbc_passLogin.fill = GridBagConstraints.HORIZONTAL;
				gbc_passLogin.insets = new Insets(0, 0, 5, 5);
				gbc_passLogin.gridx = 2;
				gbc_passLogin.gridy = 5;
				frmPrincipal.getContentPane().add(passLogin, gbc_passLogin);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 7;
		frmPrincipal.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
				JButton btSalir = new JButton("Salir");
				btSalir.setToolTipText("Salir de la aplicaci\u00F3n.");
				btSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int resp = JOptionPane.showConfirmDialog(null,"¿Desea salir de la aplicación?", "Atención", JOptionPane.YES_NO_OPTION);
						if(JOptionPane.OK_OPTION==resp){
							frmPrincipal.dispose();
						}
						else if(JOptionPane.CANCEL_OPTION==resp){
							frmPrincipal.setVisible(true);
						}

					}
				});
				GridBagConstraints gbc_btSalir = new GridBagConstraints();
				gbc_btSalir.anchor = GridBagConstraints.NORTHEAST;
				gbc_btSalir.insets = new Insets(0, 0, 0, 5);
				gbc_btSalir.gridx = 2;
				gbc_btSalir.gridy = 7;
				frmPrincipal.getContentPane().add(btSalir, gbc_btSalir);
						
								JButton btnAceptar = new JButton("Aceptar");
								btnAceptar.setToolTipText("Ingresar al sistema.");
								btnAceptar.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										String nombreUsuario = txtUsuario.getText().toUpperCase();
										String clave = passLogin.getText().toUpperCase();
										try {
											if(nombreUsuario.isEmpty()){
												JOptionPane.showMessageDialog(null, "Debe ingresar un Nombre de usuario.", "Login",
														JOptionPane.INFORMATION_MESSAGE);
												txtUsuario.grabFocus();
											}
										} catch (HeadlessException e3) {
											e3.printStackTrace();
										}

										try {
											if(clave.isEmpty()){
												JOptionPane.showMessageDialog(null, "Debe ingresar una contraseña.", "Login",
														JOptionPane.INFORMATION_MESSAGE);
												passLogin.grabFocus();
											}
										} catch (HeadlessException e2) {

											e2.printStackTrace();
										}


										Usuario usuario = null;

										try {
											TamboBeanRemote tamboBean = (TamboBeanRemote)
													InitialContext.doLookup("CentroSur_N2/TamboBean!com.services.TamboBeanRemote");
											usuario = tamboBean.buscarUsuarioLogin(nombreUsuario, clave);
										} catch (NamingException | TamboException e1) {
											e1.printStackTrace();
										}
									if(usuario!= null){		
										FrameInicio inicio = new FrameInicio();
										frmPrincipal.dispose();

									}
									}});
								GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
								gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
								gbc_btnAceptar.anchor = GridBagConstraints.NORTHEAST;
								gbc_btnAceptar.gridx = 3;
								gbc_btnAceptar.gridy = 7;
								frmPrincipal.getContentPane().add(btnAceptar, gbc_btnAceptar);
	}
}
