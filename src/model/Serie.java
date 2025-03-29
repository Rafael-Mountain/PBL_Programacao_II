package model;

import java.time.LocalDateTime;
import java.util.List;

public class Serie extends MediaAudioVisual{
    private LocalDateTime dataFim;
    private List<Temporada> temporadas;

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

    @Override
    public void Avaliar(LocalDateTime dataAvaliacao, int pontuacao, String review, LocalDateTime dataConsumo) {

    }
}



