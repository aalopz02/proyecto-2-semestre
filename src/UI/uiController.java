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

    protected static void agregarValorTabla(TreeView tree){
        TreeItem<String> item = (TreeItem<String>) tree.getSelectionModel().getSelectedItem();
        TreeItem<String> newItem;
        List valores = new ArrayList();
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


}
