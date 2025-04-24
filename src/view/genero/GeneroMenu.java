package view.genero;

import view.commons.IScreen;
import view.commons.Screen;

import java.util.Scanner;

/**
 * Classe que representa o menu de opções para o gerenciamento de gêneros no sistema.
 * <p>
 * A classe oferece opções ao usuário para adicionar um novo gênero, listar os gêneros existentes ou voltar para o menu anterior.
 * Ela herda de {@link view.commons.Screen} e, através do método {@link #draw(Scanner)}, exibe as opções de interação no terminal.
 * </p>
 */
public class GeneroMenu extends Screen {

    /**
     * Exibe o menu de opções para o gerenciamento de gêneros. O usuário pode escolher entre:
     * <ul>
     *     <li>Adicionar Gênero</li>
     *     <li>Listar Gêneros</li>
     *     <li>Voltar</li>
     * </ul>
     * O processo de interação continua até o usuário escolher a opção "Voltar".
     *
     * @param terminal o objeto {@link Scanner} utilizado para ler as entradas do usuário
     */
    @Override
    public void draw(Scanner terminal) {
        String opcao;
        do {
            // Exibe as opções de menu
            System.out.println("\n=== Gênero ===");
            System.out.println("1. Adicionar Gênero");
            System.out.println("2. Listar Gêneros");
            System.out.println("3. Voltar");
            System.out.print("Escolha uma opção: ");

            // Lê a opção selecionada pelo usuário
            opcao = terminal.nextLine();
            switch (opcao) {
                case "1":
                    // Chama o formulário para adicionar um novo gênero
                    new CreateGeneroForm().draw(terminal);
                    break;
                case "2":
                    // Chama o formulário para listar os gêneros existentes
                    new ListGenero().draw(terminal);
                    break;
                case "3":
                    // Finaliza a interação (volta para o menu anterior)
                    return;
                default:
                    // Mensagem para opções inválidas
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!opcao.equals("3"));  // Continua até o usuário escolher "Voltar"
    }
}
