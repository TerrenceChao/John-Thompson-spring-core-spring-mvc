package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import guru.springframework.commands.ProductForm;
import guru.springframework.domain.Product;

@Component
public class ProductToProductForm implements Converter<Product, ProductForm> {

	@Override
	public ProductForm convert(Product product) {
		
		ProductForm productForm = new ProductForm();
		
		productForm.setProductId(product.getId());
		productForm.setProductVersion(product.getVersion());
		productForm.setDescription(product.getDescription());
		productForm.setPrice(product.getPrice());
		productForm.setImageUrl(product.getImageUrl());
		
		return productForm;
	}

}
