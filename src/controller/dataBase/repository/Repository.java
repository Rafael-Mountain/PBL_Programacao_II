package controller.dataBase.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import controller.dataBase.file.FileManagement;
import model.commons.Identifiable;

import java.util.ArrayList;
import java.util.List;

public abstract class Repository<t extends Identifiable> implements IRepository<t> {
    private List<t> itemsList;
    private int itemId;
    private String nameItem;


    public Repository(String nameItem) {
        this.itemsList = new ArrayList<>();
        this.itemId = 0;
        this.nameItem = nameItem;
        this.load();
    }

    @Override
    public List<t> getItems() {
        return itemsList;
    }

    @Override
    public t getItemById(int id) {
        for ( t item : itemsList) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override
    public void update(t item) {
        for (int i = 0; i < itemsList.size(); i++) {
            if (itemsList.get(i).getId() == item.getId()) {
                itemsList.set(i, item);
                this.save();
                return;
            }
        }
        throw new RuntimeException(nameItem + " não encontrado para atualização");
    }

    @Override
    public void add(t item) {
        item.setId(itemId++);
        itemsList.add(item);
        this.save();
    }

    @Override
    public void delete(t item) {
        itemsList.removeIf(listItem -> listItem.getId() == item.getId());
        this.save();
    }

    @Override
    public void save() {
        //TODO: implementar o throw
        try {
            RepositoryContent repositoryContent = new RepositoryContent(itemsList, itemId);
            FileManagement fileManagement = new FileManagement(nameItem, getItemClass());
            fileManagement.dump(repositoryContent);
        }
        catch (Exception e) {
            System.out.println("Erro ao gravar os " + nameItem + "s : " + e.getMessage());
        }
    }

    @Override
    public void load() {
        try {
            FileManagement<t> fileManagement = new FileManagement<>(nameItem, getItemClass());
            RepositoryContent<t> repositoryContent = fileManagement.load();

            this.itemsList = repositoryContent.getListItems();
            this.itemId = repositoryContent.getIdCounter();
            System.out.println(repositoryContent.getListItems() + " " + repositoryContent.getIdCounter());
        } catch (Exception e) {
            System.out.println("Erro ao carregar os " + nameItem + "s : " + e.getMessage());
        }
    }

    @Override
    public void clear() {
        itemsList.clear();
        itemId = 0;
    }
}
