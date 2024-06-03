package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.AttractionTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionTimeDAO extends JpaRepository<AttractionTime, Long> {
}
