package ng.farayolaj.xchrateboard.controller;

import ng.farayolaj.xchrateboard.dto.RateDto;
import ng.farayolaj.xchrateboard.service.RateService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RateController.class)
@ActiveProfiles("test")
class RateControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RateService rateService;

    @Test
    void getLatestRates_returnsRates() throws Exception {
        List<RateDto> rates = List.of(
                new RateDto("Wise", "USD", "EUR", 0.85, LocalDateTime.now()),
                new RateDto("WorldRemit", "GBP", "USD", 1.39, LocalDateTime.now()));
        Mockito.when(rateService.getLatestRates()).thenReturn(rates);

        mockMvc.perform(get("/api/rates/latest"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].provider").value("Wise"))
                .andExpect(jsonPath("$[1].provider").value("WorldRemit"));
    }
}
