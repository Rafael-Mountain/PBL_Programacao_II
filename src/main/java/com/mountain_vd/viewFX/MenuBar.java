package com.mountain_vd.viewFX;

import com.mountain_vd.viewFX.commons.Component;
import com.mountain_vd.viewFX.handlers.MenuBarController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class MenuBar implements Component {
    private VBox vbox;
    private final RootScene rootScene;
    private final MenuBarController controller;
    private double width;
    private double proportion = 0.2;

    public MenuBar(RootScene rootScene) {
        this.rootScene = rootScene;
        this.controller = new MenuBarController(rootScene);
        render();
    }

    @Override
    public Node getNode() {
        return vbox;
    }

    @Override
    public void render() {
        vbox = new VBox();
        width = rootScene.getScene().getWidth() * proportion;
        vbox.setMinWidth(width);

        ToggleGroup mediaToggleGroup = new ToggleGroup();

        // Criação dos botões com ações delegadas
        ToggleButton filmeButton = renderMediaButton("Filme", mediaToggleGroup, controller::onFilmeSelected, "filme-icon");
        ToggleButton serieButton = renderMediaButton("Série", mediaToggleGroup, controller::onSerieSelected, "serie-icon");
        ToggleButton livroButton = renderMediaButton("Livro", mediaToggleGroup, controller::onLivroSelected, "livro-icon");

        VBox buttonVbox = new VBox(filmeButton, serieButton, livroButton);
        buttonVbox.getStyleClass().add("div_botoes_menu_bar");

        HBox divThemeButton = new HBox(renderThemeButton());
        divThemeButton.setPadding(new Insets(10, 10, 10, 10));

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        vbox.getChildren().addAll(
                buttonVbox,
                spacer,
                divThemeButton
        );

        vbox.setPadding(new Insets(10, 0, 10, 0));
        vbox.getStyleClass().add("menu-bar");
    }

    private ToggleButton renderMediaButton(String label, ToggleGroup group, Runnable action, String iconStyle) {
        Label labelNode = new Label(label);

        Region icon = new Region();
        icon.getStyleClass().addAll(iconStyle,"menu-button-icon");
        icon.setPrefSize(24, 24);

        Region line = new Region();
        line.getStyleClass().add("menu-button-line");

        HBox hbox = new HBox(line,icon, labelNode);
        hbox.getStyleClass().add("menu-bar-button-div");

        ToggleButton button = new ToggleButton();
        button.setGraphic(hbox);

        button.getStyleClass().add("menu-bar-button");
        button.setMinWidth(width);
        button.setToggleGroup(group);

        button.setOnAction(e -> {
            if (button.isSelected()) {
                action.run();
            }
        });

        return button;
    }

    private ToggleButton renderThemeButton() {
        ToggleButton toggleButton = new ToggleButton("Modo Claro");
        toggleButton.setMinWidth(width / 3);

        toggleButton.setOnAction(e -> controller.onThemeToggle(toggleButton));

        return toggleButton;
    }
}
