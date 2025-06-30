package com.mountain_vd.viewFX.handlers.Livro;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.livro.CreateLivroAction;
import com.mountain_vd.controller.action.livro.CreateLivroValidation;
import com.mountain_vd.model.Livro;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.SearchPane;
import com.mountain_vd.viewFX.forms.LivroForm;
import com.mountain_vd.viewFX.handlers.CreateMediaController;
import javafx.scene.Node;

import java.time.LocalDate;

public class CreateLivroController implements CreateMediaController {
    RootScene rootScene;
    LivroForm livroForm;

    public CreateLivroController(RootScene rootScene) {
        this.rootScene = rootScene;
    }

    @Override
    public Node getForm() {
        livroForm = new LivroForm(rootScene);
        return livroForm.getNode();
    }

    @Override
    public void save() {
        Livro livro = new Livro();

        livro.setTitulo(livroForm.getTitle());
        livro.setDataLancamento(LocalDate.of(Integer.parseInt(livroForm.getAnoLancamento()), 1, 1));
        livro.setGeneros(livroForm.getGeneros());
        livro.setConsumido(livroForm.getConsumer());

        livro.setAutor(livroForm.getAutor());
        livro.setEditora(livroForm.getEditora());
        livro.setIsbn(livroForm.getIsbn());
        livro.setPossui(livroForm.getPossuiCheckbox());

        CreateLivroAction action = new CreateLivroAction(new CreateLivroValidation());
        ActionResult result = action.execute(livro);

        if (result.isSuccess()) {
            returnPage();
            rootScene.showSuccess("Livro cadastrado com sucesso!");
        } else {
            rootScene.showError(result.getMessage());
        }
    }

    @Override
    public void returnPage() {
        SearchPane pane = new SearchPane(rootScene, new LivroSearchPaneController(rootScene));
        rootScene.setMainContent(pane.getNode());
    }
}
