package UI;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import static javafx.application.Application.launch;

public class ventanaError {

    public void crearVentana(Stage primaryStage, String texto){

        primaryStage.setTitle(texto);
        BorderPane root  = new BorderPane();
        Scene scene = new Scene(root, 250, 100, Color.WHITE);
        root.setCenter(verificarError(texto));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Label verificarError(String error){

        Label etiqueta = new Label();
        String texto = "a";
        if (error == "Error Tipo"){texto = "          El tipo de dato no \n" +
                "corresponde al tipo de variable";}
        etiqueta.setText(texto);

        return etiqueta;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
