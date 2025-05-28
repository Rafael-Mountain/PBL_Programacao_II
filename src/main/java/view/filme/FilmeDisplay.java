package view.filme;

import controller.dataBase.FilmeRepository;
import model.Filme;
import view.avaliacao.AvaliacaoDisplay;
import view.avaliacao.CreateAvaliacaoForm;
import view.commons.Screen;

import java.util.Scanner;

/**
 * Tela que exibe os detalhes de um filme, incluindo seu título, ano de lançamento, gêneros, elenco e mais.
 * Também permite que o usuário execute ações relacionadas ao filme, como editar ou adicionar avaliações.
 * <p>
 * A classe estende {@link view.commons.Screen} e é responsável por apresentar uma visão completa de um filme,
 * incluindo uma lista de avaliações e a opção de classificar o filme ou editar suas informações. O usuário também
 * pode optar por voltar à tela anterior.
 * </p>
 */
public class FilmeDisplay extends Screen {
    private final Filme filme;

    /**
     * Constrói uma instância de {@link FilmeDisplay} com o objeto {@link Filme} fornecido.
     *
     * @param filme o filme a ser exibido
     */
    public FilmeDisplay(Filme filme) {
        this.filme = filme;
    }

    /**
     * Exibe os detalhes do filme, incluindo o título, título original, ano de lançamento, gêneros, diretor,
     * duração, elenco, local disponível, status de consumo e roteiro. Também exibe a lista de avaliações
     * (se houver) associadas ao filme.
     * <p>
     * Após mostrar os detalhes, o usuário tem as opções de:
     * <ul>
     *     <li>Editar os detalhes do filme</li>
     *     <li>Avaliar o filme (se assistido)</li>
     *     <li>Voltar para a tela anterior</li>
     * </ul>
     * O método solicita a entrada do usuário e responde de acordo com a opção escolhida.
     * Se o usuário escolher avaliar o filme, ele será direcionado para o {@link CreateAvaliacaoForm} para fornecer uma avaliação.
     * Se o usuário escolher editar o filme, ele será direcionado para o {@link UpdateFilmeForm}.
     * </p>
     *
     * @param terminal o objeto scanner usado para ler a entrada do console
     */
    @Override
    public void draw(Scanner terminal) {
        System.out.println("\n=== Detalhes do Filme ===");
        System.out.println("Título: " + filme.getTitulo());
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
                    new UpdateFilmeForm(filme).draw(terminal);
                    new FilmeDisplay(FilmeRepository.getInstance().getItemById(filme.getId())).draw(terminal);
                    return;
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
