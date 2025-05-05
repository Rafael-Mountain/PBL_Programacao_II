package controller.dataBase;

import controller.dataBase.repository.IRepository;
import controller.dataBase.repository.Repository;
import model.Filme;

/**
 * Repositório responsável por gerenciar instâncias da classe {@link Filme}.
 *
 * Implementa a interface {@link IRepository} para fornecer operações básicas de persistência em memória,
 * como salvar, buscar, atualizar e deletar objetos do tipo {@link Filme}.
 *
 * Esta implementação utiliza o padrão Singleton, garantindo que exista apenas uma instância da classe em tempo de execução.
 */
public class FilmeRepository extends Repository<Filme> {
    private static FilmeRepository instance;

    private FilmeRepository() {
        super("Filme");
    }

    /**
     * Retorna a instância única da classe {@code FilmeRepository}.
     *
     * @return Instância única do repositório de filmes.
     */
    public static FilmeRepository getInstance() {
        if (instance == null) {
            instance = new FilmeRepository();
        }
        return instance;
    }

}
