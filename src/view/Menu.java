package view;

import view.commons.Screen;
import view.genero.GeneroMenu;

import java.util.Scanner;

public class Menu extends Screen {

    @Override
    public void draw(Scanner terminal) {
        String opcao;

        do {
            System.out.println("\n=== Bem-Vindo ao Diario Cultural ===");
            System.out.println("1 - Criar Midia");
            System.out.println("2 - Listar Midia");
            System.out.println("3 - Generos");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = terminal.nextLine();

            switch (opcao) {
                case "1":
                    new MenuCreateMedia().draw(terminal);
                    break;
                case "2":
                    new MenuListMedia().draw(terminal);
                    break;
                case "3":
                    new GeneroMenu().draw(terminal);
                    break;
                case "4":
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (!opcao.equals("4"));

    }

}
