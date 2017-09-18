package UI;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import funciones.leerIniciales;
import funciones.leerJson;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import UI.uiController.cellFactory;

public class main extends Application {

    private static VBox ventana = new VBox();

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("LinkedDB");
        BorderPane root  = new BorderPane();
        GridPane grid = new GridPane();
        Scene scene = new Scene(root, 1000, 700, Color.WHITE);
        Image icono = new Image("img/icono.png");
        primaryStage.setResizable(false);
        crearMenus(root);
        crearEtiquetas(grid);
        crearArbolDocumentos(grid);
        crearVentanaCambios(grid);
        root.setCenter(grid);
        primaryStage.getIcons().add(icono);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void crearArbolDocumentos(GridPane grid){
        TableView<Void> tablaModificar = crearTabla(grid);
        crearBotonGuardar(grid,tablaModificar);
        TreeItem<String> rootArchivo = new TreeItem<>("Archivos");
        rootArchivo.setExpanded(false);
        leerIniciales archivos = new leerIniciales();
        ArrayList<String> listaArchivos = (ArrayList<String>) archivos.leerArchivos();
        leerJson lecturaJson = new leerJson();
        for (int i = 0;i < listaArchivos.size();i++){
            String nomArchivo = listaArchivos.get(i).toString().replace(".json","");
            TreeItem<String> ramaArchivo = new TreeItem<>(nomArchivo);
            ramaArchivo.setExpanded(false);
            rootArchivo.getChildren().add(ramaArchivo);
            for (int j = 0;j<lecturaJson.leerNombres(nomArchivo).size();j++){
                String llave = lecturaJson.leerLlave(nomArchivo,j).toString();
                TreeItem<String> ramaCaracteristica = new TreeItem<>(llave.replace("[","").replace("]",""));
                ramaCaracteristica.setExpanded(false);
                ramaArchivo.getChildren().add(ramaCaracteristica);
                String caracteristica = lecturaJson.leerCaracteristicas(nomArchivo,j);
                TreeItem<String> leafDato = new TreeItem<>(caracteristica);
                ramaCaracteristica.getChildren().add(leafDato);
            }

        }
        TreeView<String> tree = new TreeView<>(rootArchivo);
        tree.setContextMenu((ContextMenu) crearContextMenu(tree,tablaModificar));
        grid.add(tree,0,2);

    }

    private void crearEtiquetas(GridPane grid){
        javafx.scene.control.Label documentos = new javafx.scene.control.Label();
        documentos.setText("Documentos");
        grid.add(documentos,0,1);
        grid.setHalignment(grid.getChildren().get(0),HPos.CENTER);
        javafx.scene.control.Label movimientos = new javafx.scene.control.Label();
        movimientos.setText("Cambios");
        grid.add(movimientos,0,3);
        grid.setHalignment(grid.getChildren().get(1),HPos.CENTER);

    }

