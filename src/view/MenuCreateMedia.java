package view;

import view.commons.Screen;
import view.filme.CreateFilmeForm;
import view.livro.CreateLivroForm;
import view.serie.CreateSerieForm;

import java.util.Scanner;

public class MenuCreateMedia extends Screen {
    @Override
    public void draw(Scanner terminal) {
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
                    new CreateFilmeForm().draw(terminal);
                    break;
                case "2":
                    new CreateSerieForm().draw(terminal);
                    break;
                case "3":
                    new CreateLivroForm().draw(terminal);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (!opcao.equals("4"));

    }

}
