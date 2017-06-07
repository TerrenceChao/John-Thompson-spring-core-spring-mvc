package guru.springframework.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.domain.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
