package DatosTenista;

/** Clase de torneo grand slam
 */
public class Torneo extends DatoTabular {

	/** Construye un nuevo torneo partiendo de un dato
	 * @param dato	Dato de origen. Debe incluir los siguientes tres datos en este orden: Código (int), Nombre (string), Ciudad (string)
	 * @throws DatoException	Si los datos no responden a los necesitados
	 */
	public Torneo( DatoTabular dato ) throws DatoException {
		super( dato.lCabeceras, dato.lTipos, dato.lValores );
		if (getNumCampos()!=3 || getTipo(0)!=Integer.class || getTipo(1)!=String.class || getTipo(2)!=String.class) {
			throw new DatoException( "Error en tipos incorrectos de dato torneo" );
		}
	}
	
	/** Devuelve el código
	 * @return	Código de torneo (1-4)
	 */
	public int getCodigo() {
		return ((Integer) getValor(0));
	}
	
	/** Devuelve el nombre
	 * @return	Nombre de torneo
	 */
	public String getNombre() {
		return ((String) getValor(1));
	}
	
	/** Devuelve la ciudad
	 * @return	Ciudad del torneo
	 */
	public String getCiudad() {
		return ((String) getValor(2));
	}

	@Override
	public String toString() {
		return getNombre();
	}
	
}
