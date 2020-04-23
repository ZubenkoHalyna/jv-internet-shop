package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ProductDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Product;

@Dao
public class ProductDaoImpl implements ProductDao {
    @Override
    public Product create(Product product) {
        Storage.addNewProduct(product);
        return product;
    }

    @Override
    public Optional<Product> get(Long id) {
        return Storage.products.stream()
                .filter(it -> it.getId().equals(id))
                .findAny();
    }

    @Override
    public List<Product> getAll() {
        return Storage.products;
    }

    @Override
    public Product update(Product product) {
        delete(product.getId());
        Storage.products.add(product);
        return product;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Product> product = get(id);
        product.ifPresent(Storage.products::remove);
        return product.isPresent();
    }
}
