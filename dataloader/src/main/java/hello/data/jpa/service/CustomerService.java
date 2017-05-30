package hello.data.jpa.service;

import hello.data.jpa.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;

/**
 * Created by satya on 5/28/17.
 */
public interface CustomerService {


    Customer addCustomer(
            String customerId,
            String firstName,
            String lastName,
            Timestamp joinedDate,
            Integer score,
            Double weighting);

    Iterable<Customer> getCustomers();


    Page<Customer> getCustomersByPage(Pageable pageable);
}
