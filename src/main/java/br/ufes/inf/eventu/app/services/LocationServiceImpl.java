package br.ufes.inf.eventu.app.services;

import br.ufes.inf.eventu.app.domain.Location;
import br.ufes.inf.eventu.app.persistence.LocationDAO;
import br.ufes.inf.eventu.app.services.interfaces.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationDAO attractionDAO;

    @Override
    public Location save(Location model) throws Exception {
        return attractionDAO.save(model);
    }

    @Override
    public Location edit(long id, Location model) throws Exception {
        return null;
    }

    @Override
    public void delete(long id) throws Exception {

    }
}
