package funciones;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class leerJson {
    private static List listaArchivosDisponibles = new leerIniciales().leerArchivos();

    public static void main(String[] args){
        System.out.println(listaArchivosDisponibles);
        leerCaracteristicas(listaArchivosDisponibles.indexOf("Persona.json"));
    }
    protected static JSONArray leerCaracteristicas(int indice){
        JSONParser parser = new JSONParser();
        JSONArray arregloCaracteristicas = new JSONArray();
        try {
            Object lectura = parser.parse(new FileReader("./src/datos/"+listaArchivosDisponibles.get(indice)));
            JSONObject jsonObject = (JSONObject) lectura;
            arregloCaracteristicas = (JSONArray) jsonObject.get(listaArchivosDisponibles.get(indice).toString().replaceAll(".json",""));
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(arregloCaracteristicas);
        return arregloCaracteristicas;
    }
}
