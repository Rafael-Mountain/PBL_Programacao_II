package model;

import java.time.LocalDateTime;

public class Temporada  implements  IAvaliavel{
    private LocalDateTime ano;
    private int qEpisodios;

    @Override
    public int getPontuacao() {
        return 0;
    }

    @Override
    public void Avaliar(LocalDateTime dataAvaliacao, int pontuacao, String review, LocalDateTime dataConsumo) {

    }
}

