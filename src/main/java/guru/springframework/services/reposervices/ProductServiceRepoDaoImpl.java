package guru.springframework.services.reposervices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Product;
import guru.springframework.repositories.ProductRepository;
import guru.springframework.services.ProductService;

@Service
@Profile("springdatajpa")
public class ProductServiceRepoDaoImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

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
}
