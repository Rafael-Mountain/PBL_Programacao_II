package com.mountain_vd.view.avaliacao;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.avaliacao.CreateAvaliacaoAction;
import com.mountain_vd.controller.action.avaliacao.CreateAvaliacaoValidation;
import com.mountain_vd.model.Avaliacao;
import com.mountain_vd.model.commons.IAvaliavel;
import com.mountain_vd.view.commons.Screen;
import com.mountain_vd.view.commons.ViewCommons;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * A classe {@code CreateAvaliacaoForm} é responsável por exibir um formulário de criação de avaliação para o usuário.
 * Ela coleta as informações necessárias para criar uma avaliação de uma mídia, incluindo o comentário, a pontuação e a data de consumo.
 *
 * Esta classe interage com a lógica de negócios para validar e salvar a avaliação por meio da ação {@link CreateAvaliacaoAction}.
 * O formulário repete o processo até que uma avaliação válida seja fornecida.
 *
 * <p>Exemplo de uso:</p>
 * <pre>
 * CreateAvaliacaoForm form = new CreateAvaliacaoForm();
 * form.setObjAvaliavel(someAvaliavelObject);
 * form.draw(new Scanner(System.in));
 * </pre>
 *
 * @author
 */
public class CreateAvaliacaoForm extends Screen {

    /** O objeto que pode ser avaliado. */
    private IAvaliavel objAvaliavel;

    /**
     * Exibe o formulário de criação de avaliação e coleta as entradas do usuário.
     * O formulário solicita um comentário, uma pontuação (entre 1 e 5), e a data de consumo da mídia.
     * O processo continua até que a avaliação seja validada com sucesso.
     *
     * @param terminal O scanner usado para ler as entradas do usuário.
     */
    @Override
    public void draw(Scanner terminal) {
        ActionResult result;
        Avaliacao avaliacao;

        do {
            System.out.println("\n=== Avaliar Mídia ===\n");
            System.out.print("Comentário: ");
            String review = terminal.nextLine();

            int pontuacao = ViewCommons.inputIntWRange(terminal, "Pontuação (1-5): ", 1, 5);
            LocalDate data_consumo = ViewCommons.inputData(terminal, "Data que assistiu/leu");

            avaliacao = new Avaliacao(null, pontuacao, review, data_consumo);

            CreateAvaliacaoAction createAvaliacao = new CreateAvaliacaoAction(new CreateAvaliacaoValidation());

            createAvaliacao.setSuperModel(objAvaliavel);
            result = createAvaliacao.execute(avaliacao);
            System.out.println("\n=== Resultado: " + result.getMessage());

        } while (!result.isSuccess());
    }

    /**
     * Define o objeto avaliável, ou seja, o objeto ao qual a avaliação será associada.
     *
     * @param objAvaliavel O objeto que pode ser avaliado.
     */
    public void setObjAvaliavel(IAvaliavel objAvaliavel) {
        this.objAvaliavel = objAvaliavel;
    }

    /**
     * Retorna o objeto avaliável.
     *
     * @return O objeto que pode ser avaliado.
     */
    public IAvaliavel getObjAvaliavel() {
        return objAvaliavel;
    }
}
