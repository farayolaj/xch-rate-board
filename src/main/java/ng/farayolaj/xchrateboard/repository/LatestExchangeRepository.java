package ng.farayolaj.xchrateboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ng.farayolaj.xchrateboard.model.LatestExchange;

@Repository
public interface LatestExchangeRepository extends JpaRepository<LatestExchange, Long> {
}