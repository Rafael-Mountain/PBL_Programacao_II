package view.genero;

import view.commons.IScreen;
import view.commons.Screen;

import java.util.Scanner;

public class GeneroMenu extends Screen {
    @Override
    public void draw(Scanner terminal) {
        String opcao;
        do {
            System.out.println("\n=== Gênero ===");
            System.out.println("1. Adicionar Gênero");
            System.out.println("2. Listar Gêneros");
            System.out.println("3. Voltar");
            System.out.print("Escolha uma opção: ");


            opcao = terminal.nextLine();
            switch (opcao) {
                case "1":
                    new CreateGeneroForm().draw(terminal);
                    break;
                case "2":
                    new ListGenero().draw(terminal);
                    break;
                case "3":

                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != "3");
    }
}
