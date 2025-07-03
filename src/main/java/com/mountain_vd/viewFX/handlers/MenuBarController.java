package com.mountain_vd.viewFX.handlers;

import com.mountain_vd.viewFX.handlers.Livro.LivroSearchPaneController;
import com.mountain_vd.viewFX.handlers.Serie.SerieSearchPaneController;
import javafx.scene.control.ToggleButton;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.SearchPane;
import com.mountain_vd.viewFX.handlers.Filme.FilmeSearchPaneController;

/**
 * Controlador responsável pela barra de menu da aplicação,
 * que gerencia as ações de seleção dos tipos de mídia e troca de tema.
 */
public class MenuBarController {
    private final RootScene rootScene;

    /**
     * Construtor que recebe a instância da cena principal para manipular o conteúdo.
     *
     * @param rootScene a cena raiz da aplicação
     */
    public MenuBarController(RootScene rootScene) {
        this.rootScene = rootScene;
    }

    /**
     * Método acionado quando o usuário seleciona o menu de Filmes.
     * Atualiza o conteúdo principal para a tela de busca de filmes.
     */
    public void onFilmeSelected() {
        SearchPane pane = new SearchPane(rootScene, new FilmeSearchPaneController(rootScene));
        rootScene.setMainContent(pane.getNode());
    }

    /**
     * Método acionado quando o usuário seleciona o menu de Séries.
     * Atualiza o conteúdo principal para a tela de busca de séries.
     */
    public void onSerieSelected() {
        SearchPane pane = new SearchPane(rootScene, new SerieSearchPaneController(rootScene));
        rootScene.setMainContent(pane.getNode());
    }

    /**
     * Método acionado quando o usuário seleciona o menu de Livros.
     * Atualiza o conteúdo principal para a tela de busca de livros.
     */
    public void onLivroSelected() {
        SearchPane pane = new SearchPane(rootScene, new LivroSearchPaneController(rootScene));
        rootScene.setMainContent(pane.getNode());
    }

    /**
     * Método para alternar o tema da aplicação entre modo claro e modo escuro.
     * Altera o texto do botão de toggle conforme o estado atual.
     *
     */
    public void onThemeToggle(boolean state) {
        if (state) {
            rootScene.setDarkMode();
        } else {;
            rootScene.setLightMode();
        }
    }
}
