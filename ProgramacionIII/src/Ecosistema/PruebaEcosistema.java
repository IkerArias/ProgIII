package Ecosistema;

public class PruebaEcosistema {

		/** M�todo principal.
		 * @param args	No utilizado
		 */
		public static void main(String[] args) {
			Agua agua = new Agua( "Lago Ontario", 100, 100, 200, 100 );
			ColoniaAbejas abejas = new ColoniaAbejas( "Colmena 1", 300, 200, 150, 50 );
			PlantacionFlores flores = new PlantacionFlores( "Rosal", 500, 300, 100, 100 );
			Ecosistema.getMundo().getElementos().add( agua );
			Ecosistema.getMundo().getElementos().add( abejas );
			Ecosistema.getMundo().getElementos().add( flores );
			int dia=0;
			while (true) {
				System.out.println( "D�a " + dia );
				for (ElementoEcosistema ee : Ecosistema.getMundo().getElementos()) {
					System.out.println( "  " + ee );
				}
				for (ElementoEcosistema ee : Ecosistema.getMundo().getElementos()) {
					if (ee instanceof Evolucionable) ((Evolucionable) ee).evoluciona( 1 );
				}
				dia++;
				try { Thread.sleep( 1000 ); } catch (Exception e) {}
			}
		}

	}
