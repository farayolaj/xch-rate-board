package ng.farayolaj.xchrateboard.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "provider_supported_currency_pairs", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "provider_id", "src_id", "dst_id" })
})
public class ProviderSupportedCurrencyPairs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "src_id", nullable = false)
    private Currency sourceCurrency;

    @ManyToOne
    @JoinColumn(name = "dst_id", nullable = false)
    private Currency destinationCurrency;

    public ProviderSupportedCurrencyPairs(Provider provider, Currency sourceCurrency, Currency destinationCurrency) {
        this.provider = provider;
        this.sourceCurrency = sourceCurrency;
        this.destinationCurrency = destinationCurrency;
    }
}