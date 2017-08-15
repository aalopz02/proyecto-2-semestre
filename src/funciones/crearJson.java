package funciones;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.Scanner;

public class crearJson {

    private JSONObject caracteristica = new JSONObject();
    private JSONArray contenido = new JSONArray();
    private String nombre;
    private int cantidad;
    private Scanner entradas = new Scanner(System.in);

    protected void setnombre(String nombre){

        this.nombre = nombre;
    }

    protected void setcantidad(int cantidad){

        this.cantidad = cantidad;
    }

    protected String setnombredat(){

        System.out.println("Digite el nombre de la caracteristica: ");
        String nombredat = entradas.next();
        return nombredat;
    }

    protected String settipodat(){

        System.out.println("Digite el tipo de la caracteristica: ");
        String tipodat = entradas.next();
        return tipodat;
    }

    protected String setdato(){

        System.out.println("Digite el dato: ");
        String dato = entradas.next();
        return dato;
    }

    protected Object escribirJson(){
        while (cantidad > 0){
            JSONArray arreglo = new JSONArray();
            JSONObject datos = new JSONObject();
            arreglo.add(settipodat());
            arreglo.add(setdato());
            datos.put(setnombredat(),arreglo);
            contenido.add(datos);
            cantidad = cantidad - 1;
        }
        caracteristica.put(nombre,contenido);
        return caracteristica;
    }

    protected int lencontenido(){

        return contenido.size();
    }

}