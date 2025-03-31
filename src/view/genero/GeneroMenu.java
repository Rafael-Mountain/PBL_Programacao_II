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
            System.out.println("2. Editar Gênero");
            System.out.println("3. Excluir Gênero");
            System.out.println("4. Listar Gêneros");
            System.out.println("5. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");


            opcao = terminal.nextLine();
            switch (opcao) {
                case "1":
                    new CreateGeneroForm().draw(terminal);
                    break;
                case "2":
                    break;
                case "3":
                    // Excluir Gênero
                    break;
                case "4":
                    // Listar Gêneros
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != "5");
    }
}
