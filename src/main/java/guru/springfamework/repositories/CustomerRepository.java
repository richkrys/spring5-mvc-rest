package guru.springfamework.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import guru.springfamework.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Optional<Customer> findById(Long id);
	List<Customer> findByFirstname(String firstname);
	List<Customer> findByLastname(String lastname);
	List<Customer> findByFirstnameAndLastname(String firstname, String lastname);
}
