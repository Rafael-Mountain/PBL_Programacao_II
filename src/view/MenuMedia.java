package view;

import view.commons.IScreen;
import view.commons.Screen;
import view.filme.CreateFilmeForm;

import java.util.Scanner;

public class MenuMedia extends Screen {
    @Override
    public void draw(Scanner terminal) {
        String opcao;

        do {
            System.out.println("\n=== Criar Media ===");
            System.out.println("1 - Criar Filme");
            System.out.println("2 - Criar Serie");
            System.out.println("3 - Criar Livro");
            System.out.println("4 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = terminal.nextLine();

            switch (opcao) {
                case "1":
                    new CreateFilmeForm().draw(terminal);
                    break;
                case "2":
                    System.out.println("Executando opção 2...");
                    break;
                case "3":
                    System.out.println("Executando opção 3...");
                    break;
                case "4":
                    System.out.println("Voltando");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (!opcao.equals("4"));

    }

}
