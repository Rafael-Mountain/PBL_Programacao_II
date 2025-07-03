package com.mountain_vd.viewFX.handlers.Filme;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.filme.UpdateFilmeAction;
import com.mountain_vd.controller.action.filme.UpdateFilmeValidation;
import com.mountain_vd.model.Filme;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.SearchPane;
import com.mountain_vd.viewFX.forms.FilmeForm;
import com.mountain_vd.viewFX.handlers.DisplayMediaController;
import javafx.scene.Node;

public class DisplayFilmeController extends DisplayMediaController {
    RootScene rootScene;
    Filme filme;
    FilmeForm filmeForm;

    public DisplayFilmeController(RootScene rootScene, Filme filme) {
        this.rootScene = rootScene;
        this.filme = filme;
    }

    @Override
    public Node getForm() {
        filmeForm = new FilmeForm(rootScene);
        filmeForm.addTabAvailable(filme);

        //Preenche o formulário com os dados do filme
        filmeForm.setTitle(filme.getTitulo());
        if (filme.getDataLancamento() != null)
            filmeForm.setAnoLancamento(String.valueOf(filme.getDataLancamento().getYear()));
        filmeForm.setGeneros(filme.getGeneros());
        filmeForm.setConsumer(filme.isConsumido());

        filmeForm.setTituloOriginal(filme.getTituloOriginal());
        filmeForm.setLocalDisponivel(filme.getLocalDisponivel());
        filmeForm.setElenco(filme.getElenco());

        if (filme.getDuracao() != null)
            filmeForm.setDuracao(String.valueOf(filme.getDuracao()));
        filmeForm.setDiretor(filme.getDirecao());
        filmeForm.setRoteiro(filme.getRoteiro());

        //Disabilita os campos do formulário
        filmeForm.disableTitleField();
        filmeForm.disableAnoField();
        filmeForm.disableGeneroList();

        filmeForm.disableTituloOriginalField();
        filmeForm.disableLocalDisponivelField();
        filmeForm.disableElencoList();

        filmeForm.disableDuracaoField();
        filmeForm.disableDiretorField();
        filmeForm.disableRoteiroTextArea();

        return filmeForm.getNode();
    }

    @Override
    public void update() {
        filme.setConsumido(filmeForm.getConsumer());

        UpdateFilmeAction action = new UpdateFilmeAction(new UpdateFilmeValidation());
        ActionResult result = action.execute(filme);
        if (result.isSuccess()) {
            returnPage();
            rootScene.showSuccess("Filme atualizado com sucesso!");
        } else {
            rootScene.showError(result.getMessage());
        }
    }

    @Override
    public void returnPage() {
        SearchPane pane = new SearchPane(rootScene, new FilmeSearchPaneController(rootScene));
        rootScene.setMainContent(pane.getNode());
    }
}
