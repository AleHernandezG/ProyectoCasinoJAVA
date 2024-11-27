package Control;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import Model.Modelo;
import Model.ModeloBlacjack;
import Model.ObjetoUsuario.*;
import Model.ObjetoCartas.*;

public class Controlador {

    private Modelo modelo;
    private ModeloBlacjack modeloBlackjack;

    public Controlador() {
        this.modelo = new Modelo();
        this.modeloBlackjack = new ModeloBlacjack();
    }

    public boolean iniciarPorDefecto(Path p) {
        return modelo.iniciarPorDefecto(p);
    }

    public boolean guardarFich(Path p) {
        return modelo.guardarFich(p);
    }
    
    public boolean createUser(String name, String email, String password, int age, Boolean genero) {
        return modelo.createUser(name, email, password, age, genero);
    }

    public ArrayList<Usuario> seeUsers() {
        return modelo.getUsers();
    }

    public boolean iniSession(String email, String password) {
        return modelo.iniSession(email, password);
    }

    public boolean comprobarAdmin(String email) {
        return modelo.comprobarAdmin(email);
    }

    public boolean modify(String name, String emailActual, String password, int age, Boolean genero) {
        return modelo.modify(name, emailActual, password, age, genero);
    }

    public boolean deposit(String email, double cantidad) {
        return modelo.deposit(email, cantidad);
    }

    public boolean retir(String email, double cantidad) {
        return modelo.retir(email, cantidad);
    }

    public double verSaldo(String email) {
        return modelo.verSaldo(email);
    }

    public Usuario getUsuarioActual() {
        return modelo.getUsuarioActual();
    }

    public boolean iniciarBaraja() {
        return modeloBlackjack.iniciarBaraja();
    }

    public boolean logicDealer(List<CartaPoker> cartDealer, List<CartaPoker> cartPlayer) {
        return modeloBlackjack.logicDealer(cartDealer, cartPlayer);
    }

    public CartaPoker pedirCarta() {
        return modeloBlackjack.pedirCarta();
    }

    public boolean plantarse() {
        return modeloBlackjack.plantarse();
    }

    public void printTable(List<CartaPoker> cartPlayer, List<CartaPoker> cartDealer, boolean showDealer) {
        modeloBlackjack.printTable(cartPlayer, cartDealer, showDealer);
    }

    public boolean comprobarCartas(List<CartaPoker> cartPlayer) {
        return modeloBlackjack.comprobarCartas(cartPlayer);
    }

    public void resetBaraja() {
        modeloBlackjack.resetBaraja();
    }

}
