package model;

import java.time.LocalDateTime;
import java.util.List;

public  class Serie extends MediaAudioVisual{
    private LocalDateTime dataFim;
    private List<Temporada> temporadas;

    @Override
    public int getPontuacao() {
        return 0;
    }

    @Override
    public void Avaliar(LocalDateTime dataAvaliacao, int pontuacao, String review, LocalDateTime dataConsumo) {

    }
}



