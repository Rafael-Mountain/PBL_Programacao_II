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

/**
 * Componente que representa a barra lateral de navegação do sistema.
 * Contém botões toggle para selecionar o tipo de mídia (Filme, Série, Livro)
 * e um botão toggle para alternar o tema da aplicação (modo claro/escuro).
 *
 * A barra é configurada para ocupar uma proporção fixa da largura da janela principal.
 */
public class MenuBar implements Component {
    /** Container vertical principal da barra de menu. */
    private VBox vbox;

    /** Cena principal onde o menu está inserido. */
    private final RootScene rootScene;

    /** Controlador para tratar as ações do menu. */
    private final MenuBarController controller;

    /** Largura da barra calculada proporcionalmente à largura da cena principal. */
    private double width;

    /** Proporção da largura da cena usada para definir a largura da barra. */
    private double proportion = 0.2;

    /** Botão toggle para selecionar a opção "Filme". */
    private ToggleButton filmeButton;

    /**
     * Cria a barra de menu, associando o controlador e inicializando o layout.
     *
     * @param rootScene Cena principal onde a barra será exibida.
     */
    public MenuBar(RootScene rootScene) {
        this.rootScene = rootScene;
        this.controller = new MenuBarController(rootScene);
        render();
        init();
    }

    /**
     * Inicializa o estado inicial do menu,
     * selecionando o botão "Filme" e disparando sua ação associada.
     */
    private void init() {
        filmeButton.setSelected(true);
        controller.onFilmeSelected();
    }

    /**
     * Retorna o nó JavaFX que representa a barra de menu.
     *
     * @return VBox com todos os elementos do menu.
     */
    @Override
    public Node getNode() {
        return vbox;
    }

    /**
     * Renderiza o layout da barra de menu, incluindo:
     * - Botões toggle para Filme, Série e Livro com ícones e linhas decorativas.
     * - Espaçador flexível para empurrar os itens para o topo.
     * - Botão toggle para alternar entre modo claro e escuro.
     */
    @Override
    public void render() {
        vbox = new VBox();
        width = rootScene.getScene().getWidth() * proportion;
        vbox.setMinWidth(width);

        ToggleGroup mediaToggleGroup = new ToggleGroup();

        mediaToggleGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null && oldVal != null) {
                // Restaura a seleção anterior se tentar desmarcar o botão atual
                oldVal.setSelected(true);
            }
        });

        // Criação dos botões com ações delegadas ao controlador
        filmeButton = renderMediaButton("Filme", mediaToggleGroup, controller::onFilmeSelected, "filme-icon");
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

    /**
     * Cria um botão toggle para seleção de tipo de mídia.
     * Cada botão possui um ícone, uma linha decorativa e um label.
     *
     * @param label Texto do botão.
     * @param group Grupo toggle ao qual o botão pertence.
     * @param action Ação a ser executada quando o botão for selecionado.
     * @param iconStyle Nome da classe CSS para o ícone do botão.
     * @return ToggleButton configurado para o menu.
     */
    private ToggleButton renderMediaButton(String label, ToggleGroup group, Runnable action, String iconStyle) {
        Label labelNode = new Label(label);

        Region icon = new Region();
        icon.getStyleClass().addAll(iconStyle,"menu-button-icon");
        icon.setPrefSize(24, 24);

        Region line = new Region();
        line.getStyleClass().add("menu-button-line");

        HBox hbox = new HBox(line, icon, labelNode);
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

    /**
     * Cria o botão toggle para alternar entre os modos claro e escuro.
     *
     * @return SwitchButton configurado para alternar o tema da aplicação.
     */
    private SwitchButton renderThemeButton() {
        SwitchButton switchButton = new SwitchButton();

        // Registra listener para saber quando o estado mudar
        switchButton.addStateChangeListener((obs, oldVal, newVal) -> {
            controller.onThemeToggle(newVal); // chama o método passando o novo valor (true/false)
        });

        return switchButton;
    }
}
