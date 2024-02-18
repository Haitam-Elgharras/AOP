package org.example.service;

import org.example.dao.ProductDao;
import org.example.dao.entities.Product;

import java.util.List;

public class ProductService implements IProductService {
    // autowired with is not raccomanded cause it stole the encapsulation in the context of object-oriented programming
    // instead we use constructor injection and let the setter for later usages
    //@Autowired
    //@Qualifier("pv2")
    private ProductDao productDao;

    // this is a constructor injection
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }


    @Override
    public void addProduct(Product product) {
        productDao.create(product);
    }

    @Override
    public void deleteProductById(int id) {
        productDao.delete(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAll();
    }

    @Override
    public void updateProduct(Product product) {
        productDao.update(product);
    }

    @Override
    public List<Product> searchProductByQuery(String query) {
        return productDao.searchProductByQuery(query);
    }

    public void setDao(ProductDao dao) {
        this.productDao =dao;
    }
}
