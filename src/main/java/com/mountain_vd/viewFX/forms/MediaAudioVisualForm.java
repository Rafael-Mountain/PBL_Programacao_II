package com.mountain_vd.viewFX.forms;

import com.mountain_vd.viewFX.GenericListPane;
import com.mountain_vd.viewFX.RootScene;
import com.mountain_vd.viewFX.handlers.ElencoListController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.List;

/**
 * Formulário específico para mídias audiovisuais, estendendo {@link MediaForm}.
 * Fornece campos adicionais como título original, local disponível e elenco.
 */
public class MediaAudioVisualForm extends MediaForm {
    private TextField tituloOriginalField;
    private TextField LocalDisponivelField;
    private ObservableList<String> elenco;
    private GenericListPane<String> elencoListPane;

    /**
     * Construtor que recebe a cena raiz para controle de interface.
     *
     * @param rootScene Cena raiz da aplicação.
     */
    public MediaAudioVisualForm(RootScene rootScene) {
        super(rootScene);
    }

    /**
     * Renderiza o formulário, criando a aba principal "Mídia".
     */
    @Override
    public void render() {
        tabPane = new TabPane();
        Tab tab = new Tab("Mídia");
        tab.setContent(renderMidiaAudioVisual());
        tab.setClosable(false);
        tabPane.getTabs().add(tab);
    }

    /**
     * Renderiza o conteúdo principal do formulário audiovisual, incluindo campos:
     * título original, local disponível e lista de elenco.
     *
     * @return Um {@link HBox} com os elementos do formulário.
     * @throws IllegalStateException se o GridPane esperado não for encontrado.
     */
    protected HBox renderMidiaAudioVisual() {
        this.elenco = FXCollections.observableArrayList();
        HBox content = renderMidia();
        content.setMaxHeight(400);

        GridPane gridPane = content.getChildren().stream()
                .filter(node -> node instanceof GridPane)
                .map(node -> (GridPane) node)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("GridPane não encontrado"));

        // --- Campo: Título Original ---
        Label tituloOriginalLabel = new Label("Título Original");
        Tooltip tituloOriginalTooltip = new Tooltip("Ex: Título no idioma original, como 'Fight Club' ou 'Le Fabuleux Destin d’Amélie Poulain'");
        Tooltip.install(tituloOriginalLabel, tituloOriginalTooltip);

        tituloOriginalField = new TextField();
        tituloOriginalField.setPromptText("Digite o título original da obra");
        Tooltip.install(tituloOriginalField, tituloOriginalTooltip);

        HBox.setHgrow(tituloOriginalField, Priority.ALWAYS);
        HBox divTituloOriginal = new HBox(10, tituloOriginalLabel, tituloOriginalField);
        gridPane.add(divTituloOriginal, 0, 3, 5, 1);

        // --- Campo: Local Disponível ---
        Label localDisponivelLabel = new Label("Local Disponível");
        Tooltip localTooltip = new Tooltip("Informe onde a mídia pode ser acessada atualmente");
        Tooltip.install(localDisponivelLabel, localTooltip);

        LocalDisponivelField = new TextField();
        LocalDisponivelField.setPromptText("Ex: Netflix, Amazon Prime...");
        Tooltip.install(LocalDisponivelField, localTooltip);

        HBox divLocalDisponivel = new HBox(10, localDisponivelLabel, LocalDisponivelField);
        gridPane.add(divLocalDisponivel, 0, 4, 2, 1);

        // --- Lista de Elenco ---
        ElencoListController elencoListController = new ElencoListController(rootScene);
        elencoListPane = new GenericListPane<>(elenco, elencoListController::addAtor, "Elenco");
        Node elencoNode = elencoListPane.getNode();
        GridPane.setColumnSpan(elencoNode, 3);
        GridPane.setRowSpan(elencoNode, 2);
        gridPane.add(elencoNode, 2, 4);

        return content;
    }

    // =============================
    //          GETTERS
    // =============================

    /**
     * Obtém o título original preenchido no formulário.
     *
     * @return Título original da mídia.
     */
    public String getTituloOriginal() {
        return tituloOriginalField.getText();
    }

    /**
     * Obtém o local onde a mídia está disponível.
     *
     * @return Local disponível da mídia.
     */
    public String getLocalDisponivel() {
        return LocalDisponivelField.getText();
    }

    /**
     * Obtém a lista de atores/participantes do elenco.
     *
     * @return Lista de nomes do elenco.
     */
    public List<String> getElenco() {
        return elenco;
    }

    // =============================
    //          SETTERS
    // =============================

    /**
     * Define o título original no campo correspondente.
     *
     * @param tituloOriginal Texto com o título original.
     */
    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginalField.setText(tituloOriginal);
    }

    /**
     * Define o local disponível no campo correspondente.
     *
     * @param localDisponivel Texto com o local onde a mídia pode ser acessada.
     */
    public void setLocalDisponivel(String localDisponivel) {
        this.LocalDisponivelField.setText(localDisponivel);
    }

    /**
     * Define a lista de elenco a partir de uma lista externa.
     *
     * @param elenco Lista com nomes do elenco.
     */
    public void setElenco(List<String> elenco) {
        this.elenco.setAll(elenco);
    }

    // =============================
    //          DISABLE
    // =============================

    /**
     * Desabilita o campo de título original para edição, mantendo a opacidade para visualização.
     */
    public void disableTituloOriginalField() {
        if (tituloOriginalField != null) {
            tituloOriginalField.setDisable(true);
            tituloOriginalField.setOpacity(1);
        }
    }

    /**
     * Desabilita o campo de local disponível para edição, mantendo a opacidade para visualização.
     */
    public void disableLocalDisponivelField() {
        if (LocalDisponivelField != null) {
            LocalDisponivelField.setDisable(true);
            LocalDisponivelField.setOpacity(1);
        }
    }

    /**
     * Desabilita a lista de elenco para edição, mantendo a opacidade para visualização.
     */
    public void disableElencoList() {
        if (elencoListPane != null && elencoListPane.getNode() != null) {
            elencoListPane.getNode().setDisable(true);
            elencoListPane.getNode().setOpacity(1);
        }
    }

    /**
     * Desabilita todos os campos do formulário audiovisual.
     */
    @Override
    public void disableFields() {
        super.disableFields();
        disableTituloOriginalField();
        disableLocalDisponivelField();
        disableElencoList();
    }
}
