package View;

import java.util.Scanner;

import Model.ObjetoUsuario.Usuario;

public interface InterfazJuegos {

    public void interfazMenuJuegos(Scanner sc, Usuario usuario);

    public void interfazBlackjack(Scanner sc);

    public void interfazRuleta(Scanner sc);

    public void interfazDados(Scanner sc);
}
