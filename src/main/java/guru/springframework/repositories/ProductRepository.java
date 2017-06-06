package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
