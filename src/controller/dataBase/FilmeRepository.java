package controller.dataBase;

import model.Filme;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositório responsável por gerenciar instâncias da classe {@link Filme}.
 *
 * Implementa a interface {@link IRepository} para fornecer operações básicas de persistência em memória,
 * como salvar, buscar, atualizar e deletar objetos do tipo {@link Filme}.
 *
 * Esta implementação utiliza o padrão Singleton, garantindo que exista apenas uma instância da classe em tempo de execução.
 */
public class FilmeRepository implements IRepository<Filme> {
    private static FilmeRepository instance;
    private List<Filme> filmes;
    private int filmeId;

    /**
     * Construtor privado para garantir o padrão Singleton.
     * Inicializa a lista de filmes e o contador de IDs.
     */
    private FilmeRepository() {
        filmes = new ArrayList<>();
        filmeId = 0;
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

    /**
     * Retorna todos os filmes armazenados no repositório.
     *
     * @return Lista de filmes.
     */
    @Override
    public List<Filme> getItems() {
        return filmes;
    }

    /**
     * Retorna um filme com base no seu ID.
     *
     * @param id ID do filme desejado.
     * @return Filme com o ID correspondente ou {@code null} se não encontrado.
     */
    @Override
    public Filme getItemById(int id) {
        for (Filme filme : filmes) {
            if (filme.getId() == id) {
                return filme;
            }
        }
        return null;
    }

    /**
     * Atualiza as informações de um filme existente.
     *
     * @param item Filme atualizado que substituirá o anterior.
     */
    @Override
    public void update(Filme item) {
        for (int i = 0; i < filmes.size(); i++) {
            if (filmes.get(i).getId() == item.getId()) {
                filmes.set(i, item);
                return;
            }
        }
        System.out.println("Filme não encontrado para atualização.");
    }

    /**
     * Salva um novo filme no repositório e atribui um ID único a ele.
     *
     * @param filme Filme a ser salvo.
     */
    @Override
    public void save(Filme filme) {
        filme.setId(filmeId++);
        filmes.add(filme);
    }

    /**
     * Remove um filme do repositório com base no seu ID.
     *
     * @param filme Filme a ser removido.
     */
    @Override
    public void delete(Filme filme) {
        filmes.removeIf(filmeItem -> filmeItem.getId() == filme.getId());
    }
}
