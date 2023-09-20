package Practica0;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class VentanaJuego extends JFrame { 
	
public VentanaJuego() {
		
		//Configuracion de la ventana
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(2000, 0);
		setSize(400, 300);
		
		//Creamos el panel principal con Layout null y en el centro
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(null);
		add(panelPrincipal, BorderLayout.CENTER);
		
		CocheJuego coche = new CocheJuego();
        coche.setPosX(150); 
        coche.setPosY(100); 
        add(coche.getLabelCoche());
        
        JButton aceleraButton = new JButton("Acelera");
        JButton frenaButton = new JButton("Frena");
        JButton giraIzquierdaButton = new JButton("Gira Izq.");
        JButton giraDerechaButton = new JButton("Gira Der.");
        
        aceleraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coche.Acelerar(5); // Acelerar el coche en 5 píxeles/segundo
                System.out.println("Velocidad actual: " + coche.getVelocidad() + " pixels/segundo");
            }
        });
        
        frenaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coche.Acelerar(-5); // Decrementar la velocidad en 5 píxeles/segundo
                System.out.println("Velocidad actual: " + coche.getVelocidad() + " pixels/segundo");
            }
        });
        
        giraIzquierdaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coche.Girar(-10); // Girar 10 grados a la izquierda
                System.out.println("Dirección actual: " + coche.getDireccionActual() + " grados");
            }
        });
        
        giraDerechaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                coche.Girar(10); // Girar 10 grados a la derecha
                System.out.println("Dirección actual: " + coche.getDireccionActual() + " grados");
            }
        });
        
        
        JPanel panelBotonera = new JPanel();
        panelBotonera.add(aceleraButton);
        panelBotonera.add(frenaButton);
        panelBotonera.add(giraIzquierdaButton);
        panelBotonera.add(giraDerechaButton);
        add(panelBotonera, BorderLayout.SOUTH);
		
        Thread miHilo = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // Mueve el coche cada 40 milisegundos
                    coche.Mover(40); 

                    // Actualiza la posición del JLabelCoche
                    coche.getLabelCoche().setLocation((int) coche.getPosX(), (int) coche.getPosY());
                    coche.getLabelCoche().repaint();

                    // Espera 40 milisegundos antes de la siguiente iteración
                    try {
                        Thread.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        miHilo.start();
    }

	
	//Metodo main
	public static void main(String[] args) {
	            VentanaJuego ventana = new VentanaJuego();
	            
	            
	            Coche cochePrueba = new Coche();
	            cochePrueba.setPosX(150);
	            cochePrueba.setPosY(100);
	            cochePrueba.setNombrePiloto("Iker");
	            
	            System.out.println(cochePrueba.toString());
	            
	            cochePrueba.Acelerar(15);
	            cochePrueba.Girar(10);
	            cochePrueba.Mover(4);
	            
	            LabelCoche cochelabel = new LabelCoche();
	            ventana.add(cochelabel);
	            
	            
	            
	            ventana.setVisible(true);
	            
	}
	         
	
}
	

	