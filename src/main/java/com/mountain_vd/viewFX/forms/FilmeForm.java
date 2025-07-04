package com.mountain_vd.viewFX.forms;

import com.mountain_vd.controller.util.TextFieldUtil;
import com.mountain_vd.viewFX.RootScene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class FilmeForm extends MediaAudioVisualForm {
    private TextField duracaoField;
    private TextField diretorField;
    private TextArea roteiroTextArea;

    public FilmeForm(RootScene rootScene) {
        super(rootScene);
        render();
    }

    @Override
    public void render() {
        tabPane = new TabPane();
        Tab tab = new Tab("Filme");
        tab.setContent(renderFilme());
        tab.setClosable(false);
        tabPane.getTabs().add(tab);
    }

    public HBox renderFilme() {
        HBox content = renderMidiaAudioVisual();
        content.setMaxHeight(520);

        GridPane gridPane = content.getChildren().stream()
                .filter(node -> node instanceof GridPane)
                .map(node -> (GridPane) node)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("GridPane não encontrado"));

        // ===== COLUNA ESQUERDA (linha 5, coluna 0, rowspan 2) =====
        Label duracaoLabel = new Label("Duração (min)");
        duracaoField = new TextField();
        duracaoField.setTextFormatter(TextFieldUtil.numericFormatter(3));
        duracaoField.setPromptText("Ex: 120");
        Tooltip duracaoTooltip = new Tooltip("Informe a duração total do filme em minutos");
        Tooltip.install(duracaoLabel, duracaoTooltip);
        Tooltip.install(duracaoField, duracaoTooltip);

        Label diretorLabel = new Label("Diretor");
        diretorField = new TextField();
        diretorField.setPromptText("Nome do diretor");
        Tooltip diretorTooltip = new Tooltip("Digite o nome completo do diretor responsável pela obra");
        Tooltip.install(diretorLabel, diretorTooltip);
        Tooltip.install(diretorField, diretorTooltip);

        VBox leftColumn = new VBox(10,
                new VBox(duracaoLabel, duracaoField),
                new VBox(diretorLabel, diretorField)
        );
        leftColumn.setPrefWidth(250);
        GridPane.setRowSpan(leftColumn, 2);
        GridPane.setColumnSpan(leftColumn, 2);
        gridPane.add(leftColumn, 0, 5);

        // ===== TEXTAREA DE ROTEIRO (linha 6, coluna 0) =====
        Label roteiroLabel = new Label("Roteiro");
        roteiroTextArea = new TextArea();
        roteiroTextArea.setWrapText(true);
        roteiroTextArea.getStyleClass().add("roteiro-text-area");
        roteiroTextArea.setPromptText("Digite detalhes do roteiro, créditos ou outras observações");
        Tooltip roteiroTooltip = new Tooltip("Inclua detalhes sobre o roteiro, observações ou autoria");
        Tooltip.install(roteiroLabel, roteiroTooltip);
        Tooltip.install(roteiroTextArea, roteiroTooltip);

        VBox rightField = new VBox(5, roteiroLabel, roteiroTextArea);
        GridPane.setRowSpan(rightField, 2);
        GridPane.setColumnSpan(rightField, 5);
        gridPane.add(rightField, 0, 6);

        return content;
    }

    // ========= GETTERS =========
    public String getDuracao() {
        return duracaoField.getText();
    }

    public String getDiretor() {
        return diretorField.getText();
    }

    public String getRoteiro() {
        return roteiroTextArea.getText();
    }

    // ========= SETTERS =========
    public void setDuracao(String duracao) {
        duracaoField.setText(duracao);
    }

    public void setDiretor(String diretor) {
        diretorField.setText(diretor);
    }

    public void setRoteiro(String roteiro) {
        roteiroTextArea.setText(roteiro);
    }

    // ========= DISABLE METHODS =========
    public void disableDuracaoField() {
        if (duracaoField != null) {
            duracaoField.setDisable(true);
            duracaoField.setOpacity(1);
        }
    }

    public void disableDiretorField() {
        if (diretorField != null) {
            diretorField.setDisable(true);
            diretorField.setOpacity(1);
        }
    }

    public void disableRoteiroTextArea() {
        if (roteiroTextArea != null) {
            roteiroTextArea.setDisable(true);
            roteiroTextArea.setOpacity(1);
        }
    }

    @Override
    public void disableFields() {
        super.disableFields();
        disableDuracaoField();
        disableDiretorField();
        disableRoteiroTextArea();
    }
}
