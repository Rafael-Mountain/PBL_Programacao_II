package view.avaliacao;

import controller.action.ActionResult;
import controller.action.avaliacao.CreateAvaliacaoAction;
import controller.action.avaliacao.CreateAvaliacaoValidation;
import model.Avaliacao;
import model.commons.IAvaliavel;
import view.commons.Screen;
import view.commons.ViewCommons;

import java.time.LocalDate;
import java.util.Scanner;

public class CreateAvaliacaoForm extends Screen {
    IAvaliavel objAvaliavel;

    @Override
    public void draw(Scanner terminal) {
        ActionResult result;
        Avaliacao avaliacao;

        do {
            System.out.println("\n=== Avaliar Midia ===\n");
            System.out.print("Comentário: ");
            String review = terminal.nextLine();

            int pontuacao = ViewCommons.inputIntWRange(terminal, "Pontuação (1-5): ", 1, 5);
            LocalDate data_consumo = ViewCommons.inputData(terminal, "Data que assistiu/leu");


            avaliacao = new Avaliacao(null, pontuacao, review, data_consumo);

            CreateAvaliacaoAction createAvaliacao = new CreateAvaliacaoAction(new CreateAvaliacaoValidation());

            createAvaliacao.SetSuperModel(objAvaliavel);
            result = createAvaliacao.execute(avaliacao);
            System.out.println("\n=== Resultado: " + result.getMessage());

        } while (!result.isSuccess());
    }

    public void setObjAvaliavel(IAvaliavel objAvaliavel) {
        this.objAvaliavel = objAvaliavel;
    }

    public IAvaliavel getObjAvaliavel() {
        return objAvaliavel;
    }
}