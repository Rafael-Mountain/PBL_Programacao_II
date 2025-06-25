package com.mountain_vd.viewFX.handlers.Serie;

import com.mountain_vd.controller.search.SearchController;
import com.mountain_vd.controller.search.SearchFields;
import com.mountain_vd.controller.search.serie.SearchSerieController;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.handlers.SearchPaneController;

import java.util.HashMap;
import java.util.Map;

public class SerieSearchPaneController extends SearchPaneController {
        private final Map<String, SearchFields> fields = new HashMap<>();

        public SerieSearchPaneController(RootScene rootScene) {
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
}
