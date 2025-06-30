package com.mountain_vd.viewFX.handlers.Filme;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.filme.CreateFilmeAction;
import com.mountain_vd.controller.action.filme.CreateFilmeValidation;
import com.mountain_vd.model.Filme;
import com.mountain_vd.viewFX.handlers.CreateMediaController;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.SearchPane;
import com.mountain_vd.viewFX.forms.FilmeForm;
import javafx.scene.Node;

import java.time.LocalDate;

public class CreateFilmeController implements CreateMediaController {
    RootScene rootScene;
    FilmeForm filmeForm;

    public CreateFilmeController(RootScene rootScene) {
        this.rootScene = rootScene;
    }

    @Override
    public Node getForm() {
        filmeForm = new FilmeForm(rootScene);
        return filmeForm.getNode();
    }

    @Override
    public void save() {
        Filme filme = new Filme();

        filme.setTitulo(filmeForm.getTitle());
        filme.setDataLancamento(LocalDate.of(Integer.parseInt(filmeForm.getAnoLancamento()),1,1));
        filme.setGeneros(filmeForm.getGeneros());
        filme.setConsumido(filmeForm.getConsumer());

        filme.setTituloOriginal(filmeForm.getTituloOriginal());
        filme.setElenco(filmeForm.getElenco());
        filme.setLocalDisponivel(filmeForm.getLocalDisponivel());

        filme.setDirecao(filmeForm.getDiretor());
        filme.setDuracao(Integer.parseInt(filmeForm.getDuracao()));
        filme.setRoteiro(filmeForm.getRoteiro());

        CreateFilmeAction action = new CreateFilmeAction(new CreateFilmeValidation());
        ActionResult result =  action.execute(filme);

        if (result.isSuccess()){
            returnPage();
            rootScene.showSuccess("Filme cadastrado com sucesso!");
        } else  {
            rootScene.showError(result.getMessage());
        }
    }

    @Override
    public void returnPage() {
        SearchPane pane = new SearchPane(rootScene, new FilmeSearchPaneController(rootScene));
        rootScene.setMainContent(pane.getNode());
    }
}
