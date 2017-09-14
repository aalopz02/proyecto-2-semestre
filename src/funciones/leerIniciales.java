package funciones;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class leerIniciales {

    public List leerArchivos(){
        File carpeta = new File("./src/datos");
        File[] listaArchivos = carpeta.listFiles();
        List listaArchivosLista = new ArrayList();
        for (int i = 0; i < listaArchivos.length; i++) {
            if (listaArchivos[i].isFile()) {
                listaArchivosLista.add(listaArchivos[i].getName());
                }
            }
            return listaArchivosLista;
        }
}
