package view.serie.temporada;

import controller.dataBase.SerieRepository;
import model.Serie;
import model.Temporada;
import view.avaliacao.AvaliacaoDisplay;
import view.avaliacao.CreateAvaliacaoForm;
import view.serie.SerieDisplay;

import java.util.Scanner;

public class TemporadaDisplay {
    Temporada temporada;

    public TemporadaDisplay(Temporada temporada) {
        this.temporada = temporada;
    }

    public void draw() {
        System.out.println("Ano da temporada: " + temporada.getAno());
        System.out.println("--------------------");
        System.out.println("Id: " + temporada.getId());
        System.out.println("Pontuação: " + temporada.getPontuacao());
        System.out.println("Quantidade episodios: " + temporada.getqEpisodios());
    }

    public void draw(Serie serie, Scanner terminal) {
        draw();
        System.out.println("\n=== Avaliações ===");
        if (temporada.getAvaliacoes().isEmpty()) {
            System.out.println("Nenhuma avaliação ainda.");
        } else {
            System.out.println("======================================");
            temporada.getAvaliacoes().forEach(avaliacao -> {
                new AvaliacaoDisplay(avaliacao).draw();
                System.out.println("======================================");
            });
        }
        System.out.println("\n=== Ações ===");
        System.out.println("1. Avaliar");
        System.out.println("2. Voltar");


        String input;
        do {
            System.out.print("Escolha uma opção: ");
            input = terminal.nextLine();
            SerieRepository serieRepository = SerieRepository.getInstance();

            switch (input) {
                case "1":
                    // Avaliar serie
                    if (!serie.isConsumido()) {
                        System.out.println("Você precisa assistir a serie antes de avaliá-la.");
                    } else {
                        CreateAvaliacaoForm createAvaliacaoForm = new CreateAvaliacaoForm();
                        createAvaliacaoForm.setObjAvaliavel(temporada);
                        createAvaliacaoForm.draw(terminal);
                        new SerieDisplay(serieRepository.getItemById(serie.getId())).draw(terminal);
                        return;
                    }
                case "2":
                    return;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (!input.equals("2"));
    }
}
