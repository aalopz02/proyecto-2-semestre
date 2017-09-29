package UI;

import funciones.crearJson;
import funciones.escribirJson;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

import static javafx.application.Application.launch;

public class ventanaError {

    public static void crearVentana(Stage primaryStage, String texto){

        primaryStage.setTitle(texto);
        BorderPane root  = new BorderPane();
        Image icono = new Image("img/iconoError.png");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(icono);
        Scene scene = new Scene(root, 250, 100, Color.WHITE);
        root.setCenter(verificarError(texto));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Label verificarError(String error){

        Label etiqueta = new Label();
        String texto = "Error";
        if (error.equals("Error Tipo")){texto = "El tipo de dato no \n" + "corresponde al tipo de variable";}
        if (error.equals("Error Escritura")){texto = "Ocurrió un error \n" + "al intentar escribir el archivo";}
        if (error.equals("Error Lectura")){texto = "El archivo no se puede leer \n" + "o no se puede encontrar";}
        if (error.equals("Error Eliminar")){texto = "El archivo no se pudo eliminar";}
        if (error.equals("Archivo Eliminado")){texto = "El archivo fue eliminado";}
        if (error.equals("El Archivo Vacio Fue Eliminado")){texto = "Se eliminó la última\n" + "característica de un documento";}
        if (error.equals("Dato es Requerido")){texto = "El dato que se quiere eliminar es requerido";}
        if (error.equals("Error buscar")){texto = "El nombre de llave especificado\n" + "no se encuentra";}
        etiqueta.setText(texto);
        etiqueta.setTextAlignment(TextAlignment.CENTER);

        return etiqueta;
    }

    public static void ventanaNombreArchivo(crearJson lista, escribirJson archivo, TreeItem item){
        Stage nuevo = new Stage();
        BorderPane root = new BorderPane();
        nuevo.setTitle("Configurar Nombre");
        nuevo.setResizable(false);
        Image icono = new Image("img/iconoError.png");
        nuevo.getIcons().add(icono);
        Scene scene = new Scene(root, 250, 100, Color.WHITE);
        GridPane grid = new GridPane();
        TextField nombreArchivo = new TextField();
        Label etiqueta = new Label("Nombre Archivo");
        nombreArchivo.setPromptText("Nombre Archivo");
        Button aceptar = new Button("Aceptar");
        grid.add(etiqueta,0,0);
        grid.add(nombreArchivo,1,0);
        grid.add(aceptar ,0,3,2,2);
        grid.setHalignment(aceptar,HPos.CENTER);
        grid.setValignment(aceptar, VPos.BOTTOM);
        root.requestFocus();
        root.setCenter(grid);
        nuevo.setScene(scene);
        nuevo.setOnCloseRequest(new EventHandler<WindowEvent>(){
            @Override
            public void handle(WindowEvent event) {
                if (nombreArchivo.getText().equals("")) {
                    ventanaNombreArchivo(lista, archivo, item);
                }
            }
        });
        aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String texto = nombreArchivo.getText();
                if (texto.equals("")) {
                    texto = "ArchivoNuevo";
                }
                TreeItem<String> nuevoHoja = new TreeItem<String>("Caracteristica");
                TreeItem<String> nuevoRama = new TreeItem<String>("NombreCaracteristica");
                nuevoRama.getChildren().add(nuevoHoja);
                TreeItem<String> nuevoRoot = new TreeItem<String>(texto);
                nuevoRoot.getChildren().add(nuevoRama);
                item.getChildren().add(nuevoRoot);
                lista.setNombreArchivo(texto);
                lista.setNombreCaracteristica("NombreCaracteristica");
                lista.setTipo("String");
                ventanaTipoLlave(lista);
                ArrayList indice = new ArrayList();
                indice.add(true);
                archivo.escribirArchivo(texto, lista, indice);
                main.crearEtiquetasCambios("Se creó un documento Json nuevo");
                nombreArchivo.setText(texto);
                nuevo.close();
            }
        });
        nuevo.show();

    }

    public static void ventanaTipoLlave(crearJson lista){
        Stage nuevo = new Stage();
        BorderPane root = new BorderPane();
        GridPane grid = new GridPane();
        nuevo.setTitle("Configurar Llave");
        nuevo.setResizable(false);
        Image icono = new Image("img/iconoError.png");
        nuevo.getIcons().add(icono);
        Scene scene = new Scene(root, 250, 100, Color.WHITE);
        Label etiqueta1 = new Label("Tipo de llave ");
        TextField tipo = new TextField();
        tipo.setPromptText("Tipo llave");
        Label etiqueta2 = new Label("Requerido ");
        TextField requerido = new TextField();
        requerido.setPromptText("Requerido");
        root.requestFocus();
        root.setCenter(grid);
        nuevo.setScene(scene);
        Button aceptar = new Button("Aceptar");
        grid.add(etiqueta1,0,0);
        grid.add(etiqueta2,0,1);
        grid.add(tipo,1,0);
        grid.add(requerido,1,1);
        grid.add(aceptar,0,3,2,2);
        grid.setHalignment(aceptar, HPos.CENTER);
        grid.setValignment(aceptar, VPos.BOTTOM);
        ArrayList posibles = new ArrayList();
        posibles.add("Primaria");
        posibles.add("Foranea");
        posibles.add("true");
        posibles.add("false");
        nuevo.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                String tipoLlave = tipo.getText();
                String esRequerido = requerido.getText();
                if (!posibles.contains(tipoLlave) || !posibles.contains(esRequerido)){
                    ventanaTipoLlave(lista);
                }
            }
        });
        aceptar.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String tipoLlave = tipo.getText();
                String esRequerido = requerido.getText();
                if (!posibles.contains(tipoLlave) && !posibles.contains(esRequerido)){
                    ventanaTipoLlave(lista);
                }
                else {
                    lista.setRequerido(Boolean.valueOf(requerido.getText()));
                    lista.setTipoLlave(tipo.getText());
                }
                nuevo.close();
            }
        });
        nuevo.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
