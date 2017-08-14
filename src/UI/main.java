package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
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
        BorderPane root  = new BorderPane(crearCuadros(root));
        Scene scene = new Scene(root, 1000, 850, Color.WHITE);
        crearMenus(root);
        crearCuadros(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static ScrollPane crearCuadros(BorderPane root){
        ScrollPane cuadroEditor = new ScrollPane();
        cuadroEditor.setSize(250,350);

    }
    public static void crearMenus(BorderPane root){
        MenuBar menuArriba = new MenuBar();
        root.setTop(menuArriba);
        Menu menuArchivo = new Menu("Archivo");
        MenuItem subMenuNuevo = new MenuItem( "Nuevo");
        subMenuNuevo.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN));
        MenuItem subMenuSalvar = new MenuItem("Salvar");
        subMenuSalvar.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));
        MenuItem subMenuSalir = new MenuItem( "Salir");
        subMenuSalir.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));
        subMenuSalir.setOnAction(actionEvent -> Platform.exit());
        menuArchivo.getItems().addAll(subMenuNuevo, subMenuSalvar,new SeparatorMenuItem(), subMenuSalir);
        Menu menuVer = new Menu("Ver");
        MenuItem subMenuRetroceso = new MenuItem("Retroceso");
        subMenuRetroceso.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN));
        MenuItem subMenuRehacer = new MenuItem(  "Rehacer");
        subMenuRehacer.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.SHORTCUT_DOWN));
        MenuItem subMenuCambios = new MenuItem("Ver Cambios");
        menuVer.getItems().addAll(subMenuCambios,subMenuRetroceso,subMenuRehacer);
        Menu menuAyuda = new Menu("Ayuda");
        MenuItem subMenuAyuda = new MenuItem("Ayuda");
        menuAyuda.getItems().addAll(subMenuAyuda);
        menuArriba.getMenus().addAll(menuArchivo,menuVer,menuAyuda);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
