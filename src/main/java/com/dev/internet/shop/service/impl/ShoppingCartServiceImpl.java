package com.dev.internet.shop.service.impl;

import com.dev.internet.shop.dao.ShoppingCartDao;
import com.dev.internet.shop.dao.UserDao;
import com.dev.internet.shop.lib.Inject;
import com.dev.internet.shop.lib.Service;
import com.dev.internet.shop.model.Product;
import com.dev.internet.shop.model.ShoppingCart;
import com.dev.internet.shop.service.ShoppingCartService;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;
    @Inject
    private UserDao userDao;

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
                .orElse(new ShoppingCart(List.of(), userId));
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
