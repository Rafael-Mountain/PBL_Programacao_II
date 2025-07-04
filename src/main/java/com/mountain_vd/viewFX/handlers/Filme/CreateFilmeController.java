package com.mountain_vd.viewFX.handlers.Filme;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.filme.CreateFilmeAction;
import com.mountain_vd.controller.action.filme.CreateFilmeValidation;
import com.mountain_vd.model.Filme;
import com.mountain_vd.viewFX.handlers.CreateMediaController;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.SearchPane;
import com.mountain_vd.viewFX.forms.FilmeForm;
import javafx.scene.Node;

import java.time.LocalDate;

/**
 * Controlador responsável por gerenciar a criação de um novo filme
 * na interface gráfica, integrando o formulário de filme com a lógica
 * de negócio e ações de validação e persistência.
 */
public class CreateFilmeController implements CreateMediaController {

    /** Referência para a cena principal da aplicação */
    RootScene rootScene;

    /** Formulário visual para cadastro de filmes */
    FilmeForm filmeForm;

    /**
     * Construtor que recebe a cena raiz para manipulação da UI.
     *
     * @param rootScene a cena principal da aplicação
     */
    public CreateFilmeController(RootScene rootScene) {
        this.rootScene = rootScene;
    }

    /**
     * Retorna o componente de formulário para cadastro do filme.
     *
     * @return {@link Node} contendo o formulário visual do filme
     */
    @Override
    public Node getForm() {
        filmeForm = new FilmeForm(rootScene);
        return filmeForm.getNode();
    }

    /**
     * Realiza o processo de salvar o filme, coletando dados do formulário,
     * realizando validações, executando a ação de criação e tratando o resultado.
     */
    @Override
    public void save() {
        Filme filme = new Filme();

        // Configura título e ano de lançamento
        filme.setTitulo(filmeForm.getTitle());
        if (!(filmeForm.getAnoLancamento() == null) && !filmeForm.getAnoLancamento().isEmpty()) {
            filme.setDataLancamento(LocalDate.of(Integer.parseInt(filmeForm.getAnoLancamento()), 1, 1));
        }
        // Configura outros dados do filme
        filme.setGeneros(filmeForm.getGeneros());
        filme.setConsumido(filmeForm.getConsumer());

        filme.setTituloOriginal(filmeForm.getTituloOriginal());
        filme.setElenco(filmeForm.getElenco());
        filme.setLocalDisponivel(filmeForm.getLocalDisponivel());

        filme.setDirecao(filmeForm.getDiretor());
        if (!(filmeForm.getDuracao() == null) && !filmeForm.getDuracao().isEmpty()) {
            filme.setDuracao(Integer.parseInt(filmeForm.getDuracao()));
        }
        filme.setRoteiro(filmeForm.getRoteiro());

        // Executa a ação de criação com validação
        CreateFilmeAction action = new CreateFilmeAction(new CreateFilmeValidation());
        ActionResult result = action.execute(filme);

        // Tratamento de sucesso ou falha
        if (result.isSuccess()) {
            returnPage();
            rootScene.showSuccess("Filme cadastrado com sucesso!");
        } else {
            rootScene.showError(result.getMessage());
        }
    }

    /**
     * Retorna para a página de busca/listagem de filmes após o cadastro.
     */
    @Override
    public void returnPage() {
        SearchPane pane = new SearchPane(rootScene, new FilmeSearchPaneController(rootScene));
        rootScene.setMainContent(pane.getNode());
    }
}
