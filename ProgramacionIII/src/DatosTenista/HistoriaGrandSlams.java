package DatosTenista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;

public class HistoriaGrandSlams {

	private ArrayList<Resultado> resultados;
	private Map<String, Torneo> torneoPorNombre;
	private Map<String, Torneo> torneosPorCodigo;
	private Map<String, Tenista> tenistas;
	
	public ArrayList<Resultado> getResultados() {
		return resultados;
	}
	public void setResultados(ArrayList<Resultado> resultados) {
		this.resultados = resultados;
	}
	public Map<String, Torneo> getTorneoPorNombre() {
		return torneoPorNombre;
	}
	public void setTorneoPorNombre(Map<String, Torneo> torneoPorNombre) {
		this.torneoPorNombre = torneoPorNombre;
	}
	public Map<String, Torneo> getTorneosPorCodigo() {
		return torneosPorCodigo;
	}
	public void setTorneosPorCodigo(Map<String, Torneo> torneosPorCodigo) {
		this.torneosPorCodigo = torneosPorCodigo;
	}
	public Map<String, Tenista> getTenistas() {
		return tenistas;
	}
	public void setTenistas(Map<String, Tenista> tenistas) {
		this.tenistas = tenistas;
	}
	
	public HistoriaGrandSlams(ArrayList<Resultado> resultados, Map<String, Torneo> torneoPorNombre,
			Map<String, Torneo> torneosPorCodigo, Map<String, Tenista> tenistas) {
		super();
		this.resultados = new ArrayList<>();
		this.torneoPorNombre = new HashMap<>();
		this.torneosPorCodigo = new HashMap<>();
		this.tenistas = new HashMap<>();
	}
	

	
	public HashMap<String, Integer> calculaClasificacion(int anyoInicial, int anyoFinal) {
        HashMap<String, Integer> clasificacion = new HashMap<>();
        
        for (Resultado resultado : resultados) {
            int anioTorneo = resultado.getAño();
            
            if (anioTorneo >= anyoInicial && anioTorneo <= anyoFinal) {
                String ganador = resultado.getGanador();
                clasificacion.put(ganador, clasificacion.getOrDefault(ganador, 0) + 1);
            }
        }
        
        return clasificacion;
	}
	
	
	
	public void cargarTorneosDesdeCsv(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean header = true; // Flag para omitir la primera línea (cabecera)

            while ((line = br.readLine()) != null) {
                if (header) {
                    header = false;
                    continue; // Saltar la cabecera
                }

                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String codigo = parts[0].trim();
                    String nombre = parts[1].trim();
                    String ciudad = parts[2].trim();

                    // Puedes agregar más campos del CSV según sea necesario

                    Torneo torneo = new Torneo(Integer.parseInt(codigo), nombre, ciudad);
                    // Agregar el torneo al mapa de torneos por nombre y código
                    torneoPorNombre.put(nombre, torneo);
                    torneosPorCodigo.put(codigo, torneo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void cargarTenistasDesdeCsv(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean header = true; // Flag para omitir la primera línea (cabecera)

            while ((line = br.readLine()) != null) {
                if (header) {
                    header = false;
                    continue; // Saltar la cabecera
                }

                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String nombre1 = parts[1].trim();
                    String nacionalidad1 = parts[3].trim();
                    String nombre2 = parts[4].trim();
                    String nacionalidad2 = parts[6].trim();

                    // Puedes agregar más campos del CSV según sea necesario

                    Tenista tenista1 = new Tenista(nombre1, nacionalidad1, 0);
                    Tenista tenista2 = new Tenista(nombre2, nacionalidad2, 0);
                    // Agregar el tenista al mapa de tenistas por nombre
                    if (!tenistas.containsKey(nombre1)) {
                    	tenistas.put(nombre1, tenista1);
                    }
                    if (!tenistas.containsKey(nombre2)) {
                    	tenistas.put(nombre2, tenista2);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void cargarResultadosDesdeCsv(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean header = true; // Flag para omitir la primera línea (cabecera)

            while ((line = br.readLine()) != null) {
                if (header) {
                    header = false;
                    continue; // Saltar la cabecera
                }

                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    int anio = Integer.parseInt(parts[0].trim());
                    String ganador = parts[1].trim();
                    String subcampeon = parts[4].trim();
                    int rankingGanador = Integer.parseInt(parts[2].trim());
                    int rankingSubcampeon = Integer.parseInt(parts[5].trim());
                    String resultadoFinal = parts[7].trim();

                    // Puedes agregar más campos del CSV según sea necesario

                    Resultado resultado = new Resultado("", anio, ganador, subcampeon, rankingGanador, rankingSubcampeon, resultadoFinal);
                    // Agregar el resultado a la lista de resultados
                    resultados.add(resultado);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	@Override
	public String toString() {
		return "HistoriaGrandSlams [resultados=" + resultados + ", torneoPorNombre=" + torneoPorNombre
				+ ", torneosPorCodigo=" + torneosPorCodigo + ", tenistas=" + tenistas + "]";
	}
	
	
	
}
