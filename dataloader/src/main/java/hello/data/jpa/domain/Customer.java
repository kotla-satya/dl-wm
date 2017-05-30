package hello.data.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by satya on 5/28/17.
 */
@Entity
public class Customer implements Serializable{

    @Id
  //  @GeneratedValue
    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = true)
    private Timestamp joinedDate;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
    private Ranking ranking;

    protected Customer(){

    }

    public Customer(String customerId, String firstName, String lastName, Timestamp joinedDate){
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.joinedDate = joinedDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Timestamp getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Timestamp joinedDate) {
        this.joinedDate = joinedDate;
    }

    public Ranking getRanking() {
        return ranking;
    }

    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }
}
