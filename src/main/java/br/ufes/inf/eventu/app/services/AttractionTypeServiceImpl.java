package br.ufes.inf.eventu.app.services;

import br.ufes.inf.eventu.app.domain.AttractionType;
import br.ufes.inf.eventu.app.persistence.AttractionTypeDAO;
import br.ufes.inf.eventu.app.services.interfaces.AttractionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class AttractionTypeServiceImpl implements AttractionTypeService {

    @Autowired
    private AttractionTypeDAO attractionDAO;

    @Override
    public AttractionType save(AttractionType model) throws Exception {
        return attractionDAO.save(model);
    }

    @Override
    public AttractionType edit(long id, AttractionType model) throws Exception {
        return null;
    }

    @Override
    public void delete(long id) throws Exception {

    }
}
