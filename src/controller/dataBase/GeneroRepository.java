package controller.dataBase;

import model.Genero;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code GeneroRepository} é uma implementação do padrão de repositório para a entidade {@link Genero}.
 * <p>
 * Fornece métodos para persistência em memória, incluindo operações de criação, leitura, atualização e remoção (CRUD).
 * Implementa a interface {@link IRepository}.
 * <p>
 * Utiliza o padrão Singleton para garantir uma única instância durante a execução da aplicação.
 */
public class GeneroRepository implements IRepository<Genero> {
    private static GeneroRepository instance;
    private List<Genero> generos;
    private int generoId;

    /**
     * Construtor privado. Inicializa a lista de gêneros e o contador de IDs.
     */
    private GeneroRepository() {
        generos = new ArrayList<>();
        generoId = 0;
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

    /**
     * Retorna a lista de todos os gêneros armazenados.
     *
     * @return lista de {@link Genero}.
     */
    @Override
    public List<Genero> getItems() {
        return generos;
    }

    /**
     * Retorna o gênero com o ID especificado.
     *
     * @param id o ID do gênero.
     * @return {@link Genero} correspondente ou {@code null} se não for encontrado.
     */
    @Override
    public Genero getItemById(int id) {
        for (Genero genero : generos) {
            if (genero.getId() == id) {
                return genero;
            }
        }
        return null;
    }

    /**
     * Atualiza um gênero existente na lista. Caso não encontre o ID, exibe uma mensagem no console.
     *
     * @param item gênero com os novos dados.
     */
    @Override
    public void update(Genero item) {
        for (int i = 0; i < generos.size(); i++) {
            if (generos.get(i).getId() == item.getId()) {
                generos.set(i, item);
                return;
            }
        }
        System.out.println("Gênero não encontrado para atualização.");
    }

    /**
     * Salva um novo gênero atribuindo-lhe um ID único.
     *
     * @param genero o novo gênero a ser salvo.
     */
    @Override
    public void save(Genero genero) {
        genero.setId(generoId++);
        generos.add(genero);
    }

    /**
     * Remove um gênero da lista com base no seu ID.
     *
     * @param genero o gênero a ser removido.
     */
    @Override
    public void delete(Genero genero) {
        generos.removeIf(generoItem -> generoItem.getId() == genero.getId());
    }
}
