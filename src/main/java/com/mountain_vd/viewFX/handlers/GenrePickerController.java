package com.mountain_vd.viewFX.handlers;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.genero.CreateGeneroAction;
import com.mountain_vd.controller.action.genero.CreateGeneroValidation;
import com.mountain_vd.controller.dataBase.GeneroRepository;
import com.mountain_vd.controller.util.StringUtil;
import com.mountain_vd.model.Genero;
import javafx.collections.ObservableList;

public class GenrePickerController {

    public static void addGenre(String genre, ObservableList<Genero> generos) {
        if (genre != null && !genre.trim().isEmpty()) {
            String trimmedFilter = genre.trim();

            GeneroRepository repository = GeneroRepository.getInstance();

            // Busca um gênero no repositório com nome igual, ignorando maiúsculas/minúsculas e espaços
            Genero generoEncontrado = repository.getItems().stream()
                    .filter(g -> g.getNome().trim().equalsIgnoreCase(trimmedFilter))
                    .findFirst()
                    .orElse(null);

            // Se encontrado e ainda não estiver na lista, adiciona
            if (generoEncontrado != null && generos.stream()
                    .noneMatch(g -> g.getNome().trim().equalsIgnoreCase(trimmedFilter))) {
                generos.add(generoEncontrado);
            } else if (generoEncontrado == null) {
                // Capitaliza todas as palavras (ex: "rock alternativo" → "Rock Alternativo")
                String capitalizado = StringUtil.capitalize(trimmedFilter);

                Genero novoGenero = new Genero(capitalizado);
                CreateGeneroAction createGeneroAction = new CreateGeneroAction(new CreateGeneroValidation());
                ActionResult result = createGeneroAction.execute(novoGenero); // Executa a ação de criação

                if (result.isSuccess()) {
                    generos.add(novoGenero); // Adiciona o novo gênero à lista
                }
            }
        }
    }
}
