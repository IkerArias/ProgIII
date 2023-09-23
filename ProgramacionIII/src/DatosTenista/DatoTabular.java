package DatosTenista;

import java.util.ArrayList;

abstract class DatoTabular {
	
	private ArrayList<String> cabeceras;
	private ArrayList<String> valores;
	
	
	public ArrayList<String> getCabeceras() {
		return cabeceras;
	}
	public void setCabeceras(ArrayList<String> cabeceras) {
		this.cabeceras = cabeceras;
	}
	public ArrayList<String> getValores() {
		return valores;
	}
	public void setValores(ArrayList<String> valores) {
		this.valores = valores;
	}
	
	public DatoTabular(ArrayList<String> cabeceras, ArrayList<String> valores) {
		super();
		this.cabeceras = cabeceras;
		this.valores = new ArrayList<>(cabeceras.size());
	}
	
	@Override
	public String toString() {
		return "DatoTabular [cabeceras=" + cabeceras + ", valores=" + valores + "]";
	}
	
	//METODOS
	
	public String getValor(String cabecera) {
		int indice = cabeceras.indexOf(cabecera);
		if (indice != -1) {
			return valores.get(indice); }
			else {
				return null;
			}
		}
	
	public void setValor(String cabecera, String valor) {
		int indice = cabecera.indexOf(cabecera);
		if (indice != -1) {
			valores.set(indice, valor);
		}
	}
	
	public abstract String getTipo();
	
	public abstract void cargarDesdeCsv(String[] datos);
	
	public abstract String[] guardarEnCsv();
	
	
}
