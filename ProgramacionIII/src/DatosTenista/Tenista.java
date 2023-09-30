package DatosTenista;

import java.util.ArrayList;
import java.util.Arrays;

/** Clase de datos de tenista
 */
public class Tenista extends DatoTabular {
	
	private static ArrayList<String> cabeceras;
	private static ArrayList<Class<?>> tipos;
	static {
		cabeceras = new ArrayList<>( Arrays.asList( "Nombre", "Nacionalidad", "Num. victorias GS" ) );
		tipos = new ArrayList<>( Arrays.asList( String.class, String.class, Integer.class ) );
	}

	/** Crea un nuevo tenista partiendo de un dato
	 * @param dato	Dato de origen, debe tener el siguiente formato: Nombre (string), Nacionalidad (string), Nº victorias (int)
	 * @throws DatoException	Si hay error en alguno de los datos
	 */
	public Tenista( DatoTabular dato ) throws DatoException {
		super( dato.lCabeceras, dato.lTipos, dato.lValores );
		if (getNumCampos()!=3 || getTipo(0)!=String.class || getTipo(1)!=String.class || getTipo(2)!=Integer.class) {
			throw new DatoException( "Error en tipos incorrectos de dato tenista" );
		}
	}
	
	/** Crea un nuevo tenista
	 * @param nombre	Nombre del tenista (único)
	 * @param nacionalidad	País del tenista
	 */
	public Tenista( String nombre, String nacionalidad ) {
		super( cabeceras, tipos, new ArrayList<Object>() );
		lValores.add( nombre );
		lValores.add( nacionalidad );
		lValores.add( new Integer( 0 ) );
	}
	
	/** Devuelve el nombre
	 * @return	Nombre del tenista
	 */
	public String getNombre() {
		return ((String) getValor(0));
	}
	
	/** Devuelve la nacionalidad
	 * @return	País del tenista
	 */
	public String getNacionalidad() {
		return ((String) getValor(1));
	}
	
	/** Devuelve las victorias
	 * @return	Número de victorias del tenista en grand slams
	 */
	public int getVictoriasGrandSlam() {
		return ((Integer) getValor(2));
	}
	
	/** Reinicia las victorias del tenista a 0
	 */
	public void resetVictoriasGrandSlam() {
		setValor( 2, new Integer(0) );
	}
	
	/** Incrementa en uno el número de victorias del tenista
	 */
	public void incVictoriasGrandSlam() {
		setValor( 2, (1 + (Integer) getValor( 2 )) );
	}
	
}
