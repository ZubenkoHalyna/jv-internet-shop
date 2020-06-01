package com.dev.internet.shop.service.impl;

import com.dev.internet.shop.dao.ProductDao;
import com.dev.internet.shop.lib.Inject;
import com.dev.internet.shop.lib.Service;
import com.dev.internet.shop.model.Product;
import com.dev.internet.shop.service.ProductService;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Inject
    private ProductDao productDao;

    @Override
    public Product create(Product product) {
        return productDao.create(product);
    }

    @Override
    public Product get(Long id) {
        return productDao.get(id).get();
    }

    @Override
    public List<Product> getAll() {
        return productDao.getAll();
    }

    @Override
    public Product update(Product product) {
        return productDao.update(product);
    }

    @Override
    public boolean delete(Long id) {
        return productDao.delete(id);
    }
}
