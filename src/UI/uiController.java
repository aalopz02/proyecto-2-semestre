package UI;

import funciones.crearJson;
import funciones.escribirJson;
import funciones.leerJson;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class uiController {

    public static class cellFactory{

        private SimpleStringProperty nomArchivo;
        public SimpleStringProperty caracteristica;
        public SimpleStringProperty dato;
        public SimpleStringProperty tipo;
        public SimpleStringProperty tipollave;
        public Boolean requerido;

        public cellFactory(String nomArchivo, String caracteristica, String dato, String tipo, Boolean requerido, String tipollave){
            this.nomArchivo = new SimpleStringProperty(nomArchivo);
            this.caracteristica = new SimpleStringProperty(caracteristica);
            this.dato = new SimpleStringProperty(dato);
            this.tipo = new SimpleStringProperty(tipo);
            this.requerido = requerido;
            this.tipollave = new SimpleStringProperty(tipollave);

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

        public void setRequerido(Boolean requerido) {
            this.requerido = requerido;
        }

        public void setTipollave(String tipollave) {
            this.tipollave.set(tipollave);
        }

        public Boolean getRequerido() {
            return requerido;
        }

        public String getTipollave() {
            return tipollave.get();
        }
    }

    public static ObservableList listaTabla = FXCollections.observableArrayList();

    protected static void agregarValorTabla(TreeView<String> tree, TableView<Void> tablaModificar){

        TreeItem<String> item = (TreeItem<String>) tree.getSelectionModel().getSelectedItem();
        TreeItem<String> newItem;
        if (item.getValue().toString().equals("Archivos") || item.getValue() == null){
            return;
        }
        newItem = item.getParent();
        leerJson lectura = new leerJson();
        if (newItem.getValue().toString().equals("Archivos")){
            newItem = item;
            for (int i = 0;i < lectura.leerNombres(newItem.getValue()).size(); i++){
                cellFactory datos1 = new cellFactory(
                    newItem.getValue().toString(),
                    lectura.leerLlave(newItem.getValue().toString(), i).toString().replace("[","").replace("]",""),
                    lectura.leerCaracteristicas(newItem.getValue().toString(), i),
                    lectura.leerTipo(newItem.getValue().toString(), i),
                    lectura.leerRequerido(newItem.getValue().toString(), i),
                    lectura.leerTipoLlave(newItem.getValue().toString(), i)
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
                cellFactory datos1 = new cellFactory(
                        newItem.getValue(),
                        item.getParent().getValue(),
                        item.getValue(),
                        lectura.leerTipo(newItem.getValue().toString(), item.getParent().getParent().getChildren().indexOf(item.getParent())),
                        lectura.leerRequerido(newItem.getValue().toString(), item.getParent().getParent().getChildren().indexOf(item.getParent())),
                        lectura.leerTipoLlave(newItem.getValue().toString(), item.getParent().getParent().getChildren().indexOf(item.getParent()))

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
                        lectura.leerTipo(newItem.getValue().toString(), item.getParent().getChildren().indexOf(item)),
                        lectura.leerRequerido(newItem.getValue().toString(), item.getParent().getChildren().indexOf(item)),
                        lectura.leerTipoLlave(newItem.getValue().toString(), item.getParent().getChildren().indexOf(item))
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

        if (item == null || item.getValue().toString().equals("Archivos") || item.getValue() == null){
            return;
        }
        if (item.getParent().getValue().toString().equals("Archivos")){
            File archivo = new File("./src/datos/" + item.getValue().toString() + ".json");
            if (archivo.delete()){
                item.getParent().getChildren().remove(item);
                main.crearEtiquetasCambios("Se eliminó el archivo: " + item.getValue().toString());
                ventanaError.crearVentana(new Stage(),"Archivo Eliminado");
                return;
            }
            else{
                ventanaError.crearVentana(new Stage(),"Error Eliminar");
                return;
            }
        }
        if (item.getParent().getParent().getValue().equals("Archivos")){
            leerJson archivo = new leerJson();
            if (item.getParent().getChildren().size() == 1){
                File archivos = new File("./src/datos/" + item.getParent().getValue().toString() + ".json");
                if (archivos.delete()){
                    item.getParent().getParent().getChildren().remove(item.getParent());
                    main.crearEtiquetasCambios("Se eliminó el archivo: " + item.getParent().getValue().toString());
                    ventanaError.crearVentana(new Stage(),"El Archivo Vacio Fue Eliminado");
                    return;
                }
                else{
                    ventanaError.crearVentana(new Stage(),"Error Eliminar");
                    return;
                }

            }
            if(archivo.leerRequerido(item.getParent().getValue(),item.getParent().getChildren().indexOf(item))){
                ventanaError.crearVentana(new Stage(), "Dato es Requerido");
            }
            else{
                ArrayList lista = new ArrayList();
                lista.add("Eliminar");
                lista.add(item.getParent().getChildren().indexOf(item));
                escribirJson.escribirArchivo(item.getParent().getValue(),new crearJson(),lista);
                item.getParent().getChildren().remove(item);
                main.crearEtiquetasCambios("Se eliminó la característica: " + item.getValue().toString());
            }
        }

    }

    protected static void nuevoValor(TreeView<String> tree){
        TreeItem<String> item = (TreeItem<String>) tree.getSelectionModel().getSelectedItem();
        escribirJson archivo = new escribirJson();
        crearJson lista = new crearJson();
        if (item == null || item.getValue() == null){return;}
        if (item.getValue().toString().equals("Archivos")){
            ventanaError.ventanaNombreArchivo(lista,archivo,item);
            return;
        }
        if (item.isLeaf()){ return; }
        if (item.getParent().getValue().toString().equals("Archivos")){
            TreeItem<String> nuevoHoja = new TreeItem<String>("Caracteristica");
            TreeItem<String> nuevoRama = new TreeItem<String>("NombreCaracteristica");
            nuevoRama.getChildren().add(nuevoHoja);
            item.getChildren().add(nuevoRama);
            lista.setNombreArchivo("ArchivoNuevo");
            lista.setNombreCaracteristica("NombreCaracteristica");
            ventanaError.ventanaTipoLlave(lista);
            lista.setTipo("String");
            ArrayList indice = new ArrayList();
            indice.add(false);
            indice.add("+");
            archivo.escribirArchivo( item.getValue().toString(), lista, indice );
            main.crearEtiquetasCambios("Se le agregó una nueva caracteristica al documento: " + item.getValue().toString());

            return;
        }

    }

    protected static void realizarCambiosTabla(TableView<Void> tablaModificar){
        for(int i = 0; i < listaTabla.size(); i++){
            cellFactory objeto = (cellFactory) listaTabla.get(i);
            crearJson listaJson = new crearJson();
            listaJson.setNombreCaracteristica(objeto.getCaracteristica());
            listaJson.setRequerido(false);
            listaJson.setTipoLlave("Primaria");
            listaJson.setTipo(objeto.getTipo());
            listaJson.setValorDefecto(objeto.getDato());
            listaJson.setNombreArchivo(objeto.getNomArchivo());
            listaJson.crearListaJson();
            escribirJson archivo = new escribirJson();
            ArrayList indice = new ArrayList();
            indice.add(false);
            indice.add(i);
            archivo.escribirArchivo(objeto.getNomArchivo(),listaJson,indice);
        }
        listaTabla.removeAll(listaTabla);
        main.crearEtiquetasCambios("Se realizó el guardado de los cambios a los documentos");
    }

    protected static void abrirTexto() {
        File file = new File("./src/funciones/Ayuda.txt");
        if (!Desktop.isDesktopSupported()) {
            ventanaError.verificarError("Error Lectura");
            return;
        }
        Desktop escritorio = Desktop.getDesktop();
        try {
            escritorio.open(file);
        }catch (IOException e){
            ventanaError.verificarError("Error Lectura");
        }
    }

}