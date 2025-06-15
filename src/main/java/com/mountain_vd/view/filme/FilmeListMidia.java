package com.mountain_vd.view.filme;

import com.mountain_vd.controller.search.SearchResults;
import com.mountain_vd.model.Filme;
import com.mountain_vd.view.ListMidia;
import com.mountain_vd.view.SearchView;

import java.util.Scanner;

/**
 * Classe responsável por exibir uma lista de filmes e permitir que o usuário interaja com essa lista,
 * realizando pesquisas e visualizando detalhes de cada filme.
 * <p>
 * A classe estende {@link com.mountain_vd.view.ListMidia} e fornece implementações específicas para filmes, permitindo que o
 * usuário busque filmes e veja os detalhes de um filme selecionado. Ela também é responsável por exibir a lista
 * de filmes com base nos resultados da pesquisa.
 * </p>
 */
public class FilmeListMidia  extends ListMidia {

    /**
     * Constrói uma instância de {@link FilmeListMidia} com os resultados de pesquisa fornecidos.
     *
     * @param searchResults os resultados da pesquisa de filmes
     */
    public FilmeListMidia(SearchResults searchResults) {
        super(searchResults);
    }

    /**
     * Redireciona o usuário para a tela de pesquisa de filmes.
     * Exibe a tela de pesquisa onde o usuário pode buscar por filmes.
     *
     * @param terminal o objeto scanner usado para ler a entrada do console
     */
    @Override
    public void goToSearch(Scanner terminal) {
        SearchView searchView = new SearchViewFilme();
        FilmeListMidia filmeListMidia =  new FilmeListMidia(searchView.draw(terminal));
        filmeListMidia.draw(terminal);
    }

    /**
     * Redireciona o usuário para a tela de exibição dos detalhes de um filme selecionado.
     *
     * @param terminal o objeto scanner usado para ler a entrada do console
     * @param id o identificador do filme selecionado
     */
    @Override
    public void goToDisplay(Scanner terminal, int id) {
        new FilmeDisplay((Filme) medias.get(id)).draw(terminal);
    }

    /**
     * Retorna o nome da mídia que está sendo exibida, que neste caso é "Filme".
     *
     * @return o nome da mídia
     */
    @Override
    public String getNomeMidia() {
        return "Filme";
    }
}
