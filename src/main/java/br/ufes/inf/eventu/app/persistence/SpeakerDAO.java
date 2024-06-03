package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerDAO extends JpaRepository<Speaker, Long> {
}
