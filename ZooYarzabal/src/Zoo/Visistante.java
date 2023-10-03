package Zoo;

public class Visistante {
	//Declaracion de atributos
	protected String nombre;
	protected int DNI;
	protected int edad;
	protected int nVisitas;
	protected String correo;
	
	//Constructores con y sin argumentos
	public Visistante(String nombre, int dNI, int edad, int nVisitas, String correo) {
		super();
		this.nombre = nombre;
		DNI = dNI;
		this.edad = edad;
		this.nVisitas = nVisitas;
		this.correo = correo;
	}
	
	public Visistante() {
		this.nombre = "";
		DNI = 0;
		this.edad = 0;
		this.nVisitas = 0;
		this.correo = "";
	}

	//Getters y Setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDNI() {
		return DNI;
	}

	public void setDNI(int dNI) {
		DNI = dNI;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getnVisitas() {
		return nVisitas;
	}

	public void setnVisitas(int nVisitas) {
		this.nVisitas = nVisitas;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	//Metodo toString
	@Override
	public String toString() {
		return "Visistante [nombre=" + nombre + ", DNI=" + DNI + ", edad=" + edad + ", nVisitas=" + nVisitas
				+ ", correo=" + correo + "]";
	}
	
	
	

}
