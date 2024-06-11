package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionDAO extends JpaRepository<Attraction, Long> {
}
