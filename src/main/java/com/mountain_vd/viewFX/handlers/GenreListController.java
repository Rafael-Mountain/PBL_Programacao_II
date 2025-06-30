package com.mountain_vd.viewFX.handlers;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.genero.CreateGeneroAction;
import com.mountain_vd.controller.action.genero.CreateGeneroValidation;
import com.mountain_vd.controller.dataBase.GeneroRepository;
import com.mountain_vd.controller.util.StringUtil;
import com.mountain_vd.model.Genero;
import com.mountain_vd.viewFX.RootScene;
import javafx.collections.ObservableList;

public class GenreListController {
    RootScene rootScene;

    public GenreListController(RootScene rootScene) {
        this.rootScene = rootScene;
    }

    @SuppressWarnings("unchecked")
    public void addGenre(String genre, ObservableList generos) {
        if (genre != null && !genre.trim().isEmpty()) {
            String trimmedFilter = genre.trim();

            // Safe cast
            ObservableList<Genero> generoList = (ObservableList<Genero>) generos;

            GeneroRepository repository = GeneroRepository.getInstance();

            // Busca um gênero existente
            Genero generoEncontrado = repository.getItems().stream()
                    .filter(g -> g.getNome().trim().equalsIgnoreCase(trimmedFilter))
                    .findFirst()
                    .orElse(null);

            if (generoEncontrado != null && generoList.stream()
                    .noneMatch(g -> g.getNome().trim().equalsIgnoreCase(trimmedFilter))) {
                generoList.add(generoEncontrado);
            } else if (generoEncontrado == null) {
                // Capitaliza o nome
                String capitalizado = StringUtil.capitalize(trimmedFilter);
                Genero novoGenero = new Genero(capitalizado);
                CreateGeneroAction createGeneroAction = new CreateGeneroAction(new CreateGeneroValidation());
                ActionResult result = createGeneroAction.execute(novoGenero);

                if (result.isSuccess()) {
                    generoList.add(novoGenero);
                }else {
                    rootScene.showError(result.getMessage());
                }
            }
        }
    }
}
