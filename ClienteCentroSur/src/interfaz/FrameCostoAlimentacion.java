package interfaz;
//Modificado por AC 24/11/17
/*Se realizaron ajustes acorde a la nomenclatura modificada
 * Se realiza la función que permite mostrar solo el total sin detalle
 * No se prueba xq falta bd
 */
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.DAOs.DAOConsumo;
import com.DAOs.DAOTernera;
import com.cliente.EJBLocator;
import com.excepciones.TamboException;
import com.models.Consumo;
import com.models.Ternera;
import com.services.TamboBeanRemote;

import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class FrameCostoAlimentacion {

	private JFrame frmAnalisisAlimentacion;
	private JTextField txtBuscarTerneraID;
	private JTable table;
	private JTextField txtTotal;

	@EJB DAOConsumo daoConsumo;
	@EJB DAOTernera daoTernera;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameCostoAlimentacion window = new FrameCostoAlimentacion();
					window.frmAnalisisAlimentacion.setVisible(true);
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
	public FrameCostoAlimentacion() {
		initialize();
		daoTernera=new DAOTernera();
		daoConsumo=new DAOConsumo();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException
	 */
	private void initialize() {
		frmAnalisisAlimentacion = new JFrame();
		frmAnalisisAlimentacion.setResizable(false);
		frmAnalisisAlimentacion.setIconImage(Toolkit.getDefaultToolkit().getImage(FrameCostoAlimentacion.class.getResource("/pic/logoCS.jpeg")));
		frmAnalisisAlimentacion.setTitle("Costo de alimentaci\u00F3n por ternera");
		frmAnalisisAlimentacion.setBounds(100, 100, 461, 421);
		frmAnalisisAlimentacion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAnalisisAlimentacion.getContentPane().setLayout(null);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FrameInicio frmInicio = new FrameInicio();
				frmAnalisisAlimentacion.dispose();
			}
		});
		btnVolver.setToolTipText("Regresar a la pantalla anterior");
		btnVolver.setBounds(331, 50, 89, 23);
		frmAnalisisAlimentacion.getContentPane().add(btnVolver);

		JLabel lblCostoAlimentacion = new JLabel("Por favor, ingrese el identificador de la ternera.");
		lblCostoAlimentacion.setBounds(24, 26, 375, 14);
		frmAnalisisAlimentacion.getContentPane().add(lblCostoAlimentacion);

		txtBuscarTerneraID = new JTextField();
		txtBuscarTerneraID.setToolTipText("Buscar ternera por identificador .");
		txtBuscarTerneraID.setColumns(10);
		txtBuscarTerneraID.setBounds(24, 51, 152, 20);
		frmAnalisisAlimentacion.getContentPane().add(txtBuscarTerneraID);

		JButton btnGenerarInforme = new JButton("Generar informe");

		btnGenerarInforme.setToolTipText("Presionar para generar el informe de costo de alimentaci\u00F3n de la ternera.");
		btnGenerarInforme.setBounds(186, 50, 135, 23);
		frmAnalisisAlimentacion.getContentPane().add(btnGenerarInforme);

		final JCheckBox cboxSinDetalle = new JCheckBox("Informe sin detalle");
		cboxSinDetalle.setToolTipText("Seleccione para obtener solamente el costo total de alimentaci\u00F3n.");
		cboxSinDetalle.setBounds(24, 78, 152, 23);
		frmAnalisisAlimentacion.getContentPane().add(cboxSinDetalle);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setToolTipText("Detalle de los costos de alimentaci\u00F3n de la ternera seleccionada.");
		scrollPane.setBounds(59, 108, 340, 226);
		frmAnalisisAlimentacion.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JLabel lblTotalCostoAlimentacin = new JLabel("TOTAL COSTO ALIMENTACI\u00D3N");
		lblTotalCostoAlimentacin.setBounds(69, 341, 194, 14);
		frmAnalisisAlimentacion.getContentPane().add(lblTotalCostoAlimentacin);

		txtTotal = new JTextField();
		txtTotal.setToolTipText("Muestra el costo total de alimentaci\u00F3n de la ternera seleccionada.");
		txtTotal.setEditable(false);
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.setBounds(276, 338, 123, 20);
		frmAnalisisAlimentacion.getContentPane().add(txtTotal);
		txtTotal.setColumns(10);
		frmAnalisisAlimentacion.setVisible(true);

		btnGenerarInforme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TamboBeanRemote tamboBean = null;
				Long idTernera = null;
				Ternera terneraPorId = null;

				if(txtBuscarTerneraID.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "El número de identificación no puede estar vacío");
					txtBuscarTerneraID.getFocusListeners();
				}
				else{
					try {
						idTernera = Long.parseLong(txtBuscarTerneraID.getText());
					} catch (NumberFormatException e2) {
						e2.printStackTrace();
					} 
					if(idTernera==null){
						JOptionPane.showMessageDialog(null, "El número de identificación solo puede contener números(0000)");
						txtBuscarTerneraID.setText("");
						txtBuscarTerneraID.getFocusListeners();
					}
					else{
						try {
							tamboBean = EJBLocator.getInstance().lookup(TamboBeanRemote.class);
							terneraPorId = tamboBean.buscarTerneraPorIdViva(idTernera);
							if(terneraPorId == null)
							{
								JOptionPane.showMessageDialog(null, "No se econtró la ternera por id (ternera desconocida)");
							}
							else if (terneraPorId!=null)
							{
								JOptionPane.showMessageDialog(null, "Ternera encontrada por Número de Identificador");
							}
							//id = tamboBean.buscarMaxId();
						} catch (NamingException e1) {
							e1.printStackTrace();
						} catch (TamboException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage(), "Informe Costo de Alimentación",
									JOptionPane.INFORMATION_MESSAGE);
						}
						if(cboxSinDetalle.isSelected()==false)
						{
							LinkedList<Consumo> consumos = null;
							try {
								consumos = tamboBean.consumoPorTernera(terneraPorId);
							} catch (TamboException e1) {
								e1.printStackTrace();
							}

							String[] nombreColumnas = { "DÍA", "ALIMENTO", "CANTIDAD", "COSTO" };

							Object[][] datos = new Object[consumos.size()][4];


							/* Cargamos la matriz */
							int fila = 0;

							SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");

							for (Consumo c : consumos) {

								datos[fila][0] = formateadorFecha.format(c.getFechaConsumo().getTime());
								datos[fila][1] = c.getAlimento().getNombre();
								datos[fila][2] = c.getCantidad();
								datos[fila][3] = c.getAlimento().getCostoPorUnidad()*c.getCantidad();
								fila++;

							}

							double total=0;
							LinkedList<Consumo> consumos2 = null;
							try {
								consumos2 = tamboBean.consumoPorTernera(terneraPorId);
							} catch (TamboException e1) {
								e1.printStackTrace();
							}
							for(Consumo con: consumos2){
								double costo = con.getAlimento().getCostoPorUnidad()* con.getCantidad();
								total += costo;
							}
							String tot = new Double(total).toString();
							txtTotal.setText(tot);

							/*
							 * Este codigo indica que las celdas no son editables y que son todas
							 * del tipos String
							 */
							DefaultTableModel model = new DefaultTableModel(datos, nombreColumnas) {

								@Override
								public boolean isCellEditable(int row, int column) {
									return false;
								}

								@Override
								public Class<?> getColumnClass(int columnIndex) {
									return String.class;
								}
							};

							table.setModel(model);
						}


						else if(cboxSinDetalle.isSelected()){
							table.setVisible(false);
							double total=0;
							LinkedList<Consumo> consumos = null;
							try {
								consumos = tamboBean.consumoPorTernera(terneraPorId);
							} catch (TamboException e1) {
								e1.printStackTrace();
							}
							for(Consumo con: consumos){
								double costo = con.getAlimento().getCostoPorUnidad()* con.getCantidad();
								total += costo;
							}
							String tot = new Double(total).toString();
							txtTotal.setText(tot);
						}			
					}
				}
			}
		});
	}
}