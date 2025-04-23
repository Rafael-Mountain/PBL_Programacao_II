import tests.mocks.MockFilme;
import tests.mocks.MockLivro;
import tests.mocks.MockSerie;
import view.Menu;

import java.util.Scanner;

public class DiarioCultural {
    public static void main(String[] args) {
        boolean teste = true;

        if (teste){
            MockFilme.rodar();
            MockLivro.rodar();
            MockSerie.rodar();
        }
        Scanner terminal = new Scanner(System.in);
        new Menu().draw(terminal);
    }
}
