package controller.search;

import model.Media;

import java.util.List;

public class SearchResults {
    List<Media> mediaList;
    String message;

    public SearchResults(List<Media> mediaList, String message) {
        this.mediaList = mediaList;
        this.message = message;
    }

    public List<Media> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<Media> mediaList) {
        this.mediaList = mediaList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isEmpty() {
        return mediaList.isEmpty();
    }
}
