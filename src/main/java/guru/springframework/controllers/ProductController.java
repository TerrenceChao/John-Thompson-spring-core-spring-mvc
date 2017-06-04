package guru.springframework.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;

@Controller
@RequestMapping("/product/v1.0")
public class ProductController {

	private ProductService productService;
	
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
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
		model.addAttribute("product", new Product());
		return "product/form";
	}
	
	//update
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		model.addAttribute("product", this.productService.getById(id));
		return "product/form";
	}
	
	//confirm create/update
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public String saveOrUpdate(Product product) {
		Product savedProduct = this.productService.saveOrUpdate(product);
		System.out.println("\n#################\n Save or Update\n#################\n");
		return "redirect:/product/v1.0/show/" + savedProduct.getId();
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable Integer id) {
		this.productService.delete(id);
		return "redirect:/product/v1.0/list";
	}
}
