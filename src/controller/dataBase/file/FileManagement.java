package controller.dataBase.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import controller.dataBase.repository.RepositoryContent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Gerencia a leitura e escrita de dados de um repositório em arquivos JSON.
 *
 * <p>Esta classe é responsável por serializar objetos {@link RepositoryContent} para JSON
 * e desserializar o conteúdo de arquivos JSON de volta para objetos {@link RepositoryContent}.
 * Ela utiliza a biblioteca Jackson para manipulação de JSON e garante que um arquivo
 * de dados padrão seja criado se não existir.</p>
 *
 * @param <T> O tipo de entidade contida no {@link RepositoryContent} que será gerenciado.
 *           Este tipo deve corresponder à classe dos itens armazenados no repositório.
 *
 * @see RepositoryContent
 * @see ObjectMapper
 */
public class FileManagement<T> {
    private final String fileName;
    private final ObjectMapper objectMapper;
    private final String path;
    private final Class<T> clazz;

    /**
     * Constrói uma instância de {@code FileManagement}.
     *
     * <p>Inicializa o {@link ObjectMapper} com o módulo {@link JavaTimeModule} para
     * serialização/desserialização correta de tipos {@code java.time} e desabilita
     * a escrita de datas como timestamps para melhor legibilidade.</p>
     *
     * <p>O caminho do arquivo JSON é construído com base no nome do arquivo fornecido
     * e no diretório de trabalho atual da aplicação, resultando em um arquivo como
     * {@code <diretorio_atual>/<fileName>.json}.</p>
     *
     * @param fileName O nome base do arquivo JSON (sem a extensão .json) onde os dados
     *                 serão armazenados. Este nome geralmente corresponde ao nome da entidade
     *                 do repositório (ex: "Filme", "Livro").
     * @param clazz    A classe do tipo de entidade {@code T} armazenada no {@link RepositoryContent}.
     *                 Isto é necessário para a correta desserialização genérica dos itens.
     */
    public FileManagement(String fileName, Class<T> clazz) {
        this.fileName = fileName;
        this.clazz = clazz;
        Path cwd = Paths.get("").toAbsolutePath();
        this.path = cwd + File.separator + fileName + ".json"; // Define o caminho completo do arquivo JSON
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(SerializationFeature.INDENT_OUTPUT);
    }

    /**
     * Carrega o conteúdo do repositório a partir do arquivo JSON correspondente.
     *
     * <p>Antes de tentar carregar, o método {@link #verifyFile()} é chamado para
     * garantir que o arquivo exista (criando um padrão se necessário).</p>
     *
     * <p>Utiliza o {@link ObjectMapper} para desserializar o conteúdo do arquivo JSON
     * em um objeto {@link RepositoryContent} parametrizado com a classe {@code T}
     * fornecida no construtor.</p>
     *
     * @return Um objeto {@link RepositoryContent} contendo a lista de itens e o contador de ID
     *         lidos do arquivo JSON.
     * @throws IOException Se ocorrer um erro durante a leitura ou desserialização do arquivo.
     */
    public RepositoryContent<T> load() throws IOException {
        verifyFile(); // Garante que o arquivo exista
        return objectMapper.readValue(
                new File(path),
                objectMapper.getTypeFactory()
                        .constructParametricType(RepositoryContent.class, clazz) // Constrói o tipo genérico para desserialização
        );
    }

    /**
     * Salva (serializa) o conteúdo fornecido de um repositório para o arquivo JSON correspondente.
     *
     * <p>Antes de tentar salvar, o método {@link #verifyFile()} é chamado para
     * garantir que o diretório e o arquivo possam ser acessados (criando um padrão se necessário,
     * embora o foco aqui seja mais para o load inicial).</p>
     *
     * <p>Utiliza o {@link ObjectMapper} para serializar o objeto {@link RepositoryContent}
     * para o formato JSON e escrevê-lo no arquivo.</p>
     *
     * @param repositoryContent O objeto {@link RepositoryContent} a ser salvo, contendo
     *                          a lista de itens e o contador de ID.
     * @throws RuntimeException Se ocorrer um erro durante a escrita (serialização) do arquivo,
     *                          encapsulando a {@link IOException} original.
     */
    public void dump(RepositoryContent<T> repositoryContent) {
        verifyFile(); // Garante que o arquivo (ou pelo menos o caminho) esteja preparado
        try {
            // System.out.println("Saving to: " + this.path); // Linha de debug pode ser útil
            objectMapper.writeValue(new File(this.path), repositoryContent);
        } catch (IOException e) {
            // Considerar um tratamento de exceção mais específico ou logging em uma aplicação maior.
            throw new RuntimeException("Error writing to file: " + fileName, e);
        }
    }

    /**
     * Verifica se o arquivo JSON de dados para este gerenciador existe.
     * Se o arquivo não existir, ele cria um novo arquivo com um conteúdo JSON padrão
     * representando um repositório vazio (lista de itens vazia e contador de ID zerado).
     *
     * <p>Este método é crucial para a primeira execução da aplicação ou se o arquivo
     * de dados for acidentalmente deletado, permitindo que o sistema inicie
     * corretamente com um estado de dados vazio.</p>
     */
    public void verifyFile() {
        File file = new File(this.path);
        if (!file.exists()) {
            // Estrutura JSON padrão para um repositório vazio
            String json = "{\"listItems\":[],\"idCounter\":0}";
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(json);
                // System.out.println("Default file created: " + this.path);
            } catch (IOException e) {
                System.err.println("Erro ao criar arquivo padrão '" + this.path + "': " + e.getMessage());
            }
        }
    }
}