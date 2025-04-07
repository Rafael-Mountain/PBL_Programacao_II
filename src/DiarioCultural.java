import view.Menu;

import java.util.Scanner;

public class DiarioCultural {
    public static void main(String[] args) {
        Scanner terminal = new Scanner(System.in);
        Testes.rodar3();
        new Menu().draw(terminal);
    }
}
