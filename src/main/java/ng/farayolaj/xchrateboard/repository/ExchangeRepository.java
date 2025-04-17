package ng.farayolaj.xchrateboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ng.farayolaj.xchrateboard.model.Exchange;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
}