package guru.springfamework.services;

import java.util.List;

import guru.springfamework.api.v1.model.CustomerDTO;

public interface CustomerService {
	public CustomerDTO getCustomerById(Long id);
	public List<CustomerDTO> getAllCustomers();
	public List<CustomerDTO> getCustomerByFirstname(String firstname);
	public List<CustomerDTO> getCustomerByLastname(String lastname);
	public List<CustomerDTO> getCustomerByFirstnameAndLastname(String firstname, String lastname);
}
