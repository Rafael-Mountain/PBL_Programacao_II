package model;

import java.time.LocalDateTime;
import java.util.List;

public class Serie extends MediaAudioVisual {
    private LocalDateTime dataFim;
    private List<Temporada> temporadas;

    public Serie(String titulo, LocalDateTime dataLancamento, boolean consumido,
                 List<Genero> generos, String tituloOriginal, String localDisponivel,
                 List<String> elenco) {
        super(titulo, dataLancamento, consumido, generos, tituloOriginal, localDisponivel, elenco);
    }

    @Override
    public int getPontuacao() {
        return 0;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(List<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

    public void updateTemporadaById(int id, Temporada temporada) {
        for (int i = 0; i < temporadas.size(); i++) {
            if (temporadas.get(i).getId() == id) {
                temporadas.set(i, temporada);
                break;
            }
        }

    }

    public void getTemporadaById(int id) {
        for (int i = 0; i < temporadas.size(); i++) {
            if (temporadas.get(i).getId() == id) {
                System.out.println(temporadas.get(i));
                break;
            }
        }

    }

    public void addTemporada(Temporada t) {
        temporadas.add(t);
    }


    @Override
    public void Avaliar(LocalDateTime dataAvaliacao, int pontuacao, String review, LocalDateTime dataConsumo) {

    }
}



