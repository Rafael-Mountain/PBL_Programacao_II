package com.mountain_vd.view.livro;

import com.mountain_vd.controller.action.ActionResult;
import com.mountain_vd.controller.action.livro.UpdateLivroAction;
import com.mountain_vd.controller.action.livro.UpdateLivroValidation;
import com.mountain_vd.model.Livro;
import com.mountain_vd.view.commons.Screen;
import com.mountain_vd.view.commons.ViewCommons;

import java.util.Scanner;

/**
 * Classe responsável pela exibição do formulário de edição de um livro existente.
 * Permite que o usuário atualize informações como o status de leitura e posse do livro.
 */
public class UpdateLivroForm extends Screen {
    private final Livro livro;

    /**
     * Construtor que inicializa o formulário de edição com o livro que será alterado.
     *
     * @param livro O livro a ser editado.
     */
    public UpdateLivroForm(Livro livro) {
        this.livro = livro;
    }

    /**
     * Exibe o formulário de edição do livro e permite ao usuário atualizar o status de leitura e posse do livro.
     * O formulário continua a ser exibido até que a atualização seja bem-sucedida.
     *
     * @param terminal O scanner utilizado para capturar a entrada do usuário no terminal.
     */
    @Override
    public void draw(Scanner terminal) {
        ActionResult result;
        System.out.println("\n=== Editar Livro ===");

        do {
            // Solicita ao usuário as informações sobre o status de leitura e posse do livro
            boolean consumido = ViewCommons.inputBoolean(terminal, "Já Lido?");
            boolean possui = ViewCommons.inputBoolean(terminal, "\nPossui exemplar?");

            // Atualiza o livro com as novas informações
            livro.setConsumido(consumido);
            livro.setPossui(possui);

            // Executa a ação de atualização do livro
            UpdateLivroAction updateLivro = new UpdateLivroAction(new UpdateLivroValidation());
            result = updateLivro.execute(livro);

            // Exibe o resultado da operação
            System.out.println("\n=== Resultado: " + result.getMessage());

        } while (!result.isSuccess()); // Continua até a operação ser bem-sucedida
    }
}
