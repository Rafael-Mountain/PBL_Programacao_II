package com.mountain_vd.viewFX.forms;

import com.mountain_vd.viewFX.RootScene;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Formulário específico para cadastro e edição de informações sobre livros.
 * <p>
 * Esta classe estende {@link MediaForm} e adiciona campos próprios do contexto de livros,
 * como autor, editora, ISBN e um checkbox indicando se o usuário possui o livro.
 * </p>
 *
 * <p>
 * O formulário é exibido dentro de uma aba nomeada "Livro" e organizado
 * em campos de texto e checkbox com tooltips explicativos.
 * </p>
 */
public class LivroForm extends MediaForm {

    /** Campo para o nome do autor do livro. */
    private TextField autorField;

    /** Campo para o nome da editora do livro. */
    private TextField editoraField;

    /** Checkbox que indica se o usuário possui o livro. */
    private CheckBox possuiCheckbox;

    /** Campo para o código ISBN do livro. */
    private TextField isbnField;

    /**
     * Construtor que inicializa o formulário de livro.
     *
     * @param rootScene a cena principal onde o formulário será exibido
     */
    public LivroForm(RootScene rootScene) {
        super(rootScene);
    }

    /**
     * Renderiza a aba principal do formulário, configurando os campos específicos de livro.
     */
    @Override
    public void render() {
        tabPane = new TabPane();
        Tab tab = new Tab("Livro");
        tab.setContent(renderLivro());
        tab.setClosable(false);
        tabPane.getTabs().add(tab);
    }

    /**
     * Cria e organiza visualmente os campos do formulário de livro,
     * como autor, editora, ISBN e o checkbox de posse.
     *
     * @return um {@link HBox} contendo a estrutura visual montada
     */
    public HBox renderLivro() {
        HBox content = renderMidia();
        content.setMaxHeight(520);

        GridPane gridPane = content.getChildren().stream()
                .filter(node -> node instanceof GridPane)
                .map(node -> (GridPane) node)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("GridPane não encontrado"));

        int startRow = 3;

        // ===== Autor =====
        Label autorLabel = new Label("Autor");
        autorField = new TextField();
        autorField.setPromptText("Nome do autor");
        Tooltip autorTooltip = new Tooltip("Digite o nome do autor do livro");
        Tooltip.install(autorLabel, autorTooltip);
        Tooltip.install(autorField, autorTooltip);

        HBox autorBox = new HBox(10, autorLabel, autorField);
        HBox.setHgrow(autorField, Priority.ALWAYS);
        GridPane.setColumnSpan(autorBox, 5);
        gridPane.add(autorBox, 0, startRow++);

        // ===== Editora =====
        Label editoraLabel = new Label("Editora");
        editoraField = new TextField();
        editoraField.setPromptText("Nome da editora");
        Tooltip editoraTooltip = new Tooltip("Digite o nome da editora do livro");
        Tooltip.install(editoraLabel, editoraTooltip);
        Tooltip.install(editoraField, editoraTooltip);

        HBox editoraBox = new HBox(10, editoraLabel, editoraField);
        HBox.setHgrow(editoraField, Priority.ALWAYS);
        GridPane.setColumnSpan(editoraBox, 5);
        gridPane.add(editoraBox, 0, startRow++);

        // ===== ISBN e Checkbox =====
        Label isbnLabel = new Label("ISBN");
        isbnField = new TextField();
        isbnField.setPromptText("Código ISBN");
        Tooltip isbnTooltip = new Tooltip("Digite o código ISBN do livro");
        Tooltip.install(isbnLabel, isbnTooltip);
        Tooltip.install(isbnField, isbnTooltip);
        HBox.setHgrow(isbnField, Priority.ALWAYS);

        possuiCheckbox = new CheckBox("Você possui esse livro?");
        Tooltip possuiTooltip = new Tooltip("Marque se você possui o livro em formato físico ou digital");
        Tooltip.install(possuiCheckbox, possuiTooltip);

        HBox isbnAndCheckboxBox = new HBox(10, isbnLabel, isbnField, possuiCheckbox);
        isbnAndCheckboxBox.setPadding(new Insets(5, 0, 0, 0));
        GridPane.setColumnSpan(isbnAndCheckboxBox, 5);
        gridPane.add(isbnAndCheckboxBox, 0, startRow++);

        return content;
    }

    // ======= GETTERS =======

    /**
     * Obtém o nome do autor informado.
     *
     * @return nome do autor
     */
    public String getAutor() {
        return autorField.getText();
    }

    /**
     * Obtém o nome da editora informada.
     *
     * @return nome da editora
     */
    public String getEditora() {
        return editoraField.getText();
    }

    /**
     * Verifica se o checkbox "possui" está marcado.
     *
     * @return true se o usuário possui o livro; false caso contrário
     */
    public boolean getPossuiCheckbox() {
        return possuiCheckbox.isSelected();
    }

    /**
     * Obtém o código ISBN informado.
     *
     * @return texto do ISBN
     */
    public String getIsbn() {
        return isbnField.getText();
    }

    // ======= SETTERS =======

    /**
     * Define o valor do campo autor.
     *
     * @param autor nome do autor
     */
    public void setAutor(String autor) {
        autorField.setText(autor);
    }

    /**
     * Define o valor do campo editora.
     *
     * @param editora nome da editora
     */
    public void setEditora(String editora) {
        editoraField.setText(editora);
    }

    /**
     * Define se o checkbox "possui" deve estar marcado.
     *
     * @param possui true para marcar; false para desmarcar
     */
    public void setPossuiCheckbox(boolean possui) {
        possuiCheckbox.setSelected(possui);
    }

    /**
     * Define o valor do campo ISBN.
     *
     * @param isbn código ISBN do livro
     */
    public void setIsbn(String isbn) {
        isbnField.setText(isbn);
    }

    // ======= DISABLE METHODS =======

    /**
     * Desativa o campo autor, mantendo a aparência visual.
     */
    public void disableAutorField() {
        if (autorField != null) {
            autorField.setDisable(true);
            autorField.setOpacity(1);
        }
    }

    /**
     * Desativa o campo editora, mantendo a aparência visual.
     */
    public void disableEditoraField() {
        if (editoraField != null) {
            editoraField.setDisable(true);
            editoraField.setOpacity(1);
        }
    }

    /**
     * Desativa o campo ISBN, mantendo a aparência visual.
     */
    public void disableIsbnField() {
        if (isbnField != null) {
            isbnField.setDisable(true);
            isbnField.setOpacity(1);
        }
    }

    /**
     * Desativa o checkbox "possui", mantendo a aparência visual.
     */
    public void disablePossuiCheckbox() {
        if (possuiCheckbox != null) {
            possuiCheckbox.setDisable(true);
            possuiCheckbox.setOpacity(1);
        }
    }

    /**
     * Desativa todos os campos específicos do formulário de livro,
     * além dos campos herdados da classe pai.
     */
    @Override
    public void disableFields() {
        super.disableFields();
        disableAutorField();
        disableEditoraField();
        disableIsbnField();
        disablePossuiCheckbox();
    }
}
