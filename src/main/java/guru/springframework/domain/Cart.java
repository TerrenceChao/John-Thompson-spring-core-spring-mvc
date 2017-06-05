package guru.springframework.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class Cart extends AbstractDomainClass {
	
	@OneToOne
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="cart", orphanRemoval = true)
	private List<CartDetail> carDetails = new ArrayList<>();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<CartDetail> getCarDetails() {
		return carDetails;
	}

	public void setCarDetails(List<CartDetail> carDetails) {
		this.carDetails = carDetails;
	}
	
	public void addCartDetail(CartDetail cartDetail) {
		cartDetail.setCart(this);
		this.carDetails.add(cartDetail);
	}
	
	public void removeCartDetail(CartDetail cartDetail) {
		cartDetail.setCart(null);
		this.carDetails.remove(cartDetail);
	}
}
