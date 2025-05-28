package view.commons;

import controller.dataBase.GeneroRepository;
import model.Genero;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A classe {@code ViewCommons} contém métodos auxiliares para interação com o terminal,
 * fornecendo funcionalidades como entrada de dados para gêneros, elenco, anos, entre outros.
 *
 * <p>Essa classe facilita a coleta de entradas de usuários em telas interativas, validando as entradas
 * e oferecendo uma interface para coleta de dados comuns, como listas, inteiros, booleans e datas.</p>
 *
 * <p>Exemplo de uso:</p>
 * <pre>
 * Scanner scanner = new Scanner(System.in);
 * int ano = ViewCommons.inputAno(scanner, "Digite o ano de lançamento");
 * </pre>
 *
 */
public class ViewCommons {

    /**
     * Solicita ao usuário a seleção de gêneros e retorna uma lista de objetos {@link Genero} selecionados.
     * O usuário pode continuar selecionando gêneros até decidir parar, digitando '-1'.
     *
     * @param terminal O scanner usado para capturar entradas do terminal.
     * @return Uma lista de objetos {@link Genero} selecionados pelo usuário.
     */
    public static List<Genero> inputGenero(Scanner terminal) {
        List<Genero> generos = new ArrayList<>();
        GeneroRepository generoRepository = GeneroRepository.getInstance();
        int generoId;

        System.out.println("=== Gêneros disponíveis ");
        for (Genero g : generoRepository.getItems()) {
            System.out.println(g.getId() + " - " + g.getNome());
        }

        do {
            System.out.println("Lista atual: " + generos);
            generoId = inputIntOrExit(terminal, "Selecione o genero(ou '-1' para finalizar): ");

            if (generoId == -1) {
                break;
            }

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

    /**
     * Solicita ao usuário a entrada do elenco (atores/atrizes) e retorna uma lista com os nomes dos integrantes.
     * O usuário pode adicionar múltiplos atores até digitar 'sair' para finalizar.
     *
     * @param terminal O scanner usado para capturar entradas do terminal.
     * @return Uma lista de strings representando os nomes dos atores/atrizes.
     */
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

    /**
     * Solicita ao usuário o ano de lançamento e retorna um valor inteiro validado.
     * O ano deve ser informado no formato de 4 dígitos (YYYY).
     *
     * @param terminal O scanner usado para capturar entradas do terminal.
     * @param message A mensagem a ser exibida ao usuário para solicitar a entrada.
     * @return O ano de lançamento como um valor inteiro.
     */
    public static int inputAno(Scanner terminal, String message) {
        String anoLancamento;
        int ano;
        do {
            System.out.print(message + " (YYYY): ");
            anoLancamento = terminal.nextLine();
            if (anoLancamento.length() != 4 || !anoLancamento.matches("[0-9]+")) {
                System.out.println("Ano inválido. Tente novamente.");
            }
        } while (anoLancamento.length() != 4 || !anoLancamento.matches("[0-9]+"));
        ano = Integer.parseInt(anoLancamento);
        return ano;
    }

    /**
     * Solicita ao usuário um valor inteiro e retorna esse valor após validá-lo.
     * O valor deve ser um número inteiro positivo.
     *
     * @param terminal O scanner usado para capturar entradas do terminal.
     * @param message A mensagem a ser exibida ao usuário para solicitar a entrada.
     * @return O valor inteiro fornecido pelo usuário.
     */
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

    /**
     * Solicita ao usuário um valor inteiro ou a opção de sair (valor -1).
     * O valor deve ser um número inteiro ou '-1' para finalizar a seleção.
     *
     * @param terminal O scanner usado para capturar entradas do terminal.
     * @param message A mensagem a ser exibida ao usuário para solicitar a entrada.
     * @return O valor inteiro fornecido pelo usuário, ou '-1' para sair.
     */
    public static int inputIntOrExit(Scanner terminal, String message) {
        String input;
        int value;
        do {
            System.out.print(message);
            input = terminal.nextLine();
            if (!input.matches("^(-1|\\d+)$")) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        } while (!input.matches("^(-1|\\d+)$"));
        value = Integer.parseInt(input);
        return value;
    }

    /**
     * Solicita ao usuário uma entrada booleana (Sim/Não) e retorna o valor correspondente.
     * O valor deve ser 's' ou 'n', representando Sim e Não, respectivamente.
     *
     * @param terminal O scanner usado para capturar entradas do terminal.
     * @param message A mensagem a ser exibida ao usuário para solicitar a entrada.
     * @return O valor booleano correspondente à entrada do usuário.
     */
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

    /**
     * Solicita ao usuário um valor inteiro dentro de um intervalo específico e retorna o valor.
     * O valor deve estar dentro do intervalo definido entre {@code min} e {@code max}.
     *
     * @param terminal O scanner usado para capturar entradas do terminal.
     * @param message A mensagem a ser exibida ao usuário para solicitar a entrada.
     * @param min O valor mínimo permitido.
     * @param max O valor máximo permitido.
     * @return O valor inteiro fornecido pelo usuário dentro do intervalo.
     */
    public static int inputIntWRange(Scanner terminal, String message, int min, int max) {
        int input;
        do {
            input = inputInt(terminal, message);

            if(input == -1){
                break;
            }
            if (input < min || input > max) {
                System.out.println("Valor fora do intervalo. Tente novamente.");
            }

        } while (input < min || input > max );
        return input;
    }

    /**
     * Solicita ao usuário um valor inteiro negativo e retorna esse valor após validá-lo.
     * O valor deve ser um número inteiro, incluindo números negativos.
     *
     * @param terminal O scanner usado para capturar entradas do terminal.
     * @param message A mensagem a ser exibida ao usuário para solicitar a entrada.
     * @return O valor inteiro negativo fornecido pelo usuário.
     */
    public static int inputNegativeInt(Scanner terminal, String message) {
        String input;
        int value;
        do {
            System.out.print(message);
            input = terminal.nextLine();
            if (!input.matches("-?[0-9]+")) {
                System.out.println("Valor inválido. Tente novamente.");
            }
        } while (!input.matches("-?[0-9]+"));
        value = Integer.parseInt(input);
        return value;
    }

    /**
     * Solicita ao usuário uma data no formato "dd/MM/yyyy" e retorna o valor de data validado.
     *
     * @param terminal O scanner usado para capturar entradas do terminal.
     * @param message A mensagem a ser exibida ao usuário para solicitar a entrada.
     * @return O valor da data fornecida pelo usuário.
     */
    public static LocalDate inputData(Scanner terminal, String message) {
        LocalDate data = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            System.out.print(message + " (formato: dd/MM/yyyy): ");
            String input = terminal.nextLine();

            try {
                data = LocalDate.parse(input, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Tente novamente.");
            }
        }

        return data;
    }

}




