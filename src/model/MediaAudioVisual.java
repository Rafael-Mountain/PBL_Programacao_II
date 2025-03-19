package model;

import java.util.List;

public class MediaAudioVisual extends Media{
    private List<String> elenco;
    private String tituloOriginal;
    private String localDisponivel;

    public List<String> getElenco() {
        return elenco;
    }

    public void setElenco(List<String> elenco) {
        this.elenco = elenco;
    }

    public String getTituloOriginal() {
        return tituloOriginal;
    }

    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
    }

    public String getLocalDisponivel() {
        return localDisponivel;
    }

    public void setLocalDisponivel(String localDisponivel) {
        this.localDisponivel = localDisponivel;
    }
}
