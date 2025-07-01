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

public class LivroSearchPaneController extends SearchPaneController {
    private final Map<String, SearchFields> fields = new HashMap<>();

    public LivroSearchPaneController(RootScene rootScene) {
        super(rootScene);
        initFields();
    }

    private void initFields() {
        fields.put("Tudo", SearchFields.TUDO);
        fields.put("Título", SearchFields.TITULO);
        fields.put("Gênero", SearchFields.GENERO);
        fields.put("Ano de Lançamento", SearchFields.ANO_LANCAMENTO);
        fields.put("Autor", SearchFields.AUTOR);
        fields.put("ISBN", SearchFields.ISBN);
    }

    @Override
    public Map<String, SearchFields> getSearchFields() {
        return fields;
    }

    @Override
    public SearchController getSearchController(String searchTerm, SearchFields searchField) {
        return new SearchLivroController(searchTerm, searchField);
    }

    @Override
    public Node getAddScreen() {
        CreateMediaPane pane = new CreateMediaPane(rootScene,new CreateLivroController(rootScene));
        return pane.getNode();
    }

    @Override
    public void goTotDisplayPane(Media media) {
        DisplayMediaPane pane = new DisplayMediaPane(new DisplayLivroController(rootScene, (Livro) media) ,rootScene);
        rootScene.setMainContent(pane.getNode());
    }
}

