package view.livro;

import controller.action.ActionResult;
import controller.action.livro.UpdateLivroAction;
import controller.action.livro.UpdateLivroValidation;
import model.Livro;
import view.commons.Screen;
import view.commons.ViewCommons;

import java.util.Scanner;

public class UpdateLivroForm extends Screen {
    Livro livro;

    public UpdateLivroForm(Livro livro) {
        this.livro = livro;
    }

    @Override
    public void draw(Scanner terminal) {
        ActionResult result;
        System.out.println("\n=== Editar Livro ===");
        do {
            boolean consumido = ViewCommons.inputBoolean(terminal, "Já Lido?");
            boolean possui = ViewCommons.inputBoolean(terminal, "\nPossui exemplar?");

            livro.setConsumido(consumido);
            livro.setPossui(possui);

            UpdateLivroAction updateLivro = new UpdateLivroAction(new UpdateLivroValidation());
            result = updateLivro.execute(livro);

            System.out.println("\n=== Resultado: " + result.getMessage());

        } while (!result.isSuccess());
    }
}
