package UI;

import funciones.crearJson;
import funciones.escribirJson;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

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
        if (error == "Error Tipo"){texto = "El tipo de dato no \n" + "corresponde al tipo de variable";}
        if (error == "Error Escritura"){texto = "Ocurrió un error \n" + "al intentar escribir el archivo";}
        if (error == "Error Lectura"){texto = "El archivo no se puede leer \n" + "o no se puede encontrar";}
        if (error == "Error Eliminar"){texto = "El archivo no se pudo eliminar";}
        if (error == "Archivo Eliminado"){texto = "El archivo fue eliminado";}
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
        TextField nombreArchivo = new TextField();
        nombreArchivo.setPromptText("Nombre Archivo");
        root.requestFocus();
        root.setCenter(nombreArchivo);
        nuevo.setScene(scene);
        nombreArchivo.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.ENTER)){
                    TreeItem<String> nuevoHoja = new TreeItem<String>("Caracteristica");
                    TreeItem<String> nuevoRama = new TreeItem<String>("NombreCaracteristica");
                    nuevoRama.getChildren().add(nuevoHoja);
                    TreeItem<String> nuevoRoot = new TreeItem<String>(nombreArchivo.getText());
                    nuevoRoot.getChildren().add(nuevoRama);
                    item.getChildren().add(nuevoRoot);
                    lista.setNombreArchivo(nombreArchivo.getText());
                    lista.setNombreCaracteristica("NombreCaracteristica");
                    lista.setTipoLlave("Primaria");
                    lista.setTipo("String");
                    lista.setRequerido(false);
                    archivo.escribirArchivo(nombreArchivo.getText() , lista ,true);
                    main.crearEtiquetasCambios("Se creó un documento Json nuevo");

                    nuevo.close();
                }
            }
        });
        nuevo.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
