package Model;

import java.util.ArrayList;
import java.util.List;

import Model.ObjetoCartas.CartaPoker;

public class ModeloBlacjack {
    // Baraja de cartas
    List<CartaPoker> baraja = new ArrayList<CartaPoker>();
    List<CartaPoker> barajaCopia = new ArrayList<CartaPoker>();


    //Metodos
    public boolean iniciarBaraja(){
        // Creamos los parametros del objeto cartaPoker
        String[] palos = {"Corazones", "Diamantes", "Picas", "Treboles"};
        String[] valores = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        int[] valorNumerico = {1,2,3,4,5,6,7,8,9,10,10,10,10};

        // Creamos la baraja
        for (int i = 0; i < palos.length; i++) {
            for (int j = 0; j < valores.length; j++) {
                baraja.add(new CartaPoker(palos[i], valores[j], valorNumerico[j]));
            }
        }

        // Creamos una copia de la baraja
        for (CartaPoker i : baraja) {
            barajaCopia.add(i);
        }

        if(barajaCopia.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void resetBaraja(){
        barajaCopia.clear();
        for (CartaPoker i : baraja) {
            barajaCopia.add(i);
        }
    }

    public boolean plantarse(){
        return true;
    }

    public CartaPoker pedirCarta(){
        int random = (int) (Math.random() * barajaCopia.size());
        CartaPoker carta = barajaCopia.get(random);
        barajaCopia.remove(random);
        return carta;
    }

    public boolean logicDealer(List<CartaPoker> cartDealer, List<CartaPoker> cartPlayer){
        
        int sumaDealer = 0;
        int sumaPlayer = 0;
    
        // Calcula la suma inicial del jugador
        for (CartaPoker carta : cartPlayer) {
            sumaPlayer += carta.getValorNumerico();
        }
    
        // Calcula la suma inicial del dealer
        for (CartaPoker carta : cartDealer) {
            sumaDealer += carta.getValorNumerico();
        }
    
        // Lógica del dealer: seguir pidiendo cartas hasta alcanzar al menos 17
        while (sumaDealer < 17) {
            CartaPoker carta = pedirCarta();
            cartDealer.add(carta);
            sumaDealer += carta.getValorNumerico();
            printTable(cartPlayer, cartDealer, true); // Imprime el estado de la mesa
        }
    
        // Si el dealer se pasa de 21, gana el jugador
        if (sumaDealer > 21) {
            return true;
        }
    
        // Comparación final: gana el jugador si su suma es mayor que la del dealer
        // o si la suma del dealer es menor o igual que la del jugador (empate a favor del jugador).
        if (sumaPlayer > sumaDealer) {
            return true;
        } else {
            return false; // Gana el dealer
        }
    }

    public void printTable(List<CartaPoker> cartPlayer, List<CartaPoker> cartDealer, boolean showDealer){
        int sumaDealer = 0;
        int sumaPlayer = 0;

        for(CartaPoker i : cartPlayer){
            sumaPlayer += i.getValorNumerico();
        }

        for(CartaPoker i : cartDealer){
            sumaDealer += i.getValorNumerico();
        }
        
        clearWindow();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("===============================================");
        System.out.println("               PARTIDA EN CURSO                ");
        System.out.println("===============================================");
        System.out.println("");
        System.out.println("");
        System.out.println("");

        System.out.println("===============================================");
        System.out.println("Cartas del jugador: ");
        for (CartaPoker i : cartPlayer) {
            i.mostrarCarta();
        }

        System.out.println("");
        System.out.println("La suma de las cartas del jugador es: " + sumaPlayer);
        System.out.println("Recuerda que si te pasas de 21 pierdes");
        System.out.println("===============================================");
        System.out.println("===============================================");

        System.out.println("Cartas del dealer: ");
        if(showDealer){
            for (CartaPoker i : cartDealer) {
                i.mostrarCarta();
            }
        } else {
            System.out.println("Carta oculta");
            for (int i = 1; i < cartDealer.size(); i++) {
                cartDealer.get(i).mostrarCarta();
            }
        }
        System.out.println("");
        System.out.println("La suma de las cartas del dealer es: " + sumaDealer);
        System.out.println("===============================================");
    }

    public boolean comprobarCartas(List<CartaPoker> cartPlayer){
        int suma = 0;
        for (CartaPoker i : cartPlayer) {
            suma += i.getValorNumerico();
        }

        if(suma <= 21){
            return true;
        } else {
            return false;
        }
    }

    public void clearWindow(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
