package view.filme;

import model.Filme;
import view.commons.Screen;

import java.util.Scanner;

public class FilmeDisplay extends Screen {
    private final Filme filme;

    public FilmeDisplay(Filme filme) {
        this.filme = filme;
    }

    @Override
    public void draw(Scanner terminal) {
        clear();
        System.out.println("=== Detalhes do Filme ===");
        System.out.println("Título " + filme.getTitulo());
        System.out.println("Título Original: " + filme.getTituloOriginal());
        System.out.println("Ano de Lançamento: " + filme.getDataLancamento().getYear());
        System.out.println("Gêneros: " + String.join(", ", filme.getGeneros().stream().map(g -> g.getNome()).toList()));
        System.out.println("Diretor: " + filme.getDirecao());
        System.out.println("Duração: " + filme.getDuracao() + " minutos");
        System.out.println("Elenco: " + String.join(", ", filme.getElenco()));
        System.out.println("Local Disponível: " + filme.getLocalDisponivel());
        System.out.println("Já assisti: " + (filme.isConsumido() ? "Sim" : "Não"));
        System.out.println("Roteiro: " + filme.getRoteiro());

        System.out.println("\n=== Ações ===");
        System.out.println("1. Editar");
        System.out.println("2. Excluir");
        System.out.println("3. Avaliar");
        System.out.println("4. Voltar");

        String input;
        do {
            System.out.print("Escolha uma opção");
            input = terminal.nextLine();

            switch (input) {
                case "1":
                    // Editar filme
                    break;
                case "2":
                    // Excluir filme
                    break;
                case "3":
                    // Avaliar filme
                    break;
                case "4":
                    break;
            }
        }
        while (input != "4");

    }
}
