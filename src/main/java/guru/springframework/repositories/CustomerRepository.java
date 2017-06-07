package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
