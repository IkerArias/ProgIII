package Practica0;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class LabelCoche extends JLabel {
	
	private ImageIcon image;
    
	public LabelCoche() {

            ImageIcon image = new ImageIcon("/Users/ikerariasmartinez/git/ud-prog3/Clase/src/es/deusto/prog3/cap00/coche.png");

}
	
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            // Escalar la imagen al tamaño deseado (100x100)
            Image scaledImage = image.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

            // Dibujar la imagen en el JLabel
            g.drawImage(scaledImage, 0, 0, this);
        }
	
}
}