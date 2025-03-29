package model.commons;

import java.time.LocalDateTime;

public interface IAvaliavel {
    int getPontuacao();
    void Avaliar(LocalDateTime dataAvaliacao, int pontuacao, String review, LocalDateTime dataConsumo);
}
