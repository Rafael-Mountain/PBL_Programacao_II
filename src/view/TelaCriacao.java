package view;

import java.util.Scanner;

public class TelaCriacao {
    public static void makeTela() {
        Scanner terminal = new Scanner(System.in);
        String opcao;

        do {
            System.out.println("\n=== Criar Media ===");
            System.out.println("1 - Criar Filme");
            System.out.println("2 - Criar Serie");
            System.out.println("3 - Criar Livro");
            System.out.println("4 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = terminal.nextLine();

            switch (opcao) {
                case "1":
                    System.out.println("Executando opção 1...");
                    break;
                case "2":
                    System.out.println("Executando opção 2...");
                    break;
                case "3":
                    System.out.println("Executando opção 3...");
                    break;
                case "4":
                    System.out.println("Voltando");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (!opcao.equals("4"));

        terminal.close(); // Fecha o Scanner para evitar vazamento de recursos
    }

}
