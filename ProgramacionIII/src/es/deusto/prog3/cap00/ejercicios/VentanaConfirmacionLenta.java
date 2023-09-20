package es.deusto.prog3.cap00.ejercicios;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** Ejercicio de hilos con ventanas. Programa esta clase para que se cree una ventana
 * que pida un dato de texto al usuario y un botón de confirmar para que se confirme.
 * Haz que al pulsar el botón de confirmación se llame al procesoConfirmar()
 * que simula un proceso de almacenamiento externo que tarda unos segundos.
 * Observa que hasta que ocurre esta confirmación la ventana no responde.
 * 1. Arréglalo para que la ventana no se quede "frita" hasta que se acabe de confirmar.
 * 2. Haz que el botón de "confirmar" no se pueda pulsar dos veces mientras el proceso
 * de confirmación se esté realizando
 * @author andoni.eguiluz @ ingenieria.deusto.es
 */
public class VentanaConfirmacionLenta {
	
	private static Thread hilo;

		private static Random r = new Random();
	// Este método simula un proceso que tarda un tiempo en hacerse (entre 5 y 10 segundos)
	private static void procesoConfirmar() {
		try {
			Thread.sleep( 5000 + 1000*r.nextInt(6) );
		} catch (InterruptedException e) {}
	}
	
	public static void main(String[] args) {
		// TODO Desarrollar la clase de acuerdo a los comentarios de la cabecera
		// Comentario de prueba
		// Comentario 2	
		
		verHilos( "Antes de ventana" );
		
		JFrame ventana = new JFrame();
		ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		ventana.setSize(200,100);
		ventana.setLocation(20000,0);
		ventana.setVisible(true);
		
		JPanel panelCentral = new JPanel();
		JTextField txtUsuario = new JTextField("Usuario");
		panelCentral.add(txtUsuario);
		ventana.add(panelCentral, BorderLayout.NORTH);
		
		JButton btnAceptar = new JButton("Aceptar");
		ventana.add(btnAceptar);
		
		btnAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnAceptar.setEnabled(false);
				System.out.println("Pulsando el boton");
				btnAceptar.setForeground(Color.RED);
				
				hilo = new Thread() {
					public void run() {
						System.out.println("Inciio proceso");
						System.out.println(hilo.isAlive() + " " + hilo.getState());
						procesoConfirmar();
						System.out.println("Fin proceso");
						btnAceptar.setEnabled(true);
						
					}
				};
				
				hilo.start();
				
				}	
			});
		
		System.out.println("Fin");
		verHilos("Despues de ventana");	
}
	
	private static void verHilos(String mensaje) {
		System.out.println( mensaje);
		for (Thread hilo : Thread.getAllStackTraces().keySet()) {
			System.out.println(" " + hilo.getName() + " " + hilo.isDaemon());
		}
	}
	
	private static class MiHilo extends Thread {
		
	}
	
	
}