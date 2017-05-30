package hello.data.jpa.service;

import hello.data.jpa.domain.Customer;
import hello.data.jpa.domain.Ranking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by satya on 5/28/17.
 */
@Component("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerReopository customerReopository;

    private final RankingRepository rankingRepository;

    public CustomerServiceImpl(CustomerReopository customerReopository, RankingRepository rankingRepository) {
        this.customerReopository = customerReopository;
        this.rankingRepository = rankingRepository;
    }

    @Override
    public Customer addCustomer(String customerId, String firstName, String lastName, Timestamp joinedDate, Integer score, Double weighting) {
        Customer customer = new Customer(customerId, firstName, lastName, joinedDate);
        customer = this.customerReopository.save(customer);
        Ranking ranking = new Ranking(customer, score, weighting);
        this.rankingRepository.save(ranking);
        return customer;
    }

    @Override
    public Iterable<Customer> getCustomers() {
        Iterable<Customer> customerList = this.customerReopository.findAll();
        return customerList;
    }

    @Override
    public Page<Customer> getCustomersByPage(Pageable pageable){
        return this.customerReopository.findAll(pageable);
    }
}
