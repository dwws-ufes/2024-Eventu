package br.ufes.inf.eventu.app.services;

import br.ufes.inf.eventu.app.domain.AttractionTime;
import br.ufes.inf.eventu.app.persistence.AttractionTimeDAO;
import br.ufes.inf.eventu.app.services.interfaces.AttractionTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttractionTimeServiceImpl implements AttractionTimeService {

    @Autowired
    private AttractionTimeDAO attractionTimeDAO;

    @Override
    public AttractionTime save(AttractionTime model) throws Exception {
        return attractionTimeDAO.save(model);
    }

    @Override
    public AttractionTime edit(long id, AttractionTime model) throws Exception {
        return null;
    }

    @Override
    public void delete(long id) throws Exception {

    }
}
