package guru.springfamework.controllers.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;

@Controller
@RequestMapping("/api/v1/customers/")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("id/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
         return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }
    
    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers() {
        return new ResponseEntity<>(
            new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("firstname/{firstname}")
    public ResponseEntity<CustomerListDTO> getCustomerByFirstname(@PathVariable String firstname) {
        return new ResponseEntity<>(
            new CustomerListDTO(customerService.getCustomerByFirstname(firstname)), HttpStatus.OK);
    }

    @GetMapping("lastname/{lastname}")
    public ResponseEntity<CustomerListDTO> getCustomerByLastname(@PathVariable String lastname) {
        return new ResponseEntity<>(
            new CustomerListDTO(customerService.getCustomerByLastname(lastname)), HttpStatus.OK);
    }

    @GetMapping("name/{firstname}/{lastname}")
    public ResponseEntity<CustomerListDTO> getCustomerByFirstnameAndLastname(@PathVariable String firstname, @PathVariable String lastname) {
        return new ResponseEntity<>(
            new CustomerListDTO(customerService.getCustomerByFirstnameAndLastname(firstname, lastname)), HttpStatus.OK);
    }

}
