package br.ufes.inf.eventu.app.services.interfaces;

import java.util.List;

import br.ufes.inf.eventu.app.domain.Attraction;
import br.ufes.inf.eventu.app.domain.Topic;

public interface AttractionService extends GenericService<Attraction> {
    List<Topic> retrieveTopicsRDF();
}
