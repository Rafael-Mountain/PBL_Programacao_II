package view.serie;

import controller.dataBase.FilmeRepository;
import controller.dataBase.SerieRepository;
import model.Filme;
import model.Serie;
import view.avaliacao.AvaliacaoDisplay;
import view.avaliacao.CreateAvaliacaoForm;
import view.commons.Screen;
import view.commons.ViewCommons;
import view.serie.temporada.CreateTemporadaForm;
import view.serie.temporada.TemporadaDisplay;


import java.util.Scanner;

public class SerieDisplay extends Screen {
    private final Serie serie;
    
    
    public SerieDisplay(Serie serie) {
        this.serie = serie;
    }

    @Override
    public void draw(Scanner terminal) {
        System.out.println("\n=== Detalhes do Filme ===");
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
                    // TODO: Fazer que nem gente.
                    serie.setConsumido(true);
                    break;

                case "2":
                    //TODO : nâo funcionou ver o pq
                    new CreateTemporadaForm(serie).draw(terminal);
                    new SerieDisplay(SerieRepository.getInstance().getItemById(serie.getId())).draw(terminal);
                    return;

                case "3":
                    if (serie.getTemporadas().isEmpty()) {
                        System.out.println("Nenhuma temporada cadastrada.");
                        break;
                    }
                    boolean v = true;
                    do {
                        int id = ViewCommons.inputInt(terminal, "Digite o id da temporada: ");
                        if (serie.getTemporadas().stream().anyMatch(temporada -> temporada.getId() == id)) {
                            new TemporadaDisplay(serie.getTemporadaById(id)).draw(serie,terminal);
                            v = false;
                        } else {
                            System.out.println("Temporada não encontrada.");
                        }
                    } while (v);
                    break;

                case "4":
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (!input.equals("3"));


    }
}
