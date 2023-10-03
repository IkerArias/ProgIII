package Zoo;

public class Trabajador {
	//Declaracion de atributos
	protected int DNI;
	protected String nombre;
	protected String apellido;
	protected int horasDeTrabajo;
	protected Puesto puesto;
	protected int nTlf;
	protected String correo;
	
	//Constructores con y sin argumentos
	public Trabajador(int dNI, String nombre, String apellido, int sueldoPorHora, int horasDeTrabajo, Puesto puesto,
			int nTlf, String correo) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.apellido = apellido;
		this.horasDeTrabajo = horasDeTrabajo;
		this.puesto = puesto;
		this.nTlf = nTlf;
		this.correo = correo;
	}
	
	public Trabajador() {
		
		DNI = 0;
		this.nombre = "";
		this.apellido = "";
		this.horasDeTrabajo = 0;
		this.puesto = null;
		this.nTlf = 0;
		this.correo = "";
	}
	
	//Getters y Setters 

	public int getDNI() {
		return DNI;
	}

	public void setDNI(int dNI) {
		DNI = dNI;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getHorasDeTrabajo() {
		return horasDeTrabajo;
	}

	public void setHorasDeTrabajo(int horasDeTrabajo) {
		this.horasDeTrabajo = horasDeTrabajo;
	}

	public Puesto getPuesto() {
		return puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}

	public int getnTlf() {
		return nTlf;
	}

	public void setnTlf(int nTlf) {
		this.nTlf = nTlf;
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
		return "Trabajador [DNI=" + DNI + ", nombre=" + nombre + ", apellido=" + apellido + ", puesto=" + puesto + ", nTlf=" + nTlf
				+ ", correo=" + correo + "]";
	}
	
	public int CalcularSueldo() {
		
		int sueldoPorHora;
		
		if (puesto == Puesto.ADMINISTRADOR) {
			sueldoPorHora = 14;
			
		} else if (puesto == Puesto.CUIDADOR) {
			sueldoPorHora = 15;
			
		} else if (puesto == Puesto.VETERINARIO) {
			sueldoPorHora = 18;
			
		} else {
			sueldoPorHora = 12;
		}
		
		return sueldoPorHora * horasDeTrabajo;
		
	}
	
	
	
	
	
	

}
