package Zoo;

public class Animal {
	
	protected String nombre;
	protected int ID;
	protected Genero genero;
	protected int edad;
	protected Trabajador cuidador;
	protected String descripcion;
	
	//Constructores
	public Animal(String nombre, int iD, Genero genero, int edad, Trabajador cuidador, String descripcion) {
		super();
		this.nombre = nombre;
		ID = iD;
		this.genero = genero;
		this.edad = edad;
		this.cuidador = cuidador;
		this.descripcion = descripcion;
	}

	public Animal() {
		
		this.nombre = "";
		ID = 0;
		this.genero = null;
		this.edad = 0;
		this.cuidador = null;
		this.descripcion = "";
	}
	
	//Getters y Setters

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Trabajador getCuidador() {
		return cuidador;
	}

	public void setCuidador(Trabajador cuidador) {
		this.cuidador = cuidador;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
	
	
	
	
	
	
	

}
