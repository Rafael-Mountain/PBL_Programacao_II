package com.mountain_vd.viewFX;

import com.mountain_vd.viewFX.commons.Displayable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

/**
 * Representa a cena principal da aplicação, gerenciando o layout,
 * o tema visual (modo claro/escuro) e exibindo mensagens de erro e sucesso.
 *
 * A cena possui uma barra lateral (MenuBar) e uma área central para o conteúdo principal.
 */
public class RootScene implements Displayable {
    /** Layout principal em formato BorderPane, que organiza as regiões da janela. */
    BorderPane borderPane;

    /** Cena JavaFX que é exibida na janela principal da aplicação. */
    Scene scene;

    /** StackPane raiz que permite sobrepor elementos na cena (como modais). */
    private StackPane rootStack;

    /**
     * Construtor padrão que inicializa a cena,
     * renderizando o layout e configurando o tema padrão.
     */
    public RootScene() {
        render();
        init();
    }

    /**
     * Retorna o objeto Scene da aplicação.
     *
     * @return Cena principal da aplicação.
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * Inicializa a cena definindo o tema base e
     * configurando o modo claro como padrão.
     */
    private void init() {
        setBaseTheme();
        setLightMode();
    }

    /**
     * Renderiza o layout da cena, incluindo a criação da barra de menu
     * e definindo a estrutura base do BorderPane.
     */
    @Override
    public void render() {
        borderPane = new BorderPane();
        rootStack = new StackPane(borderPane);
        scene = new Scene(rootStack, 1000, 600);

        MenuBar menuBar = new MenuBar(this);
        borderPane.setLeft(menuBar.getNode());

        scene.setRoot(rootStack);
    }

    /**
     * Define o conteúdo principal da cena na área central do BorderPane.
     *
     * @param node Nó JavaFX a ser exibido na área principal.
     */
    public void setMainContent(Node node) {
        borderPane.setCenter(node);
    }

    /**
     * Aplica o tema claro à cena, limpando estilos anteriores
     * e carregando as folhas de estilo apropriadas.
     */
    public void setLightMode() {
        scene.getStylesheets().clear();
        setBaseTheme();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/light-theme.css")).toExternalForm());
    }

    /**
     * Aplica o tema escuro à cena, limpando estilos anteriores
     * e carregando as folhas de estilo apropriadas.
     */
    public void setDarkMode() {
        scene.getStylesheets().clear();
        setBaseTheme();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/dark-theme.css")).toExternalForm());
    }

    /**
     * Carrega as folhas de estilo base comuns para todos os temas.
     */
    private void setBaseTheme() {
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/reset.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/base.css")).toExternalForm());
    }

    /**
     * Exibe uma mensagem modal de erro sobrepondo o conteúdo da cena.
     *
     * @param message Mensagem de erro a ser exibida.
     */
    public void showError(String message) {
        VBox vbox = new VBox();

        Label label = new Label(message);
        label.setWrapText(true);

        Image image = new Image(getClass().getResource("/images/error.png").toExternalForm());
        ImageView imageView = new ImageView(image);

        vbox.getChildren().addAll(imageView, label);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        showMessage(vbox);
    }

    /**
     * Exibe uma mensagem modal de sucesso sobrepondo o conteúdo da cena.
     *
     * @param message Mensagem de sucesso a ser exibida.
     */
    public void showSuccess(String message) {
        VBox vbox = new VBox();

        Label label = new Label(message);
        label.setWrapText(true);

        Image image = new Image(getClass().getResource("/images/confirm.png").toExternalForm());
        ImageView imageView = new ImageView(image);

        vbox.getChildren().addAll(imageView, label);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20);

        showMessage(vbox);
    }

    /**
     * Método privado que exibe um nó qualquer como uma mensagem modal,
     * com fundo escurecido e botão para fechar.
     *
     * @param messageContent Nó que representa o conteúdo da mensagem.
     */
    private void showMessage(Node messageContent) {
        // Fundo escurecido
        Region background = new Region();
        background.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);");
        background.setPrefSize(scene.getWidth(), scene.getHeight());
        background.setOnMouseClicked(e -> {
        });
        background.setMouseTransparent(false);

        VBox content = new VBox(messageContent);

        // Botão de fechar (canto superior direito)
        Image normalImage = new Image(Objects.requireNonNull(getClass().getResource("/images/close.png")).toExternalForm());
        Image hoverImage = new Image(Objects.requireNonNull(getClass().getResource("/images/closeBold.png")).toExternalForm());
        ImageView imageView = new ImageView(normalImage);
        imageView.setFitWidth(16);
        imageView.setFitHeight(16);

        Button closeButton = new Button();
        closeButton.setGraphic(imageView);
        closeButton.setStyle("-fx-background-color: transparent; -fx-padding: 4;");
        closeButton.setOnMouseEntered(e -> imageView.setImage(hoverImage));
        closeButton.setOnMouseExited(e -> imageView.setImage(normalImage));

        StackPane overlay = new StackPane();
        overlay.setPrefSize(scene.getWidth(), scene.getHeight());

        // Caixa principal (com borda e canto arredondado)
        BorderPane card = new BorderPane();
        card.setCenter(content);
        card.setTop(closeButton);
        BorderPane.setAlignment(closeButton, Pos.TOP_RIGHT);

        BorderPane.setMargin(closeButton, new Insets(10, 10, 0, 0));
        card.setStyle("-fx-background-color: white; -fx-background-radius: 8px; -fx-border-radius: 8px;");
        card.setMaxWidth(300);
        card.setMaxHeight(200);

        StackPane.setAlignment(card, Pos.CENTER);
        overlay.getChildren().addAll(background, card);

        rootStack.getChildren().add(overlay);

        closeButton.setOnAction(e -> rootStack.getChildren().remove(overlay));
    }
}
