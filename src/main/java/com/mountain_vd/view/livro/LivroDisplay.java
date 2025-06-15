package com.mountain_vd.view.livro;

import com.mountain_vd.controller.dataBase.LivroRepository;
import com.mountain_vd.model.Livro;
import com.mountain_vd.view.avaliacao.AvaliacaoDisplay;
import com.mountain_vd.view.avaliacao.CreateAvaliacaoForm;
import com.mountain_vd.view.commons.Screen;

import java.util.Scanner;

/**
 * Exibe os detalhes de um livro, incluindo informações como título, autor, ISBN,
 * ano de lançamento, avaliação e ações possíveis (editar, avaliar, voltar).
 * Permite ao usuário interagir com o livro, editá-lo, avaliá-lo ou voltar ao menu anterior.
 */
public class LivroDisplay extends Screen {

    /**
     * O livro cujos detalhes serão exibidos.
     */
    private final Livro livro;

    /**
     * Construtor para a classe LivroDisplay.
     *
     * @param livro O livro cujos detalhes serão exibidos.
     */
    public LivroDisplay(Livro livro) {
        this.livro = livro;
    }

    /**
     * Exibe os detalhes do livro e permite ao usuário realizar ações como
     * editar o livro, avaliá-lo (se já foi lido) ou voltar ao menu anterior.
     *
     * @param terminal O objeto Scanner usado para capturar a entrada do usuário.
     */
    @Override
    public void draw(Scanner terminal) {
        // Exibe os detalhes do livro
        System.out.println("\n=== Detalhes do Livro ===");
        System.out.println("Título: " + livro.getTitulo());
        System.out.println("ISBN: " + livro.getIsbn());
        System.out.println("Autor: " + livro.getAutor());
        System.out.println("Possui Exemplar: " + (livro.isPossui() ? "Sim" : "Não"));
        System.out.println("Pontuação: " + livro.getPontuacao());
        System.out.println("Ano de Lançamento: " + livro.getDataLancamento().getYear());
        System.out.println("Gêneros: " + String.join(", ", livro.getGeneros().stream().map(g -> g.getNome()).toList()));
        System.out.println("Já li: " + (livro.isConsumido() ? "Sim" : "Não"));

        // Exibe as avaliações, se houver
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

        // Exibe o menu de ações
        System.out.println("\n=== Ações ===");
        System.out.println("1. Editar");
        System.out.println("2. Avaliar");
        System.out.println("3. Voltar");

        // Lê a opção do usuário
        String input;
        do {
            System.out.print("Escolha uma opção: ");
            input = terminal.nextLine();
            LivroRepository livroRepository = LivroRepository.getInstance();

            switch (input) {
                case "1":
                    // Edita o livro
                    new UpdateLivroForm(livro).draw(terminal);
                    new LivroDisplay(LivroRepository.getInstance().getItemById(livro.getId())).draw(terminal);
                    return;
                case "2":
                    // Avalia o livro, se já foi lido
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
                    // Volta para o menu anterior
                    break;
                default:
                    // Opção inválida
                    System.out.println("Opção inválida.");
            }
        } while (!input.equals("3"));
    }
}
