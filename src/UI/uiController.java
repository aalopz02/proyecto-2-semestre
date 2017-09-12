package UI;

import funciones.crearJson;
import funciones.escribirJson;
import funciones.leerIniciales;
import funciones.leerJson;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;
import java.util.*;
import static java.lang.System.in;

public class uiController {

    public static class cellFactory{

        private SimpleStringProperty nomArchivo;
        public SimpleStringProperty caracteristica;
        public SimpleStringProperty dato;
        public SimpleStringProperty tipo;

        public cellFactory(String nomArchivo, String caracteristica, String dato, String tipo){
            this.nomArchivo = new SimpleStringProperty(nomArchivo);
            this.caracteristica = new SimpleStringProperty(caracteristica);
            this.dato = new SimpleStringProperty(dato);
            this.tipo = new SimpleStringProperty(tipo);
        }

        public void setNomArchivo(String nomArchivo) {
            this.nomArchivo.set(nomArchivo);
        }

        public void setCaracteristica(String caracteristica) {
            this.caracteristica.set(caracteristica);
        }

        public void setDato(String dato) {
            this.dato.set(dato);
        }

        public void setTipo(String tipo) {
            this.tipo.set(tipo);
        }

        public String getNomArchivo() {
            return nomArchivo.get();
        }

        public String getCaracteristica() {
            return caracteristica.get();
        }

        public String getDato() {
            return dato.get();
        }

        public String getTipo() {
            return tipo.get();
        }
    }

    public static ObservableList listaTabla = FXCollections.observableArrayList();

    protected static void agregarValorTabla(TreeView<String> tree, TableView<Void> tablaModificar){

        TreeItem<String> item = (TreeItem<String>) tree.getSelectionModel().getSelectedItem();
        TreeItem<String> newItem;
        if (item.getValue().toString() == "Archivos" || item.getValue() == null){
            return;
        }
        newItem = item.getParent();
        leerJson lectura = new leerJson();
        if (newItem.getValue().toString() == "Archivos"){
            newItem = item;
            for (int i = 0;i < lectura.leerNombres(newItem.getValue()).size(); i++){
                cellFactory datos1 = new cellFactory(
                newItem.getValue().toString(),
                lectura.leerLlave(newItem.getValue().toString(), i).toString().replace("[","").replace("]",""),
                lectura.leerCaracteristicas(newItem.getValue().toString(), i),
                lectura.leerTipo(newItem.getValue().toString(), i)
                );
                listaTabla.add(datos1);
                tablaModificar.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nomArchivo"));
                tablaModificar.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("caracteristica"));
                tablaModificar.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("tipo"));
                tablaModificar.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("dato"));

            }
            tablaModificar.setItems(listaTabla);
            return;
        }
        else {
            if (item.isLeaf()) {
                newItem = item.getParent().getParent();
                System.out.println(item.getParent().getParent().getChildren());
                System.out.println(item);
                cellFactory datos1 = new cellFactory(
                        newItem.getValue(),
                        item.getParent().getValue(),
                        item.getValue(),
                        lectura.leerTipo(newItem.getValue().toString(), item.getParent().getParent().getChildren().indexOf(item.getParent()))

                );
                listaTabla.add(datos1);
                tablaModificar.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nomArchivo"));
                tablaModificar.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("caracteristica"));
                tablaModificar.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("tipo"));
                tablaModificar.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("dato"));

            }
            else{
                newItem = item.getParent();
                cellFactory datos1 = new cellFactory(
                        newItem.getValue(),
                        item.getValue(),
                        item.getChildren().get(0).getValue(),
                        lectura.leerTipo(newItem.getValue().toString(), item.getParent().getChildren().indexOf(item))

                );
                listaTabla.add(datos1);
                tablaModificar.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nomArchivo"));
                tablaModificar.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("caracteristica"));
                tablaModificar.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("tipo"));
                tablaModificar.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("dato"));
            }
        }
        tablaModificar.setItems(listaTabla);
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