package funciones;

import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class escribirJson {

    public static void escribirArchivo(String dato, crearJson archivo, ArrayList indice) {

        if ((Boolean) indice.get(0)){

            try (FileWriter file = new FileWriter("./src/datos/" + dato + ".json")) {
                file.write(archivo.escribirJson().toString());
            } catch (IOException e) {
                UI.ventanaError.crearVentana(new Stage(),"Error Escritura");
            }
        }
        else{

            JSONObject archivoNuevo = new JSONObject();
            ArrayList arreglo = new leerJson().leerNombres(dato);
            if (indice.get(1).equals("+")){
                arreglo.add(archivo.crearListaJson().get(0));
                archivoNuevo.put(dato,arreglo);
            }
            else{
                try {
                    arreglo.set((int) indice.get(1),archivo.crearListaJson().get(0));
                    archivoNuevo.put(dato,arreglo);
                }catch (IndexOutOfBoundsException e){
                    archivoNuevo.put(dato,arreglo);
                }
            }
            try (FileWriter file = new FileWriter("./src/datos/" + dato + ".json")) {
                file.write(archivoNuevo.toString());
            } catch (IOException e) {
                UI.ventanaError.crearVentana(new Stage(),"Error Escritura");
            }
        }

    }
}