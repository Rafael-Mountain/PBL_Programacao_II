package com.mountain_vd.viewFX.handlers;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.genero.CreateGeneroAction;
import com.mountain_vd.controller.action.genero.CreateGeneroValidation;
import com.mountain_vd.controller.dataBase.GeneroRepository;
import com.mountain_vd.controller.util.StringUtil;
import com.mountain_vd.model.Genero;
import com.mountain_vd.viewFX.RootScene;
import javafx.collections.ObservableList;

/**
 * Controlador responsável por gerenciar a lista de gêneros em formulários.
 * Permite adicionar gêneros existentes ou criar novos se não existirem no repositório.
 */
public class GenreListController {
    private final RootScene rootScene;

    /**
     * Construtor que recebe a instância da RootScene para permitir exibição de mensagens.
     *
     * @param rootScene referência à cena raiz da aplicação
     */
    public GenreListController(RootScene rootScene) {
        this.rootScene = rootScene;
    }

    /**
     * Adiciona um gênero na lista observável de gêneros.
     *
     * O método verifica se o gênero informado já existe no repositório global:
     * - Se existir e ainda não estiver na lista, adiciona o gênero existente.
     * - Se não existir, cria um novo gênero, tenta salvar via ação de criação e,
     *   em caso de sucesso, adiciona o novo gênero à lista.
     *
     * Gêneros são comparados ignorando maiúsculas/minúsculas e espaços extras.
     * Caso a criação do gênero falhe, exibe uma mensagem de erro na interface.
     *
     * @param genre  texto representando o nome do gênero a adicionar
     * @param generos lista observável de gêneros onde o novo gênero será adicionado
     */
    @SuppressWarnings("unchecked")
    public void addGenre(String genre, ObservableList generos) {
        if (genre != null && !genre.trim().isEmpty()) {
            String trimmedFilter = genre.trim();

            // Safe cast para ObservableList<Genero>
            ObservableList<Genero> generoList = (ObservableList<Genero>) generos;

            GeneroRepository repository = GeneroRepository.getInstance();

            // Procura gênero existente no repositório global
            Genero generoEncontrado = repository.getItems().stream()
                    .filter(g -> g.getNome().trim().equalsIgnoreCase(trimmedFilter))
                    .findFirst()
                    .orElse(null);

            // Adiciona gênero existente se não estiver na lista atual
            if (generoEncontrado != null && generoList.stream()
                    .noneMatch(g -> g.getNome().trim().equalsIgnoreCase(trimmedFilter))) {
                generoList.add(generoEncontrado);
            }
            // Cria novo gênero se não encontrado no repositório
            else if (generoEncontrado == null) {
                String capitalizado = StringUtil.capitalize(trimmedFilter);
                Genero novoGenero = new Genero(capitalizado);
                CreateGeneroAction createGeneroAction = new CreateGeneroAction(new CreateGeneroValidation());
                ActionResult result = createGeneroAction.execute(novoGenero);

                if (result.isSuccess()) {
                    generoList.add(novoGenero);
                } else {
                    rootScene.showError(result.getMessage());
                }
            }
        }
    }
}
