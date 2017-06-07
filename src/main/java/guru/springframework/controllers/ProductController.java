package guru.springframework.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import guru.springframework.commands.ProductForm;
import guru.springframework.converters.ProductToProductForm;
import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;

@Controller
@RequestMapping("/product/v1.0")
public class ProductController {

	private ProductService productService;
	private ProductToProductForm productToProductForm;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@Autowired
	public void setProductToProductForm(ProductToProductForm productToProductForm) {
		this.productToProductForm = productToProductForm;
	}

	//read all
	@RequestMapping("/list")
	public String listAll(Model model) {
		model.addAttribute("products", this.productService.listAll());
		return "product/list";
	}
	
	//read
	@RequestMapping("/show/{id}")
	public String show(@PathVariable Integer id, Model model) {
		model.addAttribute("product", this.productService.getById(id));
		return "product/show";
	}
	
	//create
	@RequestMapping("/new")
	public String create(Model model) {
		model.addAttribute("productForm", new ProductForm());
		return "product/productform";
	}
	
	//update
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		
		Product existingProduct = this.productService.getById(id);
		ProductForm productForm = productToProductForm.convert(existingProduct);
		
		model.addAttribute("productForm", productForm);
		return "product/productform";
	}
	
	//confirm create/update
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public String saveOrUpdate(@Valid ProductForm productForm, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "product/productform";
		}
		
		Product savedProduct = this.productService.saveOrUpdateProductForm(productForm);
		return "redirect:/product/v1.0/show/" + savedProduct.getId();
	}
	
	//delete
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable Integer id) {
		this.productService.delete(id);
		return "redirect:/product/v1.0/list";
	}
}
