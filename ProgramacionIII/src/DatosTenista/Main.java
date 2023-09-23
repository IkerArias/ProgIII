package DatosTenista;

import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HistoriaGrandSlams historia = new HistoriaGrandSlams(null, null, null, null);
		
		historia.cargarTorneosDesdeCsv("/Users/ikerariasmartinez/Downloads/GrandSlam.csv");
	    historia.cargarTenistasDesdeCsv("/Users/ikerariasmartinez/Downloads/Australian Open Championships, Champion vs Runner-up, Men's Singles,1969 - 2021.csv");
	    historia.cargarResultadosDesdeCsv("/Users/ikerariasmartinez/Downloads/Australian Open Championships, Champion vs Runner-up, Men's Singles,1969 - 2021.csv");
	  
	    
	    HashMap<String, Integer> clasificacion = historia.calculaClasificacion(2000, 2020);
	    
	    System.out.println("Clasificación de victorias en Grand Slams entre 2000 y 2020:");
	    for (HashMap.Entry<String, Integer> entry : clasificacion.entrySet()) {
	        System.out.println(entry.getKey() + ": " + entry.getValue() + " victorias");
	    }
	    
	    System.out.println(historia.toString());

	}

}
