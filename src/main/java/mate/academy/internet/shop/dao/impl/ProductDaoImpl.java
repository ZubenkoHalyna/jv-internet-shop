package mate.academy.internet.shop.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internet.shop.dao.ProductDao;
import mate.academy.internet.shop.db.Storage;
import mate.academy.internet.shop.model.Order;
import mate.academy.internet.shop.model.Product;
import mate.academy.internet.shop.model.ShoppingCart;

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
        Product oldProduct = get(product.getId()).get();
        if (!oldProduct.getName().equals(product.getName())) {
            oldProduct.setName(product.getName());
        }
        if (!oldProduct.getPrice().equals(product.getPrice())) {
            oldProduct.setPrice(product.getPrice());
        }
        return oldProduct;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Product> product = get(id);
        product.ifPresent(Storage.products::remove);
        return product.isPresent();
    }

    @Override
    public List<Product> getByOrder(Long orderId) {
        return Storage.orders.stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .map(Order::getProducts)
                .orElse(new ArrayList<>());
    }

    @Override
    public List<Product> getByShoppingCart(Long orderId) {
        return Storage.shoppingCarts.stream()
                .filter(cart -> cart.getId().equals(orderId))
                .findFirst()
                .map(ShoppingCart::getProducts)
                .orElse(new ArrayList<>());
    }
}
