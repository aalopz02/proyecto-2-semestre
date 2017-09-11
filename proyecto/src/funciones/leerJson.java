package funciones;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class leerJson {

    public static JSONArray leerNombres(String nomArchivo){
        JSONParser parser = new JSONParser();
        JSONArray arregloCaracteristicas = new JSONArray();
        try {
            Object lectura = parser.parse(new FileReader("./src/datos/"+nomArchivo+".json"));
            JSONObject jsonObject = (JSONObject) lectura;
            arregloCaracteristicas = (JSONArray) jsonObject.get((String) nomArchivo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return arregloCaracteristicas;
    }

    public static Set<String> leerLlave(String nomArchivo, int indice){
        JSONArray listaCaracteristicas = leerNombres(nomArchivo);
        JSONObject objetoJson = (JSONObject) listaCaracteristicas.get(indice);
        Set<String> llaveJson = objetoJson.keySet();
        return llaveJson;
    }

    public static String leerCaracteristicas(String nomArchivo, int indice){
        JSONArray listaCaracteristicas = leerNombres(nomArchivo);
        JSONObject objetoJson = (JSONObject) listaCaracteristicas.get(indice);
        Set<String> llave = leerLlave(nomArchivo,indice);
        ArrayList caracteristica = (ArrayList) objetoJson.get(llave.toString().replace("]","").replace("[",""));

        return (String) caracteristica.get(1);
    }

    public static String leerTipo(String nomArchivo, int indice){
        JSONArray listaCaracteristicas = leerNombres(nomArchivo);
        JSONObject objetoJson = (JSONObject) listaCaracteristicas.get(indice);
        Set<String> llave = leerLlave(nomArchivo,indice);
        ArrayList caracteristica = (ArrayList) objetoJson.get(llave.toString().replace("]","").replace("[",""));

        return (String) caracteristica.get(0);
    }
}