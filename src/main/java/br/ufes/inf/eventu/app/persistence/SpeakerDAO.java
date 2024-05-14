package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.Speaker;
import org.springframework.data.repository.CrudRepository;

public interface SpeakerDAO extends CrudRepository<Speaker, Long> {
}
