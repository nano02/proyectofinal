package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JPasswordField;

public class FrameAccesoTecnico {

	private JFrame frmAccesoTecnico;
	private JPasswordField passAccesoTec;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAccesoTecnico window = new FrameAccesoTecnico();
					window.frmAccesoTecnico.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameAccesoTecnico() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAccesoTecnico = new JFrame();
		frmAccesoTecnico.setIconImage(Toolkit.getDefaultToolkit().getImage(FrameAccesoTecnico.class.getResource("/pic/logoCS.jpeg")));
		frmAccesoTecnico.setVisible(true);
		frmAccesoTecnico.setTitle("Acceso T\u00E9cnico");
		frmAccesoTecnico.setBounds(100, 100, 376, 157);
		frmAccesoTecnico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		passAccesoTec = new JPasswordField();
		passAccesoTec.setToolTipText("Ingresar contrase\u00F1a de acceso t\u00E9cnico");
		JLabel lblVerificacionAccTec = new JLabel("C\u00F3digo de Verificaci\u00F3n");

		JButton btnNewButton = new JButton("Salir");
		btnNewButton.setToolTipText("Volver al men\u00FA anterior.");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				FrameLogin abrirPrincipal = new FrameLogin();
				frmAccesoTecnico.dispose();
			}
		});

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setToolTipText("Ingresar. ");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(passAccesoTec.getText()=="1234"){
					FrameAltaUsuario alta = new FrameAltaUsuario();
					frmAccesoTecnico.dispose();
				}

			}
		});


		GroupLayout groupLayout = new GroupLayout(frmAccesoTecnico.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnAceptar))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblVerificacionAccTec)
							.addGap(35)
							.addComponent(passAccesoTec, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVerificacionAccTec)
						.addComponent(passAccesoTec, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAceptar)
						.addComponent(btnNewButton))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		frmAccesoTecnico.getContentPane().setLayout(groupLayout);
	}
}
