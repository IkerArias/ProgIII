package DatosTenista;

public class Tenista {

	private String nombre;
	private String nacionalidad;
	private int numeroVictorias;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public int getNumeroVictorias() {
		return numeroVictorias;
	}
	public void setNumeroVictorias(int numeroVictorias) {
		this.numeroVictorias = numeroVictorias;
	}
	
	public Tenista(String nombre, String nacionalidad, int numeroVictorias) {
		super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.numeroVictorias = numeroVictorias;
	}
	
	public Tenista() {
		super();
		this.nombre = "";
		this.nacionalidad = "";
		this.numeroVictorias = 0;
	}
	
	@Override
	public String toString() {
		return "Tenista [nombre=" + nombre + ", nacionalidad=" + nacionalidad + ", numeroVictorias=" + numeroVictorias
				+ "]";
	}
	
	
	
}
