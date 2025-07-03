package com.mountain_vd.viewFX.forms;

import com.mountain_vd.controller.util.TextFieldUtil;
import com.mountain_vd.controller.util.TitledPaneCollapseListener;
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

/**
 * Formulário para gerenciamento das temporadas de uma série.
 * <p>
 * Permite adicionar novas temporadas, listar temporadas existentes
 * com seus respectivos anos, número de episódios e pontuação,
 * e exibir avaliações associadas a cada temporada em painéis expansíveis.
 * </p>
 */
public class TemporadaForm implements Component {

    /** Lista observável que mantém as temporadas da série */
    private final ObservableList<Temporada> temporadas;

    /** Referência à cena raiz da aplicação */
    private final RootScene rootScene;

    /** Série associada a este formulário */
    private Serie serie;

    /** Container principal do formulário */
    private VBox mainContent;

    /** Container que guarda a lista visual das temporadas */
    private final VBox listaTemporadasContainer = new VBox(5);

    /** Controle para garantir que somente um TitledPane fique expandido por vez */
    private TitledPane expandedPane = null;

    /**
     * Construtor que inicializa o formulário para uma dada série.
     *
     * @param rootScene referência à cena raiz da aplicação
     * @param serie série cujas temporadas serão gerenciadas
     */
    public TemporadaForm(RootScene rootScene, Serie serie) {
        this.temporadas = FXCollections.observableArrayList();
        this.temporadas.addAll(serie.getTemporadas());
        this.serie = serie;
        this.rootScene = rootScene;
        render();
    }

    /**
     * Retorna o nó raiz do formulário, que contém todos os componentes visuais.
     *
     * @return {@link Node} raiz do formulário
     */
    @Override
    public Node getNode() {
        return mainContent;
    }

    /**
     * Constrói e organiza visualmente os componentes do formulário,
     * incluindo campos para inserir nova temporada e a lista de temporadas.
     */
    @Override
    public void render() {
        mainContent = new VBox(10);
        mainContent.setPrefHeight(520);
        VBox.setVgrow(mainContent, Priority.ALWAYS);

        // Linha de entrada: Ano, nº de episódios e botão adicionar
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
        });

        hBox.getChildren().addAll(AnoBox, nEpisodesBox, addButton);

        // Lista scrollável de temporadas
        ScrollPane temporadaList = renderTemporadaList();
        VBox.setVgrow(temporadaList, Priority.ALWAYS);

        mainContent.getChildren().addAll(hBox, temporadaList);
    }

    /**
     * Cria o painel scrollável que contém a lista de temporadas,
     * atualizando automaticamente quando a lista de temporadas muda.
     *
     * @return {@link ScrollPane} contendo a lista de temporadas
     */
    public ScrollPane renderTemporadaList() {
        listaTemporadasContainer.getChildren().clear();
        listaTemporadasContainer.setFillWidth(true);
        VBox.setVgrow(listaTemporadasContainer, Priority.ALWAYS);

        // Escuta alterações na lista de temporadas para atualizar a exibição
        temporadas.addListener((javafx.collections.ListChangeListener<Temporada>) change -> atualizarLista());
        atualizarLista();

        ScrollPane scrollPane = new ScrollPane(listaTemporadasContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true); // força ocupar o espaço vertical disponível
        scrollPane.setPadding(new Insets(5));
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        return scrollPane;
    }

    /**
     * Atualiza a lista visual de temporadas exibindo um painel expansível
     * para cada temporada com informações e avaliações.
     */
    private void atualizarLista() {
        listaTemporadasContainer.getChildren().clear();

        for (Temporada temporada : temporadas) {
            // Labels com informações básicas da temporada
            Label anoLabel = new Label("Ano: " + temporada.getAno().getYear());
            Label episodiosLabel = new Label("Episódios: " + temporada.getqEpisodios());
            Label pontuacaoLabel = new Label("Pontuação: " + temporada.getPontuacao());

            // Div contendo as informações da temporada (cabeçalho do painel)
            HBox infoDiv = new HBox(20, anoLabel, episodiosLabel, pontuacaoLabel);
            infoDiv.setPadding(new Insets(5));

            // Painel expansível para avaliações da temporada
            TitledPane pane = new TitledPane();
            TitledPaneCollapseListener.attach(pane);
            pane.setExpanded(false);
            pane.setGraphic(infoDiv); // cabeçalho personalizado

            // Conteúdo do painel: formulário de avaliações da temporada
            AvaliacaoForm avaliacaoForm = new AvaliacaoForm(rootScene, temporada);
            VBox form = (VBox) avaliacaoForm.getNode();
            form.setPadding(new Insets(10));
            form.setPrefHeight(350);
            pane.setContent(form);

            // Garante que apenas um painel fique aberto por vez
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
