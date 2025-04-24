package controller.dataBase;

import model.Genero;
import java.util.ArrayList;
import java.util.List;

public class GeneroRepository implements IRepository<Genero> {
    private static GeneroRepository instance;
    private List<Genero> generos;
    private int generoId;

    private GeneroRepository() {
        generos = new ArrayList<>();
        generoId = 0;
    }

    public static GeneroRepository getInstance() {
        if (instance == null) {
            instance = new GeneroRepository();
        }
        return instance;
    }

    @Override
    public List<Genero> getItems() {
        return generos;
    }

    @Override
    public Genero getItemById(int id) {
        // Busca o gênero pelo ID
        for (Genero genero : generos) {
            if (genero.getId() == id) {
                return genero;
            }
        }
        return null; // Caso não encontre o gênero
    }

    @Override
    public void update(Genero item) {
        for (int i = 0; i < generos.size(); i++) {
            if (generos.get(i).getId() == item.getId()) {
                generos.set(i, item);
                return;
            }
        }
        throw new RuntimeException("Genero não encontrado para atualização");
    }

    @Override
    public void add(Genero genero) {
        genero.setId(generoId++);  // Atribui um ID único ao gênero antes de salvar
        generos.add(genero);
    }

    @Override
    public void delete(Genero genero) {
        generos.removeIf(generoItem -> generoItem.getId() == genero.getId());
    }
}
