package interfaz;
//Modificado por AC 24/11/17
/*La modificación no permite realizar modificaciones en el peso de la ternera
 *en la versión anterior la ternera tenía un atributo peso de nacimiento, y eso era
 *lo que se podía modificar
 *Habría que ver como hace el usuario para agregar un peso nuevo o modificar un peso específico 
 * */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;

import com.DAOs.DAOGuachera;
import com.DAOs.DAOMadre;
import com.DAOs.DAOPadre;
import com.DAOs.DAOTernera;
import com.DAOs.DatabaseManager;
import com.cliente.EJBLocator;
import com.enums.RazaTernera;
import com.enums.TipoParto;
import com.excepciones.TamboException;
import com.models.Madre;
import com.models.Padre;
import com.models.Ternera;
import com.services.TamboBeanRemote;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;
import java.awt.Font;


public class FrameModificarTernera {

	private JFrame frmModificarTernera;
	private JTextField txtBuscarTernera;
	private JButton btnVolverDeEliminar;
	private JButton btnModificarTernera;
	private JTextField tbNroCaravana;
	private JTextField tbNroMadre;
	private JTextField tbNroPadre;
	private JTextField tbGuachera;
	private JTextField tbPeso;

	@EJB DAOTernera daoTernera;
	@EJB DAOPadre daoPadre;
	@EJB DAOMadre daoMadre;
	@EJB DAOGuachera daoGuachera;
	
