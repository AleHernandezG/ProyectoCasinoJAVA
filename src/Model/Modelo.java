package Model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import Model.ModeloFichDAO.ModeloFicheroDAO;
import Model.ObjetoUsuario.*;

public class Modelo {
    // Atributos
    ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    ArrayList<Usuario> usuariosAdmin = new ArrayList<Usuario>();
    HashMap<String, Double> fondos = new HashMap<String, Double>();
    Usuario usuarioActual = new Usuario(null, null, null, 0, null);

    private ModeloFicheroDAO ficheroDAO;

    // Constructor
    public Modelo() {
        this.ficheroDAO = new ModeloFicheroDAO();
    }

    //  Metodos
    public boolean iniciarPorDefecto(Path p)
    {
        usuarios = ficheroDAO.leerFicheroJSON(p);

        //Hacer un forech para ver si hay algun admin admiin es cuando edad = 0
        for (Usuario usuario : usuarios) {
            if (usuario.getEdad() == 0) {
                usuariosAdmin.add(usuario);
            }
        }

        //Anadimos fondos a los usuarios por defecto
        for (Usuario usuario : usuarios) {
            fondos.put(usuario.getEmail(), Math.random() * 100 * 100.0 / 100.0);
        }

        if(usuarios.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean guardarFich(Path p)
    {
        return ficheroDAO.escribirFicheroJSON(p, usuarios);
    }

    public boolean createUser(String name, String email, String password, int age, Boolean genero)
    {
        Usuario usuario = new Usuario(name, email, password, age, genero);

        if (usuarios.contains(usuario)) {
            return false;
        } else {
            usuarios.add(usuario);
            usuarioActual = usuario;
            fondos.put(email, 0.0);
            return true;
        }
    }

    public ArrayList<Usuario> getUsers()
    {
        return usuarios;
    }

    public boolean iniSession(String email, String password)
    {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getPassword().equals(password)) {
                usuarioActual = usuario;
                return true;
            }
        }
        return false;
    }

    public boolean comprobarAdmin(String email)
    {
        for (Usuario usuario : usuariosAdmin) {
            if (usuario.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean modify(String name, String emailActual, String password, int age, Boolean genero)
    {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(emailActual)) {
                usuario.setNombre(name);
                usuario.setPassword(password);
                usuario.setEdad(age);
                usuario.setGenero(genero);
                return true;
            }
        }
        return false;
    }

    public boolean deposit(String email, double cantidad)
    {
        if (fondos.containsKey(email)) {
            fondos.put(email, fondos.get(email) + cantidad);
            return true;
        } else {
            return false;
        }
    }

    public boolean retir(String email, double cantidad)
    {
        if (fondos.containsKey(email)) {
            if (fondos.get(email) >= cantidad) {
                fondos.put(email, fondos.get(email) - cantidad);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public double verSaldo(String email)
    {
        if (fondos.containsKey(email)) {
            return fondos.get(email);
        } else {
            return -1;
        }
    }

    public Usuario getUsuarioActual()
    {
        return usuarioActual;
    }
}
