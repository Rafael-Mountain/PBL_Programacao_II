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

/**
 * Componente gráfico que exibe uma interface para busca, filtragem e exibição de resultados
 * de mídias, integrando o controlador {@link SearchPaneController} com a interface do usuário.
 *
 * Permite o usuário escolher o campo de busca, aplicar filtros de gênero e ano, escolher
 * a ordenação dos resultados, e navegar para a tela de adição de nova mídia.
 */
public class SearchPane implements Component {
    private SearchPaneController controller;
    private RootScene rootScene;
    private SearchTableResult tableResult;
    private StackPane pane;
    private double paddingFloatingButton = 40;

    private ComboBox<String> searchBox; // Campo de seleção do campo de busca
    private TextField searchField;      // Campo para digitar o termo de busca
    private FilterItem genreFilter;     // Filtro para gênero
    private FilterItem yearFilter;      // Filtro para ano
    private ToggleGroup orderByGroup;   // Grupo de botões para escolher ordenação

    /**
     * Construtor que associa o painel ao {@link RootScene} e ao controlador de busca.
     * Inicializa a interface e executa uma busca inicial.
     *
     * @param rootScene Cena raiz da aplicação
     * @param controller Controlador responsável pela lógica da busca e manipulação dos dados
     */
    public SearchPane(RootScene rootScene, SearchPaneController controller) {
        this.rootScene = rootScene;
        this.controller = controller;
        render();
        beginSearch();
    }

    /**
     * Retorna o nó JavaFX raiz deste componente para inclusão na cena.
     *
     * @return Nó raiz do painel de busca.
     */
    @Override
    public Node getNode() {
        return pane;
    }

    /**
     * Renderiza toda a interface gráfica do painel de busca,
     * incluindo barra de busca, filtros, tabela de resultados e botão flutuante.
     */
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

    /**
     * Executa a busca conforme os critérios definidos nos campos e filtros da interface,
     * atualizando os resultados exibidos na tabela.
     */
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

    /**
     * Cria e retorna o botão flutuante que permite o usuário acessar a tela de adição de nova mídia.
     *
     * @return Botão flutuante de adicionar
     */
    private Button getFloatingButton() {
        Button floatingButton = new Button("+");
        floatingButton.getStyleClass().add("floating-button");
        floatingButton.setOnAction(e -> {
            rootScene.setMainContent(controller.getAddScreen());
        });
        return floatingButton;
    }

    /**
     * Renderiza a barra de busca contendo o campo para selecionar o campo de busca,
     * o campo de texto para o termo de busca e o botão de executar a busca.
     *
     * @return HBox contendo os componentes da barra de busca.
     */
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
        searchButton.setOnAction(e -> beginSearch());

        searchBox = new ComboBox<>();
        searchBox.getItems().addAll(controller.getListSearchFields());
        searchBox.setValue(controller.getListSearchFields().get(0));  // Corrigido getFirst() para get(0)
        searchBox.getStyleClass().add("search-combo-box");

        searchField = new TextField();
        searchField.setPromptText("Pesquisar...");
        searchField.setMaxWidth(Double.MAX_VALUE);
        searchField.getStyleClass().add("search-text-field");

        HBox.setHgrow(searchField, Priority.ALWAYS);
        searchBar.getChildren().addAll(searchButton, searchBox, searchField);

        return searchBar;
    }

    /**
     * Renderiza o painel de filtros contendo filtros de gênero, ano e ordenação.
     *
     * @return TitledPane contendo os filtros.
     */
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

    /**
     * Cria o grupo de botões de rádio para seleção da ordenação dos resultados
     * (Melhor Avaliado ou Pior Avaliado).
     *
     * @return VBox contendo os controles de ordenação.
     */
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

        descending.setSelected(true);

        // Evita que o botão selecionado seja desmarcado (nenhum selecionado)
        orderByGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal == null && oldVal != null) {
                oldVal.setSelected(true);
            }
        });

        hBox.getChildren().addAll(label, descending, ascending);

        return hBox;
    }

    /**
     * Classe interna que representa um item de filtro com uma ComboBox para operador
     * e um TextField para o valor do filtro.
     */
    private class FilterItem {
        VBox root;
        String itemName;
        ComboBox<String> comboBox;
        TextField textField;

        /**
         * Construtor padrão sem formatter.
         *
         * @param itemName Nome do filtro
         * @param operadores Lista de operadores para o filtro
         */
        FilterItem(String itemName, List<String> operadores) {
            this(itemName, operadores, null);
        }

        /**
         * Construtor com TextFormatter opcional para validar entrada do campo texto.
         *
         * @param itemName Nome do filtro
         * @param operadores Lista de operadores para o filtro
         * @param formatter Formatter para o campo texto (pode ser null)
         */
        FilterItem(String itemName, List<String> operadores, TextFormatter<?> formatter) {
            this.itemName = itemName;
            root = new VBox(5);

            Text label = new Text(itemName);
            label.getStyleClass().add("filter-label");

            comboBox = new ComboBox<>();
            comboBox.getItems().addAll(operadores);
            comboBox.setValue(operadores.get(0));
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

        /**
         * Retorna o nó JavaFX raiz deste item de filtro.
         *
         * @return Nó raiz do filtro.
         */
        Node getNode() {
            return root;
        }
    }
}
