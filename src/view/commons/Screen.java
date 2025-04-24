package view.commons;

/**
 * A classe abstrata {@code Screen} implementa a interface {@link IScreen} e fornece métodos auxiliares para telas interativas.
 *
 * <p>Ela contém métodos para limpar a tela do terminal e para aguardar um tempo especificado.</p>
 *
 * <p>As classes que extendem {@code Screen} podem aproveitar esses métodos e devem fornecer a implementação para o método,
 * que é responsável por desenhar o conteúdo da tela e coletar entradas do usuário.</p>
 *
 * <p>Exemplo de uso:</p>
 * <pre>
 * public class ExemploTela extends Screen {
 *     @Override
 *     public void draw(Scanner terminal) {
 *         clear();  // Limpa a tela
 *         System.out.println("Exibindo conteúdo na tela.");
 *         await();  // Aguardar 2 segundos
 *     }
 * }
 * </pre>
 *
 * @author
 */
public abstract class Screen implements IScreen {

    /**
     * Limpa a tela do terminal. Dependendo do sistema operacional, utiliza o comando apropriado.
     * No Windows, o comando {@code cls} é utilizado, enquanto no Linux/Mac é utilizado o comando {@code clear}.
     *
     * Se ocorrer algum erro ao limpar a tela, uma mensagem de erro será exibida no terminal.
     */
    public void clear() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Erro ao limpar a tela: " + e.getMessage());
        }
    }

    /**
     * Aguarda por 2 segundos (2000 milissegundos). Este método pode ser usado para criar pausas entre exibições de telas.
     */
    public void await() {
        try {
            Thread.sleep(2000); // Pausa por 2 segundos (2000 milissegundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
