package model.commons;

import model.TipoMedia;

public interface ITemAvaliacao extends Identifiable{
    double getPontuacao();
    TipoMedia getTipoMedia();
}
