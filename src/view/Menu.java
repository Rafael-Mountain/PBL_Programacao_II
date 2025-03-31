package view;

import view.commons.IScreen;
import view.commons.Screen;
import view.genero.GeneroMenu;

import java.util.Scanner;

public class Menu extends Screen {

    @Override
    public void draw(Scanner terminal) {
        String opcao;

        do {
            System.out.println("\n=== Bem-Vindo ao Sistema ===");
            System.out.println("1 - Criar Media");
            System.out.println("2 - Criar Genero");
            System.out.println("3 - Opção 3");
            System.out.println("4 - Opção 4");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = terminal.nextLine();

            switch (opcao) {
                case "1":
                    new MenuMedia().draw(terminal);
                    break;
                case "2":
                    new GeneroMenu().draw(terminal);
                    break;
                case "3":
                    System.out.println("Executando opção 3...");
                    break;
                case "4":
                    System.out.println("Executando opção 4...");
                    break;
                case "5":
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (!opcao.equals("5"));

    }

}
