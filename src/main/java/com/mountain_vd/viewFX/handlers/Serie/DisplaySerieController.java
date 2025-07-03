package com.mountain_vd.viewFX.handlers.Serie;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.serie.UpdateSerieAction;
import com.mountain_vd.controller.action.serie.UpdateSerieValidation;
import com.mountain_vd.model.Serie;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.SearchPane;
import com.mountain_vd.viewFX.forms.FilmeForm;
import com.mountain_vd.viewFX.forms.SerieForm;
import com.mountain_vd.viewFX.handlers.DisplayMediaController;
import javafx.scene.Node;

public class DisplaySerieController extends DisplayMediaController {
    RootScene rootScene;
    Serie serie;
    SerieForm serieForm;


    public DisplaySerieController(RootScene rootScene, Serie media) {
        this.rootScene = rootScene;
        this.serie = media;
    }

    @Override
    public Node getForm() {
        serieForm = new SerieForm(rootScene);
        serieForm.addTabTemporada(serie);

        //Preenche o formulário com os dados do serie
        serieForm.setTitle(serie.getTitulo());
        if (serie.getDataLancamento() != null)
            serieForm.setAnoLancamento(String.valueOf(serie.getDataLancamento().getYear()));
        serieForm.setGeneros(serie.getGeneros());
        serieForm.setConsumer(serie.isConsumido());

        serieForm.setTituloOriginal(serie.getTituloOriginal());
        serieForm.setLocalDisponivel(serie.getLocalDisponivel());
        serieForm.setElenco(serie.getElenco());

        serieForm.setDataFim(serie.getDataFim());

        //Disabilita os campos do formulário
        serieForm.disableTitleField();
        serieForm.disableAnoField();
        serieForm.disableGeneroList();

        serieForm.disableTituloOriginalField();
        serieForm.disableLocalDisponivelField();
        serieForm.disableElencoList();

        serieForm.disableDataFimPicker();

        return serieForm.getNode();
    }

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

    @Override
    public void returnPage() {
        SearchPane pane = new SearchPane(rootScene, new SerieSearchPaneController(rootScene));
        rootScene.setMainContent(pane.getNode());
    }
}
