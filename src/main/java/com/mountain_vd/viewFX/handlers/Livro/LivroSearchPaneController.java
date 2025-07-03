package com.mountain_vd.viewFX.handlers.Livro;

import com.mountain_vd.controller.search.SearchController;
import com.mountain_vd.controller.search.SearchFields;
import com.mountain_vd.controller.search.livro.SearchLivroController;
import com.mountain_vd.model.Livro;
import com.mountain_vd.model.Media;
import com.mountain_vd.viewFX.DisplayMediaPane;
import com.mountain_vd.viewFX.CreateMediaPane;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.handlers.SearchPaneController;
import javafx.scene.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador específico para a tela de busca de livros.
 * Configura os campos disponíveis para busca, cria o controlador de busca adequado,
 * e gerencia as transições para telas de criação e exibição de livros.
 */
public class LivroSearchPaneController extends SearchPaneController {

    /** Mapeamento dos nomes visíveis dos campos para os enums de busca */
    private final Map<String, SearchFields> fields = new HashMap<>();

    /**
     * Construtor que recebe a cena raiz para manipulação da interface.
     *
     * @param rootScene cena principal da aplicação
     */
    public LivroSearchPaneController(RootScene rootScene) {
        super(rootScene);
        initFields();
    }

    /**
     * Inicializa o mapa de campos que podem ser usados na busca de livros,
     * mapeando nomes amigáveis para os valores do enum {@link SearchFields}.
     */
    private void initFields() {
        fields.put("Tudo", SearchFields.TUDO);
        fields.put("Título", SearchFields.TITULO);
        fields.put("Gênero", SearchFields.GENERO);
        fields.put("Ano de Lançamento", SearchFields.ANO_LANCAMENTO);
        fields.put("Autor", SearchFields.AUTOR);
        fields.put("ISBN", SearchFields.ISBN);
    }

    /**
     * Retorna o mapa de campos disponíveis para busca, onde a chave é o nome
     * a ser exibido na interface e o valor é o campo usado na lógica de busca.
     *
     * @return mapa de campos de busca
     */
    @Override
    public Map<String, SearchFields> getSearchFields() {
        return fields;
    }

    /**
     * Cria e retorna o controlador de busca específico para livros, com base
     * no termo de busca e no campo selecionado.
     *
     * @param searchTerm termo de busca informado pelo usuário
     * @param searchField campo onde a busca deve ser realizada
     * @return controlador de busca configurado para livros
     */
    @Override
    public SearchController getSearchController(String searchTerm, SearchFields searchField) {
        return new SearchLivroController(searchTerm, searchField);
    }

    /**
     * Retorna a interface de criação de um novo livro, embutida dentro do painel de criação de mídia.
     *
     * @return nó JavaFX contendo o formulário de criação de livro
     */
    @Override
    public Node getAddScreen() {
        CreateMediaPane pane = new CreateMediaPane(rootScene, new CreateLivroController(rootScene));
        return pane.getNode();
    }

    /**
     * Navega para a tela de exibição detalhada do livro selecionado,
     * configurando o conteúdo principal da cena raiz.
     *
     * @param media objeto de mídia que será exibido, esperado ser do tipo {@link Livro}
     */
    @Override
    public void goTotDisplayPane(Media media) {
        DisplayMediaPane pane = new DisplayMediaPane(new DisplayLivroController(rootScene, (Livro) media), rootScene);
        rootScene.setMainContent(pane.getNode());
    }
}
