package ng.farayolaj.xchrateboard.model;

import java.time.LocalDateTime;

import org.springframework.data.domain.Persistable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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
@Table(name = "latest_exchange")
public class LatestExchange implements Persistable<Long> {

    @Id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "pscp_id")
    private ProviderSupportedCurrencyPairs providerSupportedCurrencyPair;

    @Column(nullable = false)
    private Double rate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp;

    public LatestExchange(ProviderSupportedCurrencyPairs providerSupportedCurrencyPair, Double rate,
            LocalDateTime timestamp) {
        this.id = providerSupportedCurrencyPair.getId();
        this.providerSupportedCurrencyPair = providerSupportedCurrencyPair;
        this.rate = rate;
        this.timestamp = timestamp;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}