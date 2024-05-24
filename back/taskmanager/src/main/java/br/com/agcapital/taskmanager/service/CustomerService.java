package br.com.agcapital.taskmanager.service;

import br.com.agcapital.taskmanager.dto.CustomerRequestDTO;
import br.com.agcapital.taskmanager.entity.Customer;
import br.com.agcapital.taskmanager.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(CustomerRequestDTO req) {
        if (req.getId() != null) {
            Customer customer = findById(req.getId());
            if (customer != null) {
                customer.setCustomerName(req.getCustomerName());
                return customerRepository.save(customer);
            }
        }
        Customer customer = new Customer();
        customer.setCustomerName(req.getCustomerName());
        return customerRepository.save(customer);
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> listCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

}
