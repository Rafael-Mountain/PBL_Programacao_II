package model;

import java.util.Date;

public interface IAvaliavel {
    int getPontuacao();
    void Avaliar(Date dataAvaliacao, int pontuacao, String review, Date dataConsumo);
}
