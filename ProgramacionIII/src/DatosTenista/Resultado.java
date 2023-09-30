package DatosTenista;

import java.util.ArrayList;
import java.util.Arrays;

/** Clase de gestión de resultado de grand slam
 */
public class Resultado extends DatoTabular {
	
	private static final ArrayList<String> CABECERAS;
	private static final ArrayList<Class<?>> TIPOS;
	static {
		CABECERAS = new ArrayList<>( Arrays.asList( "Year", "Major#", "Champion", "Seed_Champion", "Ctry_Champion", "Runner-up", "Seed_Runner-up", "Ctry_Runner-up", "Score in the Final" ) );
		TIPOS = new ArrayList<>( Arrays.asList( Integer.class, Integer.class, String.class, Integer.class, String.class, String.class, Integer.class, String.class, String.class ) );
	}
	
	// Construye resultado partiendo de un dato si tiene el siguiente formato:
	
	/** Crea un nuevo resultado 
	 * @param dato	Dato del que partir para crear el resultado
	 *            Debe tener el siguiente formato: Year (int), Major# (int), Champion (string), Seed_Champion (int), Ctry_Champion (string), Runner-up (string), Seed_Runner-up (int), Ctry_Runner-up (string), Score in the Final (string)
	 * @throws DatoException	Si hay error en algún valor del dato de partida
	 */
	public Resultado( DatoTabular dato ) throws DatoException {
		super( dato.lCabeceras, dato.lTipos, dato.lValores );
		if (getNumCampos()!=9 || getTipo(0)!=Integer.class || getTipo(1)!=Integer.class || getTipo(2)!=String.class || getTipo(3)!=Integer.class || getTipo(4)!=String.class
				 || getTipo(5)!=String.class || getTipo(6)!=Integer.class || getTipo(7)!=String.class || getTipo(8)!=String.class) {
			throw new DatoException( "Error en tipos incorrectos de dato resultado" );
		}
	}
	
	/** Crea un nuevo resultado partiendo de toda su información detallada
	 * @param anyo	Año del resultado
	 * @param codTorneo	Código del torneo (1-4)
	 * @param ganador	Tenista ganador (nombre único)
	 * @param rankingGanador	Ranking en el torneo (cabeza de serie)
	 * @param paisGanador	País
	 * @param finalista	Tenista finalista (nombre único)
	 * @param rankingFinalista	Ranking en el torneo (cabeza de serie)
	 * @param paisFinalista	País
	 * @param resultadoEnLaFinal	Resultado en la final en formato habitual de resultado de tenis (juegosG-juegosF, juegosG-juegosF,...)  Por ejemplo "6-1, 2-6, 6-4, 7-6(7-3)"
	 */
	public Resultado( int anyo, int codTorneo, String ganador, int rankingGanador, String paisGanador, String finalista, int rankingFinalista, String paisFinalista, String resultadoEnLaFinal ) {
		super( CABECERAS, TIPOS, new ArrayList<Object>() );
		lValores.add( anyo );
		lValores.add( codTorneo );
		lValores.add( ganador );
		lValores.add( rankingGanador );
		lValores.add( paisGanador );
		lValores.add( finalista );
		lValores.add( rankingFinalista );
		lValores.add( paisFinalista );
		lValores.add( resultadoEnLaFinal );
	}
	
	/** Devuelve el año
	 * @return	Año del resultado
	 */
	public int getAnyo() {
		return ((Integer) getValor(0));
	}
	
	/** Devuelve el código
	 * @return	Código del torneo (1-4)
	 */
	public int getCodTorneo() {
		return ((Integer) getValor(1));
	}
	
	/** Devuelve el nombre del ganador
	 * @return	Nombre del tenista ganador
	 */
	public String getGanador() {
		return ((String) getValor(2));
	}
	 
	/** Devuelve el nº de cabeza de serie del ganador
	 * @return	Número de ranking en el torneo del tenista ganador
	 */
	public int getRankingGanador() {
		return ((Integer) getValor(3));
	}
	
	/** Devuelve el país del ganador
	 * @return	País del tenista ganador
	 */
	public String getPaisGanador() {
		return ((String) getValor(4));
	}
	
	/** Devuelve el nombre del finalista
	 * @return	Nombre del tenista finalista
	 */
	public String getFinalista() {
		return ((String) getValor(5));
	}
	
	/** Devuelve el nº de cabeza de serie del finalista
	 * @return	Número de ranking en el torneo del tenista finalista
	 */
	public int getRankingFinalista() {
		return ((Integer) getValor(6));
	}
	
	/** Devuelve el país del finalista
	 * @return	País del tenista finalista
	 */
	public String getPaisFinalista() {
		return ((String) getValor(7));
	}

	/** Devuelve el resultado
	 * @return	Resultado de la final, en formato habitual de resultado de tenis, por ejemplo "6-1, 2-6, 6-4, 7-6(7-3)"
	 */
	public String getResultado() {
		return ((String) getValor(8));
	}
	
}
