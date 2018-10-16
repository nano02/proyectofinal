package interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.DAOs.DAOCuadroClinico;
import com.cliente.EJBLocator;
import com.excepciones.TamboException;
import com.models.CuadroClinico;
import com.services.TamboBeanRemote;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class FrameDiaEventoClinico {

	private JFrame frmDiaEvento;
	private JTable table;

	@EJB DAOCuadroClinico daoCuadroClinico;
	/**
	 * Launch the application
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameDiaEventoClinico window = new FrameDiaEventoClinico();
					window.frmDiaEvento.setVisible(true);
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
	public FrameDiaEventoClinico() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() {
		frmDiaEvento = new JFrame();
		frmDiaEvento.setResizable(false);
		frmDiaEvento.setIconImage(Toolkit.getDefaultToolkit().getImage(FrameDiaEventoClinico.class.getResource("/pic/logoCS.jpeg")));

		frmDiaEvento.setTitle("Informe del d\u00EDa de vida en el que ocurre el evento cl\u00EDnico");
		frmDiaEvento.setBounds(100, 100, 545, 354);
		frmDiaEvento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane();

		JButton btnGenerarInforme = new JButton("GENERAR INFORME");
		btnGenerarInforme.setToolTipText("Presione para generar el informe de d\u00EDa de vida de los eventos cl\u00EDnicos.");

		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FrameInicio inicio = new FrameInicio();
				frmDiaEvento.dispose();
			}
		});

		GroupLayout groupLayout = new GroupLayout(frmDiaEvento.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(196)
							.addComponent(btnGenerarInforme))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(220)
							.addComponent(btnVolver))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 477, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(btnGenerarInforme)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnVolver)
					.addContainerGap())
		);

		table = new JTable();
		scrollPane.setViewportView(table);
		frmDiaEvento.getContentPane().setLayout(groupLayout);
		frmDiaEvento.setVisible(true);

		btnGenerarInforme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");
				LinkedList<CuadroClinico> cuadroClinicos = null;
				TamboBeanRemote tamboBean = null;
				try {
					tamboBean = EJBLocator.getInstance().lookup(TamboBeanRemote.class);
					cuadroClinicos = tamboBean.diaEvento();
				} catch (NamingException e2) {
					e2.printStackTrace();
				} catch (TamboException e1) {
					e1.printStackTrace();
				}

				String[] nombreColumnas = { "ID TERNERA", "FECHA INICIO", "FECHA FIN", "FECHA DE NACIMIENTO", "DIA DE VIDA" };

				Object[][] datos = new Object[cuadroClinicos.size()][5];

				/* Cargamos la matriz */
				int fila = 0;

				//SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");
				//formateadorFecha.format(c.getFechaInicio());

				for (CuadroClinico c : cuadroClinicos) {

					datos[fila][0] = c.getTernera().getIdTernera();
					datos[fila][1] = formateadorFecha.format(c.getFechaInicio());
					datos[fila][2] = formateadorFecha.format(c.getFechaFin());
					datos[fila][3] = formateadorFecha.format(c.getTernera().getFechaNacimiento());
					datos[fila][4] = c.calcularDiaEvento(c.getTernera().getFechaNacimiento(), c.getFechaInicio());
					fila++;

				}

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

		});


	}
}

