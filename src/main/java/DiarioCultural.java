package main.java;

import view.Menu;
import mocks.MockFilme;
import mocks.MockLivro;
import mocks.MockSerie;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;
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
public class DiarioCultural {

    /**
     * Ponto de entrada da aplicação DiarioCultural.
     *
     * <p>Carrega as configurações a partir do arquivo {@code config.properties}, executa os dados de teste
     * se habilitados, e inicia a interface de menu via terminal.</p>
     *
     * @param args argumentos de linha de comando (não utilizados atualmente).
     */
    public static void main(String[] args) {
        boolean teste = true;

        if (teste) {
            MockFilme.rodar();
            MockLivro.rodar();
            MockSerie.rodar();
        }

        // Inicializa o menu principal
        Scanner terminal = new Scanner(System.in);
        new Menu().draw(terminal);
    }
}
