package DatosTenista;

import java.util.ArrayList;
import java.util.Comparator;
import java.awt.*;
import javax.swing.*;

/** Ventana de clasificación gráfica (barras) de los mejores tenistas en torneos ganados Grand Slam
 */
@SuppressWarnings("serial")
public class VentanaClasificacion extends JFrame {
	// Datos
	private HistoriaGrandSlams historia;
	private int anyoIni;
	private int anyoFin;
	private int anyoActual;
	private ArrayList<Tenista> clasifTenistas;
	// Componentes
	private JPanel pDibujo;
	
	/** Crea una ventana de clasificación de los mejores tenistas en grand slams
	 * @param historia	Historia completa de resultados de la que partir para el gráfico
	 * @param anyoInicio	Año inicial (inclusive) a tomar en cuenta para el conteo de victorias en grand slams
	 * @param anyoFinal	Año final (inclusive) a considerar para el conteo
	 */
	public VentanaClasificacion( HistoriaGrandSlams historia, int anyoInicio, int anyoFinal ) {
		this.historia = historia;
		this.anyoIni = anyoInicio;
		this.anyoFin = anyoFinal;
		// Configuración ventana
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setTitle( "Visualización de clasificación GS entre " + anyoIni + " y " + anyoFin );
		setSize( 700, 400 );
		setLocationRelativeTo( null );  // Centra en pantalla
		// Paneles internos
		pDibujo = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (clasifTenistas==null || clasifTenistas.size()<10) return;
				int yDibujo = 40;  // Pixels de dibujado en y (se incrementa por cada tenista)
				int xNombre = 10;  // Pixel x de inicio de nombre
				int xBarra = 140;  // Pixel x de inicio de barra
				g.drawString( "Año "+anyoActual, 200, 25 );
				for (int i=0; i<10; i++) {
					g.setColor( Color.BLUE );
					Tenista tenista = clasifTenistas.get(i);
					g.drawString( tenista.getNombre(), xNombre, yDibujo+20 );
					g.fillRect( xBarra, yDibujo, 25*tenista.getVictoriasGrandSlam(), 25 );
					g.setColor( Color.YELLOW );
					g.drawString( "" + tenista.getVictoriasGrandSlam(), xBarra+3, yDibujo+20 );
					yDibujo += 30;
				}
			}
		};
		pDibujo.setBackground( Color.white );
		add( pDibujo, BorderLayout.CENTER );
	}
	
	/** Dibuja en la ventana una evolución progresiva año a año la clasificación de mejores tenistas en grand slams, avanzando un año cada tiempo indicado
	 * @param milisEntreAnyos	Milisegundos de pausa de dibujo entre año y año
	 */
	public void dibuja( final long milisEntreAnyos ) {
		Thread hilo = new Thread() {
			@Override
			public void run() {
				initClasif();
				for (int anyo=anyoIni; anyo<=anyoFin; anyo++) {
					anyadeAnyoAClasif( clasifTenistas, anyo );
					try {
						Thread.sleep( milisEntreAnyos );
					} catch (InterruptedException e) {
					}
					pDibujo.repaint();
				}
			}
		};
		hilo.start();
	}

	// Crea una clasificación nueva a cero victorias de todos los tenistas
	private void initClasif() {
		clasifTenistas = new ArrayList<>();
		for (Tenista tenista : historia.getMapaTenistas().values()) {
			clasifTenistas.add( new Tenista( tenista.getNombre(), tenista.getNacionalidad() ) );  // Creamos tenistas nuevos para empezar la clasificación desde 0
		}
		anyoActual = anyoIni - 1;
	}

	// Añade a la clasificación el año indicado y reordena de acuerdo al acumulado
	private void anyadeAnyoAClasif( ArrayList<Tenista> clasif, int anyo ) {
		anyoActual = anyo;
		for (Resultado resultado : historia.getListaResultados()) {
			if (resultado.getAnyo()==anyo) {
				for (Tenista tenista : clasif) {
					if (tenista.getNombre().equals( resultado.getGanador() )) {
						tenista.incVictoriasGrandSlam();
						break;
					}
				}
			}
		}
		clasif.sort( new Comparator<Tenista>() {
			@Override
			public int compare(Tenista t1, Tenista t2) {
				int comp = t2.getVictoriasGrandSlam() - t1.getVictoriasGrandSlam();
				if (comp==0) {
					comp = t1.getNombre().compareTo( t2.getNombre() );
				}
				return comp;
			}
		});
	}
	
}
