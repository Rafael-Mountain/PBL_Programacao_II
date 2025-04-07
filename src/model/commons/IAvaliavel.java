package model.commons;

import model.Avaliacao;
import model.TipoMedia;

import java.time.LocalDateTime;

public interface IAvaliavel extends Identifiable {
    int getPontuacao();
    TipoMedia getTipoMedia();
    void Avaliar(Avaliacao avaliacao);
}
