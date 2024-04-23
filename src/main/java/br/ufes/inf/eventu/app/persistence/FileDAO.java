package br.ufes.inf.eventu.app.persistence;

import br.ufes.inf.eventu.app.domain.File;
import org.springframework.data.repository.CrudRepository;

public interface FileDAO extends CrudRepository<File, Long> {
}
