package guru.springframework.services.reposervices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.commands.ProductForm;
import guru.springframework.converters.ProductFormToProduct;
import guru.springframework.domain.Product;
import guru.springframework.repositories.ProductRepository;
import guru.springframework.services.ProductService;

@Service
@Profile("springdatajpa")
public class ProductServiceRepoDaoImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductFormToProduct productFormToProduct;

	@Override
	public List<Product> listAll() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add); //fun with Java 8
		return products;
	}

	@Override
	public Product getById(Integer id) {
		return productRepository.findOne(id);
	}

	@Override
	public Product saveOrUpdate(Product domainObject) {
		return productRepository.save(domainObject);
	}

	@Override
	public void delete(Integer id) {
		productRepository.delete(id);
	}

	@Override
	public Product saveOrUpdateProductForm(ProductForm productForm) {

		if (productForm.getProductId() != null) {
			Product existingProduct = getById(productForm.getProductId());
			
			existingProduct.setVersion(productForm.getProductVersion());
			existingProduct.setDescription(productForm.getDescription());
			existingProduct.setPrice(productForm.getPrice());
			existingProduct.setImageUrl(productForm.getImageUrl());
			
			return saveOrUpdate(existingProduct);
			
		} else {
			return saveOrUpdate(this.productFormToProduct.convert(productForm));
		}
	}
}
