package com.mountain_vd.viewFX;

import com.mountain_vd.controller.search.SearchResults;
import com.mountain_vd.model.Genero;
import com.mountain_vd.model.Media;
import com.mountain_vd.viewFX.commons.Component;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.function.Consumer;

/**
 * Componente que exibe uma tabela com resultados de mídias provenientes de uma busca.
 *
 * Utiliza um {@link TableView} para apresentar propriedades do objeto {@link Media} como título, gêneros, ano e pontuação.
 * Também permite interação via clique duplo na linha para ações adicionais.
 */
public class SearchTableResult implements Component {
    private ObservableList<Media> medias;
    private SearchResults searchResults;
    private Consumer<Media> onMediaDoubleClick;
    private TableView<Media> tableView;

    /**
     * Cria uma nova instância do componente, inicializando a lista observável e renderizando a tabela.
     */
    public SearchTableResult() {
        medias = FXCollections.observableArrayList();
        render();
    }

    /**
     * Retorna o nó JavaFX raiz do componente (a tabela de resultados).
     *
     * @return A {@link TableView} contendo os resultados.
     */
    @Override
    public Node getNode() {
        return tableView;
    }

    /**
     * Atualiza os resultados exibidos com base em um objeto {@link SearchResults}.
     * Também define o consumidor que será chamado ao clicar duas vezes em uma linha.
     *
     * @param searchResults Objeto contendo os dados de mídia encontrados.
     * @param onMediaDoubleClick Função executada ao clicar duas vezes sobre uma mídia.
     */
    public void setSearchResults(SearchResults searchResults, Consumer<Media> onMediaDoubleClick) {
        this.searchResults = searchResults;
        this.onMediaDoubleClick = onMediaDoubleClick;
        this.medias.setAll(searchResults.getMediaList());
    }

    /**
     * Inicializa e configura o componente {@link TableView}, incluindo colunas,
     * formato das células e eventos de interação.
     */
    @Override
    public void render() {
        tableView = new TableView<>();
        tableView.getStyleClass().add("result-table");
        VBox.setVgrow(tableView, Priority.ALWAYS);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Media, String> titleCol = new TableColumn<>("Título");
        titleCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTitulo()));

        TableColumn<Media, List<Genero>> generoCol = getGeneroColumn();
        generoCol.setMaxWidth(Double.MAX_VALUE); // Permite expandir

        TableColumn<Media, Integer> yearCol = new TableColumn<>("Ano");
        yearCol.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getDataLancamento().getYear()).asObject());

        yearCol.setCellFactory(column -> new TableCell<Media, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.toString());
                    setAlignment(Pos.CENTER); // Alinha o texto ao centro da célula
                }
            }
        });

        TableColumn<Media, Double> scoreCol = getScoreColumn();

        TableColumn<Media, Void> visualizarCol = getVisualizarColumn();

        tableView.getColumns().addAll(titleCol, generoCol, yearCol, scoreCol, visualizarCol);
        tableView.setItems(medias);

        // Define o evento de clique duplo para acionar a ação informada
        tableView.setRowFactory(tv -> {
            TableRow<Media> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    Media clickedMedia = row.getItem();
                    onMediaDoubleClick.accept(clickedMedia);
                }
            });
            return row;
        });
    }

    /**
     * Cria a coluna de gêneros que exibe os gêneros da mídia em forma de labels estilizadas.
     *
     * @return Coluna de gêneros da tabela.
     */
    private TableColumn<Media, List<Genero>> getGeneroColumn() {
        TableColumn<Media, List<Genero>> generoCol = new TableColumn<>("Gêneros");
        generoCol.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getGeneros()));

        generoCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(List<Genero> item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox(5);
                    hbox.setAlignment(Pos.CENTER_LEFT);
                    for (Genero genero : item) {
                        Label generoLabel = new Label(genero.getNome());
                        generoLabel.getStyleClass().addAll("rounded-label", "genero-label");
                        hbox.getChildren().add(generoLabel);
                    }
                    setGraphic(hbox);
                }
            }
        });

        return generoCol;
    }

    /**
     * Cria a coluna de pontuação que exibe a nota da mídia com cores conforme valor.
     *
     * @return Coluna de pontuação da tabela.
     */
    private TableColumn<Media, Double> getScoreColumn() {
        TableColumn<Media, Double> scoreCol = new TableColumn<>("Pontuação");
        scoreCol.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getPontuacao()));

        scoreCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    String formattedScore = String.format("%.1f", item);
                    Label scoreLabel = new Label(formattedScore);
                    scoreLabel.getStyleClass().clear();
                    scoreLabel.getStyleClass().add("rounded-label");

                    if (item >= 0 && item <= 1) {
                        scoreLabel.getStyleClass().add("pontuacao-baixa");
                    } else if (item > 1 && item < 3) {
                        scoreLabel.getStyleClass().add("pontuacao-media");
                    } else if (item >= 3 && item <= 5) {
                        scoreLabel.getStyleClass().add("pontuacao-alta");
                    }

                    HBox hbox = new HBox(scoreLabel);
                    hbox.setAlignment(Pos.CENTER);
                    setGraphic(hbox);
                }
            }
        });

        return scoreCol;
    }

    /**
     * Cria a coluna que contém um botão para visualizar detalhes da mídia,
     * acionando a mesma ação do clique duplo.
     *
     * @return Coluna com botão de visualizar.
     */
    private TableColumn<Media, Void> getVisualizarColumn() {
        TableColumn<Media, Void> col = new TableColumn<>("");

        col.setCellFactory(param -> new TableCell<>() {
            private final VBox vbox = new VBox();
            private final Button eyeButton = new Button();

            {
                eyeButton.getStyleClass().add("eye-button");
                eyeButton.setPrefSize(24, 24);
                eyeButton.setOnAction(event -> {
                    Media media = getTableView().getItems().get(getIndex());
                    if (onMediaDoubleClick != null) {
                        onMediaDoubleClick.accept(media);
                    }
                });
                vbox.getChildren().add(eyeButton);
                vbox.setAlignment(Pos.CENTER);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(vbox);
                }
            }
        });

        col.setPrefWidth(40);
        col.setSortable(false);
        return col;
    }
}
