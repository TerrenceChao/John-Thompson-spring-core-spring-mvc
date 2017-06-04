package guru.springframework.services.mapservices;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;

@Profile("map")
@Service
public class ProductServiceImpl implements ProductService {

	private Map<Integer, Product> products;
	
	public ProductServiceImpl() {
		loadProducts();
	}
	
	private void loadProducts() {
		products = new HashMap<>();
		
		Product p1 = new Product();
		p1.setId(1);
		p1.setDescription("Product 1");
		p1.setPrice(new BigDecimal("12.99"));
		p1.setImageUrl("http://www.ex.com/product1");
		
		products.put(1, p1);
		
		Product p2 = new Product();
		p2.setId(2);
		p2.setDescription("Product 2");
		p2.setPrice(new BigDecimal("13.99"));
		p2.setImageUrl("http://www.ex.com/product2");
		
		products.put(2, p2);
		
		Product p3 = new Product();
		p3.setId(3);
		p3.setDescription("Product 3");
		p3.setPrice(new BigDecimal("14.99"));
		p3.setImageUrl("http://www.ex.com/product3");
		
		products.put(3, p3);
		
		Product p4 = new Product();
		p4.setId(4);
		p4.setDescription("Product 4");
		p4.setPrice(new BigDecimal("15.99"));
		p4.setImageUrl("http://www.ex.com/product4");
		
		products.put(4, p4);
		
	}
	
	@Override
	public List<Product> listAll() {
		return new ArrayList<>(products.values());
	}

	@Override
	public Product getById(Integer id) {
		return products.get(id);
	}

	@Override
	public Product saveOrUpdate(Product product) {
		if (product == null) {
			System.out.println("Product is Empty");
			return null;
		}
		
		if (product.getId() == null) {
			product.setId(products.size());
		}
		
		this.products.put(product.getId(), product);
		return this.products.get(product.getId());
	}

	@Override
	public void delete(Integer id) {
		products.remove(id);
	}

}
