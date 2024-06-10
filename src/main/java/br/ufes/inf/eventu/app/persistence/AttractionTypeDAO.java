package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.AttractionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AttractionTypeDAO extends JpaRepository<AttractionType, Long> {

    @Query("SELECT t FROM AttractionType t WHERE t.name = ?1")
    AttractionType findByName(String name);
}
