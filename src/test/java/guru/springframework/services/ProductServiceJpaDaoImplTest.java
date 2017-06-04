package guru.springframework.services;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpadao")
public class ProductServiceJpaDaoImplTest {

	private ProductService productService;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Test
	public void teswtListAllMethod() throws Exception {
		
		List<Product> products = this.productService.listAll();	
		assert products.size() == 5;
	}
	
	@Test
	public void testOtherMethods() throws Exception {

		assert this.productService.listAll().size() == 5;
		
		//init new product's value:
		final String newDescription = "new product descrip...";
		final BigDecimal newPrice = new BigDecimal("123.456");
		final String newImgUrl = "http://xxx.xxx.org/newProduct";
		
		
		Product newProduct = new Product();
		newProduct.setDescription(newDescription);
		newProduct.setPrice(newPrice);
		newProduct.setImageUrl(newImgUrl);
		
		Product savedProduct = this.productService.saveOrUpdate(newProduct);
		Integer newProductID = savedProduct.getId();
		
		//test save
		assert savedProduct.getDescription() == newDescription;
		assert savedProduct.getPrice() == newPrice;
		assert savedProduct.getImageUrl() == newImgUrl;
		assert this.productService.listAll().size() == 6;
		
		
		//test update and getId
		final String modifiedDescription = "xoxoxoxoxoxoxoxo...";
		final BigDecimal modifiedPrice = new BigDecimal("654.321");
		final String modifiedImgUrl = "~X__X~";
		savedProduct.setDescription(modifiedDescription);
		savedProduct.setPrice(modifiedPrice);
		savedProduct.setImageUrl(modifiedImgUrl);
		Product modifiedProduct = this.productService.saveOrUpdate(savedProduct);
		
		//test update
		assert modifiedProduct.getDescription() == modifiedDescription;
		assert modifiedProduct.getPrice() == modifiedPrice;
		assert modifiedProduct.getImageUrl() == modifiedImgUrl;
		//test getId
		assert modifiedProduct.getId() == newProductID;
		
		//test delete
		Product deletedProduct = this.productService.getById(newProductID);
		assert deletedProduct.getId() == newProductID;
		this.productService.delete(newProductID);
		assert this.productService.getById(newProductID) == null;
		assert this.productService.listAll().size() == 5;
	}
}
