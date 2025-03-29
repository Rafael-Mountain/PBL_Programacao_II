package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class MediaAudioVisual extends Media {
    private List<String> elenco;
    private String tituloOriginal;
    private String localDisponivel;

    // Construtor padrão
    public MediaAudioVisual() {
        super();
        this.elenco = new ArrayList<>();
    }

    // Construtor com parâmetros
    public MediaAudioVisual(String titulo, LocalDateTime dataLancamento, boolean consumido, List<Genero> generos,
                            String tituloOriginal, String localDisponivel, List<String> elenco) {

        super(titulo, dataLancamento,consumido,generos); // Chama o construtor da classe pai
        this.tituloOriginal = tituloOriginal;
        this.localDisponivel = localDisponivel;
        this.elenco = (elenco != null) ? new ArrayList<>(elenco) : new ArrayList<>();
    }

    public List<String> getElenco() {
        return new ArrayList<>(elenco); // Retorna uma cópia para evitar modificações externas
    }

    public void setElenco(List<String> elenco) {
        if (elenco != null) {
            this.elenco = new ArrayList<>(elenco);
        }
    }

    public void addElenco(String ator) {
        if (ator != null && !ator.trim().isEmpty()) {
            this.elenco.add(ator);
        }
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
