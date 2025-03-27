package controller.DataBase;

import model.Filme;
import java.util.ArrayList;
import java.util.List;

public class FilmeRepository implements IRepository<Filme> {
    private static FilmeRepository instance;
    private List<Filme> filmes;
    private int filmeId;

    private FilmeRepository() {
        filmes = new ArrayList<>();
        filmeId = 0;
    }

    public static FilmeRepository getInstance() {
        if (instance == null) {
            instance = new FilmeRepository();
        }
        return instance;
    }

    @Override
    public List<Filme> getItems() {
        return filmes;
    }

    @Override
    public Filme getItemById(int id) {
        // Busca o filme pelo ID
        for (Filme filme : filmes) {
            if (filme.getId() == id) {
                return filme;
            }
        }
        return null; // Caso não encontre o filme
    }

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

    @Override
    public void save(Filme filme) {
        filme.setId(filmeId++);  // Atribui um ID único ao filme antes de salvar
        filmes.add(filme);
    }

    @Override
    public void delete(Filme filme) {
        filmes.removeIf(filmeItem -> filmeItem.getId() == filme.getId());
    }
}
