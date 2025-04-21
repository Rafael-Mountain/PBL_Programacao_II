package model.commons;

import model.Avaliacao;
import model.TipoMedia;

import java.time.LocalDateTime;
import java.util.List;

public interface IAvaliavel extends Identifiable {
    int getPontuacao();
    TipoMedia getTipoMedia();
    List<Avaliacao> getAvaliacoes();
    void Avaliar(Avaliacao avaliacao);
}
