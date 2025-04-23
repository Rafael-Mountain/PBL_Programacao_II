package view.livro;

import controller.dataBase.LivroRepository;
import model.Livro;
import view.avaliacao.AvaliacaoDisplay;
import view.avaliacao.CreateAvaliacaoForm;
import view.commons.Screen;

import java.util.Scanner;

public class LivroDisplay extends Screen {
    private final Livro livro;

    public LivroDisplay(Livro livro) {
        this.livro = livro;
    }

    @Override
    public void draw(Scanner terminal) {
        System.out.println("\n=== Detalhes do Livro ===");
        System.out.println("Título: " + livro.getTitulo());
        System.out.println("ISBN: " + livro.getIsbn());
        System.out.println("Autor: " + livro.getAutor());
        System.out.println("Possui Exemplar: " + (livro.isPossui() ? "Sim" : "Não"));
        System.out.println("Pontuação: " + livro.getPontuacao());
        System.out.println("Ano de Lançamento: " + livro.getDataLancamento().getYear());
        System.out.println("Gêneros: " + String.join(", ", livro.getGeneros().stream().map(g -> g.getNome()).toList()));
        System.out.println("Já li: " + (livro.isConsumido() ? "Sim" : "Não"));

        System.out.println("\n=== Avaliações ===");
        if (livro.getAvaliacoes().isEmpty()) {
            System.out.println("Nenhuma avaliação ainda.");
        } else {
            System.out.println("======================================");
            livro.getAvaliacoes().forEach(avaliacao -> {
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
            LivroRepository livroRepository = LivroRepository.getInstance();

            switch (input) {
                case "1":
                    new UpdateLivroForm(livro).draw(terminal);
                    new LivroDisplay(LivroRepository.getInstance().getItemById(livro.getId())).draw(terminal);
                    return;
                case "2":
                    if (!livro.isConsumido()) {
                        System.out.println("Você precisa ler o livro antes de avaliá-lo.");
                    } else {
                        CreateAvaliacaoForm createAvaliacaoForm = new CreateAvaliacaoForm();
                        createAvaliacaoForm.setObjAvaliavel(livro);
                        createAvaliacaoForm.draw(terminal);
                        Livro livroAvaliado = livroRepository.getItemById(livro.getId());
                        new LivroDisplay(livroAvaliado).draw(terminal);
                        return;
                    }
                    break;
                case "3":
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (!input.equals("3"));
    }
}

