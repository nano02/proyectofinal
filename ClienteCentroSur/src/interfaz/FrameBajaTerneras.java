package interfaz;
//Modificado por AC 24/11/17
/*Agrego Entidad baja y DAo Baja para poder registrar en la base los datos
 * correspondientes a la baja
 * Se realizó la actualización de los datos acorde a la nueva nomenclatura
 * */
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Date;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.DAOs.DAOBaja;
import com.DAOs.DAOTernera;
import com.cliente.EJBLocator;
import com.excepciones.TamboException;
import com.models.BajaTernera;
import com.models.Ternera;
import com.services.TamboBeanRemote;
import com.toedter.calendar.JDateChooser;

public class FrameBajaTerneras {

	private JFrame frmBajaTernera;
	private JTextField txtBuscarTernera;
	private JButton btnVolverDeEliminar;
	private JButton btnEliminarTernera;
	

	@EJB DAOTernera daoTernera;
	@EJB DAOBaja daoBaja;
	private Ternera ternera = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameBajaTerneras window = new FrameBajaTerneras();
					window.frmBajaTernera.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameBajaTerneras() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBajaTernera = new JFrame();
		frmBajaTernera.setResizable(false);
		frmBajaTernera.setIconImage(Toolkit.getDefaultToolkit().getImage(FrameBajaTerneras.class.getResource("/pic/logoCS.jpeg")));
		frmBajaTernera.setVisible(true);
		frmBajaTernera.setTitle("Eliminar ternera");
		frmBajaTernera.setBounds(100, 100, 515, 375);
		frmBajaTernera.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBajaTernera.getContentPane().setLayout(null);

		txtBuscarTernera = new JTextField();
		txtBuscarTernera.setToolTipText("Buscar ternera por identificador o n\u00FAmero de caravana");
		txtBuscarTernera.setBounds(34, 36, 212, 20);
		frmBajaTernera.getContentPane().add(txtBuscarTernera);
		txtBuscarTernera.setColumns(10);

		JLabel lblBajaTernera = new JLabel(
				"Por favor, ingrese el n\u00FAmero de caravana o identificador de la ternera.");
		lblBajaTernera.setBounds(24, 11, 440, 14);
		frmBajaTernera.getContentPane().add(lblBajaTernera);

		JButton btnBuscarTernera = new JButton("Buscar ternera");
		btnBuscarTernera.setToolTipText(
				"Presionar para buscar a la ternera por el n\u00FAmero de caravana o identificador ingresado.");

		
		
		btnBuscarTernera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nroCaravana = txtBuscarTernera.getText();
				Long idTernera = null;

				try {
					idTernera = Long.parseLong(txtBuscarTernera.getText());
				} catch (NumberFormatException e2) {
					e2.printStackTrace();
				}

