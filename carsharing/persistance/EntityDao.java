package carsharing.persistance;

import java.util.List;

public  abstract class EntityDao<T> {
    abstract public List<T> findAll();
    abstract public T findById(int id);
    abstract public void add(T entity);
    abstract public void update(T entity);
    abstract public void deleteById(int id);
}
