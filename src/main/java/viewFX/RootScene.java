package viewFX;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class RootScene implements Displayable {
    BorderPane borderPane;
    Scene scene;

    RootScene(){
        render();
    }

    public Scene getScene() {
        return scene;
    }

    @Override
     public void render() {
        borderPane = new BorderPane();
    }

    void setMainContent(Node node){
        borderPane.setCenter(node);
    }
}
