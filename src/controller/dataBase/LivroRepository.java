package controller.dataBase;

import controller.dataBase.repository.Repository;
import model.Genero;
import model.Livro;

/**
 * {@code LivroRepository} é uma classe singleton responsável por gerenciar o armazenamento
 * em memória de objetos do tipo {@link Livro}.
 * <p>
 * Essa classe fornece funcionalidades para adicionar, atualizar, buscar e remover livros,
 * simulando o comportamento de um repositório de dados.
 */
public class LivroRepository extends Repository<Livro> {

    private static LivroRepository instance;

    /**
     * Construtor privado para garantir o padrão Singleton.
     * Inicializa a lista de livros e o contador de IDs.
     */
    private LivroRepository() {
        super("Livro");
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
    @Override
    public Class<Livro> getItemClass() {
        return Livro.class;
    }
}
