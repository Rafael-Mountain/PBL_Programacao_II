package com.mountain_vd.viewFX;


import com.mountain_vd.controller.search.SearchResults;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import com.mountain_vd.model.Genero;
import com.mountain_vd.model.Media;
import com.mountain_vd.viewFX.commons.Component;

import java.util.List;

public class SearchTableResult implements Component {
    private ObservableList<Media> medias;
    private SearchResults searchResults;
    private TableView<Media> tableView;

    public SearchTableResult() {
        medias = FXCollections.observableArrayList();
        render();
    }

    @Override
    public Node getNode() {
        return tableView;
    }

    public void setSearchResults(SearchResults searchResults) {
        this.searchResults = searchResults;
        this.medias.setAll(searchResults.getMediaList());
    }

    @Override
    public void render() {
        tableView = new TableView<Media>();
        TableColumn<Media, String> titleCol = new TableColumn<>("Título");
        titleCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTitulo()));

        TableColumn<Media, List<Genero>> generoCol = getGeneroColumn();

        TableColumn<Media, Integer> yearCol = new TableColumn<>("Ano");
        yearCol.setCellValueFactory(cellData ->
                new SimpleIntegerProperty(cellData.getValue().getDataLancamento().getYear()).asObject());

        TableColumn<Media, Double> scoreCol = getScoreColumn();

        tableView.getColumns().addAll(titleCol,generoCol,yearCol,scoreCol);
        tableView.setItems(medias);
    }

    private TableColumn<Media, List<Genero>> getGeneroColumn() {
        TableColumn<Media, List<Genero>> generoCol = new TableColumn<>("Gêneros");
        generoCol.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getGeneros()));

        // Altera a construção da celula da tabela
        generoCol.setCellFactory(column -> {
            // retorna nova celula
            return new TableCell<Media, List<Genero>>() {
                @Override
                protected void updateItem(List<Genero> item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setGraphic(null);
                    } else {
                        //constroi uma lista de generos label
                        HBox hbox = new HBox(5);
                        hbox.setAlignment(Pos.CENTER_LEFT);
                        for (Genero genero : item) {
                            Label generoLabel = new Label(genero.getNome());  // Supondo que Genero tenha getNome()
                            generoLabel.getStyleClass().add("rounded-label");
                            generoLabel.getStyleClass().add("genero-label");
                            hbox.getChildren().add(generoLabel);
                        }
                        setGraphic(hbox);
                    }
                }
            };
        });
        return generoCol;
    }

    private TableColumn<Media, Double> getScoreColumn() {
        TableColumn<Media, Double> scoreCol = new TableColumn<>("Pontuação");

        scoreCol.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getPontuacao()));

        scoreCol.setCellFactory(column -> {
            return new TableCell<Media, Double>() {
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
            };
        });
        return scoreCol;
    }
}
