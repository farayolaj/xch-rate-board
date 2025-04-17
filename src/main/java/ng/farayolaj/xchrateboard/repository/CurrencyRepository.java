package ng.farayolaj.xchrateboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ng.farayolaj.xchrateboard.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}