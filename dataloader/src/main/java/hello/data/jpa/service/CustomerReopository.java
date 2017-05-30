package hello.data.jpa.service;

import hello.data.jpa.domain.Customer;
import hello.data.jpa.domain.Ranking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * Created by satya on 5/28/17.
 */
public interface CustomerReopository extends PagingAndSortingRepository<Customer, String> {

    Customer findByFirstName(String firstName);

    /*Customer save(Customer customer, Ranking ranking);

    List<Customer> saveAll(List<Customer> customers);

    List<Customer> getAllCustomers();*/

}
