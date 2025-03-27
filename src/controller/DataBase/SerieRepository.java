package controller.DataBase;

import model.Serie;
import java.util.ArrayList;
import java.util.List;

public class SerieRepository implements IRepository<Serie> {
    private static SerieRepository instance;
    private List<Serie> series;
    private int serieId;

    private SerieRepository() {
        series = new ArrayList<>();
        serieId = 0;
    }

    public static SerieRepository getInstance() {
        if (instance == null) {
            instance = new SerieRepository();
        }
        return instance;
    }

    @Override
    public List<Serie> getItems() {
        return series;
    }

    @Override
    public Serie getItemById(int id) {
        // Busca a série pelo ID
        for (Serie serie : series) {
            if (serie.getId() == id) {
                return serie;
            }
        }
        return null; // Caso não encontre a série
    }

    @Override
    public void update(Serie item) {
        for (int i = 0; i < series.size(); i++) {
            if (series.get(i).getId() == item.getId()) {
                series.set(i, item);
                return;
            }
        }
        System.out.println("Série não encontrada para atualização.");
    }

    @Override
    public void save(Serie serie) {
        serie.setId(serieId++);  // Atribui um ID único à série antes de salvar
        series.add(serie);
    }

    @Override
    public void delete(Serie serie) {
        series.removeIf(serieItem -> serieItem.getId() == serie.getId());
    }
}
