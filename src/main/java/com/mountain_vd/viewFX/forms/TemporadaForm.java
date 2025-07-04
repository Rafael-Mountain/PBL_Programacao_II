package com.mountain_vd.viewFX.forms;

import com.mountain_vd.controller.util.TextFieldUtil;
import com.mountain_vd.controller.util.TitledPaneCollapseListener;
import com.mountain_vd.model.Avaliacao;
import com.mountain_vd.model.Serie;
import com.mountain_vd.model.Temporada;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.commons.Component;
import com.mountain_vd.viewFX.handlers.TemporadaFormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class TemporadaForm implements Component {

    private final ObservableList<Temporada> temporadas;
    private final RootScene rootScene;
    private Serie serie;
    private VBox mainContent;

    private final VBox listaTemporadasContainer = new VBox(5);
    private TitledPane expandedPane = null;

    public TemporadaForm(RootScene rootScene, Serie serie) {
        this.temporadas = FXCollections.observableArrayList();
        this.temporadas.addAll(serie.getTemporadas());
        this.serie = serie;
        this.rootScene = rootScene;
        render();
    }

    @Override
    public Node getNode() {
        return mainContent;
    }

    @Override
    public void render() {
        mainContent = new VBox(10);
        mainContent.setPrefHeight(520);
        VBox.setVgrow(mainContent, Priority.ALWAYS);

        // ==== Linha de entrada com ano, episódios e botão ====
        HBox hBox = new HBox(20);
        hBox.setPadding(new Insets(5, 0, 10, 0));

        // Campo Ano da Temporada
        Label labelAno = new Label("Ano da Temporada");
        TextField anoField = new TextField();
        anoField.setPromptText("Ex: 2024");
        anoField.setTextFormatter(TextFieldUtil.numericFormatter(4));
        Tooltip tooltipAno = new Tooltip("Digite o ano da temporada (somente números)");
        Tooltip.install(labelAno, tooltipAno);
        Tooltip.install(anoField, tooltipAno);
        VBox AnoBox = new VBox(5, labelAno, anoField);
        AnoBox.setPrefWidth(180);

        // Campo Número de Episódios
        Label labelEpisodios = new Label("Número de Episódios");
        TextField nEpisodesField = new TextField();
        nEpisodesField.setPromptText("Ex: 12");
        nEpisodesField.setTextFormatter(TextFieldUtil.numericFormatter(2));
        Tooltip tooltipEp = new Tooltip("Informe o número de episódios da temporada");
        Tooltip.install(labelEpisodios, tooltipEp);
        Tooltip.install(nEpisodesField, tooltipEp);
        VBox nEpisodesBox = new VBox(5, labelEpisodios, nEpisodesField);
        nEpisodesBox.setPrefWidth(180);

        // Botão Adicionar
        Button addButton = new Button("Adicionar");
        addButton.setPrefHeight(38);
        addButton.setOnAction(e -> {
            TemporadaFormController.addTemporada(
                    rootScene,
                    temporadas,
                    anoField.getText(),
                    nEpisodesField.getText(),
                    serie
            );
            anoField.clear();
            nEpisodesField.clear();
            atualizarLista();
        });

        hBox.getChildren().addAll(AnoBox, nEpisodesBox, addButton);

        // ==== Lista Scrollável de Temporadas ====
        ScrollPane temporadaList = renderTemporadaList();
        VBox.setVgrow(temporadaList, Priority.ALWAYS);

        mainContent.getChildren().addAll(hBox, temporadaList);
    }

    public ScrollPane renderTemporadaList() {
        listaTemporadasContainer.getChildren().clear();
        listaTemporadasContainer.setFillWidth(true);
        VBox.setVgrow(listaTemporadasContainer, Priority.ALWAYS);

        // Atualiza a lista dinamicamente
        temporadas.addListener((javafx.collections.ListChangeListener<Temporada>) change -> atualizarLista());
        atualizarLista();

        ScrollPane scrollPane = new ScrollPane(listaTemporadasContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true); // força ocupar o espaço vertical
        scrollPane.setPadding(new Insets(5));
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        return scrollPane;
    }

    private void atualizarLista() {
        listaTemporadasContainer.getChildren().clear();

        for (Temporada temporada : temporadas) {
            // Labels de informação
            Label anoLabel = new Label("Ano: " + temporada.getAno().getYear());
            Label episodiosLabel = new Label("Episódios: " + temporada.getqEpisodios());
            Label pontuacaoLabel = new Label("Pontuação: " + temporada.getPontuacao());

            // Div de informações da temporada
            HBox infoDiv = new HBox(20, anoLabel, episodiosLabel, pontuacaoLabel);
            infoDiv.setPadding(new Insets(5));

            // Painel expansível
            TitledPane pane = new TitledPane();
            TitledPaneCollapseListener.attach(pane);
            pane.setExpanded(false);
            pane.setGraphic(infoDiv); // infoDiv como cabeçalho

            // Conteúdo: lista de avaliações
            AvaliacaoForm avaliacaoForm = new AvaliacaoForm(rootScene, temporada);
            VBox form = (VBox) avaliacaoForm.getNode();
            form.setPadding(new Insets(10));
            form.setPrefHeight(350);
            pane.setContent(form);

            // Comportamento: apenas um aberto por vez
            pane.expandedProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal && expandedPane != null && expandedPane != pane) {
                    expandedPane.setExpanded(false);
                }
                if (newVal) expandedPane = pane;
            });

            listaTemporadasContainer.getChildren().add(pane);
        }
    }
}
