package com.mountain_vd.viewFX.handlers.Serie;

import com.mountain_vd.model.Filme;
import com.mountain_vd.model.Serie;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.forms.SerieForm;
import com.mountain_vd.viewFX.handlers.DisplayMediaController;
import javafx.scene.Node;

public class DisplaySerieController extends DisplayMediaController {
    RootScene rootScene;
    Serie serie;
    SerieForm SerieForm;


    public DisplaySerieController(RootScene rootScene, Serie media) {
        this.rootScene = rootScene;
        this.serie = media;
    }

    @Override
    public Node getForm() {
        SerieForm = new SerieForm(rootScene);

        return SerieForm.getNode();
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
