package hello.csv;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

/**
 * Created by satya on 5/29/17.
 */
public class CustomerInfo {
    @CsvBindByPosition(position = 0)
    //@CsvBindByName(column = "customer_id")
    private String customerId;
    //@CsvBindByName(column = "name")
    @CsvBindByPosition(position = 1)
    private String name;
    //@CsvBindByName(column = "date")
    @CsvBindByPosition(position = 2)
    private String joinedDate;
    //@CsvBindByName(column = "score")
    @CsvBindByPosition(position = 3)
    private String score;
    //@CsvBindByName(column = "weighting")
    @CsvBindByPosition(position = 4)
    private String weighting;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(String joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getWeighting() {
        return weighting;
    }

    public void setWeighting(String weighting) {
        this.weighting = weighting;
    }
}
