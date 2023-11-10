package Swing;

import java.awt.Container;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class EjemploJTree extends JFrame {

	public static void main(String[] args) {
		
		EjemploJTree v = new EjemploJTree();
		v.setVisible(true);
		
	}
	
	private JTree arbol;
	
	public EjemploJTree() {
		
		setTitle("Equipos de La Liga");
		setBounds(350,300,600,600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Europa");
		
		DefaultMutableTreeNode espania = new DefaultMutableTreeNode("España"); 
		raiz.add(espania);
		DefaultMutableTreeNode italia = new DefaultMutableTreeNode("Italia");
		raiz.add(italia);
		DefaultMutableTreeNode inglaterra = new DefaultMutableTreeNode("Inglaterra");
		raiz.add(inglaterra);
		DefaultMutableTreeNode alemania = new DefaultMutableTreeNode("Alemania");
		raiz.add(alemania);
		
		DefaultMutableTreeNode madrid = new DefaultMutableTreeNode("Real Madrid");
		espania.add(madrid);
		DefaultMutableTreeNode athletic = new DefaultMutableTreeNode("Athletic Club de Bilbao");
		espania.add(athletic);
		DefaultMutableTreeNode liverpool = new DefaultMutableTreeNode("Liverpool");
		inglaterra.add(liverpool);
		
		JTree arbol = new JTree(raiz);
		
		Container laminaContenido = getContentPane();
		laminaContenido.add(new JScrollPane(arbol));
		
		
	}
}
