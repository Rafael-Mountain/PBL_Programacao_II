package view;

import controller.search.SearchFields;
import controller.search.filme.SearchFilmeController;
import view.commons.Screen;
import view.filme.FilmeListMidia;

import java.util.Scanner;

public class MenuListMedia extends Screen {
    @Override
    public void draw(Scanner terminal) {
        String opcao;

        do {
            System.out.println("\n=== Listar Media ===");
            System.out.println("1 - Listar Filme");
            System.out.println("2 - Listar Serie");
            System.out.println("3 - Listar Livro");
            System.out.println("4 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = terminal.nextLine();

            switch (opcao) {
                case "1":
                    SearchFilmeController searchController = new SearchFilmeController(null, SearchFields.TUDO);
                    searchController.execute();
                    new FilmeListMidia(searchController.getSearchResults()).draw(terminal);
                    break;
                case "2":
                    System.out.println("Executando opção 2...");
                    break;
                case "3":
                    System.out.println("Executando opção 3...");
                    break;
                case "4":
                    System.out.println("Voltando");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (!opcao.equals("4"));

    }
}
