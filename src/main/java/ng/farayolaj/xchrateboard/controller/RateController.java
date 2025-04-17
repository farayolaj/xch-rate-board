package ng.farayolaj.xchrateboard.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ng.farayolaj.xchrateboard.dto.RateDto;
import ng.farayolaj.xchrateboard.repository.LatestExchangeRepository;

@RestController
@RequestMapping("/api/rates")
@RequiredArgsConstructor
public class RateController {

  private final LatestExchangeRepository latestExchangeRepository;

  @GetMapping("/latest")
  public List<RateDto> getLatestRates() {
    return latestExchangeRepository.findAll().stream().map(RateDto::fromExchange).toList();
  }
}