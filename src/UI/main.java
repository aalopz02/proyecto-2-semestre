package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.SeparatorMenuItem;
import javafx.application.Platform;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("LinkedDB");
        BorderPane root  = new BorderPane();
        Scene scene = new Scene(root, 1000, 850, Color.WHITE);
        MenuBar menuArriba = new MenuBar();
        menuArriba.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setTop(menuArriba);
        Menu menuArchivo = new Menu("Archivo");
        MenuItem subMenuNuevo = new MenuItem("Nuevo");
        MenuItem subMenuSalvar = new MenuItem("Salvar");
        MenuItem subMenuSalir = new MenuItem("Salir");
        menuArchivo.getItems().addAll(subMenuNuevo, subMenuSalvar,new SeparatorMenuItem(), subMenuSalir);
        Menu menuVer = new Menu("Ver");
        MenuItem subMenuRetroceso = new MenuItem("Retroceso");
        MenuItem subMenuRehacer = new MenuItem("Rehacer");
        MenuItem subMenuCambios = new MenuItem("Ver Cambios");
        menuVer.getItems().addAll(subMenuCambios,subMenuRetroceso,subMenuRehacer);
        Menu menuAyuda = new Menu("Ayuda");
        MenuItem subMenuAyuda = new MenuItem("Ayuda");
        menuAyuda.getItems().addAll(subMenuAyuda);
        menuArriba.getMenus().addAll(menuArchivo,menuVer,menuAyuda);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
