package com.mountain_vd.mocks;

import com.mountain_vd.controller.action.genero.CreateGeneroAction;
import com.mountain_vd.controller.action.genero.CreateGeneroValidation;
import com.mountain_vd.model.Genero;

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

    public static void main(String[] args) {
        rodar();
    }
}
