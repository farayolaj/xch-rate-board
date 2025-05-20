package ng.farayolaj.xchrateboard.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ng.farayolaj.xchrateboard.dto.RateDto;
import ng.farayolaj.xchrateboard.model.Exchange;
import ng.farayolaj.xchrateboard.model.LatestExchange;
import ng.farayolaj.xchrateboard.model.ProviderSupportedCurrencyPairs;
import ng.farayolaj.xchrateboard.repository.ExchangeRepository;
import ng.farayolaj.xchrateboard.repository.LatestExchangeRepository;
import ng.farayolaj.xchrateboard.repository.ProviderSupportedCurrencyPairsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RateService {
    private final ExchangeRepository exchangeRepository;
    private final LatestExchangeRepository latestExchangeRepository;
    private final ProviderSupportedCurrencyPairsRepository pscpRepository;

    @Transactional
    public void saveLatestRate(ProviderSupportedCurrencyPairs pair, double rate, LocalDateTime timestamp) {
        var exchange = new Exchange(pair, rate, timestamp);
        exchangeRepository.save(exchange);

        if (latestExchangeRepository.existsById(pair.getId())) {
            // Update existing exchange rate
            var latestExchange = latestExchangeRepository.findById(pair.getId()).orElseThrow();
            latestExchange.setRate(rate);
            latestExchange.setTimestamp(timestamp);
            latestExchangeRepository.save(latestExchange);
        } else {
            // Save new exchange rate
            var pairRef = pscpRepository.getReferenceById(pair.getId());
            var latestExchange = new LatestExchange(pairRef, rate, timestamp);
            latestExchangeRepository.save(latestExchange);
        }
    }

    public List<RateDto> getLatestRates() {
        return latestExchangeRepository.findLatestRates();
    }
}
