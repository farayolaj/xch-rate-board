package ng.farayolaj.xchrateboard.repository;

import ng.farayolaj.xchrateboard.dto.RateDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ng.farayolaj.xchrateboard.model.LatestExchange;

import java.util.List;

@Repository
public interface LatestExchangeRepository extends JpaRepository<LatestExchange, Long> {
    @Query("""
            SELECT new ng.farayolaj.xchrateboard.dto.RateDto(\
            le.providerSupportedCurrencyPair.provider.name, \
            le.providerSupportedCurrencyPair.sourceCurrency.code, \
            le.providerSupportedCurrencyPair.destinationCurrency.code, \
            le.rate, \
            le.timestamp) \
            FROM LatestExchange le""")
    List<RateDto> findLatestRates();
}