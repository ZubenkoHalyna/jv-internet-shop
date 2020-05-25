package mate.academy.internet.shop.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internet.shop.dao.ShoppingCartDao;
import mate.academy.internet.shop.db.Storage;
import mate.academy.internet.shop.model.ShoppingCart;

public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        Storage.addNewShoppingCart(shoppingCart);
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        return Storage.shoppingCarts.stream()
                .filter(it -> it.getId().equals(id))
                .findAny();
    }

    @Override
    public Optional<ShoppingCart> getByUser(Long userId) {
        return Storage.shoppingCarts.stream()
                .filter(order -> order.getUserId().equals(userId))
                .findAny();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return Storage.shoppingCarts;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        ShoppingCart oldShoppingCart = get(shoppingCart.getId()).get();
        if (!oldShoppingCart.getUserId().equals(shoppingCart.getUserId())) {
            oldShoppingCart.setUserId(shoppingCart.getUserId());
        }
        if (!oldShoppingCart.getProducts().equals(shoppingCart.getProducts())) {
            oldShoppingCart.setProducts(new ArrayList<>(shoppingCart.getProducts()));
        }
        return oldShoppingCart;
    }

    @Override
    public boolean delete(Long id) {
        Optional<ShoppingCart> shoppingCart = get(id);
        shoppingCart.ifPresent(Storage.shoppingCarts::remove);
        return shoppingCart.isPresent();
    }
}