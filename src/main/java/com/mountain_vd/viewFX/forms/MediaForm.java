package com.mountain_vd.viewFX.forms;

import com.mountain_vd.controller.util.TextFieldUtil;
import com.mountain_vd.model.Avaliacao;
import com.mountain_vd.model.Genero;
import com.mountain_vd.viewFX.GenericListPane;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.commons.Component;
import com.mountain_vd.viewFX.handlers.GenreListController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.List;

public class MediaForm implements Component {
    RootScene rootScene;
    protected TabPane tabPane;
    private TextField titleField;
    private TextField anoField;
    private CheckBox consumerCheckbox;
    private ObservableList<Genero> generos;
    private ObservableList<Avaliacao> avaliacaos;

    public MediaForm(RootScene rootScene) {
        this.rootScene = rootScene;
        this.avaliacaos = FXCollections.observableArrayList();
        this.generos = FXCollections.observableArrayList();
        render();
    }

    @Override
    public Node getNode() {
        return tabPane;
    }

    @Override
    public void render() {
        tabPane = new TabPane();
        Tab tab = new Tab("Mídia");
        tab.setContent(renderMidia());
        tab.setClosable(false);
        tabPane.getTabs().add(tab);
    }

    public void addTabAvailable() {
        Tab tab = new Tab("Avaliações");
        tab.setClosable(false);

        AvaliacaoForm avaliacaoForm = new AvaliacaoForm(rootScene,avaliacaos);

        tab.setContent(avaliacaoForm.getNode());
        tabPane.getTabs().add(tab);
    }

    protected HBox renderMidia() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10));
        hbox.setMaxHeight(290);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        // Definindo proporções das colunas (5 colunas no total)
        ColumnConstraints col0 = new ColumnConstraints();
        col0.setPercentWidth(10);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(20);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(20);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(20);
        gridPane.getColumnConstraints().addAll(col0, col1, col2, col3, col4);
        HBox.setHgrow(gridPane, Priority.ALWAYS);

        // Linha 0: Título
        Label tituloLabel = new Label("Título");
        Tooltip tituloTooltip = new Tooltip("Digite o nome oficial da obra (ex: 'Clube da Luta', 'Matrix')");
        Tooltip.install(tituloLabel, tituloTooltip);

        titleField = new TextField();
        titleField.setPromptText("Digite o título da mídia");
        Tooltip.install(titleField, tituloTooltip);

        gridPane.add(tituloLabel, 0, 0);
        GridPane.setColumnSpan(titleField, 4);
        GridPane.setHgrow(titleField, Priority.ALWAYS);
        gridPane.add(titleField, 1, 0);

        // Linha 1: Ano de lançamento
        Label anoLabel = new Label("Ano de lançamento");
        Tooltip anoTooltip = new Tooltip("Digite o ano de lançamento com 4 dígitos (ex: 1999)");
        Tooltip.install(anoLabel, anoTooltip);

        anoField = new TextField();
        anoField.setPromptText("AAAA");
        anoField.setTextFormatter(TextFieldUtil.numericFormatter(4));
        Tooltip.install(anoField, anoTooltip);

        HBox anoDiv = new HBox(5, anoLabel, anoField);

        // Linha 2: CheckBox "já consumida"
        consumerCheckbox = new CheckBox("mídia já consumida");
        Tooltip.install(consumerCheckbox, new Tooltip("Marque se você já assistiu, ouviu ou leu esta mídia"));

        VBox vBox = new VBox(10, anoDiv, consumerCheckbox);
        vBox.setPadding(new Insets(10, 0, 0, 0));
        GridPane.setColumnSpan(vBox, 2);
        gridPane.add(vBox, 0, 1);

        // Linha 1: Gêneros
        GenreListController controller = new GenreListController(rootScene);
        GenericListPane<Genero> genrePickerPane = new GenericListPane<>(generos, controller::addGenre, "Gêneros");
        Node genreNode = genrePickerPane.getNode();
        GridPane.setColumnSpan(genreNode, 3);
        GridPane.setRowSpan(genreNode, 2);
        GridPane.setHgrow(genreNode, Priority.ALWAYS);
        GridPane.setVgrow(genreNode, Priority.ALWAYS);
        gridPane.add(genreNode, 2, 1);

        hbox.getChildren().add(gridPane);
        return hbox;
    }

    public List<Genero> getGeneros() {
        return generos;
    }
}
