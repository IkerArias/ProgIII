package Ecosistema;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlantacionFlores extends ElementoEcosistema implements Evolucionable {
	protected long cantidad;  // Cantidad de flores (en unidades)
	
	/** Constructor de plantaci�n de flores. La inicializa con una cantidad = ra�z cuadrada de anchura * altura
	 * @param titulo	T�tulo (nombre)
	 * @param x	Coordenada x
	 * @param y	Coordenada y
	 * @param anch	Anchura (en p�xels)
	 * @param alt	Altura (en p�xels)
	 */
	public PlantacionFlores( String titulo, int x, int y, int anch, int alt ) {
		this.titulo = titulo;
		cantidad = (long) (Math.sqrt( anch * alt )); 
		posicion = new Point( x, y );
		dimension = new Dimension( anch, alt );
	}
	
	private JLabel lCantidad = new JLabel("", JLabel.CENTER);   // Label adicional para el panel de flores
	@Override
	public JPanel getPanel() {
		if (miPanel == null) {
			miPanel = new JPanel();
			miPanel.setLayout( new BorderLayout() );
			miPanel.add( lTitulo, BorderLayout.NORTH );
			miPanel.add( lCantidad, BorderLayout.CENTER );
			miPanel.add( new JLabel("Flores", JLabel.CENTER), BorderLayout.SOUTH );
			lCantidad.setText( ""+cantidad );
			lTitulo.setText( titulo );
			miPanel.setLocation( posicion );
			miPanel.setSize( dimension );
			miPanel.setBackground( Color.green );
		}
		return miPanel;
	}
	
	/** Devuelve la cantidad de flores existentes
	 * @return	cantidad de agua
	 */
	public long getCantidad() {
		return cantidad;
	}

	/** Modifica la cantidad de flores
	 * @param cantidad	Nuevo n�mero de flores
	 */
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public void evoluciona(int dias) {
		double factorCrecimiento = 0.75;
		for (ElementoEcosistema ee : Ecosistema.getMundo().getElementos()) {
			int dist = Ecosistema.distancia( this, ee );
			if (ee instanceof ColoniaAbejas) {  // La cercan�a de una colonia de abejas beneficia
				if (dist < 500) factorCrecimiento = factorCrecimiento / dist * 500;
			} else if (ee instanceof Agua) {  // La cercan�a de agua beneficia
				if (dist < 500) factorCrecimiento = factorCrecimiento / dist * 500;
			}
		}
		cantidad = (long) (cantidad * factorCrecimiento * dias);
		if (cantidad > 5000) cantidad = 5000;
		lCantidad.setText( "" + cantidad );
	}
	
	@Override
	public String toString(){
		return "Flores: " + titulo + " - " + cantidad + " uds. - Coord (" + posicion.x + "," + posicion.y + ") - Tama�o (" + dimension.width + "," + dimension.height + ")";
	}

}
