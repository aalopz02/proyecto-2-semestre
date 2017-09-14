package funciones;

import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class escribirJson {

    public static void escribirArchivo(String dato, crearJson archivo, Boolean reescribir) {

        if (reescribir == true){

            try (FileWriter file = new FileWriter("./src/datos/" + dato + ".json")) {
                file.write(archivo.escribirJson().toString());
            } catch (IOException e) {
                UI.ventanaError.crearVentana(new Stage(),"Error Escritura");
            }
        }
        else{

            JSONObject archivoNuevo = new JSONObject();
            JSONArray arreglo = new leerJson().leerNombres(dato);
            arreglo.add(archivo.crearListaJson().get(0));
            archivoNuevo.put(dato,arreglo);
            try (FileWriter file = new FileWriter("./src/datos/" + dato + ".json")) {
                file.write(archivoNuevo.toString());
            } catch (IOException e) {
                UI.ventanaError.crearVentana(new Stage(),"Error Escritura");
            }
        }

    }
}