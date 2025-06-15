package com.mountain_vd.view.serie;

import com.mountain_vd.controller.dataBase.SerieRepository;
import com.mountain_vd.model.Serie;
import com.mountain_vd.view.commons.Screen;
import com.mountain_vd.view.commons.ViewCommons;
import com.mountain_vd.view.serie.temporada.CreateTemporadaForm;
import com.mountain_vd.view.serie.temporada.TemporadaDisplay;


import java.util.Scanner;

public class SerieDisplay extends Screen {
    private final Serie serie;
    
    
    public SerieDisplay(Serie serie) {
        this.serie = serie;
    }

    @Override
    public void draw(Scanner terminal) {
        System.out.println("\n=== Detalhes da Serie ===");
        System.out.println("Título " + serie.getTitulo());
        System.out.println("Título Original: " + serie.getTituloOriginal());
        System.out.println("Pontuação: " + serie.getPontuacao());
        System.out.println("Ano de Lançamento: " + serie.getDataLancamento().getYear());
        System.out.println("Ano de Encerramento: " + serie.getDataFim());
        System.out.println("Gêneros: " + String.join(", ", serie.getGeneros().stream().map(g -> g.getNome()).toList()));
        System.out.println("Elenco: " + String.join(", ", serie.getElenco()));
        System.out.println("Local Disponível: " + serie.getLocalDisponivel());
        System.out.println("Já assisti: " + (serie.isConsumido() ? "Sim" : "Não"));


        System.out.println("\n=== Temporadas ===");
        if (serie.getTemporadas().isEmpty()) {
            System.out.println("Nenhuma Temporada cadastrada.");
        } else {
            System.out.println("======================================");
            serie.getTemporadas().forEach(temporada -> {
                new TemporadaDisplay(temporada).draw();
                System.out.println("======================================");
            });
        }



        System.out.println("\n=== Ações ===");
        System.out.println("1. Editar");
        System.out.println("2. Adicionar Temporada");
        System.out.println("3. Selecionar Temporada");
        System.out.println("4. Voltar");

        String input;
        do {
            System.out.print("Escolha uma opção: ");
            input = terminal.nextLine();

            switch (input) {
                case "1":
                    new UpdateSerieForm(serie).draw(terminal);
                    new SerieDisplay(SerieRepository.getInstance().getItemById(serie.getId())).draw(terminal);
                    return;

                case "2":
                    new CreateTemporadaForm(serie).draw(terminal);
                    new SerieDisplay(SerieRepository.getInstance().getItemById(serie.getId())).draw(terminal);
                    return;

                case "3":
                    if (serie.getTemporadas().isEmpty()) {
                        System.out.println("Nenhuma temporada cadastrada.");
                        break;
                    }

                    do {
                        int id = ViewCommons.inputInt(terminal, "Digite o id da temporada: ");
                        if (serie.getTemporadas().stream().anyMatch(temporada -> temporada.getId() == id)) {
                            new TemporadaDisplay(serie.getTemporadaById(id)).draw(serie,terminal);
                            new SerieDisplay(SerieRepository.getInstance().getItemById(serie.getId())).draw(terminal);
                        } else {
                            System.out.println("Temporada não encontrada.");
                            break;
                        }
                    } while (true);
                    break;

                case "4":
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (!input.equals("3"));


    }
}
