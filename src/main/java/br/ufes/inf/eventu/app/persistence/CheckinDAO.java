package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.Checkin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckinDAO extends JpaRepository<Checkin, Long> {
}
