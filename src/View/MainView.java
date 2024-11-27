package View;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import Control.Controlador;
import Model.ObjetoUsuario.*;

public class MainView {

    private Controlador controlador;

    public MainView(Controlador controlador) {
        this.controlador = controlador;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        Controlador controlador = new Controlador();
        MainView mainView = new MainView(controlador);

        mainView.clearWindow();
        Path p = args.length > 0 ? Path.of(args[0]) : null;
        mainView.menuPrincipal(scanner,p);

        scanner.close(); // Cerrar scanner al final del programa
    }

    public void menuPrincipal(Scanner scanner, Path p) {

        if(controlador.iniciarPorDefecto(p)) {
            System.out.println("Usuarios por defecto creados correctamente");
            System.out.println();
        } else {
            System.out.println("Error al crear los usuarios por defecto");
            System.out.println();
        }

        int option = 0;
        do {
            if(option != 0){clearWindow();}

            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("===============================================");
            System.out.println("              MENÚ PRINCIPAL");
            System.out.println("===============================================");
            System.out.println("");
            System.out.println("");
            System.out.println("");

            System.out.println("===============================================");
            System.out.println("1. Crear un nuevo usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Limpieza de pantalla");
            System.out.println("4. Salir");
            System.out.println("Introduce el número de la opción que deseas:");
            System.out.println("===============================================");

            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer después de nextInt
            switch (option) {
                case 1:
                    createUser(scanner); // Pasar el scanner como parámetro
                    break;
                case 2:
                    iniSession(scanner);
                    break;
                case 3:
                    clearWindow();
                    break;
                case 4:
                    if(controlador.guardarFich(p)) {
                        System.out.println("Usuarios guardados correctamente");
                    } else {
                        System.out.println("Error al guardar los usuarios");
                    }
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (option != 4);
    }
    
    public void createUser(Scanner scanner) {
        System.out.flush();
        boolean resultado = false;

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("===============================================");
        System.out.println("    BIENVENIDO A LA CREACXION DE USUARIO");
        System.out.println("===============================================");
        System.out.println("");
        System.out.println("");
        System.out.println("");

        System.out.println("Introduce tu nombre:");
        String name = scanner.nextLine();
        System.out.println("Introduce tu email:");
        String email = scanner.nextLine();
        System.out.println("Introduce tu contraseña:");
        String password = scanner.nextLine();

        int age = 0;
        boolean validInput = false;
        do {
            try {
            System.out.println("Introduce tu edad:");
            age = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer después de nextInt
            validInput = true;
            } catch (Exception e) {
            System.out.println("Por favor, introduce un número válido para la edad.");
            scanner.nextLine(); // Limpiar el buffer después de la entrada inválida
            }
        } while (!validInput);

        Boolean genero = null;
        validInput = false;
        do {
            try {
            System.out.println("Introduce tu género (true para masculino, false para femenino):");
            genero = scanner.nextBoolean();
            scanner.nextLine(); // Limpiar el buffer después de nextBoolean
            validInput = true;
            } catch (Exception e) {
            System.out.println("Por favor, introduce un valor válido para el género (true o false).");
            scanner.nextLine(); // Limpiar el buffer después de la entrada inválida
            }
        } while (!validInput);

        if (controlador.createUser(name, email, password, age, genero)) {
            System.out.println("Usuario creado correctamente");
            resultado = controlador.iniSession(email, password);
        } else {
            System.out.println("Error al crear el usuario");
        }

        System.out.println("Pulsa enter para continuar");
        scanner.nextLine();

        if(resultado) {
            boolean permit = controlador.comprobarAdmin(email);
            menuUser(scanner, permit);
        }
    }

    public void iniSession(Scanner scanner) {
        System.out.flush();

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("===============================================");
        System.out.println("    INICIO DE SESIÓN");
        System.out.println("===============================================");
        System.out.println("");
        System.out.println("");
        System.out.println("");

        System.out.println("Introduce tu email:");
        String email = scanner.nextLine();
        System.out.println("Introduce tu contraseña:");
        String password = scanner.nextLine();

        if (controlador.iniSession(email, password)) {
            System.out.println("Inicio de sesión correcto");
            
            boolean permit = controlador.comprobarAdmin(email);

            System.out.println("Pulsa enter para continuar");
            scanner.nextLine();

            menuUser(scanner, permit);
        } else {
            System.out.println("Error al iniciar sesión");

            System.out.println("Pulsa enter para continuar");
            scanner.nextLine();
        }
    }
    
    public void menuUser(Scanner scanner, boolean isAdmin) {
        
        int option = 0;
        do {
            clearWindow();
            Usuario usuarioActual = controlador.getUsuarioActual();
            System.out.println("===============================================");
            System.out.println("            BIENVENIDO " + usuarioActual.getNombre().toUpperCase());
            System.out.println("===============================================");

            System.out.println("1. Ver usuarios");
            System.out.println("2. Depositar fondos");
            System.out.println("3. Retirar fondos");
            System.out.println("4. Ver saldo");
            System.out.println("5. Modificar datos");
            System.out.println("6. Jugar");
            System.out.println("7. Cerrar sesión");

            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer después de nextInt
            switch (option) {
                case 1:
                    if(isAdmin) seeUsers(scanner);
                    else System.out.println("No tienes permisos para ver los usuarios");
                    break;
                case 2:
                    deposit(scanner);
                    break;
                case 3:
                    retir(scanner);
                    break;
                case 4:
                    System.out.println("Tu saldo es: " + controlador.verSaldo(usuarioActual.getEmail()));
                    System.out.println("Pulsa enter para continuar");
                    scanner.nextLine();
                    break;
                case 5:
                    modify(scanner);
                    break;
                case 6:
                    play(scanner);
                    break;
                case 7:
                    System.out.println("Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (option != 7);
    }

    public void seeUsers(Scanner scanner) {
        List<Usuario> usuarios = controlador.seeUsers();

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("===============================================");
        System.out.println("    ZONA DE USUARIOS");
        System.out.println("===============================================");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("===============================================");

        for (Usuario usuario : usuarios) {
            usuario.mostrarDatos();
            System.out.println("===============================================");
        }
        System.out.println("Pulsa enter para continuar");
        scanner.nextLine();
    }

    public void deposit(Scanner scanner) {
        boolean success = false;

        Usuario usuarioActual = controlador.getUsuarioActual();
        while (!success) {
            try {
                System.out.println("Introduce la cantidad a depositar:");
                double cantidad = scanner.nextDouble();
                scanner.nextLine(); // Limpiar el buffer después de nextDouble

                if (controlador.deposit(usuarioActual.getEmail(),cantidad)) {
                    System.out.println("Depósito realizado correctamente");
                    success = true;
                } else {
                    System.out.println("Error al realizar el depósito");
                }
            } catch (Exception e) {
                System.out.println("Entrada no válida. Por favor, introduce un número.");
                scanner.nextLine(); // Limpiar el buffer después de una entrada no válida
            }
        }

        System.out.println("Pulsa enter para continuar");
        scanner.nextLine();
    }

    public void retir(Scanner scanner) {
        boolean success = false;
        Usuario usuarioActual = controlador.getUsuarioActual();
        while (!success) {
            try {
                System.out.println("Introduce la cantidad a retirar:");
                double cantidad = scanner.nextDouble();
                scanner.nextLine(); // Limpiar el buffer después de nextDouble

                if (controlador.retir(usuarioActual.getEmail(),cantidad)) {
                    System.out.println("Retiro realizado correctamente");
                    success = true;
                } else {
                    System.out.println("Error al realizar el retiro");
                }
            } catch (Exception e) {
                System.out.println("Entrada no válida. Por favor, introduce un número.");
                scanner.nextLine(); // Limpiar el buffer después de una entrada no válida
            }
        }

        System.out.println("Pulsa enter para continuar");
        scanner.nextLine();
    }

    public void modify(Scanner scanner) {
        System.out.flush();

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("===============================================");
        System.out.println("    MODIFICAR DATOS");
        System.out.println("===============================================");
        System.out.println("");
        System.out.println("");
        System.out.println("");

        Usuario usuarioActual = controlador.getUsuarioActual();
        String emailActual = usuarioActual.getEmail();

        System.out.println("Introduce tu nombre:");
        String name = scanner.nextLine();
        System.out.println("Introduce tu contraseña:");
        String password = scanner.nextLine();

        int age = 0;
        boolean validInput = false;
        do {
            try {
                System.out.println("Introduce tu edad:");
                age = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer después de nextInt
                validInput = true;
            } catch (Exception e) {
                System.out.println("Por favor, introduce un número válido para la edad.");
                scanner.nextLine(); // Limpiar el buffer después de la entrada inválida
            }
        } while (!validInput);

        Boolean genero = null;
        validInput = false;
        do {
            try {
                System.out.println("Introduce tu género (true para masculino, false para femenino):");
                genero = scanner.nextBoolean();
                scanner.nextLine(); // Limpiar el buffer después de nextBoolean
                validInput = true;
            } catch (Exception e) {
                System.out.println("Por favor, introduce un valor válido para el género (true o false).");
                scanner.nextLine(); // Limpiar el buffer después de la entrada inválida
            }
        } while (!validInput);

        if (controlador.modify(name, emailActual, password, age, genero)) {
            System.out.println("Datos modificados correctamente");
        } else {
            System.out.println("Error al modificar los datos");
        }

        System.out.println("Pulsa enter para continuar");
        scanner.nextLine();
    }

    public void clearWindow() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void play(Scanner scanner) {
        System.out.println("Jugando...");
        System.out.println("Pulsa enter para continuar");
        scanner.nextLine();

        Usuario usuarioActual = controlador.getUsuarioActual();
        GamesView1 gamesView1 = new GamesView1(controlador);

        gamesView1.interfazMenuJuegos(scanner, usuarioActual);
    }
}
