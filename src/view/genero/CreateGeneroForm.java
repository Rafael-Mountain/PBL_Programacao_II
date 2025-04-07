package view.genero;

import controller.action.ActionResult;
import controller.action.genero.CreateGeneroAction;
import controller.action.genero.CreateGeneroValidation;
import model.Genero;
import view.commons.Screen;

import java.util.Scanner;

public class CreateGeneroForm extends Screen {

    @Override
    public void draw(Scanner terminal) {
        ActionResult result;
        do {
            System.out.println("\n=== Criar Genero ===");
            System.out.print("Digite o nome do genero(ou 'sair' para finalizar): ");
            String nomeGenero = terminal.nextLine();

            if (nomeGenero.equalsIgnoreCase("sair")) {
                break;
            }

            Genero genero = new Genero(nomeGenero);
            CreateGeneroAction action = new CreateGeneroAction(new CreateGeneroValidation());
            result = action.execute(genero);

            System.out.println(result.getMessage());

        } while (!result.isSuccess());
    }
}
