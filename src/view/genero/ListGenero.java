package view.genero;

import controller.dataBase.GeneroRepository;
import model.Genero;
import view.commons.IScreen;

import java.util.Scanner;

public class ListGenero implements IScreen {

    @Override
    public void draw(Scanner terminal) {
        GeneroRepository generoRepository = GeneroRepository.getInstance();
        System.out.println("\n=== Listar Gêneros ===");
        for ( Genero g : generoRepository.getItems()) {
            System.out.println(g.getId() + " - " + g.getNome());
        }
        System.out.print("Pressione Enter para continuar...");
        terminal.nextLine();
    }

}
