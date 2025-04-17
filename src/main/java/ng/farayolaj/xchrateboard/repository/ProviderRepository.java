package ng.farayolaj.xchrateboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ng.farayolaj.xchrateboard.model.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {
}