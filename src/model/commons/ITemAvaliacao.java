package model.commons;

import model.Avaliacao;
import model.TipoMedia;

import java.util.List;

public interface ITemAvaliacao extends Identifiable{
    int getPontuacao();
    TipoMedia getTipoMedia();
}
