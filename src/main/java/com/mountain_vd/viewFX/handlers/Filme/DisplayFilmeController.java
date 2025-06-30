package com.mountain_vd.viewFX.handlers.Filme;

import com.mountain_vd.model.Filme;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.forms.FilmeForm;
import com.mountain_vd.viewFX.handlers.DisplayMediaController;
import javafx.scene.Node;

public class DisplayFilmeController extends DisplayMediaController{
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

        return filmeForm.getNode();
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void returnPage() {

    }
}
