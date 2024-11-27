package View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import Control.Controlador;
import Model.ObjetoCartas.CartaPoker;
import Model.ObjetoUsuario.Usuario;

public class GamesView1 implements InterfazJuegos {
    
    public Usuario usuarioActual;
    private Controlador controlador;

    public GamesView1 (Controlador contro) {
        this.controlador = contro;
    }

    // Implement the methods from the interface InterfazJuegos
    @Override
    public void interfazMenuJuegos(Scanner scanner, Usuario usuario) {
        int option = 0;
        this.usuarioActual = usuario;

        MainView mainView = new MainView(controlador);

        do {
            mainView.clearWindow();

            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("===============================================");
            System.out.println("              SELECCION DE JUEGOS              ");
            System.out.println("===============================================");
            System.out.println("");
            System.out.println("");
            System.out.println("");

            System.out.println("===============================================");
            System.out.println("1. Blackjack");
            System.out.println("2. Ruleta");
            System.out.println("3. Dados");
            System.out.println("4. Salir");
            System.out.println("===============================================");

            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer después de nextInt
            switch (option) {
                case 1:
                    interfazBlackjack(scanner);
                    break;
                case 2:
                    interfazRuleta(scanner);
                    break;
                case 3:
                    interfazDados(scanner);
                    break;
                case 4:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (option != 4);

    }

    @Override
    public void interfazBlackjack(Scanner scanner) {
        MainView mainView = new MainView(controlador);
        int option = 0;
        do{
            mainView.clearWindow();
            
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("===============================================");
            System.out.println("                 BLACKJACK                     ");
            System.out.println("===============================================");
            System.out.println("");
            System.out.println("");
            System.out.println("");

            System.out.println("===============================================");
            System.out.println("1. Jugar");
            System.out.println("2. Salir");
            System.out.println("===============================================");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer después de nextInt

            switch (option) {
                case 1:
                    jugarBlackJack(scanner);
                    break;
                case 2:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }while(option != 2);
    }


    public void jugarBlackJack(Scanner scanner) {
        MainView mainView = new MainView(controlador);
        List<CartaPoker> cartPlayer = new ArrayList<>();
        List<CartaPoker> cartDealer = new ArrayList<>();
        double saldoApostado = 0;

        // Inicializar la baraja
        if(controlador.iniciarBaraja()) {
            System.out.println("Baraja iniciada");
        } else {
            System.out.println("Error al iniciar la baraja");
        }

        // Apostar dinero
        System.out.println("Introduce la cantidad a apostar:");

        boolean validInput = false;
        while (!validInput) {
            try {
                saldoApostado = scanner.nextDouble();
                scanner.nextLine(); // Limpiar el buffer después de nextInt

                while (saldoApostado > controlador.verSaldo(usuarioActual.getEmail())) {
                    System.out.println("No tienes suficiente saldo");
                    System.out.println("Tu saldo es: " + controlador.verSaldo(usuarioActual.getEmail()));
                    System.out.println("Introduce una cantidad válida a apostar:");
                    saldoApostado = scanner.nextDouble();
                    scanner.nextLine(); // Limpiar el buffer después de nextInt
                }
                validInput = true;
            } catch (Exception e) {
                System.out.println("Por favor, introduce un número válido.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }

        controlador.retir(usuarioActual.getEmail(), saldoApostado);

        // Repartir cartas
        for (int i = 0; i < 2; i++) {
            CartaPoker cart = controlador.pedirCarta();
            cartPlayer.add(cart);
            
            CartaPoker cart1 = controlador.pedirCarta();
            cartDealer.add(cart1);
        }

        mainView.clearWindow();
        controlador.printTable(cartPlayer, cartDealer, false);

        int option = 0;
        boolean endGame = false;
        boolean turnDealer = false;
        do {
            if(controlador.comprobarCartas(cartPlayer)){
                System.out.println("===============================================");
                System.out.println("1. Pedir carta");
                System.out.println("2. Plantarse");
                System.out.println("===============================================");
                option = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer después de nextInt

                switch (option) {
                    case 1:
                        CartaPoker cart = controlador.pedirCarta();
                        cartPlayer.add(cart);
                        mainView.clearWindow();
                        controlador.printTable(cartPlayer, cartDealer, false);
                        break;
                    case 2:
                        turnDealer = controlador.plantarse();
                        mainView.clearWindow();
                        controlador.printTable(cartPlayer, cartDealer, false);
                        break;
                    default:
                        System.out.println("Opción no válida");
                        break;
                }
                
                if(turnDealer){
                    controlador.printTable(cartPlayer, cartDealer, true);
                    boolean result = controlador.logicDealer(cartDealer, cartPlayer);
                    if(result){
                        System.out.println("Ganaste enorabuena");
                        saldoApostado *= 2;
                        controlador.deposit(usuarioActual.getEmail(), saldoApostado);
                        System.out.println("Tu saldo actual es: " + controlador.verSaldo(usuarioActual.getEmail()));
                        System.out.println("Pulsa enter para continuar");
                        scanner.nextLine();
                    }
                    else{
                        System.out.println("Perdiste, suerte la próxima vez!");
                        System.out.println("Tu saldo actual es: " + controlador.verSaldo(usuarioActual.getEmail()));
                        System.out.println("Pulsa enter para continuar");
                        scanner.nextLine();
                    }
                    endGame = true;
                }
            }else{
                System.out.println("Perdiste, suerte la próxima vez!");
                System.out.println("Tu saldo actual es: " + controlador.verSaldo(usuarioActual.getEmail()));
                System.out.println("Pulsa enter para continuar");
                scanner.nextLine();
                endGame = true;
            }

        } while (endGame == false);
        mainView.clearWindow();
        controlador.resetBaraja();
    }

    @Override
    public void interfazRuleta(Scanner scanner) {
        // TODO Auto-generated method stub
    }

    @Override
    public void interfazDados(Scanner scanner) {
        // TODO Auto-generated method stub
    }
}
