package DatosTenista;

import java.util.Comparator;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.table.TableModel;

import java.util.List;

/** Ventana de tablas de datos de los resultados y tenistas ganadores y finalistas en torneos de Grand Slam
 */
@SuppressWarnings("serial")
public class VentanaGrandSlam extends JFrame {
	// Datos
	private HistoriaGrandSlams historia;
	private TablaDatos tablaResultados;
	private TablaDatos tablaTenistas;
	// Componentes
	private JTabbedPane panelPestanyas;
	private JPanel pTenistas;

	/** Crea una ventana de tablas de datos de grand slams
	 * @param historia	Historia completa de resultados de la que partir para las tablas de datos
	 */
	public VentanaGrandSlam( HistoriaGrandSlams historia ) {
		this.historia = historia;
		// Configuración ventana
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setTitle( "Datos de Grand Slams" );
		setSize( 1000, 800 );
		setLocationRelativeTo( null );  // Centra en pantalla
		// Paneles internos
		JPanel pResultados = new JPanel( new BorderLayout() );
		pTenistas = new JPanel( new BorderLayout() );
		JPanel pTorneos = new JPanel( new BorderLayout() );
		panelPestanyas = new JTabbedPane( JTabbedPane.TOP );
		panelPestanyas.addTab( "Resultados", pResultados );
		panelPestanyas.addTab( "Tenistas", pTenistas );
		panelPestanyas.addTab( "Torneos", pTorneos );
		add( panelPestanyas, BorderLayout.CENTER );
		// Panel de resultados
		tablaResultados = new TablaDatos( historia.getListaResultados() );
		tablaResultados.setEnabled( false );  // Para que no se pueda editar
		pResultados.add( new JScrollPane( tablaResultados ), BorderLayout.CENTER );
		// Panel de tenistas
		recrearTablaTenistas();
		// Panel de torneos
		TablaDatos tablaTorneos = new TablaDatos( historia.getMapaTorneos() );
		tablaTorneos.setEnabled( false );
		pTorneos.add( new JScrollPane( tablaTorneos ), BorderLayout.CENTER );
		// Botoneras
		JPanel pBotoneraResultados = new JPanel( new FlowLayout( FlowLayout.RIGHT ) );
		JButton bAnyadir = new JButton( "Añadir" );
		JButton bGuardar = new JButton( "Guardar" );
		pBotoneraResultados.add( bAnyadir );
		pBotoneraResultados.add( bGuardar );
		pResultados.add( pBotoneraResultados, BorderLayout.SOUTH );
		JPanel pBotoneraPrincipal = new JPanel();
		JButton bEvolucion = new JButton( "Evolución ranking" );
		pBotoneraPrincipal.add( bEvolucion );
		add( pBotoneraPrincipal, BorderLayout.SOUTH );
		// Eventos
		bGuardar.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardar();
			}
		});
		bAnyadir.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				anyadirResultado();
			}
		});
		bEvolucion.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String resp = JOptionPane.showInputDialog( VentanaGrandSlam.this, "Año inicial para ver evolución de ranking:", "" + historia.getAnyoInicial() );
					if (resp==null) return;
					int anyoIni = Integer.parseInt( resp );
					resp = JOptionPane.showInputDialog( VentanaGrandSlam.this, "Año final para ver evolución de ranking:", "" + historia.getAnyoFinal() );
					if (resp==null) return;
					int anyoFin = Integer.parseInt( resp );
					VentanaClasificacion vc = new VentanaClasificacion( historia, anyoIni, anyoFin );
					vc.setVisible( true );
					vc.dibuja( 1000 );  // Dibuja con 1 sg de pausa entre años
				} catch (Exception ex) {
					JOptionPane.showMessageDialog( VentanaGrandSlam.this, "Error en entrada de datos: año incorrecto", "Error", JOptionPane.ERROR_MESSAGE );
				}
			}
		});
		KeyListener kl = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_N && e.isControlDown()) {
					anyadirResultado();
				} else if (e.getKeyCode()==KeyEvent.VK_S && e.isControlDown()) {
					guardar();
				}
			}
		};
		panelPestanyas.addKeyListener( kl );  // Añadimos el escuchador de Ctrl+N a todos los componentes focusables de ese contexto
		tablaResultados.addKeyListener( kl );
		bAnyadir.addKeyListener( kl );
		bGuardar.addKeyListener( kl );
		bEvolucion.addKeyListener( kl );
		tablaResultados.addMouseListener( new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()>1) {
					// System.out.println( "Doble click en " + tablaResultados.rowAtPoint( e.getPoint() ) + "," + tablaResultados.columnAtPoint( e.getPoint() ) );
					int fila = tablaResultados.rowAtPoint( e.getPoint() );
					int columna = tablaResultados.columnAtPoint( e.getPoint() );
					if (columna == 2 || columna == 5) {  // Columnas de los tenistas
						List<Resultado> l = historia.getListaResultados();
						String nomTenista = (columna==2) ? l.get(fila).getGanador() : l.get(fila).getFinalista();
						int cabezaDeSerie = (columna==2) ? l.get(fila).getRankingGanador() : l.get(fila).getRankingFinalista();
						Tenista tenista = historia.getMapaTenistas().get( nomTenista );
						TableModel modelo = tablaTenistas.getModel();
						int ranking = 0;
						for (int i = 0; i < modelo.getRowCount(); i++) {
							if (modelo.getValueAt(i, 0).equals(nomTenista)) {
								ranking = i+1;
								break;
							}
						}
						JOptionPane.showMessageDialog( VentanaGrandSlam.this, nomTenista + "\n" + "Cabeza de serie en este torneo: " + cabezaDeSerie + "\n" +
							"Actualmente es nº " + ranking + " en el ranking de grand slams\n" +
							"Habiendo ganado " + tenista.getVictoriasGrandSlam() );
					}
				}
			}
		});
	}

	// Guarda datos en csv
	private void guardar() {
		DatoTabular.guardaCsv( historia.getListaResultados(), new File( "resultados.csv" ) );
	}
	
	// Añade resultado nuevo pidiendo datos de forma interactiva al usuario
	private void anyadirResultado() {
		try {
			String resp = JOptionPane.showInputDialog( VentanaGrandSlam.this, "Año del torneo:", "Nuevo resultado", JOptionPane.QUESTION_MESSAGE );
			if (resp==null) return;
			int anyo = Integer.parseInt( resp );
			Object resp2 = JOptionPane.showInputDialog( VentanaGrandSlam.this, "Torneo:", "Nuevo resultado", JOptionPane.QUESTION_MESSAGE, null, historia.getMapaTorneos().values().toArray( new Object[0] ), null );
			if (resp2==null) return;
			Torneo t = (Torneo) resp2;
			int torneo = t.getCodigo();
			String nombreGanador = JOptionPane.showInputDialog( VentanaGrandSlam.this, "Tenista ganador/a:", "Nuevo resultado", JOptionPane.QUESTION_MESSAGE );
			if (nombreGanador==null) return;
			Tenista ganador = historia.getMapaTenistas().get( nombreGanador );
			String paisGanador = null;
			if (ganador==null) {  // Si el tenista no existía se pide su dato
				paisGanador = JOptionPane.showInputDialog( VentanaGrandSlam.this, "País de tenista ganador/a:", "Nuevo resultado", JOptionPane.QUESTION_MESSAGE );
				if (paisGanador==null) return;
			} else {
				paisGanador = ganador.getNacionalidad();
			}
			resp = JOptionPane.showInputDialog( VentanaGrandSlam.this, "Ranking de ganador/a:", "Nuevo resultado", JOptionPane.QUESTION_MESSAGE );
			if (resp==null) return;
			int rankingGanador = Integer.parseInt( resp );
			String nombreFinalista = JOptionPane.showInputDialog( VentanaGrandSlam.this, "Tenista finalista:", "Nuevo resultado", JOptionPane.QUESTION_MESSAGE );
			if (nombreFinalista==null) return;
			Tenista finalista = historia.getMapaTenistas().get( nombreFinalista );
			String paisFinalista = null;
			if (finalista==null) {  // Si el tenista no existía se pide su dato
				paisFinalista = JOptionPane.showInputDialog( VentanaGrandSlam.this, "País de tenista finalista:", "Nuevo resultado", JOptionPane.QUESTION_MESSAGE );
				if (paisFinalista==null) return;
			} else {
				paisFinalista = finalista.getNacionalidad();
			}
			resp = JOptionPane.showInputDialog( VentanaGrandSlam.this, "Ranking de finalista:", "Nuevo resultado", JOptionPane.QUESTION_MESSAGE );
			if (resp==null) return;
			int rankingFinalista = Integer.parseInt( resp );
			String resultado = JOptionPane.showInputDialog( VentanaGrandSlam.this, "Resultado del partido final:", "Nuevo resultado", JOptionPane.QUESTION_MESSAGE );
			if (resultado==null) return;
			Resultado res = new Resultado( anyo, torneo, nombreGanador, rankingGanador, paisGanador, nombreFinalista, rankingFinalista, paisFinalista, resultado );
			historia.anyadeResultado( res );
			tablaResultados.setListaDatos( historia.getListaResultados() );
			tablaTenistas.setMapaDatos( historia.getMapaTenistas() );
		} catch (Exception ex) {
			JOptionPane.showMessageDialog( VentanaGrandSlam.this, "Error en entrada de datos: dato incorrecto", "Error", JOptionPane.ERROR_MESSAGE );
		}
	}

	// Recalcula la tala de tenistas ordenándoles por victorias en grand slams
	private void recrearTablaTenistas() {
		tablaTenistas = new TablaDatos( historia.getMapaTenistas() );
		tablaTenistas.setEnabled( false );
		pTenistas.add( new JScrollPane( tablaTenistas ), BorderLayout.CENTER );
		tablaTenistas.reordenar( new Comparator<DatoTabular>() {
			@Override
			public int compare(DatoTabular o1, DatoTabular o2) {
				Tenista t1 = (Tenista) o1;
				Tenista t2 = (Tenista) o2;
				int comp = t2.getVictoriasGrandSlam() - t1.getVictoriasGrandSlam();
				if (comp==0) {
					comp = t1.getNombre().compareTo( t2.getNombre() );
				}
				return comp;
			}
		});
	}
}
