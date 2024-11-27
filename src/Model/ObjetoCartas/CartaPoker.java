package Model.ObjetoCartas;

public class CartaPoker extends Carta {
    // Constructor
    public CartaPoker(String palo, String tipo, int valorNumerico) {
        super(palo, tipo, valorNumerico);
    }

    // Métodos
    @Override
    public void mostrarCarta() {
        System.out.println(this.getValorNumerico() + " de " + this.getPalo());
    }
    
}
