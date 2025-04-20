package ng.farayolaj.xchrateboard.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ng.farayolaj.xchrateboard.model.Provider;
import ng.farayolaj.xchrateboard.model.ProviderSupportedCurrencyPairs;
import ng.farayolaj.xchrateboard.repository.ProviderRepository;
import ng.farayolaj.xchrateboard.repository.ProviderSupportedCurrencyPairsRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class IngestionService {

    private final ProviderRepository providerRepository;
    private final ProviderSupportedCurrencyPairsRepository pscpRepository;
    private final RateService rateService;

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
                log.info("Fetched rate: {} for pair: {} - {} from {}", rate, pair.getSourceCurrency().getCode(),
                        pair.getDestinationCurrency().getCode(), pair.getProvider().getName());

                // Save the rate to the database
                rateService.saveLatestRate(pair, rate, timestamp);
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