import tests.Testes;
import view.Menu;

import java.util.Scanner;

public class DiarioCultural {
    public static void main(String[] args) {
        Scanner terminal = new Scanner(System.in);
        Testes.rodar3(); // Dados estáticos para teste.
        new Menu().draw(terminal);
    }
}
