package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.ShoppingCart;

@Dao
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
                .filter(order -> order.getUser().getId().equals(userId))
                .findAny();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return Storage.shoppingCarts;
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        delete(shoppingCart.getId());
        Storage.shoppingCarts.add(shoppingCart);
        return shoppingCart;
    }

    @Override
    public boolean delete(Long id) {
        Optional<ShoppingCart> shoppingCart = get(id);
        shoppingCart.ifPresent(Storage.shoppingCarts::remove);
        return shoppingCart.isPresent();
    }
}
