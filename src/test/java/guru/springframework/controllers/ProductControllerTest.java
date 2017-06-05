package guru.springframework.controllers;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class ProductControllerTest {

	@Mock //Mockito Mock object
    private ProductService productService;

    @InjectMocks //setups up controller, and injects mock objects into it.
    private ProductController productController;

    private MockMvc mockMvc;
    
    @Before
	public void setup() {
    	MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(productController).build();	
	}
    
    @Test
	public void testListAll() throws Exception {
    	List<Product> products = new ArrayList<>();
    	products.add(new Product());
    	products.add(new Product());
    	
    	//specific Mockito interaction, tell stub to return list of products
        when(productService.listAll()).thenReturn((List) products); //need
        
        this.mockMvc.perform(get("/product/v1.0/list"))
        			.andExpect(status().isOk())
        			.andExpect(view().name("product/list")) //folder path
        			.andExpect(model().attribute("products", hasSize(2)));
        
    }
    
    @Test
    public void testShow() throws Exception{
        Integer id = 1;

        //Tell Mockito stub to return new product for ID 1
        when(productService.getById(id)).thenReturn(new Product());
        
        this.mockMvc.perform(get("/product/v1.0/show/1"))	
        			.andExpect(status().isOk())
        			.andExpect(view().name("product/show")) //folder path
        			.andExpect(model().attribute("product", instanceOf(Product.class)));
        
    }

    @Test
    public void testEdit() throws Exception{
        Integer id = 1;

        //Tell Mockito stub to return new product for ID 1
        when(this.productService.getById(id)).thenReturn(new Product());
        
        this.mockMvc.perform(get("/product/v1.0/edit/1"))	
					.andExpect(status().isOk())
					.andExpect(view().name("product/form")) //folder path
					.andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testNewProduct() throws Exception {
        Integer id = 1;

        //should not call service
        verifyZeroInteractions(productService);
        
        this.mockMvc.perform(get("/product/v1.0/new"))
        	.andExpect(status().isOk())
        	.andExpect(view().name("product/form"))
        	.andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Integer id = 1;
        String description = "Test Description";
        BigDecimal price = new BigDecimal("12.00");
        String imageUrl = "example.com";
        
        Product returnProduct = new Product();
        returnProduct.setId(id);
        returnProduct.setDescription(description);
        returnProduct.setPrice(price);
        returnProduct.setImageUrl(imageUrl);
        
        //compared the returned 
        when(this.productService.saveOrUpdate(Matchers.<Product>any())).thenReturn(returnProduct);
        

        
        this.mockMvc.perform(post("/product/v1.0/saveOrUpdate")
        		.param("id", "1")
        		.param("description", description)
        		.param("price", "12.00")
        		.param("imageUrl", imageUrl))
        			.andExpect(status().is3xxRedirection())
        			.andExpect(view().name("redirect:/product/v1.0/show/1"))
                    .andExpect(model().attribute("product", instanceOf(Product.class)))
                    .andExpect(model().attribute("product", hasProperty("id", is(id))))
                    .andExpect(model().attribute("product", hasProperty("description", is(description))))
                    .andExpect(model().attribute("product", hasProperty("price", is(price))))
                    .andExpect(model().attribute("product", hasProperty("imageUrl", is(imageUrl))));
       
        /**
         * verify properties of bound object??
         * Don't understand here!
         * 
         * Why?? Why?? Why?? Why?? Why?? Why?? Why?? Why?? Why?? Why?? 
         * Why?? Why?? Why?? Why?? Why?? Why?? Why?? Why?? Why?? Why?? 
         * Why?? Why?? Why?? Why?? Why?? Why?? Why?? Why?? Why?? Why?? 
         * 
         */
        ArgumentCaptor<Product> boundProduct = ArgumentCaptor.forClass(Product.class);
        verify(productService).saveOrUpdate(boundProduct.capture());
        
        assertEquals(id, boundProduct.getValue().getId());
        assertEquals(description, boundProduct.getValue().getDescription());
        assertEquals(price, boundProduct.getValue().getPrice());
        assertEquals(imageUrl, boundProduct.getValue().getImageUrl());
    }

    @Test
    public void testDelete() throws Exception{
    	Integer id = 1;

        mockMvc.perform(get("/product/v1.0/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/v1.0/list"));
        
        verify(productService, times(1)).delete(id);
    }
}
