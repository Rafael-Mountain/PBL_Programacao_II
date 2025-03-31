package view.commons;

import controller.dataBase.GeneroRepository;
import model.Genero;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewCommons {
    public static List<Genero> inputGenero(Scanner terminal) {
        List<Genero>  generos = new ArrayList<>();
        GeneroRepository generoRepository = GeneroRepository.getInstance();
        String genero;

        System.out.println("=== Gêneros disponíveis ");
        for (Genero g : generoRepository.getItems()) {
            System.out.println(g.getId() + " - " + g.getNome());
        }

        do {
            System.out.println("Lista atual: " + generos);
            System.out.print("Selecione o genero(ou 'sair' para finalizar): ");
            genero = terminal.nextLine();

            if (genero.equalsIgnoreCase("sair")) {
                break;
            }

            int generoId = Integer.parseInt(genero);
            Genero generoSelecionado = generoRepository.getItemById(generoId);

            if (generoSelecionado != null) {
                if (generos.contains(generoSelecionado)) {
                    System.out.println("Gênero já adicionado. Tente novamente.");

                } else {
                    generos.add(generoSelecionado);
                }

            } else {
                System.out.println("Gênero inválido. Tente novamente.");
            }
        } while (true);
        return generos;
    }

    public static List<String> inputElenco(Scanner terminal) {
        List<String> elenco = new ArrayList<>();
        String ator;
        System.out.println("=== Elenco ");
        do {
            System.out.println("Lista atual: " + elenco);
            System.out.print("Ator/atriz (ou 'sair' para finalizar): ");
            ator = terminal.nextLine();
            if (ator.equalsIgnoreCase("sair")) {
                break;
            }
            String finalAtor = ator;
            if (elenco.stream().anyMatch(a -> a.equalsIgnoreCase(finalAtor))) {
                System.out.println("Ator/atriz já adicionado. Tente novamente.");
                continue;
            }
            if (ator.isEmpty()) {
                System.out.println("Nome inválido. Tente novamente.");
                continue;
            }
            elenco.add(ator);
            System.out.println("Ator/atriz adicionado: " + ator);
        } while (!ator.equalsIgnoreCase("sair"));
        return elenco;
    }

    public static int inputAnoLancamento(Scanner terminal) {
        String anoLancamento;
        int ano;
        do {
            System.out.print("Ano de lançamento (YYYY): ");
            anoLancamento = terminal.nextLine();
            if (anoLancamento.length() != 4 || !anoLancamento.matches("[0-9]+")) {
                System.out.println("Ano inválido. Tente novamente.");
            }
        } while (anoLancamento.length() != 4 || !anoLancamento.matches("[0-9]+"));
        ano = Integer.parseInt(anoLancamento);
        return ano;
    }

    public static int inputInt(Scanner terminal, String message) {
        String input;
        int value;
        do {
            System.out.print(message);
            input = terminal.nextLine();
            if (!input.matches("[0-9]+")) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        } while (!input.matches("[0-9]+"));
        value = Integer.parseInt(input);
        return value;
    }

    public static boolean inputBoolean(Scanner terminal, String message) {
        String input;
        boolean value;
        do {
            System.out.print(message + ("(S/N): "));
            input = terminal.nextLine();
            if (!input.equalsIgnoreCase("s") && !input.equalsIgnoreCase("n")) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        } while (!input.equalsIgnoreCase("s") && !input.equalsIgnoreCase("n"));

        if (input.equalsIgnoreCase("s"))
            value = true;
        else
            value = false;

        System.out.println("Valor selecionado: " + (value ? "Sim" : "Não"));
        return value;
    }
}

