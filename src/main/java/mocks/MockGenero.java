package mocks;

import controller.action.genero.CreateGeneroAction;
import controller.action.genero.CreateGeneroValidation;
import model.Genero;

public class MockGenero {
    public static void rodar() {
        String[] nomesGeneros = {
                "Ficção Científica",
                "Fantasia",
                "Romance",
                "Mistério",
                "Biografia",
                "História",
                "Tecnologia",
                "Autoajuda",
                "Aventura",
                "Terror"
        };

        CreateGeneroAction action = new CreateGeneroAction(new CreateGeneroValidation());

        for (String nome : nomesGeneros) {
            action.execute(new Genero(nome));
        }
    }
}
