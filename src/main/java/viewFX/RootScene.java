package viewFX;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import viewFX.commons.Displayable;

import java.util.Objects;


public class RootScene implements Displayable {
    BorderPane borderPane;
    Scene scene;

    public RootScene() {
        render();
    }

    public Scene getScene() {
        return scene;
    }

    @Override
    public void render() {
        borderPane = new BorderPane();
        scene = new Scene(borderPane, 800, 600);

        MenuBar menuBar = new MenuBar(this);

        borderPane.setLeft(menuBar.getNode());

        scene.setRoot(borderPane);
        setLitghMode();
    }

    void setLitghMode() {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/light-theme.css")).toExternalForm());

    }

    void setDarkMode() {
        scene.getStylesheets().clear();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/dark-theme.css")).toExternalForm());
    }

    void setMainContent(Node node) {
        borderPane.setCenter(node);
    }
}
