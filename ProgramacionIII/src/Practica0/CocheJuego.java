package Practica0;

public class CocheJuego extends Coche {
	
	private LabelCoche labelcoche;
	
	public CocheJuego() {
        super();
        labelcoche = new LabelCoche();
        labelcoche.setBounds((int) getPosX(), (int) getPosY(), 100, 100); 
    }
	
	public void setPosX(double posX) {
        super.setPosX(posX);
        labelcoche.setBounds((int) posX, (int) getPosY(), 100, 100);
    }
	
	public void setPosY(double posY) {
        super.setPosY(posY);
        labelcoche.setBounds((int) getPosX(), (int) posY, 100, 100);
    }
	
	public LabelCoche getLabelCoche() {
        return labelcoche;
    }

}