	private long terneraPorId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameModificarTernera window = new FrameModificarTernera();
					window.frmModificarTernera.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws TamboException 
	 */
	public FrameModificarTernera() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException
	 */
	private void initialize() {
		frmModificarTernera = new JFrame();
		frmModificarTernera.setIconImage(Toolkit.getDefaultToolkit().getImage(FrameModificarTernera.class.getResource("/pic/logoCS.jpeg")));
		frmModificarTernera.setResizable(false);
		frmModificarTernera.setTitle("Modificar ternera");
		frmModificarTernera.setBounds(100, 100, 409, 415);
		frmModificarTernera.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmModificarTernera.getContentPane().setLayout(null);

		txtBuscarTernera = new JTextField();
		txtBuscarTernera.setToolTipText("Buscar ternera por identificador o n\u00FAmero de caravana");
		txtBuscarTernera.setBounds(25, 66, 185, 20);
		frmModificarTernera.getContentPane().add(txtBuscarTernera);
		txtBuscarTernera.setColumns(10);

		JLabel lblDescBuscadorTernera = new JLabel("Por favor, ingrese el n\u00FAmero de caravana o identificador de la ternera.");
		lblDescBuscadorTernera.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDescBuscadorTernera.setBounds(25, 29, 358, 14);
		frmModificarTernera.getContentPane().add(lblDescBuscadorTernera);

		JButton btnBuscarTernera = new JButton("Buscar ternera");
		btnBuscarTernera.setToolTipText("Presionar para buscar a la ternera por el n\u00FAmero de caravana o identificador ingresado.");

		btnBuscarTernera.setBounds(231, 65, 134, 23);
		frmModificarTernera.getContentPane().add(btnBuscarTernera);

		btnVolverDeEliminar = new JButton("Volver");
		btnVolverDeEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameInicio inicio = new FrameInicio();
				frmModificarTernera.dispose();
			}
		});
		btnVolverDeEliminar.setToolTipText("Regresar a la pantalla anterior.");
		btnVolverDeEliminar.setBounds(164, 342, 89, 23);
		frmModificarTernera.getContentPane().add(btnVolverDeEliminar);

		btnModificarTernera = new JButton("Modificar");

		btnModificarTernera.setToolTipText("Modifica la informaci\u00F3n la ternera encontrada.");
		btnModificarTernera.setBounds(276, 342, 89, 23);
		frmModificarTernera.getContentPane().add(btnModificarTernera);

		JLabel label = new JLabel("Identificador Ternera");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setToolTipText("Muestra el n\u00FAmero identificador de la ternera encontrada.");
		label.setBounds(27, 96, 178, 14);
		frmModificarTernera.getContentPane().add(label);

		final JLabel lbIdTernera = new JLabel("0");
		lbIdTernera.setToolTipText("Muestra el n\u00FAmero identificador de la ternera encontrada.");
		lbIdTernera.setBackground(Color.WHITE);
		lbIdTernera.setBounds(228, 96, 67, 14);
		frmModificarTernera.getContentPane().add(lbIdTernera);

		JLabel label_2 = new JLabel("");
		label_2.setBounds(-18, 66, 0, 0);
		frmModificarTernera.getContentPane().add(label_2);

		JLabel label_3 = new JLabel("N\u00FAmero de Caravana");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_3.setBounds(27, 118, 173, 14);
		frmModificarTernera.getContentPane().add(label_3);

		tbNroCaravana = new JTextField();
		tbNroCaravana.setToolTipText("N\u00FAmero entero positivo de 10 d\u00EDgitos que comienza con 000.");
		tbNroCaravana.setColumns(10);
		tbNroCaravana.setBounds(221, 115, 105, 20);
		frmModificarTernera.getContentPane().add(tbNroCaravana);

		JLabel label_4 = new JLabel("*");
		label_4.setBounds(336, 118, 6, 14);
		frmModificarTernera.getContentPane().add(label_4);

		JLabel label_5 = new JLabel("Identificador Madre");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_5.setBounds(27, 143, 185, 14);
		frmModificarTernera.getContentPane().add(label_5);

		tbNroMadre = new JTextField();
		tbNroMadre.setToolTipText("N\u00FAmero entero positivo de m\u00E1ximo 4 d\u00EDgitos que identifica a una madre en el sistema.");
		tbNroMadre.setColumns(4);
		tbNroMadre.setBounds(221, 140, 105, 20);
		frmModificarTernera.getContentPane().add(tbNroMadre);

		JLabel label_6 = new JLabel("*");
		label_6.setBounds(336, 143, 6, 14);
		frmModificarTernera.getContentPane().add(label_6);

		JLabel label_7 = new JLabel("Identificador Padre");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setBounds(27, 168, 174, 14);
		frmModificarTernera.getContentPane().add(label_7);

		tbNroPadre = new JTextField();
		tbNroPadre.setToolTipText("N\u00FAmero entero positivo de m\u00E1ximo 4 d\u00EDgitos que identifica a un padre en el sistema.");
		tbNroPadre.setColumns(4);
		tbNroPadre.setBounds(221, 165, 105, 20);
		frmModificarTernera.getContentPane().add(tbNroPadre);

		JLabel label_8 = new JLabel("*");
		label_8.setBounds(336, 168, 6, 14);
		frmModificarTernera.getContentPane().add(label_8);

		JLabel label_9 = new JLabel("Guachera");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_9.setBounds(27, 193, 166, 14);
		frmModificarTernera.getContentPane().add(label_9);

		tbGuachera = new JTextField();
		tbGuachera.setToolTipText("Nombre que identifica a la guachera en el formato GUACHERANNN.");
		tbGuachera.setEditable(false);
		tbGuachera.setColumns(4);
		tbGuachera.setBounds(221, 190, 105, 20);
		frmModificarTernera.getContentPane().add(tbGuachera);

		JLabel label_10 = new JLabel("*");
		label_10.setBounds(336, 193, 6, 14);
		frmModificarTernera.getContentPane().add(label_10);

		JLabel label_11 = new JLabel("Fecha de Nacimiento");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_11.setBounds(27, 218, 178, 14);
		frmModificarTernera.getContentPane().add(label_11);

		final JDateChooser calendarNacimiento = new JDateChooser();
		calendarNacimiento.getCalendarButton().setToolTipText("Fecha de nacimiento de la ternera. Formato DD/MM/YYYY. No se permite el ingreso de fechas futuras.");
		calendarNacimiento.setToolTipText("Fecha de nacimiento de la ternera en el formato DD/MM/YYYY");
		calendarNacimiento.setBounds(221, 215, 103, 20);
		frmModificarTernera.getContentPane().add(calendarNacimiento);
		Date hoy = new Date(System.currentTimeMillis());
		calendarNacimiento.setMaxSelectableDate(hoy);

		JLabel label_12 = new JLabel("*");
		label_12.setBounds(336, 218, 6, 14);
		frmModificarTernera.getContentPane().add(label_12);

		JLabel label_13 = new JLabel("Peso");
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_13.setBounds(27, 243, 104, 14);
		frmModificarTernera.getContentPane().add(label_13);

		tbPeso = new JTextField();
		tbPeso.setToolTipText("N\u00FAmero positivo de m\u00E1ximo 4 enteros y 2 decimales que indican el peso de la ternera en el nacimiento.");
		tbPeso.setColumns(16);
		tbPeso.setBounds(221, 240, 105, 20);
		frmModificarTernera.getContentPane().add(tbPeso);

		JLabel label_14 = new JLabel(" kg   *");
		label_14.setBounds(336, 243, 47, 14);
		frmModificarTernera.getContentPane().add(label_14);

		JLabel label_15 = new JLabel("Raza");
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_15.setBounds(27, 268, 127, 14);
		frmModificarTernera.getContentPane().add(label_15);

		final JComboBox cboxRaza = new JComboBox();
		cboxRaza.setToolTipText("Seleccione la raza de la ternera.");
		cboxRaza.setModel(new DefaultComboBoxModel(RazaTernera.values()));
		cboxRaza.setBounds(221, 265, 105, 20);
		frmModificarTernera.getContentPane().add(cboxRaza);

		JLabel label_16 = new JLabel("*");
		label_16.setBounds(336, 268, 6, 14);
		frmModificarTernera.getContentPane().add(label_16);

		JLabel label_17 = new JLabel("Parto");
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_17.setBounds(27, 293, 136, 14);
		frmModificarTernera.getContentPane().add(label_17);

		final JComboBox cboxParto = new JComboBox();
		cboxParto.setToolTipText("Seleccione el tipo de parto de la ternera.");
		cboxParto.setModel(new DefaultComboBoxModel(TipoParto.values()));
		cboxParto.setBounds(221, 290, 105, 20);
		frmModificarTernera.getContentPane().add(cboxParto);

		JLabel label_1 = new JLabel("*");
		label_1.setBounds(25, 342, 6, 14);
		frmModificarTernera.getContentPane().add(label_1);

		JLabel label_18 = new JLabel("(Campos Obligatorios)");
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_18.setBounds(36, 342, 106, 14);
		frmModificarTernera.getContentPane().add(label_18);

		btnBuscarTernera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				Ternera ternera = null;
				TamboBeanRemote tamboBean = null;
						
				try {
					terneraPorId = Long.parseLong(txtBuscarTernera.getText());
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Debe ingresar solamente números");
					e2.printStackTrace();
				}

				try {
					tamboBean = EJBLocator.getInstance().lookup(TamboBeanRemote.class);
					ternera = tamboBean.buscarTerneraPorIdViva(terneraPorId);
				} catch (NamingException e1) {
					e1.printStackTrace();
				} catch (TamboException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());					
					e1.printStackTrace();
				}				

				String terneraPorNroCaravana = txtBuscarTernera.getText();
				Ternera ternera2 = null;

				try {
					ternera2 = tamboBean.buscarTerneraPorCaravana(terneraPorNroCaravana);
				} catch (TamboException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());					
					e1.printStackTrace();
				}

				if( ternera==null && ternera2== null )
				{
					JOptionPane.showMessageDialog(null, "Ternera desconocida");
				}

				else if (ternera!=null)
				{

					JOptionPane.showMessageDialog(null, "Ternera encontrada por Número de Identificador");
					//Si encuentra la ternera por id, carga los campos con los valores que tiene la ternera en bd
					lbIdTernera.setText(String.valueOf(ternera.getIdTernera()));
					tbNroCaravana.setText(String.valueOf(ternera.getNroCaravana()));
					tbGuachera.setText(ternera.getGuachera().getNombreGuachera());
					tbNroMadre.setText(String.valueOf(ternera.getMadre().getIdMadre()));
					tbNroPadre.setText(String.valueOf(ternera.getPadre().getIdPadre()));
					tbPeso.setText(String.valueOf(ternera.getPesoNacimiento()));
					calendarNacimiento.setDate(ternera.getFechaNacimiento());
					cboxRaza.setSelectedIndex(ternera.getRaza().ordinal());
					cboxParto.setSelectedIndex(ternera.getParto().ordinal());

				}
				else if(ternera2!= null){

					JOptionPane.showMessageDialog(null, "Ternera encontrada por Número de Caravana");
					lbIdTernera.setText(String.valueOf(ternera2.getIdTernera()));
					tbNroCaravana.setText(String.valueOf(ternera2.getNroCaravana()));
					tbGuachera.setText(ternera2.getGuachera().getNombreGuachera());
					tbNroMadre.setText(String.valueOf(ternera2.getMadre().getIdMadre()));
					tbNroPadre.setText(String.valueOf(ternera2.getPadre().getIdPadre()));
					tbPeso.setText(String.valueOf(ternera2.getPesoNacimiento()));
					calendarNacimiento.setDate(ternera2.getFechaNacimiento());
					cboxRaza.setSelectedIndex(ternera2.getRaza().ordinal());
					cboxParto.setSelectedIndex(ternera2.getParto().ordinal());
				}

			}
		});

		btnModificarTernera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TamboBeanRemote tamboBean = null;
				String nroCaravana = tbNroCaravana.getText();
				Madre madre = null;
				Long idMadre = null;
				Boolean isVacioMadre = false;

				if (tbNroMadre.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "El id de la madre no puede ser vacío", "Alta de ternera",
							JOptionPane.INFORMATION_MESSAGE); 
					isVacioMadre = true;
				}
				else {
					
					try {
						idMadre = Long.parseLong(tbNroMadre.getText());
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					}
					isVacioMadre = false;
					
				}

				if (!isVacioMadre) {
					try {
						tamboBean = EJBLocator.getInstance().lookup(TamboBeanRemote.class);
						madre = tamboBean.buscarIdMadre(idMadre);				   

					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}catch (TamboException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Alta de ternera",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				

				Padre padre= null;
				Long idPadre = null;
				Boolean isVacioPadre = false;

				if (tbNroPadre.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "El id del padre no puede ser vacío", "Alta de ternera",
							JOptionPane.INFORMATION_MESSAGE); 
					isVacioPadre = true;
				}
				else {
					
					try {
						idPadre = Long.parseLong(tbNroPadre.getText());
					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					}
					isVacioPadre = false;
					
				}

				if (!isVacioPadre) {
					try {
						tamboBean = EJBLocator.getInstance().lookup(TamboBeanRemote.class);
						padre = tamboBean.buscarIdPadre(idPadre);				   

					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}catch (TamboException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Alta de ternera",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

				Date fechaNacimiento = null;
				try {
					fechaNacimiento = calendarNacimiento.getDate();
				} catch (Exception e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}

				Double pesoNacimiento = null;		

				int coma = tbPeso.getText().indexOf(".");	

				if (coma<=4){

					try {
						pesoNacimiento = redondear(Double.parseDouble(tbPeso.getText()), 2);

					} catch (NumberFormatException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "El peso debe tener 4 enteros o menos", "Alta de ternera",
							JOptionPane.INFORMATION_MESSAGE);
					try {
						throw new TamboException("Peso inválido");
					} catch (TamboException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}

				RazaTernera raza = (RazaTernera) cboxRaza.getSelectedItem();
				TipoParto parto = (TipoParto) cboxParto.getSelectedItem();		
				Long idTernera = null;
				try {
					idTernera = Long.parseLong(lbIdTernera.getText());
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}

				Ternera ternera = new Ternera(idTernera, nroCaravana, madre, padre, fechaNacimiento, raza, parto, pesoNacimiento);					
				try {
					try {
						tamboBean = EJBLocator.getInstance().lookup(TamboBeanRemote.class);
						tamboBean.editarTernera(ternera);
						JOptionPane.showMessageDialog(null, "La ternera ha sido modificada con éxito.", "Modificación exitosa",
								JOptionPane.INFORMATION_MESSAGE);	
						frmModificarTernera.setVisible(true);
						txtBuscarTernera.setText("");
						lbIdTernera.setText("");
						tbNroCaravana.setText("");
						tbGuachera.setText("");
						tbNroMadre.setText("");
						tbNroPadre.setText("");
						calendarNacimiento.setDate(null);
						tbPeso.setText("");


						int resp = JOptionPane.showConfirmDialog(null,"¿Desea salir de la aplicación?", "Atención", JOptionPane.YES_NO_OPTION);

						if(JOptionPane.OK_OPTION==resp){
							frmModificarTernera.dispose();
						}
						else if(JOptionPane.CANCEL_OPTION==resp){
							frmModificarTernera.setVisible(true);
							txtBuscarTernera.setText("");
							lbIdTernera.setText("");
							tbNroCaravana.setText("");
							tbGuachera.setText("");
							tbNroMadre.setText("");
							tbNroPadre.setText("");
							calendarNacimiento.setDate(null);
							tbPeso.setText("");		
						}

					} catch (TamboException e3) {
						JOptionPane.showMessageDialog(null, e3.getMessage());					
						e3.printStackTrace();
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

			private Double redondear(double numero, int decimales) {
				return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
			}
		});

		frmModificarTernera.setVisible(true);
	}
}
