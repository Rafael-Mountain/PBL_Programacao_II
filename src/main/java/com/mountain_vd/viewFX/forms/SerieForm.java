package com.mountain_vd.viewFX.forms;

import com.mountain_vd.model.Avaliacao;
import com.mountain_vd.model.Temporada;
import com.mountain_vd.viewFX.RootScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.time.LocalDateTime;
import  java.time.LocalDate;

public class SerieForm extends MediaAudioVisualForm {

    private DatePicker dataFimPicker;
    private ObservableList<Temporada> temporadas;

    public SerieForm(RootScene rootScene) {
        super(rootScene);
        this.temporadas = FXCollections.observableArrayList();
    }

    /**
     * Inicializa a Tab principal com os dados da série.
     */
    @Override
    public void render() {
        tabPane = new TabPane();

        // Aba principal da Série
        Tab tab = new Tab("Série");
        tab.setContent(renderSerie());
        tab.setClosable(false);

        tabPane.getTabs().add(tab);
    }

    /**
     * Renderiza o formulário principal da série, incluindo a Data Fim.
     *
     * @return HBox com os campos de entrada.
     */
    private HBox renderSerie() {
        // Conteúdo principal baseado na mídia audiovisual (herdado)
        HBox content = renderMidiaAudioVisual();
        content.setMaxHeight(520);

        // Recupera o GridPane existente da estrutura herdada
        GridPane gridPane = content.getChildren().stream()
                .filter(node -> node instanceof GridPane)
                .map(node -> (GridPane) node)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("GridPane não encontrado"));

        // === Campo Data Fim ===
        Label dataFimLabel = new Label("Data Fim");
        Tooltip dataFimTooltip = new Tooltip("Informe a data em que a série terminou ou foi finalizada.");
        Tooltip.install(dataFimLabel, dataFimTooltip);

        dataFimPicker = new DatePicker();
        dataFimPicker.setPromptText("Ex: 20/12/2023");
        dataFimPicker.getEditor().setDisable(true);
        dataFimPicker.getEditor().setOpacity(1);
        Tooltip.install(dataFimPicker, dataFimTooltip);

        // Caixa agrupando o label e o campo
        HBox dataFimBox = new HBox(10, dataFimLabel, dataFimPicker);
        dataFimBox.setPadding(new Insets(5, 0, 0, 0));

        GridPane.setColumnSpan(dataFimBox, 5);
        GridPane.setHgrow(dataFimBox, Priority.ALWAYS);
        HBox.setHgrow(dataFimPicker, Priority.ALWAYS);

        gridPane.add(dataFimBox, 0, 5);

        return content;
    }

    /**
     * Adiciona uma aba para gerenciar as temporadas da série.
     */
    public void addTabTemporada() {
        Tab tab = new Tab("Temporadas");
        tab.setClosable(false);

        Temporada temporada1 = new Temporada();
        temporada1.setAno(LocalDateTime.of(2020, 1, 1, 0, 0));
        temporada1.setqEpisodios(10);
        temporada1.avaliar(new Avaliacao(LocalDateTime.now().minusDays(10), 5, "Excelente!", LocalDate.now().minusDays(20)));
        temporada1.avaliar(new Avaliacao(LocalDateTime.now().minusDays(8), 4, "Muito boa!", LocalDate.now().minusDays(18)));

        Temporada temporada2 = new Temporada();
        temporada2.setAno(LocalDateTime.of(2021, 1, 1, 0, 0));
        temporada2.setqEpisodios(8);
        temporada2.avaliar(new Avaliacao(LocalDateTime.now().minusDays(5), 4, "Gostei!", LocalDate.now().minusDays(10)));

        Temporada temporada3 = new Temporada();
        temporada3.setAno(LocalDateTime.of(2022, 1, 1, 0, 0));
        temporada3.setqEpisodios(12);
        temporada3.avaliar(new Avaliacao(LocalDateTime.now().minusDays(2), 4, "Boa temporada.", LocalDate.now().minusDays(5)));
        temporada3.avaliar(new Avaliacao(LocalDateTime.now().minusDays(1), 5, "Melhor temporada!", LocalDate.now().minusDays(3)));

        temporadas.add(temporada1);
        temporadas.add(temporada2);
        temporadas.add(temporada3);


        TemporadaForm temporadaForm = new TemporadaForm(rootScene, temporadas);
        tab.setContent(temporadaForm.getNode());

        tabPane.getTabs().add(tab);
    }
}
