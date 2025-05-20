package ng.farayolaj.xchrateboard.service;

import ng.farayolaj.xchrateboard.model.Currency;
import ng.farayolaj.xchrateboard.model.Provider;
import ng.farayolaj.xchrateboard.model.ProviderSupportedCurrencyPairs;
import ng.farayolaj.xchrateboard.repository.ProviderRepository;
import ng.farayolaj.xchrateboard.repository.ProviderSupportedCurrencyPairsRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngestionServiceTest {
    @Mock
    private ProviderRepository providerRepository;
    @Mock
    private ProviderSupportedCurrencyPairsRepository pscpRepository;
    @Mock
    private RateService rateService;
    @InjectMocks
    private IngestionService ingestionService;

    @BeforeAll
    static void setUp() {
        MockitoAnnotations.openMocks(IngestionServiceTest.class);
    }

    @Test
    void fetchAndStoreRates_callsDependencies() {
        Provider provider = new Provider("Wise", "api");
        provider.setId(1L);
        Currency usd = new Currency("USD", "US Dollar", "$ ");
        usd.setId(1L);
        Currency ngn = new Currency("NGN", "Naira", "₦");
        ngn.setId(2L);
        ProviderSupportedCurrencyPairs pair = new ProviderSupportedCurrencyPairs(provider, usd, ngn);
        pair.setId(10L);
        when(providerRepository.findAll()).thenReturn(List.of(provider));
        when(pscpRepository.findByProvider(provider)).thenReturn(List.of(pair));
        doNothing().when(rateService).saveLatestRate(any(), anyDouble(), any());

        ingestionService.fetchAndStoreRates();

        verify(providerRepository).findAll();
        verify(pscpRepository).findByProvider(provider);
        verify(rateService).saveLatestRate(eq(pair), anyDouble(), any());
    }
}
