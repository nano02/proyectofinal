package interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;

import com.enums.RazaTernera;
import com.enums.TipoParto;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import com.enums.TipoPeso;
import com.excepciones.TamboException;
import com.models.Guachera;
import com.models.Madre;
import com.models.Padre;
import com.models.Peso;
import com.models.Ternera;
import com.services.TamboBeanRemote;
import com.services.TerneraBeanRemote;
import com.toedter.calendar.JDateChooser;
import com.DAOs.DAOGuachera;
import com.DAOs.DAOMadre;
import com.DAOs.DAOPadre;
import com.DAOs.DAOTernera;
import com.DAOs.DatabaseManager;
import com.cliente.EJBLocator;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JFormattedTextField;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;	
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class FrameAltaTernera{

	private JFrame FrameAltaTernero;
	private JTextField tbIdMadre;
	private JTextField tbIdPadre;
	private JTextField tbIdGuachera;
	private JTextField tbPeso;
	private JTextField tbNroCaravana;
	private JLabel lbIdTernera;

	@EJB 
	TerneraBeanRemote daoTernera;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameAltaTernera window = new FrameAltaTernera();
					window.FrameAltaTernero.setVisible(true);
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
	public FrameAltaTernera() throws SQLException, TamboException  {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException, TamboException {
		FrameAltaTernero = new JFrame();
		FrameAltaTernero.setResizable(false);
		FrameAltaTernero.setIconImage(Toolkit.getDefaultToolkit().getImage(FrameAltaTernera.class.getResource("/pic/logoCS.jpeg")));
		FrameAltaTernero.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {

			}
		});
		FrameAltaTernero.getContentPane().setEnabled(false);
		FrameAltaTernero.setTitle("ALTA DE TERNERA");
		FrameAltaTernero.setVisible(true);
		FrameAltaTernero.setBounds(100, 100, 473, 413);
		FrameAltaTernero.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Long id = null;
		TamboBeanRemote tamboBean;
		try {
			tamboBean = EJBLocator.getInstance().lookup(TamboBeanRemote.class);
			id = tamboBean.buscarMaxId();

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (TamboException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Alta de ternera",
					JOptionPane.INFORMATION_MESSAGE);
		}

		Date hoy = new Date(System.currentTimeMillis());
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1, 45, 118, 108, 73, 0};
		gridBagLayout.rowHeights = new int[]{1, 26, 14, 20, 20, 20, 20, 20, 20, 20, 20, 32, 23, 14, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		FrameAltaTernero.getContentPane().setLayout(gridBagLayout);

		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.NORTHWEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		FrameAltaTernero.getContentPane().add(label, gbc_label);

		JLabel lblIdternera = new JLabel("Identificador Ternera");
		lblIdternera.setToolTipText("Muestra el identificador con el que se ingresar\u00E1 a la ternera.");
		GridBagConstraints gbc_lblIdternera = new GridBagConstraints();
		gbc_lblIdternera.anchor = GridBagConstraints.EAST;
		gbc_lblIdternera.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdternera.gridx = 2;
		gbc_lblIdternera.gridy = 2;
		FrameAltaTernero.getContentPane().add(lblIdternera, gbc_lblIdternera);

		lbIdTernera = new JLabel("New label");
		lbIdTernera.setToolTipText("Muestra el Id de la ternera a ingresar");
		lbIdTernera.setBackground(Color.WHITE);
		lbIdTernera.setText(id.toString());				   
		GridBagConstraints gbc_lbIdTernera = new GridBagConstraints();
		gbc_lbIdTernera.anchor = GridBagConstraints.NORTHWEST;
		gbc_lbIdTernera.insets = new Insets(0, 0, 5, 5);
		gbc_lbIdTernera.gridx = 3;
		gbc_lbIdTernera.gridy = 2;
		FrameAltaTernero.getContentPane().add(lbIdTernera, gbc_lbIdTernera);

		JLabel lblNewLabel = new JLabel("N\u00FAmero de Caravana");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 3;
		FrameAltaTernero.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		tbNroCaravana = new JTextField();
		tbNroCaravana.setToolTipText("N\u00FAmero entero de 10 d\u00EDgitos que comienza con 000");
		tbNroCaravana.setColumns(12);
		GridBagConstraints gbc_tbNroCaravana = new GridBagConstraints();
		gbc_tbNroCaravana.anchor = GridBagConstraints.NORTH;
		gbc_tbNroCaravana.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbNroCaravana.insets = new Insets(0, 0, 5, 5);
		gbc_tbNroCaravana.gridx = 3;
		gbc_tbNroCaravana.gridy = 3;
		FrameAltaTernero.getContentPane().add(tbNroCaravana, gbc_tbNroCaravana);

		JLabel label_1 = new JLabel("*");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.WEST;
		gbc_label_1.insets = new Insets(0, 0, 5, 0);
		gbc_label_1.gridx = 4;
		gbc_label_1.gridy = 3;
		FrameAltaTernero.getContentPane().add(label_1, gbc_label_1);

		JLabel lblNewLabel_1 = new JLabel("Identificador Madre");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 4;
		FrameAltaTernero.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);

		tbIdMadre = new JTextField();
		tbIdMadre.setToolTipText("N\u00FAmero entero de m\u00E1ximo 4 d\u00EDgitos que identifica a la madre en el sistema");
		tbIdMadre.setColumns(12);
		GridBagConstraints gbc_tbIdMadre = new GridBagConstraints();
		gbc_tbIdMadre.anchor = GridBagConstraints.NORTH;
		gbc_tbIdMadre.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbIdMadre.insets = new Insets(0, 0, 5, 5);
		gbc_tbIdMadre.gridx = 3;
		gbc_tbIdMadre.gridy = 4;
		FrameAltaTernero.getContentPane().add(tbIdMadre, gbc_tbIdMadre);

		JLabel label_2 = new JLabel("*");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.WEST;
		gbc_label_2.insets = new Insets(0, 0, 5, 0);
		gbc_label_2.gridx = 4;
		gbc_label_2.gridy = 4;
		FrameAltaTernero.getContentPane().add(label_2, gbc_label_2);

		JLabel lblIdentificadorPadre = new JLabel("Identificador Padre");
		GridBagConstraints gbc_lblIdentificadorPadre = new GridBagConstraints();
		gbc_lblIdentificadorPadre.anchor = GridBagConstraints.EAST;
		gbc_lblIdentificadorPadre.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdentificadorPadre.gridx = 2;
		gbc_lblIdentificadorPadre.gridy = 5;
		FrameAltaTernero.getContentPane().add(lblIdentificadorPadre, gbc_lblIdentificadorPadre);
		tbIdPadre = new JTextField();
		tbIdPadre.setToolTipText("N\u00FAmero entero de m\u00E1ximo 4 d\u00EDgitos que identifica al padre en el sistema");
		tbIdPadre.setColumns(12);
		GridBagConstraints gbc_tbIdPadre = new GridBagConstraints();
		gbc_tbIdPadre.anchor = GridBagConstraints.NORTH;
		gbc_tbIdPadre.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbIdPadre.insets = new Insets(0, 0, 5, 5);
		gbc_tbIdPadre.gridx = 3;
		gbc_tbIdPadre.gridy = 5;
		FrameAltaTernero.getContentPane().add(tbIdPadre, gbc_tbIdPadre);

		JLabel label_3 = new JLabel("*");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.WEST;
		gbc_label_3.insets = new Insets(0, 0, 5, 0);
		gbc_label_3.gridx = 4;
		gbc_label_3.gridy = 5;
		FrameAltaTernero.getContentPane().add(label_3, gbc_label_3);

		JLabel lblIdentificadorGuachera = new JLabel("Guachera");
		GridBagConstraints gbc_lblIdentificadorGuachera = new GridBagConstraints();
		gbc_lblIdentificadorGuachera.anchor = GridBagConstraints.EAST;
		gbc_lblIdentificadorGuachera.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdentificadorGuachera.gridx = 2;
		gbc_lblIdentificadorGuachera.gridy = 6;
		FrameAltaTernero.getContentPane().add(lblIdentificadorGuachera, gbc_lblIdentificadorGuachera);

		tbIdGuachera = new JTextField();
		tbIdGuachera.setToolTipText("Nombre de la guachera en formato GUACHERANNN");
		tbIdGuachera.setColumns(12);
		GridBagConstraints gbc_tbIdGuachera = new GridBagConstraints();
		gbc_tbIdGuachera.anchor = GridBagConstraints.NORTH;
		gbc_tbIdGuachera.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbIdGuachera.insets = new Insets(0, 0, 5, 5);
		gbc_tbIdGuachera.gridx = 3;
		gbc_tbIdGuachera.gridy = 6;
		FrameAltaTernero.getContentPane().add(tbIdGuachera, gbc_tbIdGuachera);

		final JDateChooser calendarNac = new JDateChooser();
		calendarNac.getCalendarButton().setToolTipText("Fecha de nacimiento de la ternera. Formato DD/MM/YYYY. No se permite el ingreso de fechas futuras.");
		calendarNac.setDateFormatString("DD-MM-YYYY (Fecha de nacimiento de la ternera. Formato DD/MM/YYYY. No se permite el ingreso de fechas futuras.)");
		calendarNac.setToolTipText("Fecha de nacimiento de la ternera. Formato DD/MM/YYYY. No se permite el ingreso de fechas futuras.");
		calendarNac.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}

		});

		JLabel label_4 = new JLabel("*");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.WEST;
		gbc_label_4.insets = new Insets(0, 0, 5, 0);
		gbc_label_4.gridx = 4;
		gbc_label_4.gridy = 6;
		FrameAltaTernero.getContentPane().add(label_4, gbc_label_4);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento");
		GridBagConstraints gbc_lblFechaDeNacimiento = new GridBagConstraints();
		gbc_lblFechaDeNacimiento.anchor = GridBagConstraints.EAST;
		gbc_lblFechaDeNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeNacimiento.gridx = 2;
		gbc_lblFechaDeNacimiento.gridy = 7;
		FrameAltaTernero.getContentPane().add(lblFechaDeNacimiento, gbc_lblFechaDeNacimiento);
		calendarNac.setMaxSelectableDate(hoy);
		GridBagConstraints gbc_calendarNac = new GridBagConstraints();
		gbc_calendarNac.anchor = GridBagConstraints.NORTH;
		gbc_calendarNac.fill = GridBagConstraints.HORIZONTAL;
		gbc_calendarNac.insets = new Insets(0, 0, 5, 5);
		gbc_calendarNac.gridx = 3;
		gbc_calendarNac.gridy = 7;
		FrameAltaTernero.getContentPane().add(calendarNac, gbc_calendarNac);

		JLabel label_5 = new JLabel("*");
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.WEST;
		gbc_label_5.insets = new Insets(0, 0, 5, 0);
		gbc_label_5.gridx = 4;
		gbc_label_5.gridy = 7;
		FrameAltaTernero.getContentPane().add(label_5, gbc_label_5);

		
		JLabel lblPeso = new JLabel("Peso");
		GridBagConstraints gbc_lblPeso = new GridBagConstraints();
		gbc_lblPeso.anchor = GridBagConstraints.EAST;
		gbc_lblPeso.insets = new Insets(0, 0, 5, 5);
		gbc_lblPeso.gridx = 2;
		gbc_lblPeso.gridy = 8;
		FrameAltaTernero.getContentPane().add(lblPeso, gbc_lblPeso);

		tbPeso = new JTextField();
		tbPeso.setToolTipText("N\u00FAmero decimal de hasta 4 d\u00EDgitos enteros y 2 decimales. Peso de nacimiento de la ternera expresado en kilos.");
		tbPeso.setColumns(10);
		GridBagConstraints gbc_tbPeso = new GridBagConstraints();
		gbc_tbPeso.anchor = GridBagConstraints.NORTH;
		gbc_tbPeso.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbPeso.insets = new Insets(0, 0, 5, 5);
		gbc_tbPeso.gridx = 3;
		gbc_tbPeso.gridy = 8;
		FrameAltaTernero.getContentPane().add(tbPeso, gbc_tbPeso);

		JLabel lblKg = new JLabel(" kg   *");
		GridBagConstraints gbc_lblKg = new GridBagConstraints();
		gbc_lblKg.anchor = GridBagConstraints.WEST;
		gbc_lblKg.insets = new Insets(0, 0, 5, 0);
		gbc_lblKg.gridx = 4;
		gbc_lblKg.gridy = 8;
		FrameAltaTernero.getContentPane().add(lblKg, gbc_lblKg);
		
		JLabel lblNewLabel_2 = new JLabel("Raza");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 9;
		FrameAltaTernero.getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);

		final JComboBox cbRaza = new JComboBox();
		cbRaza.setToolTipText("Seleccione la raza de la ternera.");
		cbRaza.setModel(new DefaultComboBoxModel(RazaTernera.values()));
		GridBagConstraints gbc_cbRaza = new GridBagConstraints();
		gbc_cbRaza.anchor = GridBagConstraints.NORTH;
		gbc_cbRaza.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbRaza.insets = new Insets(0, 0, 5, 5);
		gbc_cbRaza.gridx = 3;
		gbc_cbRaza.gridy = 9;
		FrameAltaTernero.getContentPane().add(cbRaza, gbc_cbRaza);

		JLabel label_6 = new JLabel("*");
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.anchor = GridBagConstraints.WEST;
		gbc_label_6.insets = new Insets(0, 0, 5, 0);
		gbc_label_6.gridx = 4;
		gbc_label_6.gridy = 9;
		FrameAltaTernero.getContentPane().add(label_6, gbc_label_6);

		JLabel lblNewLabel_3 = new JLabel("Parto");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 10;
		FrameAltaTernero.getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);

		final JComboBox cbTipoParto = new JComboBox();
		cbTipoParto.setToolTipText("Seleccione el tipo de parto de la ternera.");
		cbTipoParto.setModel(new DefaultComboBoxModel(TipoParto.values()));
		GridBagConstraints gbc_cbTipoParto = new GridBagConstraints();
		gbc_cbTipoParto.anchor = GridBagConstraints.NORTH;
		gbc_cbTipoParto.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbTipoParto.insets = new Insets(0, 0, 5, 5);
		gbc_cbTipoParto.gridx = 3;
		gbc_cbTipoParto.gridy = 10;
		FrameAltaTernero.getContentPane().add(cbTipoParto, gbc_cbTipoParto);

		JLabel label_7 = new JLabel("*");
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.anchor = GridBagConstraints.WEST;
		gbc_label_7.insets = new Insets(0, 0, 5, 0);
		gbc_label_7.gridx = 4;
		gbc_label_7.gridy = 10;
		FrameAltaTernero.getContentPane().add(label_7, gbc_label_7);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setToolTipText("Ingresa los datos anteriores al sistema como una nueva ternera. Por favor verificar que los datos son correctos y cumplen con el formato establecido.");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				TamboBeanRemote tamboBean;			
				String nombreGuachera = tbIdGuachera.getText();
				Guachera guachera = null;

				try {
					tamboBean = EJBLocator.getInstance().lookup(TamboBeanRemote.class);
					guachera = tamboBean.buscarNombreGuachera(nombreGuachera);				   

				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (TamboException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Alta ternera",
							JOptionPane.INFORMATION_MESSAGE);
				}

				String nroCaravana = tbNroCaravana.getText();				
				Madre madre = null;
				Long idMadre = null;
				Boolean isVacioMadre = false;

				if (tbIdMadre.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "El id de la madre no puede ser vac�o", "Alta de ternera",
							JOptionPane.INFORMATION_MESSAGE); 
					isVacioMadre = true;
				}
				else {
					
					try {
						idMadre = Long.parseLong(tbIdMadre.getText());
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					isVacioMadre = false;
					
				}

				if (!isVacioMadre) {
					try {
						tamboBean = EJBLocator.getInstance().lookup(TamboBeanRemote.class);
						madre = tamboBean.buscarIdMadre(idMadre);				   

					} catch (NamingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}catch (TamboException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Alta de ternera",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
				

				Padre padre= null;
				Long idPadre = null;
				Boolean isVacioPadre = false;

				if (tbIdPadre.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "El id del padre no puede ser vac�o", "Alta de ternera",
							JOptionPane.INFORMATION_MESSAGE); 
					isVacioPadre = true;
				}
				else {
					
					try {
						idPadre = Long.parseLong(tbIdPadre.getText());
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
					isVacioPadre = false;
					
				}

				if (!isVacioPadre) {
					try {
						tamboBean = EJBLocator.getInstance().lookup(TamboBeanRemote.class);
						padre = tamboBean.buscarIdPadre(idPadre);				   

					} catch (NamingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}catch (TamboException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Alta de ternera",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

				Date fechaNacimiento = null;
				try {
					fechaNacimiento = calendarNac.getDate();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Por favor ingrese una fecha v�lida (DD/MM/AA)", "Alta de ternera",
							JOptionPane.INFORMATION_MESSAGE);
				}			


				Double pesoNacimiento = null;		

				int coma = tbPeso.getText().indexOf(".");	

				if (coma<=4){

					try {
						pesoNacimiento = redondear(Double.parseDouble(tbPeso.getText()), 2);

					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "El peso debe tener 4 enteros o menos", "Alta de ternera",
							JOptionPane.INFORMATION_MESSAGE);
					try {
						throw new TamboException("Peso inv�lido");
					} catch (TamboException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				RazaTernera raza = (RazaTernera)cbRaza.getSelectedItem();
				TipoParto parto = (TipoParto)cbTipoParto.getSelectedItem();
				Boolean baja = false;

				Ternera ternera = new Ternera(guachera, nroCaravana, madre, padre, fechaNacimiento, raza, parto, pesoNacimiento, baja);
				try {
					tamboBean = EJBLocator.getInstance().lookup(TamboBeanRemote.class);
					tamboBean.altaTernera(ternera);	
					JOptionPane.showMessageDialog(null, "La ternera ha sido ingresada con �xito.", "Alta ternera exitosa",
							JOptionPane.INFORMATION_MESSAGE);	
					tbIdGuachera.setText("");
					tbNroCaravana.setText("");
					tbIdMadre.setText("");
					tbIdPadre.setText("");
					tbPeso.setText("");		

					try {
						tamboBean = EJBLocator.getInstance().lookup(TamboBeanRemote.class);
						Long id = tamboBean.buscarMaxId();
						lbIdTernera.setText(id.toString());				   

					} catch (NamingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}catch (TamboException e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Alta de ternera",
								JOptionPane.INFORMATION_MESSAGE);
					}


				} catch (NamingException e) {
					e.printStackTrace();
				}catch (TamboException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Alta de ternera",
							JOptionPane.INFORMATION_MESSAGE);
				}				

			}
		});

		JButton btnBorrar = new JButton("Reportar muerte");
		btnBorrar.setToolTipText("Ir a la secci\u00F3n reportar muerte.");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameBajaTerneras baja = new FrameBajaTerneras();
				/*
				 Insertar nueva ternera asi el usuario no tiene que salir de la ventana
				 */
				FrameAltaTernero.dispose();	
			}
		});
		GridBagConstraints gbc_btnBorrar = new GridBagConstraints();
		gbc_btnBorrar.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnBorrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBorrar.gridx = 2;
		gbc_btnBorrar.gridy = 12;
		FrameAltaTernero.getContentPane().add(btnBorrar, gbc_btnBorrar);

		JButton button = new JButton("Cancelar");
		button.setToolTipText("Cancela el proceso de ingreso de una ternera. Los datos no ser\u00E1n ingresados en el sistema.");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int resp = JOptionPane.showConfirmDialog(null,"�Desea volver al men� anterior? (No se guardar�n los datos ingresados)", "Atenci�n", JOptionPane.YES_NO_OPTION);
				if(JOptionPane.OK_OPTION==resp){
					FrameInicio inicio = new FrameInicio();
					FrameAltaTernero.dispose();
				}
				else if(JOptionPane.CANCEL_OPTION==resp){
					FrameAltaTernero.setVisible(true);
				}
			}
		});
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.anchor = GridBagConstraints.NORTH;
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 3;
		gbc_button.gridy = 12;
		FrameAltaTernero.getContentPane().add(button, gbc_button);
		GridBagConstraints gbc_btnIngresar = new GridBagConstraints();
		gbc_btnIngresar.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnIngresar.insets = new Insets(0, 0, 5, 0);
		gbc_btnIngresar.gridx = 4;
		gbc_btnIngresar.gridy = 12;
		FrameAltaTernero.getContentPane().add(btnIngresar, gbc_btnIngresar);

		JLabel lblcamposObligatorios = new JLabel("*  (Campos Obligatorios)");
		GridBagConstraints gbc_lblcamposObligatorios = new GridBagConstraints();
		gbc_lblcamposObligatorios.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblcamposObligatorios.insets = new Insets(0, 0, 0, 5);
		gbc_lblcamposObligatorios.gridx = 2;
		gbc_lblcamposObligatorios.gridy = 13;
		FrameAltaTernero.getContentPane().add(lblcamposObligatorios, gbc_lblcamposObligatorios);

	}

	public static double redondear(double numero, int decimales)
	{
		return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
	}

}

