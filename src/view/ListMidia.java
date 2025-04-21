package view;

import controller.search.SearchResults;
import model.Media;
import view.commons.IScreen;

import java.util.List;
import java.util.Scanner;

public class ListMidia implements IScreen {
    List<Media> medias;
    boolean reset = true;
    String message;

    public ListMidia (SearchResults searchResults){
        medias = searchResults.getMediaList();
        message = searchResults.getMessage();
    }

    @Override
    public void draw(Scanner terminal) {
        if (medias == null) {
            System.out.println("Error: List of Medias was not set");
            return;
        }
        String input;
        do {
            if (reset) {
                if (medias.isEmpty()) {
                    System.out.println("\n=== Lista de Mídias ===");
                    System.out.println("Nenhuma mídia encontrada.");
                    reset = false;
                } else {
                    System.out.println("\n === Lista de Mídias ===");
                    new ListGridMedia().draw(medias);
                    reset = false;
                }
            }

            System.out.println("\n 1 - Buscar Mídia");
            System.out.println(" 2 - Filtrar Mídia");
            System.out.println(" 3 - Selecionar Mídia");
            System.out.println(" 4 - Voltar");
            System.out.print("Escolha uma opção: ");
            input = terminal.nextLine();
            switch (input) {
                case "1":
//                    SearchResults searchResults= new SearchView().draw(terminal);
                    break;
                case "2":
                    // Implementar filtro
                    break;
                case "3":
                    // Implementar seleção de mídia
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!input.equals("4"));
    }

    public void setMedias(SearchResults medias) {
        this.medias = medias.getMediaList();
        this.message = medias.getMessage();
    }
}


