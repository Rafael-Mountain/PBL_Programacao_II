package viewFX;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class MenuBar implements Component {
    VBox vbox;
    RootScene rootScene;

    MenuBar(){
        render();
    }

    @Override
    public Node getNode() {
        return vbox;
    }

    @Override
    public void render() {
        vbox = new VBox();

    }
}
