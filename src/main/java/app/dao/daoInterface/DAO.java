package app.dao.daoInterface;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    Optional<T> save(T obj);
    Optional<Boolean> delete(T obj);
    void deleteAll(List<T> entities);
    Optional<List<T>> saveAll(List<T> entities);
    Optional<List<T>> findAll();
    Optional<Boolean> deleteById(long id);
    Optional<T> getOne(long id);
}
