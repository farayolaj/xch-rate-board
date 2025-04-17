package ng.farayolaj.xchrateboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ng.farayolaj.xchrateboard.model.Provider;
import ng.farayolaj.xchrateboard.model.ProviderSupportedCurrencyPairs;

@Repository
public interface ProviderSupportedCurrencyPairsRepository extends JpaRepository<ProviderSupportedCurrencyPairs, Long> {
    List<ProviderSupportedCurrencyPairs> findByProvider(Provider provider);
}