package ng.farayolaj.xchrateboard.service;

import ng.farayolaj.xchrateboard.dto.RateDto;
import ng.farayolaj.xchrateboard.model.LatestExchange;
import ng.farayolaj.xchrateboard.model.ProviderSupportedCurrencyPairs;
import ng.farayolaj.xchrateboard.repository.ExchangeRepository;
import ng.farayolaj.xchrateboard.repository.LatestExchangeRepository;
import ng.farayolaj.xchrateboard.repository.ProviderSupportedCurrencyPairsRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RateServiceTest {
    @Mock
    private ExchangeRepository exchangeRepository;
    @Mock
    private LatestExchangeRepository latestExchangeRepository;
    @Mock
    private ProviderSupportedCurrencyPairsRepository pscpRepository;
    @InjectMocks
    private RateService rateService;

    @BeforeAll
    static void setUp() {
        MockitoAnnotations.openMocks(RateServiceTest.class);
    }

    @Test
    void saveLatestRate_updatesExisting() {
        ProviderSupportedCurrencyPairs pair = mock(ProviderSupportedCurrencyPairs.class);
        when(pair.getId()).thenReturn(1L);
        LocalDateTime now = LocalDateTime.now();
        LatestExchange latest = mock(LatestExchange.class);
        when(latestExchangeRepository.existsById(1L)).thenReturn(true);
        when(latestExchangeRepository.findById(1L)).thenReturn(Optional.of(latest));

        rateService.saveLatestRate(pair, 2.5, now);

        verify(exchangeRepository).save(any());
        verify(latest).setRate(2.5);
        verify(latest).setTimestamp(now);
        verify(latestExchangeRepository).save(latest);
    }

    @Test
    void saveLatestRate_savesNew() {
        ProviderSupportedCurrencyPairs pair = mock(ProviderSupportedCurrencyPairs.class);
        when(pair.getId()).thenReturn(2L);
        LocalDateTime now = LocalDateTime.now();
        when(latestExchangeRepository.existsById(2L)).thenReturn(false);
        when(pscpRepository.getReferenceById(2L)).thenReturn(pair);

        rateService.saveLatestRate(pair, 3.5, now);

        verify(exchangeRepository).save(any());
        ArgumentCaptor<LatestExchange> captor = ArgumentCaptor.forClass(LatestExchange.class);
        verify(latestExchangeRepository).save(captor.capture());
        assertThat(captor.getValue().getRate()).isEqualTo(3.5);
        assertThat(captor.getValue().getTimestamp()).isEqualTo(now);
    }

    @Test
    void getLatestRates_returnsList() {
        List<RateDto> expected = List.of(mock(RateDto.class));
        when(latestExchangeRepository.findLatestRates()).thenReturn(expected);
        List<RateDto> result = rateService.getLatestRates();
        assertThat(result).isEqualTo(expected);
    }
}
