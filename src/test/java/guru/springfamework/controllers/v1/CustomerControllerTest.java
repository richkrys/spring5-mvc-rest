package guru.springfamework.controllers.v1;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;

public class CustomerControllerTest {

    private static final String LAST_NAME = "last name";

    private static final String FIRST_NAME = "firstname";

    private static final Long ID = 1l;

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
            .build();
    }

    @Test
    public void getCustomerById() throws Exception {
        when(customerService.getCustomerById(ID)).thenReturn(createCustomerDTO());

        mockMvc.perform(get("/api/v1/customers/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", equalTo(ID)))
            .andExpect(jsonPath("$.firstname", equalTo(FIRST_NAME)))
            .andExpect(jsonPath("$.lastname", equalTo(LAST_NAME)));
    }

    @Test
    public void getAllCustomers() throws Exception {
        List<CustomerDTO> customers = createCustomerDTOList();

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customers/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.customers", hasSize(3)));
    }
    
    @Test
    public void getCustomerByFirstname() throws Exception {
        List<CustomerDTO> customers = createCustomerDTOList();

        when(customerService.getCustomerByFirstname(FIRST_NAME)).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customers/firstname/" + FIRST_NAME)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.customers", hasSize(3)));
    }
    
    @Test
    public void getCustomerByLastname() throws Exception {
        List<CustomerDTO> customers = createCustomerDTOList();

        when(customerService.getCustomerByFirstname(LAST_NAME)).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customers/lastname/" + LAST_NAME)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.customers", hasSize(3)));
    }
    
    @Test
    public void getCustomerByFirstnameAndLastname() throws Exception {
        List<CustomerDTO> customers = createCustomerDTOList();

        when(customerService.getCustomerByFirstnameAndLastname(FIRST_NAME, LAST_NAME)).thenReturn(customers);

        mockMvc.perform(get("/api/v1/customers/name/" + FIRST_NAME + "/" + LAST_NAME)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.customers", hasSize(3)));
    }
    
    private CustomerDTO createCustomerDTO() {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(ID);
        dto.setFirstname(FIRST_NAME);
        dto.setLastname(LAST_NAME);
        return dto;
    }

    private List<CustomerDTO> createCustomerDTOList() {
        List<CustomerDTO> customers = Arrays.asList(new CustomerDTO(), new CustomerDTO(), new CustomerDTO());
        Long id = 1l;
        for (CustomerDTO dto : customers) {
            dto.setId(id++);
            dto.setFirstname(FIRST_NAME);
            dto.setLastname(LAST_NAME);
        }
        return customers;
    }
}
