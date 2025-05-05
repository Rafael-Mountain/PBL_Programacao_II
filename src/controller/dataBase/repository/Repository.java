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
    private ObjectMapper objectMapper;


    public Repository(String nameItem) {
        this.itemsList = new ArrayList<>();
        this.itemId = 0;
        this.nameItem = nameItem;
        objectMapper = new ObjectMapper(); // Inicializa aqui
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
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
    }

    @Override
    public void save() {
        //TODO: implementar o throw
        try {
            RepositoryContent repositoryContent = new RepositoryContent(itemsList, itemId);
            FileManagement fileManagement = new FileManagement(nameItem);
            fileManagement.dump(repositoryContent);
        }
        catch (Exception e) {
            System.out.println("Erro ao gravar os " + nameItem + "s : " + e.getMessage());
        }
    }

    @Override
    public void load() {
        //TODO: implementar o load
    }

    @Override
    public void clear() {
        itemsList.clear();
        itemId = 0;
    }
}
