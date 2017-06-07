package guru.springframework.services.jpaservices;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.commands.ProductForm;
import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;

@Service
@Profile("jpadao")
public class ProductServiceJpaDaoImpl extends AbstractJpaDaoService implements ProductService {
	
	@Override
	public List<Product> listAll() {
		EntityManager em = this.emf.createEntityManager();
		return em.createQuery("from Product", Product.class).getResultList();
	}

	@Override
	public Product getById(Integer id) {
		EntityManager em = this.emf.createEntityManager();
		return em.find(Product.class, id);
	}

	@Override
	public Product saveOrUpdate(Product domainObject) {
		EntityManager em = this.emf.createEntityManager();
		
		em.getTransaction().begin();
		Product savedProduct = em.merge(domainObject);
		em.getTransaction().commit();
		
		return savedProduct;
	}

	@Override
	public void delete(Integer id) {
		EntityManager em = this.emf.createEntityManager();
		
		em.getTransaction().begin();
		em.remove(em.find(Product.class, id));
		em.getTransaction().commit();
	}

	@Override
	public Product saveOrUpdateProductForm(ProductForm productForm) {
		// TODO Auto-generated method stub
		return null;
	}

}
