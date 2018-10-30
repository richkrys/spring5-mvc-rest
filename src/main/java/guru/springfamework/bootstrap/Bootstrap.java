package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(Bootstrap.class);

	private CategoryRepository categoryRespository;
	private CustomerRepository customerRepository;

	public Bootstrap(CategoryRepository categoryRespository, CustomerRepository customerRepository) {
		this.categoryRespository = categoryRespository;
		this.customerRepository = customerRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		// init H2 categories
		Category fruits = new Category();
		fruits.setName("Fruits");

		Category dried = new Category();
		dried.setName("Dried");

		Category fresh = new Category();
		fresh.setName("Fresh");

		Category exotic = new Category();
		exotic.setName("Exotic");

		Category nuts = new Category();
		nuts.setName("Nuts");

		categoryRespository.save(fruits);
		categoryRespository.save(dried);
		categoryRespository.save(fresh);
		categoryRespository.save(exotic);
		categoryRespository.save(nuts);

		// init H2 customers
		customerRepository.save(createCustomer(1l, "Joe", "Newman"));
		customerRepository.save(createCustomer(2l, "Michael", "Lachappele"));
		customerRepository.save(createCustomer(7l, "David", "Winter"));
		customerRepository.save(createCustomer(102l, "Anne", "Hine"));
		customerRepository.save(createCustomer(342l, "Alice", "Eastman"));
		customerRepository.save(createCustomer(343l, "Ramazan", "Demir"));
		customerRepository.save(createCustomer(344l, "Ramazan", "Demir"));

		if (LOG.isInfoEnabled()) {
			LOG.info(String.format("Categories Loaded = %1d", categoryRespository.count()));
			LOG.info(String.format("Customers Loaded = %1d", customerRepository.count()));
		}
	}

	private Customer createCustomer(Long id, String firstname, String lastname) {
		Customer customer = new Customer();
		customer.setFirstname(firstname);
		customer.setId(id);
		customer.setLastname(lastname);
		return customer;
	}
}
