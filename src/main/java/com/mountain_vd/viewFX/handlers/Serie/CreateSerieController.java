package com.mountain_vd.viewFX.handlers.Serie;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.serie.CreateSerieAction;
import com.mountain_vd.controller.action.serie.CreateSerieValidation;
import com.mountain_vd.model.Serie;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.SearchPane;
import com.mountain_vd.viewFX.forms.SerieForm;
import com.mountain_vd.viewFX.handlers.CreateMediaController;
import javafx.scene.Node;

import java.time.LocalDate;

/**
 * Controlador responsável pela criação de novas séries na aplicação.
 * Gera o formulário para entrada de dados da série e executa a ação de criação.
 */
public class CreateSerieController implements CreateMediaController {
    private final RootScene rootScene;
    private SerieForm serieForm;

    /**
     * Construtor que recebe a cena raiz para controle da interface.
     *
     * @param rootScene cena principal da aplicação
     */
    public CreateSerieController(RootScene rootScene) {
        this.rootScene = rootScene;
    }

    /**
     * Cria e retorna o formulário da série para preenchimento dos dados.
     *
     * @return nó JavaFX contendo o formulário de criação da série
     */
    @Override
    public Node getForm() {
        serieForm = new SerieForm(rootScene);
        return serieForm.getNode();
    }

    /**
     * Coleta os dados do formulário, cria um objeto {@link Serie} e executa a ação de criação.
     * Exibe mensagens de sucesso ou erro conforme o resultado da ação.
     */
    @Override
    public void save() {
        Serie serie = new Serie();

        serie.setTitulo(serieForm.getTitle());
        if (serieForm.getAnoLancamento() != null && !serieForm.getAnoLancamento().isEmpty()) {
            serie.setDataLancamento(LocalDate.of(Integer.parseInt(serieForm.getAnoLancamento()), 1, 1));
        }
        serie.setGeneros(serieForm.getGeneros());
        serie.setConsumido(serieForm.getConsumer());

        serie.setTituloOriginal(serieForm.getTituloOriginal());
        serie.setElenco(serieForm.getElenco());
        serie.setLocalDisponivel(serieForm.getLocalDisponivel());

        serie.setDataFim(serieForm.getDataFim());

        CreateSerieAction action = new CreateSerieAction(new CreateSerieValidation());
        ActionResult result = action.execute(serie);

        if (result.isSuccess()) {
            returnPage();
            rootScene.showSuccess("Série cadastrada com sucesso!");
        } else {
            rootScene.showError(result.getMessage());
        }
    }

    /**
     * Navega para a página de busca de séries, atualizando o conteúdo principal da cena raiz.
     */
    @Override
    public void returnPage() {
        SearchPane pane = new SearchPane(rootScene, new SerieSearchPaneController(rootScene));
        rootScene.setMainContent(pane.getNode());
    }
}
