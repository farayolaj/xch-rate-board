package ng.farayolaj.xchrateboard.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link ng.farayolaj.xchrateboard.model.LatestExchange}
 */
public record RateDto(String provider,
                      String sourceCurrency,
                      String targetCurrency, Double rate,
                      LocalDateTime timestamp) implements Serializable {
}