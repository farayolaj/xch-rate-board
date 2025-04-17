package ng.farayolaj.xchrateboard.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exchange")
public class Exchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pscp_id", nullable = false)
    private ProviderSupportedCurrencyPairs providerSupportedCurrencyPair;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private Double rate;

    public Exchange(ProviderSupportedCurrencyPairs providerSupportedCurrencyPair, Double rate, LocalDateTime timestamp) {
        this.providerSupportedCurrencyPair = providerSupportedCurrencyPair;
        this.rate = rate;
        this.timestamp = timestamp;
    }
}