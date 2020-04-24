package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDao<T, K> {
    T create(T product);

    Optional<T> get(K id);

    List<T> getAll();

    T update(T product);

    boolean delete(K id);
}
