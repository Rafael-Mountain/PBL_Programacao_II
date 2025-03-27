package controller.DataBase;

import model.Livro;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository implements IRepository<Livro> {
    private static LivroRepository instance;
    private List<Livro> livros;
    private int livroId;

    private LivroRepository() {
        livros = new ArrayList<>();
        livroId = 0;
    }

    public static LivroRepository getInstance() {
        if (instance == null) {
            instance = new LivroRepository();
        }
        return instance;
    }

    @Override
    public List<Livro> getItems() {
        return livros;
    }

    @Override
    public Livro getItemById(int id) {
        // Busca o livro pelo ID
        for (Livro livro : livros) {
            if (livro.getId() == id) {
                return livro;
            }
        }
        return null; // Caso não encontre o livro
    }

    @Override
    public void update(Livro item) {
        for (int i = 0; i < livros.size(); i++) {
            if (livros.get(i).getId() == item.getId()) {
                livros.set(i, item);
                return;
            }
        }
        System.out.println("Livro não encontrado para atualização.");
    }

    @Override
    public void save(Livro livro) {
        livro.setId(livroId++);  // Atribui um ID único ao livro antes de salvar
        livros.add(livro);
    }

    @Override
    public void delete(Livro livro) {
        livros.removeIf(livroItem -> livroItem.getId() == livro.getId());
    }
}