				ternera = obtenerTernera(idTernera, nroCaravana);

			}			
		});

		btnBuscarTernera.setBounds(261, 35, 174, 23);
		frmBajaTernera.getContentPane().add(btnBuscarTernera);

		btnVolverDeEliminar = new JButton("VOLVER");
		btnVolverDeEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameInicio inicio = new FrameInicio();
				frmBajaTernera.dispose();
			}
		});
		btnVolverDeEliminar.setToolTipText("Regresar a la pantalla anterior.");
		btnVolverDeEliminar.setBounds(9, 300, 89, 23);
		frmBajaTernera.getContentPane().add(btnVolverDeEliminar);
		btnEliminarTernera = new JButton("CONFIRMAR");	

		btnEliminarTernera.setToolTipText("Eliminar la ternera encontrada.");
		btnEliminarTernera.setBounds(192, 300, 118, 23);
		frmBajaTernera.getContentPane().add(btnEliminarTernera);

		JLabel lblNewLabel = new JLabel("FECHA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 120, 66, 14);
		frmBajaTernera.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("CAUSA");
		lblNewLabel_1.setToolTipText("Causa de baja de la ternera. Obligatoria.");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(9, 175, 44, 14);
		frmBajaTernera.getContentPane().add(lblNewLabel_1);

		final JDateChooser calendarBaja = new JDateChooser();
		calendarBaja.getCalendarButton().setToolTipText("Fecha de baja de la ternera. DD/MM/AAAA");
		calendarBaja.setToolTipText("Fecha de baja de la ternera. DD/MM/AAAA");

		calendarBaja.setBounds(57, 120, 141, 20);
		frmBajaTernera.getContentPane().add(calendarBaja);

		final JDateChooser calendarMuerte = new JDateChooser();
		calendarMuerte.getCalendarButton().setToolTipText("Fecha de muerte de la ternera. DD/MM/AAAA");
		calendarMuerte.setToolTipText("Fecha de muerte de la ternera. DD/MM/AAAA");
		calendarMuerte.setBounds(323, 120, 141, 20);
		frmBajaTernera.getContentPane().add(calendarMuerte);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 154, 179, 93);
		frmBajaTernera.getContentPane().add(scrollPane);

		final JTextArea txtCausaBaja = new JTextArea();
		txtCausaBaja.setToolTipText("Causa de baja de la ternera. Obligatoria.");
		txtCausaBaja.setLineWrap(true);
		scrollPane.setViewportView(txtCausaBaja);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(323, 154, 179, 93);
		frmBajaTernera.getContentPane().add(scrollPane_1);

		final JTextArea txtCausaMuerte = new JTextArea();
		txtCausaMuerte.setToolTipText("Causa de muerte de la ternera.");
		scrollPane_1.setViewportView(txtCausaMuerte);
		txtCausaMuerte.setLineWrap(true);

		JLabel label = new JLabel("FECHA");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(256, 120, 66, 14);
		frmBajaTernera.getContentPane().add(label);

		JLabel label_1 = new JLabel("CAUSA");
		label_1.setToolTipText("Causa de muerte de la ternera. ");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(267, 171, 44, 14);
		frmBajaTernera.getContentPane().add(label_1);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(251, 106, 16, 192);
		frmBajaTernera.getContentPane().add(separator);

		JButton btnBaja = new JButton("BAJA");
		btnBaja.setFont(new Font("Simplified Arabic", Font.BOLD, 12));
		btnBaja.setEnabled(false);
		btnBaja.setBounds(82, 79, 89, 23);
		frmBajaTernera.getContentPane().add(btnBaja);

		JButton btnMuerte = new JButton("MUERTE");
		btnMuerte.setFont(new Font("Simplified Arabic", Font.BOLD, 12));
		btnMuerte.setEnabled(false);
		btnMuerte.setBounds(346, 79, 89, 23);
		frmBajaTernera.getContentPane().add(btnMuerte);


		btnEliminarTernera.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				TamboBeanRemote tamboBean = null;
				boolean sePudo = false;

				try {
					tamboBean = EJBLocator.getInstance().lookup(TamboBeanRemote.class);
				} catch (NamingException e1) {
					e1.printStackTrace();
				}

				Date fechaMuerte = calendarMuerte.getDate();						
				String causaMuerte = txtCausaMuerte.getText();
				Date fechaBaja = calendarBaja.getDate();
				String causaBaja = txtCausaBaja.getText();

				BajaTernera baja = new BajaTernera(ternera, fechaMuerte, causaMuerte, fechaBaja, causaBaja);

				int respuesta = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar?","Sistema",JOptionPane.YES_NO_OPTION);
				if(respuesta == JOptionPane.YES_OPTION){

					if(calendarMuerte.getDate()== null){
						try {
							tamboBean = EJBLocator.getInstance().lookup(TamboBeanRemote.class);
							tamboBean.bajaTernera(baja);							
							sePudo = true;
						} catch (TamboException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage() , "Baja ternera",
									JOptionPane.INFORMATION_MESSAGE);
						} catch (NamingException e1) {
							e1.printStackTrace();
						}						

					} else if(calendarMuerte.getDate()!= null){
						try {
							tamboBean = EJBLocator.getInstance().lookup(TamboBeanRemote.class);
							tamboBean.muerteTernera(baja);
							Date fechaBajaFrm = calendarMuerte.getDate();
							calendarBaja.setDate(fechaBajaFrm);
							txtCausaBaja.setText("Muerte");
							sePudo = true;
						} catch (TamboException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage() , "Baja ternera",
									JOptionPane.INFORMATION_MESSAGE);
						} catch (NamingException e1) {
							e1.printStackTrace();
						}
						
					}	
					
					if (sePudo) {

						try {
							tamboBean = EJBLocator.getInstance().lookup(TamboBeanRemote.class);
							tamboBean.eliminarTernera(ternera);
						} catch (TamboException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (NamingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}}
					
				} else if(respuesta == JOptionPane.NO_OPTION) {
					FrameInicio inicio = new FrameInicio();
					frmBajaTernera.dispose();
				}

				if(sePudo)
				{
					JOptionPane.showMessageDialog(null, "Ternera eliminada correctamente");
					int respuestaFin = JOptionPane.showConfirmDialog(null, "¿Desea registrar una nueva baja?","Sistema",JOptionPane.YES_NO_OPTION);
					if(respuestaFin == JOptionPane.YES_OPTION){
						txtBuscarTernera.setText("");
						txtBuscarTernera.getFocusListeners();
						calendarMuerte.setDate(null);						
						txtCausaMuerte.setText("");
						calendarBaja.setDate(null);
						txtCausaBaja.setText("");
					}
					else if(respuestaFin == JOptionPane.NO_OPTION){
						FrameInicio inicio = new FrameInicio();
						frmBajaTernera.dispose();
					}
				}					 

			}
		});
	}

	private Ternera obtenerTernera(Long idTernera, String nroCaravana) {

		Ternera terneraPorCaravana = null;
		Ternera terneraPorId = null;
		Ternera ternera = null;
		TamboBeanRemote tamboBean = null;

		try {
			tamboBean = EJBLocator.getInstance().lookup(TamboBeanRemote.class);			
		} catch (NamingException e2) {
			e2.printStackTrace();
		} 		
		
		try {
			idTernera = Long.parseLong(txtBuscarTernera.getText());
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(null, "Debe ingresar solamente números");
			e2.printStackTrace();
		}
		
		try {
			terneraPorCaravana = tamboBean.buscarTerneraPorCaravana(nroCaravana);
		} catch (TamboException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Baja de ternera",
					JOptionPane.INFORMATION_MESSAGE);
		}	

		try {
			terneraPorId = tamboBean.buscarTerneraPorIdViva(idTernera);
		} catch (TamboException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Baja de ternera",
					JOptionPane.INFORMATION_MESSAGE);
		}		

		if(terneraPorId==null && terneraPorCaravana==null )
		{
			JOptionPane.showMessageDialog(null, "Ternera desconocida");
		}

		else if (terneraPorId!=null)
		{
			JOptionPane.showMessageDialog(null, "Ternera encontrada por Número de Identificador");
			ternera  = terneraPorId;

		}
		else if(terneraPorCaravana!= null){
			JOptionPane.showMessageDialog(null, "Ternera encontrada por Número de Caravana");
			ternera  = terneraPorCaravana;
		}
		return ternera;
	}

}