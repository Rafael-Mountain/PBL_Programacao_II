package controller.dataBase;

import java.util.List;

public interface IRepository<T> {
    List<T> getItems();
    T getItemById(int id);
    void update(T item);
    void save(T item);
    void delete(T item);
}