package DatosTenista;

import java.io.File;

/** Programa principal gestión resultados grand slams
 */
public class Main {
	/** Método principal - carga los datos de ficheros por defecto y lanza la ventana de visualización de tablas de datos
	 * Ficheros por defecto:
	 *  Resultados: src/ud/prog3/pr00/datos/Grand Slam Championships, Champion vs Runner-up, Men's Singles, 1968-2021.csv
	 *   (Si existe el fichero resultados.csv en la carpeta por defecto, se carga en su lugar)
	 *	Torneos: src/ud/prog3/pr00/datos/torneos.csv
	 * @param args	No utilizado
	 */
	public static void main(String[] args) {
		File fResultados = new File( "resultados.csv");
		if (!fResultados.exists()) {  // Si no hay un fichero nuevo guardado, se usa el inicial
			fResultados = new File( "src/ud/prog3/pr00/datos/Grand Slam Championships, Champion vs Runner-up, Men's Singles, 1968-2021.csv" );
		}
		HistoriaGrandSlams hgs = new HistoriaGrandSlams( new File( "src/ud/prog3/pr00/datos/torneos.csv"), fResultados );
		VentanaGrandSlam v = new VentanaGrandSlam( hgs );
		v.setVisible( true );
	}
}
