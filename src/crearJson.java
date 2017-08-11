import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
class crearjson {
    private JSONObject caracteristica = new JSONObject();
    private JSONObject contenido = new JSONObject();
    private String nombre;
    private String nombredat;
    private String tipodat;
    private String dato;
    protected void setnombre(String nombre){
        this.nombre = nombre;
    }
    protected void setnombredat(String nombredat){
        this.nombredat = nombredat;
    }
    protected void settipodat(String tipodat){
        this.tipodat = tipodat;
    }
    protected void setdato(String dato){
        this.dato = dato;
    }
    protected void escribirJson(){
        JSONArray arreglo = new JSONArray();
        arreglo.add(tipodat);
        arreglo.add(dato);
        contenido.put(nombredat,arreglo);
        System.out.println(contenido);
        caracteristica.put(nombre,contenido);
        System.out.println(caracteristica);
    }

}