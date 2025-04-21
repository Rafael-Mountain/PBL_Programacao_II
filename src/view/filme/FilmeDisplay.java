package view.filme;

import controller.dataBase.FilmeRepository;
import model.Filme;
import view.avaliacao.AvaliacaoDisplay;
import view.avaliacao.CreateAvaliacaoForm;
import view.commons.Screen;

import java.util.Scanner;

public class FilmeDisplay extends Screen {
    private final Filme filme;

    public FilmeDisplay(Filme filme) {
        this.filme = filme;
    }

    @Override
    public void draw(Scanner terminal) {
        System.out.println("\n=== Detalhes do Filme ===");
        System.out.println("Título " + filme.getTitulo());
        System.out.println("Título Original: " + filme.getTituloOriginal());
        System.out.println("Pontuação: " + filme.getPontuacao());
        System.out.println("Ano de Lançamento: " + filme.getDataLancamento().getYear());
        System.out.println("Gêneros: " + String.join(", ", filme.getGeneros().stream().map(g -> g.getNome()).toList()));
        System.out.println("Diretor: " + filme.getDirecao());
        System.out.println("Duração: " + filme.getDuracao() + " minutos");
        System.out.println("Elenco: " + String.join(", ", filme.getElenco()));
        System.out.println("Local Disponível: " + filme.getLocalDisponivel());
        System.out.println("Já assisti: " + (filme.isConsumido() ? "Sim" : "Não"));
        System.out.println("Roteiro: " + filme.getRoteiro());

        System.out.println("\n=== Avaliações ===");
        if (filme.getAvaliacoes().isEmpty()) {
            System.out.println("Nenhuma avaliação ainda.");
        } else {
            System.out.println("======================================");
            filme.getAvaliacoes().forEach(avaliacao -> {
                new AvaliacaoDisplay(avaliacao).draw();
                System.out.println("======================================");
            });
        }


        System.out.println("\n=== Ações ===");
        System.out.println("1. Editar");
        System.out.println("2. Avaliar");
        System.out.println("3. Voltar");

        String input;
        do {
            System.out.print("Escolha uma opção: ");
            input = terminal.nextLine();
            FilmeRepository filmeRepository = FilmeRepository.getInstance();

            switch (input) {
                case "1":
                    // TODO: Fazer que nem gente.
                    filme.setConsumido(true);
                    break;
                case "2":
                    // Avaliar filme
                    if (!filme.isConsumido()) {
                        System.out.println("Você precisa assistir o filme antes de avaliá-lo.");
                    } else {
                        CreateAvaliacaoForm createAvaliacaoForm = new CreateAvaliacaoForm();
                        createAvaliacaoForm.setObjAvaliavel(filme);
                        createAvaliacaoForm.draw(terminal);
                        Filme filmeAvaliado = filmeRepository.getItemById(filme.getId());
                        new FilmeDisplay(filmeAvaliado).draw(terminal);
                        return;
                    }
                case "3":
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (!input.equals("3"));


    }
}
