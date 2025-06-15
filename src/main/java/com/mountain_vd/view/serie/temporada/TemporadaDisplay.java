package com.mountain_vd.view.serie.temporada;

import com.mountain_vd.model.Serie;
import com.mountain_vd.model.Temporada;
import com.mountain_vd.view.avaliacao.AvaliacaoDisplay;
import com.mountain_vd.view.avaliacao.CreateAvaliacaoForm;

import java.util.Scanner;

public class TemporadaDisplay {
    Temporada temporada;

    public TemporadaDisplay(Temporada temporada) {
        this.temporada = temporada;
    }

    public void draw() {
        System.out.println("Ano da temporada: " + temporada.getAno().getYear());
        System.out.println("--------------------");
        System.out.println("Id: " + temporada.getId());
        System.out.println("Pontuação: " + temporada.getPontuacao());
        System.out.println("Quantidade episodios: " + temporada.getqEpisodios());
    }

    public void draw(Serie serie, Scanner terminal) {
        String input;
        do {
            System.out.println("\n=== Detalhes da Temporada ===");
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


            System.out.print("Escolha uma opção: ");
            input = terminal.nextLine();

            switch (input) {
                case "1":{
                    if (!serie.isConsumido()) {
                        System.out.println("Você precisa assistir a serie antes de avaliá-la.");

                    } else {
                        CreateAvaliacaoForm createAvaliacaoForm = new CreateAvaliacaoForm();
                        createAvaliacaoForm.setObjAvaliavel(temporada);
                        createAvaliacaoForm.draw(terminal);
                        return;
                    }
                }
                break;
                case "2":
                    return;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (!input.equals("2"));
    }
}
