package com.mountain_vd.viewFX;

import com.mountain_vd.controller.filter.GenreFilterType;
import com.mountain_vd.controller.filter.YearFilterType;
import com.mountain_vd.controller.util.EnumUtils;
import com.mountain_vd.controller.util.TextFieldUtil;
import com.mountain_vd.controller.util.TitledPaneCollapseListener;
import com.mountain_vd.viewFX.commons.Component;
import com.mountain_vd.viewFX.handlers.SearchPaneController;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.List;

public class SearchPane implements Component {
    SearchPaneController controller;
    RootScene rootScene;
    SearchTableResult tableResult;
    StackPane pane;
    double paddingFloatingButton = 40;

    ComboBox<String> searchBox; //contem o campo que vai ser buscado
    TextField searchField;
    FilterItem genreFilter;
    FilterItem yearFilter;
    ToggleGroup orderByGroup;


    public SearchPane(RootScene rootScene, SearchPaneController controller) {
        this.rootScene = rootScene;
        this.controller = controller;
        render();
        beginSearch();
    }


    @Override
    public Node getNode() {
        return pane;
    }


    @Override
    public void render() {
        pane = new StackPane();
        VBox mainContent = new VBox();
        mainContent.getStyleClass().add("Search_Container");

        tableResult = new SearchTableResult();

        mainContent.getChildren().addAll(
                renderSearchBar(),
                renderFilterPane(),
                tableResult.getNode()
        );

        Button floatingButton = getFloatingButton();
        AnchorPane floatingPane = new AnchorPane(floatingButton);
        floatingPane.setPickOnBounds(false);
        AnchorPane.setBottomAnchor(floatingButton, paddingFloatingButton);
        AnchorPane.setRightAnchor(floatingButton, paddingFloatingButton);

        pane.getChildren().addAll(mainContent, floatingPane);
    }

    private void beginSearch() {
        controller.setSearchTerm(searchField.getText());
        controller.setSearchField(searchBox.getValue());
        controller.setGenreFilterInfomation(
                genreFilter.comboBox.getValue(),
                genreFilter.textField.getText());
        controller.setYearFilterInfomation(
                yearFilter.comboBox.getValue(),
                yearFilter.textField.getText());
        controller.setOrderby(
                orderByGroup.getSelectedToggle().getUserData().toString().equals("Melhor Avaliado")
                        ? "desc"
                        : "asc");

        controller.search();
        tableResult.setSearchResults(controller.getSearchResults(), controller::goTotDisplayPane);
    }

    private Button getFloatingButton() {
        Button floatingButton = new Button("+");
        floatingButton.setId("floatingButton");
        floatingButton.setOnAction(e -> {
            rootScene.setMainContent(controller.getAddScreen());
        });
        return floatingButton;
    }

    private HBox renderSearchBar() {
        HBox searchBar = new HBox();
        searchBar.getStyleClass().add("search-bar");

        ImageView searchIcon = new ImageView(
                new Image(getClass().getResource("/images/search.png").toExternalForm())
        );
        searchIcon.setFitWidth(18);
        searchIcon.setFitHeight(18);

        Button searchButton = new Button();
        searchButton.setGraphic(searchIcon);
        searchButton.getStyleClass().add("search-button");
        searchButton.setOnAction(e ->
                beginSearch()
        );

        searchBox = new ComboBox<>();
        searchBox.getItems().addAll(controller.getListSearchFields());
        searchBox.setValue(controller.getListSearchFields().getFirst());
        searchBox.getStyleClass().add("search-combo-box");
        //setando no controle o campo colocado acima como valor padrão

        searchField = new TextField();
        searchField.setPromptText("Pesquisar...");
        searchField.setMaxWidth(Double.MAX_VALUE);
        searchField.getStyleClass().add("search-text-field");

        HBox.setHgrow(searchField, Priority.ALWAYS);
        searchBar.getChildren().addAll(searchButton, searchBox, searchField);

        return searchBar;
    }

    private TitledPane renderFilterPane() {
        TitledPane filterPane = new TitledPane();
        filterPane.setText("Filtros");
        TitledPaneCollapseListener.attach(filterPane);
        filterPane.getStyleClass().add("filter-pane");
        VBox vbox = new VBox(10);

        HBox filterBox = new HBox();
        genreFilter = new FilterItem("Genêro", EnumUtils.getDescricoes(GenreFilterType.class));

        yearFilter = new FilterItem("Ano", EnumUtils.getDescricoes(YearFilterType.class), TextFieldUtil.numericFormatter(4));

        Region spacer = new Region();
        HBox.setHgrow(genreFilter.getNode(), Priority.ALWAYS);
        HBox.setHgrow(yearFilter.getNode(), Priority.ALWAYS);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        filterBox.getChildren().addAll(
                genreFilter.getNode(),
                spacer,
                yearFilter.getNode());
        filterBox.setId("filterBox");

        VBox orderBy = orderByItem();

        vbox.getChildren().addAll(filterBox, orderBy);
        filterPane.setContent(vbox);
        return filterPane;
    }

    private VBox orderByItem() {
        VBox hBox = new VBox();
        Label label = new Label("Ordernar por");
        orderByGroup = new ToggleGroup();
        RadioButton descending = new RadioButton("Melhor Avaliado");
        RadioButton ascending = new RadioButton("Pior Avaliado");

        descending.setToggleGroup(orderByGroup);
        ascending.setToggleGroup(orderByGroup);

        descending.setUserData("Melhor Avaliado");
        ascending.setUserData("Pior Avaliado");

        // Seleciona o botão "descending" por padrão
        descending.setSelected(true);

        // Evita que o botão selecionado seja desmarcado (nenhum selecionado)
        orderByGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null && oldVal != null) {
                // Restaura a seleção anterior se tentar desmarcar o botão atual
                oldVal.setSelected(true);
            }
        });

        hBox.getChildren().addAll(label, descending, ascending);

        return hBox;
    }

    private class FilterItem {
        VBox root;
        String itemName;
        ComboBox<String> comboBox;
        TextField textField;

        // Construtor padrão sem formatter
        FilterItem(String itemName, List<String> operadores) {
            this(itemName, operadores, null); // Chama o outro construtor com formatter nulo
        }

        // Construtor com TextFormatter opcional
        FilterItem(String itemName, List<String> operadores, TextFormatter<?> formatter) {
            this.itemName = itemName;
            root = new VBox(5);


            Text label = new Text(itemName);
            label.getStyleClass().add("filter-label");

            comboBox = new ComboBox<>();
            comboBox.getItems().addAll(operadores);
            comboBox.setValue(operadores.getFirst());
            comboBox.getStyleClass().add("filter-combo-box");

            textField = new TextField();
            textField.setPromptText("Valor...");
            textField.setMaxWidth(Double.MAX_VALUE);
            textField.getStyleClass().add("filter-text-field");

            if (formatter != null) {
                textField.setTextFormatter(formatter);
            }

            HBox.setHgrow(textField, Priority.ALWAYS);
            HBox hbox = new HBox(comboBox, textField);

            root.getChildren().addAll(label, hbox);
        }

        Node getNode() {
            return root;
        }
    }


}
