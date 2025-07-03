package com.mountain_vd.viewFX.handlers.Livro;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.livro.UpdateLivroAction;
import com.mountain_vd.controller.action.livro.UpdateLivroValidation;
import com.mountain_vd.model.Filme;
import com.mountain_vd.model.Livro;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.SearchPane;
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
        livroForm.addTabAvailable(livro);

        //Preenche o formulário com os dados do livro
        livroForm.setTitle(livro.getTitulo());
        if (livro.getDataLancamento() != null)
            livroForm.setAnoLancamento(String.valueOf(livro.getDataLancamento().getYear()));
        livroForm.setGeneros(livro.getGeneros());
        livroForm.setConsumer(livro.isConsumido());

        livroForm.setAutor(livro.getAutor());
        livroForm.setEditora(livro.getEditora());
        livroForm.setIsbn(livro.getIsbn());
        livroForm.setPossuiCheckbox(livro.isPossui());

        //Disabilita os campos do formulário
        livroForm.disableTitleField();
        livroForm.disableAnoField();
        livroForm.disableGeneroList();

        livroForm.disableAutorField();
        livroForm.disableEditoraField();
        livroForm.disableIsbnField();

        return livroForm.getNode();
    }

    @Override
    public void update() {
        livro.setPossui(livroForm.getPossuiCheckbox());
        livro.setConsumido(livroForm.getConsumer());

        UpdateLivroAction action = new UpdateLivroAction(new UpdateLivroValidation());
        ActionResult result = action.execute(livro);

        if (result.isSuccess()) {
            returnPage();
            rootScene.showSuccess("Livro atualizado com sucesso!");
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
