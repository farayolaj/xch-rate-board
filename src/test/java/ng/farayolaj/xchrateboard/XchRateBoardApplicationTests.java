package ng.farayolaj.xchrateboard;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ng.farayolaj.xchrateboard.dto.RateDto;

@SpringBootTest
@AutoConfigureMockMvc
class XchRateBoardApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getLatestRates_returnsRates() throws Exception {
        var result = mockMvc.perform(get("/api/rates/latest"))
                .andExpect(status().isOk())
                .andReturn();
        String json = result.getResponse().getContentAsString();
        RateDto[] rates = objectMapper.readValue(json, RateDto[].class);
        assertThat(rates).isNotNull();
        if (rates.length > 0) {
            assertThat(rates[0].provider()).isNotBlank();
            assertThat(rates[0].sourceCurrency()).isNotBlank();
            assertThat(rates[0].targetCurrency()).isNotBlank();
            assertThat(rates[0].rate()).isNotNull();
        }
    }
}
