package ng.farayolaj.xchrateboard.dto;

import java.time.LocalDateTime;

import lombok.Data;
import ng.farayolaj.xchrateboard.model.Exchange;
import ng.farayolaj.xchrateboard.model.LatestExchange;

@Data
public class RateDto {
  private String provider;
  private String srcCurrency;
  private String targetCurrency;
  private Double rate;
  private LocalDateTime timestamp;

  public static RateDto fromExchange(LatestExchange latestExchange) {
    var rateDto = new RateDto();
    var pair = latestExchange.getProviderSupportedCurrencyPair();
    rateDto.setProvider(pair.getProvider().getName());
    rateDto.setSrcCurrency(pair.getSourceCurrency().getCode());
    rateDto.setTargetCurrency(pair.getDestinationCurrency().getCode());
    rateDto.setRate(latestExchange.getRate());
    rateDto.setTimestamp(latestExchange.getTimestamp());
    return rateDto;
  }

  public static RateDto fromExchange(Exchange exchange) {
    var rateDto = new RateDto();
    var pair = exchange.getProviderSupportedCurrencyPair();
    rateDto.setProvider(pair.getProvider().getName());
    rateDto.setSrcCurrency(pair.getSourceCurrency().getCode());
    rateDto.setTargetCurrency(pair.getDestinationCurrency().getCode());
    rateDto.setRate(exchange.getRate());
    rateDto.setTimestamp(exchange.getTimestamp());
    return rateDto;
  }
}