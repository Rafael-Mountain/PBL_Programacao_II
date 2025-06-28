package com.mountain_vd.viewFX.forms;

import com.mountain_vd.viewFX.GenericListPane;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.handlers.ElencoListController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.List;

public class MediaAudioVisualForm extends MediaForm {
    private TextField tituloOriginalField;
    private TextField LocalDisponivelField;
    private ObservableList<String> elenco;

    public MediaAudioVisualForm(RootScene rootScene) {
        super(rootScene);
    }

    public String getTituloOriginal() {
        return tituloOriginalField.getText();
    }

    public String getLocalDisponivel() {
        return LocalDisponivelField.getText();
    }

    public List<String> getElenco() {
        return elenco;
    }

    @Override
    public void render() {
        tabPane = new TabPane();
        Tab tab = new Tab("Mídia");
        tab.setContent(renderMidiaAudioVisual());
        tab.setClosable(false);
        tabPane.getTabs().add(tab);
    }

    protected HBox renderMidiaAudioVisual() {
        this.elenco = FXCollections.observableArrayList();
        HBox content = renderMidia();
        content.setMaxHeight(400);

        GridPane gridPane = content.getChildren().stream()
                .filter(node -> node instanceof GridPane)
                .map(node -> (GridPane) node)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("GridPane não encontrado"));

        // --- Campo: Título Original ---
        Label tituloOriginalLabel = new Label("Título Original");
        Tooltip tituloOriginalTooltip = new Tooltip("Ex: Título no idioma original, como 'Fight Club' ou 'Le Fabuleux Destin d’Amélie Poulain'");
        Tooltip.install(tituloOriginalLabel, tituloOriginalTooltip);

        tituloOriginalField = new TextField();
        tituloOriginalField.setPromptText("Digite o título original da obra");
        Tooltip.install(tituloOriginalField, tituloOriginalTooltip);

        HBox.setHgrow(tituloOriginalField, Priority.ALWAYS);
        HBox divTituloOriginal = new HBox(10, tituloOriginalLabel, tituloOriginalField);
        gridPane.add(divTituloOriginal, 0, 3, 5, 1);

        // --- Campo: Local Disponível ---
        Label localDisponivelLabel = new Label("Local Disponível");
        Tooltip localTooltip = new Tooltip("Informe onde a mídia pode ser acessada atualmente");
        Tooltip.install(localDisponivelLabel, localTooltip);

        LocalDisponivelField = new TextField();
        LocalDisponivelField.setPromptText("Ex: Netflix, Amazon Prime...");
        Tooltip.install(LocalDisponivelField, localTooltip);

        HBox divLocalDisponivel = new HBox(10, localDisponivelLabel, LocalDisponivelField);
        gridPane.add(divLocalDisponivel, 0, 4, 2, 1);

        // --- Lista de Elenco ---
        ElencoListController elencoListController = new ElencoListController(rootScene);
        GenericListPane<String> ElencoList = new GenericListPane<>(
                elenco, elencoListController::addAtor, "Elenco");
        Node elencoNode = ElencoList.getNode();
        GridPane.setColumnSpan(elencoNode, 3);
        GridPane.setRowSpan(elencoNode, 2);
        gridPane.add(elencoNode, 2, 4);

        return content;
    }
}
