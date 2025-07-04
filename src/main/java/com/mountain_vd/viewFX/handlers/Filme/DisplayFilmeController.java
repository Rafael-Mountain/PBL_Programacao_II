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

/**
 * Controlador responsável pela exibição e atualização dos dados de um filme específico.
 * Gera o formulário preenchido com os dados do filme e controla a atualização da informação,
 * incluindo desabilitação dos campos editáveis que não devem ser alterados.
 */
public class DisplayFilmeController extends DisplayMediaController {

    /** Referência para a cena principal da aplicação */
    RootScene rootScene;

    /** Instância do filme que será exibido e eventualmente atualizado */
    Filme filme;

    /** Formulário que representa a interface gráfica para exibir os dados do filme */
    FilmeForm filmeForm;

    /**
     * Construtor que recebe a cena raiz e o filme a ser exibido.
     *
     * @param rootScene a cena principal da aplicação
     * @param filme     o objeto filme a ser exibido e atualizado
     */
    public DisplayFilmeController(RootScene rootScene, Filme filme) {
        this.rootScene = rootScene;
        this.filme = filme;
    }

    /**
     * Retorna o formulário preenchido com os dados do filme e com os campos não editáveis desabilitados.
     *
     * @return {@link Node} contendo o formulário para exibição dos dados do filme
     */
    @Override
    public Node getForm() {
        filmeForm = new FilmeForm(rootScene);
        // Adiciona aba de avaliações relacionada ao filme
        filmeForm.addTabAvailable(filme);

        // Preenche o formulário com os dados do filme
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

        // Desabilita os campos para evitar edição direta na exibição
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

    /**
     * Atualiza o estado do filme com os dados que podem ser alterados pelo usuário,
     * executa a ação de atualização e trata o resultado exibindo mensagem de sucesso ou erro.
     */
    @Override
    public void update() {
        // Atualiza apenas o campo consumido, que está habilitado para edição
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

    /**
     * Retorna para a tela de busca/listagem de filmes após atualização ou cancelamento.
     */
    @Override
    public void returnPage() {
        SearchPane pane = new SearchPane(rootScene, new FilmeSearchPaneController(rootScene));
        rootScene.setMainContent(pane.getNode());
    }
}
