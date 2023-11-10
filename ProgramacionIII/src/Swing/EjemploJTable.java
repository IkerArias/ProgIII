package Swing;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.*;

public class EjemploJTable extends JFrame {
	
	public static void main(String[] args) {
		EjemploJTable v = new EjemploJTable();
		v.setVisible(true);
	}
	
	private JTable tabla;
	private String [] nombresColumnas = {"Nombre", "Radio", "Lunas"};
	private Object [][] datosFila= {
			{"Mercurio", 2440.0, 0},
			{"Venus", 6052.0, 0},
			{"Tierra", 6378.1, 1},
			{"Saturno", 60245.0, 17}
	};
	
	
	public EjemploJTable() {
		
		setTitle("Tabla Sistema Solar");
		setBounds(300,300,400,200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JTable tabla = new JTable(datosFila, nombresColumnas);
		
		add(new JScrollPane(tabla), BorderLayout.CENTER);
		
		
		
	}

}
