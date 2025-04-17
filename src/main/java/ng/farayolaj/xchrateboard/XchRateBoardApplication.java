package ng.farayolaj.xchrateboard;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ng.farayolaj.xchrateboard.model.Currency;
import ng.farayolaj.xchrateboard.model.Provider;
import ng.farayolaj.xchrateboard.model.ProviderSupportedCurrencyPairs;
import ng.farayolaj.xchrateboard.repository.CurrencyRepository;
import ng.farayolaj.xchrateboard.repository.ProviderRepository;
import ng.farayolaj.xchrateboard.repository.ProviderSupportedCurrencyPairsRepository;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class XchRateBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(XchRateBoardApplication.class, args);
    }

    @Bean
    CommandLineRunner cmdRunner(CurrencyRepository currencyRepository, ProviderRepository providerRepository,
            ProviderSupportedCurrencyPairsRepository providerSupportedCurrencyPairsRepository) {
        return args -> {
            // Add sample currencies
            Currency usd = new Currency("USD", "United States Dollar", "$");
            Currency eur = new Currency("EUR", "Euro", "€");
            Currency ngn = new Currency("NGN", "Nigerian Naira", "₦");
            Currency gbp = new Currency("GBP", "British Pound Sterling", "£");
            currencyRepository.saveAll(List.of(usd, eur, ngn, gbp));

            // Add sample providers
            Provider wiseProvider = new Provider("Wise", "wise_api_key");
            Provider worldRemitProvider = new Provider("WorldRemit", "worldremit_api_key");
            providerRepository.saveAll(List.of(wiseProvider, worldRemitProvider));

            // Add sample provider-supported currency pairs
            var wiseUSDNGN = new ProviderSupportedCurrencyPairs(wiseProvider, usd, ngn);
            var wiseNGNUSD = new ProviderSupportedCurrencyPairs(wiseProvider, ngn, usd);
            var wiseEURUSD = new ProviderSupportedCurrencyPairs(wiseProvider, eur, usd);
            var wiseUSDEUR = new ProviderSupportedCurrencyPairs(wiseProvider, usd, eur);
            var wiseEURGBP = new ProviderSupportedCurrencyPairs(wiseProvider, eur, gbp);
            var wiseGBPEUR = new ProviderSupportedCurrencyPairs(wiseProvider, gbp, eur);
            var worldRemitUSDNGN = new ProviderSupportedCurrencyPairs(worldRemitProvider, usd, ngn);
            var worldRemitNGNUSD = new ProviderSupportedCurrencyPairs(worldRemitProvider, ngn, usd);
            providerSupportedCurrencyPairsRepository.saveAll(
                    List.of(wiseUSDNGN, wiseNGNUSD, wiseEURUSD, wiseUSDEUR, wiseEURGBP, wiseGBPEUR,
                            worldRemitUSDNGN, worldRemitNGNUSD));
        };
    }
}
