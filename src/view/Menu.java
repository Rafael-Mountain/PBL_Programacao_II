package view;

import view.commons.Screen;
import view.genero.GeneroMenu;

import java.util.Scanner;

/**
 * Classe que representa o menu principal do sistema "Diário Cultural".
 * O menu oferece opções para criar, listar mídias, acessar o menu de gêneros, ou sair do sistema.
 */
public class Menu extends Screen {

    /**
     * Método responsável por desenhar e exibir o menu principal do sistema.
     * O menu permite ao usuário escolher entre criar mídia, listar mídias,
     * acessar os gêneros ou sair do sistema.
     *
     * @param terminal Scanner usado para capturar entradas do usuário.
     */
    @Override
    public void draw(Scanner terminal) {
        String opcao;

        do {
            // Exibe o menu com as opções
            System.out.println("\n=== Bem-Vindo ao Diario Cultural ===");
            System.out.println("1 - Criar Midia");
            System.out.println("2 - Listar Midia");
            System.out.println("3 - Generos");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = terminal.nextLine();

            // Processa a opção escolhida pelo usuário
            switch (opcao) {
                case "1":
                    // Chama o menu para criar uma nova mídia
                    new MenuCreateMedia().draw(terminal);
                    break;
                case "2":
                    // Chama o menu para listar as mídias existentes
                    new MenuListMedia().draw(terminal);
                    break;
                case "3":
                    // Chama o menu de gêneros
                    new GeneroMenu().draw(terminal);
                    break;
                case "4":
                    // Exibe mensagem de saída e encerra o loop
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    // Caso o usuário digite uma opção inválida
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (!opcao.equals("4")); // O loop continua até que o usuário escolha sair
    }
}
