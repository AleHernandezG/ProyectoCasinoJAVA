package Model.ModeloFichDAO;

import java.nio.file.Path;
import java.util.ArrayList;
import Model.ObjetoUsuario.Usuario;

public interface InterfazFuncionesFicheroJSON {

    // Método que se encarga de leer un fichero JSON
    public ArrayList<Usuario> leerFicheroJSON(Path nombreFichero);

    // Método que se encarga de escribir un fichero JSON
    public boolean escribirFicheroJSON(Path nombreFichero, ArrayList<Usuario> listaUsuarios);
    
} 