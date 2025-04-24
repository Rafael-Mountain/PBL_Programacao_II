package controller.dataBase;

import model.Serie;
import java.util.ArrayList;
import java.util.List;

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
public class SerieRepository implements IRepository<Serie> {

    private static SerieRepository instance;
    private List<Serie> series;
    private int serieId;

    /**
     * Construtor privado. Garante que a instância seja única (Singleton).
     * Inicializa a lista de séries e o contador de IDs.
     */
    private SerieRepository() {
        series = new ArrayList<>();
        serieId = 0;
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

    /**
     * Retorna a lista de todas as séries cadastradas no repositório.
     *
     * @return lista de séries
     */
    @Override
    public List<Serie> getItems() {
        return series;
    }

    /**
     * Retorna uma série com base no ID fornecido.
     *
     * @param id o identificador da série
     * @return a série correspondente ou {@code null} se não for encontrada
     */
    @Override
    public Serie getItemById(int id) {
        for (Serie serie : series) {
            if (serie.getId() == id) {
                return serie;
            }
        }
        return null;
    }

    /**
     * Atualiza os dados de uma série já existente no repositório.
     *
     * @param item a série com as informações atualizadas
     * @throws RuntimeException se a série não for encontrada para atualização
     */
    @Override
    public void update(Serie item) {
        for (int i = 0; i < series.size(); i++) {
            if (series.get(i).getId() == item.getId()) {
                series.set(i, item);
                return;
            }
        }
        throw new RuntimeException("Série não encontrada para atualização.");
    }

    /**
     * Adiciona uma nova série ao repositório, atribuindo a ela um ID único.
     *
     * @param serie a série a ser adicionada
     */
    @Override
    public void add(Serie serie) {
        serie.setId(serieId++);
        series.add(serie);
    }

    /**
     * Remove uma série do repositório com base em seu ID.
     *
     * @param serie a série a ser removida
     */
    @Override
    public void delete(Serie serie) {
        series.removeIf(serieItem -> serieItem.getId() == serie.getId());
    }
}
