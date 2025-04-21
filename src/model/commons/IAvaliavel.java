package model.commons;

import model.Avaliacao;
import model.TipoMedia;

import java.time.LocalDateTime;
import java.util.List;

public interface IAvaliavel extends ITemAvaliacao{
    void Avaliar(Avaliacao avaliacao);
    List<Avaliacao> getAvaliacoes();
}
