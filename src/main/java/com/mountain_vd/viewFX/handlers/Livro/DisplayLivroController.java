package com.mountain_vd.viewFX.handlers.Livro;

import com.mountain_vd.model.Filme;
import com.mountain_vd.model.Livro;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.forms.FilmeForm;
import com.mountain_vd.viewFX.forms.LivroForm;
import com.mountain_vd.viewFX.handlers.DisplayMediaController;
import javafx.scene.Node;

public class DisplayLivroController extends DisplayMediaController {
    RootScene rootScene;
    Livro livro;
    LivroForm livroForm;

    public DisplayLivroController(RootScene rootScene, Livro livro) {
        this.rootScene = rootScene;
        this.livro = livro;
    }

    @Override
    public Node getForm() {
        livroForm = new LivroForm(rootScene);

        return livroForm.getNode();
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
