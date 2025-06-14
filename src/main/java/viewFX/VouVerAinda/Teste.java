package viewFX.VouVerAinda;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Teste extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Conteúdo de fundo (pode ser qualquer layout)
        VBox backgroundContent = new VBox(new Text("Conteúdo principal"));

        // Botão flutuante
        Button floatingButton = new Button("+");
        floatingButton.setStyle("-fx-background-color: #009688; -fx-text-fill: white; -fx-font-size: 20px;");
        floatingButton.setMinSize(60, 60);
        floatingButton.setPadding(Insets.EMPTY);
//        floatingButton.setBorder(new Border(new BorderStroke(Color.DARKGRAY, BorderStrokeStyle.SOLID, new CornerRadii(25), BorderWidths.DEFAULT)));
//        floatingButton.setBackground(new Background(new BackgroundFill(Color.TEAL, new CornerRadii(10), Insets.EMPTY)));
        floatingButton.setShape(new Circle(60 / 2));
        // Layout para posicionar o botão
        StackPane root = new StackPane(backgroundContent);

        // Usar AnchorPane para posicionamento absoluto
        AnchorPane floatingPane = new AnchorPane(floatingButton);
        AnchorPane.setBottomAnchor(floatingButton, 20.0);
        AnchorPane.setRightAnchor(floatingButton, 20.0);

        root.getChildren().add(floatingPane);

        BorderPane borderPane = new BorderPane();
        VBox sidebar = new VBox(new Text("Conteúdo lateral"));
        root.setStyle("-fx-padding: 20;");

        borderPane.setCenter(root);
        borderPane.setLeft(sidebar);
        Scene scene = new Scene(borderPane, 700, 500);


        stage.setScene(scene);
        stage.setTitle("Exemplo com CSS");
        stage.show();

    }

}
