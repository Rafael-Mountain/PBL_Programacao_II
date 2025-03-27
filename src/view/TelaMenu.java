package view;

import java.util.Scanner;

public class TelaMenu {

    public static void makeTela() {
        Scanner terminal = new Scanner(System.in);
        String opcao;

        do {
            System.out.println("\n=== Bem-Vindo ao Sistema ===");
            System.out.println("1 - Criar Media");
            System.out.println("2 - Opção 2");
            System.out.println("3 - Opção 3");
            System.out.println("4 - Opção 4");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = terminal.nextLine();

            switch (opcao) {
                case "1":
                    TelaCriacao.makeTela();
                    break;
                case "2":
                    System.out.println("Executando opção 2...");
                    break;
                case "3":
                    System.out.println("Executando opção 3...");
                    break;
                case "4":
                    System.out.println("Executando opção 4...");
                    break;
                case "5":
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (!opcao.equals("5"));

        terminal.close(); // Fecha o Scanner para evitar vazamento de recursos
    }

}
