package com.mountain_vd.viewFX.handlers.Livro;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.livro.UpdateLivroAction;
import com.mountain_vd.controller.action.livro.UpdateLivroValidation;
import com.mountain_vd.model.Livro;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.SearchPane;
import com.mountain_vd.viewFX.forms.LivroForm;
import com.mountain_vd.viewFX.handlers.DisplayMediaController;
import javafx.scene.Node;

/**
 * Controlador responsável pela exibição e atualização dos detalhes de um livro.
 * Preenche o formulário com os dados do livro selecionado, permite edição de alguns campos,
 * executa validações e atualizações, além de gerenciar a navegação para a tela de busca.
 */
public class DisplayLivroController extends DisplayMediaController {

    /** Cena raiz da aplicação para controle de navegação e exibição */
    RootScene rootScene;

    /** Objeto livro que está sendo exibido e eventualmente atualizado */
    Livro livro;

    /** Formulário de livro para visualização e edição */
    LivroForm livroForm;

    /**
     * Construtor que recebe a cena raiz e o livro a ser exibido.
     *
     * @param rootScene cena principal da aplicação
     * @param livro objeto Livro a ser exibido
     */
    public DisplayLivroController(RootScene rootScene, Livro livro) {
        this.rootScene = rootScene;
        this.livro = livro;
    }

    /**
     * Inicializa o formulário, preenche com os dados do livro,
     * adiciona a aba de avaliações e desabilita campos que não podem ser editados diretamente.
     *
     * @return nó JavaFX contendo o formulário com os dados do livro
     */
    @Override
    public Node getForm() {
        livroForm = new LivroForm(rootScene);
        livroForm.addTabAvailable(livro);

        // Preenche os campos do formulário com os dados do livro
        livroForm.setTitle(livro.getTitulo());
        if (livro.getDataLancamento() != null)
            livroForm.setAnoLancamento(String.valueOf(livro.getDataLancamento().getYear()));
        livroForm.setGeneros(livro.getGeneros());
        livroForm.setConsumer(livro.isConsumido());

        livroForm.setAutor(livro.getAutor());
        livroForm.setEditora(livro.getEditora());
        livroForm.setIsbn(livro.getIsbn());
        livroForm.setPossuiCheckbox(livro.isPossui());

        // Desabilita campos que não devem ser editados diretamente neste modo de exibição
        livroForm.disableTitleField();
        livroForm.disableAnoField();
        livroForm.disableGeneroList();

        livroForm.disableAutorField();
        livroForm.disableEditoraField();
        livroForm.disableIsbnField();

        return livroForm.getNode();
    }

    /**
     * Atualiza os campos editáveis do livro com os valores do formulário,
     * executa a ação de atualização e trata o resultado exibindo mensagens apropriadas.
     * Em caso de sucesso, retorna para a tela de busca.
     */
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

    /**
     * Navega para a tela de busca de livros, configurando o conteúdo principal da cena raiz.
     */
    @Override
    public void returnPage() {
        SearchPane pane = new SearchPane(rootScene, new LivroSearchPaneController(rootScene));
        rootScene.setMainContent(pane.getNode());
    }
}
