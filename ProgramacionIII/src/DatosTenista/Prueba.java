package DatosTenista;

import java.io.File;
import java.util.ArrayList;

/** Clase de prueba de gestión de datos en csv
 */
public class Prueba {

	public static void main(String[] args) {
		System.out.println( "Prueba lectura csv: ");
		ArrayList<DatoTabular> l = DatoTabular.cargaCsv( new File( "src/ud/prog3/pr00/datos/Grand Slam Championships, Champion vs Runner-up, Men's Singles, 1968-2021.csv") );
		for (DatoTabular dato : l) {
			System.out.println( dato );
		}
		System.out.println( "Prueba escritura csv (ver fichero salida.csv)" );
		DatoTabular.guardaCsv( l, new File( "salida.csv") );
		
		System.out.println( "Prueba lectura torneos: ");
		l = DatoTabular.cargaCsv( new File( "src/ud/prog3/pr00/datos/torneos.csv") );
		for (DatoTabular dato : l) {
			System.out.println( dato );
		}
	}
	
}
