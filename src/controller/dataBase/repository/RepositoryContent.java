package controller.dataBase.repository;

import java.util.List;

public class RepositoryContent <t>{
    List<t> listItems;
    int idCounter;

    public RepositoryContent(List<t> listItems, int idCounter) {
        this.listItems = listItems;
        this.idCounter = idCounter;
    }
    public List<t> getListItems() {
        return listItems;
    }

    public int getIdCounter(){
        return idCounter;
    }
}
