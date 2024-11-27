package Model.ModeloFichDAO;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Model.ObjetoUsuario.Usuario;


public class ModeloFicheroDAO implements InterfazFuncionesFicheroJSON {

    @Override
    public ArrayList<Usuario> leerFicheroJSON(Path nombreFichero) {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        try {
            // Lee el contenido del archivo como una cadena
            String contenidoJSON = Files.readString(nombreFichero);

            // Usa Gson para convertir el JSON en una lista de usuarios
            Gson gson = new Gson();
            listaUsuarios = gson.fromJson(contenidoJSON, new TypeToken<ArrayList<Usuario>>() {}.getType());

        } catch (IOException e) {
            System.err.println("Error al leer el fichero JSON: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error al procesar el JSON: " + e.getMessage());
        }

        return listaUsuarios;
    }

    @Override
    public boolean escribirFicheroJSON(Path nombreFichero, ArrayList<Usuario> listaUsuarios) {
        // Crea un objeto Gson
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Convierte la lista de usuarios a JSON
        String json = gson.toJson(listaUsuarios);

        // Escribe el JSON en el archivo especificado
        try (FileWriter writer = new FileWriter(nombreFichero.toFile())) {
            writer.write(json);
            System.out.println("Fichero JSON escrito correctamente.");
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir el fichero JSON: " + e.getMessage());
            return false;
        }
    }
    
}
