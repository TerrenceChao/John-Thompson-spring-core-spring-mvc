package guru.springframework.bootstrap;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;

@Component
public class SpringJPABootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private ProductService productService;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		loadProducts();
	}
	
	public void loadProducts() {
		Product p1 = new Product();
		p1.setDescription("Product JPA 1");
		p1.setPrice(new BigDecimal("11.99"));
		p1.setImageUrl("http://www.ex.com/product1");
		this.productService.saveOrUpdate(p1);
		
		Product p2 = new Product();
		p2.setDescription("Product JPA 2");
		p2.setPrice(new BigDecimal("23.99"));
		p2.setImageUrl("http://www.ex.com/product2");
		this.productService.saveOrUpdate(p2);
		
		Product p3 = new Product();
		p3.setDescription("Product JPA 3");
		p3.setPrice(new BigDecimal("33.99"));
		p3.setImageUrl("http://www.ex.com/product3");
		this.productService.saveOrUpdate(p3);
		
		Product p4 = new Product();
		p4.setDescription("Product JPA 4");
		p4.setPrice(new BigDecimal("44.99"));
		p4.setImageUrl("http://www.ex.com/product4");
		this.productService.saveOrUpdate(p4);
		
		Product p5 = new Product();
		p5.setDescription("Product JPA 5");
		p5.setPrice(new BigDecimal("55.99"));
		p5.setImageUrl("http://www.ex.com/product5");
		this.productService.saveOrUpdate(p5);
	}

}
