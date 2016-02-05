package us.roff.springtutorial.services;

import java.util.List;

import us.roff.springtutorial.domain.Product;

public interface ProductService {

	List<Product> listAllProducts();
	
	Product getProductById(Integer id);
	
	Product saveOrUpdateProduct(Product product);
	
	void deleteProductById(Integer id);
}
