package funciones;

import java.io.FileWriter;
import java.io.IOException;

public class escribirJson {

    protected static void escribirJson(String dato, crearJson archivo) {

        try (FileWriter file = new FileWriter("./src/datos/" + dato + ".json")) {
            file.write(archivo.escribirJson().toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + archivo.escribirJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}