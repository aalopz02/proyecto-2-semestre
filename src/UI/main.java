package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("LinkedDB");
        BorderPane root  = new BorderPane();
        GridPane grid = new GridPane();
        Scene scene = new Scene(root, 1000, 850, Color.WHITE);
        crearMenus(grid,root);
        crearEtiquetas(grid,root);
        crearArbolDocumentos(grid,root);
        root.setCenter(grid);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void crearArbolDocumentos(GridPane grid,BorderPane root){
        TreeItem<String> rootArchivo = new TreeItem<>("Archivos");
        rootArchivo.setExpanded(true);
        for (int i = 0;i < 3;i++){
            TreeItem<String> ramaArchivo = new TreeItem<>("NombreArchivo");
            ramaArchivo.setExpanded(true);
            rootArchivo.getChildren().add(ramaArchivo);

            TreeItem<String> ramaCaracteristica = new TreeItem<>("Caracteristica");
            ramaCaracteristica.setExpanded(true);
            ramaArchivo.getChildren().add(ramaCaracteristica);

            TreeItem<String> leafDato = new TreeItem<>("Dato");
            leafDato.setExpanded(false);
            ramaCaracteristica.getChildren().add(leafDato);
        }
        TreeView<String> tree = new TreeView<>(rootArchivo);
        grid.add(tree,0,2);

    }
    public static void crearEtiquetas(GridPane grid, BorderPane root){
        javafx.scene.control.Label documentos = new javafx.scene.control.Label();
        documentos.setText("              Documentos");
        grid.add(documentos,0,1);
        javafx.scene.control.Label movimientos = new javafx.scene.control.Label();
        movimientos.setText("Cambios");
        grid.add(movimientos,0,3);

    }
    public static void crearMenus(GridPane grid,BorderPane root){
        MenuBar menuArriba = new MenuBar();
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
        root.setTop(menuArriba);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
