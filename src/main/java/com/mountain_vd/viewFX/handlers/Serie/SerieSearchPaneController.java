package com.mountain_vd.viewFX.handlers.Serie;

import com.mountain_vd.controller.search.SearchController;
import com.mountain_vd.controller.search.SearchFields;
import com.mountain_vd.controller.search.serie.SearchSerieController;
import com.mountain_vd.viewFX.handlers.Filme.CreateFilmeController;
import com.mountain_vd.viewFX.CreateMediaPane;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.handlers.SearchPaneController;
import javafx.scene.Node;

import java.util.HashMap;
import java.util.Map;

public class SerieSearchPaneController extends SearchPaneController {
        private final Map<String, SearchFields> fields = new HashMap<>();

        public SerieSearchPaneController(RootScene rootScene) {
            super(rootScene);
            initFields();
        }

        private void initFields() {
            fields.put("Título", SearchFields.TITULO);
            fields.put("Gênero", SearchFields.GENERO);
            fields.put("Ano de Lançamento", SearchFields.ANO_LANCAMENTO);
            fields.put("Diretor", SearchFields.DIRETOR);
            fields.put("Duração", SearchFields.DURACAO);
            fields.put("Elenco", SearchFields.ELENCO);
            fields.put("Roteiro", SearchFields.ROTEIRO);
        }

        @Override
        public Map<String, SearchFields> getSearchFields() {
            return fields;
        }

        @Override
        public SearchController getSearchController(String searchTerm, SearchFields searchField) {
            return new SearchSerieController(searchTerm, searchField);
        }

        @Override
        public Node getAddScreen() {
            CreateMediaPane pane = new CreateMediaPane(rootScene,new CreateSerieController(rootScene));
            return pane.getNode();
        }
}
