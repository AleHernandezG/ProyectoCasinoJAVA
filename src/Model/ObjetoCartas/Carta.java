package Model.ObjetoCartas;

public abstract class Carta {
    // Atributos
    private String palo;
    private String tipo;
    private int valorNumerico;

    // Constructor
    public Carta(String palo, String tipo, int valorNumerico) {
        this.palo = palo;
        this.tipo = tipo;
        this.valorNumerico = valorNumerico;
    }

    // Getters y Setters
    public String getPalo() {
        return this.palo;
    }

    public void setPalo(String palo) {
        this.palo = palo;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getValorNumerico() {
        return this.valorNumerico;
    }

    public void setValorNumerico(int valorNumerico) {
        this.valorNumerico = valorNumerico;
    }

    // Métodos
    public abstract void mostrarCarta();
}
