package controller.dataBase;

import controller.dataBase.repository.Repository;
import model.Serie;

/**
 * {@code SerieRepository} é uma classe que implementa o padrão Singleton para gerenciar
 * uma coleção de objetos {@link Serie}.
 *
 * Atua como um repositório em memória, oferecendo operações básicas como adicionar,
 * buscar, atualizar e remover séries.
 *
 * Essa implementação não realiza persistência em disco ou banco de dados, sendo ideal
 * para fases iniciais de desenvolvimento e testes.
 */
public class SerieRepository extends Repository<Serie> {

    private static SerieRepository instance;

    /**
     * Construtor privado. Garante que a instância seja única (Singleton).
     * Inicializa a lista de séries e o contador de IDs.
     */
    private SerieRepository() {
        super("Serie");
    }

    /**
     * Retorna a instância única da classe {@code SerieRepository}.
     *
     * @return instância única de {@code SerieRepository}
     */
    public static SerieRepository getInstance() {
        if (instance == null) {
            instance = new SerieRepository();
        }
        return instance;
    }

    @Override
    public Class<Serie> getItemClass() {
        return Serie.class;
    }
}
