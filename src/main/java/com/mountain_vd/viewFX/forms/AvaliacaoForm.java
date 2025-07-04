package com.mountain_vd.viewFX.forms;

import com.mountain_vd.model.Avaliacao;
import com.mountain_vd.model.commons.IAvaliavel;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.commons.Component;
import com.mountain_vd.viewFX.handlers.AvaliacaoFormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.time.format.DateTimeFormatter;

/**
 * Formulário para cadastro e exibição de avaliações de uma entidade {@link IAvaliavel}.
 * <p>
 * Este componente permite que o usuário insira uma nova avaliação contendo data de consumo,
 * nota e comentário, além de exibir uma lista das avaliações já registradas.
 * </p>
 *
 * <p>
 * A classe implementa a interface {@link Component}, garantindo que possa ser renderizada
 * e adicionada à interface gráfica principal.
 * </p>
 */
public class AvaliacaoForm implements Component {

    /** Referência à cena principal da aplicação. */
    RootScene rootScene;

    /** Lista observável que armazena as avaliações da entidade. */
    ObservableList<Avaliacao> avaliacoes;

    /** Entidade a ser avaliada, que implementa {@link IAvaliavel}. */
    IAvaliavel entity;

    /** Conteúdo principal do formulário, composto por campos de entrada e lista. */
    VBox content;

    private DatePicker dataConsumoPicker;
    private Spinner<Integer> notaSpinner;
    private TextArea comentarioArea;
    private ListView<Avaliacao> listView;

    /**
     * Construtor do formulário de avaliações.
     *
     * @param rootScene a cena principal onde o formulário será exibido
     * @param entity    a entidade a ser avaliada
     */
    public AvaliacaoForm(RootScene rootScene, IAvaliavel entity) {
        this.rootScene = rootScene;
        this.entity = entity;
        this.avaliacoes = FXCollections.observableArrayList();
        this.avaliacoes.addAll(entity.getAvaliacoes());
        render();
    }

    /**
     * Retorna o nó principal do formulário, que pode ser adicionado a outros containers.
     *
     * @return nó raiz do componente
     */
    @Override
    public Node getNode() {
        return content;
    }

    /**
     * Renderiza a interface do formulário, criando campos de entrada,
     * botões e a lista de avaliações.
     */
    @Override
    public void render() {
        content = new VBox(10);
        content.setPadding(new Insets(10, 0, 10, 0));

        // Container dos campos de entrada
        HBox camposBox = new HBox(10);

        // Seção esquerda com data, nota e botão
        VBox esquerdaBox = new VBox(10);

        // Campo de data de consumo
        Label dataConsumoLabel = new Label("Data de Consumo");
        Tooltip dataTooltip = new Tooltip("Selecione a data em que você consumiu a mídia.");
        Tooltip.install(dataConsumoLabel, dataTooltip);

        dataConsumoPicker = new DatePicker();
        dataConsumoPicker.setPromptText("Ex: 01/01/2024");
        Tooltip.install(dataConsumoPicker, dataTooltip);

        // Campo de nota
        Label notaLabel = new Label("Nota");
        Tooltip notaTooltip = new Tooltip("Atribua uma nota de 1 a 5 para a mídia.");
        Tooltip.install(notaLabel, notaTooltip);

        notaSpinner = new Spinner<>(1, 5, 1);
        notaSpinner.setEditable(true);
        notaSpinner.setPromptText("Ex: 4");
        Tooltip.install(notaSpinner, notaTooltip);

        // Validação para garantir notas de 1 a 5
        notaSpinner.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            try {
                int value = Integer.parseInt(newValue);
                if (value < 1 || value > 5) notaSpinner.getEditor().setText(oldValue);
            } catch (NumberFormatException e) {
                notaSpinner.getEditor().setText(oldValue);
            }
        });

        // Botão para adicionar avaliação
        Button addButton = new Button("Adicionar Avaliação");
        addButton.setOnAction(e -> {
            AvaliacaoFormController.adicionarAvaliacao(
                    rootScene, entity, dataConsumoPicker.getValue(),
                    notaSpinner.getValue(), comentarioArea.getText(), avaliacoes
            );
            dataConsumoPicker.setValue(null);
            notaSpinner.getValueFactory().setValue(1);
            comentarioArea.clear();
        });

        esquerdaBox.getChildren().addAll(
                new VBox(5, dataConsumoLabel, dataConsumoPicker),
                new VBox(5, notaLabel, notaSpinner),
                addButton
        );

        // Campo de comentário
        Label comentarioLabel = new Label("Comentário");
        Tooltip comentarioTooltip = new Tooltip("Escreva um breve comentário sobre a mídia.");
        Tooltip.install(comentarioLabel, comentarioTooltip);

        comentarioArea = new TextArea();
        comentarioArea.setWrapText(true);
        comentarioArea.setPromptText("Ex: Achei muito interessante!");
        comentarioArea.setPrefRowCount(5);
        Tooltip.install(comentarioArea, comentarioTooltip);

        VBox comentarioBox = new VBox(5, comentarioLabel, comentarioArea);
        HBox.setHgrow(comentarioBox, Priority.ALWAYS);
        HBox.setHgrow(comentarioArea, Priority.ALWAYS);

        camposBox.getChildren().addAll(esquerdaBox, comentarioBox);

        // Lista de avaliações existentes
        listView = renderAvaliacaoList();
        VBox.setVgrow(listView, Priority.ALWAYS);

        VBox rightBox = new VBox(new Label("Avaliações"), listView);
        rightBox.setSpacing(5);

        // Monta o conteúdo final
        content.getChildren().addAll(camposBox, rightBox);
    }

    /**
     * Cria e configura a lista que exibe as avaliações existentes,
     * formatando cada item com data, nota e comentário.
     *
     * @return ListView configurado com as avaliações
     */
    private ListView<Avaliacao> renderAvaliacaoList() {
        ListView<Avaliacao> listView = new ListView<>(avaliacoes);
        listView.getStyleClass().add("generic-list");
        listView.setMinHeight(100);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Avaliacao avaliacao, boolean empty) {
                super.updateItem(avaliacao, empty);
                if (empty || avaliacao == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    VBox vbox = new VBox(5);

                    HBox hBox = new HBox(10);
                    Label dataAvaliacaoLabel = new Label("Avaliado em: " + avaliacao.getDataAvaliacao().format(dateTimeFormat));
                    Label dataConsumoLabel = new Label("Consumido em: " + avaliacao.getDataConsumo().format(dateFormat));
                    Label notaLabel = new Label("Nota: " + avaliacao.getPontuacao());

                    Region spacer1 = new Region();
                    Region spacer2 = new Region();
                    Region spacer3 = new Region();
                    HBox.setHgrow(spacer1, Priority.ALWAYS);
                    HBox.setHgrow(spacer2, Priority.ALWAYS);
                    HBox.setHgrow(spacer3, Priority.ALWAYS);

                    hBox.getChildren().addAll(dataAvaliacaoLabel, spacer1, dataConsumoLabel, spacer2, notaLabel, spacer3);

                    Label comentarioLabel = new Label("Comentário: " + avaliacao.getReview());
                    comentarioLabel.setWrapText(true);

                    vbox.getChildren().addAll(hBox, comentarioLabel);
                    setGraphic(vbox);
                }
            }
        });

        return listView;
    }
}
