package br.ufes.inf.eventu.app.services;

import br.ufes.inf.eventu.app.core.EventuException;
import br.ufes.inf.eventu.app.domain.User;
import br.ufes.inf.eventu.app.domain.enums.UserRole;
import br.ufes.inf.eventu.app.persistence.UserDAO;
import br.ufes.inf.eventu.app.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User save(User model) throws Exception {

        if(userDAO.findByEmail(model.getEmail()) != null)
            throw new EventuException("Email já cadastrado");

        model.setPassword(new BCryptPasswordEncoder().encode(model.getPassword()));
        model.setRole(UserRole.USER);
        return userDAO.save(model);
    }

    @Override
    public User edit(long id, User model) throws Exception {
        return null;
    }

    @Override
    public void delete(long id) throws Exception {

    }
}
