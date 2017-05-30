package hello.data.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by satya on 5/28/17.
 */
@Entity
public class Ranking  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private Double weighting;

   /* @Column(nullable = false)
    private String customerId;
*/
    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    protected Ranking(){

    }

   /* public Ranking(String customerId, Integer score, Double weighting){
        this.customerId = customerId;
        this.score = score;
        this.weighting = weighting;
    }*/

    public Ranking(Customer customer, Integer score, Double weighting){
        this.customer = customer;
        this.score = score;
        this.weighting = weighting;
    }

    public Long getId() {
        return id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Double getWeighting() {
        return weighting;
    }

    public void setWeighting(Double weighting) {
        this.weighting = weighting;
    }

  /*  public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
*/
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
