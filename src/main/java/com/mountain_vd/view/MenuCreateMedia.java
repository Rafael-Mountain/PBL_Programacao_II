package com.mountain_vd.view;

import com.mountain_vd.view.commons.Screen;
import com.mountain_vd.view.filme.CreateFilmeForm;
import com.mountain_vd.view.livro.CreateLivroForm;
import com.mountain_vd.view.serie.CreateSerieForm;

import java.util.Scanner;

/**
 * Classe que representa o menu para criação de novas mídias.
 * O menu oferece opções para criar um filme, uma série ou um livro.
 */
public class MenuCreateMedia extends Screen {

    /**
     * Método responsável por desenhar e exibir o menu de criação de mídia.
     * O menu permite ao usuário escolher entre criar um filme, uma série ou um livro,
     * ou voltar para o menu anterior.
     *
     * @param terminal Scanner usado para capturar entradas do usuário.
     */
    @Override
    public void draw(Scanner terminal) {
        String opcao;

        do {
            // Exibe o menu de criação de mídia com as opções disponíveis
            System.out.println("\n=== Criar Media ===");
            System.out.println("1 - Criar Filme");
            System.out.println("2 - Criar Serie");
            System.out.println("3 - Criar Livro");
            System.out.println("4 - Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = terminal.nextLine();

            // Processa a opção escolhida pelo usuário
            switch (opcao) {
                case "1":
                    // Chama o formulário para criar um filme
                    new CreateFilmeForm().draw(terminal);
                    break;
                case "2":
                    // Chama o formulário para criar uma série
                    new CreateSerieForm().draw(terminal);
                    break;
                case "3":
                    // Chama o formulário para criar um livro
                    new CreateLivroForm().draw(terminal);
                    break;
                case "4":
                    // Volta ao menu anterior
                    return;
                default:
                    // Caso o usuário digite uma opção inválida
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (!opcao.equals("4")); // O loop continua até que o usuário escolha voltar
    }
}
