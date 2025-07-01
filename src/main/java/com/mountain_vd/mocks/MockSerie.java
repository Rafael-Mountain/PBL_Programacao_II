package com.mountain_vd.mocks;

import com.mountain_vd.controller.action.serie.CreateSerieAction;
import com.mountain_vd.controller.action.serie.CreateSerieValidation;
import com.mountain_vd.controller.action.serie.UpdateSerieAction;
import com.mountain_vd.controller.action.serie.UpdateSerieValidation;
import com.mountain_vd.controller.dataBase.GeneroRepository;
import com.mountain_vd.model.Genero;
import com.mountain_vd.model.Serie;
import com.mountain_vd.model.Temporada;

import java.time.LocalDate;
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

        Serie breakingBad = new Serie("Breaking Bad", LocalDate.of(2008, 1, 20), true, generos.subList(0, 2),
                "Breaking Bad - Química do mal", "AMC", Arrays.asList("Bryan Cranston", "Aaron Paul"), LocalDate.of(2013, 9, 29));
        Serie strangerThings = new Serie("Stranger Things", LocalDate.of(2016, 7, 15), false, generos.subList(1, 3),
                "Stranger Things - Mundo invertido", "Netflix", Arrays.asList("Millie Bobby Brown", "David Harbour"), LocalDate.of(2025, 1, 1));
        Serie theOffice = new Serie("The Office", LocalDate.of(2005, 3, 24), true, generos.subList(0, 2),
                "The Office - Escritório americano", "NBC", Arrays.asList("Steve Carell", "John Krasinski"), LocalDate.of(2013, 5, 16));
        Serie gameOfThrones = new Serie("Game of Thrones", LocalDate.of(2011, 4, 17), true, generos.subList(0, 2),
                "Game of Thrones - A Guerra dos Tronos", "HBO", Arrays.asList("Emilia Clarke", "Kit Harington"), LocalDate.of(2019, 5, 19));
        Serie theWitcher = new Serie("The Witcher", LocalDate.of(2019, 12, 20), false, generos.subList(0, 2),
                "The Witcher - Geralt de Rívia", "Netflix", Arrays.asList("Henry Cavill", "Freya Allan"), LocalDate.of(2025, 1, 1));
        Serie dark = new Serie("Dark", LocalDate.of(2017, 12, 1), true, generos.subList(0, 2),
                "Dark - O ciclo eterno", "Netflix", Arrays.asList("Louis Hofmann", "Maja Schöne"), LocalDate.of(2020, 6, 27));
        Serie sherlock = new Serie("Sherlock", LocalDate.of(2010, 7, 25), true, generos.subList(0, 2),
                "Sherlock Holmes - BBC", "BBC iPlayer", Arrays.asList("Benedict Cumberbatch", "Martin Freeman"), LocalDate.of(2017, 1, 1));
        Serie chernobyl = new Serie("Chernobyl", LocalDate.of(2019, 5, 6), true, generos.subList(1, 3),
                "Chernobyl - HBO Minissérie", "HBO Max", Arrays.asList("Jared Harris", "Stellan Skarsgård"), LocalDate.of(2019, 5, 6));

        List<Serie> series = Arrays.asList(breakingBad, strangerThings, theOffice, gameOfThrones, theWitcher, dark, sherlock, chernobyl);

        for (Serie serie : series) {
            createSerieAction.execute(serie);
        }

        breakingBad.setTemporadas(Arrays.asList(
                new Temporada(7, LocalDate.of(2008, 1, 20)),
                new Temporada(13, LocalDate.of(2009, 3, 8)),
                new Temporada(13, LocalDate.of(2010, 3, 21)),
                new Temporada(13, LocalDate.of(2011, 7, 17)),
                new Temporada(16, LocalDate.of(2012, 7, 15))
        ));

        strangerThings.setTemporadas(Arrays.asList(
                new Temporada(8, LocalDate.of(2016, 7, 15)),
                new Temporada(9, LocalDate.of(2017, 10, 27)),
                new Temporada(8, LocalDate.of(2019, 7, 4)),
                new Temporada(9, LocalDate.of(2022, 5, 27))
        ));

        theOffice.setTemporadas(Arrays.asList(
                new Temporada(6, LocalDate.of(2005, 3, 24)),
                new Temporada(22, LocalDate.of(2006, 9, 21)),
                new Temporada(23, LocalDate.of(2007, 9, 27)),
                new Temporada(14, LocalDate.of(2008, 9, 25)),
                new Temporada(28, LocalDate.of(2009, 9, 17)),
                new Temporada(26, LocalDate.of(2010, 9, 23)),
                new Temporada(24, LocalDate.of(2011, 9, 22)),
                new Temporada(24, LocalDate.of(2012, 9, 20)),
                new Temporada(23, LocalDate.of(2013, 1, 10))
        ));

        gameOfThrones.setTemporadas(Arrays.asList(
                new Temporada(10, LocalDate.of(2011, 4, 17)),
                new Temporada(10, LocalDate.of(2012, 4, 1)),
                new Temporada(10, LocalDate.of(2013, 3, 31)),
                new Temporada(10, LocalDate.of(2014, 4, 6)),
                new Temporada(10, LocalDate.of(2015, 4, 12)),
                new Temporada(10, LocalDate.of(2016, 4, 24)),
                new Temporada(7, LocalDate.of(2017, 7, 16)),
                new Temporada(6, LocalDate.of(2019, 4, 14))
        ));

        theWitcher.setTemporadas(Arrays.asList(
                new Temporada(8, LocalDate.of(2019, 12, 20)),
                new Temporada(8, LocalDate.of(2021, 12, 17)),
                new Temporada(8, LocalDate.of(2023, 6, 29))
        ));

        dark.setTemporadas(Arrays.asList(
                new Temporada(10, LocalDate.of(2017, 12, 1)),
                new Temporada(8, LocalDate.of(2019, 6, 21)),
                new Temporada(8, LocalDate.of(2020, 6, 27))
        ));

        sherlock.setTemporadas(Arrays.asList(
                new Temporada(3, LocalDate.of(2010, 7, 25)),
                new Temporada(3, LocalDate.of(2012, 1, 1)),
                new Temporada(3, LocalDate.of(2014, 1, 1)),
                new Temporada(4, LocalDate.of(2017, 1, 1))
        ));

        chernobyl.setTemporadas(Arrays.asList(
                new Temporada(5, LocalDate.of(2019, 5, 6))
        ));

        for (Serie serie : series) {
            updateSerieAction.execute(serie);
        }
    }
}
