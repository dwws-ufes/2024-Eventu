package br.ufes.inf.eventu.app.services.interfaces;

import br.ufes.inf.eventu.app.domain.Location;

import java.util.List;

public interface LocationService extends GenericService<Location> {
    List<Location> retrieveLocationRDF();
}
