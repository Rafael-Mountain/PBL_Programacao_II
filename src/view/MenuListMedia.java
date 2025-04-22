package view;

import controller.search.SearchFields;
import controller.search.filme.SearchFilmeController;
import controller.search.livro.SearchLivroController;
import controller.search.serie.SearchSerieController;
import view.commons.Screen;
import view.filme.FilmeListMidia;
import view.livro.LivroListMidia;
import view.serie.SerieListMidia;

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
                    SearchSerieController searchSerieController = new SearchSerieController(null, SearchFields.TUDO);
                    searchSerieController.execute();
                    new SerieListMidia(searchSerieController.getSearchResults()).draw(terminal);
                    break;
                case "3":
                    SearchLivroController searchLivroController = new SearchLivroController(null, SearchFields.TUDO);
                    searchLivroController.execute();
                    new LivroListMidia(searchLivroController.getSearchResults()).draw(terminal);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (!opcao.equals("4"));

    }
}
