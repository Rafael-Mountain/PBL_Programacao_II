package com.mountain_vd.viewFX.forms;

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

/**
 * Formulário específico para cadastro e edição de séries.
 * <p>
 * Herda campos comuns de {@link MediaAudioVisualForm} (como título original, local disponível e elenco)
 * e adiciona campos próprios, como:
 * <ul>
 *   <li>Data de finalização da série</li>
 *   <li>Aba extra para gerenciamento de temporadas</li>
 * </ul>
 * </p>
 */
public class SerieForm extends MediaAudioVisualForm {

    /** Campo de seleção de data para informar quando a série terminou */
    private DatePicker dataFimPicker;

    /** Lista observável para armazenar as temporadas da série */
    private ObservableList<Temporada> temporadas;

    /**
     * Construtor que inicializa o formulário da série.
     *
     * @param rootScene referência à cena principal da aplicação
     */
    public SerieForm(RootScene rootScene) {
        super(rootScene);
    }

    /**
     * Renderiza o {@link TabPane} com a aba principal "Série".
     */
    @Override
    public void render() {
        tabPane = new TabPane();

        Tab tab = new Tab("Série");
        tab.setContent(renderSerie());
        tab.setClosable(false);

        tabPane.getTabs().add(tab);
    }

    /**
     * Constrói e organiza visualmente os campos adicionais específicos da série,
     * além dos campos herdados de {@link MediaAudioVisualForm}.
     *
     * @return {@link HBox} contendo todo o layout da aba principal da série
     */
    private HBox renderSerie() {
        HBox content = renderMidiaAudioVisual();
        content.setMaxHeight(520);

        GridPane gridPane = content.getChildren().stream()
                .filter(node -> node instanceof GridPane)
                .map(node -> (GridPane) node)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("GridPane não encontrado"));

        // Campo: Data Fim
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

    /**
     * Adiciona dinamicamente uma aba "Temporadas" para gerenciar as temporadas de uma série.
     *
     * @param serie objeto da série que possui as temporadas
     */
    public void addTabTemporada(Serie serie) {
        Tab tab = new Tab("Temporadas");
        tab.setClosable(false);

        TemporadaForm temporadaForm = new TemporadaForm(rootScene, serie);
        tab.setContent(temporadaForm.getNode());

        tabPane.getTabs().add(tab);
    }

    // ======= GETTERS =======

    /**
     * @return data de finalização da série selecionada no DatePicker
     */
    public LocalDate getDataFim() {
        return dataFimPicker.getValue();
    }

    /**
     * @return lista observável de temporadas cadastradas
     */
    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    // ======= SETTERS =======

    /**
     * Define a data de finalização da série.
     *
     * @param data data de fim
     */
    public void setDataFim(LocalDate data) {
        dataFimPicker.setValue(data);
    }

    // ======= DISABLE METHODS =======

    /**
     * Desativa o campo DatePicker de data de finalização da série.
     */
    public void disableDataFimPicker() {
        if (dataFimPicker != null) {
            dataFimPicker.setDisable(true);
            dataFimPicker.setOpacity(1);
        }
    }

    /**
     * Desativa todos os campos editáveis, incluindo os herdados e o campo data de fim.
     */
    @Override
    public void disableFields() {
        super.disableFields();
        disableDataFimPicker();
    }
}
