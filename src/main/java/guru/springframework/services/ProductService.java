package guru.springframework.services;

import guru.springframework.commands.ProductForm;
import guru.springframework.domain.Product;

public interface ProductService extends CRUDService<Product> {
	Product saveOrUpdateProductForm(ProductForm productForm);
}
