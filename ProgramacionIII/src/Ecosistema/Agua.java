package Ecosistema;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

/** Clase para instanciar elementos de ecosistema de tipo agua
 * @author Andoni Egu�luz Mor�n
 * Facultad de Ingenier�a - Universidad de Deusto
 */
public class Agua extends ElementoEcosistema {
	protected long cantidad;  // Cantidad de agua (en hectolitros)
	
	/** Constructor de agua. La inicializa con una cantidad = ra�z cuadrada de anchura * altura
	 * @param titulo	T�tulo (nombre)
	 * @param x	Coordenada x
	 * @param y	Coordenada y
	 * @param anch	Anchura (en p�xels)
	 * @param alt	Altura (en p�xels)
	 */
	public Agua( String titulo, int x, int y, int anch, int alt ) {
		this.titulo = titulo;
		cantidad = (long) (Math.sqrt( anch * alt )); 
		posicion = new Point( x, y );
		dimension = new Dimension( anch, alt );
	}
	
	private JLabel lCantidad = new JLabel("", JLabel.CENTER);   // Label adicional para el panel de agua
	@Override
	public JPanel getPanel() {
		if (miPanel == null) {
			miPanel = new JPanel();
			miPanel.setLayout( new BorderLayout() );
			miPanel.add( lTitulo, BorderLayout.NORTH );
			miPanel.add( lCantidad, BorderLayout.CENTER );
			miPanel.add( new JLabel("Agua", JLabel.CENTER), BorderLayout.SOUTH );
			lCantidad.setText( ""+cantidad );
			lTitulo.setText( titulo );
			miPanel.setLocation( posicion );
			miPanel.setSize( dimension );
			miPanel.setBackground( Color.blue );
		}
		return miPanel;
	}
	
	/** Devuelve la cantidad de agua en hectolitros
	 * @return	cantidad de agua
	 */
	public long getCantidad() {
		return cantidad;
	}

	/** Modifica la cantidad de agua 
	 * @param cantidad	hectolitros de agua
	 */
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString(){
		return "Agua: " + titulo + " - " + cantidad + " metros c�bicos - Coord (" + posicion.x + "," + posicion.y + ") - Tama�o (" + dimension.width + "," + dimension.height + ")";
	}

}

