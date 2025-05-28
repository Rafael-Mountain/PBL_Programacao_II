package view.filme;

import controller.dataBase.GeneroRepository;
import controller.action.filme.CreateFilmeAction;
import controller.action.filme.CreateFilmeValidation;
import controller.action.ActionResult;
import model.Filme;
import model.Genero;
import view.commons.Screen;
import view.commons.ViewCommons;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * Tela responsável por permitir ao usuário criar um novo filme, fornecendo detalhes como título, diretor, gêneros,
 * entre outros. Esta classe gerencia o processo de coleta das informações do filme, validação da entrada e criação
 * do objeto de filme.
 * <p>
 * A classe estende {@link view.commons.Screen} e implementa o processo de criação do filme de forma interativa
 * através do console. Ela garante que um filme só possa ser criado caso existam gêneros disponíveis no sistema.
 * </p>
 */
public class CreateFilmeForm extends Screen {

    /**
     * Solicita ao usuário que insira todos os detalhes necessários para criar um novo filme. Isso inclui:
     * <ul>
     *     <li>Título do filme</li>
     *     <li>Título original do filme</li>
     *     <li>Ano de lançamento</li>
     *     <li>Duração do filme em minutos</li>
     *     <li>Diretor</li>
     *     <li>Roteirista</li>
     *     <li>Local onde o filme está disponível</li>
     *     <li>Gêneros associados ao filme</li>
     *     <li>Elenco (atores/atrizes)</li>
     *     <li>Se o filme foi assistido ou não</li>
     * </ul>
     * <p>
     * O método valida as entradas, garantindo que todos os campos obrigatórios sejam fornecidos e formatados
     * corretamente. Se algum dado necessário estiver ausente ou inválido, o usuário será solicitado a corrigir a
     * informação.
     * </p>
     * Após coletar todos os dados, o método tenta criar um novo objeto {@link model.Filme} e processá-lo usando a
     * classe {@link controller.action.filme.CreateFilmeAction}.
     * <p>
     * Caso a criação do filme seja bem-sucedida, uma mensagem de sucesso será exibida. Caso contrário, o usuário
     * será solicitado a corrigir qualquer problema com os dados fornecidos.
     * </p>
     *
     * @param terminal o objeto scanner usado para ler a entrada do console
     */
    @Override
    public void draw(Scanner terminal) {
        if (GeneroRepository.getInstance().getItems().isEmpty()) {
            System.out.println("Nenhum genero cadastrado. Cadastre um genero antes de criar um filme.");
            return;
        }

        ActionResult result;
        Filme filme;

        do {
            System.out.println("\n=== Criar Filme ===\n");
            System.out.print("Titulo do filme: ");
            String nome = terminal.nextLine();
            System.out.print("Titulo Original: ");
            String nomeOriginal = terminal.nextLine();

            int AnoLancamento = ViewCommons.inputAno(terminal, "Ano de Lançamento");
            int duracao = ViewCommons.inputInt(terminal, "Duração (em minutos): ");

            System.out.print("Diretor: ");
            String diretor = terminal.nextLine();
            System.out.print("Roteiro: ");
            String roteiro = terminal.nextLine();
            System.out.print("Local disponível: ");
            String localDisponivel = terminal.nextLine();

            List<Genero> generosFilme = ViewCommons.inputGenero(terminal);
            List<String> elenco = ViewCommons.inputElenco(terminal);

            boolean consumido = ViewCommons.inputBoolean(terminal, "Já Assisti? ");

            filme = new Filme(nome, LocalDateTime.of(AnoLancamento, 1, 1, 0, 0),
                    consumido, generosFilme, nomeOriginal, localDisponivel,
                    elenco, diretor, duracao, roteiro);

            CreateFilmeAction createFilme = new CreateFilmeAction(new CreateFilmeValidation());
            result = createFilme.execute(filme);

            System.out.println("\n=== Resultado: " + result.getMessage());

        } while (!result.isSuccess());

        new FilmeDisplay(filme).draw(terminal);
    }
}
