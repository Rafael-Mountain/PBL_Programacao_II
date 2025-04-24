package view.genero;

import controller.action.ActionResult;
import controller.action.genero.CreateGeneroAction;
import controller.action.genero.CreateGeneroValidation;
import model.Genero;
import view.commons.Screen;

import java.util.Scanner;

/**
 * Classe responsável por exibir o formulário de criação de um novo gênero. O usuário pode inserir o nome do
 * gênero e, se a validação for bem-sucedida, o gênero será criado no sistema.
 * <p>
 * A classe herda de {@link view.commons.Screen} e permite a criação de um gênero por meio de um formulário interativo.
 * A validação e a criação do gênero são realizadas pela classe {@link controller.action.genero.CreateGeneroAction}.
 * O processo continua até que um gênero seja criado com sucesso ou o usuário decida sair.
 * </p>
 */
public class CreateGeneroForm extends Screen {

    /**
     * Exibe o formulário para a criação de um gênero. O usuário pode inserir o nome do gênero ou digitar "sair" para
     * finalizar o processo. A validação do gênero é realizada por meio da classe {@link CreateGeneroAction}.
     *
     * @param terminal o objeto {@link Scanner} utilizado para ler as entradas do usuário
     */
    @Override
    public void draw(Scanner terminal) {
        ActionResult result;
        do {
            System.out.println("\n=== Criar Genero ===");
            System.out.print("Digite o nome do genero(ou 'sair' para finalizar): ");
            String nomeGenero = terminal.nextLine();

            // Se o usuário digitar "sair", o processo será interrompido
            if (nomeGenero.equalsIgnoreCase("sair")) {
                break;
            }

            // Cria um objeto Genero e tenta executá-lo através da ação CreateGeneroAction
            Genero genero = new Genero(nomeGenero);
            CreateGeneroAction action = new CreateGeneroAction(new CreateGeneroValidation());
            result = action.execute(genero);

            // Exibe a mensagem de resultado da ação
            System.out.println(result.getMessage());

        } while (!result.isSuccess());  // Continua até o gênero ser criado com sucesso
    }
}
