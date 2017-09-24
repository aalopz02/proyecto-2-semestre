package funciones;

import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import UI.ventanaError;
import java.util.Date;
import java.text.SimpleDateFormat;

public class crearJson {

    private String nombreArchivo;
    private String nombreCaracteristica;
    private String tipo;
    private String tipoLlave;
    private Boolean requerido;
    private Object valorDefecto;

    public void setNombreArchivo(String nombreArchivo){
        this.nombreArchivo = nombreArchivo;
    }

    public void setNombreCaracteristica(String nombreCaracteristica){
        this.nombreCaracteristica = nombreCaracteristica;
    }

    public void setTipo(String tipo){
        if (tipo.equals("Int") || tipo.equals("String") || tipo.equals("Double") || tipo.equals("Date")){
            this.tipo = tipo;
        }
        else{
            new ventanaError().crearVentana(new Stage(),"Error Tipo");
            this.tipo = "String";
        }
    }

    public void setTipoLlave(String tipoLlave) {
            this.tipoLlave = tipoLlave;
    }

    public void setRequerido(Boolean requerido){
        this.requerido = requerido;
    }

    public void setValorDefecto(String valorDefecto){
        if (requerido){
            if (tipo.toString() == "Int"){this.valorDefecto = 123456;}
            if (tipo.toString() == "String"){this.valorDefecto = "abcd";}
            if (tipo.toString() == "Double"){this.valorDefecto = 12.34;}
            if (tipo.toString() == "Date"){this.valorDefecto = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date());}
        }
        else this.valorDefecto = valorDefecto;
    }

    public JSONArray crearListaJson(){
        JSONArray listaObjetos = new JSONArray();
        JSONObject caracteristica = new JSONObject();
        JSONArray arreglo = new JSONArray();
        arreglo.add(tipo);
        arreglo.add(tipoLlave);
        arreglo.add(requerido);
        arreglo.add(valorDefecto);
        caracteristica.put(nombreCaracteristica,arreglo);
        listaObjetos.add(caracteristica);
        return(listaObjetos);
    }

    public JSONObject escribirJson(){
        JSONObject objetoJson = new JSONObject();
        objetoJson.put(nombreArchivo,crearListaJson());
        return objetoJson;
    }

}