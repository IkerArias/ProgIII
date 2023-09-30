package DatosTenista;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Clase de gestión de datos tabulares
 */
public class DatoTabular {
	
	/** Método de utilidad que carga un csv en una lista de datos tabulares, de acuerdo al formato de datos del csv
	 * FORMATO: El csv debe tener un dato por línea, separado por comas, con valores de tipo entero, doble (con punto decimal), o string (entre dobles comillas).
	 * La primera línea del csv debe ser la de cabeceras (nombres de cada dato)
	 * Todas las líneas deben tener el mismo número de datos y en el mismo orden
	 * @param ficheroCsv
	 * @return
	 */
	public static ArrayList<DatoTabular> cargaCsv( File ficheroCsv ) {
		ArrayList<DatoTabular> lista = new ArrayList<>();
		// try con autoclose - manera alternativa a un try normal de un recurso con close al final
		try (Scanner scanner = new Scanner(ficheroCsv)) {
			ArrayList<String> cabeceras = new ArrayList<>();
			ArrayList<Class<?>> tipos = new ArrayList<>();
			// Proceso de cabecera
			String lineaCabeceras = scanner.nextLine();  // Trabajamos con csv con línea de cabeceras
			String[] trozos = dividePorComas(lineaCabeceras);  // Trabajamos con csv separados por comas
			for (String trozo : trozos) {
				if (trozo.startsWith("\"") && trozo.endsWith("\"")) { // Quitamos comillas si vienen
					trozo = trozo.substring( 1, trozo.length()-1 );
				}
				cabeceras.add( trozo );
				tipos.add( Object.class );  // Partimos de tipo Object (luego indicaremos si encontramos enteros, strings o reales)
			}
			// Proceso de resto de líneas (un dato por cada línea)
			int numLinea = 1;
			while (scanner.hasNextLine()) {
				procesaLinea( numLinea, scanner, cabeceras, tipos, lista );
				numLinea++;
			}
		} catch (FileNotFoundException e) {
		}
		return lista;
	}
	
	private static void procesaLinea( int numLinea, Scanner scanner, ArrayList<String> cabeceras, ArrayList<Class<?>> tipos, ArrayList<DatoTabular> lista ) {
		String linea = scanner.nextLine();
		String[] trozos = dividePorComas(linea);  // Trabajamos con csv separados por comas
		ArrayList<Object> valores = new ArrayList<>();
		for (int numCampo=0; numCampo<trozos.length; numCampo++) {
			String trozo = trozos[numCampo];
			if (trozo.startsWith("\"") && trozo.endsWith("\"")) { // Quitamos comillas si vienen
				trozo = trozo.substring( 1, trozo.length()-1 );
				valores.add( trozo );  // Si viene entre comillas es un string seguro
				if (tipos.get(numCampo)==Object.class) { 
					tipos.set( numCampo, String.class ); 
				} else if (tipos.get(numCampo)!=String.class) {
					System.err.println( "Error en línea " + numLinea + ": Se ha encontrado valor de tipo string en campo " + numCampo + " cuando antes había " + tipos.get(numCampo) );
					return;
				}
			} else {
				try {
					int valorEntero = Integer.parseInt( trozo );
					valores.add( new Integer( valorEntero ) );
					if (tipos.get(numCampo)==Object.class) { 
						tipos.set( numCampo, Integer.class ); 
					} else if (tipos.get(numCampo)!=Integer.class) {
						System.err.println( "Error en línea " + numLinea + ": Se ha encontrado valor de tipo int en campo " + numCampo + " cuando antes había " + tipos.get(numCampo) );
						return;
					}
				} catch (NumberFormatException e) {
					try {
						double valorDoble = Double.parseDouble( trozo );
						valores.add( new Double( valorDoble ) );
						if (tipos.get(numCampo)==Object.class) { 
							tipos.set( numCampo, Double.class ); 
						} else if (tipos.get(numCampo)!=Double.class) {
							System.err.println( "Error en línea " + numLinea + ": Se ha encontrado valor de tipo double en campo " + numCampo + " cuando antes había " + tipos.get(numCampo) );
							return;
						}
					} catch (NumberFormatException e2) {  // Si no es ni entero ni doble, lo dejamos en String
						valores.add( trozo );
						if (tipos.get(numCampo)==Object.class) { 
							tipos.set( numCampo, String.class ); 
						} else if (tipos.get(numCampo)!=String.class) {
							System.err.println( "Error en línea " + numLinea + ": Se ha encontrado valor de tipo string en campo " + numCampo + " cuando antes había " + tipos.get(numCampo) );
							return;
						}
					}
				}
			}
		}
		DatoTabular nuevoDato = new DatoTabular( cabeceras, tipos, valores );
		lista.add( nuevoDato );
	}

	/** Divide un string utilizando comas, pero no divide si las comas están dentro de un string (empezado y acabado en dobles comillas)
	 * @param linea	String a dividir
	 * @return	Array de strings una vez separada la línea por comas (en el mismo orden en el que venían en la línea)
	 */
	public static String[] dividePorComas( String linea ) {
		ArrayList<String> listaTokens = new ArrayList<String>();
		boolean entreComillas = false;
		StringBuilder b = new StringBuilder();
		for (char c : linea.toCharArray()) {
		    if (c==',') {
		        if (entreComillas) {
		            b.append(c);
		        } else {
		            listaTokens.add( b.toString() );
		            b = new StringBuilder();
		        }
		    } else if (c=='\"') {
		        entreComillas = !entreComillas;
		    } else {
		        b.append(c);
		    }
		}
		listaTokens.add( b.toString() );
		return listaTokens.toArray( new String[0] );
	}
	
