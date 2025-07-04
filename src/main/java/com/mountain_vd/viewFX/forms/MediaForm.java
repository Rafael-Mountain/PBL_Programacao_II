package com.mountain_vd.viewFX.forms;

import com.mountain_vd.controller.util.TextFieldUtil;
import com.mountain_vd.model.Avaliacao;
import com.mountain_vd.model.Genero;
import com.mountain_vd.model.commons.IAvaliavel;
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

/**
 * Formulário base para mídias, contendo campos comuns como título, ano de lançamento,
 * gêneros e status de consumo.
 * Possui suporte para abas e integração com avaliações.
 */
public class MediaForm implements Component {
    RootScene rootScene;
    protected TabPane tabPane;
    private TextField titleField;
    private TextField anoField;
    private CheckBox consumerCheckbox;
    private ObservableList<Genero> generos;

    private AvaliacaoForm avaliacaoForm;

    private GenericListPane<Genero> genrePickerPane;

    /**
     * Construtor que inicializa o formulário com referência à cena raiz da aplicação.
     *
     * @param rootScene Cena raiz da aplicação.
     */
    public MediaForm(RootScene rootScene) {
        this.rootScene = rootScene;
        this.generos = FXCollections.observableArrayList();
        render();
    }

    /**
     * Retorna o nó raiz do componente, neste caso o TabPane.
     *
     * @return Nó raiz {@link Node} contendo o formulário.
     */
    @Override
    public Node getNode() {
        return tabPane;
    }

    /**
     * Renderiza o formulário inicializando a aba "Mídia" e seus componentes.
     */
    @Override
    public void render() {
        tabPane = new TabPane();
        Tab tab = new Tab("Mídia");
        tab.setContent(renderMidia());
        tab.setClosable(false);
        tabPane.getTabs().add(tab);
    }

    /**
     * Adiciona uma aba para avaliações vinculadas a uma entidade avaliada.
     *
     * @param entity Objeto que implementa a interface {@link IAvaliavel}.
     */
    public void addTabAvailable(IAvaliavel entity) {
        Tab tab = new Tab("Avaliações");
        tab.setClosable(false);

        System.out.println(entity);
        avaliacaoForm = new AvaliacaoForm(rootScene, entity);

        tab.setContent(avaliacaoForm.getNode());
        tabPane.getTabs().add(tab);
    }

    /**
     * Renderiza a parte do formulário relacionada à mídia com campos de título, ano,
     * gêneros e checkbox de consumo.
     *
     * @return Um {@link HBox} contendo os campos do formulário.
     */
    protected HBox renderMidia() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(10));
        hbox.setMaxHeight(290);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

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
        genrePickerPane = new GenericListPane<>(generos, controller::addGenre, "Gêneros");
        Node genreNode = genrePickerPane.getNode();
        GridPane.setColumnSpan(genreNode, 3);
        GridPane.setRowSpan(genreNode, 2);
        GridPane.setHgrow(genreNode, Priority.ALWAYS);
        GridPane.setVgrow(genreNode, Priority.ALWAYS);
        gridPane.add(genreNode, 2, 1);

        hbox.getChildren().add(gridPane);
        return hbox;
    }

    // ======= GETTERS =======

    /**
     * Obtém o título da mídia informado no formulário.
     *
     * @return Título da mídia.
     */
    public String getTitle() {
        return titleField.getText();
    }

    /**
     * Obtém o ano de lançamento informado no formulário.
     *
     * @return Ano de lançamento (string com 4 dígitos).
     */
    public String getAnoLancamento() {
        return anoField.getText().trim();
    }

    /**
     * Verifica se a mídia foi marcada como já consumida.
     *
     * @return true se marcada como consumida, false caso contrário.
     */
    public boolean getConsumer() {
        return consumerCheckbox.isSelected();
    }

    /**
     * Obtém a lista de gêneros selecionados.
     *
     * @return Lista de {@link Genero} selecionados.
     */
    public List<Genero> getGeneros() {
        return generos;
    }

    // ======= SETTERS =======

    /**
     * Define o título da mídia.
     *
     * @param title Título a ser definido.
     */
    public void setTitle(String title) {
        titleField.setText(title);
    }

    /**
     * Define o ano de lançamento da mídia.
     *
     * @param ano Ano a ser definido (string com 4 dígitos).
     */
    public void setAnoLancamento(String ano) {
        anoField.setText(ano);
    }

    /**
     * Define o estado da checkbox de mídia consumida.
     *
     * @param value true para marcada, false para desmarcada.
     */
    public void setConsumer(boolean value) {
        consumerCheckbox.setSelected(value);
    }

    /**
     * Define a lista de gêneros selecionados.
     *
     * @param generosList Lista de gêneros para setar.
     */
    public void setGeneros(List<Genero> generosList) {
        this.generos.setAll(generosList);
    }

    // ======= DISABLE METHODS =======

    /**
     * Desabilita o campo do título para edição, mantendo a opacidade.
     */
    public void disableTitleField() {
        if (titleField != null) {
            titleField.setDisable(true);
            titleField.setOpacity(1); // força a opacidade a 1
        }
    }

    /**
     * Desabilita o campo do ano para edição, mantendo a opacidade.
     */
    public void disableAnoField() {
        if (anoField != null) {
            anoField.setDisable(true);
            anoField.setOpacity(1);
        }
    }

    /**
     * Desabilita a checkbox de mídia consumida, mantendo a opacidade.
     */
    public void disableConsumerCheckbox() {
        if (consumerCheckbox != null) {
            consumerCheckbox.setDisable(true);
            consumerCheckbox.setOpacity(1);
        }
    }

    /**
     * Desabilita a lista de gêneros para seleção, mantendo a opacidade.
     */
    public void disableGeneroList() {
        if (genrePickerPane != null && genrePickerPane.getNode() != null) {
            genrePickerPane.disableAllFields();
        }
    }

    /**
     * Desabilita todos os campos do formulário.
     */
    public void disableFields() {
        disableTitleField();
        disableAnoField();
        disableConsumerCheckbox();
        disableGeneroList();
    }
}
