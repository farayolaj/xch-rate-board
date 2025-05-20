package ng.farayolaj.xchrateboard.controller;

import lombok.RequiredArgsConstructor;
import ng.farayolaj.xchrateboard.dto.RateDto;
import ng.farayolaj.xchrateboard.service.RateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rates")
@RequiredArgsConstructor
public class RateController {

    private final RateService rateService;

    @GetMapping("/latest")
    public List<RateDto> getLatestRates() {
        return rateService.getLatestRates();
    }
}