package hello.data.jpa.service;

import hello.data.jpa.domain.Ranking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by satya on 5/29/17.
 */
public interface RankingRepository extends PagingAndSortingRepository<Ranking, Long> {

}
