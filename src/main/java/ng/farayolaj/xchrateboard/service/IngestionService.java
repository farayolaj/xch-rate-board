package ng.farayolaj.xchrateboard.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ng.farayolaj.xchrateboard.model.Exchange;
import ng.farayolaj.xchrateboard.model.LatestExchange;
import ng.farayolaj.xchrateboard.model.Provider;
import ng.farayolaj.xchrateboard.model.ProviderSupportedCurrencyPairs;
import ng.farayolaj.xchrateboard.repository.ExchangeRepository;
import ng.farayolaj.xchrateboard.repository.LatestExchangeRepository;
import ng.farayolaj.xchrateboard.repository.ProviderRepository;
import ng.farayolaj.xchrateboard.repository.ProviderSupportedCurrencyPairsRepository;

@RequiredArgsConstructor
@Service
@Slf4j
public class IngestionService {

    private final ProviderRepository providerRepository;
    private final ProviderSupportedCurrencyPairsRepository pscpRepository;
    private final ExchangeRepository exchangeRepository;
    private final LatestExchangeRepository latestExchangeRepository;

    @Transactional
    @Scheduled(fixedRateString = "30m", initialDelay = 1000) // Runs every 30 minutes
    public void fetchAndStoreRates() {
        List<Provider> providers = providerRepository.findAll();

        for (Provider provider : providers) {
            log.info("Fetching rates for provider: {}", provider.getName());
            List<ProviderSupportedCurrencyPairs> pairs = pscpRepository.findByProvider(provider);

            for (ProviderSupportedCurrencyPairs pair : pairs) {
                log.info("Fetching rate for pair: {} - {}", pair.getSourceCurrency().getCode(),
                        pair.getDestinationCurrency().getCode());

                // Simulate fetching rate from provider's API
                double rate = fetchRateFromProviderAPI(provider, pair);
                var timestamp = LocalDateTime.now();
                var exchange = new Exchange(pair, rate, timestamp);

                if (latestExchangeRepository.existsById(pair.getId())) {
                    // Update existing exchange rate
                    var latestExchange = latestExchangeRepository.findById(pair.getId()).orElseThrow();
                    latestExchange.setRate(rate);
                    latestExchange.setTimestamp(timestamp);
                    latestExchangeRepository.save(latestExchange);
                } else {
                    // Save new exchange rate
                    var latestExchange = new LatestExchange(pair, rate, timestamp);
                    latestExchangeRepository.save(latestExchange);
                }
                exchangeRepository.save(exchange);
            }

            log.info("Rates fetched and stored for provider: {}", provider.getName());
        }
    }

    private double fetchRateFromProviderAPI(Provider provider, ProviderSupportedCurrencyPairs pair) {
        // Mock api call to fetch rate
        // Replace this with actual API integration logic
        return Math.random() * 10; // Random rate for demonstration
    }
}