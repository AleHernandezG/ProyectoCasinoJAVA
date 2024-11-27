package Model.ObjetoUsuario;

public class Usuario {
    // Atributos
    private String nombre;
    private String email;
    private String password;
    private int edad;
    private Boolean genero;

    // Constructor

    public Usuario(String nombre, String email, String password, int edad, Boolean genero) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.edad = edad;
        this.genero = genero;
    }

    // Getters y Setters

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Boolean getGenero() {
        return this.genero;
    }

    public void setGenero(Boolean genero) {
        this.genero = genero;
    }

    // Métodos
    public void mostrarDatos() {
        System.out.println("Nombre: " + this.nombre);
        System.out.println("Email: " + this.email);
        System.out.println("Contraseña: " + this.password);
        System.out.println("Edad: " + this.edad);
        System.out.println("Género: " + this.genero);
    }
    
}
