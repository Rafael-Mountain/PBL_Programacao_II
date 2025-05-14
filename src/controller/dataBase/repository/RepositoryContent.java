package controller.dataBase.repository;

import java.util.ArrayList;
import java.util.List;

public class RepositoryContent <t>{
    List<t> listItems;
    int idCounter;

    public RepositoryContent(List<t> listItems, int idCounter) {
        this.listItems = listItems;
        this.idCounter = idCounter;
    }

    public RepositoryContent() {
        this.listItems = new ArrayList<t>();
    }

    public List<t> getListItems() {
        return listItems;
    }

    public int getIdCounter(){
        return idCounter;
    }

    public void setIdCounter(int idCounter) {
        this.idCounter = idCounter;
    }

    public void setListItems(List<t> listItems) {
        this.listItems = listItems;
    }
}
