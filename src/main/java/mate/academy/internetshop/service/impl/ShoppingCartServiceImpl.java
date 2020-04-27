package mate.academy.internetshop.service.impl;

import java.util.List;
import mate.academy.internetshop.dao.ShoppingCartDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Product;
import mate.academy.internetshop.model.ShoppingCart;
import mate.academy.internetshop.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    ShoppingCartDao shoppingCartDao;
    @Inject
    UserDao userDao;

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        if (shoppingCart.getId() == null) {
            shoppingCartDao.create(shoppingCart);
        }
        shoppingCart.getProducts().add(product);
        return shoppingCartDao.update(shoppingCart);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        boolean productPresent = shoppingCart.getProducts().contains(product);
        shoppingCart.getProducts().remove(product);
        shoppingCartDao.update(shoppingCart);
        return productPresent;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getProducts().clear();
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartDao.getByUser(userId)
                .orElse(new ShoppingCart(List.of(), userDao.get(userId).get()));
    }

    @Override
    public List<Product> getAllProducts(ShoppingCart shoppingCart) {
        return shoppingCartDao.get(shoppingCart.getId())
                .orElse(shoppingCart)
                .getProducts();
    }

    @Override
    public ShoppingCart get(Long id) {
        return shoppingCartDao.get(id).get();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return shoppingCartDao.getAll();
    }

    @Override
    public boolean delete(Long id) {
        return shoppingCartDao.delete(id);
    }
}
