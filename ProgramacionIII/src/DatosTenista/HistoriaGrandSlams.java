package DatosTenista;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/** Clase contenedora de datos históricos de grand slams
 * Suponemos que no se repite nunca el nombre en dos tenistas diferentes
 */
public class HistoriaGrandSlams {

	private ArrayList<Resultado> lResultados; // Lista de todos los resultados históricos
	private HashMap<String,Torneo> mapaTorneosNombre; // Mapa de todos los torneos, con clave nombre del torneo, para facilitar las búsquedas por torneo
	private HashMap<Integer,Torneo> mapaTorneosCod; // Otro mapa de todos los torneos con clave código de torneo (1-4), para facilitar las búsquedas por código de torneo
	private TreeMap<String,Tenista> mapaTenistas; // Mapa de tenistas, con clave nombre del tenista, para poder buscar rápido por el nombre de cada tenista
	
	/** Crea un objeto de datos históricos, partiendo de los datos existentes en ficheros
	 * @param fTorneos	Fichero csv de torneos (debe existir y estar cargado con datos correctos)
	 * @param fResultados	Fichero csv de resultados (ídem)
	 */
	public HistoriaGrandSlams( File fTorneos, File fResultados ) {
		lResultados = new ArrayList<>();
		mapaTorneosNombre = new HashMap<>();
		mapaTorneosCod = new HashMap<>();
		mapaTenistas = new TreeMap<>();
		try {
			// Proceso de carga de torneos
			ArrayList<DatoTabular> l = DatoTabular.cargaCsv( fTorneos );
			for (DatoTabular dato : l) {
				Torneo torneo = new Torneo( dato );
				mapaTorneosCod.put( torneo.getCodigo(), torneo );
				mapaTorneosNombre.put( torneo.getNombre(), torneo );
			}
			// Proceso de carga de resultados
			l = DatoTabular.cargaCsv( fResultados );
			for (DatoTabular dato : l) {
				Resultado resultado = new Resultado( dato );
				anyadeResultado( resultado );
			}
		} catch (DatoException e) {
			System.err.println( "No se han podido cargar correctamente los datos de inicio.");
		}
	}
	
	/** Devuelve los resultados
	 * @return	Lista de resultados guardados
	 */
	public List<Resultado> getListaResultados() {
		return lResultados;
	}
	
	/** Devuelve los torneos
	 * @return	Mapa de torneos guardados
	 */
	public Map<Integer,Torneo> getMapaTorneos() {
		return mapaTorneosCod;
	}
	
	/** Devuelve los tenistas
	 * @return	Mapa de tenistas guardados, con clave única nombre de tenista y valor objeto tenista
	 */
	public TreeMap<String,Tenista> getMapaTenistas() {
		return mapaTenistas;
	}
	
	/** Añade un nuevo resultado a la estructura, incorporando a los tenistas si procede
	 * @param resultado	Resultado correcto a añadir
	 */
	public void anyadeResultado( Resultado resultado ) {
		// Almacenar tenistas en el mapa e incrementar contador ganadores
		anyadeTenista( resultado.getGanador(), resultado.getPaisGanador() );
		mapaTenistas.get(resultado.getGanador()).incVictoriasGrandSlam();
		anyadeTenista( resultado.getFinalista(), resultado.getPaisFinalista() );
		// Almacenar resultados
		lResultados.add( resultado );
	}
	
	/** Añade un nuevo tenista a la estructura. Si el tenista ya existía no se modifica nada.
	 * @param nombre	Nombre del tenista
	 * @param pais	País del tenista
	 */
	public void anyadeTenista( String nombre, String pais ) {
		if (!mapaTenistas.containsKey(nombre)) {
			mapaTenistas.put( nombre, new Tenista( nombre, pais ) );
		}
	}
	
	/** Calcula el número de victorias de los jugadores en grand slams entre los años dados
	 * @param anyoInicial	Año inicial (inclusive)
	 * @param anyoFinal	Año final (inclusive)
	 */
	public void calculaClasificacion( int anyoInicial, int anyoFinal ) {
		// Inicializamos contadores a 0
		for (Tenista tenista : mapaTenistas.values()) {
			tenista.resetVictoriasGrandSlam();
		}
		// Recalculamos contadores solo entre los años indicados
		for (Resultado resultado : lResultados) {
			if (resultado.getAnyo()>=anyoInicial && resultado.getAnyo()<=anyoFinal) {
				mapaTenistas.get( resultado.getGanador() ).incVictoriasGrandSlam();
			}
		}
	}
	
	/** Devuelve el primer año en el que hay resultados
	 * @return	Año inicial
	 */
	public int getAnyoInicial() {
		int ret = Integer.MAX_VALUE;
		for (Resultado resultado : lResultados) {
			if (resultado.getAnyo() < ret) {
				ret = resultado.getAnyo();
			}
		}
		return ret;
	}
	
	/** Devuelve el último año en el que hay resultados
	 * @return	Año final
	 */
	public int getAnyoFinal() {
		int ret = Integer.MIN_VALUE;
		for (Resultado resultado : lResultados) {
			if (resultado.getAnyo() > ret) {
				ret = resultado.getAnyo();
			}
		}
		return ret;
	}
		
}
