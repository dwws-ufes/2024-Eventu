package br.ufes.inf.eventu.app.services;

import br.ufes.inf.eventu.app.domain.Attraction;
import br.ufes.inf.eventu.app.persistence.AttractionDAO;
import br.ufes.inf.eventu.app.services.interfaces.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class AttractionServiceImpl implements AttractionService {

    @Autowired
    private AttractionDAO attractionDAO;

    @Override
    public Attraction save(Attraction model) throws Exception {
        model.setCreatedAt(LocalTime.now());
        return attractionDAO.save(model);
    }

    @Override
    public Attraction edit(long id, Attraction model) throws Exception {
        return null;
    }

    @Override
    public void delete(long id) throws Exception {

    }
}
