
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.Menu;
import mocks.MockFilme;
import mocks.MockLivro;
import mocks.MockSerie;

import java.util.Scanner;

/**
 * Classe principal do sistema DiarioCultural.
 *
 * <p>Responsável por iniciar a aplicação, carregando opcionalmente dados de teste e exibindo
 * a interface de menu no terminal.</p>
 *
 * <p>O comportamento de inicialização com dados de teste pode ser controlado por meio de um
 * arquivo de configuração externo {@code config.properties}. Este arquivo deve conter a propriedade
 * {@code teste}, cujo valor define se os métodos de simulação de dados {@code MockFilme},
 * {@code MockLivro} e {@code MockSerie} serão executados.</p>
 *
 * <p>Exemplo de conteúdo do arquivo {@code config.properties}:</p>
 * <pre>
 * teste=true
 * </pre>
 *
 * <p>Se o arquivo de configuração estiver ausente ou ocorrer algum erro durante a leitura,
 * o programa prosseguirá com {@code teste = false} por padrão.</p>
 *
 * <p>Após a execução dos testes (se habilitados), é criada uma instância do menu principal
 * da aplicação, que permite a interação do usuário através do terminal.</p>
 *
 * @author Rafael Henrique e Vitor Gabriel
 * @version 1.0
 * @since 2025-04-23
 */
public class DiarioCultural extends Application {


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
        floatingButton.setShape(new Circle(60/2));
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

    /**
     * Ponto de entrada da aplicação DiarioCultural.
     *
     * <p>Carrega as configurações a partir do arquivo {@code config.properties}, executa os dados de teste
     * se habilitados, e inicia a interface de menu via terminal.</p>
     *
     * @param args argumentos de linha de comando (não utilizados atualmente).
     */
    public static void main(String[] args) {
        launch(args);
    }
}
