package com.mountain_vd.viewFX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import com.mountain_vd.viewFX.commons.Displayable;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class RootScene implements Displayable {
    BorderPane borderPane;
    Scene scene;
    private StackPane rootStack;

    public RootScene() {
        render();
        init();
    }


    public Scene getScene() {
        return scene;
    }

    private void init() {
        setBaseTheme();
//        setLitghMode();
    }

    @Override
    public void render() {
        borderPane = new BorderPane();
        rootStack = new StackPane(borderPane);
        scene = new Scene(rootStack, 1000, 600);

        MenuBar menuBar = new MenuBar(this);
        borderPane.setLeft(menuBar.getNode());

        scene.setRoot(rootStack);
    }

    public void setMainContent(Node node) {
        borderPane.setCenter(node);
    }

    public void setLitghMode() {
        scene.getStylesheets().clear();
        setBaseTheme();
//        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/light-theme.css")).toExternalForm());

    }

    public void setDarkMode() {
        scene.getStylesheets().clear();
        setBaseTheme();
//        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/dark-theme.css")).toExternalForm());
    }

    private void setBaseTheme(){
//        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/reset.css")).toExternalForm());
//        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/base.css")).toExternalForm());
    }

    public void showMessage(String message) {
        // Fundo escurecido
        Region background = new Region();
        background.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);");
        background.setPrefSize(scene.getWidth(), scene.getHeight());
        background.setOnMouseClicked(e -> {});
        background.setMouseTransparent(false);

        // Mensagem
        Label label = new Label(message);
        label.setWrapText(true);

        VBox content = new VBox(label);
        content.setAlignment(Pos.CENTER_LEFT);
        content.setPadding(new Insets(20, 20, 20, 20));
        content.setSpacing(10);

        // Botão de fechar (canto superior direito)
        Image normalImage = new Image(getClass().getResource("/images/close.png").toExternalForm());
        Image hoverImage = new Image(getClass().getResource("/images/closeBold.png").toExternalForm());
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
