package view.livro;

import controller.action.ActionResult;
import controller.action.livro.CreateLivroAction;
import controller.action.livro.CreateLivroValidation;
import controller.dataBase.GeneroRepository;
import model.Livro;
import model.Genero;
import view.commons.Screen;
import view.commons.ViewCommons;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * Tela para criação de um novo livro no sistema.
 * <p>
 * A classe coleta informações sobre o livro, como título, autor, editora, ISBN,
 * ano de lançamento, gêneros, se o livro foi consumido, e se o usuário possui ou já leu o livro.
 * Depois de criar o livro, o mesmo é registrado no sistema e o resultado da operação é mostrado.
 * </p>
 */
public class CreateLivroForm extends Screen {

    /**
     * Exibe o formulário para a criação de um livro e coleta as informações do usuário.
     * <p>
     * Caso não haja gêneros cadastrados no sistema, o usuário é informado para cadastrar um
     * gênero antes de criar o livro.
     * </p>
     * @param terminal O objeto Scanner usado para capturar entradas do usuário
     */
    @Override
    public void draw(Scanner terminal) {
        // Verifica se há gêneros cadastrados no sistema
        if (GeneroRepository.getInstance().getItems().isEmpty()) {
            System.out.println("Nenhum genero cadastrado. Cadastre um genero antes de criar um livro.");
            return;
        }

        ActionResult result;  // Armazenará o resultado da operação
        Livro livro;          // Instância do livro que será criada

        do {
            // Exibe o título da tela de criação de livro
            System.out.println("\n=== Criar Livro ===\n");

            // Coleta as informações sobre o livro
            System.out.print("Título do livro: ");
            String nome = terminal.nextLine();

            System.out.print("Autor: ");
            String autor = terminal.nextLine();

            System.out.print("Editora: ");
            String editora = terminal.nextLine();

            System.out.print("ISBN: ");
            String isbn = terminal.nextLine();

            // Solicita o ano de lançamento
            int anoLancamento = ViewCommons.inputAno(terminal, "Ano de Lançamento");

            // Coleta os gêneros do livro
            List<Genero> generosLivro = ViewCommons.inputGenero(terminal);

            // Pergunta se o livro foi lido e se o usuário possui o livro
            boolean consumido = ViewCommons.inputBoolean(terminal, "Já Leu? ");
            boolean possui = ViewCommons.inputBoolean(terminal, "Possui? ");

            // Criação do livro com os dados coletados
            livro = new Livro(nome, LocalDateTime.of(anoLancamento, 1, 1, 0, 0),
                    consumido, generosLivro, autor, editora, possui, isbn);

            System.out.println(livro.getTitulo());

            // Criação do objeto que executa a ação de salvar o livro
            CreateLivroAction createLivro = new CreateLivroAction(new CreateLivroValidation());
            result = createLivro.execute(livro);  // Executa a ação de criação

            // Exibe o resultado da operação (sucesso ou falha)
            System.out.println("\n=== Resultado: " + result.getMessage());

        } while (!result.isSuccess());  // Repete até a criação do livro ser bem-sucedida

        // Exibe a tela com as informações do livro criado
        new LivroDisplay(livro).draw(terminal);
    }
}
