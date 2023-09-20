package Practica0;

public class Coche {
	
	private double velocidad;
	private double direccionActual;
	private double posX;
	private double posY;
	private String nombrePiloto;
	
	//Getters y Setters
	public double getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}
	public double getDireccionActual() {
		return direccionActual;
	}
	public void setDireccionActual(double direccionActual) {
		this.direccionActual = direccionActual;
	}
	public double getPosX() {
		return posX;
	}
	public void setPosX(double posX) {
		this.posX = posX;
	}
	public double getPosY() {
		return posY;
	}
	public void setPosY(double posY) {
		this.posY = posY;
	}
	public String getNombrePiloto() {
		return nombrePiloto;
	}
	public void setNombrePiloto(String nombrePiloto) {
		this.nombrePiloto = nombrePiloto;
	}
	
	//Constructores
	
	public Coche(double velocidad, double direccionActual, double posX, double posY, String nombrePiloto) {
		super();
		this.velocidad = velocidad;
		this.direccionActual = direccionActual;
		this.posX = posX;
		this.posY = posY;
		this.nombrePiloto = nombrePiloto;
	}
	
	public Coche() {
		super();
		this.velocidad = 0.0;
		this.direccionActual = 0.0;
		this.posX = 0.0;
		this.posY = 0.0;
		this.nombrePiloto = "";
	}
	
	//Metodo toString
	@Override
	public String toString() {
		return "Coche [velocidad=" + velocidad + ", direccionActual=" + direccionActual + ", posX=" + posX + ", posY="
				+ posY + ", nombrePiloto=" + nombrePiloto + "]";
	}
	
	// Paso 4
	
	public void Acelerar(double aceleracion) {
		velocidad += aceleracion;
	}
	public void Girar(double giro) {
		direccionActual += giro;
		
		if (direccionActual < 0) {
			direccionActual += 360;
		} else if (direccionActual >= 360) {
			direccionActual -= 360;
		}
	}
	public void Mover(double tiempoMover) {
		double radianes = Math.toRadians(direccionActual);
		posX = velocidad * tiempoMover * Math.cos(radianes);
		posY = velocidad * tiempoMover * Math.sin(radianes);
	
	}
	
	
	
	
	
	
	
	
	
	

}
