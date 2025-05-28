package view.filme;

import controller.action.ActionResult;
import controller.action.filme.UpdateFilmeValidation;
import controller.action.filme.UpdateFilmeAction;
import model.Filme;
import view.commons.Screen;
import view.commons.ViewCommons;

import java.util.Scanner;

/**
 * Classe responsável por exibir o formulário de edição de um filme. Permite ao usuário alterar informações relacionadas
 * ao filme, como seu status de "já assistido".
 * <p>
 * A classe herda de {@link view.commons.Screen} e permite a modificação do estado do filme. A edição é feita por meio
 * de um formulário interativo que valida e aplica as mudanças no objeto filme. O processo de atualização é gerido
 * pela classe {@link controller.action.filme.UpdateFilmeAction}, que executa a lógica de validação e atualização.
 * </p>
 */
public class UpdateFilmeForm extends Screen {
    private Filme filme;

    /**
     * Construtor da classe {@link UpdateFilmeForm} que recebe o objeto {@link Filme} a ser editado.
     *
     * @param filme o filme a ser editado
     */
    public UpdateFilmeForm(Filme filme) {
        this.filme = filme;
    }

    /**
     * Exibe o formulário de edição para o filme. O usuário pode alterar o status de "já assistido" do filme.
     * O processo de validação e atualização é realizado por meio de {@link UpdateFilmeAction}.
     * <p>
     * O método garante que a atualização do filme só será realizada se a validação for bem-sucedida.
     * </p>
     *
     * @param terminal o objeto {@link Scanner} utilizado para ler as entradas do usuário
     */
    @Override
    public void draw(Scanner terminal) {
        ActionResult result;
        System.out.println("\n=== Editar Filme ===");
        do {
            // Solicita ao usuário se ele já assistiu o filme
            boolean consumido = ViewCommons.inputBoolean(terminal, "Já Assisti?");

            // Atualiza o status do filme com base na entrada do usuário
            filme.setConsumido(consumido);

            // Cria a ação de atualização do filme e executa
            UpdateFilmeAction updateFilme = new UpdateFilmeAction(new UpdateFilmeValidation());
            result = updateFilme.execute(filme);

            // Exibe o resultado da atualização
            System.out.println("\n=== Resultado: " + result.getMessage());

        } while (!result.isSuccess());  // Continua tentando até a atualização ser bem-sucedida
    }
}
