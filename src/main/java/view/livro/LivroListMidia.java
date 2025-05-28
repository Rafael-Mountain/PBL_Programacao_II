package view.livro;

import controller.search.SearchResults;
import model.Livro;
import view.ListMidia;
import view.SearchView;

import java.util.Scanner;

/**
 * Classe responsável por exibir a lista de livros e permitir ações de busca e exibição dos detalhes de um livro.
 * Estende a classe {@link ListMidia} para aproveitar o comportamento genérico da lista de mídias, adaptando-o para o caso dos livros.
 */
public class LivroListMidia extends ListMidia {

    /**
     * Construtor que inicializa a lista de livros com os resultados da busca.
     *
     * @param searchResults Os resultados da busca que serão exibidos.
     */
    public LivroListMidia(SearchResults searchResults) {
        super(searchResults);
    }

    /**
     * Exibe a tela de busca de livros.
     *
     * @param terminal O scanner para capturar a entrada do usuário.
     */
    @Override
    public void goToSearch(Scanner terminal) {
        SearchView searchView = new SearchViewLivro();
        LivroListMidia livroListMidia = new LivroListMidia(searchView.draw(terminal));
        livroListMidia.draw(terminal);
    }

    /**
     * Exibe os detalhes de um livro selecionado a partir da lista de mídias.
     *
     * @param terminal O scanner para capturar a entrada do usuário.
     * @param id O ID do livro a ser exibido.
     */
    @Override
    public void goToDisplay(Scanner terminal, int id) {
        new LivroDisplay((Livro) medias.get(id)).draw(terminal);
    }

    /**
     * Retorna o nome da mídia, que neste caso é "Livro".
     *
     * @return O nome da mídia ("Livro").
     */
    @Override
    public String getNomeMidia() {
        return "Livro";
    }
}
