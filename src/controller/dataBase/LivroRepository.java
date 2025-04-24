package controller.dataBase;

import model.Livro;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code LivroRepository} é uma classe singleton responsável por gerenciar o armazenamento
 * em memória de objetos do tipo {@link Livro}.
 *
 * Essa classe fornece funcionalidades para adicionar, atualizar, buscar e remover livros,
 * simulando o comportamento de um repositório de dados.
 */
public class LivroRepository implements IRepository<Livro> {

    private static LivroRepository instance;
    private List<Livro> livros;
    private int livroId;

    /**
     * Construtor privado para garantir o padrão Singleton.
     * Inicializa a lista de livros e o contador de IDs.
     */
    private LivroRepository() {
        livros = new ArrayList<>();
        livroId = 0;
    }

    /**
     * Retorna a instância única da classe {@code LivroRepository}.
     *
     * @return instância única de {@code LivroRepository}
     */
    public static LivroRepository getInstance() {
        if (instance == null) {
            instance = new LivroRepository();
        }
        return instance;
    }

    /**
     * Retorna todos os livros armazenados no repositório.
     *
     * @return lista de livros cadastrados
     */
    @Override
    public List<Livro> getItems() {
        return livros;
    }

    /**
     * Retorna um livro com base no ID informado.
     *
     * @param id o identificador do livro
     * @return o livro correspondente ao ID ou {@code null} se não for encontrado
     */
    @Override
    public Livro getItemById(int id) {
        // Busca o livro pelo ID
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                return livro;
            }
        }
        return null;
    }

    /**
     * Atualiza um livro existente no repositório.
     *
     * @param item o livro com os dados atualizados
     * @throws RuntimeException se o livro não for encontrado para atualização
     */
    @Override
    public void update(Livro item) throws RuntimeException {
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getId() == item.getId()) {
                livros.set(i, item);
                return;
            }
        }
        throw new RuntimeException("Livro não encontrado para atualização.");
    }

    /**
     * Adiciona um novo livro ao repositório, atribuindo-lhe um ID único.
     *
     * @param livro o livro a ser adicionado
     */
    @Override
    public void add(Livro livro) {
        livro.setId(livroId++);
        livros.add(livro);
    }

    /**
     * Remove um livro do repositório com base em seu ID.
     *
     * @param livro o livro a ser removido
     */
    @Override
    public void delete(Livro livro) {
        livros.removeIf(livroItem -> livroItem.getId() == livro.getId());
    }

    public void setLivroId(int id) {
        this.livroId = id;
    }
}
