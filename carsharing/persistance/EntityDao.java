package carsharing.persistance;

import java.util.List;

public  interface EntityDao<T> {
    List<T> findAll();
    T findById(int id);
    void add(T entity);
    void update(T entity);
    void deleteById(int id);
}
