package model;

import java.time.LocalDateTime;
import java.util.List;

public class Serie extends MediaAudioVisual {
    private int temporadaId = 0;
    private LocalDateTime dataFim;
    private List<Temporada> temporadas;
    private TipoMedia tipoMedia = TipoMedia.SERIE;

    public Serie(String titulo, LocalDateTime dataLancamento, boolean consumido,
                 List<Genero> generos, String tituloOriginal, String localDisponivel,
                 List<String> elenco, LocalDateTime dataFim) {
        super(titulo, dataLancamento, consumido, generos, tituloOriginal, localDisponivel, elenco);
        this .dataFim = dataFim;
        this.temporadas = new java.util.ArrayList<>();
    }

    @Override
    public double getPontuacao() {
        if (!temporadas.isEmpty()) {
            return temporadas.stream()
                    .mapToDouble(Temporada::getPontuacao)
                    .sum() / temporadas.size();
        } else {
            return 0;
        }
    }

    public TipoMedia getTipoMedia() {
        return this.tipoMedia;
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
        this.temporadas = temporadas.stream()
                .peek(temporada -> {
                    temporada.setId(temporadaId++);
                    temporada.setSerieId(getId());
                })
                .collect(java.util.stream.Collectors.toList());
    }

    public void updateTemporadaById(int id, Temporada temporada) {
        for (int i = 0; i < temporadas.size(); i++) {
            if (temporadas.get(i).getId() == id) {
                temporadas.set(i, temporada);
                break;
            }
        }

    }

    public Temporada getTemporadaById(int id) {
        for (int i = 0; i < temporadas.size(); i++) {
            if (temporadas.get(i).getId() == id) {
                return temporadas.get(i);
            }
        }
        return null;
    }

    public void addTemporada(Temporada t) {
        t.setId(temporadaId++);
        t.setSerieId(getId());
        temporadas.add(t);
    }

}



