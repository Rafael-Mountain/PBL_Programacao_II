package com.mountain_vd.mocks;

import com.mountain_vd.controller.action.filme.CreateFilmeAction;
import com.mountain_vd.controller.action.filme.CreateFilmeValidation;
import com.mountain_vd.controller.dataBase.GeneroRepository;
import com.mountain_vd.model.Filme;
import com.mountain_vd.model.Genero;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MockFilme {
    public static void rodar() {
        List<Genero> g = GeneroRepository.getInstance().getItems();
        if (g.isEmpty()) {
            MockGenero.rodar();
        }
        CreateFilmeAction action = new CreateFilmeAction(new CreateFilmeValidation());

        List<Filme> filmes = Arrays.asList(
                new Filme("Avengers", LocalDate.of(2019, 4, 26), true, g.subList(0, 2), "Avengers Ultimato", "Prime Video",
                        Arrays.asList("Robert Downey Jr.", "Scarlett Johansson"), "Alguém", 120, "Filme muito bom"),

                new Filme("Her", LocalDate.of(2013, 12, 18), false, g.subList(3, 4), "Homem se apaixona por IA", "HBO Max",
                        Arrays.asList("Joaquin Phoenix", "Scarlett Johansson"), "Spike Jonze", 126, "Drama romântico"),

                new Filme("Corra!", LocalDate.of(2017, 2, 24), false, g.subList(4, 6), "Suspense psicológico", "Netflix",
                        Arrays.asList("Daniel Kaluuya", "Allison Williams"), "Jordan Peele", 104, "Terror e crítica social"),

                new Filme("Toy Story", LocalDate.of(1995, 11, 22), false, g.subList(8, 9), "Brinquedos vivos", "Disney+",
                        Arrays.asList("Tom Hanks", "Tim Allen"), "Pixar", 81, "Animação clássica"),

                new Filme("O Jogo da Imitação", LocalDate.of(2014, 11, 28), false, g.subList(5, 7), "Alan Turing", "Globoplay",
                        Arrays.asList("Benedict Cumberbatch", "Keira Knightley"), "Morten Tyldum", 113, "Drama histórico"),

                new Filme("Interestelar", LocalDate.of(2014, 11, 7), false, g.subList(2, 3), "Viagem no tempo e espaço", "Netflix",
                        Arrays.asList("Matthew McConaughey", "Anne Hathaway"), "Christopher Nolan", 169, "Ficção científica épica"),

                new Filme("O Labirinto do Fauno", LocalDate.of(2006, 10, 11), false, g.subList(1, 2), "Fantasia sombria na guerra civil espanhola", "HBO Max",
                        Arrays.asList("Ivana Baquero", "Sergi López"), "Guillermo del Toro", 118, "Conto sombrio e mágico"),

                new Filme("Senna", LocalDate.of(2010, 10, 7), false, g.subList(9, 10), "Documentário sobre Ayrton Senna", "Amazon Prime",
                        Arrays.asList("Ayrton Senna", "Alain Prost"), "Asif Kapadia", 106, "Documentário emocionante sobre um ídolo")
        );

        for (Filme f : filmes) {
            action.execute(f);
        }
    }
}
