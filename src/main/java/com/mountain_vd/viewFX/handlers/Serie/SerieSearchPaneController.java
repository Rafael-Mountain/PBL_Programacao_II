package com.mountain_vd.viewFX.handlers.Serie;

import com.mountain_vd.controller.search.SearchController;
import com.mountain_vd.controller.search.SearchFields;
import com.mountain_vd.controller.search.serie.SearchSerieController;
import com.mountain_vd.model.Media;
import com.mountain_vd.model.Serie;
import com.mountain_vd.viewFX.CreateMediaPane;
import com.mountain_vd.viewFX.DisplayMediaPane;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.handlers.SearchPaneController;
import javafx.scene.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * Controlador responsável por gerenciar a busca e navegação das séries na interface gráfica.
 * Configura os campos de busca, cria o controlador de busca específico para séries,
 * disponibiliza a tela de cadastro e navega para a tela de exibição detalhada.
 */
public class SerieSearchPaneController extends SearchPaneController {
    private final Map<String, SearchFields> fields = new HashMap<>();

    /**
     * Construtor que inicializa o controlador com a cena raiz e os campos de busca.
     *
     * @param rootScene cena principal da aplicação
     */
    public SerieSearchPaneController(RootScene rootScene) {
        super(rootScene);
        initFields();
    }

    /**
     * Inicializa o mapa de campos de busca disponíveis para séries.
     * Os campos são exibidos no seletor de busca na interface.
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
     * Retorna o mapa de campos de busca disponíveis para séries.
     *
     * @return mapa chave-valor onde a chave é o nome do campo para exibição,
     *         e o valor é o enum correspondente do campo de busca
     */
    @Override
    public Map<String, SearchFields> getSearchFields() {
        return fields;
    }

    /**
     * Cria e retorna o controlador de busca para séries com base no termo e campo de busca.
     *
     * @param searchTerm termo de busca informado pelo usuário
     * @param searchField campo da busca selecionado pelo usuário
     * @return controlador de busca específico para séries
     */
    @Override
    public SearchController getSearchController(String searchTerm, SearchFields searchField) {
        return new SearchSerieController(searchTerm, searchField);
    }

    /**
     * Retorna o nó da interface gráfica para cadastro de uma nova série.
     *
     * @return nó JavaFX da tela de criação de série
     */
    @Override
    public Node getAddScreen() {
        CreateMediaPane pane = new CreateMediaPane(rootScene, new CreateSerieController(rootScene));
        return pane.getNode();
    }

    /**
     * Navega para a tela de exibição detalhada de uma série selecionada.
     *
     * @param media objeto de mídia do tipo série a ser exibido
     */
    @Override
    public void goTotDisplayPane(Media media) {
        DisplayMediaPane pane = new DisplayMediaPane(new DisplaySerieController(rootScene, (Serie) media), rootScene);
        rootScene.setMainContent(pane.getNode());
    }
}
