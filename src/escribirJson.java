import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class escribirJson {
    public static void main(String[] args) {
        Scanner entradas = new Scanner(System.in);
        crearJson archivo = new crearJson();
        System.out.println("Digite el nombre del archivo: ");
        String nombre = entradas.next();
        archivo.setnombre(nombre);
        System.out.println("Digite el nombre del dato: ");
        String nombredat = entradas.next();
        archivo.setnombredat(nombredat);
        System.out.println("Digite el tipo del dato: ");
        String tipodat = entradas.next();
        archivo.settipodat(tipodat);
        System.out.println("Digite el dato: ");
        String dato = entradas.next();
        archivo.setdato(dato);
        escribirJson(nombre, archivo);
    }
    protected static void escribirJson(String dato, crearJson archivo) {
        try (FileWriter file = new FileWriter("C:/Users/Andres/IdeaProjects/proyecto/src/" + dato + ".json")) {
            file.write(archivo.escribirJson().toString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + archivo.escribirJson());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}