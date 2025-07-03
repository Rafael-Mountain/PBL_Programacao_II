package com.mountain_vd.viewFX.handlers.Filme;

import com.mountain_vd.controller.search.SearchController;
import com.mountain_vd.controller.search.SearchFields;
import com.mountain_vd.controller.search.filme.SearchFilmeController;
import com.mountain_vd.model.Filme;
import com.mountain_vd.model.Media;
import com.mountain_vd.viewFX.CreateMediaPane;
import com.mountain_vd.viewFX.DisplayMediaPane;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.handlers.SearchPaneController;
import javafx.scene.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador da tela de busca para filmes.
 * Gerencia os campos de busca específicos para filmes e
 * cria os controladores de busca e telas relacionadas à manipulação de filmes.
 */
public class FilmeSearchPaneController extends SearchPaneController {

    /**
     * Mapeamento entre o nome do campo de busca visível ao usuário
     * e o identificador usado internamente para realizar a busca.
     */
    private final Map<String, SearchFields> fields = new HashMap<>();

    /**
     * Construtor que recebe a cena raiz da aplicação.
     * Inicializa os campos de busca específicos para filmes.
     *
     * @param rootScene a cena principal da aplicação
     */
    public FilmeSearchPaneController(RootScene rootScene) {
        super(rootScene);
        initFields();
    }

    /**
     * Inicializa o mapa de campos de busca para filmes,
     * adicionando os campos relevantes para busca de filmes.
     */
    private void initFields() {
        fields.put("Título", SearchFields.TITULO);
        fields.put("Gênero", SearchFields.GENERO);
        fields.put("Ano de Lançamento", SearchFields.ANO_LANCAMENTO);
        fields.put("Diretor", SearchFields.DIRETOR);
        fields.put("Duração", SearchFields.DURACAO);
        fields.put("Elenco", SearchFields.ELENCO);
        fields.put("Roteiro", SearchFields.ROTEIRO);
    }

    /**
     * Retorna o mapa de campos de busca visíveis ao usuário
     * e seus identificadores internos.
     *
     * @return mapa dos campos de busca para filmes
     */
    @Override
    public Map<String, SearchFields> getSearchFields() {
        return fields;
    }

    /**
     * Cria e retorna um controlador de busca para filmes,
     * configurado com o termo e campo de busca especificados.
     *
     * @param searchTerm termo que será buscado
     * @param searchField campo onde a busca será realizada
     * @return controlador responsável por realizar a busca de filmes
     */
    @Override
    public SearchController getSearchController(String searchTerm, SearchFields searchField) {
        return new SearchFilmeController(searchTerm, searchField);
    }

    /**
     * Retorna a tela de criação para adicionar um novo filme.
     *
     * @return nó da interface gráfica para criar um filme
     */
    @Override
    public Node getAddScreen() {
        CreateMediaPane pane = new CreateMediaPane(rootScene, new CreateFilmeController(rootScene));
        return pane.getNode();
    }

    /**
     * Navega para a tela de exibição detalhada de um filme,
     * recebendo a mídia selecionada e abrindo o display correspondente.
     *
     * @param media objeto de mídia que deve ser exibido (espera-se que seja um Filme)
     */
    @Override
    public void goTotDisplayPane(Media media) {
        DisplayMediaPane pane = new DisplayMediaPane(new DisplayFilmeController(rootScene, (Filme) media), rootScene);
        rootScene.setMainContent(pane.getNode());
    }
}
