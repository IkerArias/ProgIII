package Practica6;

import java.awt.BorderLayout;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class VentanaArbol extends JFrame {
    public VentanaArbol() {
        // Configuración de la ventana
        setTitle("VentanaArbol");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        
        // Crear un label en la parte superior
        JLabel mensajeLabel = new JLabel("");
        add(mensajeLabel, BorderLayout.NORTH);

        // Crear un JTree a la izquierda
        DefaultMutableTreeNode raiz = new DefaultMutableTreeNode("Raiz");
        JTree tree = new JTree(raiz);
        JScrollPane treeScrollPane = new JScrollPane(tree);
        add(treeScrollPane, BorderLayout.WEST);

        // Crear una tabla en el centro
        JTable table = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(table);
        add(tableScrollPane, BorderLayout.CENTER);

        // Crear un panel de visualización a la derecha
        JPanel panel = new JPanel();
        add(panel, BorderLayout.EAST);

        // Crear una botonera en la parte inferior
        JPanel botonera = new JPanel();
        JButton insertButton = new JButton("Inserción");
        JButton deleteButton = new JButton("Borrado");
        JButton orderButton = new JButton("Orden");
        botonera.add(insertButton);
        botonera.add(deleteButton);
        botonera.add(orderButton);
        add(botonera, BorderLayout.SOUTH);

        
     
        setVisible(true);
    }

    public static void main(String[] args) {
        
            VentanaArbol ventana = new VentanaArbol();
       
    }
}
