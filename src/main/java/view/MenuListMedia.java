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

/**
 * Classe que representa o menu para listar mídias.
 * O menu oferece opções para listar filmes, séries ou livros.
 */
public class MenuListMedia extends Screen {

    /**
     * Método responsável por desenhar e exibir o menu de listagem de mídias.
     * O menu permite ao usuário escolher entre listar filmes, séries ou livros,
     * ou voltar para o menu anterior.
     *
     * @param terminal Scanner usado para capturar entradas do usuário.
     */
    @Override
    public void draw(Scanner terminal) {
        String opcao;

        do {
            // Exibe o menu de listagem de mídias com as opções disponíveis
            System.out.println("\n=== Listar Media ===");
            System.out.println("1 - Listar Filme");
            System.out.println("2 - Listar Serie");
            System.out.println("3 - Listar Livro");
            System.out.println("4 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = terminal.nextLine();

            // Processa a opção escolhida pelo usuário
            switch (opcao) {
                case "1":
                    // Executa a busca e exibe a lista de filmes
                    SearchFilmeController searchController = new SearchFilmeController(null, SearchFields.TUDO);
                    searchController.execute();
                    new FilmeListMidia(searchController.getSearchResults()).draw(terminal);
                    break;
                case "2":
                    // Executa a busca e exibe a lista de séries
                    SearchSerieController searchSerieController = new SearchSerieController(null, SearchFields.TUDO);
                    searchSerieController.execute();
                    new SerieListMidia(searchSerieController.getSearchResults()).draw(terminal);
                    break;
                case "3":
                    // Executa a busca e exibe a lista de livros
                    SearchLivroController searchLivroController = new SearchLivroController(null, SearchFields.TUDO);
                    searchLivroController.execute();
                    new LivroListMidia(searchLivroController.getSearchResults()).draw(terminal);
                    break;
                case "4":
                    // Volta ao menu anterior
                    return;
                default:
                    // Caso o usuário digite uma opção inválida
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (!opcao.equals("4")); // O loop continua até que o usuário escolha voltar
    }
}
