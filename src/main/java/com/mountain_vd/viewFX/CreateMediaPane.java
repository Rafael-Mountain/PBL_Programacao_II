package com.mountain_vd.viewFX;

import com.mountain_vd.viewFX.commons.Component;
import com.mountain_vd.viewFX.forms.*;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class CreateMediaPane implements Component {
    RootScene rootScene;
    VBox vbox;


    public CreateMediaPane(RootScene rootScene) {
        this.rootScene = rootScene;
        render();
    }

    @Override
    public Node getNode() {
        return vbox;
    }

    @Override
    public void render() {
        vbox = new VBox(10);

        SerieForm mediaForm = new SerieForm(rootScene);
        mediaForm.addTabAvailable();
        mediaForm.addTabTemporada();

        vbox.getChildren().add(mediaForm.getNode());
    }
}
