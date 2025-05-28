package controller.dataBase;

import controller.dataBase.repository.IRepository;
import controller.dataBase.repository.Repository;
import model.Genero;

/**
 * {@code GeneroRepository} é uma implementação do padrão de repositório para a entidade {@link Genero}.
 * <p>
 * Fornece métodos para persistência em memória, incluindo operações de criação, leitura, atualização e remoção (CRUD).
 * Implementa a interface {@link IRepository}.
 * <p>
 * Utiliza o padrão Singleton para garantir uma única instância durante a execução da aplicação.
 */
public class GeneroRepository extends Repository<Genero> {
    private static GeneroRepository instance;

    /**
     * Construtor privado.
     */
    private GeneroRepository() {
       super("Genero");
    }

    /**
     * Retorna a instância única de {@code GeneroRepository}.
     *
     * @return instância única do repositório.
     */
    public static GeneroRepository getInstance() {
        if (instance == null) {
            instance = new GeneroRepository();
        }
        return instance;
    }

    @Override
    public Class<Genero> getItemClass() {
        return Genero.class;
    }
}
