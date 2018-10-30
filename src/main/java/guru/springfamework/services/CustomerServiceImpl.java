package guru.springfamework.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerMapper customerMapper;
	private final CustomerRepository customerRepository;

	@Autowired
	public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
		this.customerMapper = customerMapper;
		this.customerRepository = customerRepository;
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		return customer.isPresent() ? customerMapper.customerToCustomerDTO(customer.get()) : null;
	}

	@Override
	public List<CustomerDTO> getAllCustomers() {
		return customerRepository.findAll().stream().map(customerMapper::customerToCustomerDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<CustomerDTO> getCustomerByFirstname(String firstname) {
		return customerRepository.findByFirstname(firstname).stream().map(customerMapper::customerToCustomerDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<CustomerDTO> getCustomerByLastname(String lastname) {
		return customerRepository.findByLastname(lastname).stream().map(customerMapper::customerToCustomerDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<CustomerDTO> getCustomerByFirstnameAndLastname(String firstname, String lastname) {
		return customerRepository.findByFirstnameAndLastname(firstname, lastname).stream().map(customerMapper::customerToCustomerDTO)
				.collect(Collectors.toList());
	}
}
