package mate.academy.internetshop.service;

import java.util.List;

public interface BaseService<T, K> {
    T get(K id);

    List<T> getAll();

    boolean delete(K id);
}
