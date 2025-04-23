package controller.filter;

import model.Media;

import java.util.List;

public abstract class Filter {
    protected List<Media> medias;

    //Todo : Implementar o uso de mensagens
    private String message;

    abstract void apply();

    List<Media> getMedias() {
        return medias;
    }

    void setMedias(List<Media> medias) {
        this.medias = medias;
    }

    String getMessage() {
        return message;
    }


}
