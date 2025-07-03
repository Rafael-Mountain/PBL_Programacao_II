package com.mountain_vd.viewFX.handlers.Serie;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.serie.UpdateSerieAction;
import com.mountain_vd.controller.action.serie.UpdateSerieValidation;
import com.mountain_vd.model.Serie;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.SearchPane;
import com.mountain_vd.viewFX.forms.SerieForm;
import com.mountain_vd.viewFX.handlers.DisplayMediaController;
import javafx.scene.Node;

/**
 * Controlador responsável por exibir e atualizar uma série existente.
 * Exibe os dados da série em um formulário e permite atualizar seu estado.
 */
public class DisplaySerieController extends DisplayMediaController {
    private final RootScene rootScene;
    private final Serie serie;
    private SerieForm serieForm;

    /**
     * Construtor que recebe a cena raiz e a série a ser exibida.
     *
     * @param rootScene cena principal da aplicação
     * @param media série a ser exibida e editada
     */
    public DisplaySerieController(RootScene rootScene, Serie media) {
        this.rootScene = rootScene;
        this.serie = media;
    }

    /**
     * Cria e retorna o formulário preenchido com os dados da série.
     * Adiciona a aba de temporadas e desabilita os campos para edição direta.
     *
     * @return nó JavaFX com o formulário da série
     */
    @Override
    public Node getForm() {
        serieForm = new SerieForm(rootScene);
        serieForm.addTabTemporada(serie);

        // Preenche os dados da série no formulário
        serieForm.setTitle(serie.getTitulo());
        if (serie.getDataLancamento() != null)
            serieForm.setAnoLancamento(String.valueOf(serie.getDataLancamento().getYear()));
        serieForm.setGeneros(serie.getGeneros());
        serieForm.setConsumer(serie.isConsumido());

        serieForm.setTituloOriginal(serie.getTituloOriginal());
        serieForm.setLocalDisponivel(serie.getLocalDisponivel());
        serieForm.setElenco(serie.getElenco());

        serieForm.setDataFim(serie.getDataFim());

        // Desabilita os campos para impedir edição direta
        serieForm.disableTitleField();
        serieForm.disableAnoField();
        serieForm.disableGeneroList();

        serieForm.disableTituloOriginalField();
        serieForm.disableLocalDisponivelField();
        serieForm.disableElencoList();

        serieForm.disableDataFimPicker();

        return serieForm.getNode();
    }

    /**
     * Atualiza a série com os dados do formulário (somente o campo consumido),
     * executa a ação de atualização e exibe mensagens conforme resultado.
     */
    @Override
    public void update() {
        serie.setConsumido(serieForm.getConsumer());

        UpdateSerieAction action = new UpdateSerieAction(new UpdateSerieValidation());
        ActionResult result  = action.execute(serie);

        if (result.isSuccess()) {
            returnPage();
            rootScene.showSuccess("Série atualizada com sucesso!");
        } else {
            rootScene.showError(result.getMessage());
        }
    }

    /**
     * Retorna para a tela de busca de séries, atualizando o conteúdo principal da cena raiz.
     */
    @Override
    public void returnPage() {
        SearchPane pane = new SearchPane(rootScene, new SerieSearchPaneController(rootScene));
        rootScene.setMainContent(pane.getNode());
    }
}
