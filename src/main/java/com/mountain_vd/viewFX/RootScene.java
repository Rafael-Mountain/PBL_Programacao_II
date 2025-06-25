package com.mountain_vd.viewFX;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import com.mountain_vd.viewFX.commons.Displayable;

import java.util.Objects;


public class RootScene implements Displayable {
    BorderPane borderPane;
    Scene scene;

    public RootScene() {
        render();
        init();
    }


    public Scene getScene() {
        return scene;
    }

    private void init() {
        setBaseTheme();
        setLightMode();
    }

    @Override
    public void render() {
        borderPane = new BorderPane(); //
        scene = new Scene(borderPane, 1000, 600);


        MenuBar menuBar = new MenuBar(this);
        borderPane.setLeft(menuBar.getNode());

        scene.setRoot(borderPane);

    }

    public void setLightMode() {
        scene.getStylesheets().clear();
        setBaseTheme();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/light-theme.css")).toExternalForm());

    }

    public void setDarkMode() {
        scene.getStylesheets().clear();
        setBaseTheme();
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/dark-theme.css")).toExternalForm());
    }

    private void setBaseTheme(){
        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/reset.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/base.css")).toExternalForm());
    }

    public void setMainContent(Node node) {
        borderPane.setCenter(node);
    }
}
