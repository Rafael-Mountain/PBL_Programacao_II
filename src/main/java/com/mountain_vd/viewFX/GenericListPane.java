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

public class GenericListPane<T> implements Component {
    private VBox vbox;
    private final ObservableList<T> items;
    private TextField inputField;
    private final BiConsumer<String, ObservableList<T>> controller;
    private final String label;

    public GenericListPane(ObservableList<T> items, BiConsumer<String, ObservableList<T>> controller, String label) {
        this.items = items;
        this.controller = controller;
        this.label = label;
        render();
    }

    @Override
    public Node getNode() {
        return vbox;
    }

    @Override
    public void render() {
        vbox = new VBox();
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

    public ListView<T> renderListView() {
        ListView<T> lv = new ListView<>(items);

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
}
