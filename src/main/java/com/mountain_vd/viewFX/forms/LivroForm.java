package com.mountain_vd.viewFX.forms;

import com.mountain_vd.viewFX.RootScene;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class LivroForm extends MediaForm {

    private TextField autorField;
    private TextField editoraField;
    private CheckBox possuiCheckbox;
    private TextField isbnField;

    public LivroForm(RootScene rootScene) {
        super(rootScene);
    }

    @Override
    public void render() {
        tabPane = new TabPane();
        Tab tab = new Tab("Livro");
        tab.setContent(renderLivro());
        tab.setClosable(false);
        tabPane.getTabs().add(tab);
    }

    public HBox renderLivro() {
        HBox content = renderMidia();
        content.setMaxHeight(520);

        GridPane gridPane = content.getChildren().stream()
                .filter(node -> node instanceof GridPane)
                .map(node -> (GridPane) node)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("GridPane não encontrado"));

        int startRow = 3;

        // ===== AUTOR =====
        Label autorLabel = new Label("Autor");
        autorField = new TextField();
        autorField.setPromptText("Nome do autor");
        Tooltip autorTooltip = new Tooltip("Digite o nome do autor do livro");
        Tooltip.install(autorLabel, autorTooltip);
        Tooltip.install(autorField, autorTooltip);
        HBox autorBox = new HBox(10, autorLabel, autorField);
        HBox.setHgrow(autorField, Priority.ALWAYS);
        GridPane.setColumnSpan(autorBox, 5);
        gridPane.add(autorBox, 0, startRow++);

        // ===== EDITORA =====
        Label editoraLabel = new Label("Editora");
        editoraField = new TextField();
        editoraField.setPromptText("Nome da editora");
        Tooltip editoraTooltip = new Tooltip("Digite o nome da editora do livro");
        Tooltip.install(editoraLabel, editoraTooltip);
        Tooltip.install(editoraField, editoraTooltip);
        HBox editoraBox = new HBox(10, editoraLabel, editoraField);
        HBox.setHgrow(editoraField, Priority.ALWAYS);
        GridPane.setColumnSpan(editoraBox, 5);
        gridPane.add(editoraBox, 0, startRow++);

        // ===== ISBN + CHECKBOX =====
        Label isbnLabel = new Label("ISBN");
        isbnField = new TextField();
        isbnField.setPromptText("Código ISBN");
        Tooltip isbnTooltip = new Tooltip("Digite o código ISBN do livro");
        Tooltip.install(isbnLabel, isbnTooltip);
        Tooltip.install(isbnField, isbnTooltip);
        HBox.setHgrow(isbnField, Priority.ALWAYS);

        possuiCheckbox = new CheckBox("Você possui esse livro?"); // sem texto como solicitado
        Tooltip possuiTooltip = new Tooltip("Marque se você possui o livro em formato físico ou digital");
        Tooltip.install(possuiCheckbox, possuiTooltip);

        HBox isbnAndCheckboxBox = new HBox(10,isbnLabel, isbnField, possuiCheckbox);
        isbnAndCheckboxBox.setPadding(new Insets(5, 0, 0, 0));
        GridPane.setColumnSpan(isbnAndCheckboxBox, 5);
        gridPane.add(isbnAndCheckboxBox, 0, startRow++);

        return content;
    }
}
