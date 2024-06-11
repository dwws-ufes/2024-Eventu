package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationDAO extends JpaRepository<Location, Long> {
}