	// Observa como esta cabecera en lugar de esta: public static void guardaCsv( List<DatoTabular> listaDatos, File ficheroCsv ) {
	// permite que se pase cualquier una lista de cualquier tipo que herede a DatoTabular. Si no, habría que mover los datos a una lista nueva genérica antes de pasarla a este método
	/** Guarda un csv con los datos indicados
	 * @param listaDatos	Lista de datos a guardar
	 * @param ficheroCsv	Fichero csv en el que guardarlos
	 */
	public static void guardaCsv( List<? extends DatoTabular> listaDatos, File ficheroCsv ) {
		if (listaDatos.isEmpty()) return; // Nada que crear
		try (PrintStream ps = new PrintStream( ficheroCsv )) {
			for (int i=0; i<listaDatos.get(0).getCabeceras().size(); i++) {
				String cabecera = listaDatos.get(0).getCabeceras().get(i);
				ps.print( "\"" + cabecera + "\"" );
				if (i<listaDatos.get(0).getCabeceras().size()-1) {
					ps.print( "," );
				}
			}
			ps.println();
			for (DatoTabular dato : listaDatos) {
				String linea = "";
				for (int i=0; i<dato.getNumCampos(); i++) {
					if (dato.getTipo(i)==String.class) {
						linea += "\"" + dato.getValor(i) + "\"";
					} else {
						linea += dato.getValor(i);
					}
					if (i < dato.getNumCampos()-1) {
						linea += ",";
					}
				}
				ps.println( linea );
			}
		} catch (FileNotFoundException e) {
			System.err.println( "Fichero " + ficheroCsv + " no ha podido crearse." );
		}
	}
	
	protected ArrayList<String> lCabeceras;
	protected ArrayList<Class<?>> lTipos;
	protected ArrayList<Object> lValores;
	
	/** Crea un nuevo dato. Las tres listas deben tener el mismo tamaño y orden:
	 * @param lCabeceras	Lista de cabeceras
	 * @param lTipos	Lista de tipos
	 * @param lValores	Lista de valores
	 */
	public DatoTabular(ArrayList<String> lCabeceras, ArrayList<Class<?>> lTipos, ArrayList<Object> lValores) {
		super();
		this.lCabeceras = lCabeceras;
		this.lTipos = lTipos;
		this.lValores = lValores;
	}
	
	/** Devuelve el valor de uno de los campos
	 * @param cabecera	Cabecera del campo a devolver. Debe ser una cabecera válida, nombre existente entre las cabeceras definidas
	 * @return	Valor de ese campo
	 */
	public Object getValor( String cabecera ) {
		return getValor( getIndiceDeCampo(cabecera) );
	}
	
	/** Modifica el valor de uno de los campos
	 * @param cabecera	Cabecera del campo a devolver. Debe ser una cabecera válida, nombre existente entre las cabeceras definidas
	 * @param valor	Nuevo valor de ese campo
	 */
	public void setValor( String cabecera, Object valor ) {
		setValor( getIndiceDeCampo(cabecera), valor );
	}
	
	/** Devuelve el valor de uno de los campos
	 * @param numCampo	Índice del campo, debe estar entre 0 y n-1
	 * @return	Valor de ese campo
	 */
	public Object getValor( int numCampo ) {
		return lValores.get( numCampo );
	}
	
	/** Modifica el valor de uno de los campos
	 * @param numCampo	Índice del campo, debe estar entre 0 y n-1
	 * @param valor	Nuevo valor de ese campo
	 */
	public void setValor( int numCampo, Object valor ) {
		lValores.set( numCampo, valor );
	}
	
	/** Devuelve el tipo de uno de los campos
	 * @param numCampo	Índice del campo, debe estar entre 0 y n-1
	 * @return	Tipo de ese campo
	 */
	public Class<?> getTipo( int numCampo ) {
		return lTipos.get( numCampo );
	}
	
	/** Modifica el tipo de uno de los campos
	 * @param numCampo	Índice del campo, debe estar entre 0 y n-1
	 * @param tipo	Nuevo tipo de ese campo
	 */
	public void setTipo( int numCampo, Class<?> tipo ) {
		lTipos.set( numCampo, tipo );
	}
	
	/** Devuelve la cabecera de uno de los campos
	 * @param numCampo	Índice del campo, debe estar entre 0 y n-1
	 * @return	Cabecera de ese campo
	 */
	public String getCabecera( int numCampo ) {
		return lCabeceras.get( numCampo );
	}
	
	/** Devuelve las cabeceras
	 * @return	Lista de cabeceras en el orden en el que están los campos
	 */
	public List<String> getCabeceras() {
		return lCabeceras;
	}
	
	/** Devuelve los valores del dato tabular
	 * @return	Lista de valores en el orden en el que están los campos
	 */
	public List<Object> getValores() {
		return lValores;
	}
	
	/** Devuelve el índice de campo (de 0 a n-1)
	 * @param cabecera	Nombre de la cabecera del campo
	 * @return	Índice del campo, -1 si no existe
	 */
	public int getIndiceDeCampo( String cabecera ) {
		return lCabeceras.indexOf( cabecera );
	}
	
	/** Devuelve el número de campos
	 * @return	Número de campos existentes en el dato tabular
	 */
	public int getNumCampos() {
		return lCabeceras.size();
	}
	
	@Override
	public String toString() {
		String ret = "{";
		for (int i=0; i<lCabeceras.size(); i++) {
			ret += (lCabeceras.get(i) + "=" + "<"+lTipos.get(i).getSimpleName()+">"+ lValores.get(i));
			if (i<lCabeceras.size()-1) {
				ret += ", ";
			}
		}
		return ret + "}";
	}
}
