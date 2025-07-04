package com.mountain_vd.viewFX;

import com.mountain_vd.viewFX.commons.Component;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Componente genérico para exibir e gerenciar uma lista editável de itens.
 * Permite adicionar novos itens via campo de texto e botão, além de remover itens individualmente.
 *
 * @param <T> Tipo dos itens da lista.
 */
public class GenericListPane<T> implements Component {
    /** Container principal vertical. */
    private VBox vbox;

    /** Lista observável de itens exibidos. */
    private final ObservableList<T> items;

    /** Campo de texto para entrada de novos itens. */
    private TextField inputField;

    /** Controlador que recebe o texto do input e a lista para adicionar novos itens. */
    private final BiConsumer<String, ObservableList<T>> controller;

    /** Texto do label descritivo do componente. */
    private final String label;

    /**
     * Construtor que inicializa o componente com a lista de itens, controlador de adição e label.
     *
     * @param items Lista observável que será exibida e modificada.
     * @param controller Função que recebe o texto do input e a lista para adicionar itens.
     * @param label Texto descritivo mostrado acima da lista.
     */
    public GenericListPane(ObservableList<T> items, BiConsumer<String, ObservableList<T>> controller, String label) {
        this.items = items;
        this.controller = controller;
        this.label = label;
        render();
    }

    /**
     * Retorna o nó JavaFX que representa este componente.
     *
     * @return VBox contendo todo o layout do componente.
     */
    @Override
    public Node getNode() {
        return vbox;
    }

    /**
     * Renderiza o layout completo do componente, incluindo:
     * - Label descritiva
     * - Campo de texto para entrada de novos itens
     * - Botão para adicionar itens
     * - Lista exibindo os itens atuais com botão para remoção de cada item.
     */
    @Override
    public void render() {
        vbox = new VBox();
        vbox.getStyleClass().add("generic-list");
        vbox.getChildren().add(new Label(label));
        vbox.setPrefHeight(140);
        vbox.setSpacing(5);

        inputField = new TextField();
        inputField.setPromptText("Digite aqui");
        HBox.setHgrow(inputField, Priority.ALWAYS);

        Button addButton = new Button("Adicionar");
        addButton.setOnAction(e -> {
            controller.accept(inputField.getText(), items);
            inputField.clear();
        });

        HBox inputBox = new HBox(inputField, addButton);
        inputBox.setSpacing(5);

        ListView<T> listView = renderListView();
        VBox.setVgrow(listView, Priority.ALWAYS);

        vbox.getChildren().addAll(inputBox, listView);
    }

    /**
     * Cria e configura o ListView que exibe os itens da lista.
     * Cada célula possui o texto do item e um botão para removê-lo da lista.
     * O botão muda sua imagem quando o mouse passa por cima.
     *
     * @return ListView configurado para exibir e manipular a lista de itens.
     */
    public ListView<T> renderListView() {
        ListView<T> lv = new ListView<>(items);
        lv.getStyleClass().add("generic-list-view");

        lv.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(T item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    HBox hbox = new HBox(10);
                    hbox.setAlignment(Pos.CENTER);
                    hbox.setFillHeight(true);

                    Label label = new Label(item.toString());
                    label.setMaxWidth(Double.MAX_VALUE);

                    StackPane labelWrapper = new StackPane(label);
                    labelWrapper.setAlignment(Pos.CENTER);
                    HBox.setHgrow(labelWrapper, Priority.ALWAYS);

                    Image normalImage = new Image(Objects.requireNonNull(getClass().getResource("/images/close.png")).toExternalForm());
                    Image hoverImage = new Image(Objects.requireNonNull(getClass().getResource("/images/closeBold.png")).toExternalForm());

                    Button removeButton = getButton(item, normalImage, hoverImage);

                    hbox.getChildren().addAll(labelWrapper, removeButton);
                    setGraphic(hbox);
                }
            }

            private Button getButton(T item, Image normalImage, Image hoverImage) {
                ImageView imageView = new ImageView(normalImage);
                imageView.setFitWidth(16);
                imageView.setFitHeight(16);

                Button removeButton = new Button();
                removeButton.setGraphic(imageView);
                removeButton.setStyle("-fx-background-color: transparent; -fx-padding: 4;");
                removeButton.setOnMouseEntered(e -> imageView.setImage(hoverImage));
                removeButton.setOnMouseExited(e -> imageView.setImage(normalImage));
                removeButton.setOnAction(event -> items.remove(item));
                return removeButton;
            }
        });

        return lv;
    }

    public void disableAllFields() {
        if (inputField != null) {
            inputField.setDisable(true);
            inputField.setOpacity(1.0);
        }

        for (Node node : vbox.getChildren()) {
            if (node instanceof HBox hbox) {
                for (Node child : hbox.getChildren()) {
                    if (child instanceof Button button) {
                        button.setDisable(true);
                        button.setOpacity(1.0);
                    }
                }
            } else if (node instanceof ListView<?> listView) {
                listView.setDisable(true);
                listView.setOpacity(1.0);
            }
        }
    }
}
