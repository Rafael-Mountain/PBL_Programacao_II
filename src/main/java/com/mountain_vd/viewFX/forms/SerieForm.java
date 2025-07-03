package com.mountain_vd.viewFX.forms;

import com.mountain_vd.model.Avaliacao;
import com.mountain_vd.model.Serie;
import com.mountain_vd.model.Temporada;
import com.mountain_vd.viewFX.RootScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.time.LocalDate;
import java.util.List;

public class SerieForm extends MediaAudioVisualForm {

    private DatePicker dataFimPicker;
    private ObservableList<Temporada> temporadas;

    public SerieForm(RootScene rootScene) {
        super(rootScene);
    }

    @Override
    public void render() {
        tabPane = new TabPane();

        Tab tab = new Tab("Série");
        tab.setContent(renderSerie());
        tab.setClosable(false);

        tabPane.getTabs().add(tab);
    }

    private HBox renderSerie() {
        HBox content = renderMidiaAudioVisual();
        content.setMaxHeight(520);

        GridPane gridPane = content.getChildren().stream()
                .filter(node -> node instanceof GridPane)
                .map(node -> (GridPane) node)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("GridPane não encontrado"));

        Label dataFimLabel = new Label("Data Fim");
        Tooltip dataFimTooltip = new Tooltip("Informe a data em que a série terminou ou foi finalizada.");
        Tooltip.install(dataFimLabel, dataFimTooltip);

        dataFimPicker = new DatePicker();
        dataFimPicker.setPromptText("Ex: 20/12/2023");
        Tooltip.install(dataFimPicker, dataFimTooltip);

        HBox dataFimBox = new HBox(10, dataFimLabel, dataFimPicker);
        dataFimBox.setPadding(new Insets(5, 0, 0, 0));

        GridPane.setColumnSpan(dataFimBox, 5);
        GridPane.setHgrow(dataFimBox, Priority.ALWAYS);
        HBox.setHgrow(dataFimPicker, Priority.ALWAYS);

        gridPane.add(dataFimBox, 0, 5);

        return content;
    }

    public void addTabTemporada(Serie serie) {
        Tab tab = new Tab("Temporadas");
        tab.setClosable(false);

        TemporadaForm temporadaForm = new TemporadaForm(rootScene, serie);
        tab.setContent(temporadaForm.getNode());

        tabPane.getTabs().add(tab);
    }

    // ======= GETTERS =======
    public LocalDate getDataFim() {
        return dataFimPicker.getValue();
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    // ======= SETTERS =======
    public void setDataFim(LocalDate data) {
        dataFimPicker.setValue(data);
    }

    // ======= DISABLE METHODS =======
    public void disableDataFimPicker() {
        if (dataFimPicker != null) {
            dataFimPicker.setDisable(true);
            dataFimPicker.setOpacity(1);
        }
    }

    @Override
    public void disableFields() {
        super.disableFields();
        disableDataFimPicker();
    }
}
