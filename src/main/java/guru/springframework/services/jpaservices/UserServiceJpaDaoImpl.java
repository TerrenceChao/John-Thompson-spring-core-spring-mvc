package guru.springframework.services.jpaservices;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.domain.User;
import guru.springframework.services.UserService;
import guru.springframework.services.security.EncryptionService;

@Service
@Profile({"jpadao", "springdatajpa"})
public class UserServiceJpaDaoImpl extends AbstractJpaDaoService implements UserService {

	@Autowired
	private EncryptionService encryptService;
	
	@Override
	public List<User> listAll() {
		EntityManager em = this.emf.createEntityManager();
		return em.createQuery("from User", User.class).getResultList();
	}

	@Override
	public User getById(Integer id) {
		EntityManager em = this.emf.createEntityManager();
		return em.find(User.class, id);
	}

	@Override
	public User saveOrUpdate(User domainObject) {		
		if (domainObject.getPassword() != null) {
			String encryptedPassword = this.encryptService.encryptString(domainObject.getPassword());
			domainObject.setEncryptedPassword(encryptedPassword);
		}
		
		EntityManager em = this.emf.createEntityManager();
		em.getTransaction().begin();
		User savedUser = em.merge(domainObject);
		em.getTransaction().commit();
		
		return savedUser;
	}

	@Override
	public void delete(Integer id) {
		EntityManager em = this.emf.createEntityManager();
		
		em.getTransaction().begin();
		em.remove(em.find(User.class, id));
		em.getTransaction().commit();
	}

}
