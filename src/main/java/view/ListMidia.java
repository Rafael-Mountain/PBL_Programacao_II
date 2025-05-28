package view;

import controller.search.SearchResults;
import model.Media;
import view.commons.IScreen;
import view.commons.ViewCommons;

import java.util.List;
import java.util.Scanner;

/**
 * Classe abstrata que representa uma tela de listagem de mídias (como filmes, livros, etc.) no sistema.
 * A classe permite a visualização de uma lista de mídias e oferece opções de interação, como buscar mídias,
 * selecionar uma mídia ou voltar à tela anterior.
 */
public abstract class ListMidia implements IScreen {
    protected List<Media> medias; // Lista de mídias a ser exibida
    private boolean reset = true;  // Flag para resetar a tela
    private String message;        // Mensagem associada à busca

    /**
     * Construtor da classe. Inicializa a lista de mídias com base nos resultados da busca.
     *
     * @param searchResults Resultados da busca contendo a lista de mídias e mensagem.
     */
    public ListMidia(SearchResults searchResults) {
        medias = searchResults.getMediaList();
        message = searchResults.getMessage();
    }

    /**
     * Exibe a lista de mídias na tela com base nas opções disponíveis para o usuário.
     * Permite ao usuário buscar mídias, selecionar uma mídia ou voltar.
     *
     * @param terminal Scanner usado para capturar entradas do usuário.
     */
    @Override
    public void draw(Scanner terminal) {
        if (medias == null) {
            System.out.println("Error: List of Medias was not set");
            return;
        }
        String input;
        do {
            // Exibe a lista de mídias ou uma mensagem se a lista estiver vazia
            if (reset) {
                if (medias.isEmpty()) {
                    System.out.println("\n=== Lista de "+ getNomeMidia() + " ===");
                    System.out.println("Nenhuma mídia encontrada.");
                    reset = false;
                } else {
                    System.out.println("\n=== Lista de "+ getNomeMidia() + " ===");
                    new ListGridMedia().draw(medias);
                    reset = false;
                }
            }
            // Exibe as opções de interação
            System.out.println("\n========= Opções ========");
            System.out.println(" 1 - Buscar " + getNomeMidia());
            System.out.println(" 2 - Selecionar " + getNomeMidia());
            System.out.println(" 3 - Voltar");
            System.out.print("Escolha uma opção: ");
            input = terminal.nextLine();
            switch (input) {
                case "1":
                    goToSearch(terminal); // Chama o método para buscar mídias
                    return;
                case "2":
                    // Permite selecionar uma mídia da lista
                    if (medias.isEmpty()) {
                        break;
                    }
                    int mediaId = ViewCommons.inputIntWRange(terminal, "\nÍndice do(a) " + getNomeMidia() +" selecionado(a): ", 0, medias.size() - 1);
                    goToDisplay(terminal, mediaId); // Chama o método para exibir a mídia selecionada
                    reset = true;
                    break;
                case "3":
                    return; // Volta à tela anterior
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!input.equals("4"));
    }

    /**
     * Atualiza a lista de mídias com os resultados de busca fornecidos.
     *
     * @param medias Resultados da busca contendo a lista de mídias e mensagem.
     */
    public void setMedias(SearchResults medias) {
        this.medias = medias.getMediaList();
        this.message = medias.getMessage();
    }

    /**
     * Método abstrato que define a busca de mídias.
     *
     * @param terminal Scanner usado para capturar entradas do usuário.
     */
    public abstract void goToSearch(Scanner terminal);

    /**
     * Método abstrato que define a exibição de uma mídia selecionada.
     *
     * @param terminal Scanner usado para capturar entradas do usuário.
     * @param id O índice da mídia selecionada na lista.
     */
    public abstract void goToDisplay(Scanner terminal, int id);

    /**
     * Método abstrato que retorna o nome da mídia (como "Filme", "Livro", etc.).
     *
     * @return O nome da mídia.
     */
    public abstract String getNomeMidia();
}
