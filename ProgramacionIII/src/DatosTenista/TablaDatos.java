package DatosTenista;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/** Clase para generar JTables especializadas en tablas de datos partiendo de objetos de tipo DatoTabular 
 */
@SuppressWarnings("serial")
public class TablaDatos extends JTable {
	private ArrayList<DatoTabular> datosGuardados;
	
	/** Crea una tabla de datos nueva, partiendo de una lista de datos
	 * @param listaDatos	Lista de datos a incluir en la tabla
	 */
	public TablaDatos( List<? extends DatoTabular> listaDatos ) {
		setListaDatos( listaDatos );
	}
	
	/** Crea una tabla de datos nueva, partiendo de un mapa de datos (valores)
	 * @param mapaDatos	Mapa de valores datos a incluir en la tabla (en el orden en el que estén en el mapa, si lo tiene)
	 */
	public TablaDatos( Map<? extends Object,? extends DatoTabular> mapaDatos ) {
		setMapaDatos( mapaDatos );
	}
	
	/** Modifica los datos de la tabla
	 * @param listaDatos	Lista de nuevos datos a incluir en la tabla (si hubiera datos anteriores, se eliminan previamente)
	 */
	public void setListaDatos( List<? extends DatoTabular> listaDatos ) {
		datosGuardados = new ArrayList<>();
		DefaultTableModel modelo = new DefaultTableModel( listaDatos.get(0).getCabeceras().toArray( new Object[0] ), 0 );
		for (DatoTabular dato : listaDatos) {
			modelo.addRow( dato.getValores().toArray( new Object[0] ) );
			datosGuardados.add( dato );
		}
		setModel( modelo );
	}
	
	/** Modifica los datos de la tabla
	 * @param mapaDatos	Mapa de nuevos datos (valores) a incluir en la tabla (si hubiera datos anteriores, se eliminan previamente)
	 */
	public void setMapaDatos( Map<? extends Object,? extends DatoTabular> mapaDatos ) {
		datosGuardados = new ArrayList<>();
		DatoTabular datoCualquiera = null;
		for (DatoTabular dato : mapaDatos.values()) {
			datoCualquiera = dato;  // Coge el primero (vale cualquiera porque todos tienen las mismas cabeceras)
			break;
		}
		DefaultTableModel modelo = new DefaultTableModel( datoCualquiera.getCabeceras().toArray( new Object[0] ), 0 );
		for (Object clave : mapaDatos.keySet()) {
			DatoTabular dato = mapaDatos.get(clave);
			modelo.addRow( dato.getValores().toArray( new Object[0] ) );
			datosGuardados.add( dato );
		}
		setModel( modelo );
	}
	
	/** Reordena los datos de la tabla de acuerdo a un comparador
	 * @param comp	Comparador a utilizar para el nuevo orden
	 */
	public void reordenar( Comparator<DatoTabular> comp ) {
		DefaultTableModel modelo = (DefaultTableModel) getModel();
		while (modelo.getRowCount()>0) {
			modelo.removeRow(0);
		}
		datosGuardados.sort( comp );
		for (DatoTabular dato : datosGuardados) {
			modelo.addRow( dato.getValores().toArray( new Object[0] ) );
		}
	}
	
}
