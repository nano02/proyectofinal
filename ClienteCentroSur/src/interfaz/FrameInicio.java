package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.excepciones.TamboException;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class FrameInicio {

	private JFrame frameInicio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameInicio window = new FrameInicio();
					window.frameInicio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameInicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameInicio = new JFrame();
		frameInicio.setResizable(false);
		frameInicio.setTitle("PROCESO DE CRIANZA DE LAS TERNERAS");
		frameInicio.setIconImage(Toolkit.getDefaultToolkit().getImage(FrameInicio.class.getResource("/pic/logoCS.jpeg")));
		frameInicio.setVisible(true);
		frameInicio.setBounds(100, 100, 450, 300);
		frameInicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnRegistroDeTerneras = new JButton("Registro de Terneras");
		btnRegistroDeTerneras.setToolTipText("Seleccione para ingresar una ternera al sistema.");
		btnRegistroDeTerneras.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRegistroDeTerneras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FrameAltaTernera abrir = new FrameAltaTernera();
				} catch (SQLException | TamboException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage() , "Alta ternera exitosa",
							JOptionPane.INFORMATION_MESSAGE);	
				}
			}

		});

		JButton btnInmunidadYSalud = new JButton("Inmunidad y Salud");
		btnInmunidadYSalud.setToolTipText("Seleccione para dirigirse a la secci\u00F3n de inmunidad y salud.");
		btnInmunidadYSalud.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnInmunidadYSalud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameDiaEventoClinico  frm = new FrameDiaEventoClinico();
			}
		});

		JButton btnAlimentacin = new JButton("Alimentaci\u00F3n");
		btnAlimentacin.setToolTipText("Seleccione para dirigirse a la secci\u00F3n de alimentaci\u00F3n de las terneras.");
		btnAlimentacin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAlimentacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameCostoAlimentacion alimentacion = new FrameCostoAlimentacion();
			}
		});

		JButton btnVolver = new JButton("Volver");
		btnVolver.setToolTipText("Seleccione para volver al men\u00FA anterior.");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameLogin abrirPrincial = new FrameLogin(); 
				frameInicio.dispose();
			}
		});

		JButton btnModifDeTerneras = new JButton("Modificar Ternera");
		btnModifDeTerneras.setToolTipText("Seleccione para editar una ternera previamente ingresada al sistema.");
		btnModifDeTerneras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameModificarTernera modificar = new FrameModificarTernera();
				frameInicio.dispose();	
			}
		});
		btnModifDeTerneras.setFont(new Font("Tahoma", Font.PLAIN, 13));

		JButton btnBajaDeTernera = new JButton("Eliminar Ternera");
		btnBajaDeTernera.setToolTipText("Seleccione para eliminar una ternera previamente ingresada al sistema.");
		btnBajaDeTernera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameBajaTerneras baja = new FrameBajaTerneras();

				frameInicio.dispose();	
			}
		});
		btnBajaDeTernera.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GroupLayout groupLayout = new GroupLayout(frameInicio.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap(195, Short.MAX_VALUE)
						.addComponent(btnVolver)
						.addGap(186))
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(134)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnModifDeTerneras, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRegistroDeTerneras, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btnAlimentacin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnInmunidadYSalud, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnBajaDeTernera, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(133, Short.MAX_VALUE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addGap(31)
						.addComponent(btnRegistroDeTerneras, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnModifDeTerneras, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnBajaDeTernera, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnInmunidadYSalud, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnAlimentacin, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(btnVolver)
						.addGap(28))
				);
		frameInicio.getContentPane().setLayout(groupLayout);
		frameInicio.setVisible(true);
	}
}
