package Practica6;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Ejercicio06_03 {
	
	private static JFrame ventana;
	private static DataSetMunicipios dataset;
	private static DefaultTreeModel treeModel;
	private static JTree tree;
	

	private static VentanaTablaDatos ventanaDatos;
	
	public static void main(String[] args) {
		ventana = new JFrame( "Ejercicio 6.3" );
		ventana.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		ventana.setLocationRelativeTo( null );
		ventana.setSize( 200, 80 );

		JButton bCargaMunicipios = new JButton( "Carga municipios > 200k" );
		bCargaMunicipios.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargaMunicipios();
			}
		});
		ventana.add( bCargaMunicipios );
		
		// Crear la estructura del JTree
        DefaultMutableTreeNode raizPrincipal = new DefaultMutableTreeNode("Municipios");
        treeModel = new DefaultTreeModel(raizPrincipal);
        tree = new JTree(treeModel);
        tree.setEditable(false);
        
        // Agregar el JTree a la ventana
        JScrollPane treeScrollPane = new JScrollPane(tree);
        ventana.add(treeScrollPane, BorderLayout.WEST);
        
		ventana.setVisible( true );
	}
	
	private static void cargaMunicipios() {
		try {
			dataset = new DataSetMunicipios( "municipios.txt" );
			System.out.println( "Cargados municipios:" );
			for (Municipio m : dataset.getListaMunicipios() ) {
				System.out.println( "\t" + m );
			}
			// TODO Resolver el ejercicio 6.3
			ventanaDatos = new VentanaTablaDatos( ventana );
			ventanaDatos.setDatos( dataset );
			ventanaDatos.setVisible( true );
			
			// Crear un HashMap para  comunidades autónomas y provincias
           HashMap<String, DefaultMutableTreeNode> comunidadesAutonomas = new HashMap<>();
           HashMap<String, DefaultMutableTreeNode> provincias = new HashMap<>();
           
           // Recorrer la lista de municipios y construir la jerarquía
           for (Municipio municipio : dataset.getListaMunicipios()) {
               String comunidadAutonoma = municipio.getAutonomia();
               String provincia = municipio.getProvincia();

               // Agregar comunidad autónoma 
               if (!comunidadesAutonomas.containsKey(comunidadAutonoma)) {
                   DefaultMutableTreeNode comunidadAutonomaNode = new DefaultMutableTreeNode(comunidadAutonoma);
                   comunidadesAutonomas.put(comunidadAutonoma, comunidadAutonomaNode);
                   treeModel.insertNodeInto(comunidadAutonomaNode, (DefaultMutableTreeNode) treeModel.getRoot(), 0);
               }
               // Agregar provincia 
               if (!provincias.containsKey(provincia)) {
                   DefaultMutableTreeNode provinciaNode = new DefaultMutableTreeNode(provincia);
                   provincias.put(provincia, provinciaNode);
                   DefaultMutableTreeNode comunidadNode = comunidadesAutonomas.get(comunidadAutonoma);
                   treeModel.insertNodeInto(provinciaNode, comunidadNode, 0);
               }
           }
           
		} catch (IOException e) {
			System.err.println( "Error en carga de municipios" );
		}
	}
	
	
}