    private void crearMenus(BorderPane root){
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

    private TableView crearTabla(GridPane grid){
        TableView<cellFactory> tablaModificar = new TableView<>();
        tablaModificar.setEditable(true);
        tablaModificar.setPlaceholder(new javafx.scene.control.Label(""));
        TableColumn<cellFactory, String> columna1 = new TableColumn<>("Archivo");
        columna1.setMaxWidth(180);
        columna1.setMinWidth(180);
        TableColumn<cellFactory, String> columna2 = new TableColumn<>("Caracteristica");
        columna2.setMaxWidth(200);
        columna2.setMinWidth(200);
        columna2.setCellFactory(TextFieldTableCell.forTableColumn());
        columna2.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<cellFactory, String>>() {
                                      @Override
                                      public void handle(TableColumn.CellEditEvent<cellFactory, String> t) {
                                          ((cellFactory) t.getTableView().getItems().get(
                                                  t.getTablePosition().getRow())
                                          ).setCaracteristica(t.getNewValue());
                                      }
                                  }
        );
        TableColumn<cellFactory, String> columna3 = new TableColumn<>("Tipo");
        columna3.setMaxWidth(180);
        columna3.setMinWidth(180);
        columna3.setCellFactory(TextFieldTableCell.forTableColumn());
        columna3.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<cellFactory, String>>() {
                                      @Override
                                      public void handle(TableColumn.CellEditEvent<cellFactory, String> t) {
                                          ((cellFactory) t.getTableView().getItems().get(
                                                  t.getTablePosition().getRow())
                                          ).setTipo(t.getNewValue());
                                      }
                                  }
        );
        TableColumn<cellFactory, String> columna4 = new TableColumn<>("Dato");
        columna4.setMaxWidth(203);
        columna4.setMinWidth(203);
        columna4.setCellFactory(TextFieldTableCell.forTableColumn());
        columna4.setOnEditCommit( new EventHandler<TableColumn.CellEditEvent<cellFactory, String>>() {
                                      @Override
                                      public void handle(TableColumn.CellEditEvent<cellFactory, String> t) {
                                          ((cellFactory) t.getTableView().getItems().get(
                                                  t.getTablePosition().getRow())
                                          ).setDato(t.getNewValue());
                                      }
                                  }
        );
        tablaModificar.getColumns().add(columna1);
        tablaModificar.getColumns().add(columna2);
        tablaModificar.getColumns().add(columna3);
        tablaModificar.getColumns().add(columna4);
        tablaModificar.setMinSize(765, 398);
        tablaModificar.setMaxSize(765, 398);
        grid.add(tablaModificar, 1, 2);

        return tablaModificar;
    }

    private ContextMenu crearContextMenu(TreeView<String> tree, TableView<Void> tablaModificar){

        ContextMenu contextMenu = new ContextMenu();
        MenuItem subMenuModificar = new MenuItem( "Modificar");
        subMenuModificar.setOnAction(action -> uiController.agregarValorTabla(tree,tablaModificar));
        MenuItem subMenuEliminar = new MenuItem( "Eliminar");
        subMenuEliminar.setOnAction(action -> uiController.eliminarValor(tree));
        MenuItem subMenuNuevo = new MenuItem( "Nuevo");
        subMenuNuevo.setOnAction(action -> uiController.nuevoValor(tree));
        MenuItem subMenuDeshacer = new MenuItem( "Deshacer");
        MenuItem subMenuRehacer = new MenuItem( "Rehacer");
        contextMenu.getItems().addAll(subMenuModificar,subMenuEliminar,subMenuNuevo,subMenuDeshacer,subMenuRehacer);

        return contextMenu;
    }

    private void crearBotonGuardar(GridPane grid,TableView<Void> tablaModificar ){
        Button nuevoBoton = new Button();
        nuevoBoton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                crearEtiquetasCambios("Se realizó el guardado de los cambios a los documentos");
                uiController.realizarCambiosTabla(tablaModificar);
            }
        });
        nuevoBoton.setMinSize(75, 25);
        nuevoBoton.setMaxSize(75,25);
        nuevoBoton.setText("Commit");
        nuevoBoton.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(nuevoBoton,1,3);
        grid.setHalignment(grid.getChildren().get(5),HPos.CENTER);

    }

    protected static void crearVentanaCambios(GridPane grid){
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setMaxWidth(750);
        scrollPane.setMinSize(750,241);
        ventana.setMaxWidth(745);
        ventana.setMinSize(745,235);
        ventana.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        scrollPane.setContent(ventana);
        grid.add(scrollPane,0,4);
        grid.setColumnSpan(grid.getChildren().get(4), 2);
        grid.setHalignment(grid.getChildren().get(4),HPos.CENTER);

    }

    protected static void crearEtiquetasCambios(String cambios){
        Label etiqueta = new Label(cambios + ". Realizado en:  " + new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date()));
        etiqueta.setFont(new Font("Calibri",16));
        Line line1 = new Line();
        line1.setStartX(100);
        line1.setEndX(850);
        line1.setStroke(Color.LIGHTGRAY);
        ventana.getChildren().add(etiqueta);
        ventana.getChildren().add(line1);

    }

    public static void main(String[] args) {

        launch(args);
    }
}