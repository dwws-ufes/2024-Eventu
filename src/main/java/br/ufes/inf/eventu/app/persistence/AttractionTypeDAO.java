package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.AttractionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttractionTypeDAO extends JpaRepository<AttractionType, Long> {
}
