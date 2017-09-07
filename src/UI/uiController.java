package UI;

import funciones.crearJson;
import funciones.escribirJson;
import funciones.leerIniciales;
import javafx.scene.Node;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.*;

import static java.lang.System.in;

public class uiController {

    protected static void agregarValorTabla(TreeView<String> tree){
    	
        TreeItem<String> item = (TreeItem<String>) tree.getSelectionModel().getSelectedItem();
        TreeItem<String> newItem;
        List<TreeItem<String>> valores = new ArrayList<TreeItem<String>>();
        if (item.getValue().toString() == "Archivos" || item.getValue() == null){
            return;
        }
        newItem = item.getParent();
        if (newItem.getValue().toString() == "Archivos"){
            newItem = item;
            valores.add(newItem);
        }
        else{
            valores.add(newItem);
        }
        if (item.isLeaf()){
            newItem = item.getParent().getParent();
            valores.add(newItem);

        }
        System.out.println(valores);

    }
    
    protected static void eliminarValor(TreeView<String> tree){
    	TreeItem<String> item = (TreeItem<String>) tree.getSelectionModel().getSelectedItem();
      
        if (item.getValue().toString() == "Archivos" || item.getValue() == null){
            return;
        }
        
        item.getParent().getChildren().remove(item);
    }
   
    protected static void nuevoValor(TreeView<String> tree){
    	TreeItem<String> item = (TreeItem<String>) tree.getSelectionModel().getSelectedItem();
    	if (item.isLeaf() || item.getValue() == null){
            return;
        }
        if (item.getValue().toString() == "Archivos"){
        	TreeItem<String> nuevoHoja = new TreeItem<String>("Caracteristica");
        	TreeItem<String> nuevoRama = new TreeItem<String>("NombreCaracteristica");
        	nuevoRama.getChildren().add(nuevoHoja);
        	TreeItem<String> nuevo = new TreeItem<String>("ArchivoNuevo");
        	nuevo.getChildren().add(nuevoRama);
        	item.getChildren().add(nuevo);
        	return;
        }
        if (item.getParent().getValue() == "Archivos"){
        	TreeItem<String> nuevoHoja = new TreeItem<String>("Caracteristica");
        	TreeItem<String> nuevoRama = new TreeItem<String>("NombreCaracteristica");
        	nuevoRama.getChildren().add(nuevoHoja);
        	item.getChildren().add(nuevoRama);
        }
        
    	
    }


}
