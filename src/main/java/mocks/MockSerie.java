package mocks;

import controller.action.serie.CreateSerieAction;
import controller.action.serie.CreateSerieValidation;
import controller.action.serie.UpdateSerieAction;
import controller.action.serie.UpdateSerieValidation;
import controller.dataBase.GeneroRepository;
import model.Genero;
import model.Serie;
import model.Temporada;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class MockSerie {
    public static void rodar() {
        List<Genero> generos = GeneroRepository.getInstance().getItems();
        if (generos.isEmpty()) {
            MockGenero.rodar();
            generos = GeneroRepository.getInstance().getItems();
        }

        CreateSerieAction createSerieAction = new CreateSerieAction(new CreateSerieValidation());
        UpdateSerieAction updateSerieAction = new UpdateSerieAction(new UpdateSerieValidation());

        Serie breakingBad = new Serie("Breaking Bad", LocalDateTime.of(2008, 1, 20, 0, 0), true, generos.subList(0, 2),
                "Breaking Bad - Química do mal", "AMC", Arrays.asList("Bryan Cranston", "Aaron Paul"), LocalDateTime.of(2013, 9, 29, 0, 0));
        Serie strangerThings = new Serie("Stranger Things", LocalDateTime.of(2016, 7, 15, 0, 0), false, generos.subList(1, 3),
                "Stranger Things - Mundo invertido", "Netflix", Arrays.asList("Millie Bobby Brown", "David Harbour"), LocalDateTime.of(2025, 1, 1, 0, 0));
        Serie theOffice = new Serie("The Office", LocalDateTime.of(2005, 3, 24, 0, 0), true, generos.subList(0, 2),
                "The Office - Escritório americano", "NBC", Arrays.asList("Steve Carell", "John Krasinski"), LocalDateTime.of(2013, 5, 16, 0, 0));
        Serie gameOfThrones = new Serie("Game of Thrones", LocalDateTime.of(2011, 4, 17, 0, 0), true, generos.subList(0, 2),
                "Game of Thrones - A Guerra dos Tronos", "HBO", Arrays.asList("Emilia Clarke", "Kit Harington"), LocalDateTime.of(2019, 5, 19, 0, 0));
        Serie theWitcher = new Serie("The Witcher", LocalDateTime.of(2019, 12, 20, 0, 0), false, generos.subList(0, 2),
                "The Witcher - Geralt de Rívia", "Netflix", Arrays.asList("Henry Cavill", "Freya Allan"), LocalDateTime.of(2025, 1, 1, 0, 0));
        Serie dark = new Serie("Dark", LocalDateTime.of(2017, 12, 1, 0, 0), true, generos.subList(0, 2),
                "Dark - O ciclo eterno", "Netflix", Arrays.asList("Louis Hofmann", "Maja Schöne"), LocalDateTime.of(2020, 6, 27, 0, 0));
        Serie sherlock = new Serie("Sherlock", LocalDateTime.of(2010, 7, 25, 0, 0), true, generos.subList(0, 2),
                "Sherlock Holmes - BBC", "BBC iPlayer", Arrays.asList("Benedict Cumberbatch", "Martin Freeman"), LocalDateTime.of(2017, 1, 1, 0, 0));
        Serie chernobyl = new Serie("Chernobyl", LocalDateTime.of(2019, 5, 6, 0, 0), true, generos.subList(1, 3),
                "Chernobyl - HBO Minissérie", "HBO Max", Arrays.asList("Jared Harris", "Stellan Skarsgård"), LocalDateTime.of(2019, 5, 6, 0, 0));

        List<Serie> series = Arrays.asList(breakingBad, strangerThings, theOffice, gameOfThrones, theWitcher, dark, sherlock, chernobyl);

        for (Serie serie : series) {
            createSerieAction.execute(serie); // Criar série sem temporadas
        }

        // Agora adiciona as temporadas
        breakingBad.setTemporadas(Arrays.asList(
                new Temporada(7, LocalDateTime.of(2008, 1, 20, 0, 0)),
                new Temporada(13, LocalDateTime.of(2009, 3, 8, 0, 0)),
                new Temporada(13, LocalDateTime.of(2010, 3, 21, 0, 0)),
                new Temporada(13, LocalDateTime.of(2011, 7, 17, 0, 0)),
                new Temporada(16, LocalDateTime.of(2012, 7, 15, 0, 0))
        ));

        strangerThings.setTemporadas(Arrays.asList(
                new Temporada(8, LocalDateTime.of(2016, 7, 15, 0, 0)),
                new Temporada(9, LocalDateTime.of(2017, 10, 27, 0, 0)),
                new Temporada(8, LocalDateTime.of(2019, 7, 4, 0, 0)),
                new Temporada(9, LocalDateTime.of(2022, 5, 27, 0, 0))
        ));

        theOffice.setTemporadas(Arrays.asList(
                new Temporada(6, LocalDateTime.of(2005, 3, 24, 0, 0)),
                new Temporada(22, LocalDateTime.of(2006, 9, 21, 0, 0)),
                new Temporada(23, LocalDateTime.of(2007, 9, 27, 0, 0)),
                new Temporada(14, LocalDateTime.of(2008, 9, 25, 0, 0)),
                new Temporada(28, LocalDateTime.of(2009, 9, 17, 0, 0)),
                new Temporada(26, LocalDateTime.of(2010, 9, 23, 0, 0)),
                new Temporada(24, LocalDateTime.of(2011, 9, 22, 0, 0)),
                new Temporada(24, LocalDateTime.of(2012, 9, 20, 0, 0)),
                new Temporada(23, LocalDateTime.of(2013, 1, 10, 0, 0))
        ));

        gameOfThrones.setTemporadas(Arrays.asList(
                new Temporada(10, LocalDateTime.of(2011, 4, 17, 0, 0)),
                new Temporada(10, LocalDateTime.of(2012, 4, 1, 0, 0)),
                new Temporada(10, LocalDateTime.of(2013, 3, 31, 0, 0)),
                new Temporada(10, LocalDateTime.of(2014, 4, 6, 0, 0)),
                new Temporada(10, LocalDateTime.of(2015, 4, 12, 0, 0)),
                new Temporada(10, LocalDateTime.of(2016, 4, 24, 0, 0)),
                new Temporada(7, LocalDateTime.of(2017, 7, 16, 0, 0)),
                new Temporada(6, LocalDateTime.of(2019, 4, 14, 0, 0))
        ));

        theWitcher.setTemporadas(Arrays.asList(
                new Temporada(8, LocalDateTime.of(2019, 12, 20, 0, 0)),
                new Temporada(8, LocalDateTime.of(2021, 12, 17, 0, 0)),
                new Temporada(8, LocalDateTime.of(2023, 6, 29, 0, 0))
        ));

        dark.setTemporadas(Arrays.asList(
                new Temporada(10, LocalDateTime.of(2017, 12, 1, 0, 0)),
                new Temporada(8, LocalDateTime.of(2019, 6, 21, 0, 0)),
                new Temporada(8, LocalDateTime.of(2020, 6, 27, 0, 0))
        ));

        sherlock.setTemporadas(Arrays.asList(
                new Temporada(3, LocalDateTime.of(2010, 7, 25, 0, 0)),
                new Temporada(3, LocalDateTime.of(2012, 1, 1, 0, 0)),
                new Temporada(3, LocalDateTime.of(2014, 1, 1, 0, 0)),
                new Temporada(4, LocalDateTime.of(2017, 1, 1, 0, 0))
        ));

        chernobyl.setTemporadas(Arrays.asList(
                new Temporada(5, LocalDateTime.of(2019, 5, 6, 0, 0))
        ));

        // Atualizar as séries com temporadas
        for (Serie serie : series) {
            updateSerieAction.execute(serie);
        }
    }
}
