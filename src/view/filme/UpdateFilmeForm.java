package view.filme;

import controller.action.ActionResult;
import controller.action.filme.UpdateFilmeValidation;
import controller.action.filme.UpdateFilmeAction;
import model.Filme;
import view.commons.Screen;
import view.commons.ViewCommons;

import java.util.Scanner;

public class UpdateFilmeForm extends Screen {
    Filme filme;

    public UpdateFilmeForm(Filme filme) {
        this.filme = filme;
    }

    @Override
    public void draw(Scanner terminal) {
        ActionResult result;
        System.out.println("\n=== Editar Filme ===");
        do {
            boolean consumido = ViewCommons.inputBoolean(terminal, "Já Assisti?");

            filme.setConsumido(consumido);
            UpdateFilmeAction updateFilme = new UpdateFilmeAction(new UpdateFilmeValidation());
            result = updateFilme.execute(filme);

            System.out.println("\n=== Resultado: " + result.getMessage());

        } while (!result.isSuccess());
    }
}
