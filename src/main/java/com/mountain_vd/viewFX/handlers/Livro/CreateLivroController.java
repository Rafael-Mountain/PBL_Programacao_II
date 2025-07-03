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

/**
 * Controlador responsável pela criação de novos livros.
 * Gerencia o formulário de livro, coleta os dados informados pelo usuário,
 * executa as validações e ações de criação, e navega de volta para a tela de busca após salvar.
 */
public class CreateLivroController implements CreateMediaController {

    /** Cena raiz da aplicação para controle de navegação e exibição */
    RootScene rootScene;

    /** Formulário para entrada dos dados do livro */
    LivroForm livroForm;

    /**
     * Construtor que recebe a cena principal da aplicação.
     *
     * @param rootScene cena raiz da aplicação
     */
    public CreateLivroController(RootScene rootScene) {
        this.rootScene = rootScene;
    }

    /**
     * Retorna o nó da interface do formulário de criação de livro.
     * Inicializa o formulário se necessário.
     *
     * @return nó JavaFX contendo o formulário de livro
     */
    @Override
    public Node getForm() {
        livroForm = new LivroForm(rootScene);
        return livroForm.getNode();
    }

    /**
     * Coleta os dados do formulário, cria um objeto Livro,
     * realiza a ação de criação validando os dados, e trata o resultado.
     * Em caso de sucesso, exibe mensagem de sucesso e retorna à página de busca.
     * Em caso de erro, exibe mensagem de erro.
     */
    @Override
    public void save() {
        Livro livro = new Livro();

        livro.setTitulo(livroForm.getTitle());
        if (!(livroForm.getAnoLancamento() == null) && !livroForm.getAnoLancamento().isEmpty()) {
            livro.setDataLancamento(LocalDate.of(Integer.parseInt(livroForm.getAnoLancamento()), 1, 1));
        }
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

    /**
     * Navega de volta para a página de busca de livros.
     * Cria a tela de busca e seta no conteúdo principal da cena raiz.
     */
    @Override
    public void returnPage() {
        SearchPane pane = new SearchPane(rootScene, new LivroSearchPaneController(rootScene));
        rootScene.setMainContent(pane.getNode());
    }
}
