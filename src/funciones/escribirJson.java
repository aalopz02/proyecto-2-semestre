package funciones;

import java.io.FileWriter;
import java.io.IOException;

public class escribirJson {

    public void main(String[] args) {
        crearJson archivo = new crearJson();
        String nombre = "Persona";
        archivo.setnombre(nombre);
        archivo.setcantidad(2);
        escribirJson(nombre, archivo);
    }

    protected static void escribirJson(String dato, crearJson archivo) {

        try (FileWriter file = new FileWriter("C:/Users/Andres/IdeaProjects/proyecto/src/datos/" + dato + ".json")) {
            file.write(archivo.escribirJson().toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + archivo.escribirJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}