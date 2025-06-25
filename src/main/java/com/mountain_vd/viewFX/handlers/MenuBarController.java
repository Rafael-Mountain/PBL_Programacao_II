package com.mountain_vd.viewFX.handlers;

import com.mountain_vd.viewFX.handlers.Livro.LivroSearchPaneController;
import javafx.scene.control.ToggleButton;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.SearchPane;
import com.mountain_vd.viewFX.handlers.Filme.FilmeSearchPaneController;

public class MenuBarController {
    private final RootScene rootScene;

    public MenuBarController(RootScene rootScene) {
        this.rootScene = rootScene;
    }

    public void onFilmeSelected() {
        SearchPane pane = new SearchPane(rootScene, new FilmeSearchPaneController(rootScene));
        rootScene.setMainContent(pane.getNode());
    }

    public void onSerieSelected() {
        System.out.println("Ação do botão Série");
        // lógica para séries
    }

    public void onLivroSelected() {
        SearchPane pane = new SearchPane(rootScene, new LivroSearchPaneController(rootScene));
        rootScene.setMainContent(pane.getNode());
    }

    public void onThemeToggle(ToggleButton toggleButton) {
        if (toggleButton.isSelected()) {
            toggleButton.setText("Modo Escuro");
            rootScene.setDarkMode();
        } else {
            toggleButton.setText("Modo Claro");
            rootScene.setLightMode();
        }
    }
}
