package viewFX;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import viewFX.commons.Component;

public class MenuBar implements Component {
    VBox vbox;
    RootScene rootScene;
    double width;

    MenuBar(RootScene rootScene) {
        this.rootScene = rootScene;
        render();
    }

    @Override
    public Node getNode() {
        return vbox;
    }

    @Override
    public void render() {
        vbox = new VBox();
        width = rootScene.getScene().getWidth() * 0.3;
        vbox.setMinWidth(width);
        vbox.setSpacing(10);

        ToggleGroup mediaToggleGroup = new ToggleGroup();

        // Cada botão chama uma função separada
        ToggleButton filmeButton = createMediaButton("Filme", mediaToggleGroup, this::onFilmeSelected);
        ToggleButton serieButton = createMediaButton("Série", mediaToggleGroup, this::onSerieSelected);
        ToggleButton livroButton = createMediaButton("Livro", mediaToggleGroup, this::onLivroSelected);

        VBox buttonVbox = new VBox(filmeButton, serieButton, livroButton);
        buttonVbox.setSpacing(10);

        HBox divThemeButton = new HBox(getThemeButton());
        divThemeButton.setPadding(new Insets(10, 10, 10, 10));

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        vbox.getChildren().addAll(
                buttonVbox,
                spacer,
                divThemeButton
        );

        vbox.setPadding(new Insets(10, 0, 10, 0));
        vbox.setId("MenuBar");
    }

    // Função genérica de criação de botões
    private ToggleButton createMediaButton(String label, ToggleGroup group, Runnable action) {
        ToggleButton button = new ToggleButton(label);
        button.setMinWidth(width);
        button.setToggleGroup(group);

        button.setOnAction(e -> {
            if (button.isSelected()) {
                action.run(); // chama a função específica
            }
        });

        return button;
    }

    // === Funções específicas para cada botão ===

    private void onFilmeSelected() {
        System.out.println("Ação do botão Filme");
        // lógica para filmes
    }

    private void onSerieSelected() {
        System.out.println("Ação do botão Série");
        // lógica para séries
    }

    private void onLivroSelected() {
        System.out.println("Ação do botão Livro");
        // lógica para livros
    }

    private ToggleButton getThemeButton() {
        ToggleButton toggleButton = new ToggleButton("Modo Escuro");
        toggleButton.setMinWidth(width / 3);

        toggleButton.setOnAction(e -> {
            if (toggleButton.isSelected()) {
                toggleButton.setText("Modo Escuro");
                rootScene.setDarkMode();
            } else {
                toggleButton.setText("Modo Claro");
                rootScene.setLitghMode();
            }
        });

        return toggleButton;
    }
}
