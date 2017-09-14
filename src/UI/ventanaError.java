package UI;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
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
        root.setCenter(verificarError(texto,root));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Label verificarError(String error, BorderPane root){

        Label etiqueta = new Label();
        String texto = "Error";
        if (error == "Error Tipo"){texto = "          El tipo de dato no \n" + "corresponde al tipo de variable";}
        if (error == "Error Escritura"){texto = "          Ocurrió un error \n" + "al intentar escribir el archivo";}
        if (error == "Error Lectura"){texto = "            El archivo no se puede leer \n" + "o no se puede encontrar";}
        if (error == "Error Eliminar"){texto = "El archivo no se pudo eliminar";}
        if (error == "Archivo Eliminado"){texto = "El archivo fue eliminado";}
        etiqueta.setText(texto);

        return etiqueta;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
