package guru.springfamework.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;

public class CustomerServiceTest {

	private static final String LASTNAME = "lastname";
	private static final String FIRSTNAME = "firstname";
	private static final Long LONG_ID_1 = 1l;

	CustomerServiceImpl customerService;

	@Mock
	CustomerRepository customerRepository;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
	}

	@Test
	public void getCustomerById() throws Exception {

		// given
		Customer customer = createCustomer();
		when(customerRepository.findById(LONG_ID_1)).thenReturn(Optional.of(customer));

		// when
		CustomerDTO customerDTO = customerService.getCustomerById(LONG_ID_1);

		// then
		assert(LONG_ID_1.equals(customerDTO.getId()));
		assert(FIRSTNAME.equals(customerDTO.getFirstname()));
		assert(LASTNAME.equals(customerDTO.getLastname()));
	}

	@Test
	public void getAllCustomers() throws Exception {

		// given
		when(customerRepository.findAll()).thenReturn(createCustomerList());

		// when
		List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

		// then
		assertEquals(3, customerDTOS.size());
		for (CustomerDTO customer : customerDTOS) {
			assertEquals(FIRSTNAME, customer.getFirstname());
			assertEquals(LASTNAME, customer.getLastname());
		}
	}
	
	@Test
	public void getCustomersByFirstname() throws Exception {

		// given
		when(customerRepository.findByFirstname(FIRSTNAME)).thenReturn(createCustomerList());

		// when
		List<CustomerDTO> customerDTOS = customerService.getCustomerByFirstname(FIRSTNAME);

		// then
		assertEquals(3, customerDTOS.size());
		for (CustomerDTO customer : customerDTOS) {
			assertEquals(FIRSTNAME, customer.getFirstname());
			assertEquals(LASTNAME, customer.getLastname());
		}
		
		// when
		customerDTOS = customerService.getCustomerByFirstname(LASTNAME);
		
		// then
		assertEquals(0, customerDTOS.size());
	}
	
	@Test
	public void getCustomersByLastname() throws Exception {

		// given
		when(customerRepository.findByLastname(LASTNAME)).thenReturn(createCustomerList());

		// when
		List<CustomerDTO> customerDTOS = customerService.getCustomerByLastname(LASTNAME);

		// then
		assertEquals(3, customerDTOS.size());
		for (CustomerDTO customer : customerDTOS) {
			assertEquals(FIRSTNAME, customer.getFirstname());
			assertEquals(LASTNAME, customer.getLastname());
		}
		
		// when
		customerDTOS = customerService.getCustomerByLastname(FIRSTNAME);
		
		// then
		assertEquals(0, customerDTOS.size());
	}
	
	@Test 
	public void getCustomerByFirstnameAndLastname() throws Exception {
		
		// given
		when(customerRepository.findByFirstnameAndLastname(FIRSTNAME, LASTNAME)).thenReturn(createCustomerList());
		
		// when
		List<CustomerDTO> customerDTOS = customerService.getCustomerByFirstnameAndLastname(FIRSTNAME, LASTNAME);
		
		// then
		assertEquals(3, customerDTOS.size());
		for (CustomerDTO customer : customerDTOS) {
			assertEquals(FIRSTNAME, customer.getFirstname());
			assertEquals(LASTNAME, customer.getLastname());
		}
	}
	
	private Customer createCustomer() {
		Customer customer = new Customer();
		customer.setId(LONG_ID_1);
		customer.setFirstname(FIRSTNAME);
		customer.setLastname(LASTNAME);
		return customer;
	}

	private List<Customer> createCustomerList() {
		List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
		long id = LONG_ID_1;
		for (Customer customer : customers) {
			customer.setId(id++);
			customer.setFirstname(FIRSTNAME);
			customer.setLastname(LASTNAME);
		}
		return customers;
	}
}