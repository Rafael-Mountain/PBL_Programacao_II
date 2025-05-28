package view.genero;

import controller.dataBase.GeneroRepository;
import model.Genero;
import view.commons.IScreen;

import java.util.Scanner;

/**
 * Classe responsável por listar todos os gêneros cadastrados no sistema.
 * <p>
 * Ela implementa a interface {@link view.commons.IScreen}, o que significa que ela exibe informações de uma tela (neste caso, a lista de gêneros).
 * O método {@link #draw(Scanner)} é responsável por buscar os gêneros no repositório e exibi-los no terminal.
 * </p>
 */
public class ListGenero implements IScreen {

    /**
     * Exibe todos os gêneros cadastrados no sistema no terminal.
     * Para cada gênero, é mostrado seu ID e nome.
     * Após exibir a lista, o usuário é instruído a pressionar Enter para continuar.
     *
     * @param terminal o objeto {@link Scanner} utilizado para ler as entradas do usuário
     */
    @Override
    public void draw(Scanner terminal) {
        // Obtém a instância do repositório de gêneros
        GeneroRepository generoRepository = GeneroRepository.getInstance();

        // Exibe o título da tela
        System.out.println("\n=== Listar Gêneros ===");

        // Percorre todos os gêneros armazenados no repositório e exibe seu ID e nome
        for (Genero g : generoRepository.getItems()) {
            System.out.println(g.getId() + " - " + g.getNome());
        }

        // Solicita que o usuário pressione Enter para continuar
        System.out.print("Pressione Enter para continuar...");
        terminal.nextLine();  // Aguarda a entrada do usuário
    }
}
