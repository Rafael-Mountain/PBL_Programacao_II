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

public class CreateSerieController implements CreateMediaController {
    RootScene rootScene;
    SerieForm serieForm;

    public CreateSerieController(RootScene rootScene) {
        this.rootScene = rootScene;
    }


    @Override
    public Node getForm() {
        SerieForm form = new SerieForm(rootScene);
        return form.getNode();
    }

    @Override
    public void save() {
        Serie serie = new Serie();

        serie.setTitulo(serieForm.getTitle());
        serie.setDataLancamento(LocalDate.of(Integer.parseInt(serieForm.getAnoLancamento()),1,1));
        serie.setGeneros(serieForm.getGeneros());
        serie.setConsumido(serieForm.getConsumer());

        serie.setTituloOriginal(serieForm.getTituloOriginal());
        serie.setElenco(serieForm.getElenco());
        serie.setLocalDisponivel(serieForm.getLocalDisponivel());

        serie.setDataFim(serieForm.getDataFim());

        CreateSerieAction action = new CreateSerieAction(new CreateSerieValidation());
        ActionResult result = action.execute(serie);

        if(result.isSuccess()){
            returnPage();
            rootScene.showSuccess("Serie cadastrada com sucesso!");
        }
        else{
            rootScene.showError(result.getMessage());
        }

    }

    @Override
    public void returnPage() {
        SearchPane pane = new SearchPane(rootScene, new SerieSearchPaneController(rootScene));
        rootScene.setMainContent(pane.getNode());
    }
}
