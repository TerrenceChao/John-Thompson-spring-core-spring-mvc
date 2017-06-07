package guru.springframework.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import guru.springframework.commands.ProductForm;
import guru.springframework.domain.Product;

@Component
public class ProductFormToProduct implements Converter<ProductForm, Product> {

	@Override
	public Product convert(ProductForm productForm) {

		Product product = new Product();
		
		product.setId(productForm.getProductId());
		product.setVersion(productForm.getProductVersion());
		product.setDescription(productForm.getDescription());
		product.setPrice(productForm.getPrice());
		product.setImageUrl(productForm.getImageUrl());
		
		return product;
	}

}
