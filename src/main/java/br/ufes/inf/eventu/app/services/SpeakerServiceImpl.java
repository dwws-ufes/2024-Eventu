package br.ufes.inf.eventu.app.services;

import br.ufes.inf.eventu.app.domain.Speaker;
import br.ufes.inf.eventu.app.persistence.SpeakerDAO;
import br.ufes.inf.eventu.app.services.interfaces.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpeakerServiceImpl implements SpeakerService {

    @Autowired
    private SpeakerDAO SpeakerDAO;

    @Override
    public Speaker save(Speaker model) throws Exception {
        return SpeakerDAO.save(model);
    }

    @Override
    public Speaker edit(long id, Speaker model) throws Exception {
        return null;
    }

    @Override
    public void delete(long id) throws Exception {

    }
}
