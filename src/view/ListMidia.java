package view;

import controller.search.SearchResults;
import model.Media;
import view.commons.IScreen;
import view.commons.ViewCommons;

import java.util.List;
import java.util.Scanner;

public abstract class ListMidia implements IScreen {
    protected List<Media> medias;
    private boolean reset = true;
    private String message;


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
                    System.out.println("\n=== Lista de "+ getNomeMidia() + " ===");
                    System.out.println("Nenhuma mídia encontrada.");
                    reset = false;
                } else {
                    System.out.println("\n=== Lista de "+ getNomeMidia() + " ===");
                    new ListGridMedia().draw(medias);
                    reset = false;
                }
            }
            System.out.println("\n========= Opções ========");
            System.out.println(" 1 - Buscar " + getNomeMidia());
            System.out.println(" 2 - Selecionar " + getNomeMidia());
            System.out.println(" 3 - Voltar");
            System.out.print("Escolha uma opção: ");
            input = terminal.nextLine();
            switch (input) {
                case "1":
                    goToSearch(terminal);
                    return;
                case "2":
                    if (medias.isEmpty()) {
                        break;
                    }
                    int mediaId = ViewCommons.inputIntWRange(terminal, "\nÍndice do(a) " + getNomeMidia() +" selecionado(a): ",0, medias.size()-1);
                    goToDisplay(terminal, mediaId);
                    reset = true;
                    break;
                case "3":
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

    public abstract void goToSearch(Scanner terminal);

    public abstract void goToDisplay(Scanner terminal, int id);

    public abstract String getNomeMidia();
}


